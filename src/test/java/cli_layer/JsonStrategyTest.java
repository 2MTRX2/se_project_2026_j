package cli_layer;

import static org.junit.jupiter.api.Assertions.*;

import api_layer.Product;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JsonStrategyTest {
  @Test
  void shouldFormatProductsAsJson() {
    OutputStrategy strategy = new JsonStrategy();

    List<Product> products = List.of(new Product("Laptop", 1200));

    String result = strategy.format(products);

    assertTrue(result.contains("Laptop"));
    assertTrue(result.contains("1200"));
    assertTrue(result.startsWith("["));
  }
}
