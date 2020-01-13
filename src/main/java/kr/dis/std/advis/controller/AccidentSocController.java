package kr.dis.std.advis.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.Authorize;
import com.noaa.base.BaseController;

import kr.dis.std.history.service.IAccidentSocService;
import kr.dis.std.history.viewmodel.AccidentSocViewModel;
import kr.dis.std.lawext.service.IMetaLawExtService;

@Controller
public class AccidentSocController extends BaseController {
	
	@Autowired
	private IAccidentSocService accidentSocService;
	
	@Autowired
	private IMetaLawExtService metaLawExtService;

	@RequestMapping(value="/history/accident/list",method=RequestMethod.POST)
	public @ResponseBody AccidentSocViewModel listAccidentSoc(@RequestBody AccidentSocViewModel vm){
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		String month_str = (month > 9 ? "" : "0") + month;
		
		vm.setListAccidentSoc(accidentSocService.listAccidentSoc(month_str, vm.getParamList()));
		vm.setListAccidentGroupCount(accidentSocService.listAccidentGroupCount(month_str, vm.getParamList()));
		
		return vm;
	}
	
	@RequestMapping(value="/history/accident/checkArea",method=RequestMethod.GET)
	public String checkAreaAccidentSoc(AccidentSocViewModel vm){
		return "/advis/interest/checkArea";
	}
	
	@Authorize
	@RequestMapping(value="/history/accident/listCheckedAreaView",method=RequestMethod.POST)
	public @ResponseBody AccidentSocViewModel listCheckedAreaView(@RequestBody AccidentSocViewModel vm){
		vm.setListSelectedSidoCodes(accidentSocService.listCheckedArea(getCommonParams().getLoginUserID()));
		vm.setListAllSidoCodes(metaLawExtService.listAllSidoCodes());
		return vm;
	}
	
	@RequestMapping(value="/history/accident/createCheckedArea",method=RequestMethod.POST)
	public @ResponseBody AccidentSocViewModel createCheckedArea(@RequestBody AccidentSocViewModel vm){
		accidentSocService.createCheckedArea(getCommonParams().getLoginUserID(), vm.getListSelectedSidoCodes());
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/history/accident/listCheckedArea",method=RequestMethod.POST)
	public @ResponseBody AccidentSocViewModel listCheckedArea(@RequestBody AccidentSocViewModel vm){
		vm.setListSelectedSidoCodes(accidentSocService.listCheckedArea(getCommonParams().getLoginUserID()));
		return vm;
	}
}