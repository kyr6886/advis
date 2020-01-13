package kr.dis.std.advis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.base.ViewerSysKeyword;

import kr.dis.std.common.service.ICommonService;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.web.viewmodel.EventViewModel;
@Controller("disasterController")
public class DisasterController extends BaseController{
	
	//@Autowired
	//private IYearDmeService yearDmeService;
	
	@Autowired
	private IEventService eventService;
	@Autowired
	private ICommonService commonService;
	@RequestMapping(value="/advis/disaster/status",method=RequestMethod.GET)
	public String statusDisasterView(EventViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/advis/disaster/status";
	}
	
	@RequestMapping(value="/advis/disaster/rain",method=RequestMethod.GET)
	public String rainDisasterView(EventViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/advis/disaster/rain";
	}
	
	@RequestMapping(value="/advis/disaster/snow",method=RequestMethod.GET)
	public String snowDisasterView(EventViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/advis/disaster/snow";
	}
	
	@RequestMapping(value="/advis/disaster/earthquake",method=RequestMethod.GET)
	public String earthquakeDisasterView(EventViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/advis/disaster/earthquake";
	}
	
	@RequestMapping(value="/advis/disaster/status/eventItem/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel  getDisEventItemList(@RequestBody EventViewModel vm, Model model)throws Exception{
		
		EventDto dto=new EventDto();
		dto.setDamage_code(vm.getParamDisType());
		dto.setBeg_date(vm.getParamStDate().substring(0, 6));
		dto.setEnd_date(vm.getParamEndDate().substring(0, 6));
		dto.setContents(vm.getParamTitle());
		vm.setResultList(eventService.selectDisEventByPeriodList(dto));
		
		List<String> paramDamageCodes=new ArrayList<String>();
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON);
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON_RAIN);
		
		vm.setListStatistics(commonService.listBuildDamges(paramDamageCodes, vm.getParamStDate(), vm.getParamEndDate()));
		model.addAttribute("vm",vm);
		return vm;
	}
	
	
}