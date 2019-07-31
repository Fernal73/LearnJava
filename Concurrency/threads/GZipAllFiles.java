package threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class GZipAllFiles {
  public static final int THREAD_COUNT = 4;

  private GZipAllFiles() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    for (String filename: args) {
      zipFile(filename, pool);
    }
    pool.shutdown();
  }

  private static void zipFile(String filename, ExecutorService pool) {
    File f = new File(filename);
    if (f.exists()) {
      zip(f, pool);
    }
  }

  private static void zip(File f, ExecutorService pool) {
    if (f.isDirectory()) {
      File[] files = f.listFiles();
      for (File file: files) {
        if (!file.isDirectory()) {  // don't recurse directories
          submitZipTask(file, pool);
        }
      }
    } else {
      submitZipTask(f, pool);
    }
  }

  private static void submitZipTask(File file, ExecutorService pool) {
    Runnable task = new GZipRunnable(file);
    pool.submit(task);
  }
}