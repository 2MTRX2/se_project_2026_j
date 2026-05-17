package api_layer;

import java.util.List;

public class Product {
  private String id;
  private String name;
  private List<Variant> variants;

  public Product() {}

  public Product(String name, double price, String id) {
    this.name = name;
    this.id = id;
    this.variants = List.of(new Variant(price));
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Variant> getVariants() {
    return this.variants;
  }

  public void setVariants(List<Variant> variants) {
    this.variants = variants;
  }

  public double getPrice() {
    if (variants != null && !variants.isEmpty()) {
      return variants.get(0).getPrice() / 100.0;
    }
    return 0.0;
  }

  public static class Variant {
    private int price;

    public Variant() {}

    public Variant(double priceAsDecimal) {
      this.price = (int) (priceAsDecimal * 100);
    }

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }
  }
}
