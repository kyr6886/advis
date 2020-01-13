package com.noaa.base.meta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.meta.service.IMetaAdmService;
import com.noaa.base.meta.viewmodel.MetaViewModel;

@Controller("metaAdmController")
public class MetaAdmController extends BaseController {
	@Autowired
	private IMetaAdmService metaAdmService;
	
	@RequestMapping(value="/api/meta/adm/sido/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listSidoAdmAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListAdm(metaAdmService.listSido());
		return vm;
	}
	@RequestMapping(value="/api/meta/adm/gungu/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listGunguAdmAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListAdm(metaAdmService.listGungu(vm.getParamSido()));
		return vm;
	}
	
	@RequestMapping(value="/api/meta/adm/dong/list",method=RequestMethod.POST)
	public @ResponseBody MetaViewModel listDongAdmAction(@RequestBody MetaViewModel vm,Model model){
		vm.setListAdm(metaAdmService.listDong(vm.getParamGungu()));
		return vm;
	}
	
	
}
