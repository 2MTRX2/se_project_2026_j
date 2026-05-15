package cli_layer;

import api_layer.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableStrategyTest {
    @Test
    void shouldFormatProductsAsTable() {
        OutputStrategy strategy = new TableStrategy();

        List<Product> products = List.of(new Product("Laptop", 1200));

        String result = strategy.format(products);

        assertTrue(result.contains("Laptop"));
        assertTrue(result.contains("1200"));
        assertTrue(result.contains("|"));
    }
}
