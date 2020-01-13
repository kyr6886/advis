package com.noaa.base.site.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.BaseService;
import com.noaa.base.CommonParams;
import com.noaa.base.file.dao.AttachFileDTO;
import com.noaa.base.file.service.IFileService;
import com.noaa.base.site.dao.IPartnerSiteDao;
import com.noaa.base.site.dao.PartnerSiteDto;
@Service("partnerSiteService")
public class PartnerSiteServiceImpl extends BaseService implements IPartnerSiteService {

	@Autowired
	private IPartnerSiteDao partnerSiteDao;
	@Autowired
	private IFileService fileService;
	
	
	@Value("${file.thumb.path}")
	private String filePath;
	
	@Override
	public List<PartnerSiteDto> list() {
		return list(null);
	}
	@Override
	public List<PartnerSiteDto> list(String paramUseYn) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("use_yn",paramUseYn);
		List<PartnerSiteDto> rs=partnerSiteDao.list(paramMap);
		List<String> fileGrps=new ArrayList<String>();
		for (PartnerSiteDto partnerSiteDto : rs) {
			if(partnerSiteDto.getFile_grp_id()!=null){
				fileGrps.add(partnerSiteDto.getFile_grp_id());
			} 
		}
		
		if(fileGrps!=null && fileGrps.size()>0){
				List<AttachFileDTO> files=fileService.list(fileGrps);
				for (AttachFileDTO attachFileDTO : files) {
					for (PartnerSiteDto partnerSiteDto : rs) {
						if(attachFileDTO.getFile_grp_id().equals(partnerSiteDto.getFile_grp_id())){
							partnerSiteDto.setFileInfo(attachFileDTO);
						}
					}
				}
		}
		return partnerSiteDao.list(paramMap);
	}
	@Override
	public List<PartnerSiteDto> detail(String paramUrl) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("site_url", paramUrl);
		return partnerSiteDao.detail(paramMap);
	}
	@Override
	public int create(PartnerSiteDto paramBean,CommonParams CommonParams, List<MultipartFile> siteThumb) {
		String fileGrpID = fileService.create(siteThumb, filePath);
		if(fileGrpID!=null){
			paramBean.setFile_grp_id(fileGrpID);
		}
		paramBean.setUse_yn(paramBean.getUse_yn() == null ? "Y":paramBean.getUse_yn());
		return partnerSiteDao.create(paramBean);
	}
	@Override
	public int update(PartnerSiteDto paramBean, CommonParams CommonParams,List<MultipartFile> siteThumb) {
		
		if(siteThumb!=null && siteThumb.get(0).getSize()<=0){
			paramBean.setFile_grp_id(null);
		}else{
			paramBean.setFile_grp_id(fileService.create(siteThumb, filePath));
		}
		return partnerSiteDao.update(paramBean);
	}
	@Override
	public int updateUseYN(PartnerSiteDto paramBean) {
		return partnerSiteDao.updateUseYN(paramBean);
	}

	@Override
	public int drop(String paramUrl) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("site_url", paramUrl);
		return partnerSiteDao.drop(paramMap);
	}
	@Override
	public int deleteSiteImage(PartnerSiteDto paramBean) {
		return partnerSiteDao.deleteSiteImage(paramBean);
	}
	@Override
	public int deleteSiteImage(String paramUrl, String paramFileId) {
		PartnerSiteDto paramBean = new PartnerSiteDto();
		paramBean.setSite_url(paramUrl);
		paramBean.setFile_grp_id(paramFileId);
		return deleteSiteImage(paramBean);
	}
	

}
