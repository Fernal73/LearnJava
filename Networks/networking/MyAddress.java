package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class MyAddress {
  private MyAddress() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      InetAddress address = InetAddress.getLocalHost();
      System.out.println(address);
      System.out.println(address.getHostAddress());
    } catch (UnknownHostException ex) {
      System.out.println("Could not find this computer's address: "
                         + ex.getMessage());
    }
  }
}
