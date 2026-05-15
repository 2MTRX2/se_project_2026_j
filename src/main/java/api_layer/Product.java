package api_layer;

public record Product(String name, double price) {

  @Override public String name() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void setName(String name) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override public double price() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void setPrice(double price) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
