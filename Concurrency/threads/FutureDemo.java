package threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to show how to use Future in Java. Future allows to write asynchronous code in Java,
 * where Future promises result to be available in future
 *
 * @author Javin
 */
@SuppressWarnings("PMD.SystemPrintln")
public enum FutureDemo {
  ;
  private static final ExecutorService THREAD_POOL =
      Executors.newFixedThreadPool(3);

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      FactorialCalculator task = new FactorialCalculator(10);
      System.out.println("Submitting Task ...");

      Future<Long> future = THREAD_POOL.submit(task);

      System.out.println("Task is submitted");

      while (!future.isDone()) {
        System.out.println("Task is not completed yet....");
        TimeUnit.MILLISECONDS.sleep(1);
        // sleep for 1 millisecond before checking again
      }

      System.out.println("Task is completed, let's check result");
      long factorial = future.get();
      System.out.println("Factorial of 10 is : " + factorial);

      THREAD_POOL.shutdown();
    } catch (InterruptedException | ExecutionException e) {
      System.err.println(e);
    }
  }

  private static class FactorialCalculator implements Callable<Long> {
    private final int number;

    FactorialCalculator(int number) {
      this.number = number;
    }

    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public Long call() {
      try {
        return factorial(number);
      } catch (InterruptedException ex) {
        Logger.getLogger(FutureDemo.class.getName())
            .log(Level.SEVERE, null, ex);
      }
      return 0L;
    }

    @SuppressWarnings("checkstyle:hiddenfield")
    private long factorial(int number) throws InterruptedException {
      if (number <= 0)
        throw new IllegalArgumentException("Number must be greater than zero: "
                                           + number);
      int num = number;
      long result = 1;
      while (num > 0) {
        TimeUnit.MILLISECONDS.sleep(1);

        // adding delay for example
        result = result * num;
        num--;
      }
      return result;
    }
  }
}
