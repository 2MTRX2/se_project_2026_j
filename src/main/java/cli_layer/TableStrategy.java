package cli_layer;

import api_layer.Product;
import java.util.List;

public class TableStrategy implements OutputStrategy {

  @Override
  public void render(List<Product> products) {
    if (products == null || products.isEmpty()) {
      System.out.println("No products available.");
      return;
    }

    String headerFormat = "| %-10s | %-30s | %10s |\n";
    String lineSeparator = "+------------+--------------------------------+------------+";

    System.out.println(lineSeparator);
    System.out.printf(headerFormat, "ID", "Name", "Price");
    System.out.println(lineSeparator);

    for (Product product : products) {

      System.out.printf(
              "| %-10s | %-30s | %10.2f |\n",
              product.getId(),
              product.getName(),
              (double) product.getPrice()
      );
    }

    System.out.println(lineSeparator);
  }
}
