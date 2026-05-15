package api_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendureClientTest {

    private VendureClient client;

    @BeforeEach
    void setUp() {
        client = new FakeVendureClient();
    }

    @Test
    void shouldReturnMockedProducts() {
        List<Product> products = client.getProducts();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).name());
    }

    @Test
    void shouldReturnMockedProductsInCart() {
        List<Product> products = client.getCart();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).name());
    }
}
