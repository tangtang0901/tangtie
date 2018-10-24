package com.yunwoo.cybershop.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.command.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0",timeout = 20000)
public class MemberCommandDubboServiceImpl implements MemberCommandDubboService {
    @Autowired
    private MemberService orderService;




    @Override
    public void create(MemberCreateCommand createCommand) {
        orderService.create(createCommand);
    }

    @Override
    public void update(MemberUpdateCommand updateCommand) {
        orderService.update(updateCommand);
    }

    @Override
    public void enable(MemberEnableCommand enableCommand) {
        orderService.enable(enableCommand);
    }

    @Override
    public void disable(MemberDisableCommand disableCommand) {
        orderService.disable(disableCommand);
    }

    @Override
    public void changePassword(MemberChangePasswordCommand changePasswordCommand) {
        orderService.changePassword(changePasswordCommand);
    }
}
