
import chatclient.ChatClient;
import chatserver.ChatServer;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author smarandadungeanu
 */
public class TestClient
{

    public TestClient()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ChatServer.main(null);
            }
        }).start();
    }

    @AfterClass
    public static void tearDownClass()
    {
        ChatServer.stopServer();
    }

    @Before
    public void setUp()
    {
    }

    @Test
    public void send() throws IOException
    {
        ChatClient client = new ChatClient();
        client.connect("localhost", 9090, "randomName");
        client.send("Hello");
        //assertEquals("HELLO", client.receive());
    }

}
