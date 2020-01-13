package kr.dis.std.event.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.BaseSysKeyword;
import com.noaa.base.CommonParams;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;

import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.dao.ICategoryDao;
import kr.dis.std.event.dao.EventActionDto;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventImgDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.EventLocationDto;
import kr.dis.std.event.dao.IEventActionDao;
import kr.dis.std.event.dao.IEventDao;
import kr.dis.std.event.dao.IEventItemDao;
import kr.dis.std.event.dao.IEventLocationDao;
@Service("eventService")
public class EventServiceImpl extends BaseService implements IEventService {

	@Autowired
	private IEventItemDao eventItemDao;
	@Autowired
	private IEventDao eventDao;
	@Autowired
	private IEventLocationDao eventLocationDao;
	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IEventActionDao eventActionDao;
	
	@Override
	public int createEventItem(List<EventItemDto> paramBean,EventDto paramEvent,CommonParams common) {
		int rs=0;
		
		paramEvent.setEvt_date(paramEvent.getEvt_date().replaceAll("-","").replaceAll(" ",""));		
		String eventID=paramEvent.getDisaster_type()+"-"+paramEvent.getEvt_date()+"-"+1;
		paramEvent.setEvt_id(eventID);
		paramEvent.setEvt_num(1);
		paramEvent.setCtg_id(paramEvent.getDisaster_type());
		paramEvent.setCreate_user_id(common.getLoginUserID());
		paramEvent.setUpdate_user_id(common.getLoginUserID());
		paramEvent.setUse_yn(BaseSysKeyword.COMMON_PARAM_USE_Y);
		rs+=eventDao.deleteDisEventItem(paramEvent);
		
			rs+=eventDao.Create(paramEvent);
			for (EventItemDto eventItemDto : paramBean) {
				eventItemDto.setEvt_id(paramEvent.getEvt_id());
				eventItemDto.setEvt_num(paramEvent.getEvt_num());
				eventItemDto.setCreate_user_id(common.getLoginUserID());
				eventItemDto.setUpdate_user_id(common.getLoginUserID());
				eventItemDto.setUse_yn(BaseSysKeyword.COMMON_PARAM_USE_Y);
			
				rs+=eventItemDao.create(eventItemDto);
			}
		
		return rs;
	}
	
	@Override
	public int createEvent(EventDto paramBean, CommonParams common) {
		paramBean.setEvt_id(paramBean.getCtg_id()+"-"+paramBean.getEvt_date()+"-1");
		paramBean.setEvt_group(paramBean.getEvt_id());
		paramBean.setCreate_user_id(common.getLoginUserID());
		paramBean.setUpdate_user_id(common.getLoginUserID());
		
		paramBean.setUse_yn("Y");
		paramBean.setEvt_num(0);
		paramBean.setSort(0);
		return eventDao.Create(paramBean);
	}

	@Override
	public int createEventFile(EventDto paramEvent, CommonParams common) {
		int rs = 0;
		
		paramEvent.setEvt_date(paramEvent.getEvt_date().replaceAll("-","").replaceAll(" ",""));		
	
		paramEvent.setEvt_id(paramEvent.getDisaster_type()+"-"+paramEvent.getEvt_date()+"-"+1);
		paramEvent.setCtg_id(paramEvent.getDisaster_type());
		paramEvent.setCreate_user_id(common.getLoginUserID());
		paramEvent.setUse_yn(BaseSysKeyword.COMMON_PARAM_USE_Y);
		
		rs= eventItemDao.createFile(paramEvent);
		return rs;
	}
	
	@Override
	public List<EventDto> searchItemList(EventDto paramBean) {
		List<EventDto> rs=eventDao.searchItemList(paramBean);
		
		return rs;
	}
	
	@Override
	public List<EventItemDto> selectEventItemList(EventItemDto paramBean){
		List<EventItemDto> rs=eventItemDao.selectEventItemList(paramBean);
		for (EventItemDto eventDto : rs) {
			if(eventDto.getContents()!=null){
				eventDto.setContents(eventDto.getContents().replaceAll("\r\n", "<br>").replaceAll("\n", "<br>"));
			}
		}
		return rs;
	}

