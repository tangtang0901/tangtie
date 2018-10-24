package dubbo;

import com.yunwoo.cybershop.BaseApplication;
import com.yunwoo.cybershop.business.MsgService;
import com.yunwoo.cybershop.constant.MsgTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BaseApplication.class)
public class MsgDubboServiceTest {
    @Autowired
    private MsgService msgService;
    @Test
    public void testSend(){
        Map<String,String> params = new HashMap<String, String>(1);
        params.put("code","999");
        boolean b = msgService.sendMsg("18076392423", MsgTemplate.REGISTER, params);
        Assert.assertTrue(b);
    }
}
