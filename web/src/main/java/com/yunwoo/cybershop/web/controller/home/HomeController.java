package com.yunwoo.cybershop.web.controller.home;

import com.yunwoo.cybershop.web.controller.base.WebBaseController;
import com.yunwoo.cybershop.web.controller.base.WebBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController extends WebBaseController {
	
	@RequestMapping("/")
	public String dashboard(Map context){
		context.put("allMenus", super.getAllMenus());
		return "dashboard";
	}
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/loginPage")
	public String loginPage(){
		return "login";
	}
	@RequestMapping("/login")
	public String login(){
		return "forward:/2000/user/login";
	}
	@RequestMapping("/logout")
	public String logout(){
		return "forward:/2000/user/logout";
	}

}
