package com.javacodegeeks.abk;

import static com.javacodegeeks.abk.Encrypt.getSalt;
import static com.lambdaworks.codec.Base64.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
  public String encrypt(String text) {
    String hash = "";
    try {
      final MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      final byte[] salt = getSalt();
      msgDigest.update(salt);
      final byte[] textBytes = msgDigest.digest(text.getBytes());
      hash = String.valueOf(encode(textBytes));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
