package converter;

import java.nio.charset.StandardCharsets;

/**
 * Describe class <code>TestByteToHex</code> here.
 *
 * @author : Linus Fernandes (linusfernandes@gmail.com)
 * @version 1.0
 * @file : TestByteToHex.java
 * @created : Friday May 03, 2019 20:08:16 IST
 * @copyright : Copyright (c) Linus Fernandes
 * @description :
 */
public enum TestByteToHex {
  ;
  static final String BYTE_STRING =
      "@#£&_-()=%?!/:'*\"[]{}<>^¡¿~™®©¢¥€"
          + "$123456789003356788990335688335678888"
          + "))5778889===66://))*£&'/!!))))?:/!?"
          + "?????!//!!!!!!!!!?????      dffvbbfrews"
          + "hjoohgvvvzscvbmmmxxvffew236889uygghhbhjk"
          + "iu65fvbhbbvvvvvdew13yhgftggjioo9hhgggg"
          + "gvvgdWeryhhhDFGJKYRESCHJKKOKVVCSSDVNJH"
          + "FDSSSGHIJJH";

  /**
   * Describe <code>main</code> method here.
   *
   * @param args a <code>String</code> value
   */
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String[] args) {
    final byte[] raw = BYTE_STRING.getBytes(StandardCharsets.UTF_8);
    long start = System.nanoTime();
    final String hex = ByteToHex.getHex(raw);
    long end = System.nanoTime();
    long elapsed = end - start;
    System.out.println("getHex: time " + elapsed);

    start = System.nanoTime();
    final String hex2 = ByteToHex.getHex2(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex2: time " + elapsed);

    start = System.nanoTime();
    final String hex3 = ByteToHex.getHex3(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex3: time " + elapsed);

    start = System.nanoTime();
    final String hex4 = ByteToHex.getHex4(raw);
    end = System.nanoTime();
    elapsed = end - start;
    System.out.println("getHex4: time " + elapsed);
    if (!(hex.equals(hex2) && hex2.equals(hex3) && hex3.equals(hex4)))
      throw new AssertionError("Not all hex conversions are equal");
  }
}
