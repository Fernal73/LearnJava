package networking;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DaytimeServer {
  public static final int PORT = 1313;

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private DaytimeServer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(PORT)) {
      while (true) {
        try (Socket connection = server.accept();
             Writer out =
                 new OutputStreamWriter(connection.getOutputStream(), UTF_8);) {
          Date now = new Date();
          SimpleDateFormat format =
              new SimpleDateFormat("yy-MM-dd hh:mm:ss Z", Locale.getDefault());
          out.write(ProcessHandle.current().pid() + " " + format.format(now)
                    + "\\r\\n");
          out.flush();
        } catch (IOException ex) {
          System.err.println(ex.getMessage());
        }
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
