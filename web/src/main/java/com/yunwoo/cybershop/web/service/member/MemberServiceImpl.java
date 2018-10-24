package com.yunwoo.cybershop.web.service.member;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.command.*;
import com.yunwoo.cybershop.constant.TableName;
import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.dubbo.MemberCommandDubboService;
import com.yunwoo.cybershop.dubbo.MemberQueryDubboService;
import com.yunwoo.cybershop.dubbo.MenuQueryDubboService;
import com.yunwoo.cybershop.dubbo.PrimaryKeyDubboService;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import org.springframework.stereotype.Service;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/10 下午2:42
 */
@Service
public class MemberServiceImpl implements MemberService{
    @Reference(version = "1.0.0",check = true)
    private MemberQueryDubboService memberQueryDubboService;

    @Reference(version = "1.0.0",check = true)
    private MemberCommandDubboService memberCommandDubboService;
    @Reference(version = "1.0.0",check = true)
    private PrimaryKeyDubboService primaryKeyDubboService;

    @Override
    public PaginationResult<MemberDTO> getByPagination(Pagination pagination) {
        return memberQueryDubboService.getByPagination(pagination);
    }

    @Override
    public boolean addMember(MemberDTO memberDTO) {
        int memberId = primaryKeyDubboService.generatePrimaryKey(TableName.MEMBER.name());
        MemberCreateCommand command = new MemberCreateCommand(memberId,memberDTO.getUsername(),memberDTO.getPassword(),memberDTO.getNickname()
        ,memberDTO.getBirthday(),memberDTO.getGender(),memberDTO.getPhonenumber(),memberDTO.getEmail(),memberDTO.getAvatar(),memberDTO.getIsEnable());
        try {
            memberCommandDubboService.create(command);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateMember(MemberDTO memberDTO) {
        MemberUpdateCommand command = new MemberUpdateCommand(memberDTO.getId(),memberDTO.getPassword(),memberDTO.getNickname()
                ,memberDTO.getBirthday(),memberDTO.getGender(),memberDTO.getEmail(),memberDTO.getAvatar());
        try {
            memberCommandDubboService.update(command);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MemberDTO getByUsername(String username) {
        return memberQueryDubboService.getByUsername(username);
    }

    @Override
    public MemberDTO getByPhonenum(String phonenum) {
        return memberQueryDubboService.getByPhonenum(phonenum);
    }

    @Override
    public boolean enable(Integer memberId) {
        try {
            memberCommandDubboService.enable(new MemberEnableCommand(memberId));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean disable(Integer memberId) {
        try {
            memberCommandDubboService.disable(new MemberDisableCommand(memberId));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changePassword(Integer memberId, String password) {
        try {
            memberCommandDubboService.changePassword(new MemberChangePasswordCommand(memberId,password));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MemberDTO getById(int id) {
        return memberQueryDubboService.getById(id);
    }
}
