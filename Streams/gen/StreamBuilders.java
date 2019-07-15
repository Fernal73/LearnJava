package gen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum StreamBuilders {
  ;

  public static void main(String[] args) {
    streamOf();
    arrayOf();
    listStream();
    streamGenerate();
    tokenStreams();
    filterStream();
    filterStreamToArray();
    coreStream();
  }

  public static void streamOf() {
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    stream.forEach(p -> System.out.println(p));
  }

  public static void arrayOf() {
    Stream<Integer> stream =
        Stream.of(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    stream.forEach(p -> System.out.println(p));
  }

  public static void listStream() {
    List<Integer> list = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      list.add(i);
    }

    Stream<Integer> stream = list.stream();
    stream.forEach(p -> System.out.println(p));
  }

  public static void streamGenerate() {
    // clang-format off
    Stream<Date> stream = Stream.generate(Date::new);
    // clang-format on
    stream.limit(50).forEach(System.out::println);
  }

  public static void tokenStreams() {
    IntStream stream = "12345_abcdefg".chars();
    stream.forEach(p -> System.out.println(p));

    // OR

    Stream<String> stream1 = Stream.of("A$B$C".split("\\$"));
    stream1.forEach(p -> System.out.println(p));
  }

  public static void filterStream() {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      list.add(i);
    }
    Stream<Integer> stream = list.stream();
    List<Integer> evenNumbersList =
        stream.filter(i -> i % 2 == 0).collect(Collectors.toList());
    System.out.println(evenNumbersList);
  }

  public static void filterStreamToArray() {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      list.add(i);
    }
    Stream<Integer> stream = list.stream();
    // clang-format off
    Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
    // clang-format on
    for (Integer num: evenNumbersArr)
      System.out.println(num);
  }

  public static void coreStream() {
    List<String> memberNames = new ArrayList<>();
    memberNames.add("Amitabh");
    memberNames.add("Shekhar");
    memberNames.add("Aman");
    memberNames.add("Rahul");
    memberNames.add("Shahrukh");
    memberNames.add("Salman");
    memberNames.add("Yana");
    memberNames.add("Lokesh");
    memberNames.stream()
        .filter(s -> s.startsWith("A"))
        .forEach(System.out::println);

    memberNames.stream()
        .filter(s -> s.startsWith("A"))
        .map(String::toUpperCase)
        .forEach(System.out::println);

    memberNames.stream()
        .sorted()
        .map(String::toUpperCase)
        .forEach(System.out::println);

    // clang-format off
    List<String> memNamesInUppercase =
        memberNames.stream().sorted().map(String::toUpperCase).collect(Collectors.toList());
    // clang-format on
    System.out.print(memNamesInUppercase);

    boolean matchedResult =
        memberNames.stream().anyMatch(s -> s.startsWith("A"));

    System.out.println(matchedResult);

    matchedResult = memberNames.stream().allMatch(s -> s.startsWith("A"));

    System.out.println(matchedResult);

    matchedResult = memberNames.stream().noneMatch(s -> s.startsWith("A"));

    System.out.println(matchedResult);

    long totalMatched =
        memberNames.stream().filter(s -> s.startsWith("A")).count();

    System.out.println(totalMatched);

    Optional<String> reduced =
        memberNames.stream().reduce((s1, s2) -> s1 + "#" + s2);

    reduced.ifPresent(System.out::println);

    String firstMatchedName =
        memberNames.stream().filter(s -> s.startsWith("L")).findFirst().get();

    System.out.println(firstMatchedName);
  }
}
