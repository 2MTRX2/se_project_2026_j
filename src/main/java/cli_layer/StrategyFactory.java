package cli_layer;

public class StrategyFactory {

  public static OutputStrategy create(String format) {

    if ("json".equalsIgnoreCase(format)) {
      return new JsonStrategy();
    }

    return new TableStrategy();
  }
}
