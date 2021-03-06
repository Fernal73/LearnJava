package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class SourceViewer {

  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  private SourceViewer() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    if (args.length > 0) {
      try (
          Reader r = new InputStreamReader(
              new BufferedInputStream(new URL(args[0]).openStream()), UTF_8);) {
        int c;
        while ((c = r.read()) != -1)
          System.out.print((char)c);
      } catch (MalformedURLException ex) {
        System.err.println(args[0] + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
