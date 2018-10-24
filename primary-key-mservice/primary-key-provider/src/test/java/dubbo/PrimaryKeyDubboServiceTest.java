package dubbo;

import com.yunwoo.cybershop.PrimaryKeyApplication;
import com.yunwoo.cybershop.business.PrimaryKeyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrimaryKeyApplication.class)
public class PrimaryKeyDubboServiceTest {
    @Autowired
    private PrimaryKeyService primaryKeyService;
    @Test
    public void testGen() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i++){
                    int order = primaryKeyService.generatePrimaryKey("order");
                    System.out.println(Thread.currentThread().getId()+"--------------------"+order);
                }

            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 1000; i++){
                    int order = primaryKeyService.generatePrimaryKey("order");
                    System.out.println(Thread.currentThread().getId()+"--------------------"+order);
                }

            }
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);

        thread.start();
        thread2.start();
        Thread.sleep(100000);

    }

    @Test
    public void testInit(){
        primaryKeyService.init("cs_order",99999);
        System.out.println("-------------"+primaryKeyService.getCurrent("cs_order"));
    }

    @Test
    public void testGenOne(){
        int order = primaryKeyService.generatePrimaryKey("cs_order");
        System.out.println(Thread.currentThread().getId()+"--------------------"+order);

    }


}
