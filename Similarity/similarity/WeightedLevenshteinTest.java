package similarity;

import info.debatty.java.stringsimilarity.WeightedLevenshtein;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

public enum WeightedLevenshteinTest {
  ;
  private static List<Pair<Character, Character>> keyPairs = new ArrayList<>();

  static {
    keyPairs.add(new Pair<>('q', 'w'));
    keyPairs.add(new Pair<>('w', 'e'));
    keyPairs.add(new Pair<>('e', 'r'));
    keyPairs.add(new Pair<>('r', 't'));
    keyPairs.add(new Pair<>('t', 'y'));
    keyPairs.add(new Pair<>('y', 'u'));
    keyPairs.add(new Pair<>('u', 'i'));
    keyPairs.add(new Pair<>('i', 'o'));
    keyPairs.add(new Pair<>('o', 'p'));

    keyPairs.add(new Pair<>('a', 's'));
    keyPairs.add(new Pair<>('s', 'd'));
    keyPairs.add(new Pair<>('d', 'f'));
    keyPairs.add(new Pair<>('f', 'g'));
    keyPairs.add(new Pair<>('g', 'h'));
    keyPairs.add(new Pair<>('h', 'j'));
    keyPairs.add(new Pair<>('j', 'k'));
    keyPairs.add(new Pair<>('k', 'l'));

    keyPairs.add(new Pair<>('z', 'x'));
    keyPairs.add(new Pair<>('x', 'c'));
    keyPairs.add(new Pair<>('c', 'v'));
    keyPairs.add(new Pair<>('v', 'b'));
    keyPairs.add(new Pair<>('b', 'n'));
    keyPairs.add(new Pair<>('n', 'm'));
  }

  public static void main(String... args) {
    WeightedLevenshtein wl = new WeightedLevenshtein((c1, c2) -> {
      // The cost for substituting 't' and 'r' is considered
      // smaller as these 2 are located next to each other
      // on a keyboard
      Pair<Character, Character> pair = new Pair<>(c1, c2);
      Pair<Character, Character> switched = new Pair<>(c2, c1);
      if (keyPairs.contains(pair) || keyPairs.contains(switched))
        return 0.5;

      // For most cases, the cost of substituting 2 characters
      // is 1.0
      return 1.0;
    });

    System.out.println("\nWeighted Levenshtein: \n");
    System.out.println(wl.distance("String1", "Srring2"));
    System.out.println(wl.distance("My string", "My $tring"));
    System.out.println(wl.distance("column", "columns"));
    System.out.println(wl.distance("col", "col1"));
    System.out.println(wl.distance("col", "col12"));
    System.out.println(wl.distance("col", "col123"));
    System.out.println(wl.distance("qaertyuiop", "warryyiipp"));
  }
}
