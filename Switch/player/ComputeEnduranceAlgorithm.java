package player;

public class ComputeEnduranceAlgorithm {

  public static int basicEndurance(int delta) {
    return 2 * delta;
  }

  private ComputeEnduranceAlgorithm() {
    throw new IllegalStateException("Private constructor");
  }
}
