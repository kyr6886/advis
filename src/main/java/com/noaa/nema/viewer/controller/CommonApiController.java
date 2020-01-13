package com.noaa.nema.viewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.nema.viewer.area.service.IAreaCodeService;
import com.noaa.nema.viewer.service.IYearDmeService;
import com.noaa.nema.viewer.viewmodel.AreaCodeViewModel;
@Controller("commonApiController")
public class CommonApiController {
	
	@Autowired
	private IAreaCodeService areaCodeService;
	
	@Autowired
	private IYearDmeService yearDmeService;
	
	@RequestMapping(value="/api/common/code/area/list.do",method=RequestMethod.POST)
	public @ResponseBody AreaCodeViewModel listAreaCode(@RequestBody AreaCodeViewModel vm,Model model){
		vm.setListAreaSidoCodes(areaCodeService.listSido());
		vm.setListAreaGunguCodes(areaCodeService.listGungu(vm.getListAreaSidoCodes().get(0).getCode()));
		return vm;
	}
	@RequestMapping(value="/api/common/code/area/gungu/list.do",method=RequestMethod.POST)
	public @ResponseBody AreaCodeViewModel listGunguAreaCode(@RequestBody AreaCodeViewModel vm,Model model){
		vm.setListAreaGunguCodes(areaCodeService.listGungu(vm.getParamSido()));
		return vm;
	}
	
	@RequestMapping(value="/api/advis/insert/summery/sido/{paramStDate}/{paramEndDate}",method=RequestMethod.GET)
	public int insertSummerySidoList(@PathVariable String paramStDate, @PathVariable String paramEndDate, Model model, HttpServletResponse response,HttpServletRequest request){
		int rs = 0;
		rs = yearDmeService.insertSummerySidoList(paramStDate, paramEndDate);
		return rs;
	}
	
	@RequestMapping(value="/api/advis/insert/summery/sigungu/{paramStDate}/{paramEndDate}",method=RequestMethod.GET)
	public int insertSummerySigunguList(@PathVariable String paramStDate, @PathVariable String paramEndDate, Model model, HttpServletResponse response,HttpServletRequest request){
		int rs = 0;
		rs = yearDmeService.insertSummerySigunguList(paramStDate, paramEndDate);
		return rs;
	}
}
