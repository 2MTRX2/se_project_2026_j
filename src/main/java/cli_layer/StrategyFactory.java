package CLI_Layer;


public class StrategyFactory {

    public static OutputStrategy create(String format) {

        if ("json".equalsIgnoreCase(format)) {
            return new JsonStrategy();
        }

        return new TableStrategy();
    }
}