	@Override
	public List<EventImgDto> selectEventImgList(EventImgDto paramBean) {
		
		ArrayList<String> arrList = new ArrayList<String>();
		List<CategoryDTO> ctgAllList = null;
		
		CategoryDTO dto = new CategoryDTO();
		dto.setDepth(paramBean.getDepth());
		ctgAllList = categoryDao.listCtgId(dto);
		
		if(ctgAllList.size()>0){
			for(int i=0; i<ctgAllList.size(); i++){
				int resultIndex = paramBean.getTitle().indexOf( ctgAllList.get(i).getTitle() );
				if(resultIndex > -1){
					arrList.add(ctgAllList.get(i).getTitle()); //keyword list
				}
			}
		}
		paramBean.setKeyWordList(arrList);
		return eventDao.selectEventImgList(paramBean);
	}
	
	@Override
	public List<EventImgDto> selectEventImgList(List<TyphoonCastInfoDto> paramList) {
		
		ArrayList<String> arrList = new ArrayList<String>();
		for(int i=0; i<paramList.size(); i++){
			arrList.add(paramList.get(i).getTyp_name());
		}
		EventImgDto paramBean=new EventImgDto();
		paramBean.setKeyWordList(arrList);
		
		return eventDao.selectEventImgList(paramBean);
	}
	
	@Override
	public List<EventDto> selectDisEventItemList(EventDto paramBean){
		
		List<EventDto> rs = eventDao.selectDisEventItemList(paramBean);
		for(int i=0; i<rs.size(); i++){
			EventItemDto paramEvent = new EventItemDto();
			paramEvent.setEvt_id(rs.get(i).getEvt_id());
			rs.get(i).setEventItemList(eventItemDao.selectEventItemList(paramEvent));
		}
		return rs;
	}
	
	@Override
	public List<EventDto> selectDisEventByPeriodList(EventDto paramBean){
		
		List<EventDto> rs = eventDao.selectDisEventByPeriodList(paramBean);
		for(int i=0; i<rs.size(); i++){
			if(rs.get(i)!=null){
				EventItemDto paramEvent = new EventItemDto();
				paramEvent.setEvt_id(rs.get(i).getEvt_id());
				rs.get(i).setEventItemList(eventItemDao.selectEventItemList(paramEvent));
			}
		}
		return rs;
	}

	@Override
	public List<EventDto> selectCtgIdDisEventByPeriodList(EventDto paramBean) {
		List<EventDto> rs = eventDao.selectCtgIdDisEventByPeriodList(paramBean);
		for(int i=0; i<rs.size(); i++){
			if(rs.get(i)!=null){
				EventItemDto paramEvent = new EventItemDto();
				paramEvent.setEvt_id(rs.get(i).getEvt_id());
				rs.get(i).setEventItemList(eventItemDao.selectEventItemList(paramEvent));
			}
		}
		return rs;
	}

	@Override
	public List<EventDto> listEventFileListAction(EventDto paramBean) {
		return eventDao.listEventFileListAction(paramBean);
	}

	@Override
	public int deleteAllEventAction(EventDto paramBean) {
		int rs = 0;
		
		rs += eventDao.deleteDisEvent(paramBean);
		rs += eventDao.deleteDisEventItem(paramBean);
		rs += eventDao.deleteDisEventFile(paramBean);
		
		EventActionDto paramActionBean = new EventActionDto();
		paramActionBean.setYear(paramBean.getEvt_id().substring(4,8));
		paramActionBean.setMonth(paramBean.getEvt_id().substring(8, 10));
		paramActionBean.setDay(paramBean.getEvt_id().substring(10, 12));
		paramActionBean.setHour(paramBean.getEvt_id().substring(12, 14));
		rs += eventActionDao.delete(paramActionBean);
		
		return rs;
	}

