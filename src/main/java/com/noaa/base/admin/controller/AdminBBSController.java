package com.noaa.base.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.Authorize;
import com.noaa.base.BaseController;
import com.noaa.base.account.service.IAccountService;
import com.noaa.base.admin.viewmodel.AdminBoardViewModel;
import com.noaa.base.bbs.dao.BBSMasterDTO;
import com.noaa.base.bbs.service.IBBSService;
import com.noaa.base.file.dao.AttachFileDTO;
import com.noaa.base.file.service.IFileService;
import com.noaa.base.utils.DateHelper;

@Controller("adminBBSController")
public class AdminBBSController extends BaseController {
	@Autowired
	private IBBSService bbsService;
	@Autowired
	private IFileService fileService;
	@Autowired
	private IAccountService accountService;
	
	@Value("${file.bbs.path}")
	private String fileBbsPath;
	
	@Authorize
	@RequestMapping(value="/api/admin/board/file/delete",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel deleteFileAction(@RequestBody AdminBoardViewModel vm,Model model){
		AttachFileDTO attachFileDTO=fileService.detail(Integer.parseInt(vm.getId()));
		fileService.drop(Integer.parseInt(vm.getId()));
		List<String> fileGrps=new ArrayList<String>();
		fileGrps.add(attachFileDTO.getFile_grp_id());
		vm.setListAttachFiles(fileService.list(fileGrps));
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/content/list",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel listAction(@RequestBody AdminBoardViewModel vm,Model model){	
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		if(bbsMasterDto.getEnableReadYN().equals("Y")){
		vm.setTotalCount(bbsService.totalCountBBSContent( 
				vm.getId(),vm.getSearchCategorySeq()
				,vm.getSearchTitle()
				,vm.getSearchContent()
				,vm.getSearchName()
				,vm.getSearchStDate()
				,vm.getSearchEnDate()
				,vm.getSearchSpellKor()
				,vm.getSearchSpellEn()
				));
		vm.setListContent(
				 bbsService.listContent(
				 vm.getId()
				,vm.getSearchCategorySeq()
				,vm.getSearchTitle()
				,vm.getSearchContent()
				,vm.getSearchName()
				,vm.getSearchStDate()
				,vm.getSearchEnDate()
				,vm.getSearchSpellKor()
				,vm.getSearchSpellEn()
				,vm.getPageNo()
				,vm.getPageSize())
				);
		}
		return vm;
	}
	
	/*@RequestMapping(value="/Api/Admin/Board/Content/Delete",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel deleteContentAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setResultCount(bbsService.deleteContent(vm.getId(), vm.getParamSeq(), getCommonParams()));
		vm.setTotalCount(bbsService.totalCountBBSContent( 
				vm.getId(),vm.getSearchCategorySeq()
				,vm.getSearchTitle()
				,vm.getSearchContent()
				,vm.getSearchName()
				,vm.getSearchStDate()
				,vm.getSearchEnDate()
				,vm.getSearchSpellKor()
				,vm.getSearchSpellEn()
				));
		vm.setListContent(
				 bbsService.listContent(
				 vm.getId()
				,vm.getSearchCategorySeq()
				,vm.getSearchTitle()
				,vm.getSearchContent()
				,vm.getSearchName()
				,vm.getSearchStDate()
				,vm.getSearchEnDate()
				,vm.getSearchSpellKor()
				,vm.getSearchSpellEn()
				,vm.getPageNo()
				,vm.getPageSize())
				);
		return vm;
	}*/
	
	@RequestMapping(value="/api/admin/board/detail",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel detailAction(@RequestBody AdminBoardViewModel vm,Model model){
		BBSMasterDTO entity= bbsService.detailMaster(vm.getId());
		vm.setDetailMaster(entity);
		
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/category/list",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel listCategoryAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setListCategory(bbsService.listCategory(vm.getId()));
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/mng",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel mngBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setListRoles(accountService.listRoles());
		vm.setListMaster(bbsService.listMaster());
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/list",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel listBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setListMaster(bbsService.listMaster());
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/create",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel createBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setResultCount(bbsService.createMaster(vm.getDetailMaster(),vm.getParamCategorys()!=null?vm.getParamCategorys().split(","):null));
		vm.setListMaster(bbsService.listMaster());
		return vm;
	}
	@RequestMapping(value="/api/admin/board/update",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel updateBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		vm.setResultCount(bbsService.updateMaster(vm.getDetailMaster(),vm.getParamCategorys()!=null?vm.getParamCategorys().split(","):null,getCommonParams()));
		vm.setListMaster(bbsService.listMaster());
		return vm;
	}
	
	@RequestMapping(value="/admin/board/content/create",method=RequestMethod.POST)
	public String createContentBoardAdminAction(AdminBoardViewModel vm,Model model){
		vm.getDetailContent().setBbs_cont(vm.getSmartEditor());
		bbsService.createContent(vm.getDetailContent(),getCommonParams(),vm.getUploadFiles());
		return "redirect:/system/manage/default#/bbsContentList";
	}
	
	@RequestMapping(value="/admin/board/content/update",method=RequestMethod.POST)
	public String updateContentBoardAdminAction(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		vm.getDetailContent().setBbs_cont(vm.getSmartEditor());
		vm.getDetailContent().setBbs_id(vm.getId());
		vm.getDetailContent().setBbs_seq(vm.getParamSeq());
		bbsService.updateBBSContent(vm.getDetailContent(),getCommonParams(),vm.getUploadFiles());
		return "redirect:/system/manage/default#/bbsContentList";
	}
	
	
	@RequestMapping(value="/api/admin/board/content/detail",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel detailContentBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		
		vm.setDetailContent(bbsService.detailContent(vm.getId(), vm.getParamSeq(),getCommonParams()));
		return vm;
	}
	
	@RequestMapping(value="/api/admin/board/content/delete",method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel deleteContentBoardAction(@RequestBody AdminBoardViewModel vm,Model model){
		
		vm.setResultCount(bbsService.deleteContent(vm.getId(),vm.getParamSeq(),getCommonParams()));
		
		return vm;
	}
	@RequestMapping(value={"/board/content/list/{id}","/board/content/list/{id}/{pageNo}"},method=RequestMethod.GET)
	public String listContentBoardView(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		return bbsMasterDto.getBbs_list_view();
	}
	@RequestMapping(value={"/board/content/create/{id}"},method=RequestMethod.GET)
	public String createContentBoardView(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		return bbsMasterDto.getBbs_write_view();
	}
	@RequestMapping(value={"/board/content/create/{id}"},method=RequestMethod.POST)
	public String idCreateContentBoardAction(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		vm.getDetailContent().setBbs_id(bbsMasterDto.getBbs_id());
		bbsService.createContent(vm.getDetailContent(),getCommonParams(),vm.getUploadFiles());
		
		return bbsMasterDto.getBbs_list_view();
	}
	@RequestMapping(value={"/board/content/update/{id}/{paramSeq}/{pageNo}","/board/content/update/{id}/{paramSeq}"},method=RequestMethod.GET)
	public String updateContentBoardView(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		return bbsMasterDto.getBbs_update_view();
	}
	@RequestMapping(value={"/board/content/update/{id}/{paramSeq}"},method=RequestMethod.POST)
	public String updateContentBoardAction(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		vm.getDetailContent().setBbs_id(vm.getId());
		vm.getDetailContent().setBbs_seq(vm.getParamSeq());
		bbsService.updateBBSContent(vm.getDetailContent(),getCommonParams(),vm.getUploadFiles());
		
		return bbsMasterDto.getBbs_list_view();
	}
	@RequestMapping(value={"/api/admin/board/content/file/update/{id}/{paramSeq}"},method=RequestMethod.POST)
	public @ResponseBody AdminBoardViewModel updateFileContentBoardAction(AdminBoardViewModel vm,Model model){
		bbsService.updateFile(vm.getId(),vm.getParamSeq());
		vm.setDetailContent(bbsService.detailContent(vm.getId(), vm.getParamSeq(),vm.getCommonParams()));
		return vm;
	}
	@RequestMapping(value={"/board/content/drop/{id}/{paramSeq}"},method=RequestMethod.POST)
	public String dropContentBoardAction(AdminBoardViewModel vm,Model model){
		bbsService.deleteContent(vm.getId(), vm.getParamSeq(), getCommonParams());

		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		return bbsMasterDto.getBbs_list_view();
	}
	
	@RequestMapping(value={"/board/content/detail/{id}/{paramSeq}/{pageNo}","/board/content/detail/{id}/{paramSeq}"},method=RequestMethod.GET)
	public String detailContentBoardView(AdminBoardViewModel vm,Model model){
		BBSMasterDTO bbsMasterDto=bbsService.detailMaster(vm.getId(),getCommonParams());
		vm.setDetailMaster(bbsMasterDto);
		model.addAttribute("vm", vm);
		return bbsMasterDto.getBbs_read_view();
	}
	
	
}
