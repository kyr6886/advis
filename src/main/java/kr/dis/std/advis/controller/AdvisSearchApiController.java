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

import com.noaa.base.Authorize;
import com.noaa.base.BaseController;

import kr.dis.std.advis.dao.AdvisSearchDto;
import kr.dis.std.advis.service.IAdvisSearchService;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.service.ICateoryService;
/*import kr.dis.std.advisMain.dao.AdvisSearchDto;
import kr.dis.std.advisMain.service.IAdvisSearchService;*/
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventImgDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.web.viewmodel.AdvisMainViewModel;
import kr.dis.std.web.viewmodel.AdvisSearchViewModel;
import kr.dis.std.web.viewmodel.EventViewModel;

@Controller("searchApiController")
public class AdvisSearchApiController extends BaseController{
	
	@Autowired
	private IAdvisSearchService searchService;
	
	@Autowired
	private ICateoryService cateoryService;
	
	@Autowired
	private IEventService eventService;

	//@Authorize(url="advis/login/login")	
	@RequestMapping(value="/advis/manage/search",method=RequestMethod.GET)
	public String mainView(AdvisMainViewModel vm,Model model,HttpServletResponse response,HttpServletRequest request)throws Exception{
		return "/advis/main/search";
	}	
	
	
	//@Authorize
	@RequestMapping(value="/api/std/advisSearch/searchResult",method=RequestMethod.POST)
	public @ResponseBody AdvisSearchViewModel SearchEventResultAction(@RequestBody AdvisSearchViewModel vm,Model model){
		AdvisSearchDto event=new AdvisSearchDto();
		event.setEvt_id(vm.getParamEvtId());
		vm.setSetResultSearchItem(searchService.searchResultList(event));
		return vm;
	}	
	
	//@Authorize(url="advis/login/login")	
	@RequestMapping(value="/advis/manage/matrix",method=RequestMethod.GET)
	public String matrixView(AdvisMainViewModel vm,Model model,HttpServletResponse response,HttpServletRequest request)throws Exception{
		return "/advis/main/matrix";
	}	
	
	
	//@Authorize
	@RequestMapping(value="/api/std/eventItem/searchItem",method=RequestMethod.POST)
	public @ResponseBody AdvisSearchViewModel SearchEventItemAction(@RequestBody AdvisSearchViewModel vm,Model model){
		AdvisSearchDto event=new AdvisSearchDto();
		event.setTitle(vm.getParamTitle());
		if (vm.getParamMonth() == null) {
			event.setEvt_date(vm.getParamYear());	
		} else {
			event.setEvt_date(vm.getParamYear() + vm.getParamMonth());
		}
		vm.setSetResultSearchItem(searchService.searchItem(getCommonParams(),event));
		return vm;
	}	
	
	@RequestMapping(value="/advis/search/list/view",method=RequestMethod.POST)
	public String getSearchListView(EventViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/code/search";
	}
	
	@RequestMapping(value="/advis/search/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel  getSearchListAction(@RequestBody EventViewModel vm, Model model)throws Exception{
		CategoryDTO categoryDto=new CategoryDTO();
		categoryDto.setTitle(vm.getParamTitle());
		categoryDto.setDepth(vm.getParamDepth());
		categoryDto.setSession_id(getCommonParams().getSessID());
		categoryDto.setCtg_group(vm.getParamDisType());
		vm.setResultList(cateoryService.listCtgId(categoryDto));
		
		EventImgDto imgDto = new EventImgDto();
		imgDto.setTitle(vm.getParamTitle());
		imgDto.setDepth(vm.getParamDepth());
		vm.setEventImgList(eventService.selectEventImgList(imgDto));
		
		model.addAttribute("vm",vm);
		return vm;
	}
	
	@RequestMapping(value="/advis/search/category/list",method=RequestMethod.GET)
	public String getSearchCategoryView(EventViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/code/categoryList";
	}
	
	
}
