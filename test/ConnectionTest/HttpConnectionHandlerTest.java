package ConnectionTest;

import com.PolygonGamesStudio.UJourney.Handler.HttpConnectionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpConnectionHandlerTest {

    private HttpConnectionHandler handler;
    private static final String URL = "http://127.0.0.1:5000/history/1";
    private static final String GET = "GET";
    private String response;

    @Before
    public void setUp() {
        handler = new HttpConnectionHandler();
    }

    @Test
    public void handleHttpRequest() {
        response = handler.ServiceCall(URL, GET);
        Assert.assertNotNull(response);
    }

}
