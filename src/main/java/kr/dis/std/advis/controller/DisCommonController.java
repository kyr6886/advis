package kr.dis.std.advis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.service.IYearDmeService;

import kr.dis.std.advis.service.IStatisticsService;
import kr.dis.std.common.service.ICommonService;
import kr.dis.std.web.viewmodel.StatisticsViewModel;
@Controller("disCommonController")
public class DisCommonController extends BaseController {
	@Autowired
	private ICommonService stdCommonService;
	@Autowired
	private IStatisticsService statisticsService;

	@Autowired
	private IYearDmeService yearDmeService;
	
	
	@RequestMapping(value="/api/yeardme/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel  listYearDmeAction(@RequestBody StatisticsViewModel vm,Model model){
		vm.setListYearDme(yearDmeService.listYearDme(vm.getParamStartDate(), vm.getParamEndDate(), vm.getParamDamageCode()));		
		return vm;
	}

	
	
}
