package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public enum MultiEchoServer {
  ;
  private static ServerSocket serverSocket;
  private static final int PORT = 1234;

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    try {
      serverSocket = new ServerSocket(PORT);
    } catch (IOException ioEx) {
      System.out.println("\nUnable to set up port!");
      System.exit(1);
    }

    while (true) {
      try {
        Socket client = serverSocket.accept();
        System.out.println("\nNew client accepted.\n");

        // Create a thread to handle communication with
        // this client and pass the constructor for this
        // thread a reference to the relevant socket…
        ClientHandler handler = new ClientHandler(client);
        handler.start();
        // As usual, method calls run.
      } catch (IOException ioe) {
        System.err.println(ioe);
      }
    }
  }

  static class ClientHandler extends Thread {
    private final Socket client;
    private Scanner input;
    private PrintWriter output;

    ClientHandler(Socket socket) {
      // Set up reference to associated socket…
      client = socket;
      try {
        input =
            new Scanner(client.getInputStream(), StandardCharsets.UTF_8.name());
        output = new PrintWriter(
            new OutputStreamWriter(client.getOutputStream(),
                                   StandardCharsets.UTF_8.name()),
            true);
      } catch (IOException ioEx) {
        System.err.println(ioEx);
      }
    }

    @Override
    public void run() {
      String received = "";
      while (!"QUIT".equals(received)) {
        if (input.hasNext()) {
          // Accept message from client on
          // the socket's input stream…
          received = input.nextLine();

          // Echo message back to client on
          // the socket's output stream…
          output.println("ECHO: " + received);
        }
        // Repeat above until 'QUIT' sent by client…
      }
      try {
        if (client != null) {
          System.out.println("Closing down connection…");
          client.close();
        }
      } catch (IOException ioEx) {
        System.out.println("Unable to disconnect!");
      }
    }
  }
}
