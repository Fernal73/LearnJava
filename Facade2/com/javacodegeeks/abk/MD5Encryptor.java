package com.javacodegeeks.abk;

import static converter.ByteToHex.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest msgDigest = MessageDigest.getInstance("MD5");
      msgDigest.update(text.getBytes());

      byte[] textBytes = msgDigest.digest();
      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
