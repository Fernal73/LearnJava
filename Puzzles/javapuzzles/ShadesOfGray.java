package javapuzzles;

@SuppressWarnings("PMD")
public enum ShadesOfGray {
  ;

  public static void main(String[] args) {
    System.out.println(X.Y.Z);
    System.out.println(Ex.Why.z);
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
enum X {
  ;
  // clang-format off
  static class Y {
    static String Z = "Black";
  }  // clang-format on

  static C Y = new C();
}

@SuppressWarnings("checkstyle:onetoplevelclass")
enum Ex {
  ;
  static See y = new See();

  // clang-format off
  static class Why {
    static String z = "Black";
  }
  // clang-format on
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class See {
  String z = "White";
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class C {
  String Z = "White";
}
