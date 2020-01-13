package com.noaa.base.account.service;

import java.util.Arrays;
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
import com.noaa.base.utils.Encryption;
import com.noaa.base.global.MessageKeyword;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.menu.dao.IMenuDao;
import com.noaa.base.menu.dao.MenuDTO;

import egovframework.let.uss.umt.service.EgovMberManageService;

@Service("userService")
public class UserServiceImpl extends BaseService implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	public int totalCount(String paramUsrId, String paramUserName,String paramTypeCode, String paramStatusCode,String paramStDate,String paramEnDate) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", paramUsrId);
		paramMap.put("userName", paramUserName);
		paramMap.put("typeCode", paramTypeCode);
		paramMap.put("statusCode", paramStatusCode);
		paramMap.put("stDate", paramStDate);
		paramMap.put("enDate", paramEnDate);
		return userDao.totalCount(paramMap);
	}
	

}