	@Override
	public EventDto getListEventItemByLastEvent(List<EventDto> paramList) {
		
		EventDto rs = new EventDto();
		List<EventDto> list = paramList;
		
		if(list.size()>0){
			rs = list.get(list.size()-1);
			EventItemDto paramEvent = new EventItemDto();
			paramEvent.setEvt_id(rs.getEvt_id());
			paramEvent.setCtg_id("50000");
			List<EventItemDto> allEventItemList = eventItemDao.selectCtgIdEventItemList(paramEvent);
			
			
			List<EventItemDto> tempList = new ArrayList<>();
			for(int i=0; i<allEventItemList.size(); i++){
				if(allEventItemList.get(i).getContentsTitle().equals("일시")){
					EventItemDto dto = new EventItemDto();
					
					String date = allEventItemList.get(i).getContents().replaceAll("[{}/?,;|()*~`!^-]", "");
					date = date.replaceAll("  ", "");
					
					String[] arr = date.split(" "); 
					if(arr.length > 0) {
						
						dto.setEvt_date(arr[0]);
						
						if(arr.length > 1){
							dto.setEvt_time(arr[1]);
						}
						if(dto.getEvt_time()==null) dto.setEvt_time("00:00");
					}
					
					if(tempList.size() == 0){
						tempList.add(dto);
					}else{
						boolean same = false;
						for(int j=0; j<tempList.size(); j++){
							if(tempList.get(j).getEvt_date().equals(dto.getEvt_date()) && tempList.get(j).getEvt_time().equals(dto.getEvt_time())){
								same = true;
							}
						}
						if(!same){
							tempList.add(dto);
						}
					}
					
				}
			}
			for(int i=0; i<tempList.size(); i++){
				
				for(int j=0; j<allEventItemList.size(); j++){
					
					if(allEventItemList.get(j).getContentsTitle().equals("일시")){
						EventItemDto dto = new EventItemDto();
						
						String date = allEventItemList.get(j).getContents().replaceAll("[{}/?,;|()*~`!^-]", "");
						date = date.replaceAll("  ", "");
						
						String[] arr = date.split(" "); 
						if(arr.length > 0) {
							dto.setEvt_date(arr[0]);
							
							if(arr.length > 1){
								dto.setEvt_time(arr[1]);
							}
							if(dto.getEvt_time()==null) dto.setEvt_time("00:00");
						}
						
						if(tempList.get(i).getEvt_date().equals(dto.getEvt_date()) && tempList.get(i).getEvt_time().equals(dto.getEvt_time())){
							List<EventItemDto> orgList = new ArrayList<>();
							EventItemDto cont = new EventItemDto();

							if(tempList.get(i).getEventItemList()!=null){
								orgList = tempList.get(i).getEventItemList();
							}
							
							for(int k=j+1; k<allEventItemList.size(); k++){
								if(allEventItemList.get(k).getContentsTitle().equals("기관명")){
									cont.setEvt_orgNm(allEventItemList.get(k).getContents());
								}else if(allEventItemList.get(k).getContentsTitle().equals("대처사항")){
									cont.setContents(allEventItemList.get(k).getContents());
								}else{
									break;
								}
							}

							if(cont.getEvt_orgNm()!=null || cont.getContents()!=null){
								orgList.add(cont);
								tempList.get(i).setEventItemList(orgList);
							}
							
						}
					}
				}
				
			}
			
			rs.setResponseStatusEventList(tempList);
		}
		
		return rs;
	}

