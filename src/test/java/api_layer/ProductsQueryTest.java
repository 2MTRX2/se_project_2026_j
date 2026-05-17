package api_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(true, query.contains("query GetProducts"));
        assertEquals(true, query.contains("products"));
        assertEquals(true, query.contains("items"));
        assertEquals(true, query.contains("id"));
        assertEquals(true, query.contains("name"));
        assertEquals(true, query.contains("price"));
    }

    @Test
    void shouldReturnEmptyVariablesWhenNoFilterIsSet() {
        Map<String, Object> variables = productsQuery.getVariables();

        assertNotNull(variables);
        assertEquals(true, variables.isEmpty());
    }

    @Test
    void shouldCorrectlyDeserializeJsonToInternalClasses() throws Exception {
        String mockJson = "{"
                + "  \"products\": {"
                + "    \"items\": ["
                + "      {\"id\": \"1\", \"name\": \"Shoes\", \"price\": 59.99},"
                + "      {\"id\": \"2\", \"name\": \"Socks\", \"price\": 9.99}"
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
        assertEquals(59.99, firstProduct.getPrice());
    }
}
