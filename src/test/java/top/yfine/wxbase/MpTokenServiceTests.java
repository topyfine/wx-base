package top.yfine.wxbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yfine.wxbase.service.MpTokenService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTokenServiceTests {
    @Autowired
    private MpTokenService mpTokenService;

    @Test
    public void test() {
        mpTokenService.refreshToken();
    }
}
