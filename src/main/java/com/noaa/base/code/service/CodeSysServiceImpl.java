package com.noaa.base.code.service;

import java.util.HashMap;
import java.util.List;

import javax.xml.soap.Detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.CommonParams;
import com.noaa.base.MessageStatus;
import com.noaa.base.code.dao.CodeGrpDTO;
import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.code.dao.ICodeSysDao;

@Service("codeSysService")
public class CodeSysServiceImpl extends BaseService implements ICodeSysService {
	@Autowired
	private ICodeSysDao codeSysDao;

	@Override
	public List<CodeSysDTO> list(String paramCodeGrp) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grp_code", paramCodeGrp);
		return codeSysDao.list(paramMap);
	}

	@Override
	public List<CodeGrpDTO> listGrp() {

		return codeSysDao.listGrp(null);
	}

	@Override
	public int createCodeGrp(CodeGrpDTO paramBean) {
		paramBean.setUse_yn(paramBean.getUse_yn() == null ? "Y" : paramBean.getUse_yn());
		return codeSysDao.createGrp(paramBean);
	}

	@Override
	public int createCodeGrp(CodeGrpDTO paramBean, CommonParams common) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("grp_code", paramBean.getGrp_code());
		if(paramBean.getGrp_code() == null || paramBean.getGrp_code().isEmpty()){
			return MessageStatus.VALID_REQUIRED.value();
		}
		if(paramBean.getGrp_title() == null || paramBean.getGrp_title().isEmpty()){
			return MessageStatus.VALID_REQUIRED.value();
		}
		if(codeSysDao.detailGrp(paramMap)!=null){
			return MessageStatus.KEY_DUPLICATED.value();
		}
		paramBean.setUse_yn(paramBean.getUse_yn() == null ? "Y" : paramBean.getUse_yn());
		if (common != null) {
			paramBean.setCreate_user_id(common.getLoginUserID());
			paramBean.setUpdate_user_id(common.getLoginUserID());
		}
		
		
		
		return codeSysDao.createGrp(paramBean);
	}

	@Override
	public int createCodeSys(CodeSysDTO paramBean) {
		
		return codeSysDao.createSys(paramBean);
	}

	@Override
	public int dropCodeGrp(String paramCodeGrp) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("grp_code", paramCodeGrp);
		return codeSysDao.dropGrp(paramMap);
	}
	@Override
	public int dropCodeSys(String paramCodeSys) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sys_code", paramCodeSys);
		return codeSysDao.dropSys(paramMap);
	}
	@Override
	public int createCodeSys(CodeSysDTO paramBean, CommonParams common) {
		HashMap<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("sys_code", paramBean.getSys_code());
		if(paramBean.getSys_code() == null || paramBean.getSys_code().isEmpty()){
			return MessageStatus.VALID_REQUIRED.value();
		}
		if(paramBean.getSys_title() == null || paramBean.getSys_title().isEmpty()){
			return MessageStatus.VALID_REQUIRED.value();
		}
		if(codeSysDao.detailSys(paramMap)!=null){
			return MessageStatus.KEY_DUPLICATED.value();
		}
		paramBean.setUse_yn(paramBean.getUse_yn() == null ? "Y" : paramBean.getUse_yn());
		if (common != null) {
			paramBean.setCreate_user_id(common.getLoginUserID());
			paramBean.setUpdate_user_id(common.getLoginUserID());
		}
		return codeSysDao.createSys(paramBean);
	}

}
