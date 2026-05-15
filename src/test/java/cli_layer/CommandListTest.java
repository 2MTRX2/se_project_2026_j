package cli_layer;

import api_layer.Product;
import api_layer.VendureClient;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.*;

public class CommandListTest {
  @Test
  void shouldFetchProductsAndPrintFormattedOutput() {

    VendureClient client = mock(VendureClient.class);
    OutputStrategy strategy = mock(OutputStrategy.class);

    Command command = new CommandList(client, strategy);

    List<Product> products = List.of(new Product("Laptop", 1200));
    when(client.getProducts()).thenReturn(products);
    when(strategy.format(products)).thenReturn("formatted-output");

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    try {
      command.execute();
      verify(client).getProducts();
      verify(strategy).format(products);
    } finally {
      System.setOut(System.out);
    }
  }
}
