package cli_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class CommandListCLITest {

    private CommandListCLI commandListCLI;
    private Main mockMain;

    @BeforeEach
    void setUp() {
        commandListCLI = new CommandListCLI();

        mockMain = new Main();
        mockMain.url = "http://localhost:3000/shop-api";

        commandListCLI.main = mockMain;
    }

    @Test
    void shouldParseDefaultFormatAsTable() {
        CommandLine cmd = new CommandLine(commandListCLI);

        cmd.parseArgs();

        assertEquals("table", commandListCLI.format);
    }

    @Test
    void shouldParseJsonFormatOption() {
        CommandLine cmd = new CommandLine(commandListCLI);

        cmd.parseArgs("--format", "json");

        assertEquals("json", commandListCLI.format);
    }

    @Test
    void shouldExecuteWithoutCrashingWithMockedBehavior() {
        CommandListCLI safeCommandListCLI = new CommandListCLI() {
            @Override
            public void run() {
                assertNotNull(main.url);
                assertEquals("http://localhost:3000/shop-api", main.url);
                assertEquals("table", this.format);
            }
        };

        safeCommandListCLI.main = mockMain;
        CommandLine cmd = new CommandLine(safeCommandListCLI);

        int exitCode = cmd.execute();

        assertEquals(0, exitCode);
    }
}
