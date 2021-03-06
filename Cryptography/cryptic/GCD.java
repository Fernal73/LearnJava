package cryptic;

import java.util.Random;

public enum GCD {
  ;

  public static long gcdRecursive(long x, long y) {
    if (y == 0)
      return x;
    return gcdRecursive(y, x % y);
  }

  @SuppressWarnings("PMD.AvoidReassigningParameters")
  public static long gcdIterative(long x, long y) {
    while (y != 0) {
      long r = x % y;
      x = y;
      y = r;
    }
    return x;
  }

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static long[] gcdKnuth(long x, long y) {
    final long[] u = {1, 0, x};
    final long[] v = {0, 1, y};
    final long[] t = new long[3];

    // at all stages, if w is any of the 3 vectors u, v or t, then
    // x*w[0] + y*w[1] = w[2] (this is verified by "check" below)
    // vector initializations: u = {1, 0, u}; v = {0, 1, v};
    while (v[2] != 0) {
      long q = u[2] / v[2];

      // vector equation: t = u - v*q
      t[0] = u[0] - v[0] * q;
      t[1] = u[1] - v[1] * q;
      t[2] = u[2] - v[2] * q;
      if (check(x, y, t))
        return new long[0];

      // vector equation: u = v;
      u[0] = v[0];
      u[1] = v[1];
      u[2] = v[2];
      if (check(x, y, u))
        return new long[0];

      // vector equation: v = t;
      v[0] = t[0];
      v[1] = t[1];
      v[2] = t[2];
      if (check(x, y, v))
        return new long[0];
    }
    return u;
  }

  public static boolean check(long x, long y, long... w) {
    return x * w[0] + y * w[1] != w[2];
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    Random rnd = new Random();
    long first = Math.abs(rnd.nextLong() % Long.MAX_VALUE);
    long second = Math.abs(rnd.nextLong() % Long.MAX_VALUE);

    System.out.printf("Finding GCD of %d and %d : %n", first, second);
    long gcd = gcdRecursive(first, second);
    long gcdIter = gcdIterative(first, second);

    assert gcd == gcdIter;

    System.out.println("GCD: " + gcd);

    long[] u = gcdKnuth(first, second);
    for (long l: u)
      System.out.println(l);
    if (u.length > 0)
      assert u[0] * first + u[1] * second == u[2];
  }
}
