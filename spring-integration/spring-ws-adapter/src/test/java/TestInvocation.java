import com.learn.spring.integration.ws.gateway.TicketService;
import com.learn.spring.integration.ws.model.TicketRequest;
import com.learn.spring.integration.ws.model.TicketResponse;
import com.learn.spring.integration.ws.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration({"classpath:spring-integration-ws-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInvocation {

    @Autowired
    private TicketService service;

    @Test
    public void testInvocation() throws InterruptedException, ExecutionException {
        TicketRequest request = new TicketRequest();
        request.setFilmId("aFilm");
        request.setQuantity(new BigInteger("3"));
        request.setSessionDate(DateUtils.convertDate(new Date()));

        TicketResponse response = service.invoke(request);
        System.out.println(response);


        assertNotNull(response);
        assertEquals("aFilm", response.getFilmId());
        assertEquals(new BigInteger("3"), response.getQuantity());
    }
}