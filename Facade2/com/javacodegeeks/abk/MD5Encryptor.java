package com.javacodegeeks.abk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>MD5Encryptor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class MD5Encryptor implements Encrypt {

  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @Override
  public String encrypt(String text) {
    String hash = "";
    try {
      final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      msgDigest.update(text.getBytes());

      final byte[] textBytes = msgDigest.digest();
      hash = Base64.getEncoder().encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("Algorithm not found : " + e.getMessage());
    }
    return hash;
  }
}
