package com.noaa.base.bbs.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.CommonParams;
import com.noaa.base.bbs.dao.BBSCategoryDTO;
import com.noaa.base.bbs.dao.BBSContentDTO;
import com.noaa.base.bbs.dao.BBSLogDTO;
import com.noaa.base.bbs.dao.BBSMasterDTO;
import com.noaa.base.bbs.dao.BBSUserDTO;

public interface IBBSService {
	public List<BBSContentDTO> listContent(String paramBBSID,String paraCatSeq,String paramSearchTitle,String paramSearchContent,String paramSearchName,String paramSearchStDate,String paramSearchEnDate,String paramSpellKor,String paramSpellEn,int pageNo, int pageSize );
	public List<BBSContentDTO> listRecentContent(String paramBBSID,int pageNo, int pageSize );
	public BBSContentDTO createContent(BBSContentDTO paramBean,CommonParams paramCommon);
	public BBSContentDTO createContent(BBSContentDTO paramBean,CommonParams paramCommon, List<MultipartFile> paramFiles);
	public BBSContentDTO createContent(BBSContentDTO paramBean, CommonParams paramCommon,List<MultipartFile> paramFiles,String paramFilePath) ;
	public BBSContentDTO detailContent(String paramBBSID,int paramBBSSeq);
	public BBSContentDTO detailContent(String paramBBSID,int paramBBSSeq,CommonParams paramCommons);
	
    
    public int deleteContent(String paramBBSID,int paramBBSSeq,CommonParams paramCommon);
    
    public int updateContent(BBSContentDTO paramBean,CommonParams paramCommon);
    public int updateContent(BBSContentDTO paramBean,CommonParams paramCommon, List<MultipartFile> paramFiles) ;
    public int updateBBSContent(BBSContentDTO paramBean,CommonParams paramCommon, List<MultipartFile> paramFiles) ;
    
    public int maxSort(HashMap<String,Object> paramMap);
    public int totalCountBBSContent(String paramBBSID,String paraCatSeq, String paramSearchTitle,String paramSearchContent, String paramSearchName,String paramStDate,String paramEnDate,String paramSpellKor,String paramSpellEn);
    
    public BBSMasterDTO detailMaster(String paramBBSID);
    public BBSMasterDTO detailMaster(String paramBBSID,CommonParams paramCommon);
    public List<BBSMasterDTO> listMaster();
    public int createMaster(BBSMasterDTO paramBean);
    public int createMaster(BBSMasterDTO paramBean,String[] paramCategoies);
    public int updateMaster(BBSMasterDTO paramBean,String[] paramCategoies,CommonParams common);
    
    public List<BBSCategoryDTO> listCategory(String paramBBSID);
    
    
    public List<BBSLogDTO> listHistory(int paramBBSSeq,String paramUserID);
    public BBSLogDTO detailHistory(int paramBBSSeq,String paramUserID,String paramTargetCode);
    public int createHistory(BBSContentDTO paramBean,String paramLogType);
    
    public int updateFile(String paramBBSID,int paramSeq);
}
