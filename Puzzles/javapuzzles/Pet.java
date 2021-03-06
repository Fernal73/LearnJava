package javapuzzles;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"PMD.ShortClassName", "PMD.DoNotCallSystemExit"})
public class Pet {
  public final String name;
  public final String food;
  public final String sound;

  public Pet(String name, String food, String sound) {
    this.name = name;
    this.food = food;
    this.sound = sound;
  }

  public void eat() {
    System.out.println(name + ": Mmmmm, " + food);
  }

  public void play() {
    System.out.println(name + ": " + sound + " " + sound);
  }

  public void sleep() {
    System.out.println(name + ": Zzzzzzz...");
  }

  public void live() {
    new Thread(() -> eatPlaySleep()).start();
  }

  private void eatPlaySleep() {
    while (true) {
      eat();
      play();
      sleep();
      try {
        TimeUnit.MILLISECONDS.sleep(1000);
      } catch (InterruptedException ie) {
        System.err.println(ie);
      }
    }
  }

  public static void main(String[] args) {
    new Pet("Fido", "beef", "Woof").live();
    try {
      TimeUnit.MILLISECONDS.sleep(5000);
    } catch (InterruptedException ie) {
      System.err.println(ie);
    }
    System.exit(0);
  }
}
