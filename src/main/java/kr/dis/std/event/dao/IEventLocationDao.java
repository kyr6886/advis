package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

public interface IEventLocationDao {
	public List<EventLocationDto> list(HashMap<String,Object> paramMap);
	public List<EventLocationDto> listGroup(HashMap<String,Object> paramMap);
}
