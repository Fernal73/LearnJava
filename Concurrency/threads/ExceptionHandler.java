package threads;

@SuppressWarnings("PMD.SystemPrintln")
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void uncaughtException(Thread t, Throwable e) {
    System.out.printf("An exception has been captured%n");
    System.out.printf("Thread: %s%n", t.getId());
    System.out.printf("Thread Name: %s%n", t.getName());
    System.out.printf("Thread Priority: %d%n", t.getPriority());
    System.out.printf("Thread Daemon: %s%n", t.isDaemon());
    System.out.printf("Thread Description: %s%n", t.toString());
    System.out.printf(
        "Exception: %s: %s%n", e.getClass().getName(), e.getMessage());
    System.out.printf("Stack Trace: %n");
    e.printStackTrace(System.out);
    System.out.printf("Thread status: %s%n", t.getState());
  }
}
