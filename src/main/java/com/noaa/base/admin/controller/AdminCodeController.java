package com.noaa.base.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.admin.viewmodel.AdminCodeViewModel;
import com.noaa.base.code.dao.CodeGrpDTO;
import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.code.service.ICodeSysService;
import com.noaa.base.meta.service.IMetaAdmService;
@Controller("adminCodeController")
public class AdminCodeController extends BaseController{
	@Autowired
	private ICodeSysService codeSysService;
	@Autowired
	private IMetaAdmService codeAdmService;
	@RequestMapping(value="/api/admin/code/grp/list",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel lisGrpCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		vm.setListCodeGrp(codeSysService.listGrp());
		return vm;
	}
	@RequestMapping(value="/api/admin/code/sys/list",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel lisSysCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		vm.setListCodeSys(codeSysService.list(vm.getParamCodeGrp()));
		return vm;
	}
	@RequestMapping(value="/api/admin/code/grp/create",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel createGrpCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		CodeGrpDTO codeGrpDto=new CodeGrpDTO();
		codeGrpDto.setGrp_code(vm.getParamCodeGrp());
		codeGrpDto.setGrp_title(vm.getParamCodeGrpTxt());
		
		vm.setResultCount(codeSysService.createCodeGrp(codeGrpDto,getCommonParams()));
		vm.setListCodeGrp(codeSysService.listGrp());
		return vm;
	}
	
	@RequestMapping(value="/api/admin/code/grp/drop",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel deleteGrpCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		vm.setResultCount(codeSysService.dropCodeGrp(vm.getParamCodeGrp()));
		vm.setListCodeGrp(codeSysService.listGrp());
		return vm;
	}
	@RequestMapping(value="/api/admin/code/sys/drop",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel deleteSysCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		vm.setResultCount(codeSysService.dropCodeSys(vm.getParamSysCode()));
		vm.setListCodeSys(codeSysService.list(vm.getParamCodeGrp()));
		return vm;
	}
	@RequestMapping(value="/api/admin/code/sys/create",method=RequestMethod.POST)
	public @ResponseBody AdminCodeViewModel createSysCodeAdminAction(@RequestBody AdminCodeViewModel vm,Model model)throws Exception{	
		CodeSysDTO codeSysDto=new CodeSysDTO();
		codeSysDto.setGrp_code(vm.getParamCodeGrp());
		codeSysDto.setSys_code(vm.getParamSysCode());
		codeSysDto.setSys_title(vm.getParamSysCodeTxt());
		vm.setResultCount(codeSysService.createCodeSys(codeSysDto,getCommonParams()));
		vm.setListCodeSys(codeSysService.list(vm.getParamCodeGrp()));
		return vm;
	}
	
	
	
	
}
