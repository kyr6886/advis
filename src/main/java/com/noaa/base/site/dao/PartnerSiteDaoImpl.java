package com.noaa.base.site.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("partnerSiteDao")
public class PartnerSiteDaoImpl extends BaseDao implements IPartnerSiteDao {

	@Override
	public int create(PartnerSiteDto paramBean) {
		return update("partnerSite.create", paramBean);
	}

	@Override
	public List<PartnerSiteDto> list() {
		return selectList("partnerSite.list",null);
	}
	@Override
	public List<PartnerSiteDto> list(HashMap<String, Object> paramMap) {
		return selectList("partnerSite.list",paramMap);
	}

	@Override
	public int drop(HashMap<String, Object> paramMap) {
		return delete("partnerSite.drop", paramMap);
	}

	@Override
	public List<PartnerSiteDto> detail(HashMap<String, Object> paramMap) {
		return selectList("partnerSite.list",paramMap);
	}

	@Override
	public int update(PartnerSiteDto paramBean) {
		return update("partnerSite.update", paramBean);
	}
	@Override
	public int updateUseYN(PartnerSiteDto paramBean) {
		return update("partnerSite.updateUseYN", paramBean);
	}

	@Override
	public int deleteSiteImage(PartnerSiteDto paramBean) {
		return delete("partnerSite.deleteSiteImage", paramBean);
	}



}
