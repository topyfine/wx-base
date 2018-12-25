package top.yfine.wxbase.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yfine.wxbase.api.request.TicketRequest;
import top.yfine.wxbase.api.response.TicketResponse;

/**
 * @author topyfine
 * @date 18-12-25 下午10:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRequestTests {
    @Autowired
    private HttpAgent httpAgent;

    @Test
    public void test() {
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.addTextParam("access_token", "")
                .addTextParam("type", "jsapi");
        TicketResponse ticketResponse = httpAgent.action(ticketRequest);
        Assert.assertNotNull(ticketResponse);
    }
}
