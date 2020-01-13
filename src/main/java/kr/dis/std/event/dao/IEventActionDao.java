package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

public interface IEventActionDao {

	public int create(EventActionDto paramBean);
	public int delete(EventActionDto paramBean);
	public List<EventActionDto> select(HashMap<String,Object> paramBean);
}
