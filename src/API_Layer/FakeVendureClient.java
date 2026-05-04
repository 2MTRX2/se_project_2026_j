package API_Layer;

import java.util.List;

public class FakeVendureClient extends VendureClient{
    public FakeVendureClient() {
        super("fake-url");
    }

    @Override
    public List<Product> getProducts() {
        return List.of(
                new Product("Laptop", 1200),
                new Product("Phone", 800)
        );
    }

    @Override
    public List<Product> getCart() {
        return List.of(
                new Product("Laptop", 1200),
                new Product("Phone", 800)
        );
    }
}
