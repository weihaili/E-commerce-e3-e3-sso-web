package cn.kkl.mall.sso.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kkl.mall.pojo.E3Result;
import cn.kkl.mall.pojo.TbUser;
import cn.kkl.mall.sso.service.TokenService;
import cn.kkl.mall.utils.JsonUtils;

@Controller
public class TokenController {
	
	@Autowired
	private TokenService tokenService;

	
	/*@RequestMapping(value="/user/token/{token}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable String token,String callback) {
		TbUser user = tokenService.getUserByToken(token);
		String result="";
		if (StringUtils.isBlank(callback)) {
			if (user==null) {
				result = JsonUtils.objectToJson(E3Result.build(201, "user login expired,please re-login"));
			}else {
				result=JsonUtils.objectToJson(E3Result.ok(user));
			}
		}else {
			if (user==null) {
				result=callback+"("+JsonUtils.objectToJson(E3Result.build(201, "user login expired,please re-login"))+");";
			}else {
				result=callback+"("+JsonUtils.objectToJson(E3Result.ok(user))+");";
			}
		}
		
		return result;
	}*/
	
	/**
	 * spring version minimum 4.1
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback) {
		TbUser user = tokenService.getUserByToken(token);
		Object result=null;
		if (user==null) {
			result = E3Result.build(201, "user login expired,please re-login");
		}else {
			result=E3Result.ok(user);
		}
		if (StringUtils.isBlank(callback)) {
			return result;
		}else {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return result;
		}
	}
}
