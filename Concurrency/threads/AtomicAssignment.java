package threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AtomicAssignment implements Runnable {
  private static final SimpleDateFormat SDF =
      new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS", Locale.getDefault());
  private static Map<String, String> configuration = new HashMap<>();

  @Override
  public void run() {
    for (int i = 0; i < 10_000; i++) {
      Map<String, String> currConfig = configuration;
      String value1 = currConfig.get("key-1");
      String value2 = currConfig.get("key-2");
      String value3 = currConfig.get("key-3");
      if (!(value1.equals(value2) && value2.equals(value3))) {
        throw new IllegalStateException("Values are not equal.");
      }
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }

  public static void readConfig() {
    Map<String, String> newConfig = new HashMap<>();
    Date now = new Date();
    synchronized (SDF) {
      newConfig.put("key-1", SDF.format(now));
      newConfig.put("key-2", SDF.format(now));
      newConfig.put("key-3", SDF.format(now));
    }
    configuration = newConfig;
  }

  @SuppressWarnings("PMD.UnnecessaryFullyQualifiedName")
  public static void main(String[] args) {
    readConfig();
    Thread configThread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10_000; i++) {
          readConfig();
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            System.err.println(e);
          }
        }
      }
    }, "configuration-thread");
    configThread.start();
    Thread[] threads = new Thread[5];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new AtomicAssignment(), "thread-" + i);
      threads[i].start();
    }
    try {
      for (Thread thread: threads) {
        thread.join();
      }
      configThread.join();
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
    System.out.println("[" + Thread.currentThread().getName()
                       + "] All threads have finished.");
  }
}
