package com.noaa.base;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("errorController")
public class ErrorController {
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String errorView(BaseViewModel vm,Model model){
	    model.addAttribute("vm", vm);
		return "error";
	}
	
	@RequestMapping(value="/error",method=RequestMethod.POST)
	public String errorPostView(BaseViewModel vm,Model model){
	    model.addAttribute("vm", vm);
		return "error";
	}
}
