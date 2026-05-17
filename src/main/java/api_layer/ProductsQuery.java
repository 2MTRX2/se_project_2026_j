package api_layer;

import api_layer.Product;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProductsQuery implements GraphQLRequest<ProductsQuery.ProductsData> {

    @Override
    public String getQuery() {
        return "query GetProducts { " +
                "  products { " +
                "    items { " +
                "      id " +
                "      name " +
                "      price " +
                "    } " +
                "  } " +
                "}";
    }

    @Override
    public Map<String, Object> getVariables() {
        return Collections.emptyMap();
    }

    @Override
    public Class<ProductsData> getResponseClass() {
        return ProductsData.class;
    }


    /* helper nested classes to unfold the json response */
    public static class ProductsData {
        public ProductListWrapper products;
    }

    public static class ProductListWrapper {
        public List<Product> items;
    }
}
