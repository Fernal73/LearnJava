package com.javacodegeeks.abk;

import static converter.ByteToHex.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA224 implements Encrypt {
  public String encrypt(String text) {
    String hash = "";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-224");
      byte[] textBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
      hash = getHex4(textBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
