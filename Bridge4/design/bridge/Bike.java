package design.bridge;

/**
 * Describe class <code>Bike</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
public class Bike extends Vehicle {
  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void manufacture() {
    System.out.println("Manufacturing Bike...");
    workshops.stream().forEach(workshop -> workshop.work(this));
    System.out.println("Done.");
    System.out.println();
  }

  @Override
  public int minWorkTime() {
    return 5;
  }
}
