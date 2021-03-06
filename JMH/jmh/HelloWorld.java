package jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

@SuppressWarnings("all")
public class HelloWorld {

  @Benchmark
  public void wellHelloThere() {
    // this method was intentionally left blank.
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
                      .include(HelloWorld.class.getSimpleName())
                      .verbosity(VerboseMode.EXTRA)
                      .forks(0)
                      .build();
    new Runner(opt).run();
  }
}
