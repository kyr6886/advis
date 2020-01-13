package com.noaa.nema.viewer.kma.service;

import java.util.HashMap;
import java.util.List;

import com.noaa.nema.viewer.kmaInform.dao.KmaAwsDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaWaterDto;

/**
 * 기사청 관련 서비스 
 * @author dev
 *
 */
public interface IKmaService {
	
	/**
	 * 최근 호우 특보
	 * @return
	 */
	public KmaInformDto detailKmaStatusRainReport(String paramDate,String paramDummyDate);
	public KmaInformDto detailKmaStatusTyphoonReport(String paramStDate,String paramEndDate,String paramDummyDate);
	public List<KmaInformDto> listKmaInform(String paramSearchTxt,String paramStDate,String paramEndDate,String paramReportType);
	/**
	 * aws 지점정보
	 * @return
	 */
	public List<KmaAwsDto> kmaAwsBrnchList(String paramSido, String paramEndDate);
	public List<KmaAwsDto> kmaAwsGunguList(String paramSido, String paramEndDate);
	
	public String awsMaxYmdh();
	
	public List<KmaWaterDto> kmaWaterObsList(String paramEndDate);
	
	
	public List<KmaWaterDto> kmaWaterDamList(String paramDamCd, String paramEndDate);
	
	public String obsWaterMaxYmdh();
	public String obsDamMaxYmdh(String paramEndDate);
	
	public KmaAwsDto obsWaterThrAwsDataList(String paramLawCode, String paramEndDate);
	public KmaAwsDto obsWaterDayAwsDataList(String paramLawCode, String paramEndDate);
	
	
	
	

}
