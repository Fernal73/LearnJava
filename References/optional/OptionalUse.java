package optional;

import java.util.Optional;

public enum OptionalUse {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    Optional<String> fullName = Optional.ofNullable(null);
    System.out.println("Full Name is set? " + fullName.isPresent());
    System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
    System.out.println(
        fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    Optional<String> firstName = Optional.of("Tom");
    System.out.println("First Name is set? " + firstName.isPresent());
    System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
    System.out.println(
        firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    System.out.println();
  }
}
