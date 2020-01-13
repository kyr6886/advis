package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("eventLocationDao")
public class EventLocationDaoImpl extends BaseDao implements IEventLocationDao {

	@Override
	public List<EventLocationDto> list(HashMap<String, Object> paramMap) {
		
		return selectList("eventLocation.list",paramMap);
	}

	@Override
	public List<EventLocationDto> listGroup(HashMap<String, Object> paramMap) {
		return selectList("eventLocation.listGroup",paramMap);
	}

}
