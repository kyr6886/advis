package kr.dis.std.event.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("eventItemDao")
public class EventItemDaoImpl extends BaseDao implements IEventItemDao {

	@Override
	public int create(EventItemDto paramBean) {
		
		return update("eventItem.create", paramBean);
	}

	@Override
	public int createFile(EventDto paramBean) {
		return update("eventItem.createFile", paramBean);
	}

	@Override
	public List<EventItemDto> selectEventItemList(EventItemDto paramBean){
		return selectList("eventItem.selectEventItemList",paramBean);
	}
	
	@Override
	public List<EventItemDto> selectDepthEventItemList(EventItemDto paramBean){
		return selectList("eventItem.selectDepthEventItemList",paramBean);
	}

	@Override
	public List<EventItemDto> selectCtgIdEventItemList(EventItemDto paramBean) {
		return selectList("eventItem.selectCtgIdEventItemList",paramBean);
	}
	
}
