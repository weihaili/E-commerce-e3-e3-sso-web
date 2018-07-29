package cn.kkl.mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kkl.mall.pojo.E3Result;
import cn.kkl.mall.sso.service.LoginService;
import cn.kkl.mall.utils.CookieUtils;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/page/login")
	public String showLoginPage() {
		return "login";
	}
	
	@Value("${TOKEN_IN_COOKIE_KEY}")
	private String tokenInCookieKey;

	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username,String password,HttpServletRequest request,HttpServletResponse response) {
		E3Result userLogin = loginService.userLogin(username, password);
		if (userLogin.getStatus()==200) {
			String token = userLogin.getData().toString();
			CookieUtils.setCookie(request, response, tokenInCookieKey, token);
		}
		return userLogin;
	}
	
}
