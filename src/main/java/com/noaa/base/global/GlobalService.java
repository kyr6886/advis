package com.noaa.base.global;

import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.menu.dao.MenuDTO;
import com.noaa.base.menu.service.IMenuService;

/**
 * @author noaapc
 *
 */
@Service("globalService")
public class GlobalService {
	private static final Logger logger = LoggerFactory.getLogger(GlobalService.class);

	@Autowired
	private IMenuService menuService;
	private HashMap<String, MenuDTO> mapMenu = null;
	private List<MenuDTO> listMenu=null;
	public GlobalService instance() {

		if (listMenu == null) {
			reload();

		}

		return this;
	}

	public HashMap<String, MenuDTO> getMapMenu() {
		return mapMenu;
	}
	
	public List<MenuDTO> getListMenu(){
		return listMenu;
	}
	
	public void reload(){
		mapMenu = new HashMap<String, MenuDTO>();
		logger.info("GlobalService");
		List<MenuDTO> menus =menuService.list(SysKeyword.COMMON_PARAM_USE_Y);
		listMenu=menus;
		for (MenuDTO menuDTO : menus) {
			
			mapMenu.put(menuDTO.getMenu_id(), menuDTO);
		
		}
	}
	
	/**
	 * 메뉴에 권한이 있는지 접근권한이 있는지 여부
	 * @param prgID
	 * @param userRole
	 * @return
	 */
	public boolean hasRole(String prgID, String userRole) {

		if(listMenu==null) reload();
		
		boolean rs = false;
		if(mapMenu.get(prgID)==null) rs=true;
		else{
		String menuRole = mapMenu.get(prgID).getMenu_role();
		if(menuRole!=null ){
			String[] menuRoles = menuRole.split(",");
			for (String strMenuRole : menuRoles) {
				if (userRole.indexOf(strMenuRole) > -1) {
					rs = true;
					break;
				}
			}
		}
		}
		return rs;
	}

	/**
	 * 해당메뉴에 쓰기 권한이 있는지 여부
	 * @param prgID
	 * @param userRole
	 * @return
	 */
	public boolean hasWriteRole(String prgID, String userRole){
		boolean isWrite=false;
		String writeYn = mapMenu.get(prgID).getWrite_yn();
		String[] writeYns=writeYn.split(",");
		// 메뉴에 접근권한이 있을경우 쓰기 권한여부 판단
			if(hasRole(prgID,userRole)){
				String menuRole = mapMenu.get(prgID).getMenu_role();
				String[] userRoles = userRole.split(",");
				String[] menuRoles = menuRole.split(",");
				for(int i=0;i<menuRoles.length;i++){
					for(int k=0;k<userRoles.length;k++){
						if(menuRoles[i].equals(userRoles[k])){
							isWrite=writeYns[k].equals("Y");
						}
					}
				}
				
			}
		return isWrite;
	}
	
	public boolean hasFileRole(String prgID, String userRole){
		boolean isEnableFileDownload=false;
		if(hasRole(prgID,userRole)){
			isEnableFileDownload=userRole.indexOf(SysKeyword.USER_ROLE_FILEDOWNLOAD)>-1;
		}
		return isEnableFileDownload;
	}
}
