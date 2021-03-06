package threads;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("PMD.SystemPrintln")
public enum MultiSemaphoreExample {
  ;

  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public static void main(String[] args) {
    Thread[] thread = new Thread[10];
    PrintQueue printQueue = new PrintQueue();
    Arrays.setAll(thread, i -> new Thread(new Job(printQueue), "Thread" + i));
    for (Thread t: thread)
      t.start();
  }

  static class PrintQueue {
    private final Semaphore semaphore;
    private final boolean[] freePrinters;
    private final Lock lockPrinters;

    PrintQueue() {
      freePrinters = new boolean[] {true, true, true};
      Random random = new Random();
      boolean fair = random.nextBoolean();
      lockPrinters = new ReentrantLock(fair);
      semaphore = new Semaphore(3, random.nextBoolean());
      System.out.println("Semaphore fair: " + semaphore.isFair());
      System.out.println("ReentrantLock fair: " + fair);
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    public void printJob(Object document) {
      try {
        semaphore.acquire();
        int assignedPrinter = getPrinter();
        long duration = (long)(Math.random() * 10);
        System.out.printf(
            "%s: PrintQueue: Printing a Job at %d utilizing %d seconds%n",
            Thread.currentThread().getName(),
            assignedPrinter,
            duration);
        TimeUnit.MILLISECONDS.sleep(duration);
        freePrinters[assignedPrinter] = true;
      } catch (InterruptedException e) {
        System.err.println(e);
      } finally {
        semaphore.release();
      }
    }

    private int getPrinter() throws InterruptedException {
      int printer = -1;
      if (lockPrinters.tryLock(100, TimeUnit.MILLISECONDS)) {
        for (int i = 0; i < freePrinters.length; i++) {
          if (freePrinters[i]) {
            freePrinters[i] = false;
            printer = i;
            break;
          }
        }
        lockPrinters.unlock();
      }
      return printer;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Job implements Runnable {
    private final PrintQueue printQueue;

    Job(PrintQueue printQueue) {
      this.printQueue = printQueue;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public void run() {
      System.out.printf("%s: Going to print a job%n",
                        Thread.currentThread().getName());
      printQueue.printJob(new Object());
      System.out.printf("%s: The document has been printed%n",
                        Thread.currentThread().getName());
    }
  }
}
