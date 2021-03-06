package command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

public class PasswordDemo implements Runnable {
  @Option(names = "--password:file")
  File passwordFile;

  @Option(names = "--password:env")
  String passwordEnvironmentVariable;

  @Option(names = "--password", interactive = true)
  String password;

  @Spec
  CommandSpec spec;

  @SuppressWarnings("checkstyle:returncount")
  @Override
  public void run() {
    try {
      if (password != null) {
        login(password);
        return;
      }
      if (passwordEnvironmentVariable != null) {
        login(System.getenv(passwordEnvironmentVariable));
        return;
      }
      if (passwordFile != null) {
        login(new String(Files.readAllBytes(passwordFile.toPath()),
                         StandardCharsets.UTF_8));
        return;
      }
      throw new ParameterException(spec.commandLine(), "Password required");
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private void login(String pwd) {
    System.out.printf("Password: %s%n", pwd);
  }

  public static void main(String[] args) {
    new CommandLine(new PasswordDemo()).execute(args);
  }
}
