package CLI_Layer;
import API_Layer.ConfigService;
import API_Layer.VendureClient;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "list")
public class CommandListCLI implements Runnable {
    @CommandLine.ParentCommand
    Main main;

    @Option(names = "--format")
    String format = "table";

    @Override
    public void run() {
        String url = main.url;

        ConfigService config = new ConfigService();

        String resolvedUrl = config.getUrl("url", url == null ? "" : url);

        VendureClient client = new VendureClient(resolvedUrl);

        OutputStrategy strategy = StrategyFactory.create(format);

        CommandList command = new CommandList(client, strategy);

        command.execute();
    }
}