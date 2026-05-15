package CLI_Layer;

import API_Layer.Product;
import java.util.List;

public interface OutputStrategy {
    String format(List<Product> products);
}
