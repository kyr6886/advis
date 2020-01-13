package kr.dis.std.advis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noaa.base.BaseController;
import com.noaa.base.global.SysKeyword;

//import kr.dis.std.advisMain.service.IAdvisMainService;
import kr.dis.std.web.viewmodel.AdvisMainViewModel;

@Controller("advisMainApiController")
public class AdvisMainApiController extends BaseController{
	
	
	
	//@Authorize(url="advis/login/login")	
	/*@RequestMapping(value="/advis/manage/main",method=RequestMethod.GET)
	public String mainView(AdvisMainViewModel vm,Model model,HttpServletResponse response,HttpServletRequest request)throws Exception{
		return "/advis/main/main";
	}
	*/
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String mainView(AdvisMainViewModel vm,Model model,HttpServletResponse response,HttpServletRequest request)throws Exception{
		Object userId = request.getSession().getAttribute(SysKeyword.SESSION_USER_ID); 
		if(userId == null){
			return "redirect:/advis/login/login";
		}
		return "/main/main";
	}
	
	
	
}
