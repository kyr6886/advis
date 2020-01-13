package com.noaa.nema.viewer.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.nema.viewer.service.IRainAnlService;
import com.noaa.nema.viewer.viewmodel.DataAnlViewModel;
import com.noaa.nema.viewer.viewmodel.RainAnlViewModel;
@Controller("rainAnlController")
public class RainAnlController {
	
	@Autowired
	IRainAnlService rainAnlService;
	
	@RequestMapping(value={"/data/rain/status/{id}"},method=RequestMethod.GET)
	public String rainStatusDataView(RainAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "";
	}
	
	@RequestMapping(value="/api/data/rain/max/{id}",method=RequestMethod.POST)
	public @ResponseBody RainAnlViewModel maxRainDataAction(@RequestBody RainAnlViewModel vm,Model model){
		return vm;
	}
	@RequestMapping(value="/rain/damage/intro.do",method=RequestMethod.GET)
	public String introDamageRainView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/rain/intro";
	}
	@RequestMapping(value="/rain/damage/viewer.do",method=RequestMethod.GET)
	public String viewerDamageRainView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/rain/viewer";
	}
	
	@RequestMapping(value="/rain/damage/image.do",method=RequestMethod.GET)
	public String imageDamageRainView(DataAnlViewModel vm,Model model){
		model.addAttribute("vm", vm);
		return "/viewer/rain/image";
	}
	
	@RequestMapping(value="/api/period/rain/obs/list.do", method = RequestMethod.POST)
	public @ResponseBody RainAnlViewModel listObsRnDay(@RequestBody RainAnlViewModel vm, Model model) {
		vm.setListSidoAsos(rainAnlService.listObsRnDay(vm.getParamStDate(), vm.getParamRnDay()));
		return vm;
	}
	
}
