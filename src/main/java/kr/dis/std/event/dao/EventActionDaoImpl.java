package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("eventActionDao")
public class EventActionDaoImpl extends BaseDao implements IEventActionDao {

	@Override
	public int create(EventActionDto paramBean) {
		return update("eventAction.create", paramBean);
	}

	@Override
	public int delete(EventActionDto paramBean) {
		return delete("eventAction.delete", paramBean);
	}

	@Override
	public List<EventActionDto> select(HashMap<String, Object> paramBean) {
		return selectList("eventAction.select", paramBean);
	}
	

}
