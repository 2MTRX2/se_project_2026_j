package cli_layer;

import api_layer.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;

public class JsonStrategy implements OutputStrategy {
  private final ObjectMapper objectMapper;

  public JsonStrategy() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @Override
  public void render(List<Product> products) {
    if (products == null || products.isEmpty()) {
      System.out.println("[]");
      return;
    }

    try {
      String jsonOutput = objectMapper.writeValueAsString(products);
      System.out.println(jsonOutput);
    } catch (Exception e) {
      System.err.println("Error in json formatting: " + e.getMessage());
    }
  }
}
