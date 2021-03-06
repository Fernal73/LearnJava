package com.javacodegeeks.client;

// @SuppressWarnings("PMD.UnusedImports")
import static com.javacodegeeks.facade.EncryptorFacade.EncryptionType.*; // NOPMD

import com.javacodegeeks.facade.EncryptorFacade;

/**
 * Describe class <code>Client</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public enum Client {
  ;
  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    String myText = "Encrypt this text";
    System.out.println("Text to be encrypted: " + myText);
    final EncryptorFacade e = new EncryptorFacade();
    System.out.println("MD5 encryption");
    System.out.println(e.encrypt(MD5, myText));
    System.out.println("MD5 salted encryption");
    System.out.println(e.encrypt(MD5SALTED, myText));
    System.out.println("SHA encryption");
    System.out.println(e.encrypt(SHA, myText));
    System.out.println("SHA salted encryption");
    System.out.println(e.encrypt(SHASALTED, myText));
    System.out.println("SHA-224 encryption");
    System.out.println(e.encrypt(SHA224, myText));
    System.out.println("SHA-224 salted encryption");
    System.out.println(e.encrypt(SHA224SALTED, myText));
    System.out.println("SHA256 encryption");
    System.out.println(e.encrypt(SHA256, myText));
    System.out.println("SHA256Salted encryption");
    System.out.println(e.encrypt(SHA256SALTED, myText));
    System.out.println("SHA384 encryption");
    System.out.println(e.encrypt(SHA384, myText));
    System.out.println("SHA384Salted encryption");
    System.out.println(e.encrypt(SHA384SALTED, myText));
    System.out.println("SHA512 encryption");
    System.out.println(e.encrypt(SHA512, myText));
    System.out.println("SHA512Salted encryption");
    System.out.println(e.encrypt(SHA512SALTED, myText));
    System.out.println("BCrypt encryption");
    System.out.println(e.encrypt(BCRYPT, myText));
    System.out.println("SCrypt encryption");
    System.out.println(e.encrypt(SCRYPT, myText));
    System.out.println("PBKDF encryption");
    System.out.println(e.encrypt(PBKDF, myText));
  }
}
