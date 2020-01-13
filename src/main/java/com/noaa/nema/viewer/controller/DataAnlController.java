package com.noaa.nema.viewer.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.noaa.nema.viewer.viewmodel.DataAnlViewModel;
import com.noaa.nema.viewer.viewmodel.YearDmeViewModel;
@Controller("dataAnlController")
public class DataAnlController {
	@RequestMapping(value="/data/damage/rain.do",method=RequestMethod.GET)
	public String rainDamageDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/statistics/rainDamage";
	}
	@RequestMapping(value="/data/damage/type.do",method=RequestMethod.GET)
	public String typeDamageDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/statistics/typeDamage";
	}
	@RequestMapping(value="/data/damage/history.do",method=RequestMethod.GET)
	public String historyDamageDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/statistics/history";
	}
	
	@RequestMapping(value="/data/damage/dmeTop.do",method=RequestMethod.GET)
	public String dmeTopDamageDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/statistics/dmeTop";
	}
	
	@RequestMapping(value="/data/damage/disasterTop.do",method=RequestMethod.GET)
	public String disasterTopDamageDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/statistics/disasterTop";
	}
	
	@RequestMapping(value="/data/damage/{detailType}/{law_code}/{beg_date}/{end_date}/{damage_code}/dmeTopDetail.do",method=RequestMethod.GET)
	public String dmePopDamageDataView(YearDmeViewModel vm,Model model,   @PathVariable String detailType,  @PathVariable String law_code, @PathVariable String beg_date, @PathVariable String end_date,  @PathVariable String damage_code){
		vm.setParamSido(law_code);
		vm.setParamStDate(beg_date);
		vm.setParamEndDate(end_date);
		vm.setDataTypeCode(detailType);
		vm.setParamDmeCode(damage_code);
		model.addAttribute("vm", vm);
		return "/viewer/statistics/dmeTopDetail";
	}
	
	@RequestMapping(value="/data/damage/detail/{detailType}/{law_code}/{beg_date}/{end_date}/introDetail.do",method=RequestMethod.GET)
	public String dmePopDetailDamageDataView(YearDmeViewModel vm,Model model, @PathVariable String detailType, @PathVariable String law_code, @PathVariable String beg_date, @PathVariable String end_date){
		vm.setParamSido(law_code);
		vm.setParamStDate(beg_date);
		vm.setParamEndDate(end_date);
		vm.setDataTypeCode(detailType);
		model.addAttribute("vm", vm);
		return "/viewer/rain/introDetail";
	}
	
	@RequestMapping(value="/data/damage/dangerAws.do",method=RequestMethod.GET)
	public String dangerAwsDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/aws/dangerAws";
	}
	
	@RequestMapping(value="/data/damage/dangerWater.do",method=RequestMethod.GET)
	public String dangerWaterDataView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/aws/dangerWater";
	}
	
}
