package api_layer;

import java.util.List;

public class VendureClient {
  private String url;

  public VendureClient(String url) {
    this.url = url;
  }

  public List<Product> getProducts() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public List<Product> getCart() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
