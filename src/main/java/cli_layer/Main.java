package cli_layer;

import picocli.CommandLine;
import picocli.CommandLine.Command;

// Picocli recognizes either list or cart as a cmd
@Command(
        name = "cli",
        subcommands = {
                CommandListCLI.class,
                CommandCartCLI.class
        }
)
public class Main implements Runnable {
    // Picocli recognizes url as an option, sequence of option and cmd doesn't matter
    @CommandLine.Option(names = "--url", scope = CommandLine.ScopeType.INHERIT)
    String url;

    @Override
    public void run() {
        System.out.println("Use a subcommand: list, cart, ...");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}