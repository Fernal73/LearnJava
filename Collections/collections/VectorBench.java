package collections;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public enum VectorBench {
  ;

  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test(false);
      test(true);
    }
  }

  private static void test(boolean parallel) {
    IntStream range = IntStream.range(1, 100_000_000);
    if (parallel)
      range = range.parallel();
    long time = System.nanoTime();
    try {
      ThreadLocal<List<Integer>> lists = ThreadLocal.withInitial(() -> {
        List<Integer> result = new Vector<>();
        for (int i = 0; i < 1024; i++)
          result.add(i);
        return result;
      });
      int sum = 1023 * 1024 / 2 * (100_000_000 / 1024);
      int mod = (100_000_000 % 1024) - 1;
      sum += mod * ++mod / 2;

      System.out.println(sum);
      time = System.nanoTime() - time;
      System.out.printf(
          "%dms%n",  time / 1_000_000);


    time = System.nanoTime();
      System.out.println("Sum = "
          + range.map(i -> {
            return lists.get().get(i & 1023);
          })
          .sum()
          );
    } finally {
      time = System.nanoTime() - time;
      System.out.printf(
          "%s %dms%n", parallel ? "parallel" : "sequential", time / 1_000_000);
    }
  }
}
