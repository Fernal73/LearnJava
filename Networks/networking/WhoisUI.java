package networking;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WhoisUI {

  private Whois server;
  private String searchFor;
  private String searchAt;
  private boolean exactMatch;
  private String whois;
  private String searchIn;

  public WhoisUI(Whois server) {
    this.server = server;
  }

  public WhoisUI() {
  }

  public void setServer(Whois whois) {
    this.server = whois;
  }

  public void getUserInput(String... args) {
    Scanner scanner = new Scanner(System.in);
    getWhoisInput(scanner);
    getExactMatchInput(scanner);
    getSearchForInput(scanner);
    getSearchInInput(scanner);
    getSearchAtInput(scanner);
  }

  private void getWhoisInput(Scanner scanner) {
    System.out.println("Enter entity name for whois record: ");
    if (scanner.hasNext())
      whois = scanner.next().trim();
  }

  private void getSearchForInput(Scanner scanner) {
    System.out.println("Enter entity type to search for: ");
    Pattern pattern = Whois.SearchFor.getRegexPattern();
    System.out.println(pattern);
    do {
      if (scanner.hasNext()) {
        searchFor = scanner.next();
      } else {
        scanner.next();
      }
    } while (!searchFor.matches(pattern.toString()));
    System.out.println(searchFor);
  }

  private void getSearchAtInput(Scanner scanner) {
    System.out.println("Enter name of registry server to search at: ");
    if (scanner.hasNext())
      searchAt = scanner.next().trim();
  }

  private void getSearchInInput(Scanner scanner) {
    System.out.println("Enter records to search in: ");
    Pattern pattern = Whois.SearchIn.getRegexPattern();
    System.out.println(pattern);
    do {
      if (scanner.hasNext()) {
        searchIn = scanner.next();
      } else {
        scanner.next();
      }
    } while (!searchIn.matches(pattern.toString()));
    System.out.println(searchIn);
  }

  private void getExactMatchInput(Scanner scanner) {
    System.out.println("Is search to be an exact match?");
    if (scanner.hasNextBoolean())
      exactMatch = scanner.nextBoolean();
  }

  public static void main(String[] args) {
    try {
      WhoisUI ui = new WhoisUI();
      ui.getUserInput(args);
      Whois server = new Whois(ui.searchAt);
      server.lookUpNames(
          ui.whois,
          Whois.SearchFor.valueOf(
              ui.searchFor.toUpperCase(Locale.getDefault())),
          Whois.SearchIn.valueOf(ui.searchIn.toUpperCase(Locale.getDefault())),
          ui.exactMatch);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
