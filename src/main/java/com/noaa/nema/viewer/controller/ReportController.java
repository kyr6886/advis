package com.noaa.nema.viewer.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.file.service.IFileService;
import com.noaa.nema.viewer.viewmodel.ReportViewModel;
import com.noaa.nema.viewer.viewmodel.YearDmeViewModel;


@Controller("reportController")
public class ReportController {
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value = "/report/index.do", method = RequestMethod.GET)
	public String indexReportView(ReportViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/report/index";
	}
	
	@RequestMapping(value = "/report/regist.do", method = RequestMethod.GET)
	public String registReportView(ReportViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/report/regist";
	}
	
	@RequestMapping(value = "/api/report/images/insert.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> createFile(YearDmeViewModel vm, ModelMap model, HttpServletRequest request)
			throws Exception {
		
		
		HttpSession session = request.getSession();

    	String paramId = "USER2";
	
	//	int rs = fileService.createFile(vm.getUploadFiles(), vm.getParamStDate(), vm.getParamEndDate(), paramId, vm.getParamFileName(), "Y");
		HashMap<String, String> map = new HashMap<String, String>();
	//	map.put("fileStatus", Integer.toString(rs));
		return map;

	}
}
