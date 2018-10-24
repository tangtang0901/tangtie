package com.yunwoo.cybershop.business.impl;

import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    CommandGateway gateway;


    @Override
    public void create(MemberCreateCommand command) {
        gateway.send(command);
    }

    @Override
    public void update(MemberUpdateCommand command) {
        gateway.send(command);
    }

    @Override
    public void enable(MemberEnableCommand enableCommand) {
        gateway.send(enableCommand);
    }

    @Override
    public void disable(MemberDisableCommand disableCommand) {
        gateway.send(disableCommand);
    }

    @Override
    public void changePassword(MemberChangePasswordCommand changePasswordCommand) {
        gateway.send(changePasswordCommand);
    }
}
