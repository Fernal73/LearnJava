package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public enum ConsumerClient {
  ;
  private static InetAddress host;
  private static final int PORT = 1234;
  private static final Random RANDOM = new Random();
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    try {
      host = InetAddress.getLocalHost();
    } catch (UnknownHostException uhEx) {
      System.out.println("\nHost ID not found!\n");
      System.exit(1);
    }
    sendMessages();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void sendMessages() {
    try (Socket socket = new Socket(host, PORT);
         Scanner networkInput = new Scanner(socket.getInputStream(), UTF_8);
         PrintWriter networkOutput = new PrintWriter(
             new OutputStreamWriter(socket.getOutputStream(), UTF_8), true);
         Scanner userEntry = new Scanner(System.in, UTF_8);) {
      String message = "";
      while (!"0".equals(message)) {
        System.out.print("Enter '1' ('0' to exit): ");
        if (userEntry.hasNext()) {
          message = userEntry.nextLine();

          // Send message to server on the
          // socket's output stream…
          // Accept response from server on the
          // socket's intput stream…
          networkOutput.println(message);
        }
        if (networkInput.hasNext())
          // Display server's response to user…
          System.out.println("\nSERVER> " + networkInput.nextLine());

        // 'Sleep' for 0-5 seconds…
        TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(5000));
      }
    } catch (IOException | InterruptedException ex) {
      System.err.println(ex);
      Thread.currentThread().interrupt();
    }
  }
}
