package com.noaa.base.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.MessageStatus;
import com.noaa.base.file.service.IFileService;
import com.noaa.base.site.service.IPartnerSiteService;
import com.noaa.base.site.viewmodel.PartnerSiteViewModel;

@Controller("partnerSiteController")
public class PartnerSiteController extends BaseController {
	@Autowired
	private IPartnerSiteService partnerSiteService;
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value="/api/partner/site/list",method=RequestMethod.POST)
	public @ResponseBody PartnerSiteViewModel listPartnerSiteAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setList(partnerSiteService.list());
		return vm;
	}
	@RequestMapping(value="/api/partner/site/usnYNList",method=RequestMethod.POST)
	public @ResponseBody PartnerSiteViewModel usnYNListPartnerSiteAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setList(partnerSiteService.list(vm.getDetail().getUse_yn()));
		return vm;
	}
	@RequestMapping(value="/api/partner/site/detail",method=RequestMethod.POST)
	public @ResponseBody PartnerSiteViewModel detailPartnerSiteAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setList(partnerSiteService.detail(vm.getParamSiteUrl()));
		return vm;
	}
	@RequestMapping(value="/api/partner/site/create",method=RequestMethod.POST)
	public String createPartnerSiteAction(PartnerSiteViewModel vm,Model model) throws Exception{
		if(fileService.isExtImgCheck(vm.getUploadFiles()) || vm.getUploadFiles().get(0).isEmpty()){
			vm.setResultCount(partnerSiteService.create(vm.getDetail(),getCommonParams(),vm.getUploadFiles()));
			return "redirect:/system/manage/default#/partnerList";
		}else{
			return "redirect:/system/manage/default#/partnerList/"+MessageStatus.VALID_EXT_IMG.value();
		}
		
		
	}
	
	@RequestMapping(value="/api/partner/site/update",method=RequestMethod.POST)
	public String updatePartnerSiteAction( PartnerSiteViewModel vm,Model model) throws Exception{
		if(fileService.isExtImgCheck(vm.getUploadFiles()) || vm.getUploadFiles().get(0).isEmpty()){
			vm.setResultCount(partnerSiteService.update(vm.getDetail(),getCommonParams(),vm.getUploadFiles()));
			return "redirect:/system/manage/default#/partnerList";
		}else{
			return "redirect:/system/manage/default#/partnerList/"+MessageStatus.VALID_EXT_IMG.value();
		}
	}
	@RequestMapping(value="/api/partner/site/useyn/update",method=RequestMethod.POST)
	public  @ResponseBody PartnerSiteViewModel updatePartnerSiteUseYNAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setResultCount(partnerSiteService.updateUseYN(vm.getDetail()));
		return vm;
	}
	
	
	@RequestMapping(value="/api/partner/site/drop",method=RequestMethod.POST)
	public  @ResponseBody PartnerSiteViewModel dropPartnerSiteAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setResultCount(partnerSiteService.drop(vm.getParamSiteUrl()));
		return vm;
	}
	@RequestMapping(value="/api/partner/site/image/delete",method=RequestMethod.POST)
	public  @ResponseBody PartnerSiteViewModel deletePartnerSiteImageAction(@RequestBody PartnerSiteViewModel vm,Model model) throws Exception{
		vm.setResultCount(partnerSiteService.deleteSiteImage(vm.getParamSiteUrl(),vm.getParamFileGrpId()));
		return vm;
	}
}
