package api_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductsQueryTest {

    private ProductsQuery productsQuery;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productsQuery = new ProductsQuery();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldReturnCorrectGraphQLQueryString() {
        String query = productsQuery.getQuery();

        assertNotNull(query);
        assertTrue(query.contains("query GetProducts"));
        assertTrue(query.contains("products"));
        assertTrue(query.contains("items"));
        assertTrue(query.contains("id"));
        assertTrue(query.contains("name"));
        assertTrue(query.contains("variants"));
        assertTrue(query.contains("price"));
    }

    @Test
    void shouldReturnEmptyVariablesWhenNoFilterIsSet() {
        Map<String, Object> variables = productsQuery.getVariables();

        assertNotNull(variables);
        assertTrue(variables.isEmpty());
    }

    @Test
    void shouldCorrectlyDeserializeJsonToInternalClasses() throws Exception {
        String mockJson =
                "{"
                        + "  \"products\": {"
                        + "    \"items\": ["
                        + "      {"
                        + "        \"id\": \"1\","
                        + "        \"name\": \"Shoes\","
                        + "        \"variants\": [{\"price\": 5999}]"
                        + "      },"
                        + "      {"
                        + "        \"id\": \"2\","
                        + "        \"name\": \"Socks\","
                        + "        \"variants\": [{\"price\": 999}]"
                        + "      }"
                        + "    ]"
                        + "  }"
                        + "}";

        Class<ProductsQuery.ProductsData> targetClass = productsQuery.getResponseClass();
        ProductsQuery.ProductsData result = objectMapper.readValue(mockJson, targetClass);

        assertNotNull(result);
        assertNotNull(result.products);
        assertNotNull(result.products.items);
        assertEquals(2, result.products.items.size());

        Product firstProduct = result.products.items.get(0);
        assertEquals("1", firstProduct.getId());
        assertEquals("Shoes", firstProduct.getName());

        assertEquals(59.99, firstProduct.getPrice(), 0.001);
    }
}
