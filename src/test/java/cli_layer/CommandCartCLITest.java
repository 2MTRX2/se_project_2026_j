package cli_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class CommandCartCLITest {

    private CommandCartCLI commandCartCLI;
    private Main mockMain;

    @BeforeEach
    void setUp() {
        commandCartCLI = new CommandCartCLI();

        mockMain = new Main();
        mockMain.url = "http://localhost:3000/shop-api";

        commandCartCLI.main = mockMain;
    }

    @Test
    void shouldParseDefaultFormatAsTable() {
        CommandLine cmd = new CommandLine(commandCartCLI);

        cmd.parseArgs();

        assertEquals("table", commandCartCLI.format);
    }

    @Test
    void shouldParseJsonFormatOption() {
        CommandLine cmd = new CommandLine(commandCartCLI);

        cmd.parseArgs("--format", "json");

        assertEquals("json", commandCartCLI.format);
    }

    @Test
    void shouldExecuteWithoutCrashingWithMockedBehavior() {
        CommandCartCLI safeCommandCartCLI = new CommandCartCLI() {
            @Override
            public void run() {
                assertNotNull(main.url);
                assertEquals("http://localhost:3000/shop-api", main.url);
                assertEquals("table", this.format);
            }
        };

        safeCommandCartCLI.main = mockMain;
        CommandLine cmd = new CommandLine(safeCommandCartCLI);

        int exitCode = cmd.execute();
        assertEquals(0, exitCode);
    }
}
