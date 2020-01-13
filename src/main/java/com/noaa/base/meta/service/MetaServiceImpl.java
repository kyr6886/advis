package com.noaa.base.meta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.BaseSysKeyword;
import com.noaa.base.meta.dao.IMetaAdmDao;
import com.noaa.base.meta.dao.IMetaDao;
import com.noaa.base.meta.dao.IMetaLawDao;
import com.noaa.base.meta.dao.MetaAdmDTO;
import com.noaa.base.meta.dao.MetaDTO;

@Service("metaService")
public class MetaServiceImpl extends BaseService implements IMetaService {

	@Autowired
	private IMetaDao metaDao;	
	@Autowired
	private IMetaAdmDao metaAdmDao;	
	@Autowired
	private IMetaLawDao metaLawDao;
	
	@Autowired
	private IMetaAdmService metaAdmService;
	@Autowired
	private IMetaLawService metaLawService;
	
	@Value("${meta.tablespace}")
	private String metaTablespace;
	
	@Override
	public List<MetaDTO> list() {
		List<MetaDTO> metaDto=new ArrayList<MetaDTO>();
		
		MetaDTO meta1=new MetaDTO();
		meta1.setMeta_table(BaseSysKeyword.META_KOR_ADM[0]);
		meta1.setDesc(BaseSysKeyword.META_KOR_ADM[1]);
		meta1.setUse_yn("N");
		metaDto.add(meta1);
		
		MetaDTO meta2=new MetaDTO();
		meta2.setMeta_table(BaseSysKeyword.META_KOR_LAW[0]);
		meta2.setDesc(BaseSysKeyword.META_KOR_LAW[1]);
		meta2.setUse_yn("N");
		metaDto.add(meta2);
		
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("owner", metaTablespace);
		paramMap.put("metaTableSpaces", BaseSysKeyword.META_TABLE_SPACES);
		List<MetaDTO> entities=metaDao.list(paramMap);
		for (MetaDTO meta : metaDto) {			
			for (MetaDTO entity : entities) {
				if(meta.getMeta_table().equals( entity.getMeta_table().toLowerCase())){
					meta.setUse_yn("Y");
					break;
				}
			}
		}
		return metaDto;
	}
	
	@Override
	public int createMeta(String paramMetaId) {
		int creTable=0;
		if(paramMetaId.equals(BaseSysKeyword.META_KOR_ADM[0])){
			creTable = metaAdmDao.createTable();
			metaAdmDao.createIndex();
		}else if(paramMetaId.equals(BaseSysKeyword.META_KOR_LAW[0])){
			creTable = metaLawDao.createTable();
			metaLawDao.createIndex();
		}		
		return creTable;
	}

	@Override
	public List<MetaAdmDTO> list(String paramMetaId) {
		
		return null;
	}

	@Override
	public void createMetaData(String paramMetaId) {
		if(paramMetaId.equals(BaseSysKeyword.META_KOR_ADM[0])){
			metaAdmService.creataData();
		}else if(paramMetaId.equals(BaseSysKeyword.META_KOR_LAW[0])){
			metaLawService.creataData();
		}
	}

	

}
