package com.yunwoo.cybershop.handler.event;

import com.yunwoo.cybershop.business.MemberService;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.event.member.*;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberEventHandler {
    @Autowired
    private MemberService memberService;

    @EventHandler
    public  void handle(MemberCreateEvent event) {
        log.warn("member-query-side receive:{}",event);
        MemberDTO dto = BeanConverter.to(event,MemberDTO.class);
        if(!memberService.save(dto)){
            new Exception("会员保存失败").printStackTrace();
        }
    }
    @EventHandler
    public  void handle(MemberEnableEvent event) {
        log.warn("member-query-side receive:{}",event);
        MemberDTO memberDTO = memberService.getById(event.getId());
        memberDTO.setLastModifyTime(event.getLastModifyTime());
        memberDTO.setIsEnable(true);
        if(!memberService.update(memberDTO)){
            new Exception("会员启用失败").printStackTrace();
        }
    }
    @EventHandler
    public  void handle(MemberDisableEvent event) {
        log.warn("member-query-side receive:{}",event);
        MemberDTO memberDTO = memberService.getById(event.getId());
        memberDTO.setLastModifyTime(event.getLastModifyTime());
        memberDTO.setIsEnable(false);
        if(!memberService.update(memberDTO)){
            new Exception("会员禁用失败").printStackTrace();
        }
    }
    @EventHandler
    public  void handle(MemberChangePasswordEvent event) {
        log.warn("member-query-side receive:{}",event);
        MemberDTO memberDTO = memberService.getById(event.getId());
        memberDTO.setPassword(event.getPassword());
        memberDTO.setLastModifyTime(event.getLastModifyTime());
        if(!memberService.update(memberDTO)){
            new Exception("会员修改密码失败").printStackTrace();
        }
    }
    @EventHandler
    public  void handle(MemberUpdateEvent event) {
        log.warn("member-query-side receive:{}",event);
        MemberDTO memberDTO = memberService.getById(event.getId());
        if(StringUtils.isNotEmpty(event.getPassword())){
            memberDTO.setPassword(event.getPassword());
        }
        memberDTO.setNickname(event.getNickname());
        if(event.getBirthday() != null){
            memberDTO.setBirthday(event.getBirthday());
        }
        memberDTO.setGender(event.getGender());
        memberDTO.setEmail(event.getEmail());
        memberDTO.setAvatar(event.getAvatar());
        memberDTO.setLastModifyTime(event.getLastModifyTime());
        if(!memberService.update(memberDTO)){
            new Exception("会员修改失败").printStackTrace();
        }
    }

}
