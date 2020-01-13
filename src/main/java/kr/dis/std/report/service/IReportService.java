package kr.dis.std.report.service;

import java.util.List;

import com.noaa.base.CommonParams;

import kr.dis.std.event.dao.EventActionDto;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventItemDto;

public interface IReportService {
	
	public int createReport(List<EventItemDto> paramBean, EventDto paramEvent, List<EventActionDto> paramActionBeans, CommonParams common);
	public List<EventDto> listReportByCtgId(String paramCtgId, String paramIsRootYn, String paramIsCsYn);
	
}