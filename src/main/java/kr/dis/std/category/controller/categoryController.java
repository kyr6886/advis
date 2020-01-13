package kr.dis.std.category.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.Authorize;
import com.noaa.base.BaseController;

import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.service.ICateoryService;
import kr.dis.std.category.viewmodel.CategoryViewModel;

@Controller("categoryController")
public class categoryController extends BaseController {
	@Autowired
	private ICateoryService service;
	
	
	@RequestMapping(value="/api/std/category/discodeCreate",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel discodeCreateAction(@RequestBody CategoryViewModel vm,Model model){
		vm.setResultCount(service.insertCategory(vm.getParam(),getCommonParams().getLoginUserID()));
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/api/std/manageDisCodelist",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel discodeListAction(@RequestBody CategoryViewModel vm,Model model)throws Exception{
	    vm.setList(service.listCategory(vm.getParamCtgId(),vm.getParamDepth(),vm.getParamPrintId()));
		return vm;
	}	

	@RequestMapping(value="/api/std/category/discodeUpdate",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel discodeUpdateAction(@RequestBody CategoryViewModel vm,Model model){
		vm.setResultCount(service.updateCategory(vm.getParam(),getCommonParams().getLoginUserID()));
		return vm;
	}
	
	@RequestMapping(value="/api/std/category/discodeDelete",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel discodeDeleteAction(@RequestBody CategoryViewModel vm,Model model){
		vm.setResultCount(service.deleteCategory(vm.getParam()));
		return vm;
	}
	
	@RequestMapping(value="/api/std/tree/category/list",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel listCategoryTreeAction(@RequestBody CategoryViewModel vm,Model model){
		CategoryDTO dto=new CategoryDTO();
		dto.setCtg_group(vm.getParamCtgId());
		vm.setList(service.listCategory(dto));
		return vm;
	}
	
	@RequestMapping(value="/api/std/ocr/file/list",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel listTbOcrFileAction(@RequestBody CategoryViewModel vm){
		vm.setOcrList(service.listTbOcrFile(vm.getPageNo(), vm.getPageSize()));
		vm.setTotalCount(service.countTbOcrFile(vm.getParamCtgId(), vm.getParamEvtId()));
		return vm;
	}
	
	@RequestMapping(value="/api/std/ocr/file/create",method=RequestMethod.POST)
	public String createTbOcrFileAction(CategoryViewModel vm){
		return service.createTbOcrImgFile(vm.getParamCtgId(), vm.getParamEvtId(),
				vm.getParamTitle(), vm.getUploadFiles().get(0)) > 0 ?
						"redirect:/system/manage/default#/dis/manage/ocr" : "";
	}
	
	@RequestMapping(value="/api/std/ocr/file/checkValidation",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel checkValidationTbOcrFileAction(@RequestBody CategoryViewModel vm){
		vm.setResultCount(service.countTbOcrFile(vm.getParamCtgId(), vm.getParamEvtId()));
		return vm;
	}
	@RequestMapping(value="/api/std/ocr/read",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel readOcrAction(@RequestBody CategoryViewModel vm){
		vm.setListSbjRptOct(service.detailOcrFile(vm.getParamCtgId(), vm.getParamEvtId()));
		return vm;
	}
	
	@RequestMapping(value="/api/std/ocr/file/delete",method=RequestMethod.POST)
	public @ResponseBody CategoryViewModel deleteTbOcrFileAction(@RequestBody CategoryViewModel vm){
		vm.setResultCount(service.deleteTbOcrImgFile(vm.getParamCtgId(), vm.getParamEvtId()));
		return vm;
	}
}