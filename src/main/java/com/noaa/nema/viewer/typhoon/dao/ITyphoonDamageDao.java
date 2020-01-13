package com.noaa.nema.viewer.typhoon.dao;

import java.util.HashMap;
import java.util.List;

public interface ITyphoonDamageDao {
	public List<TyphoonDamageDto> list(HashMap<String , Object> paramMap);
}
