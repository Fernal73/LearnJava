package javapuzzles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public enum SerialKiller {
  ;

  public static void main(String[] args) {
    Sub sub = new Sub(666);
    sub.checkInvariant();
    Sub copy = (Sub)deepCopy(sub);
    checkCopy(copy);
  }

  public static void checkCopy(Sub copy) {
    copy.checkInvariant();
  }

  // Copies its argument via serialization (See Puzzle 83)
  public static Object deepCopy(Object obj) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      new ObjectOutputStream(bos).writeObject(obj);
      ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bin).readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class Super implements Serializable {
  private static final long serialVersionUID = 1L;

  final Set<Super> set = new HashSet<>();
}

@SuppressWarnings({"checkstyle:onetoplevelclass", "PMD.ShortClassName"})
final class Sub extends Super {
  private static final long serialVersionUID = 1L;
  private final int id;

  Sub(int id) {
    this.id = id;
    set.add(this);
    // Establish invariant
  }

  public void checkInvariant() {
    if (!set.contains(this))
      throw new AssertionError("invariant violated");
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Sub && id == ((Sub)o).id;
  }
}
