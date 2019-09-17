package streams;

import java.util.stream.IntStream;

public final class PrimeNumberCount {

  public static void main(String... args) {

    int range = 100;
    try {
      range = Integer.parseInt(args[0]);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      System.err.println("Invalid or no range specified. Defaulting to 100.");
    }

    PrimeNumberCount pmc = new PrimeNumberCount();
    pmc.count(range);
  }

  public void count(int range) {
    final long count = IntStream.range(1, range)
                           .parallel()
                           .filter(number -> isPrime(number))
                           .count();
    System.out.println("Count - " + count);
  }

  public boolean isPrime(final int number) {
    return number > 1
        && IntStream.rangeClosed(2, (int)Math.sqrt(number))
               .noneMatch(divisor -> number % divisor == 0);
  }
}