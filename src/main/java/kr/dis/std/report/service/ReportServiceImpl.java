package kr.dis.std.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.BaseSysKeyword;
import com.noaa.base.CommonParams;

import kr.dis.std.event.dao.EventActionDto;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.IEventActionDao;
import kr.dis.std.event.dao.IEventDao;
import kr.dis.std.event.dao.IEventItemDao;

@Service("reportService")
public class ReportServiceImpl extends BaseService implements IReportService {
	
	@Autowired
	private IEventDao eventDao;
	@Autowired
	private IEventItemDao eventItemDao;
	@Autowired
	private IEventActionDao eventActionDao;
	
	@Override
	public int createReport(List<EventItemDto> paramBean, EventDto paramEvent, List<EventActionDto> paramActionBeans, CommonParams common) {
		int rs=0;
		paramEvent.setCreate_user_id(common.getLoginUserID());
		paramEvent.setUpdate_user_id(common.getLoginUserID());
		paramEvent.setUse_yn(BaseSysKeyword.COMMON_PARAM_USE_Y);
		rs+=eventDao.Create(paramEvent);
		
		for (EventItemDto eventItemDto : paramBean) {
			eventItemDto.setEvt_id(paramEvent.getEvt_id());
			eventItemDto.setEvt_num(paramEvent.getEvt_num());
			eventItemDto.setCreate_user_id(common.getLoginUserID());
			eventItemDto.setUpdate_user_id(common.getLoginUserID());
			eventItemDto.setUse_yn(BaseSysKeyword.COMMON_PARAM_USE_Y);
			rs+=eventItemDao.create(eventItemDto);
		}
		
		for(EventActionDto eventActionDto : paramActionBeans){
			rs += eventActionDao.create(eventActionDto);
		}
		
		return rs;
	}

	@Override
	public List<EventDto> listReportByCtgId(String paramCtgId, String paramIsRootYn, String paramIsCsYn) {
		// TODO Auto-generated method stub
		EventDto paramBean = new EventDto();
		paramBean.setCtg_id(paramCtgId);
		paramBean.setIs_root_yn(paramIsRootYn);
		paramBean.setIs_cs_yn(paramIsCsYn);
		
		List<EventDto> rs = eventDao.selectListDisEventByEvtGroup(paramBean);
		
		return rs;
	}
	
}
