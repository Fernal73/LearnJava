package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public final class ChargenClient {
  private static final int DEFAULT_PORT = 19;

  private ChargenClient() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java ChargenClient host [port]");
      return;
    }
    int port;
    try {
      port = Integer.parseInt(args[1]);
      System.out.printf("Using %d.%n", port);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      port = DEFAULT_PORT;
      System.err.printf(
          "Invalid input %s for port. Using %d.%n", args[1], port);
    }
    SocketAddress address = new InetSocketAddress(args[0], port);
    try (SocketChannel client = SocketChannel.open(address)) {
      ByteBuffer buffer = ByteBuffer.allocate(74);
      WritableByteChannel out = Channels.newChannel(System.out);
      while (client.read(buffer) != -1) {
        buffer.flip();
        out.write(buffer);
        buffer.clear();
      }
    } catch (IOException ex) {
      System.err.println("Error writing to server: " + ex.getMessage());
    }
  }
}
