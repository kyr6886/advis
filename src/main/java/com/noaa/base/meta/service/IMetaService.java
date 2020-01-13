package com.noaa.base.meta.service;

import java.util.List;

import com.noaa.base.meta.dao.MetaAdmDTO;
import com.noaa.base.meta.dao.MetaDTO;

public interface IMetaService {
	
	/**
	 * @return 전체 meta 코드 리스트
	 */
	public List<MetaDTO> list();	
	
	/**
	 * @param paramMetaId 메타리스트 그룹ID
	 * @return MetaId 리스트
	 */
	public List<MetaAdmDTO> list(String paramMetaId);	

	/**
	 * @param paramMetaId 메타리스트 그룹ID
	 * @return 생성완료
	 */
	public int createMeta(String paramMetaId);

	/** 메타 데이타 엑셀 등록
	 * @param paramMetaId
	 */
	public void createMetaData(String paramMetaId);

}
