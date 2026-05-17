package api_layer;

/*
This class should return an appropriate URL of the webshop-API according
 to the priority (priority: given url, env url, default url).
 */
public class ConfigService {

  public String getUrl(String cliUrl) {
    if (cliUrl != null && !cliUrl.isEmpty()) {
      return cliUrl;
    }

    String envUrl = getEnvVariable("URL");
    if (envUrl != null && !envUrl.isEmpty()) {
      return envUrl;
    }
    return "http://localhost:3000/shop-api";
  }

  protected String getEnvVariable(String name) {
    return System.getenv(name);
  }
}
