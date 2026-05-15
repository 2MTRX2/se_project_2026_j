package cli_layer;

import api_layer.ConfigService;
import api_layer.VendureClient;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "cart", description = "Displays cart information")
public class CommandCartCLI implements Runnable {

    @CommandLine.ParentCommand
    Main main;

    @Option(names = "--format")
    String format = "table";

    @Override
    public void run() {
        String url = main.url;

        ConfigService configService = new ConfigService();

        String resolvedUrl = configService.getUrl("url", url == null ? "" : url);

        VendureClient client = new VendureClient(resolvedUrl);

        OutputStrategy strategy = StrategyFactory.create(format);

        CommandCart command = new CommandCart(client, strategy);

        command.execute();
    }
}
