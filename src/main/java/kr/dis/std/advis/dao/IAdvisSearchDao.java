package kr.dis.std.advis.dao;

import java.util.List;

import kr.dis.std.event.dao.EventDto;

public interface IAdvisSearchDao {
	public List<AdvisSearchDto> resultCategory(AdvisSearchDto event);
	public List<AdvisSearchDto> searchItem(AdvisSearchDto event);
	public List<AdvisSearchDto> ResultCtgId(AdvisSearchDto event);
	
}
