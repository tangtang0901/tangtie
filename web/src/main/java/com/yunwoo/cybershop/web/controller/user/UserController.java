package com.yunwoo.cybershop.web.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yunwoo.cybershop.dto.UserDTO;
import com.yunwoo.cybershop.utils.MD5Util;
import com.yunwoo.cybershop.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/2000/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(String username,String password,HttpServletRequest request,Map context){
		UserDTO userDTO = userService.getByUserName(username);
		if(userDTO != null){
			//密码校验成功
			String passwordMd5 = MD5Util.MD5(password);
			if(passwordMd5.equalsIgnoreCase(userDTO.getPassword())){
				request.getSession().setAttribute("user", userDTO);
				return "redirect:/";
			}
		}
		context.put("isValid", false);
		return "forward:/loginPage";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("user");
		return "redirect:/loginPage";
	}
}
