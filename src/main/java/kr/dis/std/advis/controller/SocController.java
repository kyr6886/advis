package kr.dis.std.advis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.area.service.IAreaCodeService;

import kr.dis.std.advis.dao.TbAccidentSocDto;
import kr.dis.std.advis.service.IStatisticsService;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.service.ICateoryService;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.history.dao.AccidentSocDto;
import kr.dis.std.web.viewmodel.DisasterViewModel;
import kr.dis.std.web.viewmodel.EventViewModel;

@Controller("socController")
public class SocController extends BaseController{
	
	@Autowired
	private ICateoryService cateoryService;
	@Autowired
	private IAreaCodeService areaCodeService;
	@Autowired
	private IStatisticsService statisticsService;
	@Autowired
	private IEventService eventService;

	
	@RequestMapping(value="/advis/disaster/society",method=RequestMethod.GET)
	public String statusDisasterView(EventViewModel vm,Model model){
		model.addAttribute("vm",vm);
		return "/advis/disaster/society";
	}
	
	@RequestMapping(value="/advis/disaster/society/ctgList",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListSocietyCtgId(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		CategoryDTO categoryDto=new CategoryDTO();
		categoryDto.setCtg_id(vm.getParamCtgId());
		categoryDto.setDepth(vm.getParamDepth());
		
		vm.setResultList(cateoryService.listCategory(categoryDto));
		model.addAttribute("vm",vm);
		return vm;
	}
	
	@RequestMapping(value="/advis/disaster/society/areaSidoCodeList",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListAreaSidoCode(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		vm.setResultSidoList(areaCodeService.listSido());
		return vm;
	}
	
	@RequestMapping(value="/advis/disaster/society/areaSigunguCodeList",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListAreaSigunguCode(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		vm.setResultSigunguList(areaCodeService.listGungu(vm.getParamSidoCode()));
		return vm;
	}
	
	@RequestMapping(value="/advis/disaster/society/dmeInfoList",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListAccidentSocGroup(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		AccidentSocDto paramBean = new AccidentSocDto();
		paramBean.setCtg_id(vm.getParamCtgId());
		paramBean.setAddr_code(vm.getParamSigunguCode());
		paramBean.setObz_dt(vm.getParamMonth());
       
		vm.setResultAccidentSocList(statisticsService.listAccidentSocGroup(paramBean));
		return vm;
	}
	
	@RequestMapping(value="/advis/disaster/society/eventItem/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel  getCtgIdDisEventItemList(@RequestBody EventViewModel vm, Model model)throws Exception{
		EventDto dto=new EventDto();
		dto.setCtg_id(vm.getParamCtgId());
		dto.setBeg_date(vm.getParamStDate());
		dto.setEnd_date(vm.getParamEndDate());
		dto.setContents(vm.getParamTitle());
		vm.setResultList(eventService.selectCtgIdDisEventByPeriodList(dto));
		
		model.addAttribute("vm",vm);
		return vm;
	}

	@RequestMapping(value="/advis/disaster/society/allList",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListAccidentSocAll(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		AccidentSocDto paramBean = new AccidentSocDto();
		paramBean.setCtg_id(vm.getParamCtgId());
		paramBean.setAddr_code(vm.getParamSigunguCode());
		paramBean.setObz_dt(vm.getParamMonth());
		
		vm.setResultAccidentSocList(statisticsService.listAccidentSocAll(paramBean));
		return vm;
	}
	
	
	@RequestMapping(value="/advis/disaster/society/count/monthly",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getListAccidentSocCountByMonthly(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		AccidentSocDto paramBean = new AccidentSocDto();
		paramBean.setAddr_code(vm.getParamSigunguCode());
		vm.setResultAccidentSocList(statisticsService.listAccidentSocCountByMonthly(paramBean));
		return vm;
	}
	
	@RequestMapping(value="/advis/disaster/society/search/count/monthly",method=RequestMethod.POST)
	public @ResponseBody DisasterViewModel  getSearchListAccidentSocCountByMonthly(@RequestBody DisasterViewModel vm, Model model)throws Exception{
		AccidentSocDto paramBean = new AccidentSocDto();
		paramBean.setDi_inf_name(vm.getParamText());
		
		vm.setResultAccidentSocList(statisticsService.searchListAccidentSocCountByMonthly(paramBean));
		return vm;
	}
}
