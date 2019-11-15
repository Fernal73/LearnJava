package command;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
    name = "checksum",
    mixinStandardHelpOptions = true,
    version = "checksum 3.0")
class CheckSum implements Callable<Integer> {

  @Parameters(index = "0",
              description = "The file whose checksum to calculate.")
  private File file;

  @Option(names = {"-a", "--algorithm"},
          description = "MD5, SHA-1, SHA-256, ...")
  private String algorithm = "SHA-1";

  public static void main(String[] args) throws Exception {
    int exitCode = new CommandLine(new CheckSum()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    byte[] fileContents = Files.readAllBytes(file.toPath());
    byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
    System.out.println(Base64.getEncoder().encode(digest));
    System.out.printf("%0" + (digest.length * 2) + "x%n",
                      new BigInteger(1, digest));
    return 0;
  }
}
