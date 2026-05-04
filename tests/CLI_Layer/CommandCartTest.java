package CLI_Layer;

import API_Layer.VendureClient;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandCartTest {
    @Test
    void shouldCallCartLogic() {

        VendureClient client = mock(VendureClient.class);

        Command command = new CommandCart(client);

        command.execute();

        verify(client).getCart();
    }
}
