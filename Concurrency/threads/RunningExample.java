package threads;

import threads.HelperClass.WaitingThread;
import threads.HelperClass.WakingThread;

@SuppressWarnings("PMD.SystemPrintln")
public enum RunningExample {
  ;

  public static void main(String[] args) {
    try {
      Thread waitThread = new WaitingThread();
      Thread wakingThread = new WakingThread();

      // Start the execution.
      waitThread.start();
      wakingThread.start();

      // Wait for all threads to terminate.
      waitThread.join(10_00);
      wakingThread.join(10_00);
    } catch (InterruptedException ex) {
      System.err.println("An InterruptedException was caught: "
                         + ex.getMessage());
    }
  }
}
