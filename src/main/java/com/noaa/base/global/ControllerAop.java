package com.noaa.base.global;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.util.Calendar;
import com.noaa.base.Authorize;
import com.noaa.base.AuthorizeException;
import com.noaa.base.BaseViewModel;
import com.noaa.base.CommonParams;
import com.noaa.base.MessageStatus;
import com.noaa.base.ProgramID;
import com.noaa.base.account.dao.UserDTO;
import com.noaa.base.account.service.IAccountService;
import com.noaa.base.menu.dao.MenuDTO;
import com.noaa.base.utils.CookieHelper;
import com.noaa.nema.viewer.kma.service.IKmaService;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;

import kr.dis.std.advis.service.IStatisticsService;
@Aspect
public class ControllerAop {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAop.class);
	@Autowired
	private GlobalService globalService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IStatisticsService statisticsService;
	@Autowired
	private IKmaService kmaService;

	@Before("execution(* com.noaa.base.admin..*Controller.*Action(..)) "
			+ "|| execution(* com.noaa.base.admin..*Controller.*View(..)) "
			+ "|| execution(* kr.dis.std..*Controller.*Action(..)) "
			+ "|| execution(* kr.dis.std..*Controller.*View(..)) ")
	
	public void preAction(JoinPoint joinPoint) throws Throwable {

		HttpServletRequest request = getRequest();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		boolean isAdmin=signature.toString().indexOf("com.noaa.base")>-1;
		ProgramID progrmID = method.getAnnotation(ProgramID.class);
		String prgID = progrmID == null ? "" : progrmID.id();
		Authorize authFilter = method.getAnnotation(Authorize.class);
		CookieHelper cookie = new CookieHelper(getCookie());
		HttpSession session = getSession();
		if (authFilter != null) {
			if (cookie.hasName(SysKeyword.COOKIE_NAME_KEEP)) {

				if (session.getAttribute(SysKeyword.SESSION_USER_ID) != null) {
					
					if (!globalService.hasRole(prgID, session.getAttribute(SysKeyword.SESSION_USER_ROLE).toString())) {
						throw new Exception(MessageKeyword.ERROR_MSG_AUTH_DENY);
					}
				} else {
					String userID = cookie.getValue(SysKeyword.COOKIE_NAME_USERID);
					if(userID==null){
						//throw new AuthorizeException(MessageStatus.AUTH_REQUIRED.value(),false,true,isAdmin?"account/login":"user/login");
						throw new AuthorizeException(MessageStatus.AUTH_REQUIRED.value(),false,true,isAdmin?"account/login":authFilter.url());
					}
					UserDTO userEntity = accountService.detail(userID);					
										
					setSession(userEntity);

					if (!globalService.hasRole(prgID, session.getAttribute(SysKeyword.SESSION_USER_ROLE).toString())) {
						throw new AuthorizeException(MessageStatus.AUTH_DENY.value(),true,false,"/base/error");
					}
				}
			} else {
				if (session.getAttribute(SysKeyword.SESSION_USER_ID) != null) {
					if (!globalService.hasRole(prgID, session.getAttribute(SysKeyword.SESSION_USER_ROLE).toString())) {
						throw new AuthorizeException(MessageStatus.AUTH_DENY.value(),true,false,"/base/error");
					}
				} else {
					//throw new AuthorizeException(MessageStatus.AUTH_REQUIRED.value(),false,true,isAdmin?"account/login":"user/login");
					throw new AuthorizeException(MessageStatus.AUTH_REQUIRED.value(),false,true,isAdmin?"account/login":authFilter.url());
				}
			}
		}
		//setCommonParams(joinPoint);
		prePushViewModel(joinPoint,prgID,authFilter);
	}

	@After("execution(* com.noaa.base.admin..*Controller.*Action(..)) "
			+ "|| execution(* com.noaa.base.admin..*Controller.*View(..)) "
			+ "|| execution(* kr.dis.std..*Controller.*Action(..)) "
			+ "|| execution(* kr.dis.std..*Controller.*View(..)) ")
	public void aftAction(JoinPoint joinPoint) throws Throwable {
		logger.info("test aft aop");
		aftPushViewModel(joinPoint);
	}

	private CommonParams getCommonParams(JoinPoint joinPoint) {
		CommonParams p = null;
		try {
			HttpServletRequest request = getRequest();
		
			HttpSession session = getSession();
			Object sessionUserID = session.getAttribute(SysKeyword.SESSION_USER_ID);
			Object sessionUserName = session.getAttribute(SysKeyword.SESSION_USER_NAME);
			Object sessionUserRole=session.getAttribute(SysKeyword.SESSION_USER_ROLE);
			Object sessionAccType=session.getAttribute(SysKeyword.SESSION_ACC_TYPE);
			Object sessionAccTitle=session.getAttribute(SysKeyword.SESSION_ACC_TITLE);
			String userID =sessionUserID == null ?  null: sessionUserID.toString();
			String userName=sessionUserName == null ? null : sessionUserName.toString();
			String userRole= sessionUserRole==null?null:sessionUserRole.toString();
			String accType= sessionAccType==null?null:sessionAccType.toString();
			String accTitle= sessionAccTitle==null?null:sessionAccTitle.toString();
			p = new CommonParams();

			Method targetMethod = joinPoint.getTarget().getClass().getMethod("setCommonParams", p.getClass());
			p.setSessID(session.getId());
			p.setDeviceType(request.getHeader("User-Agent"));
			if(request.getHeader("User-Agent").indexOf("Android") > -1 || request.getHeader("User-Agent").indexOf("iPhone") > -1 || request.getHeader("User-Agent").indexOf("iPad") > -1) {
				p.setIsMobilYN("Y");      
			}else{
				p.setIsMobilYN("N");
			}
			p.setRequestIP(request.getRemoteHost());
			p.setRequestDate(new Date());
			p.setLoginUserName(userName);
			p.setLoginUserID(userID);
			p.setUserRole(userRole);
			p.setUserRole(userRole);
			p.setAccType(accType);
			p.setAccTitle(accTitle);
			targetMethod.invoke(joinPoint.getTarget(), p);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
		return p;
	}


	private void prePushViewModel(JoinPoint joinPoint,String prgID,Authorize authFilter) {
		try {
			Object[] signatureArgs = joinPoint.getArgs();
			logger.info("pre");
			for (Object signatureArg : signatureArgs) {
				if (signatureArg instanceof BaseViewModel) {
					HttpServletRequest request = getRequest();
					BaseViewModel vm = (BaseViewModel) signatureArg;
					vm.setRequestIP(request.getRemoteAddr());
						
					long time = System.currentTimeMillis();
					vm.setRequestTime(new Date(time));
					
					if (request.getContentType() == null) {
						vm.setMenus(globalService.instance().getListMenu());
						if(prgID!=null){
							vm.setMenuDetail(globalService.instance().getMapMenu().get(prgID));
						}
					}
					if(prgID!=null && authFilter!=null){
						HttpSession session = getSession();
						if (globalService.hasWriteRole(prgID, session.getAttribute(SysKeyword.SESSION_USER_ROLE).toString())) {
							vm.setEnableWriteYN(true);
						}
					}

				}

			}
			
			
			
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	private void aftPushViewModel(JoinPoint joinPoint) {
		try {
			HttpServletRequest request = getRequest();
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			ProgramID progrmID = method.getAnnotation(ProgramID.class);
			logger.info("aft");
			String prgID = progrmID == null ? "" : progrmID.id();
			Object[] signatureArgs = joinPoint.getArgs();
		//	Method targetMethod = joinPoint.getTarget().getClass().getMethod("getCommonParams",null);
			HttpSession session = getSession();
			Object sessionUserID = session.getAttribute(SysKeyword.SESSION_USER_ID);
			Object sessionUserName = session.getAttribute(SysKeyword.SESSION_USER_NAME);
			Object sessionUserRole=session.getAttribute(SysKeyword.SESSION_USER_ROLE);
			Object sessionAccType=session.getAttribute(SysKeyword.SESSION_ACC_TYPE);
			Object sessionAccTitle=session.getAttribute(SysKeyword.SESSION_ACC_TITLE);
			
			String userID =sessionUserID == null ?  null: sessionUserID.toString();
			String userName=sessionUserName == null ? null : sessionUserName.toString();
			String userRole= sessionUserRole==null?null:sessionUserRole.toString();
			String accType= sessionAccType==null?null:sessionAccType.toString();
			String accTitle= sessionAccTitle==null?null:sessionAccTitle.toString();
			
			CommonParams comm = new CommonParams();
			comm.setSessID(session.getId());
			comm.setDeviceType(request.getHeader("User-Agent"));
 			if(request.getHeader("User-Agent").indexOf("Android") > -1 || request.getHeader("User-Agent").indexOf("iPhone") > -1 || request.getHeader("User-Agent").indexOf("iPad") > -1) {
				comm.setIsMobilYN("Y");      
			}else{
				comm.setIsMobilYN("N");
			}
			comm.setRequestIP(request.getRemoteHost());
			comm.setRequestDate(new Date());
			comm.setLoginUserName(userName);
			comm.setLoginUserID(userID);
			comm.setUserRole(userRole);
			comm.setUserRole(userRole);
 			comm.setAccType(accType);
 			comm.setAccTitle(accTitle);
			 
			
			
 			for (Object signatureArg : signatureArgs) {
 				if (signatureArg instanceof BaseViewModel) {
					BaseViewModel vm = (BaseViewModel) signatureArg;
					vm.setPg(prgID);
					 if(comm!=null){
						 comm.setSessID(null);
						 vm.setCommonParams(comm);
					 }
					if(MessageStatus.valueOf(vm.getResultCount())!=null)
					vm.setResultMessage(MessageStatus.valueOf(vm.getResultCount()).getReasonPhrase());
				
				
				}
				
				if (signatureArg instanceof Model) {
				
					Model model = (Model) signatureArg;
					boolean isRedirect=model.containsAttribute("redirect");
					logger.info(request.getContentType());
					if (method.getName().indexOf("View") > -1) {
 						if(comm!=null && comm.getLoginUserID()!=null){
  							accountService.createLoginLog(comm, request.getSession().getId());
						}
 						model.addAttribute("userID", comm==null?null: comm.getLoginUserID());
 						model.addAttribute("userName",comm==null?null:comm.getLoginUserName());
						model.addAttribute("userRole",comm==null?null:comm.getUserRole());
						model.addAttribute("accType",comm==null?null:comm.getAccType());
						model.addAttribute("common",comm);
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
						Calendar cal=Calendar.getInstance();
						String _now=dateFormat.format(cal.getTime());
						
						// 하드코딩 특정날자
						KmaInformDto dto = kmaService.detailKmaStatusTyphoonReport(null, null, "201910031000");
						//KmaInformDto dto = kmaService.detailKmaStatusTyphoonReport(_now,_now);
						
						if(dto != null){
							model.addAttribute("monthDisaster",statisticsService.maxDisasterInfoByTyphoon(dto));
						}
						else{
							model.addAttribute("monthDisaster",statisticsService.maxDisasterInfoByMonth(_now.substring(4, 6)));
						}
						
						model.addAttribute("toMonth",_now.substring(4, 6));
					}else if(method.getName().indexOf("Action") > -1){
						if(isRedirect){
							model.asMap().clear();
						}
					}
					if (progrmID != null && prgID!=null && method.getName().indexOf("View") > -1) {
						HashMap<String, MenuDTO> menuDto=globalService.instance().getMapMenu();
						if(!isRedirect&&menuDto!=null && menuDto.get(prgID)!=null){
							model.addAttribute("title",globalService.instance().getMapMenu().get(prgID).getMenu_title());
							model.addAttribute("menuID",globalService.instance().getMapMenu().get(prgID).getMenu_id());
							model.addAttribute("menuGRP",globalService.instance().getMapMenu().get(prgID).getMenu_group());
						
						}
					}
				}
				if(signatureArg instanceof ModelAndView){
				
					ModelAndView model = (ModelAndView) signatureArg;
					logger.info(request.getContentType());
					if (method.getName().indexOf("View") > -1) {
						
						String refer=request.getHeader("refere");
						String requestUri = request.getRequestURI();
						
						
						
						model.addObject("userID", comm.getLoginUserID());
						model.addObject("userName",comm.getLoginUserName());
						model.addObject("userRole",comm.getUserRole());
						model.addObject("accType",comm.getAccType());
						model.addObject("common",comm);
					}
					if (progrmID != null) {
						model.addObject("title",globalService.instance().getMapMenu().get(prgID).getMenu_title());
						model.addObject("menuID",globalService.instance().getMapMenu().get(prgID).getMenu_id());
						model.addObject("menuGRP",globalService.instance().getMapMenu().get(prgID).getMenu_group());
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setSession(UserDTO userEntity) {

		HttpSession session = getSession();
		session.setAttribute(SysKeyword.SESSION_USER_ID, userEntity.getUser_id());
		session.setAttribute(SysKeyword.SESSION_USER_NAME, userEntity.getUser_name());
		session.setAttribute(SysKeyword.SESSION_USER_EMAIL, userEntity.getUser_email());
		session.setAttribute(SysKeyword.SESSION_USER_ROLE, userEntity.getUser_roles());
		session.setAttribute(SysKeyword.SESSION_ACC_TYPE, userEntity.getUser_type_code());
		session.setAttribute(SysKeyword.SESSION_ACC_TITLE, userEntity.getUser_type_title());
	    
	}

	private HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}

	private Cookie[] getCookie() {
		HttpServletRequest request = getRequest();
		return request.getCookies();
	}

	private HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		logger.info("contentType:" + request.getContentType());
		return request;
	}

	/*private String createMenu() {
		HttpServletRequest request = getRequest();
		List<MenuDTO> menus = globalService.instance().getListMenu();
		StringBuilder menuStr = new StringBuilder();
		for (MenuDTO menuDTO : menus) {

			if (menuDTO.getMENU_LEVEL() == 0) {
				List<MenuDTO> subMenus = getSubMenus(menuDTO);
				if (subMenus.size() > 0) {
					menuStr.append("<li class='has_sub'>");
					menuStr.append("<a href='#' class='waves-effect waves-light'><i class='" + menuDTO.getMENU_CLASS()
							+ "'></i>");
					menuStr.append("<span>");
					menuStr.append(menuDTO.getMENU_TITLE());
					menuStr.append("</span> </a>");
					menuStr.append("<ul class='list-unstyled'>");
					for (MenuDTO subItem : subMenus) {
						menuStr.append("<li>");
						menuStr.append("<a href='" + request.getContextPath() + subItem.getMENU_URI() + "'>");
						menuStr.append(subItem.getMENU_TITLE());
						menuStr.append("</a>");
						menuStr.append("</li>");
					}
					menuStr.append("</ul></li>");

				} else {
					menuStr.append("<li class='has_sub'>");
					menuStr.append("<a href='#' class='waves-effect waves-light'><i class='" + menuDTO.getMENU_CLASS()
							+ "'></i>");
					menuStr.append(menuDTO.getMENU_TITLE());
					menuStr.append("</span> </a>");
					menuStr.append("</li>");
				}
			}

		}
		return menuStr.toString();
	}*/

	/*private List<MenuDTO> getSubMenus(MenuDTO targetMenu) {
		List<MenuDTO> menus = globalService.instance().getListMenu();
		List<MenuDTO> rs = new ArrayList<MenuDTO>();
		for (MenuDTO menuDTO : menus) {
			if (targetMenu.getMENU_GROUP().equals(menuDTO.getMENU_GROUP())) {
				if (menuDTO.getMENU_LEVEL() > 0 && menuDTO.getMENU_TYPE().equals(SysKeyword.CODE_MENU_LEFT)) {
					rs.add(menuDTO);
				}
			}
		}
		return rs;
	}*/

}
