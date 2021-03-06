package statepattern;

public final class Acknowledged implements PackageState {
  // Singleton
  private static Acknowledged instance = new Acknowledged();

  private Acknowledged() {
    // empty constructor
  }

  public static Acknowledged getInstance() {
    return instance;
  }

  // Business logic and state transition
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void updateState(IDeliveryContext ctx) {
    System.out.println("Package is acknowledged !!");
    ctx.setCurrentState(Shipped.getInstance());
  }
}
