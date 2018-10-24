package com.yunwoo.cybershop.handler.command;

import com.yunwoo.cybershop.command.*;
import com.yunwoo.cybershop.domain.Member;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MemberCommandHandler {
    @Autowired
    @Qualifier("memberRepository")
    private Repository<Member> memberRepository;
    @CommandHandler
    public  void hannd(MemberCreateCommand command){
        Member member = new Member(command);
        memberRepository.add(member);

    }
    @CommandHandler
    public  void hannd(MemberEnableCommand command){
        Member member = memberRepository.load(command.getId());
        member.enable();
    }
    @CommandHandler
    public  void hannd(MemberDisableCommand command){
        Member member = memberRepository.load(command.getId());
        member.disable();
    }
    @CommandHandler
    public  void hannd(MemberChangePasswordCommand command){
        Member member = memberRepository.load(command.getId());
        member.changePassword(command);
    }
    @CommandHandler
    public  void hannd(MemberUpdateCommand command){
        Member member = memberRepository.load(command.getId());
        member.update(command);
    }
}
