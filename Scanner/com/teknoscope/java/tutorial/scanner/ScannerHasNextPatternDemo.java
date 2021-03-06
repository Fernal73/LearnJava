package com.teknoscope.java.tutorial.scanner;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * This is a java example source code that shows the
 * usage of hasNext(Patten pattern)
 * method of Scanner class. This code scans only tokens that consists
 * of characters on the alphabet only
 * Java Tutorial
 */
public final class ScannerHasNextPatternDemo {
  private ScannerHasNextPatternDemo() {
    throw new IllegalStateException("Private constructor invoked in class: "
        + getClass());
  }

  public static void main(String[] args) {
    // Initialize Scanner object
    Scanner scan = new Scanner("Vince1    Gandhi    Albert   1234567");

    // declare the delimiter to be used by Scanner object
    scan.useDelimiter("\\p{javaWhitespace}+");

    /*Initialize the String pattern which
    signifies that the String token contains
    characters of the alphabet only*/
    Pattern pattern = Pattern.compile("([A-Za-z]*)");

    printNames(scan, pattern);

    // closing the scanner stream
    scan.close();
  }

  @SuppressWarnings("PMD.SystemPrintln")
  static void printNames(Scanner scan, Pattern pattern) {
    while (scan.hasNext()) {
      // check if the token consists of declared pattern
      if (scan.hasNext(pattern))
        System.out.println(scan.next());
      else
        scan.next();
    }
  }
}
