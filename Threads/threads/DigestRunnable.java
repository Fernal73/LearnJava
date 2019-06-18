package threads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

// for DatatypeConverter; requires Java 6 or JAXB 1.0
public class DigestRunnable implements Runnable {
  private String filename;

  public DigestRunnable(String filename) {
    this.filename = filename;
  }

  @Override
  @SuppressWarnings("PMD.EmptyWhileStmt")
  public void run() {
    try {
      InputStream in = Files.newInputStream(Paths.get(filename));
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1) ;
      din.close();
      byte[] digest = sha.digest();
      StringBuilder result = new StringBuilder(filename);
      result.append(": ").append(DatatypeConverter.printBase64Binary(digest));
      System.out.println(result);
    } catch (IOException | NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  public static void main(String[] args) {
    System.out.println("Into DigestRunnable...");
    for (String filename : args) {
      Thread t = new Thread(new DigestRunnable(filename));
      t.start();
    }
  }
}
