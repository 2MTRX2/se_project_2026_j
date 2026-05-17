package api_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigServiceTest {

  private ConfigService configService;

  @BeforeEach
  void setUp() {
    configService = new ConfigService();
  }

  @Test
  void shouldReturnDefaultUrlWhenNothingIsProvided() {
    String result = configService.getUrl(null);
    assertEquals("http://localhost:3000/shop-api", result);
  }

  @Test
  void shouldReturnUrlWhenUrlIsProvided() {
    String url = "http://url-provided.com";
    String result = configService.getUrl(url);
    assertEquals("http://url-provided.com", result);
  }

  @Test
  void shouldReturnEnvWhenUrlIsNotProvided() {
    ConfigService configServiceWithEnv = new ConfigService() {
      /*Mocking the Env Variable */
      @Override
      protected String getEnvVariable(String name) {
        if ("URL".equals(name)) {
          return "http://url-env.com";
        }
        return null;
      }
    };
    String result = configServiceWithEnv.getUrl(null);
    assertEquals("http://url-env.com", result);
  }
}
