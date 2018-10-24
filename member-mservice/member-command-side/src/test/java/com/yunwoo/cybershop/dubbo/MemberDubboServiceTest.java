package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.MemberCommandSideApplication;
import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.command.MemberCreateCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MemberCommandSideApplication.class)
public class MemberDubboServiceTest {
    @Autowired
    private MemberService memberService;
    @Test
    public void testCreate(){
        Date now = new Date();
        MemberCreateCommand command = new MemberCreateCommand(1,"username","password","nickname",now,1,"18076392423","ss@qq.com",1,true);

        memberService.create(command);
    }
}
