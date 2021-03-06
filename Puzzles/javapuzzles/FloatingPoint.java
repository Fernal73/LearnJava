package javapuzzles;

import static java.lang.Math.abs;

import java.util.Random;

@SuppressWarnings("PMD.SystemPrintln")
public final class FloatingPoint {
  private FloatingPoint() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    loopAddFloat();
    loopAddDouble();
    nutty();
    finite();
    countInverses();
    countInversesD();
    computeSums();
  }

@SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private static void loopAddFloat() {
    float x = 0.1f;
    float y = 0;
    for (int i = 0; i < 10; i++)
      y += x;
    if (y == x)
      System.out.println("Algebra is truth.");
    else
      System.out.println("Not here.");
    System.out.println(1.0 - y);
    if (abs(1.0 - y) < 0.000_001)
      System.out.println("Close enough for government work.");
    else
      System.out.println("Not even close.");
  }

@SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private static void loopAddDouble() {
    double x = 0.1f;
    double y = 0;
    for (int i = 0; i < 10; i++)
      y += x;
    if (y == x)
      System.out.println("Algebra is truth.");
    else
      System.out.println("Not here.");
    System.out.println(1.0 - y);
    if (abs(1.0 - y) < 0.000_001)
      System.out.println("Close enough for government work.");
    else
      System.out.println("Not even close.");
  }

  private static void nutty() {
    double x = 1.25e8;
    double y = x + 7.5e-10;
    if (x == y)
      System.out.println("Am I nuts or what?");
  }

@SuppressWarnings("PMD.UnusedAssignment")
  private static void finite() {
    double a;
    int i;

    a = 0.2;
    a += 0.1;
    a -= 0.3;

    for (i = 0; a < 1.0; i++)
      a += a;

    System.out.printf("i=%d, a=%f\n", i, a);
  }

@SuppressWarnings({"PMD.UnusedAssignment",
"PMD.DontUseFloatTypeForLoopIndices"})
  private static void countInverses() {

    float y;
    float z;
    int count = 0;
    for (float x = 0.0f; x < 1000.0f; x++) {
      y = 1.0f / x;
      z = y * x;
      if (z != 1.0f)
        count++;
    }
    System.out.println("Found " + count);
  }

@SuppressWarnings("PMD.UnusedAssignment")
  private static void countInversesD() {
    double y, z;
    int count = 0;
    for (double x = 0.0; x < 1000.0; x++) {
      y = 1.0 / x;
      z = y * x;
      if (z != 1.0)
        count += 1;
    }
    System.out.println("Found " + count);
  }

  private static void computeSums() {
    Random random = new Random();
    double[] nums = new double[10_000];
    for (int i = 0; i < 10_000; i++)
      nums[i] = random.nextGaussian();
    double sum = 0.0;
    for (int i = 0; i < 10_000; i++)
      sum += nums[i];
    System.out.println("Forward sum = " + sum);
    sum = 0.0;
    for (int i = 10_000; i > 0; i--)
      sum += nums[i - 1];
    System.out.println("Backward sum = " + sum);
  }
}
