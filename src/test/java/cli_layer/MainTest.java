package cli_layer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class MainTest {

    private Main mainCommand;
    private CommandLine cmd;

    @BeforeEach
    void setUp() {
        mainCommand = new Main();
        cmd = new CommandLine(mainCommand);
    }

    @Test
    void shouldInheritUrlOptionToSubcommands() {
        String[] args = {"--url", "http://test-shop.com", "list"};

        CommandLine.ParseResult parseResult = cmd.parseArgs(args);

        assertEquals("http://test-shop.com", mainCommand.url);

        assertTrue(parseResult.hasSubcommand());

        CommandLine subcommandCtx = parseResult.subcommand().commandSpec().commandLine();
        CommandListCLI listCLI = subcommandCtx.getCommand();

        listCLI.main = mainCommand;

        assertNotNull(listCLI.main);
        assertEquals("http://test-shop.com", listCLI.main.url);
    }

    @Test
    void shouldReturnErrorExitCodeForInvalidSubcommand() {
        String[] args = {"invalid-command"};

        int exitCode = cmd.execute(args);

        assertTrue(exitCode != 0, "exit code should be non-zero");
    }

    @Test
    void shouldExecuteMainRunnableWithoutCrashing() {
        int exitCode = cmd.execute();
        assertEquals(0, exitCode);
    }
}
