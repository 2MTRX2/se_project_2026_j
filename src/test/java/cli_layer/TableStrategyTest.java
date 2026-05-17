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

public class TableStrategyTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private TableStrategy tableStrategy;
  private PrintStream originalOut;

  @BeforeEach
  void setUp() {
    tableStrategy = new TableStrategy();
    originalOut = System.out;
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void shouldPrintFallbackMessageWhenListIsEmpty() {
    tableStrategy.render(Collections.emptyList());

    assertEquals("No products available.", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldPrintFallbackMessageWhenListIsNull() {
    tableStrategy.render(null);

    assertEquals("No products available.", outputStreamCaptor.toString().trim());
  }

  @Test
  void shouldPrintWellFormattedTableWithHeaderAndData() {
    Product product = new Product("test", 19.95, "42");
    List<Product> products = List.of(product);

    tableStrategy.render(products);

    String printedOutput = outputStreamCaptor.toString();

    assertTrue(
        printedOutput.contains("+------------+--------------------------------+------------+"));

    assertTrue(printedOutput.contains("ID"));
    assertTrue(printedOutput.contains("Name"));
    assertTrue(printedOutput.contains("Price"));

    assertTrue(printedOutput.contains("42"));
    assertTrue(printedOutput.contains("test"));
    assertTrue(printedOutput.contains("19.95"));
  }
}
