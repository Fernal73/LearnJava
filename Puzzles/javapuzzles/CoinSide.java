package javapuzzles;

import java.util.Random;

@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class CoinSide {
  private static Random rnd = new Random();
  
  public static CoinSide flip() {
    return rnd.nextBoolean() ? Heads.INSTANCE : Tails.INSTANCE;
  }
  
  public static void main(String[] args) {
    System.out.println(flip());
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
final class Heads extends CoinSide {
  public static final Heads INSTANCE = new Heads();
  
  private Heads() {
    super();
  }
  
  @Override
  public String toString() {
    return "heads";
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
final class Tails extends CoinSide {
  public static final Tails INSTANCE = new Tails();
  
  private Tails() {
    super();
  }

  @Override
  public String toString() {
    return "tails";
  }
}