package com.noaa.base.admin.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.meta.dao.MetaAdmDTO;
import com.noaa.base.meta.dao.MetaDTO;

public class AdminMetaViewModel extends BaseViewModel {
	
	private List<MetaDTO> listMeta;	
	private List<MetaAdmDTO> listMetaAdm;
	
	private String paramMetaId;
	private int resultCount;

	public List<MetaDTO> getListMeta() {
		return listMeta;
	}

	public void setListMeta(List<MetaDTO> listMeta) {
		this.listMeta = listMeta;
	}

	public String getParamMetaId() {
		return paramMetaId;
	}

	public void setParamMetaId(String paramMetaId) {
		this.paramMetaId = paramMetaId;
	}

	public List<MetaAdmDTO> getListMetaAdm() {
		return listMetaAdm;
	}

	public void setListMetaAdm(List<MetaAdmDTO> listMetaAdm) {
		this.listMetaAdm = listMetaAdm;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	
	
	
	
	

}
