package com.noaa.base.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noaa.base.BaseService;
import com.noaa.base.CommonParams;
import com.noaa.base.MessageStatus;
import com.noaa.base.account.dao.IRoleDao;
import com.noaa.base.global.GlobalService;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.menu.dao.IMenuDao;
import com.noaa.base.menu.dao.MenuDTO;
import com.noaa.base.menu.dao.MenuLogDTO;
import com.noaa.base.menu.dao.MenuRoleDTO;
import com.noaa.base.utils.DateHelper;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl extends BaseService implements IMenuService {
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private GlobalService globalService;
	@Override
	public List<MenuDTO> list(String paramUseYN) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use_yn", paramUseYN);
		List<MenuDTO> menus = menuDao.list(map);
		List<MenuRoleDTO> menuRoles = menuDao.listRole(null);

		for (MenuDTO menuDTO : menus) {
			menuDTO.setRoles(new ArrayList<MenuRoleDTO>());
			for (MenuRoleDTO menuRoleDTO : menuRoles) {
				if (menuDTO.getMenu_id().equals(menuRoleDTO.getMenu_id())) {
					menuDTO.getRoles().add(menuRoleDTO);
				}
			}
		}

		return menus;
	}

	@Override
	public int create(MenuDTO paramDTO) {
		if (paramDTO.getMenu_group() == null || paramDTO.getMenu_group().isEmpty()) {
			paramDTO.setMenu_level(0);
			paramDTO.setMenu_group(paramDTO.getMenu_id());
		}
		if (paramDTO.getUse_yn() == null)
			paramDTO.setUse_yn(SysKeyword.COMMON_PARAM_USE_Y);
		globalService.reload();
		return menuDao.create(paramDTO);
	}

	@Override
	public int create(MenuDTO paramDTO, CommonParams common) {
		if((paramDTO.getMenu_id() == null || paramDTO.getMenu_id().isEmpty())
			|| (paramDTO.getMenu_title() == null || paramDTO.getMenu_title().isEmpty())
			|| (paramDTO.getMenu_uri() == null || paramDTO.getMenu_uri().isEmpty())
			|| (paramDTO.getMenu_type_code() == null || paramDTO.getMenu_type_code().isEmpty())
		  ){
			return MessageStatus.VALID_REQUIRED.value();
		}
		else{
			if (paramDTO.getMenu_group() == null || paramDTO.getMenu_group().isEmpty()) {
				paramDTO.setMenu_level(0);
				paramDTO.setMenu_group(paramDTO.getMenu_id());
			}
			if (paramDTO.getUse_yn() == null)
				paramDTO.setUse_yn(SysKeyword.COMMON_PARAM_USE_Y);
			
			paramDTO.setCreate_user_id(common.getLoginUserID());
			paramDTO.setUpdate_user_id(common.getLoginUserID());
			globalService.reload();
			return menuDao.create(paramDTO);
		}
	}

	@Override
	public int drop(String paramMenuID) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menu_id", paramMenuID);
		globalService.reload();
		return menuDao.delete(paramMap);
	}

	@Override
	public int update(MenuDTO paramDTO) {
		globalService.reload();
		return menuDao.update(paramDTO);
	}

	@Transactional
	@Override
	public int createRole(String paramMenuID, List<MenuRoleDTO> paramRoles) {
		int rs = 0;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menu_id", paramMenuID);
		menuDao.dropRole(paramMap);
		for (MenuRoleDTO menuRoleDTO : paramRoles) {
			rs += menuDao.createRole(menuRoleDTO);
		}
		globalService.reload();
		return rs;
	}

	@Transactional
	@Override
	public int createRole(String paramMenuID, String[] paramRoles) {
		int rs = 0;
		List<MenuRoleDTO> listMenuRole = new ArrayList<MenuRoleDTO>();
		for (int i = 0; i < paramRoles.length; i++) {
			MenuRoleDTO role = new MenuRoleDTO();
			role.setMenu_id(paramMenuID);
			role.setRole_id(paramRoles[i]);
			listMenuRole.add(role);
		}

		if (listMenuRole.size() > 0) {

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("menu_id", paramMenuID);
			menuDao.dropRole(paramMap);
			for (MenuRoleDTO menuRoleDTO : listMenuRole) {
				if(menuRoleDTO.getRole_id()!=null && !menuRoleDTO.getRole_id().isEmpty()){
				rs += menuDao.createRole(menuRoleDTO);
				}
			}
		}
		globalService.reload();
		return rs;
	}
	@Transactional
	@Override
	public int createRole(String paramMenuID, String[] paramRoles,String[] paramWriteRoles) {
		int rs = 0;
		List<MenuRoleDTO> listMenuRole = new ArrayList<MenuRoleDTO>();
		for (int i = 0; i < paramRoles.length; i++) {
			MenuRoleDTO role = new MenuRoleDTO();
			role.setMenu_id(paramMenuID);
			role.setRole_id(paramRoles[i]);
			role.setWrite_yn(paramWriteRoles[i]);
			listMenuRole.add(role);
		}

		if (listMenuRole.size() > 0) {

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("menu_id", paramMenuID);
			menuDao.dropRole(paramMap);
			for (MenuRoleDTO menuRoleDTO : listMenuRole) {
				if(menuRoleDTO.getRole_id()!=null && !menuRoleDTO.getRole_id().isEmpty()){
				rs += menuDao.createRole(menuRoleDTO);
				}
			}
		}
		globalService.reload();
		return rs;
	}
	@Override
	public int createLog(CommonParams common,String paramPrgID,int paramDurSecond) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("menu_id",paramPrgID);
		paramMap.put("sessid", common.getSessID());
		paramMap.put("conn_day",DateHelper.getCurrentYYYYMMDD());
		MenuLogDTO menuLog= menuDao.detailLog(paramMap);
		if(menuLog==null){ 
			menuLog=new MenuLogDTO();
			menuLog.setMenu_id(paramPrgID);
			menuLog.setConn_day(DateHelper.getCurrentYYYYMMDD());
			menuLog.setDevice(common.getDeviceType());
			menuLog.setIsMobleYN(common.getIsMobilYN());
			menuLog.setSessid(common.getSessID());
			menuLog.setDur_time(0);
			menuLog.setHit_count(0);
			menuLog.setIp_addr(common.getRequestIP());
			return menuDao.createLog(menuLog);
		}else{
			menuLog.setDur_time(menuLog.getDur_time()+paramDurSecond);
			return menuDao.updateLog(menuLog);
		}
	}
	@Override
	public int totalCountLog(String paramStDate, String paramEnDate) {
		paramStDate=paramStDate==null?DateHelper.getNextMonthYYYYMMDD(-3).substring(0, 6)+"01":paramStDate.replace("-","");
		paramEnDate=paramEnDate==null?DateHelper.getCurrentYYYYMMDD():paramEnDate.replace("-","");
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("start_day", paramStDate);
		paramMap.put("end_day", paramEnDate);
		return menuDao.totalCountLog(paramMap);
	}
	@Override
	public List<MenuLogDTO> listLog(String paramStDate, String paramEnDate, int pageNo, int pageSize) {
		paramStDate=paramStDate==null?DateHelper.getNextMonthYYYYMMDD(-3).substring(0, 6)+"01":paramStDate.replace("-","");
		paramEnDate=paramEnDate==null?DateHelper.getCurrentYYYYMMDD():paramEnDate.replace("-","");
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("start_day", paramStDate);
		paramMap.put("end_day", paramEnDate);
		addPageIndex(paramMap, pageNo, pageSize);
		return menuDao.listLog(paramMap);
	}
	@Override
	public List<MenuLogDTO> listVisitLog(String paramStDate, String paramEnDate) {
		paramStDate=paramStDate==null?DateHelper.getNextYYYYMMDD(-30):paramStDate.replace("-","");
		paramEnDate=paramEnDate==null?DateHelper.getCurrentYYYYMMDD():paramEnDate.replace("-","");
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("start_day", paramStDate);
		paramMap.put("end_day", paramEnDate);
		return menuDao.listVisitLog(paramMap);
	}

	@Override
	public MenuDTO devicePrecent(String paramStDate, String paramEnDate) {
		paramStDate=paramStDate==null?DateHelper.getNextMonthYYYYMMDD(-3).substring(0, 6)+"01":paramStDate.replace("-","");
		paramEnDate=paramEnDate==null?DateHelper.getCurrentYYYYMMDD():paramEnDate.replace("-","");
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("stDate", paramStDate);
		paramMap.put("enDate", paramEnDate);
		return menuDao.devicePrecent(paramMap);
	}

	@Override
	public int averageUseTime(String paramStDate, String paramEnDate) {
		paramStDate=paramStDate==null?DateHelper.getNextMonthYYYYMMDD(-3).substring(0, 6)+"01":paramStDate.replace("-","");
		paramEnDate=paramEnDate==null?DateHelper.getCurrentYYYYMMDD():paramEnDate.replace("-","");
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("start_day", paramStDate);
		paramMap.put("end_day", paramEnDate);
		return menuDao.averageUseTime(paramMap);
	}

	@Override
	public int countCurrentUser(int paramTime) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		String paramStDate = DateHelper.getYYYYMMDDHH24MI(paramTime*(-1));
		paramMap.put("start_day", paramStDate);
		return menuDao.countCurrentUser(paramMap);
	}

	@Override
	public int countTotalUser() {
		return menuDao.countTotalUser();
	}

}
