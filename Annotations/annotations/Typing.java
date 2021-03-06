package annotations;

import java.util.ArrayList;
import java.util.List;

public class Typing {
  @SuppressWarnings({"unused", "PMD.DataflowAnomalyAnalysis"})
  public static void main(String... args) {
    // type def
    @TypeAnnotated
    String cannotBeEmpty;

    // type
    List<@TypeAnnotated String> myList = new ArrayList<>();

    // values
    Object myObject = new @TypeAnnotated Object();
    System.out.println(myList);
  }

  // in method params
  @SuppressWarnings("PMD.SystemPrintln")
  public void methodAnnotated(@TypeAnnotated int parameter) {
    System.out.println("do nothing");
  }
}
