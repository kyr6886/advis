package com.noaa.nema.viewer.kmaInform.dao;

import java.util.HashMap;
import java.util.List;

public interface IKmaWaterDao {
	
	public List<KmaWaterDto> kmaWaterObsList(HashMap<String, Object> paramMap);
	public KmaWaterDto kmaWaterDamList(HashMap<String, Object> paramMap);
	public String obsWaterMaxYmdh(HashMap<String, Object> paramMap);
	public String obsDamMaxYmdh(HashMap<String, Object> paramMap);


}
