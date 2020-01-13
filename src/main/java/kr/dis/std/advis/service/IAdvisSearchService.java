package kr.dis.std.advis.service;

import java.util.List;
import com.noaa.base.CommonParams;

import kr.dis.std.advis.dao.AdvisSearchDto;
import kr.dis.std.event.dao.EventDto;

public interface IAdvisSearchService {
	
	public List<AdvisSearchDto> searchItem(CommonParams commonParams, AdvisSearchDto Search);
	
	public List<AdvisSearchDto> searchResultList(AdvisSearchDto Search);

	
	
}
