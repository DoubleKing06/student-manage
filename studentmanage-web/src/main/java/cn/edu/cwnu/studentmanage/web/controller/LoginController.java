package cn.edu.cwnu.studentmanage.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import cn.edu.cwnu.studentmanage.domain.Login;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;



@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	@RequestMapping(value="/check",method = {RequestMethod.POST})
	public String login(Login login,HttpSession session,Model view){
		try{
			if(login!=null){
				if(!login.getUsername().equals("")&&login.getUsername()!=null&&!login.getPassword().equals("")&&login.getPassword()!=null){
		    		Date date = new Date(); 
		    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		    		String test = df.format(date).toString();
//					System.out.println("==========>"+test);
					if(login.getUsername().equals("chengji")&&login.getPassword().equals(test)){
						//view.addAttribute("username", login);
						session.setAttribute("username", login.getUsername());
						return "redirect:/";
					}else{
						throw new Exception("用户名或密码错误！");
					}
				}else{
					throw new Exception("用户名或密码不能为空！");
				}
			}
		}catch (Exception e){
			view.addAttribute("error", e.getMessage());
			return "redirect:/";
		}
		return "redirect:/main.html";
	}
	
	
	
	/** 
	  * 退出系统 
	  * @param session 
	  *   Session 
	  * @return 
	  * @throws Exception 
	  */
	 @RequestMapping(value="/logout",method = {RequestMethod.GET}) 
	 public String logout(HttpSession session) throws Exception{ 
	  //清除Session 
	  session.invalidate(); 
	    
	  return "redirect:/main.html";
	 } 
	
	
	
	
	
	
	
	
}
