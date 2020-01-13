package kr.dis.std.advis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noaa.base.BaseController;

import kr.dis.std.web.viewmodel.DisasterViewModel;
import kr.dis.std.web.viewmodel.EventViewModel;

@Controller("disReportController")
public class DisReportController extends BaseController {
	@RequestMapping(value="/report/typ/write",method=RequestMethod.GET)
	public String  writeTypReportView(EventViewModel vm, Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/report/write-typ";
	}
	
	@RequestMapping(value="/report/list",method=RequestMethod.GET)
	public String  listDisasterReportView(EventViewModel vm, Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/report/list";
	}
	
	@RequestMapping(value="/report/neq/write",method=RequestMethod.GET)
	public String  writeNeqReportView(EventViewModel vm, Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/report/write-neq";
	}
	
	@RequestMapping(value="/report/vai/write",method=RequestMethod.GET)
	public String  writeVaiReportView(EventViewModel vm, Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/report/write-vai";
	}
	
	@RequestMapping(value="/report/nhr/write",method=RequestMethod.GET)
	public String  writeNhrReportView(EventViewModel vm, Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/report/write-rain";
	}
}
