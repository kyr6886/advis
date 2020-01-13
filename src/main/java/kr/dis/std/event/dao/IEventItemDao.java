package kr.dis.std.event.dao;

import java.util.List;

public interface IEventItemDao {
	public int create(EventItemDto paramBean);

	public int createFile(EventDto paramEvent);

	public List<EventItemDto> selectEventItemList(EventItemDto paramEvent);
	
	public List<EventItemDto> selectDepthEventItemList(EventItemDto paramEvent);
	
	public List<EventItemDto> selectCtgIdEventItemList(EventItemDto paramEvent);
	
}
