package com.noaa.base;

import java.security.PrivateKey;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.account.dao.UserDTO;
import com.noaa.base.account.service.IAccountService;
import com.noaa.base.global.RsaKeyGen;
import com.noaa.base.global.RsaKeyModel;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.utils.CookieHelper;

import egovframework.com.cmm.LoginVO;
import egovframework.let.uat.uia.service.EgovLoginService;


@Controller("baseAccountController")
public class BaseAccountController extends BaseController {
	@Autowired
	private IAccountService accountService;
	
	@Resource(name = "loginService")
    private EgovLoginService loginService;
	
	
	@RequestMapping(value="/account/login",method=RequestMethod.GET)
	public String login(BaseAccountViewModel vm,Model model,HttpServletRequest request){
		RsaKeyModel keyModel=RsaKeyGen.getInstance().createRsaKeys();
		request.getSession().setAttribute(BaseSysKeyword.PRIVATE_KEY, keyModel.privateKey);
		vm.setKeyExponent(keyModel.getKeyExponent());
		vm.setKeyModule(keyModel.getKeyModule());
		model.addAttribute("vm",vm);
		return "/base/admin/login";
	}
	
	@RequestMapping(value="/account/login",method=RequestMethod.POST)
	 public String logInAction(BaseAccountViewModel vm,Model model,HttpServletResponse response,HttpServletRequest request)throws Exception{
		  PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute(BaseSysKeyword.PRIVATE_KEY);
		  request.getSession().removeAttribute(BaseSysKeyword.PRIVATE_KEY);
	      String userID = decryptRsa(privateKey, vm.getLoginInfo().getId());
          String userPwd = decryptRsa(privateKey, vm.getLoginInfo().getPassword());
          
          RsaKeyModel keyModel=RsaKeyGen.getInstance().createRsaKeys();
			request.getSession().setAttribute(BaseSysKeyword.PRIVATE_KEY, keyModel.privateKey);
			vm.setKeyExponent(keyModel.getKeyExponent());
			vm.setKeyModule(keyModel.getKeyModule());
			
          vm.getLoginInfo().setId(userID);
          vm.getLoginInfo().setPassword(userPwd);
		// [egov]
		vm.getLoginInfo().setUserSe("GNR");
		
		UserDTO userEntity = accountService.detail(vm.getLoginInfo().getId());	
		if(userEntity != null){
			if(userEntity.getUser_status_code().equals(SysKeyword.CODE_GROUP_ACC_STATUS_LOCK) || userEntity.getUser_fail_count()>SysKeyword.CODE_GROUP_ACC_STATUS_LOCK_CNT){
				vm.setResultMessage(MessageStatus.AUTH_LOCK.getReasonPhrase());
				
				model.addAttribute("vm", vm);
				return "/base/admin/login";
			}
		}
		
        LoginVO resultVO = loginService.actionLogin(vm.getLoginInfo());
        if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") ) {
        	//UserDTO detail=accountService.detail(vm.getDetailUser().getUser_id(), vm.getDetailUser().getUser_pwd());
        	accountService.updateReFailCnt(userEntity);
        	UserDTO detail=accountService.detail(resultVO.getId(), resultVO.getPassword());
			if(detail!=null){
				request.getSession().setAttribute(SysKeyword.SESSION_USER_ID, detail.getUser_id());
				request.getSession().setAttribute(SysKeyword.SESSION_USER_EMAIL, detail.getUser_email());
				request.getSession().setAttribute(SysKeyword.SESSION_USER_NAME, detail.getUser_name());
				request.getSession().setAttribute(SysKeyword.SESSION_USER_ROLE, detail.getUser_roles());
				request.getSession().setAttribute(SysKeyword.SESSION_ACC_TYPE, detail.getUser_type_code());
				request.getSession().setAttribute(SysKeyword.SESSION_ACC_TITLE, detail.getUser_type_title());
				Cookie cookie=null;
				if(vm.getRememberMeYn()!=null && vm.getRememberMeYn().equals("Y")){
					cookie=new Cookie(SysKeyword.COOKIE_NAME_KEEP,detail.getUser_id());
					cookie.setMaxAge(60*60*24*365);
					cookie.setPath("/");
					response.addCookie(cookie);
				}else{
					CookieHelper cookieHelper=new CookieHelper(request.getCookies());
					cookieHelper.clear(response, SysKeyword.COOKIE_NAME_KEEP);
				}
				
				if(vm.getReturnURI()!=null){
					return "redirect:"+vm.getReturnURI();
				}else{
					return "redirect:/system/manage/default";
				}
			}
        }else{
        	int rs = accountService.updateFailCnt(userEntity);
        	if(rs>0){
	        	UserDTO userCh = accountService.detail(vm.getLoginInfo().getId());
	        	if(userCh.getUser_fail_count()>=SysKeyword.CODE_GROUP_ACC_STATUS_LOCK_CNT){
	        		userEntity.setUser_status_code(SysKeyword.CODE_GROUP_ACC_STATUS_LOCK);
	        		userEntity.setUser_fail_count(0);
	        		accountService.updateStatus(userEntity);
	        	}
        	}
        }
        
