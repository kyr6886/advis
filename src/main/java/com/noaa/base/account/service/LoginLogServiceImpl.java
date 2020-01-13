package com.noaa.base.account.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.noaa.base.bbs.dao.BBSUserRoleDTO;
import com.noaa.base.bbs.dao.IBBSUserDao;
import com.noaa.base.bbs.service.IBBSService;
import com.noaa.base.code.dao.CodeGrpDTO;
import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.code.dao.ICodeSysDao;
import com.noaa.base.utils.AES256Cipher;
import com.noaa.base.utils.DateHelper;
import com.noaa.base.utils.Encryption;
import com.noaa.base.global.MessageKeyword;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.menu.dao.IMenuDao;
import com.noaa.base.menu.dao.MenuDTO;

import egovframework.let.uss.umt.service.EgovMberManageService;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseService implements ILoginLogService {
	
	@Autowired
	private ILoginLogDao loginLogDao;

	@Override
	public int listLoginLog(String paramDateS, String paramDataE,String userId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS);
		paramMap.put("endDate", paramDataE);
		paramMap.put("userId", userId);
		return loginLogDao.count(paramMap);
	}

	@Override
	public int count(String paramDateS, String paramDataE,String userId, String userName) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		return loginLogDao.count(paramMap);
	}
	@Override
	public int totalCount(String paramDateS, String paramDataE,String userId, String userName) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		return loginLogDao.totalCount(paramMap);
	}
	@Override
	public List<LoginLogDTO> list(String paramDateS, String paramDataE,String userId, String userName, int pageNo, int pageSize) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		addPageIndex(paramMap, pageNo, pageSize);
		return loginLogDao.list(paramMap);
	}
	@Override
	public List<LoginLogDTO> list(String paramDateS, String paramDataE,String userId, int pageNo, int pageSize) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", userId);
		addPageIndex(paramMap, pageNo, pageSize);
		return loginLogDao.list(paramMap);
	}
	@Override
	public List<LoginLogDTO> detail(String paramDateS,String paramDataE) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		return loginLogDao.detail(paramMap);
	}

	@Override
	public LoginLogDTO curTime() {
		return loginLogDao.curTime();
	}
	@Override
	public int detailTotalCount(String paramDateS, String paramDataE,String userId, String userName) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		return loginLogDao.detailTotalCount(paramMap);
	}
	@Override
	public List<LoginLogDTO> detail(String paramDateS, String paramDataE,String paramUserId, int pageNo, int pageSize) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", paramUserId);
		addPageIndex(paramMap, pageNo, pageSize);
		List<LoginLogDTO> result = loginLogDao.detail(paramMap);
		return result;
	}

	@Override
	public List<LoginLogDTO> detail(String paramDateS, String paramDataE,String paramUserId) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", paramUserId);
		List<LoginLogDTO> result = loginLogDao.detail(paramMap);
		return result;
	}

	@Override
	public int detailConnTotalCount(String paramDateS, String paramDataE,String paramUserId) {
		paramDateS=paramDateS ==null ? DateHelper.getFirstDayYYYYMMDD():paramDateS;
		paramDataE=paramDataE ==null ? DateHelper.getCurrentYYYYMMDD():paramDataE;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startDate", paramDateS.replace("/","").replace("-",""));
		paramMap.put("endDate", paramDataE.replace("/","").replace("-",""));
		paramMap.put("userId", paramUserId);
		return loginLogDao.detailConnTotalCount(paramMap);
	}

	

}
