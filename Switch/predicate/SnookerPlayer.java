package predicate;

public class SnookerPlayer implements Player {
  private String name;

  public SnookerPlayer(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
