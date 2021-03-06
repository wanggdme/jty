package com.tianyu.jty.system.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tianyu.jty.common.utils.Global;

/**
 * 登录controller
 * @author ty
 * @date 2015年1月14日
 */
@Controller
//@RequestMapping(value = "{adminPath}") 
public class LoginController{
	
	//响应访问项目根路径的情况    http://localhost:8080/jty 或者 http://localhost:8080/jty/
	@RequestMapping(value="/")
	public String loginPage() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()||subject.isRemembered()){
			return "redirect:"+Global.getAdminPath();
		} 
		return "system/login";
	}
	
	/**
	 * 默认页面
	 * @return
	 */
	@RequestMapping(value="{adminPath}/login",method = RequestMethod.GET)
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()||subject.isRemembered()){
			return "redirect:"+Global.getAdminPath();
		} 
		return "system/login";
	}

	/**
	 * 登录失败
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="{adminPath}/login",method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "system/login";
	}

	/**
	 * 登出
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="{adminPath}/logout")
	public String logout(Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "system/login";
	}
	
}
