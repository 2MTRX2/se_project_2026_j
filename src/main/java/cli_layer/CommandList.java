package cli_layer;

import api_layer.VendureClient;

public class CommandList implements Command {
    private final VendureClient vendureClient;
    private final OutputStrategy outputStrategy;

    public CommandList(VendureClient client, OutputStrategy strategy) {
        this.vendureClient = client;
        this.outputStrategy = strategy;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
