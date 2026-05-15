package cli_layer;

import api_layer.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class JsonStrategyTest {
    @Test
    void shouldFormatProductsAsJson() {
        OutputStrategy strategy = new JsonStrategy();

        List<Product> products = List.of(
                new Product("Laptop", 1200)
        );

        String result = strategy.format(products);

        assertTrue(result.contains("Laptop"));
        assertTrue(result.contains("1200"));
        assertTrue(result.startsWith("["));
    }
}
