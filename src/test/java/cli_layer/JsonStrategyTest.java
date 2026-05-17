package cli_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api_layer.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonStrategyTest {

  private JsonStrategy jsonStrategy;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private PrintStream originalOut;

  @BeforeEach
  void setUp() {
    jsonStrategy = new JsonStrategy();
    originalOut = System.out;
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void shouldPrintEmptyJsonArrayWhenListIsEmpty() {
    jsonStrategy.render(Collections.emptyList());

    assertEquals("[]", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldPrintEmptyJsonArrayWhenListIsNull() {
    jsonStrategy.render(null);

    assertEquals("[]", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldPrintPrettyFormattedJsonForProducts() {
    Product product = new Product("test", 14.99, "10");
    List<Product> products = List.of(product);

    jsonStrategy.render(products);

    String printedOutput = outputStreamCaptor.toString();

    assertTrue(printedOutput.contains("\"id\" : \"10\""));
    assertTrue(printedOutput.contains("\"name\" : \"test\""));
    assertTrue(printedOutput.contains("\"price\" : 14.99"));

    assertTrue(printedOutput.lines().count() > 2, "the json should cotain multiple lines.");
  }
}
