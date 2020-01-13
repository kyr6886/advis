package com.noaa.base.meta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.meta.service.IMetaLawService;
import com.noaa.base.meta.viewmodel.MetaViewModel;

@Controller("metaLawController")
public class MetaLawController extends BaseController {
	@Autowired
	private IMetaLawService metaLawService;
	
	@RequestMapping(value="/api/meta/law/sido/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listSidoLawAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListLaw(metaLawService.listSido());
		return vm;
	}
	@RequestMapping(value="/api/meta/law/gungu/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listGunguLawAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListLaw(metaLawService.listGungu(vm.getParamSido()));
		return vm;
	}
	
	@RequestMapping(value="/api/meta/law/dong/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listDongLawAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListLaw(metaLawService.listDong(vm.getParamGungu()));
		return vm;
	}
	
	@RequestMapping(value="/api/meta/law/ri/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listRiLawAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListLaw(metaLawService.listRi(vm.getParamDong()));
		return vm;
	}
	
	
}
