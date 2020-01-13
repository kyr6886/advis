package kr.dis.std.advis.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("damageTyphoonDao")
public class DamageTyphoonDaoImpl extends BaseDao implements IDamageTyphoonDao  {

	@Override
	public DamageTyphoonDto detail(HashMap<String, Object> paramMap) {
		
		return (DamageTyphoonDto)selectOne("damageTyphoon.detail",paramMap);
	}

	@Override
	public List<DamageTyphoonDto> list(HashMap<String, Object> paramMap) {
		
		return selectList("damageTyphoon.list",paramMap);
	}

}
