package com.javacodegeeks.patterns.strategypattern;

import java.util.Locale;

public class LowerTextFormatter implements TextFormatter {
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void format(String text) {
    System.out.println("[LowerTextFormatter]: "
                       + text.toLowerCase(Locale.getDefault()));
  }
}
