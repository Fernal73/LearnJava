package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public enum SourceViewer2 {
  ;

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        // Open the URLConnection for reading
        URL u = new URL(args[0]);
        URLConnection uc = u.openConnection();
        try (InputStream raw = uc.getInputStream();
             InputStream buffer = new BufferedInputStream(raw);
             Reader reader = new InputStreamReader(buffer, UTF_8);) {
          int c;
          while ((c = reader.read()) != -1)
            System.out.print((char)c);
        }
      } catch (MalformedURLException ex) {
        System.err.println(args[0] + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
