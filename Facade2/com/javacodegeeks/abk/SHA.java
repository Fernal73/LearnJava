package com.javacodegeeks.abk;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Describe class <code>SHA</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class SHA implements Encrypt {
  /**
   * Describe <code>encrypt</code> method here.
   *
   * @param text a <code>String</code> value
   * @return a <code>String</code> value
   */
  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public String encrypt(String text) {
    try {
      final MessageDigest digest = MessageDigest.getInstance("SHA");
      final byte[] textBytes =
          digest.digest(text.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(textBytes);
    } catch (NoSuchAlgorithmException e) {
      throw new AssertionError("Algorithm not found : ", e);
    }
  }
}
