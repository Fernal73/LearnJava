package networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class NonblockingSingleFileHttpServer {
  private final ByteBuffer contentBuffer;
  private final int port;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public NonblockingSingleFileHttpServer(ByteBuffer data,
                                         String encoding,
                                         String mimeType,
                                         int port) {
    this.port = port;
    String header = "HTTP/1.0 200 OK\r\n"
                    + "Server: NonblockingSingleFileHTTPServer\r\n"
                    + "Content-length: " + data.limit() + "\r\n"
                    + "Content-type: " + mimeType + "\r\n\r\n";
    byte[] headerData = header.getBytes(Charset.forName("US-ASCII"));
    ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
    buffer.put(headerData);
    buffer.put(data);
    buffer.flip();
    this.contentBuffer = buffer;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() throws IOException {
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    ServerSocket serverSocket = serverChannel.socket();
    Selector selector = Selector.open();
    InetSocketAddress localPort = new InetSocketAddress(port);
    serverSocket.bind(localPort);
    serverChannel.configureBlocking(false);
    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    while (true) {
      selector.select();
      Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
      while (keys.hasNext()) {
        SelectionKey key = keys.next();
        keys.remove();
        try {
          if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel)key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
          } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel)key.channel();
            ByteBuffer buffer = (ByteBuffer)key.attachment();
            if (buffer.hasRemaining()) {
              channel.write(buffer);
            } else {
              // we're done
              channel.close();
            }
          } else if (key.isReadable()) {
            // Don't bother trying to parse the HTTP header.
            // Just read something.
            SocketChannel channel = (SocketChannel)key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            channel.read(buffer);

            // switch channel to write-only mode
            key.interestOps(SelectionKey.OP_WRITE);
            key.attach(contentBuffer.duplicate());
          }
        } catch (IOException ex) {
          key.cancel();
          try {
            key.channel().close();
          } catch (IOException cex) {
            System.err.println(cex);
          }
        }
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static int getPort(String... args) {
    try {
      int port = Integer.parseInt(args[1]);
      if (port < 1 || port > 65_535)
        port = 80;
      return port;
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
      return 80;
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static String getEncoding(String... args) {
    if (args.length > (1 + 1))
      return args[2];
    return "UTF-8";
  }

  @SuppressWarnings({"PMD.AvoidLiteralsInIfCondition", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println(
          "Usage: java NonblockingSingleFileHTTPServer file port encoding");
      return;
    }
    try {
      // read the single file to serve
      String contentType =
          URLConnection.getFileNameMap().getContentTypeFor(args[0]);
      Path file = FileSystems.getDefault().getPath(args[0]);
      byte[] data = Files.readAllBytes(file);
      ByteBuffer input = ByteBuffer.wrap(data);

      // set the port to listen on
      int port = getPort();
      String encoding = getEncoding();
      NonblockingSingleFileHttpServer server =
          new NonblockingSingleFileHttpServer(
              input, encoding, contentType, port);
      server.run();
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
