package kr.dis.std.advis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.event.dao.EventDto;
@Repository("searchDao")
public class AdvisSearchDaoImpl extends BaseDao implements IAdvisSearchDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AdvisSearchDto> resultCategory(AdvisSearchDto event) {
		return selectList("advisSearch.resultCategory",event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdvisSearchDto> searchItem(AdvisSearchDto event) {
		return selectList("advisSearch.searchItem", event);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdvisSearchDto> ResultCtgId(AdvisSearchDto event) {
		return selectList("advisSearch.searchEvt_id",event);
	}
	
}

