package cli_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api_layer.Product;
import api_layer.VendureClient;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CommandListTest {

  @Test
  void shouldFetchProductsAndPassThemToStrategy() {
    Product testProduct = new Product("test", 10.0, "11");

    VendureClient fakeClient =
        new VendureClient("http://fake-url.com") {
          @Override
          public List<Product> getProducts() {
            return List.of(testProduct);
          }
        };

    SpyOutputStrategy spyStrategy = new SpyOutputStrategy();

    CommandList commandList = new CommandList(fakeClient, spyStrategy);

    commandList.execute();

    assertTrue(spyStrategy.wasRenderCalled, "the method render() should have been called");
    assertEquals(1, spyStrategy.receivedProducts.size());
    assertEquals("test", spyStrategy.receivedProducts.get(0).getName());
  }

  private static class SpyOutputStrategy implements OutputStrategy {
    boolean wasRenderCalled = false;
    List<Product> receivedProducts = new ArrayList<>();

    @Override
    public void render(List<Product> products) {
      this.wasRenderCalled = true;
      this.receivedProducts = products;
    }
  }
}
