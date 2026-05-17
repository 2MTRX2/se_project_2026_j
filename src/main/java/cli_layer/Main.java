package cli_layer;

import picocli.CommandLine;
import picocli.CommandLine.Command;

// Picocli recognizes either list or cart as a cmd
@Command(
    name = "cli",
    subcommands = {CommandListCLI.class, CommandCartCLI.class})
public class Main implements Runnable {
  // Picocli recognizes url as an option, sequence of option and cmd doesn't matter
  @CommandLine.Option(names = "--url", scope = CommandLine.ScopeType.INHERIT)
  String url;

  public static void main(String[] args) {
    String[] testArgs = new String[] {"--url", "http://localhost:3000/shop-api", "list"};

    try {
      CommandLine cmd = new CommandLine(new Main());

      cmd.setExecutionExceptionHandler(
          (ex, commandLine, parseResult) -> {
            ex.printStackTrace(); // Zeigt uns den echten Fehler!
            return 1;
          });

      int exitCode = cmd.execute(testArgs);
      System.exit(exitCode);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    System.out.println("Use a subcommand: list, cart, ...");
  }
}
