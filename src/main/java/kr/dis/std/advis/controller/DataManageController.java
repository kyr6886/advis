package kr.dis.std.advis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;

import kr.dis.std.data.service.IDataManageService;
import kr.dis.std.web.viewmodel.DataManageViewModel;

@Controller("dataManageController")
public class DataManageController extends BaseController {
	@Autowired
	private IDataManageService dataManageService;
	
	@RequestMapping(value="/data/kmaTyphoon/list",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel listKmaTyphoonAction(@RequestBody DataManageViewModel vm){
		vm.setTyphoonList(dataManageService.listTyphoon(vm.getPageNo(), vm.getPageSize()));
		vm.setTotalCount(dataManageService.countTyphoon());
		return vm;
	}
	
	@RequestMapping(value="/data/kmaTyphoon/create",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel createKmaTyphoonAction(@RequestBody DataManageViewModel vm){
		vm.setResultCount(dataManageService.createTyphoon(vm.getStn_id(), vm.getTm_fc(),
															vm.getTyp_seq(), vm.getTyp_name(),
															vm.getTyp_en(), vm.getTyp_lat(),
															vm.getTyp_lon(), vm.getTyp_ws(),
															vm.getTyp_ps(), vm.getTyp_15(),
															vm.getFt1_lat(), vm.getFt1_lon(),
															vm.getFt1_ws(), vm.getFt1_ps(),
															vm.getFt2_lat(), vm.getFt2_lon(),
															vm.getFt2_ws(), vm.getFt2_ps(),
															vm.getFt3_lat(), vm.getFt3_lon(),
															vm.getFt3_ws(), vm.getFt3_ps()));
		return vm;
	}
	
	@RequestMapping(value="/data/kmaTyphoon/delete",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel deleteKmaTyphoonAction(@RequestBody DataManageViewModel vm){
		vm.setResultCount(dataManageService.deleteTyphoon(vm.getTm_fc()));
		return vm;
	}
	
	@RequestMapping(value="/data/kmaInform/list",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel listKmaInformAction(@RequestBody DataManageViewModel vm){
		vm.setInformList(dataManageService.listInform(vm.getPageNo(), vm.getPageSize()));
		vm.setTotalCount(dataManageService.countInform());
		return vm;
	}
	
	@RequestMapping(value="/data/kmaInform/create",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel createKmaInformAction(@RequestBody DataManageViewModel vm){
		vm.setResultCount(dataManageService.createInform(vm.getCd_stn(), vm.getDt_tm_fc(),
														vm.getNm_man_fc(), vm.getEtc_ttl(),
														vm.getSect_area_txt(), vm.getDt_tm_ef_txt()));
		return vm;
	}
	
	@RequestMapping(value="/data/kmaInform/delete",method=RequestMethod.POST)
	public @ResponseBody DataManageViewModel deleteKmaInformAction(@RequestBody DataManageViewModel vm){
		vm.setResultCount(dataManageService.deleteInform(vm.getDt_regt()));
		return vm;
	}
}