        vm.setResultMessage(MessageStatus.AUTH_FAIL.getReasonPhrase());
		model.addAttribute("vm", vm);
        if(vm.getLoginView()!=null){
        	return vm.getLoginView();
		}else{
			if(vm.getDefaultView()!=null){
				return vm.getDefaultView();
			}else{
				return "/base/admin/login";
			}
		}
        
        
	 }
	
	@RequestMapping(value="/account/logout",method=RequestMethod.GET)
	public String logout(BaseAccountViewModel vm,HttpServletResponse response,HttpServletRequest request,Model model){
		request.getSession().invalidate();
		CookieHelper cookieHelper=new CookieHelper(request.getCookies());
		cookieHelper.clear(response, BaseSysKeyword.COOKIE_NAME_KEEP);
		return "redirect:"+vm.getReturnURI();
	}
	

	@RequestMapping(value="/api/account/myInfo",method=RequestMethod.POST)
	public @ResponseBody BaseAccountViewModel myInfoAccountAction(@RequestBody BaseAccountViewModel vm,HttpServletResponse response,HttpServletRequest request,Model model){
		if(getCommonParams()!=null && getCommonParams().getLoginUserID()!=null)
		vm.setDetailUser(accountService.detail(getCommonParams().getLoginUserID()));
		return vm;
	}
	
	@RequestMapping(value="/api/account/list",method=RequestMethod.POST)
	public @ResponseBody BaseAccountViewModel listAccountAction(@RequestBody BaseAccountViewModel vm,HttpServletResponse response,HttpServletRequest request,Model model){
		vm.setList(accountService.list(vm.getParamUserType(),vm.getParamUserStatus(),vm.getParamOption(),vm.getParamText(),vm.getParamAdmCode(), vm.getParamStartDate(),vm.getParamEndDate(),vm.getPageNo(),vm.getPageSize()));
		return vm;
	}

	@RequestMapping(value="/account/find",method=RequestMethod.GET)
	public String findAccountView(BaseAccountViewModel vm,Model model){
		return "/base/account/find";
	}
	
	@RequestMapping(value="/api/account/find/id",method=RequestMethod.POST)
	public @ResponseBody BaseAccountViewModel findAccountAction(@RequestBody BaseAccountViewModel vm,Model model){
		vm.setResultMessage(accountService.findUserID(vm.getDetailUser().getUser_name(), vm.getDetailUser().getUser_email()));
		return vm;
	}
	
	@RequestMapping(value="/api/account/find/pwd",method=RequestMethod.POST)
	public @ResponseBody BaseAccountViewModel findPwdAccountAction(@RequestBody BaseAccountViewModel vm,Model model){
		vm.setResultMessage(accountService.findUserPwd(vm.getDetailUser().getUser_id(), vm.getDetailUser().getUser_email()));
		return vm;
	}
	
	@RequestMapping(value="/account/find/id",method=RequestMethod.GET)
	public String idFindAccountView(BaseAccountViewModel vm,Model model){
		return "/base/account/findid";
	}
	
	@RequestMapping(value="/account/find/pwd",method=RequestMethod.GET)
	public String pwdFindAccountView(BaseAccountViewModel vm,Model model){
		return "/base/account/findpwd";
	}
	
	  private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
	        System.out.println("will decrypt : " + securedValue);
	        Cipher cipher = Cipher.getInstance("RSA");
	        byte[] encryptedBytes = hexToByteArray(securedValue);
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
	        return decryptedValue;
	    }

	    /**
	     * 16진 문자열을 byte 배열로 변환한다.
	     */
	    public static byte[] hexToByteArray(String hex) {
	        if (hex == null || hex.length() % 2 != 0) {
	            return new byte[]{};
	        }

	        byte[] bytes = new byte[hex.length() / 2];
	        for (int i = 0; i < hex.length(); i += 2) {
	            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
	            bytes[(int) Math.floor(i / 2)] = value;
	        }
	        return bytes;
	    }
}
