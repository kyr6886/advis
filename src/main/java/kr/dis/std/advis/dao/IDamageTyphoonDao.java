package kr.dis.std.advis.dao;

import java.util.HashMap;
import java.util.List;

public interface IDamageTyphoonDao {
	public DamageTyphoonDto detail(HashMap<String,Object> paramMap);
	public List<DamageTyphoonDto> list(HashMap<String,Object> paramMap);
}
