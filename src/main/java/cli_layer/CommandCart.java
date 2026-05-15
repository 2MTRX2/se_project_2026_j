package cli_layer;

import api_layer.VendureClient;

public class CommandCart implements Command {
  VendureClient vendureClient;
  OutputStrategy outputStrategy;

  public CommandCart(VendureClient vendureClient, OutputStrategy outputStrategy) {
    this.vendureClient = vendureClient;
    this.outputStrategy = outputStrategy;
  }

  @Override
  public void execute() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
