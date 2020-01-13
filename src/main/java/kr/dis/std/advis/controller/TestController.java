package kr.dis.std.advis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noaa.base.BaseController;

import kr.dis.std.web.viewmodel.AnalysisViewModel;

@Controller("test1Controller")
public class TestController extends BaseController{
	@RequestMapping(value="/test/test",method=RequestMethod.GET)
	public String similarPathTyphoonView(AnalysisViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/test/test";
	}
}
