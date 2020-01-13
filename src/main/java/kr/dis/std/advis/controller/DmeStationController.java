package kr.dis.std.advis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.area.service.IAreaCodeService;

import kr.dis.std.station.service.IDmeStationService;
import kr.dis.std.web.viewmodel.DmeStationViewModel;

@Controller
public class DmeStationController extends BaseController{

	@Autowired
	private IDmeStationService dmeStationService;
	
	@Autowired
	private IAreaCodeService areaCodeService;
	
	@RequestMapping(value="/advis/damage/station/list",method=RequestMethod.GET)
	public String popup(DmeStationViewModel vm){
		return "/advis/damage/popupView";
	}
	
	@RequestMapping(value="/advis/damage/station/list",method=RequestMethod.POST)
	public @ResponseBody DmeStationViewModel list(@RequestBody DmeStationViewModel vm){
		vm.setListDmeStation(dmeStationService.list(vm.getParamArea(), vm.getParamType()));
		return vm;
	}
	
	@RequestMapping(value="/advis/damage/station/init",method=RequestMethod.POST)
	public @ResponseBody DmeStationViewModel init(@RequestBody DmeStationViewModel vm){
		vm.setListTypeDmeStation(dmeStationService.listTypeDmeStation());
		vm.setListSido(areaCodeService.listSido());
		return vm;
	}
}