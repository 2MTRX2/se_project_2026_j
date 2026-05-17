package api_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class VendureClientTest {

  @Test
  void shouldReturnProductsSuccessfullyWhenServerResponds() throws Exception {
    // simulating the response of a server
    String mockServerJson =
        "{"
            + "  \"data\": {"
            + "    \"products\": {"
            + "      \"items\": ["
            + "        {"
            + "          \"id\": \"1\","
            + "          \"name\": \"Shoe\","
            + "          \"variants\": [{\"price\": 4999}]" // 49.99 als Cent-Wert
            + "        }"
            + "      ]"
            + "    }"
            + "  }"
            + "}";

    // overriding the send request to avoid a real request
    VendureClient clientWithMockHttp =
        new VendureClient("http://mock-url.com") {
          @Override
          protected HttpResponse<String> sendRequest(HttpRequest httpRequest) {
            return new MockHttpResponse(mockServerJson); // returns our fake response
          }
        };

    List<Product> products = clientWithMockHttp.getProducts();

    // ASSERT
    assertNotNull(products);
    assertEquals(1, products.size());
    assertEquals("Shoe", products.get(0).getName());
    assertEquals(49.99, products.get(0).getPrice(), 0.001);
  }

  @Test
  void shouldThrowExceptionWhenGraphQLServerReturnsErrors() {
    // simulating an error response
    String mockErrorJson =
        "{" + "  \"errors\": [{" + "    \"message\": \"Access denied\"" + "  }]" + "}";

    VendureClient clientWithError =
        new VendureClient("http://mock-url.com") {
          @Override
          protected HttpResponse<String> sendRequest(HttpRequest httpRequest) {
            return new MockHttpResponse(mockErrorJson);
          }
        };

    // test if the runtime exception is thrown
    assertThrows(
        RuntimeException.class,
        () -> {
          clientWithError.getProducts();
        });
  }


  private static class MockHttpResponse implements HttpResponse<String> {
    private final String bodyContent;

    public MockHttpResponse(String bodyContent) {
      this.bodyContent = bodyContent;
    }

    @Override
    public String body() {
      return bodyContent;
    }

    @Override
    public int statusCode() {
      return 200;
    }

    // remaining methods return null
    @Override
    public HttpRequest request() {
      return null;
    }

    @Override
    public Optional<HttpResponse<String>> previousResponse() {
      return null;
    }

    @Override
    public java.net.http.HttpHeaders headers() {
      return null;
    }

    @Override
    public java.util.Optional<javax.net.ssl.SSLSession> sslSession() {
      return java.util.Optional.empty();
    }

    @Override
    public java.net.URI uri() {
      return null;
    }

    @Override
    public java.net.http.HttpClient.Version version() {
      return null;
    }
  }
}
