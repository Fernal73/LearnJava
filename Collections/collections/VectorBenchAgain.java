package collections;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.IntStream;

public enum VectorBenchAgain {
  ;

  public static void main(String... args) {
    for (int i = 0; i < 10; i++) {
      test(false);
      test(true);
    }
  }

  private static void test(boolean parallel) {
    Set<List<Integer>> vectors =
        Collections.newSetFromMap(Collections.synchronizedMap(
            // should not rely on a mutating hashCode()
            new IdentityHashMap<>()));
    IntStream range = IntStream.range(1, 100_000_000);
    if (parallel)
      range = range.parallel();
    long time = System.nanoTime();
    try {
      ThreadLocal<List<Integer>> lists = ThreadLocal.withInitial(() -> {
        List<Integer> result = new Vector<>();
        // avoid GC during run
        vectors.add(result);
        for (int i = 0; i < 1024; i++)
          result.add(i);
        return result;
      });
      System.out.println("Sum = "
          + range.map(i -> lists.get().get(i & 1023)).sum());
    } finally {
      time = System.nanoTime() - time;
      System.out.printf(
          "%s %dms%n", parallel ? "parallel" : "sequential", time / 1_000_000);
    }
  }
}
