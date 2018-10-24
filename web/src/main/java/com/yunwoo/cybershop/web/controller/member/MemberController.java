package com.yunwoo.cybershop.web.controller.member;

import com.yunwoo.cybershop.dto.MemberDTO;
import com.yunwoo.cybershop.model.Pagination;
import com.yunwoo.cybershop.model.PaginationResult;
import com.yunwoo.cybershop.utils.BeanConverter;
import com.yunwoo.cybershop.utils.DateUtil;
import com.yunwoo.cybershop.utils.PictureUtils;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.web.controller.base.WebBaseController;
import com.yunwoo.cybershop.web.service.member.MemberService;
import com.yunwoo.cybershop.web.vo.base.EasyGridResultVO;
import com.yunwoo.cybershop.web.vo.member.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @company yunwoo
 * @created by huanghaizhou
 * @date 2018/4/9 下午5:20
 */
@Controller
@RequestMapping("/5000/member")
public class MemberController extends WebBaseController{
    @Autowired
    private MemberService memberService;
    @RequestMapping("/memberList")
    public String memberList(HttpServletRequest request, HttpServletResponse response){
        return "module/member/memberList";
    }
    @RequestMapping("/memberAdd")
    public String addPage(HttpServletRequest request, HttpServletResponse response){
        return "module/member/memberAdd";
    }
    @RequestMapping("/memberEdit")
    public String editPage(Integer id,Map context){
        context.put("id",id);
        return "module/member/memberEdit";
    }

    @RequestMapping("/getMembers")
    @ResponseBody
    public EasyGridResultVO getMembers(Integer page, Integer rows, String sortField, String sortOrder,  String name){
        Map<String,Object> query = new HashMap<>();
        query.put("name",name);
        Pagination pagination = new Pagination(page,rows,query);
        PaginationResult<MemberDTO> paginationResult = memberService.getByPagination(pagination);
        EasyGridResultVO gridResultVO = new EasyGridResultVO(dtos2vos(paginationResult.getResult()),paginationResult.getTotalNum());
        return gridResultVO;
    }
    @RequestMapping("/getById")
    @ResponseBody
    public MemberVO getById(Integer id){
        MemberDTO memberDTO = memberService.getById(id);
        return dto2vo(memberDTO);
    }
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Boolean checkUsername(String username){
        MemberDTO memberDTO = memberService.getByUsername(username);
        return memberDTO == null;
    }
    @RequestMapping("/checkPhone")
    @ResponseBody
    public Boolean checkPhone(String phonenumber){
        MemberDTO memberDTO = memberService.getByPhonenum(phonenumber);
        return memberDTO == null;
    }
    @RequestMapping("/addMember")
    @ResponseBody
    public String addMember(@RequestBody MemberVO vo){
        String birthdayStr = vo.getBirthdayStr();
        if(StringUtils.isNotEmpty(birthdayStr)){
            vo.setBirthday(DateUtil.convertStringToDate(DateUtil.SHORTDATEFORMATER,birthdayStr));
        }
        vo.setIsEnable(true);
        return String.valueOf(memberService.addMember(BeanConverter.to(vo,MemberDTO.class)));
    }
    @RequestMapping("/updateMember")
    @ResponseBody
    public String updateMember(@RequestBody MemberVO vo){
        String birthdayStr = vo.getBirthdayStr();
        if(StringUtils.isNotEmpty(birthdayStr)){
            vo.setBirthday(DateUtil.convertStringToDate(DateUtil.SHORTDATEFORMATER,birthdayStr));
        }
        vo.setIsEnable(true);
        return String.valueOf(memberService.updateMember(BeanConverter.to(vo,MemberDTO.class)));
    }
    @RequestMapping("/enable")
    @ResponseBody
    public String enable(Integer id){
        MemberDTO memberDTO = memberService.getById(id);
        if(memberDTO != null && !memberDTO.getIsEnable()){
            return  String.valueOf(memberService.enable(id));
            //如果原来已经启用，直接返回成功
        }else if(memberDTO.getIsEnable()){
            return  String.valueOf(true);
        }
        return String.valueOf(false);
    }
    @RequestMapping("/disable")
    @ResponseBody
    public String disable(Integer id){
        MemberDTO memberDTO = memberService.getById(id);
        if(memberDTO != null && memberDTO.getIsEnable()){
            return  String.valueOf(memberService.disable(id));
            //如果原来已经禁用，直接返回成功
        }else if(!memberDTO.getIsEnable()){
            return  String.valueOf(true);
        }
        return String.valueOf(false);
    }
    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(Integer id,String password){
        MemberDTO memberDTO = memberService.getById(id);
        if(memberDTO != null){
            return  String.valueOf(memberService.changePassword(id,password));
            //如果原来已经禁用，直接返回成功
        }
        return String.valueOf(false);
    }
    private List<MemberVO> dtos2vos(List<MemberDTO> dtoList){
        if(dtoList != null){
            List<MemberVO> memberVOS = new ArrayList<>();
            for(MemberDTO memberDTO : dtoList){
                memberVOS.add(dto2vo(memberDTO));
            }
            return memberVOS;
        }
        return new ArrayList<>();
    }

    private MemberVO dto2vo(MemberDTO memberDTO){
        MemberVO memberVO = null;
        if(memberDTO != null){
            memberVO = BeanConverter.to(memberDTO,MemberVO.class);
            memberVO.setBirthdayStr(DateUtil.convertDateToString(DateUtil.SHORTDATEFORMATER,memberDTO.getBirthday()));
            memberVO.setAvatarUrl(PictureUtils.assemblePicUrl(memberDTO.getAvatarUrl()));
        }
        return memberVO;
    }
}
