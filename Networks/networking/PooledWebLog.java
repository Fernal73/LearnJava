package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class PooledWebLog {
  private static final int NUM_THREADS = 4;
  private static final Charset UTF_8 = StandardCharsets.UTF_8;

  private PooledWebLog() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    Queue<LogEntry> results = new LinkedList<>();
    try (BufferedReader in =
             Files.newBufferedReader(Paths.get(args[0]), UTF_8);) {
      for (String entry = in.readLine(); entry != null; entry = in.readLine()) {
        LookupTask task = new LookupTask(entry);
        Future<String> future = executor.submit(task);
        LogEntry result = new LogEntry(entry, future);
        results.add(result);
      }
    }

    // Start printing the results. This blocks each time a result isn't ready.
    for (LogEntry result: results) {
      try {
        System.out.println(result.future.get());
      } catch (InterruptedException | ExecutionException ex) {
        System.out.println(result.original);
      }
    }
    executor.shutdown();
  }

  private static class LogEntry {
    String original;
    Future<String> future;

    LogEntry(String original, Future<String> future) {
      this.original = original;
      this.future = future;
    }
  }
}
