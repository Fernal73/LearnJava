package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.FormatLogger;

@SuppressWarnings("PMD.AvoidUsingVolatile")
public abstract class UDPServer implements Runnable {
  private final int bufferSize;

  // in bytes
  private final int port;
  private final FormatLogger logger =
      new FormatLogger(Logger.getLogger(UDPServer.class.getCanonicalName()));
  private volatile boolean isShutDown;

  public UDPServer(int port, int bufferSize) {
    this.bufferSize = bufferSize;
    this.port = port;
  }

  public UDPServer(int port) {
    this(port, 8192);
  }

  @SuppressWarnings("checkstyle:returncount")
  @Override
  public void run() {
    try (DatagramSocket socket = new DatagramSocket(port)) {
      socket.setSoTimeout(10_000);

      // check every 10 seconds for shutdown
      while (true) {
        if (isShutDown)
          return;
        DatagramPacket incoming =
            new DatagramPacket(new byte[bufferSize], bufferSize);
        try {
          socket.receive(incoming);
          this.respond(socket, incoming);
        } catch (SocketTimeoutException ex) {
          if (isShutDown)
            return;
        } catch (IOException ex) {
          logger.log(Level.WARNING, "%s: %s", ex.getMessage(), ex);
        }
      }
      // end while
    } catch (SocketException ex) {
      logger.log(Level.SEVERE, "Could not bind to port %d: %s", port, ex);
    }
  }

  public abstract void respond(DatagramSocket socket, DatagramPacket request)
      throws IOException;

  public void shutDown() {
    this.isShutDown = true;
  }

  protected static int readPort(String portVal, int defaultPort) {
    try {
      return Integer.parseInt(portVal);
    } catch (NumberFormatException nfe) {
      return defaultPort;
    }
  }
}
