package cn.kkl.mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kkl.mall.pojo.E3Result;
import cn.kkl.mall.pojo.TbUser;
import cn.kkl.mall.sso.service.RegisterService;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/page/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkDateValid(@PathVariable String param,@PathVariable Integer type) {
		E3Result result = registerService.checkData(param, type);
		return result;
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public E3Result register(TbUser user) {
		E3Result result=registerService.addUser(user);
		return result;
	}

}
