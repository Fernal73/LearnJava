package javapuzzles;

public enum TestSystemProperty {
  ;

  @SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    System.out.printf(
        "System line separator() : %s",
        System.lineSeparator().replace("\r", "\\r").replace("\n", "\\n"));
    System.out.printf("System line separator property : %s",
                      System.getProperty("line.separator")
                          .replace("\r", "\\r")
                          .replace("\n", "\\n"));
    System.setProperty("line.separator", "\r\n");

    System.out.printf(
        "System line separator() : %s",
        System.lineSeparator().replace("\r", "\\r").replace("\n", "\\n"));
    System.out.printf("System line separator property : %s",
                      System.getProperty("line.separator")
                          .replace("\r", "\\r")
                          .replace("\n", "\\n"));

    System.setProperty("line.separator", "\r");

    System.out.printf(
        "System line separator() : %s",
        System.lineSeparator().replace("\r", "\\r").replace("\n", "\\n"));
    System.out.printf("System line separator property : %s",
                      System.getProperty("line.separator")
                          .replace("\r", "\\r")
                          .replace("\n", "\\n"));
  }
}
