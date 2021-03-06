package pmdtests;

import java.util.concurrent.ThreadLocalRandom;

public final class ThrowSwitch {
  private ThrowSwitch() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  @SuppressWarnings({"fallthrough", "PMD.MissingBreakInSwitch"})
  public static void main(String... args) {
    int errCode = getSimulatedErrorCode();

    switch (errCode) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
        throw new AssertionError("Error code: " + errCode);
      default:
        System.exit(0);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static int getSimulatedErrorCode() {
    return ThreadLocalRandom.current().nextInt(0, 6);
  }
}
