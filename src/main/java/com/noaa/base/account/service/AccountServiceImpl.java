package com.noaa.base.account.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.BaseSysKeyword;
import com.noaa.base.CommonParams;
import com.noaa.base.MessageStatus;
import com.noaa.base.account.dao.ILoginLogDao;
import com.noaa.base.account.dao.IRoleDao;
import com.noaa.base.account.dao.IUserDao;
import com.noaa.base.account.dao.LoginLogDTO;
import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.account.dao.UserDTO;
import com.noaa.base.bbs.dao.BBSUserDTO;
import com.noaa.base.code.dao.CodeGrpDTO;
import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.code.dao.ICodeSysDao;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.menu.dao.IMenuDao;
import com.noaa.base.menu.dao.MenuDTO;
import com.noaa.base.menu.dao.MenuRoleDTO;
import com.noaa.base.utils.AES256Cipher;
import com.noaa.base.utils.Encryption;

import egovframework.let.uss.umt.service.EgovMberManageService;
import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("accountService")
public class AccountServiceImpl extends BaseService implements IAccountService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICodeSysDao codeSysDao;
	@Autowired
	private IMenuDao menuDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private ILoginLogDao loginLogDao;
	@Autowired 
	private JavaMailSender mailSender;
	
	@Resource(name = "mberManageService")
	private EgovMberManageService mberManageService;
	@Override
	public UserDTO detail(String paramUserID) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);
		UserDTO rs = userDao.detail(paramMap);
		try {
			if(rs!=null){
			//	rs.setUser_email(AES256Cipher.AES_Decode(rs.getUser_email(), rs.getUser_key()));
			//	rs.setUser_phone(AES256Cipher.AES_Decode(rs.getUser_phone(), rs.getUser_key()));
				rs.setUser_key("");
				if(rs.getUser_adm_code()!=null && !rs.getUser_adm_code().isEmpty()){
					String sido=rs.getUser_adm_code().substring(0,2);
					String gungu=rs.getUser_adm_code().substring(0,4);
					rs.setUserAdm(new String[]{sido,gungu,rs.getUser_adm_code()});
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			rs = null;
		}
		return rs;
	}
	@Override
	public UserDTO detailByNameEmail(String paramUserName, String paramUserEmail) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_name", paramUserName);
		paramMap.put("user_email", paramUserEmail);
		return userDao.detail(paramMap);
	}
	@Override
	public List<UserDTO> list(String paramUserType, int paramPageNo, int paramRowSize) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_type_code", paramUserType);
		addPageIndex(paramMap, paramPageNo, paramRowSize);
		return userDao.list(paramMap);
	}

	@Override
	public List<UserDTO> list(String paramUserType, String paramUserStatus, int paramPageNo, int paramRowSize) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_type_code", paramUserType);
		addPageIndex(paramMap, paramPageNo, paramRowSize);
		return userDao.list(paramMap);
	}

	@Override
	public List<UserDTO> list(String paramUserType, String paramUserStatus, String paramUserName, int paramPageNo,int paramRowSize) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_type_code", paramUserType);
		paramMap.put("user_name", paramUserName);
		addPageIndex(paramMap, paramPageNo, paramRowSize);

		return userDao.list(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.noaa.base.account.service.IAccountService#create(com.noaa.base.account.dao.UserDTO)
	 */
	@Override
	public int create(UserDTO paramBean) {

		int rs = 0;
		try {
			if (detail(paramBean.getUser_id()) == null) {

				if (paramBean.getUser_pwd() != null && !paramBean.getUser_pwd().isEmpty()) {

					if (paramBean.getUser_id() != null && !paramBean.getUser_id().isEmpty()
							&& paramBean.getUser_email() != null && !paramBean.getUser_email().isEmpty()
							&& (paramBean.getUser_name() != null && !paramBean.getUser_name().isEmpty())) {
						String key = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
						paramBean.setUse_yn(SysKeyword.COMMON_PARAM_USE_Y);
						//paramBean.setUser_pwd(Encryption.encStirng(paramBean.getUser_pwd()));
						//paramBean.setUser_email(AES256Cipher.AES_Encode(paramBean.getUser_email(), key));
						//paramBean.setUser_phone(AES256Cipher.AES_Encode(paramBean.getUser_phone(), key));
						paramBean.setUser_email(paramBean.getUser_email());
						//paramBean.setUser_phone(AES256Cipher.AES_Encode(paramBean.getUser_phone(), key));
						paramBean.setUser_status_code(SysKeyword.CODE_GROUP_ACC_STATUS_DEFAULT);
						paramBean.setCreate_user_id(paramBean.getUser_id());
						paramBean.setUpdate_user_id(paramBean.getUser_id());
						paramBean.setUser_key(key);
						rs = userDao.create(paramBean);
						
						BBSUserDTO bbsUserDto=new BBSUserDTO();
						bbsUserDto.setUser_id(paramBean.getUser_id());
						bbsUserDto.setUser_pwd(paramBean.getUser_pwd());
						bbsUserDto.setUser_name(paramBean.getUser_name());
						bbsUserDto.setUser_phone(paramBean.getUser_phone());
						bbsUserDto.setUser_email(paramBean.getUser_email());
						bbsUserDto.setUser_type_code(paramBean.getUser_type_code());
						bbsUserDto.setUse_yn(paramBean.getUse_yn());
						bbsUserDto.setUser_status_code(paramBean.getUser_status_code());
						bbsUserDto.setUser_key(paramBean.getUser_key());
						bbsUserDto.setCreate_user_id(paramBean.getCreate_user_id());
						bbsUserDto.setUpdate_user_id(paramBean.getCreate_user_id());
							if(paramBean.getUser_roles()!=null){
								String[] paramRoles= paramBean.getUser_roles().split(",");
								updateRole(paramBean.getUser_id(),paramRoles);
							}
						
					} else {
						return MessageStatus.VALID_REQUIRED.value();
					}
				} else {
					return MessageStatus.VALID_REQUIRED.value();
				}
			}else{
				return MessageStatus.KEY_DUPLICATED.value();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	

	@Override
	public int delete(String paramUserID,CommonParams comm) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id",paramUserID);
		paramMap.put("use_yn", "n");
		paramMap.put("update_user_id",comm.getLoginUserID());
		userDao.dropRole(paramMap);
		return userDao.delete(paramMap);
	}

	@Override
	public int changePassword(String paramUserID,String orgPassword, String paramPassword) {
		int rs = 0;
		try{
		String newPassword=EgovFileScrty.encryptPassword(paramPassword,paramUserID);
		String encOldPassword=EgovFileScrty.encryptPassword(orgPassword,paramUserID);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);
		paramMap.put("user_pwd", encOldPassword);
		UserDTO userEntity = userDao.detail(paramMap);
		if (userEntity != null) {
			MberManageVO mberManageVO=mberManageService.selectMberByID(paramUserID);
			mberManageVO.setPassword(newPassword);
			mberManageService.updatePassword(mberManageVO);
			
			paramMap.clear();
			paramMap.put("user_id", paramUserID);
			paramMap.put("user_pwd", newPassword);
			rs = userDao.changePassword(paramMap);
			
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public int updateRole(String paramUserID, String[] paramRole) {
		int rs = 0;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);

		userDao.dropRole(paramMap);
		for (int i = 0; i < paramRole.length; i++) {
			paramMap.clear();
			paramMap.put("user_id", paramUserID);
			paramMap.put("role_id", paramRole[i]);
			rs += userDao.createRole(paramMap);
		}
		return rs;
	}

	@Override
	public int totalCount(String paramUserType, String paramUserStatus, String paramSearchOption, String paramSearchTxt,
			String paramStDate, String paramEnDate,String paramUserAdmCode) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (paramSearchOption != null && !paramSearchOption.isEmpty()) {
			paramMap.put(paramSearchOption, paramSearchOption);
			paramMap.put("search_txt", paramSearchTxt);
		}
		paramMap.put("st_date", paramStDate==null ||paramStDate.isEmpty() ? null : paramStDate.replace("-", ""));
		paramMap.put("en_date", paramEnDate==null ||paramEnDate.isEmpty() ? null : paramEnDate.replace("-", ""));
		paramMap.put("user_adm_code", paramUserAdmCode);
		return userDao.totalCount(paramMap);
	}

	@Override
	public List<UserDTO> list(
			String paramUserType, String paramUserStatus, String paramSearchOption,
			String paramSearchTxt, String paramStDate, String paramEnDate,String paramAdmCode,
			int paramPageNo, int paramRowSize)
			{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (paramSearchOption != null && !paramSearchOption.isEmpty() && paramSearchTxt != null	&& !paramSearchTxt.isEmpty()) {
			if(Arrays.asList(BaseSysKeyword.ACCOUNT_FIND_OPTIONS).contains(paramSearchOption)){
				paramMap.put(paramSearchOption, paramSearchOption);
				paramMap.put("search_txt", paramSearchTxt);
			}
		}
		paramMap.put("st_date",paramStDate==null|| paramStDate.isEmpty() ? null : paramStDate.replace("-", ""));
		paramMap.put("en_date",paramEnDate==null|| paramEnDate.isEmpty() ? null : paramEnDate.replace("-", ""));
		paramMap.put("user_adm_code", paramAdmCode);
		addPageIndex(paramMap, paramPageNo, paramRowSize);
		List<UserDTO> rs = userDao.list(paramMap);
		try{
		for (UserDTO userDTO : rs) {
			//userDTO.setUser_email(AES256Cipher.AES_Decode(userDTO.getUser_email(), userDTO.getUser_key()));
		//	userDTO.setUser_phone(AES256Cipher.AES_Decode(userDTO.getUser_phone(), userDTO.getUser_key()));
			userDTO.setUser_key("");
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}


	@Override
	public int update(String paramUserID, String paramTypeCode,String paramStatusCode) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);
		paramMap.put("user_type_code", paramTypeCode);
		paramMap.put("user_status_code", paramStatusCode);
		return userDao.updateUserType(paramMap);
	}

	@Override
	public UserDTO detail(String paramUserID, String paramPwd) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);
		//paramMap.put("user_pwd", Encryption.encStirng(paramPwd));
		paramMap.put("user_pwd", paramPwd);
		return userDao.detail(paramMap);
	}

	@Override
	public List<RoleDTO> listRoles() {
		
		return roleDao.list();
	}

	@Override
	public int countUserRoles(String paramRoleID) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", paramRoleID);
		return userDao.countRole(paramMap);
	}
	@Override
