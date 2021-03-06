package javapuzzles;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("PMD.SystemPrintln")
public enum DatingGame {
  ;

  @SuppressWarnings({"deprecation", "PMD.LawOfDemeter"})
  public static void main(String[] unused) {
    Calendar cal = Calendar.getInstance();
    cal.set(1999, 12, 31);

    // Year, Month, Day
    System.out.print(cal.get(Calendar.YEAR) + " ");
    Date d = cal.getTime();
    System.out.println(d.getDay());
    altMain(args);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void altMain(String... unused) {
    Calendar cal = Calendar.getInstance();
    cal.set(1999, Calendar.DECEMBER, 31);
    System.out.print(cal.get(Calendar.YEAR) + " ");
    System.out.println(cal.get(Calendar.DAY_OF_MONTH));
  }
}
