package com.noaa.nema.viewer.kmaInform.dao;

import java.util.HashMap;
import java.util.List;

public interface IKmaAwsDao {
	
	public List<KmaAwsDto> list(HashMap<String, Object> paramMap);
	public String awsMaxYmdh(HashMap<String, Object> paramMap);
	public List<KmaAwsDto> awsGunguList(HashMap<String, Object> paramMap);
	public KmaAwsDto obsWaterAwsDataList(HashMap<String, Object> paramMap);
	
	
	

}
