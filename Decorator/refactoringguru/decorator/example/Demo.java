package refactoringguru.decorator.example;

import java.io.IOException;
import java.util.logging.Logger;
import refactoringguru.decorator.example.decorators.CompressionDecorator;
import refactoringguru.decorator.example.decorators.DataSource;
import refactoringguru.decorator.example.decorators.DataSourceDecorator;
import refactoringguru.decorator.example.decorators.EncryptionDecorator;
import refactoringguru.decorator.example.decorators.FileDataSource;

@SuppressWarnings("PMD.ShortClassName")
public final class Demo {
  private static final Logger LOGGER = Logger.getLogger(Demo.class.getName());

  private Demo() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    try {
      String salaryRecords =
          "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
      DataSourceDecorator encoded = new CompressionDecorator(
          new EncryptionDecorator(new FileDataSource("OutputDemo.txt")));
      encoded.writeData(salaryRecords);
      DataSource plain = new FileDataSource("OutputDemo.txt");

      System.out.println("- Input ----------------");
      System.out.println(salaryRecords);
      System.out.println("- Encoded --------------");
      System.out.println(plain.readData());
      System.out.println("- Decoded --------------");
      System.out.println(encoded.readData());
    } catch (IOException ioe) {
      LOGGER.severe(ioe.getMessage());
    }
  }
}
