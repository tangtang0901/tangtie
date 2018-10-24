package com.yunwoo.cybershop.domain;

import com.yunwoo.cybershop.command.MemberChangePasswordCommand;
import com.yunwoo.cybershop.command.MemberCreateCommand;
import com.yunwoo.cybershop.command.MemberUpdateCommand;
import com.yunwoo.cybershop.event.member.*;
import com.yunwoo.cybershop.utils.MD5Util;
import com.yunwoo.cybershop.utils.StringUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Date;

/**
 * Created by water on 2016/4/13.
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class Member extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private Integer id;
    /**
     * '用户名'
     */
    private String username;
    /**
     * '密码'
     */
    private String password;
    /**
     * '昵称'
     */
    private String nickname;
    /**
     * '生日'
     */
    private Date birthday;
    /**
     * '1男 0女'
     */
    private Integer gender;
    /**
     * '手机'
     */
    private String phonenumber;
    /**
     * '邮箱'
     */
    private String email;
    /**
     * '头像图片ID'
     */
    private Integer avatar;
    /**
     * '是否启用'
     */
    private Boolean isEnable;
    /**
     * '创建时间'
     */
    private Date createTime;
    /**
     * '最后修改时间'
     */
    private Date lastModifyTime;



    public Member(MemberCreateCommand command){
        Date now = new Date();
        Date birthDay = command.getBirthday();
        Integer avatar = command.getAvatar();
        //加密
        String password = MD5Util.MD5(command.getPassword());
        if(command.getBirthday() == null){
            birthDay = new Date();
        }
        if(avatar == null){
            //TODO 默认头像ID
            avatar = 0;
        }
        MemberCreateEvent event = new MemberCreateEvent(command.getId(),command.getUsername(),password,command.getNickname(),birthDay,command.getGender(),command.getPhonenumber(),command.getEmail(),avatar,command.getIsEnable(),now,now );
        //应用事件 并 发布
        apply(event);

    }

    public void enable(){
        MemberEnableEvent enableEvent = new MemberEnableEvent(this.id,new Date());
        apply(enableEvent);
    }
    public void disable(){
        MemberDisableEvent disableEvent = new MemberDisableEvent(this.id,new Date());
        apply(disableEvent);
    }
    public void changePassword(MemberChangePasswordCommand changePasswordCommand){
        MemberChangePasswordEvent event = new MemberChangePasswordEvent(this.id,MD5Util.MD5(changePasswordCommand.getPassword()),new Date());
        apply(event);
    }
    public void update(MemberUpdateCommand command){
        Date birthDay = null;
        String password = command.getPassword();
        if(StringUtils.isNotEmpty(password)){
            password = MD5Util.MD5(password);
        }
        if(command.getBirthday() != null){
            birthDay = command.getBirthday();
        }
        MemberUpdateEvent event = new MemberUpdateEvent(command.getId(),password,command.getNickname(), birthDay,command.getGender(),command.getEmail(),command.getAvatar(),new Date());
        apply(event);
    }



    @EventSourcingHandler
    public  void hanndle(MemberCreateEvent event){
        this.id = event.getId();
        this.username=event.getUsername();
        this.password=event.getPassword();
        this.nickname=event.getNickname();
        this.birthday=event.getBirthday();
        this.gender=event.getGender();
        this.phonenumber=event.getPhonenumber();
        this.email=event.getEmail();
        this.avatar=event.getAvatar();
        this.isEnable=event.getIsEnable();
        this.createTime=event.getCreateTime();
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("Member.hanndle.MemberCreateEvent");
    }
    @EventSourcingHandler
    public  void hanndle(MemberEnableEvent event){
        this.isEnable = true;
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("Member.hanndle.MemberEnableEvent");
    }
    @EventSourcingHandler
    public  void hanndle(MemberDisableEvent event){
        this.isEnable = false;
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("Member.hanndle.MemberDisableEvent");
    }
    @EventSourcingHandler
    public  void hanndle(MemberChangePasswordEvent event){
        this.password = event.getPassword();
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("Member.hanndle.MemberDisableEvent");
    }
    @EventSourcingHandler
    public  void hanndle(MemberUpdateEvent event){
        if(StringUtils.isNotEmpty(event.getPassword())){
            this.password = event.getPassword();
        }
        this.nickname = event.getNickname();
        if(event.getBirthday() != null){
            this.birthday = event.getBirthday();
        }
        this.gender = event.getGender();
        this.email = event.getEmail();
        this.avatar = event.getAvatar();
        this.lastModifyTime=event.getLastModifyTime();
        log.warn("Member.hanndle.MemberUpdateEvent");
    }

}
