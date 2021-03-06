package com.javacodegeeks.abk;

import static com.javacodegeeks.abk.Encrypt.getSalt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>MD5Salted</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class MD5Salted implements Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String encrypt(String text) {
    try {
      final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      final byte[] salt = getSalt();
      msgDigest.update(salt);
      final byte[] textBytes =
          msgDigest.digest(text.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