	@Override
	public List<EventDto> selectListDisEventActionByDate(String paramCtgId, String paramYear, String paramMonth, String paramDay, String paramHour, String paramEvtGrpId) {
		
		EventDto paramBean = new EventDto();
		paramBean.setCtg_id(paramCtgId);
		paramBean.setEvt_group(paramEvtGrpId);
		paramBean.setYear(paramYear);
		paramBean.setMonth(paramMonth);
		paramBean.setDay(paramDay);
		paramBean.setHour(paramHour);

		return eventDao.selectListDisEventActionByDate(paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventActionByGroup(String paramCtgId, String paramEvtGrp) {
		
		EventDto paramBean = new EventDto();
		paramBean.setCtg_id(paramCtgId);
		paramBean.setEvt_group(paramEvtGrp);

		return eventDao.selectListDisEventActionByGroup(paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventByEvtGroup(String paramCtgId, String paramEvtGrp) {
		EventDto paramBean = new EventDto();
		paramBean.setCtg_id(paramCtgId);
		paramBean.setEvt_group(paramEvtGrp);
		
		List<EventDto> rs = eventDao.selectListDisEventByEvtGroup(paramBean);
		for(int i=0; i<rs.size(); i++){
			EventItemDto itemBean = new EventItemDto();
			itemBean.setEvt_id(rs.get(i).getEvt_id());
			rs.get(i).setEventItemList(eventItemDao.selectDepthEventItemList(itemBean));
		}

		return rs;
	}

	@Override
	public List<EventDto> getEventListByDate(EventDto paramBean) {
		List<EventDto> rs = new ArrayList<>();
		
		if(paramBean.getDamage_code().equals("HZD002")){
			paramBean.setEvt_year(paramBean.getEvt_year().substring(0,4));
			rs = eventDao.selectListDisEventListByEvtYear(paramBean);
		}else{
			paramBean.setBeg_date(paramBean.getBeg_date().substring(0, 6));
			paramBean.setEnd_date(paramBean.getEnd_date().substring(0, 6));
			rs = eventDao.selectDisEventByPeriodList(paramBean);
		}
		return rs;
	}

	@Override
	public EventDto selectDisEventGrpId(String paramCtgId, String paramStDate, String paramEndDate, String paramTitle) {
		EventDto paramBean = new EventDto();
		paramBean.setCtg_id(paramCtgId);
		paramBean.setBeg_date(paramStDate);
		paramBean.setEnd_date(paramEndDate);
		paramBean.setTitle(paramTitle);
	
		EventDto rs = new EventDto();
		rs = eventDao.selectDisEventGrpId(paramBean);
		if(rs == null){
			paramBean = new EventDto();
			paramBean.setCtg_id(paramCtgId);
			paramBean.setEvt_group(paramStDate);
			rs = eventDao.selectDisEventGrpId(paramBean);
		}
		
		return rs;
	}

	@Override
	public List<EventDto> listEventGroupInfos(String paramCtgId, String paramIsRoot, String paramIsCs) {
		HashMap<String,Object> paramMap=new  HashMap<String,Object>();
		paramMap.put("ctg_id", paramCtgId);
		paramMap.put("is_root_yn", paramIsRoot);
		paramMap.put("is_cs_yn", paramIsCs);
		return eventDao.listEventGropInfos(paramMap);
	}

	@Override
	public int deleteEventGroup(String paramEventGroupId) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("evt_group", paramEventGroupId);
		return eventDao.deleteDisEventGroup(paramMap);
	}

	@Override
	public List<EventLocationDto> listEventLocation(String paramDmeType, String paramStDate, String paramEndDate) {
		
		
		if(paramDmeType!=null && paramStDate!=null){
			if(paramStDate.equals(paramEndDate)){
				HashMap<String,Object> paramMap=new HashMap<String,Object>();
				paramMap.put("dme_type",paramDmeType);
				paramMap.put("st_date", paramStDate);
				
				return eventLocationDao.listGroup(paramMap);
			}else{
			
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("dme_type",paramDmeType);
			paramMap.put("st_date", paramStDate);
			paramMap.put("end_date", paramEndDate==null?paramStDate:paramEndDate);
			return eventLocationDao.list(paramMap);
			}
		}
		return null;
	}

	@Override
	public EventDto detailEventByEvtId(String paramEvtId) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("evt_id",paramEvtId);
		return eventDao.detailEventByEvtId(paramMap);
	}
	
	
	
	
}
