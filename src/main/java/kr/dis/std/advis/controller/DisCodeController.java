package kr.dis.std.advis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.code.service.ICodeSysService;
import com.noaa.base.global.SysKeyword;

import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.service.ICateoryService;
import kr.dis.std.category.viewmodel.CategoryViewModel;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventImgDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.web.viewmodel.EventViewModel;

@Controller("disCodeController")
public class DisCodeController extends BaseController {

	@Autowired
	private ICateoryService cateoryService;
	
	@Autowired
	private IEventService eventService;
	@Autowired
	private ICodeSysService codeSysService;
	
	@RequestMapping(value="/advis/code/codeView/{paramCtgId}",method=RequestMethod.GET)
	public String getCtgIdView(CategoryViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/code/codeView";
	}
	
	@RequestMapping(value="/advis/code/codeView/{paramCtgId}/{paramEvtId}",method=RequestMethod.GET)
	public String getCtgEvtIdView(CategoryViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm); 
		return "/advis/code/codeView";
	}
	@RequestMapping(value="/advis/code/search",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel searchCodeAction(@RequestBody CategoryViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		vm.setList(cateoryService.listCategory(vm.getParamCtgId(),vm.getParamDepth(),vm.getParamPrintId(),vm.getParamTitle()));
		return vm;
	}
	
	@RequestMapping(value="/advis/code/tree/category/list",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel listCategoryTreeAction(@RequestBody CategoryViewModel vm,Model model){
		CategoryDTO dto=new CategoryDTO();
		dto.setCtg_group(vm.getParamCtgId());
		vm.setCodes(codeSysService.list(SysKeyword.CODE_GROUP_KOR_ORG));
		vm.setList(cateoryService.listCategory(dto));
		return vm;
	}
	
	@RequestMapping(value="/advis/code/tree/event/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listEventTreeAction(@RequestBody EventViewModel vm,Model model){
		EventDto dto=new EventDto();
		dto.setCtg_id(vm.getParamCtgId());
		vm.setResultList(eventService.searchItemList(dto));
		return vm;
	}
	
	@RequestMapping(value="/advis/code/tree/eventItem/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listEventItemAction(@RequestBody EventViewModel vm,Model model){
		EventItemDto dto = new EventItemDto();
		dto.setEvt_id(vm.getParamEvtId());
		vm.setEventItemList(eventService.selectEventItemList(dto));
		return vm;
	}
	
	
	
}
