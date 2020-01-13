package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("eventDao")
public class EventDaoImpl extends BaseDao implements IEventDao {

	@Override
	public int Create(EventDto paramBean) {
		
		return update("eventMaster.create",paramBean);
	}

	@Override
	public List<EventDto> searchItemList(EventDto paramBean) {
		return selectList("eventMaster.searchItemList",paramBean);
	}

	@Override
	public List<EventDto> searchCtgList(EventDto paramBean) {
		return selectList("eventMaster.searchCtgList",paramBean);
	}

	@Override
	public List<EventImgDto> selectEventImgList(EventImgDto paramBean) {
		return selectList("eventMaster.selectEventImgList",paramBean);
	}

	@Override
	public List<EventDto> selectDisEventItemList(EventDto paramBean) {
		return selectList("eventMaster.selectDisEventItemList",paramBean);
	}

	@Override
	public List<EventDto> selectDisEventByPeriodList(EventDto paramBean) {
		return selectList("eventMaster.selectDisEventByPeriodList",paramBean);
	}

	@Override
	public List<EventDto> selectCtgIdDisEventByPeriodList(EventDto paramBean) {
		return selectList("eventMaster.selectCtgIdDisEventByPeriodList",paramBean);
	}

	@Override
	public List<EventDto> listEventFileListAction(EventDto paramBean) {
		return selectList("eventMaster.listEventFileListAction",paramBean);
	}

	@Override
	public int deleteDisEvent(EventDto paramBean) {
		return update("eventMaster.deleteDisEvent",paramBean);
	}

	@Override
	public int deleteDisEventItem(EventDto paramBean) {
		return update("eventMaster.deleteDisEventItem",paramBean);
	}

	@Override
	public int deleteDisEventFile(EventDto paramBean) {
		return update("eventMaster.deleteDisEventFile",paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventListByEvtYear(EventDto paramBean) {
		return selectList("eventMaster.selectListDisEventListByEvtYear",paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventActionByDate(EventDto paramBean) {
		return selectList("eventMaster.selectListDisEventActionByDate",paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventActionByGroup(EventDto paramBean) {
		return selectList("eventMaster.selectListDisEventActionByGroup",paramBean);
	}

	@Override
	public List<EventDto> selectListDisEventByEvtGroup(EventDto paramBean) {
		return selectList("eventMaster.selectListDisEventByEvtGroup",paramBean);
	}

	@Override
	public EventDto selectDisEventGrpId(EventDto paramBean) {
		return (EventDto)selectOne("eventMaster.selectDisEventGrpId",paramBean);
	}
	
	
	@Override
	public List<EventDto> listEventGropInfos(HashMap<String,Object> paramBean){
		return selectList("eventMaster.listEventGropInfos",paramBean);
	}

	@Override
	public int deleteDisEventGroup(HashMap<String, Object> paramMap) {
		
		return update("eventMaster.deleteEventGroup",paramMap);
	}

	@Override
	public EventDto detailEventByEvtId(HashMap<String, Object> paramBean) {
		return (EventDto)selectOne("eventMaster.detailEventByEvtId", paramBean);
	}
}

