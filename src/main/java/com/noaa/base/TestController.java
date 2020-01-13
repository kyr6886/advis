package com.noaa.base;

import java.io.File;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.file.service.IFileService;
import com.noaa.base.open.rss.service.IWetherRssService;
import com.noaa.base.test.viewmodel.TestViewModel;

@Controller("testController")
public class TestController extends BaseController {
	@Autowired
	private IWetherRssService wetherRssService;
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value="/test/rss/wether",method=RequestMethod.GET)
	public String unitTestView(BaseViewModel vm,Model model){
		wetherRssService.list("1159068000");
		return "/base/test";
	}
	
	@RequestMapping(value="/test/grid",method=RequestMethod.GET)
	public String gridTestView(BaseViewModel vm,Model model){
		
		return "/base/test/grid";
	}
	@RequestMapping(value="/test/vwolrd/posSelector",method=RequestMethod.GET)
	public String posSelectorTestView(BaseViewModel vm,Model model){
		
		return "/base/test";
	}
	@RequestMapping(value="/test/html5/imageConverter",method=RequestMethod.GET)
	public String imageConverterTestView(BaseViewModel vm,Model model){
		
		return "/base/test/imageConverter";
	}
	
	@RequestMapping(value="/test/html5/saveImage",method=RequestMethod.POST)
	public @ResponseBody TestViewModel saveImageTestAction(@RequestBody TestViewModel vm,Model model,HttpServletRequest request){
		String path=request.getSession().getServletContext().getRealPath("/");
		path+="images"+File.separator+"base64"+File.separator;
		String rsFileName=fileService.createBase64ImageSave(vm.getBase64Str(), path);		
		vm.setFileName(rsFileName);
		return vm;
	}
	
}
