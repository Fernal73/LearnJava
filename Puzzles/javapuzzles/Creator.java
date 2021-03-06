package javapuzzles;

@SuppressWarnings("PMD.SystemPrintln")
public enum Creator {
  ;

@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++)
      new Creature();
    System.out.println(Creature.getNumCreated());
  }
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD"})
class Creature {
  private static long numCreated;

  Creature() {
    synchronized (Creature.class) {
      numCreated++;
    }
  }

  public static long getNumCreated() {
    synchronized (Creature.class) {
      return numCreated;
    }
  }
}
