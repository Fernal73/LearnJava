package streams;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public enum ParallelArrays {
  ;
  // clang-format off
  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    long[] arrayOfLong = new long[20_000];
    Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1_000_000));
    Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
    System.out.println();
    Arrays.parallelSort(arrayOfLong);
    Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
    System.out.println();
  }
  // clang-format on
}
