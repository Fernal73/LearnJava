package networking;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer {
  public static void main(String[] args) {
    if (args.length > 0) {
      try (Reader r =
          new InputStreamReader(new BufferedInputStream((new URL(args[0])).openStream())); ) {
        int c;
        while ((c = r.read()) != -1) System.out.print((char) c);
      } catch (MalformedURLException ex) {
        System.err.println(args[0] + " is not a parseable URL");
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
