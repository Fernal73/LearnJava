package hack;

import click.CodeTalk;

public enum TypeIt {
  ;

  @SuppressWarnings({"PMD.SystemPrintln", "PMD.MissingOverride"})
  private static class ClickIt extends CodeTalk {
    void printMessage() {
      System.out.println("Hack");
    }
  }

  public static void main(String[] args) {
    ClickIt clickit = new ClickIt();
    clickit.doIt();
  }
}
