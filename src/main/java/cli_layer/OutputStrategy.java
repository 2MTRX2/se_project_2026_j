package cli_layer;

import api_layer.Product;

import java.util.List;

public interface OutputStrategy {
  String format(List<Product> products);
}
