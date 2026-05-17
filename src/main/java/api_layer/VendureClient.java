package api_layer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendureClient {
  private final String url;
  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public VendureClient(String url) {
    this.url = url;
    this.httpClient = HttpClient.newHttpClient();
    this.objectMapper = new ObjectMapper();
  }

  public <T> T execute(GraphQLRequest<T> request) throws Exception {
    /* create the http request */
    Map<String, Object> payload = new HashMap<>();
    payload.put("query", request.getQuery());
    payload.put(
        "variables",
        request.getVariables() != null ? request.getVariables() : Collections.emptyMap());

    String jsonPayload = objectMapper.writeValueAsString(payload);

    HttpRequest httpRequest =
        HttpRequest.newBuilder()
            .uri(URI.create(this.url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .build();

    /* send the http request */
    HttpResponse<String> httpResponse = sendRequest(httpRequest);

    /* convert the response into Java classes */
    JsonNode rootNode = objectMapper.readTree(httpResponse.body());

    if (rootNode.has("errors")) {
      throw new RuntimeException("GraphQL Error von Vendure: " + rootNode.get("errors").toString());
    }

    JsonNode dataNode = rootNode.get("data");

    return objectMapper.treeToValue(dataNode, request.getResponseClass());
  }

  protected HttpResponse<String> sendRequest(HttpRequest httpRequest) throws Exception {
    return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
  }

  public List<Product> getProducts() {
    try {
      ProductsQuery query = new ProductsQuery();
      ProductsQuery.ProductsData result = this.execute(query);

      return result.products.items;
    } catch (Exception e) {
      throw new RuntimeException("Error when calling products: " + e.getMessage(), e);
    }
  }

  public List<Product> getCart() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
}
