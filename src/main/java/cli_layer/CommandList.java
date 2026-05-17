package cli_layer;

import api_layer.Product;
import api_layer.VendureClient;
import java.util.List;

public class CommandList implements Command {
  private final VendureClient vendureClient;
  private final OutputStrategy outputStrategy;

  public CommandList(VendureClient client, OutputStrategy strategy) {
    this.vendureClient = client;
    this.outputStrategy = strategy;
  }

  @Override
  public void execute() {
    try {
      List<Product> products = vendureClient.getProducts();

      outputStrategy.render(products);

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
