package com.yunwoo.cybershop.dubbo;

import com.yunwoo.cybershop.command.*;

/**
 * 会员 Dubbo 服务层
 *
 * Created by bysocket on 28/02/2017.
 */
public interface MemberCommandDubboService {

    void create(MemberCreateCommand createCommand);
    void update(MemberUpdateCommand updateCommand);
    void enable(MemberEnableCommand enableCommand);
    void disable(MemberDisableCommand disableCommand);
    void changePassword(MemberChangePasswordCommand changePasswordCommand);

}
