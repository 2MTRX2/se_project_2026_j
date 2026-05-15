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
    String result = configService.getUrl(null, null);
    assertEquals("http://localhost:3000/shop-api", result);
  }

  @Test
  void shouldReturnUrlWhenUrlIsProvided() {
    String url = "http://url-provided.com";
    String result = configService.getUrl(url, null);
    assertEquals("http://url-provided.com", result);
  }

  @Test
  void shouldReturnEnvWhenUrlIsNotProvided() {
    String env = "http://url-env.com";
    String result = configService.getUrl(null, env);
    assertEquals("http://url-env.com", result);
  }
}
