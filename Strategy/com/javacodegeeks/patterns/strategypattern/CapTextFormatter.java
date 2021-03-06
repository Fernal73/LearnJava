package com.javacodegeeks.patterns.strategypattern;

import java.util.Locale;

public class CapTextFormatter implements TextFormatter {
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void format(String text) {
    System.out.println("[CapTextFormatter]: "
                       + text.toUpperCase(Locale.getDefault()));
  }
}