public int update(UserDTO paramBean, CommonParams comm) {
		
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id", paramBean.getUser_id());
		UserDTO userDto=userDao.detail(paramMap);
		
		String key = userDto.getUser_key();
		paramBean.setUpdate_user_id(comm.getLoginUserID());
		try {
			/*if(paramBean.getUser_pwd()!=null && !paramBean.getUser_pwd().isEmpty()){
				paramBean.setUser_pwd(Encryption.encStirng(paramBean.getUser_pwd()));
			}*/
			if (paramBean.getUser_email() == null || paramBean.getUser_email().isEmpty()
					|| (paramBean.getUser_name() == null || paramBean.getUser_name().isEmpty())
					|| (paramBean.getUser_type_code() == null || paramBean.getUser_type_code().isEmpty())
					|| (paramBean.getUser_roles() == null || paramBean.getUser_roles().isEmpty())
					) {
				return MessageStatus.VALID_REQUIRED.value();
			}
			
			//if(paramBean.getUser_phone()!=null && !paramBean.getUser_phone().isEmpty()){
				//paramBean.setUser_phone(AES256Cipher.AES_Encode(paramBean.getUser_phone(), key));
		//	}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		int rs =  userDao.update(paramBean);
		
		MberManageVO mberManageVO =new MberManageVO();
		mberManageVO.setMberId(paramBean.getUser_id());
		mberManageVO.setMberNm(paramBean.getUser_name());
		mberManageVO.setMberEmailAdres(paramBean.getUser_email());
		mberManageVO.setMoblphonNo(paramBean.getUser_phone());
		mberManageVO.setPassword(paramBean.getUser_pwd());
		try {
			mberManageService.updateMber(mberManageVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String[] paramRoles= paramBean.getUser_roles().split(",");
		updateRole(paramBean.getUser_id(),paramRoles);
		return rs;
	}
	
	@Override
	public int updateFailCnt(UserDTO paramBean) {			
		return  userDao.updateFailCnt(paramBean);
	}

	/*@Transactional*/
	@Override
	public int createAdmin(UserDTO paramBean) {
		int rs=0;
		
		RoleDTO roleDto=new RoleDTO();
		roleDto.setRole_id("ROLE_001");
		roleDto.setRole_title("관리자");
		roleDao.create(roleDto);
		
		roleDto.setRole_id("ROLE_002");
		roleDto.setRole_title("권한2");
		roleDao.create(roleDto);
		roleDto.setRole_id("ROLE_003");
		roleDto.setRole_title("권한3");
		roleDao.create(roleDto);
		
		CodeGrpDTO codeGrp=new CodeGrpDTO();
		codeGrp.setGrp_code("ACC_TYPE");
		codeGrp.setGrp_title("사용자 타입");
		codeGrp.setUse_yn("Y");
		codeGrp.setCreate_user_id(paramBean.getUser_id());
		codeGrp.setUpdate_user_id(paramBean.getUser_id());
		rs+=codeSysDao.createGrp(codeGrp);
		
		codeGrp.setGrp_code("ACC_STUS");
		codeGrp.setGrp_title("사용자 상태");
		codeGrp.setUse_yn("Y");
		rs+=codeSysDao.createGrp(codeGrp);

		codeGrp.setGrp_code("MNU_TYPE");
		codeGrp.setGrp_title("메뉴 타입");
		codeGrp.setUse_yn("Y");
	
		rs+=codeSysDao.createGrp(codeGrp);

		
		CodeSysDTO codeSys=new CodeSysDTO();
		codeSys.setSys_code("ACC_ST01");
		codeSys.setSys_title("사용중");
		codeSys.setGrp_code("ACC_STUS");
		codeSys.setUse_yn("Y");
		codeSys.setCreate_user_id(paramBean.getUser_id());
		codeSys.setUpdate_user_id(paramBean.getUser_id());
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("ACC_ST02");
		codeSys.setSys_title("사용정지");
		codeSys.setGrp_code("ACC_STUS");
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("ACC_ST03");
		codeSys.setSys_title("계정잠금");
		codeSys.setGrp_code("ACC_STUS");
		rs+=codeSysDao.createSys(codeSys);

		codeSys.setSys_code("ACC_0000");
		codeSys.setSys_title("시스템 관리자");
		codeSys.setGrp_code("ACC_TYPE");
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("ACC_0001");
		codeSys.setSys_title("일반인");
		codeSys.setGrp_code("ACC_TYPE");
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("ACC_0002");
		codeSys.setSys_title("공무원");
		codeSys.setGrp_code("ACC_TYPE");
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("ACC_0003");
		codeSys.setSys_title("관계자");
		codeSys.setGrp_code("ACC_TYPE");
		rs+=codeSysDao.createSys(codeSys);
		
		codeSys.setSys_code("MNU_0000");
		codeSys.setSys_title("메뉴 비노출");
		codeSys.setGrp_code("MNU_TYPE");
		rs+=codeSysDao.createSys(codeSys);
		
		paramBean.setUser_name("관리자");
		paramBean.setUser_status_code("ACC_ST01");
		paramBean.setUser_type_code("ACC_0000");
		paramBean.setUser_phone("000-000-0000");
		paramBean.setUse_yn("Y");
	
		paramBean.setCreate_user_id(paramBean.getUser_id());
		paramBean.setUpdate_user_id(paramBean.getUser_id());
		try{
		mberManageService.insertMber(paramBean);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//rs+=create(paramBean);
		
		MenuDTO menuDto=new MenuDTO();
		menuDto.setMenu_id("SYS0000");
		menuDto.setMenu_title("관리자");
		menuDto.setMenu_group("SYS0000");
		menuDto.setMenu_level(0);
		menuDto.setMenu_sort(0);
		menuDto.setMenu_uri("/system/manage/default");
		menuDto.setMenu_type_code("MNU_0000");
		menuDto.setMenu_img("");
		menuDto.setMenu_css("");
		menuDto.setUse_yn("Y");
		menuDto.setUpdate_user_id(paramBean.getUser_id());
		menuDto.setCreate_user_id(paramBean.getUser_id());
		
		rs+=menuDao.create(menuDto);
		
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id", paramBean.getUser_id());
		paramMap.put("role_id", SysKeyword.CODE_ROLE_SYS);
		rs+=userDao.createRole(paramMap);
		
		MenuRoleDTO dto = new MenuRoleDTO();
		dto.setRole_id("ROLE_001");
		dto.setMenu_id("SYS0000");
		dto.setWrite_yn("Y");
		rs+=menuDao.createRole(dto);
		return rs;
	}


	@Override
	public int createLoginLog(CommonParams commons,String paramSessionID) {
		int rs=0;
		LoginLogDTO loginLogDto=new LoginLogDTO();
		loginLogDto.setUser_id(commons.getLoginUserID());
		loginLogDto.setSession_id(paramSessionID);
		
		if(countLoginLog(commons,paramSessionID)>0){
			rs+=updateLoginLog(commons,paramSessionID);
		}else{
			rs+=loginLogDao.create(loginLogDto) ;
		}
		return rs;
	}

	

	@Override
	public int updateLoginLog(CommonParams commons, String paramSessionID) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id",commons.getLoginUserID());
		paramMap.put("session_id",paramSessionID);
		return loginLogDao.update(paramMap);
	}


	@Override
	public int countLoginLog(CommonParams commons, String paramSessionID) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id",commons.getLoginUserID());
		paramMap.put("session_id",paramSessionID);
		return loginLogDao.count(paramMap);
	}


	@Override
	public String findUserID(String paramUserName, String paramUserEmail) {
		String rs=null;
		if(paramUserName!=null && !paramUserName.isEmpty() && paramUserEmail!=null && !paramUserEmail.isEmpty()){
		UserDTO userDto=detailByNameEmail(paramUserName,paramUserEmail);
		
		if(userDto!=null ){
			String id=userDto.getUser_id();
	    	rs=id.substring(0,id.length()/2);
	    	for(int i=id.length()/2;i<id.length();i++){
	    		rs+="*";
	    	}
		}
		}
		return rs;
	}

	@Override
	public UserDTO detailByIDEmail(String paramUserID, String paramUserEmail) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id", paramUserID);
		paramMap.put("user_email", paramUserEmail);
		UserDTO userDto=userDao.detail(paramMap);
		return userDto;
	}

	@Override
	public String findUserPwd(String paramUserID, String paramUserEmail) {
		UserDTO userDto=detailByIDEmail(paramUserID,paramUserEmail);
		String rs=null;
		if(userDto!=null){
			try{
			String rndPwd=UUID.randomUUID().toString().split("-")[0];
			String newPassword=EgovFileScrty.encryptPassword(rndPwd,paramUserID);
			
			
			
			MberManageVO mberManageVO=mberManageService.selectMberByID(paramUserID);
			mberManageVO.setPassword(newPassword);
			mberManageService.updatePassword(mberManageVO);
			HashMap<String,Object>  paramMap=new HashMap<String, Object>();
			paramMap.put("user_id", paramUserID);
			paramMap.put("user_pwd", newPassword);
			userDao.changePassword(paramMap);
			
	    	
	    	String toMail=userDto.getUser_email();
	    	mailSender("maroowe.soft@gmail.com",toMail,"임시 비밀번호가 발급 되었습니다.",rndPwd);
	    	rs="귀하의 이메일로 임시 비밀번호가 발송 되었습니다.";
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		return rs;
	}

	protected void mailSender(String from,String to,String paramTitle,String paramMsg){
		 try {
			 	
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(to);
				messageHelper.setText(paramMsg);
				messageHelper.setFrom(from);
				messageHelper.setSubject(paramTitle);	
				
				mailSender.send(message);
			} catch(Exception e){
				System.out.println(e);
			}
			
	 }
	@Override
	public int countTotalUser() {
		
		return userDao.countTotalUser();
	}
	@Override
	public int updateReFailCnt(UserDTO paramBean) {
		return  userDao.updateReFailCnt(paramBean);
	}
	@Override
	public int updateStatus(UserDTO paramBean) {
		return  userDao.update(paramBean);
	}

	

}
