package javapuzzles;

// https://twitter.com/heinzkabutz/status/1174714312151187456
public final class Sleepy {
  // clang-format off
  {
    Thread.sleep(1000);
  }
  // clang-format on

  private Sleepy() throws InterruptedException {
  }

  public static void main(String... args) {
    try {
      Sleepy sy = new Sleepy();
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
  }
}