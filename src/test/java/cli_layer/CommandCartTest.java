package cli_layer;

import api_layer.VendureClient;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandCartTest {
    @Test
    void shouldCallCartLogic() {

        VendureClient client = mock(VendureClient.class);
        OutputStrategy outputStrategy = mock(OutputStrategy.class);

        Command command = new CommandCart(client, outputStrategy);

        command.execute();

        verify(client).getCart();
    }
}
