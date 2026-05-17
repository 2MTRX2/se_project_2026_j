package cli_layer;

import api_layer.Product;
import java.util.List;

public interface OutputStrategy {
  void render(List<Product> products);
}
