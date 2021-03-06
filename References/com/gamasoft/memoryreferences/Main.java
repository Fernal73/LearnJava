package com.gamasoft.memoryreferences;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public enum Main {
  ;
  public static final int HOW_MANY = 50_000;

  @SuppressWarnings("PMD.DoNotCallGarbageCollectionExplicitly")
  public static void main(String[] args) {
    // -XX:+HeapDumpOnOutOfMemoryError -Xmx4096mlex
    // try with
    // -XX:+UnlockExperimentalVMOptions -XX:G1MaxNewSizePercent=75
    // -XX:G1NewSizePercent=50 -XX:+UseG1GC or with
    // -XX:+CMSParallelRemarkEnabled, -XX:+UseConcMarkSweepGC, -XX:+UseParNewGC,
    // -XX:ParallelGCThreads=8, -XX:SurvivorRatio=25
    System.out.println("Start!");

    printMem();
    System.out.println("Press ^C to break!");
    System.out.println("\n\nUsed mem");

    long start = System.currentTimeMillis();

    ReferenceQueue<HeavyList> queue = new ReferenceQueue<>();

    Set<Reference<HeavyList>> references = new HashSet<>();
    allocationLoop(queue, references, 100);
    System.out.println("Total time " + (System.currentTimeMillis() - start));

    System.gc();
    int removed = removeRefs(queue, references);

    System.out.println(
        "Final used mem "
            + getUsedMem()
            + "    Refs removed "
            + removed
            + "   left "
            + references.size());
  }

  @SuppressWarnings("PMD.NullAssignment")
  private static void allocationLoop(
      ReferenceQueue<HeavyList> queue, Set<Reference<HeavyList>> references, int howManyTimes) {
    HeavyList head = new HeavyList(0, null);
    HeavyList oldTail = head;
    for (int i = 0; i < howManyTimes; i++) {
      final HeavyList newTail = allocate(HOW_MANY, oldTail);

      HeavyList curr = oldTail.next;

      addReferences(queue, references, curr);

      deallocateHalf(head);

      int removed = removeRefs(queue, references);

      //  System.gc();   //uncomment this line to comparing with forced gc
      System.out.println(
          "used mem "
              + getUsedMem()
              + "    Refs removed "
              + removed
              + "   left "
              + references.size());

      oldTail = newTail;
    }
    head = null;
    oldTail = null;
  }

  private static void addReferences(
      ReferenceQueue<HeavyList> queue, Set<Reference<HeavyList>> references, HeavyList curr) {
    HeavyList current = curr;
    while (current != null) {
      //                Reference<HeavyList> reference = new
      //                SoftReference<>(curr, queue);
      Reference<HeavyList> reference = new WeakReference<>(curr, queue);

      //                Reference<HeavyList> reference = new
      //                PhantomReference<>(curr, queue);
      references.add(reference);

      current = current.getNext();
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static long getUsedMem() {
    Runtime rt = Runtime.getRuntime();
    return rt.totalMemory() - rt.freeMemory();
  }

  // clang-format off
  private static int removeRefs(
      ReferenceQueue<HeavyList> queue, Set<Reference<HeavyList>> references) {
    int removed = 0;
    while (true) {
      Reference<? extends HeavyList> r = queue.poll();
      if (r == null) break;
      references.remove(r);
      removed++;
    }
    return removed;
  }

  // clang-format on
  private static void deallocateHalf(HeavyList head) {
    HeavyList curr = head;

    while (curr != null) {
      curr.dropNext();
      curr = curr.getNext();
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void printMem() {
    Runtime rt = Runtime.getRuntime();
    /* Total number of processors or cores available to the JVM */
    System.out.println(
        "Available processors (cores): " + rt.availableProcessors());

    /* Total amount of free memory available to the JVM */
    System.out.println("Free memory (bytes): " + rt.freeMemory());

    /* This will return Long.MAX_VALUE if there is no preset limit */
    long maxMemory = rt.maxMemory();

    /* Maximum amount of memory the JVM will attempt to use */
    System.out.println(
        "Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

    /* Total memory currently in use by the JVM */
    System.out.println("Total memory (bytes): " + rt.totalMemory());
  }

  private static HeavyList allocate(int howMany, HeavyList startFrom) {
    HeavyList curr = startFrom;
    for (int i = 0; i < howMany; i++) {
      curr = new HeavyList(i, curr);
    }
    return curr;
  }

  private static int count(HeavyList list) {
    HeavyList curr = list;
    int tot = 0;
    while (curr != null) {
      tot++;
      curr = curr.getNext();
    }
    return tot;
  }

  private static class HeavyList {
    byte[] mega = new byte[1000];
    private HeavyList next;

    HeavyList(int number, HeavyList prev) {
      for (int i = 0; i < mega.length; i++) {
        mega[i] = (byte) (number % 256);
      }
      if (prev != null) {
        prev.next = this;
      }
    }

    public HeavyList getNext() {
      return next;
    }

    public HeavyList dropNext() {
      if (next == null || next.next == null) 
        throw new NullPointerException("Null object encountered in method dropNext of class "
            + getClass());
      HeavyList res = next;
      next = next.next;
      return res;
    }
  }
}
