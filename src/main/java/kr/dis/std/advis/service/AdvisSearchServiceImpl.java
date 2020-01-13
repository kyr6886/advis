package kr.dis.std.advis.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.noaa.base.BaseService;
import com.noaa.base.CommonParams;

import kr.dis.std.advis.dao.AdvisSearchDto;
import kr.dis.std.advis.dao.IAdvisSearchDao;

@Service("advisSearchService")
public class AdvisSearchServiceImpl extends BaseService implements IAdvisSearchService {

	@Autowired
	private IAdvisSearchDao SearchDao;

	@Override
	public List<AdvisSearchDto> searchItem(CommonParams commonParams, AdvisSearchDto Search) {
		
		if (Search.getTitle() != null && !( "").equals(Search.getTitle())) {
			Search.setTitle(Search.getTitle());
		}
		
		return SearchDao.searchItem(Search);
	}

	@Override
	public List<AdvisSearchDto> searchResultList( AdvisSearchDto Search) {
		
		List<AdvisSearchDto> rs = SearchDao.ResultCtgId(Search);
		ArrayList<String> _array = new ArrayList<String>();
		List<AdvisSearchDto> result = null;
		if ( rs.size() > 0) {
			for (int i= 0; i < rs.size(); i++) {
				_array.add(rs.get(i).getEvt_id());
			}
			Search.setCtgArr(_array);
			result = SearchDao.resultCategory(Search);
		}
		return result;
	}

	
}
