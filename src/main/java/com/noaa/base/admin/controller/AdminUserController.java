package com.noaa.base.admin.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.noaa.base.BaseController;
import com.noaa.base.BaseSysKeyword;
import com.noaa.base.account.service.IAccountService;
import com.noaa.base.account.service.ILoginLogService;
import com.noaa.base.admin.viewmodel.AdminAccountViewModel;
import com.noaa.base.bbs.dao.BBSUserDTO;
import com.noaa.base.code.service.ICodeSysService;
import com.noaa.base.file.service.IFileService;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.menu.service.IMenuService;
import com.noaa.base.meta.service.IMetaAdmService;
import com.noaa.base.utils.DateHelper;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.let.uss.umt.service.EgovMberManageService;
import egovframework.let.uss.umt.service.UserDefaultVO;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller("adminUserController")
public class AdminUserController extends BaseController {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ICodeSysService codeService;
	
	@Autowired
	private ILoginLogService loginLogService;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IFileService fileService;
	@Autowired
	private IMetaAdmService metaAdmService;
	/** mberManageService */
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	

	
	
	
	@RequestMapping(value="/api/admin/user/manage",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel manageUserAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
		vm.setListUserTypeCodes(codeService.list(BaseSysKeyword.CODE_GROUP_ACC_TYPE));
		vm.setListUserStatusCodes(codeService.list(BaseSysKeyword.CODE_GROUP_ACC_STATUS));
		vm.setListRoles(accountService.listRoles());
		vm.setList(accountService.list(null,null,null,null,null	,null,null,vm.getPageNo(),vm.getPageSize()));
		vm.setTotalCount(accountService.totalCount(null,null,null,null,null,null,null));
		vm.setListAdm(metaAdmService.listSido());
		return vm;
	}
	
	
	
	@RequestMapping(value="/api/account/create",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel createAccountAction(@RequestBody AdminAccountViewModel vm,Model model) throws Exception{
		//비밀번호 정규화
		String Passwrod_PATTERN = "^(?=.*[a-zA-Z]+)(?=.*[!@#$%^*+=-]|.*[0-9]+).{8,16}$";
		Pattern pattern = Pattern.compile(Passwrod_PATTERN);
		Matcher matcher = pattern.matcher(vm.getDetail().getUser_pwd());
		if(!matcher.matches()){
			vm.setResultCount(0);
		}else{
			vm.getDetail().setCreate_user_id(getCommonParams().getLoginUserID());
			vm.setResultCount(mberManageService.insertMber(vm.getDetail()));
		}
		return vm;
	}
	
	
	
	@RequestMapping(value="/api/admin/user/init/check",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel checkInitUserAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
		vm.setResultCount(accountService.countUserRoles(SysKeyword.CODE_ROLE_SYS));
		return vm;
	}
	
	@RequestMapping(value="/api/admin/user/type/update",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel updateTypeUserAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
	    vm.setResultCount(accountService.update(vm.getParamUserID(),vm.getParamUserType(),vm.getParamUserStatus()));
		return vm;
	}
	
	
	@RequestMapping(value="/api/account/delete",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel deleteAccountAction(@RequestBody AdminAccountViewModel vm,Model model){
		vm.setResultCount(accountService.delete(vm.getParamUserID(),getCommonParams()));
		return vm;
	}
	
	@RequestMapping(value="/api/account/detail",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel detailAccountAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
	    vm.setDetail(accountService.detail(vm.getParamUserID()));
		return vm;
	}

	@RequestMapping(value="/api/account/update",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel updateAccountAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
	    vm.setResultCount(accountService.update(vm.getDetail(),getCommonParams()));
		return vm;
	}
	
	@RequestMapping(value="/api/account/admin/create",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel createAdminAccountAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{	
	    vm.setResultCount(accountService.createAdmin(vm.getDetail()) );
		return vm;
	}
	@RequestMapping(value="/api/account/log",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel curTimeAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{
	    vm.setLoginDetail(loginLogService.curTime());
		return vm;
	}
	@RequestMapping(value="/api/account/log/list",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel listLoginLogAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{
	    vm.setResultCount(loginLogService.totalCount(vm.getParamStartDate(),vm.getParamEndDate(),vm.getId(),vm.getParamUserName()));
	    vm.setParamStartDate( vm.getParamStartDate() ==null ? DateHelper.getFirstDayYYYYMMDD():vm.getParamStartDate());
	    vm.setParamEndDate( vm.getParamEndDate() ==null ? DateHelper.getCurrentYYYYMMDD():vm.getParamEndDate());
	    vm.setListLoginLog(loginLogService.list(vm.getParamStartDate(),vm.getParamEndDate(),vm.getId(),vm.getParamUserName(),vm.getPageNo(),vm.getPageSize()));
		return vm;
	}
	@RequestMapping(value="/api/account/log/detail",method=RequestMethod.POST)
	public @ResponseBody AdminAccountViewModel DetailLoginLogAction(@RequestBody AdminAccountViewModel vm,Model model)throws Exception{
		vm.setResultCount(loginLogService.detailTotalCount(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamUserID(),vm.getParamUserName()));
		vm.setListLoginLog(loginLogService.detail(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamUserID(),vm.getPageNo(),vm.getPageSize()));
		vm.setChartLoginLog(loginLogService.detail(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamUserID()));
		vm.setConn_cnt_tot(loginLogService.detailConnTotalCount(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamUserID()));
		vm.setAvg_use_time(menuService.averageUseTime(vm.getParamStartDate(),vm.getParamEndDate()));
		return vm;
	}
}
