package api_layer;

import java.util.Map;

public interface GraphQLRequest<T> {
    /* delivers the query string */
    String getQuery();

    /* delivers the variables for the query (filters) */
    Map<String, Object> getVariables();

    /* delivers the response class for the object wrapper because of generics and type erasing */
    Class<T> getResponseClass();
}

