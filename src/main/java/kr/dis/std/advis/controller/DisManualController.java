package kr.dis.std.advis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;

import kr.dis.std.advis.dao.TbDisManualDto;
import kr.dis.std.advis.service.IDisManualService;
import kr.dis.std.web.viewmodel.DisasterViewModel;

@Controller
public class DisManualController extends BaseController {
	
	@Autowired
	private IDisManualService disManualService;
	
	@RequestMapping(value="/disaster/manual/list",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel listDisManual(@RequestBody DisasterViewModel vm){
		TbDisManualDto paramBean = new TbDisManualDto();
		paramBean.setCtg_id(vm.getParamCtgId());
		vm.setResultManualList(disManualService.listDisManual(paramBean));
		return vm;
	}
	
	@RequestMapping(value="/disaster/manual/list/group",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel selectListGroupByDisManual(@RequestBody DisasterViewModel vm){
		TbDisManualDto paramBean = new TbDisManualDto();
		paramBean.setCtg_id(vm.getParamCtgId());
		vm.setResultManualList(disManualService.selectListGroupByDisManual(paramBean));
		return vm;
	}
	
	
	@RequestMapping(value="/disaster/manual/delete",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel deleteDisManual(@RequestBody DisasterViewModel vm){
		vm.setResultCount(disManualService.deleteDisManual(vm.getParamSeq()));
		return vm;
	}
	
	@RequestMapping(value="/disaster/manual/insert",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel insertDisManual(@RequestBody DisasterViewModel vm){
		TbDisManualDto paramBean = new TbDisManualDto();
		paramBean.setCtg_id(vm.getParamCtgId());
		paramBean.setGrp_code(vm.getParamGrpCode());
		paramBean.setSys_code(vm.getParamSysCode());
		paramBean.setManual_title(vm.getParamTitle());
		paramBean.setManual_contents(vm.getParamContents());
		vm.setResultCount(disManualService.insertDisManual(paramBean));
		return vm;
	}

}
