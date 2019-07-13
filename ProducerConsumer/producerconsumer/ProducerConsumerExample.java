package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public enum ProducerConsumerExample {
  ;

  public static void main(String[] args) {

    try {
      Buffer buffer = new Buffer(2);

      Thread producerThread = new Thread(() -> {
        try {
          int value = 0;
          while (true) {
            buffer.add(value);
            System.out.println("Produced " + value);
            value++;
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      });

      Thread consumerThread = new Thread(() -> {
        try {
          while (true) {
            int value = buffer.poll();
            System.out.println("Consumed " + value);
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      });

      Thread terminatorThread = new Thread(() -> {
        try {
          Thread.sleep(10_000);
          System.err.println("Exiting program...");
          System.exit(0);
        } catch (InterruptedException e) {
          System.out.println(e);
        }
      });
      producerThread.start();
      consumerThread.start();
      terminatorThread.start();
      producerThread.join();
      consumerThread.join();
      terminatorThread.join();
    } catch (InterruptedException ex) {
      System.err.println(ex);
    }
  }

  static class Buffer {
    private Queue<Integer> list;
    private int size;

    Buffer(int size) {
      this.list = new LinkedList<>();
      this.size = size;
    }

    public void add(int value) throws InterruptedException {
      synchronized (this) {
        while (list.size() >= size) {
          wait();
        }
        list.add(value);
        notifyAll();
      }
    }

    public int poll() throws InterruptedException {
      synchronized (this) {
        while (list.size() == 0) {
          wait();
        }
        int value = list.poll();
        notifyAll();
        return value;
      }
    }
  }
}