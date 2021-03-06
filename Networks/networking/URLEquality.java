package networking;

import java.net.MalformedURLException;
import java.net.URL;

public final class URLEquality {
  private URLEquality() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    try {
      URL www = new URL("http://www.ibiblio.org/");
      URL ibiblio = new URL("http://ibiblio.org/");
      if (ibiblio.equals(www))
        System.out.println(ibiblio + " is the same as " + www);
      else
        System.out.println(ibiblio + " is not the same as " + www);
    } catch (MalformedURLException ex) {
      System.err.println(ex);
    }
  }
}
