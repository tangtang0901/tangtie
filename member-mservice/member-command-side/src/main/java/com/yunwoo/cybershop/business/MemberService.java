package com.yunwoo.cybershop.business;

import com.yunwoo.cybershop.command.*;

public interface MemberService {
    void create(MemberCreateCommand command);
    void update(MemberUpdateCommand command);
    void enable(MemberEnableCommand enableCommand);
    void disable(MemberDisableCommand disableCommand);
    void changePassword(MemberChangePasswordCommand changePasswordCommand);
}
