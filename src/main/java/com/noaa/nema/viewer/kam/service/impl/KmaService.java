package com.noaa.nema.viewer.kam.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.area.dao.IAreaCodeDao;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.kma.service.IKmaService;
import com.noaa.nema.viewer.kmaInform.dao.IKmaAwsDao;
import com.noaa.nema.viewer.kmaInform.dao.IKmaInformDao;
import com.noaa.nema.viewer.kmaInform.dao.IKmaWaterDao;
import com.noaa.nema.viewer.kmaInform.dao.KmaAwsDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaLocDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaWaterDto;
@Service("kmaService")
public class KmaService implements IKmaService {
	@Autowired
	private IKmaInformDao kmaInformDao;
	@Autowired
	private IAreaCodeDao areaCodeDao;
	@Autowired
	private IKmaAwsDao kmaAwsDao;
	@Autowired
	private IKmaWaterDao kmaWaterDao;
	public KmaInformDto detailKmaStatusRainReport(String paramDate, String paramDummyDate) {
		KmaInformDto rs = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("report_date", paramDate);
		//paramMap.put("report_txt", ViewerSysKeyword.KMA_RPT_TYPE_RAIN);
		
		if(paramDummyDate!=null){
			paramMap.clear();
			paramMap.put("dummy_date", paramDummyDate);
		}
		
		
		rs = kmaInformDao.detailCurrent(paramMap);
		
		
		List<KmaInformModel> sidoInform = new ArrayList<KmaInformModel>();
		List<KmaInformModel> sigunguInform = new ArrayList<KmaInformModel>();
		
		//List<String> sidoTxt = new ArrayList<String>();
		//List<String> sigunguTxt = new ArrayList<String>();
		String regEx = "\\((.*?)\\)";
	
		Pattern pattern = Pattern.compile(regEx);
	
		List<KmaLocDto> kmaLocation=kmaInformDao.listKmaLocation(null);
		List<String> listExt=new ArrayList<String>();
	//	List<YearAreaCodeDto> sido = areaCodeDao.listSido(null);

	//	List<YearAreaCodeDto> gungu = areaCodeDao.listSiGungu(new HashMap<String, Object>());
	//	kmaInformDao.updateKmaRainTypeInit(new HashMap<String,Object>());
		HashMap<String, String> reportArea = new HashMap<String, String>();
		if (rs != null) {

			rs.setAlertAreaCodes(new ArrayList<String>());
			rs.setWaringAreaCodes(new ArrayList<String>());
			rs.setListAlertWindWave(new ArrayList<String>());
			rs.setListWaringWindWave(new ArrayList<String>());
			String _reportStat = rs.getStat_tm_ef().replace(ViewerSysKeyword.KMA_RPT_TYPE_RAIN,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_RAIN);
			_reportStat = _reportStat.replace(ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON);
			_reportStat=_reportStat.replace("강풍","\r\n강풍");
			_reportStat=_reportStat.replace("풍랑","\r\n풍랑");
			_reportStat=_reportStat.replace("대설","\r\n대설");
			_reportStat=_reportStat.replace("건조","\r\n건조");
			_reportStat=_reportStat.replace("폭풍","\r\n폭풍");
			_reportStat=_reportStat.replace("지진","\r\n지진");
			_reportStat=_reportStat.replace("한파","\r\n한파");
			_reportStat=_reportStat.replace("황사","\r\n황사");
			_reportStat=_reportStat.replace("폭염","\r\n폭염");
			
			
			String[] _status = _reportStat.split("\r\n");
			
		

			for (String item : _status) {
				//풍랑
				if( item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_WAVE) > -1
						&& (item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1	|| item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1)
				){
				//	parseAlertWindWave(rs,kmaLocation,item);
				//	parseWarningWindWave(rs,kmaLocation,item);
					continue;
				}
				
				
				if (   item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RAIN) > -1
						&& (item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1	|| item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1)
						&& 	item.indexOf(ViewerSysKeyword.KMA_RPT_CLEAR) <0)
						

				 {
					String[] statusTxt = item.split(":");

					/*int alarmCount = checkCountRainAlarm(item);
					int warningCount = checkCountRainWarning(item);

					rs.setAlertCount(alarmCount > 0 ? alarmCount : rs.getAlertCount());
					rs.setWarningCount(warningCount > 0 ? warningCount : rs.getWarningCount());
					 */
	
				
					
					if (statusTxt.length > 1) {
						String temp = statusTxt[1];
						
						java.util.Stack<Integer> stack = new java.util.Stack<Integer>();

						List<String> replaceStr=new ArrayList<String>();
						
						for(int i=0;i<temp.length();i++){
							if(temp.charAt(i)=='('){
								stack.add(i);
							
							}else if(temp.charAt(i)==')'){
								int index=stack.pop();
								if(stack.size()>0){
									replaceStr.add(temp.substring(index,i+1));
								}
							}
						}
						
						for (String string : replaceStr) {
							temp=temp.replace(string, "");
						}
						
						
						Matcher regexMatcher = pattern.matcher(temp);
						while (regexMatcher.find()) {
							
							String matchText = regexMatcher.group();
							
						//	sigunguTxt.add(matchText);
							KmaInformModel _sigungu=new KmaInformModel();
							_sigungu.setKamArea(matchText);
							if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1){
								_sigungu.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW);
							}
							if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1){
								_sigungu.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_RED);
							}
							
							sigunguInform.add(_sigungu);
						
							
						}
						String replaceSido=temp.replaceAll(regEx, "");
						KmaInformModel _sido=new KmaInformModel();
						_sido.setKamArea(replaceSido);
						if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1){
							_sido.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW);
						}
						if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1){
							_sido.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_RED);
						}
						sidoInform.add(_sido);
					//	sidoTxt.add(replaceSido);
						
					}
				}
			}

			if (sidoInform.size() > 0 || sidoInform.size() > 0) {
				
				// 발령 시도
				for (KmaInformModel sidoStr : sidoInform) {
				/*	if(sidoStr.getKamArea().trim().indexOf("울릉도")>-1){
						KmaInformModel temp=new KmaInformModel();
						temp.setKamArea("울릉도");
						temp.setKmaInfotType(sidoStr.getKmaInfotType());
						sigunguInform.add(temp);
						
					}*/
					for (KmaLocDto kmaLocDto : kmaLocation) {
						if(kmaLocDto.getLoc_type().equals("1")){
							String[] spSigungu=kmaLocDto.getLoc_name().split(",");
							for (String spItem : spSigungu) {
								if(sidoStr.getKamArea().trim().indexOf(spItem)>-1){
									if(!listExt.contains(kmaLocDto.getAdm_code())){
									if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
										
										rs.setAlertCount(rs.getAlertCount()+1);
										
									}
									if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
										
										rs.setWarningCount(rs.getWarningCount()+1);
										
									}
									listExt.add(kmaLocDto.getAdm_code());
									}
									
								}
							}
						}
						if(kmaLocDto.getAdm_code().equals("99999") || !kmaLocDto.getAdm_code().substring(2,5).equals("000")){
						
							continue;
						}
						
					
						
						
						if (sidoStr.getKamArea().trim().indexOf(kmaLocDto.getLoc_name().substring(0, 3)) > -1
								|| sidoStr.getKamArea().trim().indexOf(kmaLocDto.getShort_name()) > -1
								) {
							reportArea.put(kmaLocDto.getAdm_code(), "Y");
							if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
								if(!rs.getAlertAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getAlertAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
							if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
								if(!rs.getWaringAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getWaringAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
						}
					}
				} // end 발령시도
					// 발령 시군구

				for (KmaInformModel sigunguStr : sigunguInform) {
					List<String> removeSigungu=new ArrayList<String>();
					for (KmaLocDto kmaLocDto : kmaLocation) {
						String[] spSigungu=kmaLocDto.getLoc_name().split(",");
						
						
						if(kmaLocDto.getAdm_code().equals("99999") || kmaLocDto.getAdm_code().substring(2,5).equals("000")){
							continue;
						}
						
						for (String spItem : spSigungu) {
						
							 int endPosition= spItem.length()>5?3:2;
						//	if (yearAreaCodeDto.getSigungu() != null && yearAreaCodeDto.getSigungu() != "") {
								if(sigunguStr.getKamArea().trim().indexOf("제외") > -1){
									if(sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition))>-1){
										removeSigungu.add(kmaLocDto.getAdm_code());
									}
									
								}else if (sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition)) > -1) {
									for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										boolean isBreak=false;
										String[] splitAmdCode=kmaLocDto.getAdm_code().split(",");
										for (String spCode : splitAmdCode) {
											if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(spCode.substring(0, 2))){
												reportArea.put(spCode, "N");
												isBreak=true;
											}
										
										}
										if(isBreak)break;
										
									}
								
								}

						}//end for spSigungu
					}
					if(removeSigungu.size()>0){
						//시도에서 제외 시군구 이외의 시군구는 모두 넣는다.ㅣ
						for (KmaLocDto kmaLocDto : kmaLocation) {
						
							if(!kmaLocDto.getAdm_code().trim().substring(2,5).equals("000")&&kmaLocDto.getAdm_code().trim().substring(0,2).equals(removeSigungu.get(0).trim().substring(0,2))){
								if(!removeSigungu.contains(kmaLocDto.getAdm_code())){
									
									//for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										//if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(kmaLocDto.getAdm_code().substring(0, 2))){
											reportArea.put(kmaLocDto.getAdm_code(), "N");
										//	break;
									//	}
									//}
								}
							}
						}
						//removeSigungu.contains(arg0)
						//System.out.println("!");
					}
				}
				
		
				
				
				List<String> listSido = new ArrayList<String>();
				List<String> listGungu = new ArrayList<String>();
				List<String> listSea = new ArrayList<String>();
				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					if (entry.getKey() != "" && entry.getKey().length() > 1) {
						if (entry.getValue().equals("N")) {
							
							listGungu.add(entry.getKey());
						}
						
					}
				}

				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					boolean isUse = false;
					for (String item : listGungu) {
						if (entry.getKey().substring(0, 2).equals(item.substring(0, 2))) {
							isUse = true;
							break;
						}
					}
					if (!isUse) {
						listSido.add(entry.getKey());
					}
				}

				listGungu.addAll(listExt);
				rs.setReportSido(listSido);
				rs.setReportGungu(listGungu);
				

			} // end if
			
			if(rs.getListAlertWindWave().size()>0){
				paramMap.clear();
				paramMap.put("loc_type",1);
				paramMap.put("loc_codes",rs.getListAlertWindWave());
				kmaInformDao.updateKmaRainType(paramMap);
			}
			
			if(rs.getListWaringWindWave().size()>0){
				paramMap.clear();
				paramMap.put("loc_type",2);
				paramMap.put("loc_codes",rs.getListWaringWindWave());
				kmaInformDao.updateKmaRainType(paramMap);
			}
		}
		return rs;

	}
	

	@Override
	public KmaInformDto detailKmaStatusTyphoonReport(String paramDate, String paramEndDate, String paramDummyDate) {
		KmaInformDto rs = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("report_date", paramDate);
		//paramMap.put("report_txt", ViewerSysKeyword.KMA_RPT_TYPE_RAIN);
		
		if(paramDummyDate!=null){
			paramMap.clear();
			paramMap.put("dummy_date", paramDummyDate);
		}
		
		rs = kmaInformDao.detailCurrent(paramMap);
		
		List<KmaInformModel> sidoInform = new ArrayList<KmaInformModel>();
		List<KmaInformModel> sigunguInform = new ArrayList<KmaInformModel>();
		
		//List<String> sidoTxt = new ArrayList<String>();
		//List<String> sigunguTxt = new ArrayList<String>();
		String regEx = "\\((.*?)\\)";
	
		Pattern pattern = Pattern.compile(regEx);
	
		List<KmaLocDto> kmaLocation=kmaInformDao.listKmaLocation(null);
		List<String> listExt=new ArrayList<String>();
	//	List<YearAreaCodeDto> sido = areaCodeDao.listSido(null);

	//	List<YearAreaCodeDto> gungu = areaCodeDao.listSiGungu(new HashMap<String, Object>());
	//	kmaInformDao.updateKmaRainTypeInit(new HashMap<String,Object>());
		HashMap<String, String> reportArea = new HashMap<String, String>();
		if (rs != null) {

			rs.setAlertAreaCodes(new ArrayList<String>());
			rs.setWaringAreaCodes(new ArrayList<String>());
			rs.setListAlertWindWave(new ArrayList<String>());
			rs.setListWaringWindWave(new ArrayList<String>());
			String _reportStat = rs.getStat_tm_ef().replace(ViewerSysKeyword.KMA_RPT_TYPE_RAIN,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_RAIN);
			_reportStat = _reportStat.replace(ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON);
			_reportStat=_reportStat.replace("강풍","\r\n강풍");
			_reportStat=_reportStat.replace("풍랑","\r\n풍랑");
			_reportStat=_reportStat.replace("대설","\r\n대설");
			_reportStat=_reportStat.replace("건조","\r\n건조");
			_reportStat=_reportStat.replace("폭풍","\r\n폭풍");
			_reportStat=_reportStat.replace("지진","\r\n지진");
			_reportStat=_reportStat.replace("한파","\r\n한파");
			_reportStat=_reportStat.replace("황사","\r\n황사");
			_reportStat=_reportStat.replace("폭염","\r\n폭염");
			
			
			String[] _status = _reportStat.split("\r\n");
			
		

			for (String item : _status) {
				//풍랑
				if( item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_WAVE) > -1
						&& (item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1	|| item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1)
				){
				//	parseAlertWindWave(rs,kmaLocation,item);
				//	parseWarningWindWave(rs,kmaLocation,item);
					continue;
				}
				
				
				if (   item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON) > -1
						&& (item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1	|| item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1)
						&& 	item.indexOf(ViewerSysKeyword.KMA_RPT_CLEAR) <0)
						

				 {
					String[] statusTxt = item.split(":");

					/*int alarmCount = checkCountRainAlarm(item);
					int warningCount = checkCountRainWarning(item);

					rs.setAlertCount(alarmCount > 0 ? alarmCount : rs.getAlertCount());
					rs.setWarningCount(warningCount > 0 ? warningCount : rs.getWarningCount());
					 */
	
				
					
					if (statusTxt.length > 1) {
						String temp = statusTxt[1];
						
						java.util.Stack<Integer> stack = new java.util.Stack<Integer>();

						List<String> replaceStr=new ArrayList<String>();
						
						for(int i=0;i<temp.length();i++){
							if(temp.charAt(i)=='('){
								stack.add(i);
							
							}else if(temp.charAt(i)==')'){
								int index=stack.pop();
								if(stack.size()>0){
									replaceStr.add(temp.substring(index,i+1));
								}
							}
						}
						
						for (String string : replaceStr) {
							temp=temp.replace(string, "");
						}
						
						
						Matcher regexMatcher = pattern.matcher(temp);
						while (regexMatcher.find()) {
							
							String matchText = regexMatcher.group();
							
						//	sigunguTxt.add(matchText);
							KmaInformModel _sigungu=new KmaInformModel();
							_sigungu.setKamArea(matchText);
							if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1){
								_sigungu.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW);
							}
							if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1){
								_sigungu.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_RED);
							}
							
							sigunguInform.add(_sigungu);
						
							
						}
						String replaceSido=temp.replaceAll(regEx, "");
						KmaInformModel _sido=new KmaInformModel();
						_sido.setKamArea(replaceSido);
						if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW) > -1){
							_sido.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW);
						}
						if(item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RED) > -1){
							_sido.setKmaInfotType(ViewerSysKeyword.KMA_RPT_TYPE_RED);
						}
						sidoInform.add(_sido);
					//	sidoTxt.add(replaceSido);
						
					}
				}
			}

			if (sidoInform.size() > 0 || sidoInform.size() > 0) {
				
				// 발령 시도
				for (KmaInformModel sidoStr : sidoInform) {
				/*	if(sidoStr.getKamArea().trim().indexOf("울릉도")>-1){
						KmaInformModel temp=new KmaInformModel();
						temp.setKamArea("울릉도");
						temp.setKmaInfotType(sidoStr.getKmaInfotType());
						sigunguInform.add(temp);
						
					}*/
					for (KmaLocDto kmaLocDto : kmaLocation) {
						if(kmaLocDto.getLoc_type().equals("1")){
							String[] spSigungu=kmaLocDto.getLoc_name().split(",");
							for (String spItem : spSigungu) {
								if(sidoStr.getKamArea().trim().indexOf(spItem)>-1){
									if(!listExt.contains(kmaLocDto.getAdm_code())){
									if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
										
										rs.setAlertCount(rs.getAlertCount()+1);
										
									}
									if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
										
										rs.setWarningCount(rs.getWarningCount()+1);
										
									}
									listExt.add(kmaLocDto.getAdm_code());
									}
									
								}
							}
						}
						if(kmaLocDto.getAdm_code().equals("99999") || !kmaLocDto.getAdm_code().substring(2,5).equals("000")){
						
							continue;
						}
						
					
						
						
						if (sidoStr.getKamArea().trim().indexOf(kmaLocDto.getLoc_name().substring(0, 3)) > -1
								|| sidoStr.getKamArea().trim().indexOf(kmaLocDto.getShort_name()) > -1
								) {
							reportArea.put(kmaLocDto.getAdm_code(), "Y");
							if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
								if(!rs.getAlertAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getAlertAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
							if(sidoStr.getKmaInfotType().equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
								if(!rs.getWaringAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getWaringAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
						}
					}
				} // end 발령시도
					// 발령 시군구

				for (KmaInformModel sigunguStr : sigunguInform) {
					List<String> removeSigungu=new ArrayList<String>();
					for (KmaLocDto kmaLocDto : kmaLocation) {
						String[] spSigungu=kmaLocDto.getLoc_name().split(",");
						
						
						if(kmaLocDto.getAdm_code().equals("99999") || kmaLocDto.getAdm_code().substring(2,5).equals("000")){
							continue;
						}
						
						for (String spItem : spSigungu) {
						
							 int endPosition= spItem.length()>5?3:2;
						//	if (yearAreaCodeDto.getSigungu() != null && yearAreaCodeDto.getSigungu() != "") {
								if(sigunguStr.getKamArea().trim().indexOf("제외") > -1){
									if(sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition))>-1){
										removeSigungu.add(kmaLocDto.getAdm_code());
									}
									
								}else if (sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition)) > -1) {
									for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										boolean isBreak=false;
										String[] splitAmdCode=kmaLocDto.getAdm_code().split(",");
										for (String spCode : splitAmdCode) {
											if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(spCode.substring(0, 2))){
												reportArea.put(spCode, "N");
												isBreak=true;
											}
										
										}
										if(isBreak)break;
										
									}
								
								}

						}//end for spSigungu
					}
					if(removeSigungu.size()>0){
						//시도에서 제외 시군구 이외의 시군구는 모두 넣는다.ㅣ
						for (KmaLocDto kmaLocDto : kmaLocation) {
						
							if(!kmaLocDto.getAdm_code().trim().substring(2,5).equals("000")&&kmaLocDto.getAdm_code().trim().substring(0,2).equals(removeSigungu.get(0).trim().substring(0,2))){
								if(!removeSigungu.contains(kmaLocDto.getAdm_code())){
									
									//for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										//if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(kmaLocDto.getAdm_code().substring(0, 2))){
											reportArea.put(kmaLocDto.getAdm_code(), "N");
										//	break;
									//	}
									//}
								}
							}
						}
						//removeSigungu.contains(arg0)
						//System.out.println("!");
					}
				}
				
		
				
				
				List<String> listSido = new ArrayList<String>();
				List<String> listGungu = new ArrayList<String>();
				List<String> listSea = new ArrayList<String>();
				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					if (entry.getKey() != "" && entry.getKey().length() > 1) {
						if (entry.getValue().equals("N")) {
							
							listGungu.add(entry.getKey());
						}
						
					}
				}

				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					boolean isUse = false;
					for (String item : listGungu) {
						if (entry.getKey().substring(0, 2).equals(item.substring(0, 2))) {
							isUse = true;
							break;
						}
					}
					if (!isUse) {
						listSido.add(entry.getKey());
					}
				}

				listGungu.addAll(listExt);
				rs.setReportSido(listSido);
				rs.setReportGungu(listGungu);
				

			} // end if
			
			if(rs.getListAlertWindWave().size()>0){
				paramMap.clear();
				paramMap.put("loc_type",1);
				paramMap.put("loc_codes",rs.getListAlertWindWave());
				kmaInformDao.updateKmaRainType(paramMap);
			}
			
			if(rs.getListWaringWindWave().size()>0){
				paramMap.clear();
				paramMap.put("loc_type",2);
				paramMap.put("loc_codes",rs.getListWaringWindWave());
				kmaInformDao.updateKmaRainType(paramMap);
			}
		}
		return rs;
	}
	@Override
	public List<KmaAwsDto> kmaAwsBrnchList(String paramSido, String paramEndDate) {
		
		
		List<KmaAwsDto> rs = null;
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		paramMap.put("end_date", paramEndDate);
		
		rs = kmaAwsDao.list(paramMap);
	
		return rs;
	}
	@Override
	public List<KmaAwsDto> kmaAwsGunguList(String paramSido, String paramEndDate) {
		List<KmaAwsDto> rs = null;
		
		String beg_date = "";
		String end_date = "";
		String beg_date_3hr ="";
		String beg_date_6hr = "";
		String beg_date_day = "";
		String end_date_day = "";
		String beg_date_6day = "";
		SimpleDateFormat beg_date_df = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat beg_date_3hr_df = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat beg_date_6hr_df = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat beg_date_day_df = new SimpleDateFormat("yyyyMMddhh");
		SimpleDateFormat beg_date_6day_df = new SimpleDateFormat("yyyyMMddhh");
		
		Calendar cal_beg_date_df = Calendar.getInstance();
		Calendar cal_beg_date_3hr_df = Calendar.getInstance();
		Calendar cal_beg_date_6hr_df = Calendar.getInstance();
		Calendar cal_beg_date_day_df = Calendar.getInstance();
		Calendar cal_beg_date_6day_df = Calendar.getInstance();

		try {
			cal_beg_date_df.setTime(beg_date_df.parse(paramEndDate));
			cal_beg_date_3hr_df.setTime(beg_date_3hr_df.parse(paramEndDate));
			cal_beg_date_6hr_df.setTime(beg_date_6hr_df.parse(paramEndDate));
			cal_beg_date_day_df.setTime(beg_date_day_df.parse(paramEndDate));
			cal_beg_date_6day_df.setTime(beg_date_6day_df.parse(paramEndDate));
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_beg_date_df.add(Calendar.HOUR, -1);
		cal_beg_date_3hr_df.add(Calendar.HOUR, -2);
		cal_beg_date_6hr_df.add(Calendar.HOUR, -5);
		cal_beg_date_day_df.add(Calendar.DATE, -2);
		cal_beg_date_6day_df.add(Calendar.DATE, -5);
	
		beg_date = 	beg_date_df.format(cal_beg_date_df.getTime());
		end_date = paramEndDate;
		beg_date_3hr = beg_date_3hr_df.format(cal_beg_date_3hr_df.getTime());
		beg_date_6hr = beg_date_6hr_df.format(cal_beg_date_6hr_df.getTime());
		beg_date_day = beg_date_day_df.format(cal_beg_date_day_df.getTime()) + "00";
		end_date_day = paramEndDate + "23";
		beg_date_6day = beg_date_6day_df.format(cal_beg_date_6day_df.getTime()) + "00";
	
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		paramMap.put("beg_date", beg_date);
		paramMap.put("end_date", end_date);
		paramMap.put("beg_date_3hr", beg_date_3hr);
		paramMap.put("beg_date_6hr", beg_date_6hr);
		paramMap.put("beg_date_day", beg_date_day);
		paramMap.put("end_date_day", end_date_day);
		paramMap.put("beg_date_6day", beg_date_6day);
				
		rs = kmaAwsDao.awsGunguList(paramMap);
		return rs;
	}
	@Override
	public String awsMaxYmdh() {
		HashMap<String, Object> paramMap = new HashMap<>();
		return kmaAwsDao.awsMaxYmdh(paramMap);
	}
	
	
	@Override
	public List<KmaWaterDto> kmaWaterObsList(String paramEndDate) {
		

		
		SimpleDateFormat present = new SimpleDateFormat("yyyyMMddhhmmss");	
		SimpleDateFormat beg_date_df = new SimpleDateFormat("yyyyMMddhhmmss");	
		Date time = new Date();
		String time1 = present.format(time.getTime());
		Calendar cal_beg_date_df = Calendar.getInstance();

		try {
			cal_beg_date_df.setTime(beg_date_df.parse(time1));
	
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_beg_date_df.add(Calendar.DATE, -14);
		
		String prev_end_date = beg_date_df.format(cal_beg_date_df.getTime());
		
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("paramStDate", prev_end_date);

		
		return kmaWaterDao.kmaWaterObsList(paramMap);
	}
	
	@Override
	public List<KmaWaterDto> kmaWaterDamList(String paramDamCd, String paramEndDate) {
		
		List<KmaWaterDto> rs = new ArrayList<>();
		
		KmaWaterDto presentDto = new KmaWaterDto();
		KmaWaterDto prevDto = new KmaWaterDto();
		
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("damcd", paramDamCd);
		paramMap.put("end_date", paramEndDate);
		
		presentDto = kmaWaterDao.kmaWaterDamList(paramMap);
		
		String prev_end_date = "";
		SimpleDateFormat beg_date_df = new SimpleDateFormat("yyyyMMddhh");	
		
		Calendar cal_beg_date_df = Calendar.getInstance();

		try {
			cal_beg_date_df.setTime(beg_date_df.parse(paramEndDate));
	
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_beg_date_df.add(Calendar.HOUR, -3);
		
		prev_end_date = beg_date_df.format(cal_beg_date_df.getTime());
		
		paramMap.put("damcd", paramDamCd);
		paramMap.put("end_date", prev_end_date);
		
		prevDto = kmaWaterDao.kmaWaterDamList(paramMap);
		
		rs.add(presentDto);
		rs.add(prevDto);
		
		return rs;
	}
	@Override
	public String obsWaterMaxYmdh() {
		HashMap<String, Object> paramMap = new HashMap<>();
		return kmaWaterDao.obsWaterMaxYmdh(paramMap);
	}
	@Override
	public String obsDamMaxYmdh(String paramEndDate) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("end_date", paramEndDate);
		return kmaWaterDao.obsDamMaxYmdh(paramMap);
	}

	
	@Override
	public KmaAwsDto obsWaterThrAwsDataList(String paramLawCode, String paramEndDate) {
		KmaAwsDto rs = new KmaAwsDto();
	
		HashMap<String, Object> paramMap = new HashMap<>();
		
		String prev_beg_date = "";
		String prev_beg_date_3hr = "";
		String prev_end_date_3hr = "";
		SimpleDateFormat beg_date_df = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat beg_date_df_3hr = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat end_date_df_3hr = new SimpleDateFormat("yyyyMMddhh");	

		Calendar cal_beg_date_df = Calendar.getInstance();
		Calendar cal_beg_date_df_3hr = Calendar.getInstance();
		Calendar cal_end_date_df_3hr = Calendar.getInstance();

		try {
			cal_beg_date_df.setTime(beg_date_df.parse(paramEndDate));
			cal_beg_date_df_3hr.setTime(beg_date_df_3hr.parse(paramEndDate));
			cal_end_date_df_3hr.setTime(end_date_df_3hr.parse(paramEndDate));
	
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_beg_date_df.add(Calendar.HOUR, -3);
		cal_beg_date_df_3hr.add(Calendar.HOUR, -4);
		cal_end_date_df_3hr.add(Calendar.HOUR, -7);
		
		prev_beg_date = beg_date_df.format(cal_beg_date_df.getTime());
		prev_beg_date_3hr = beg_date_df_3hr.format(cal_beg_date_df_3hr.getTime());
		prev_end_date_3hr = end_date_df_3hr.format(cal_end_date_df_3hr.getTime());

		
		paramMap.put("beg_date", prev_beg_date);		
		paramMap.put("end_date", paramEndDate);
		paramMap.put("pre_beg_date", prev_beg_date_3hr);		
		paramMap.put("pre_end_date", prev_end_date_3hr);
		paramMap.put("law_code", paramLawCode);		

		rs = kmaAwsDao.obsWaterAwsDataList(paramMap);
		
		return rs;
	}
	@Override
	public KmaAwsDto obsWaterDayAwsDataList(String paramLawCode, String paramEndDate) {
		KmaAwsDto rs = new KmaAwsDto();
		
HashMap<String, Object> paramMap = new HashMap<>();
		
		String prev_beg_date = "";
		String prev_beg_date_3hr = "";
		String prev_end_date_3hr = "";
		SimpleDateFormat beg_date_df = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat beg_date_df_3hr = new SimpleDateFormat("yyyyMMddhh");	
		SimpleDateFormat end_date_df_3hr = new SimpleDateFormat("yyyyMMddhh");	

		Calendar cal_beg_date_df = Calendar.getInstance();
		Calendar cal_beg_date_df_3hr = Calendar.getInstance();
		Calendar cal_end_date_df_3hr = Calendar.getInstance();

		try {
			cal_beg_date_df.setTime(beg_date_df.parse(paramEndDate));
			cal_beg_date_df_3hr.setTime(beg_date_df_3hr.parse(paramEndDate));
			cal_end_date_df_3hr.setTime(end_date_df_3hr.parse(paramEndDate));
	
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_beg_date_df.add(Calendar.DATE, -1);
		cal_beg_date_df_3hr.add(Calendar.DATE, -24);
		cal_end_date_df_3hr.add(Calendar.DATE, -72);
		
		prev_beg_date = beg_date_df.format(cal_beg_date_df.getTime());
		prev_beg_date_3hr = beg_date_df_3hr.format(cal_beg_date_df_3hr.getTime());
		prev_end_date_3hr = end_date_df_3hr.format(cal_end_date_df_3hr.getTime());
		
		paramMap.put("beg_date", prev_beg_date);		
		paramMap.put("end_date", paramEndDate);
		paramMap.put("pre_beg_date", prev_beg_date_3hr);		
		paramMap.put("pre_end_date", prev_end_date_3hr);
		paramMap.put("law_code", paramLawCode);		

		rs = kmaAwsDao.obsWaterAwsDataList(paramMap);
		
		return rs;
	}


	@Override
	public List<KmaInformDto> listKmaInform(String paramSearchTxt,String paramStDate, String paramEndDate,String paramReportType) {
		 List<KmaInformDto> rs=null;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("report_txt", paramSearchTxt);
		paramMap.put("stDate", paramStDate);
		paramMap.put("endDate", paramEndDate);
		rs=kmaInformDao.list(paramMap);
		for (KmaInformDto kmaInformDto : rs) {
			extractRain(kmaInformDto,paramReportType);
		}
		
		
		return rs;
	}
	
	private void extractRain(KmaInformDto paramObj,String paramReportType){
		KmaInformDto rs = paramObj;
		
		List<KmaInformModel> sidoInform = new ArrayList<KmaInformModel>();
		List<KmaInformModel> sigunguInform = new ArrayList<KmaInformModel>();
		
		//List<String> sidoTxt = new ArrayList<String>();
		//List<String> sigunguTxt = new ArrayList<String>();
		String regEx = "\\((.*?)\\)";
	
		Pattern pattern = Pattern.compile(regEx);
	
		List<KmaLocDto> kmaLocation=kmaInformDao.listKmaLocation(null);
		List<String> listExt=new ArrayList<String>();
	//	List<YearAreaCodeDto> sido = areaCodeDao.listSido(null);

	//	List<YearAreaCodeDto> gungu = areaCodeDao.listSiGungu(new HashMap<String, Object>());
	//	kmaInformDao.updateKmaRainTypeInit(new HashMap<String,Object>());
		HashMap<String, String> reportArea = new HashMap<String, String>();
		if (rs != null) {

			rs.setAlertAreaCodes(new ArrayList<String>());
			rs.setWaringAreaCodes(new ArrayList<String>());
			rs.setListAlertWindWave(new ArrayList<String>());
			rs.setListWaringWindWave(new ArrayList<String>());
			String _reportStat = rs.getStat_tm_ef().replace(ViewerSysKeyword.KMA_RPT_TYPE_RAIN,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_RAIN);
			_reportStat = _reportStat.replace(ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON,
					"\r\n" + ViewerSysKeyword.KMA_RPT_TYPE_TYPHOON);
			_reportStat=_reportStat.replace("강풍","\r\n강풍");
			_reportStat=_reportStat.replace("풍랑","\r\n풍랑");
			_reportStat=_reportStat.replace("대설","\r\n대설");
			_reportStat=_reportStat.replace("건조","\r\n건조");
			_reportStat=_reportStat.replace("폭풍","\r\n폭풍");
			_reportStat=_reportStat.replace("지진","\r\n지진");
			_reportStat=_reportStat.replace("한파","\r\n한파");
			_reportStat=_reportStat.replace("황사","\r\n황사");
			_reportStat=_reportStat.replace("폭염","\r\n폭염");
			
			
			String[] _status = _reportStat.split("\r\n");
			
		

			for (String item : _status) {
				//풍랑
				if( item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_WAVE) > -1
						&& (item.indexOf(paramReportType) > -1	)
				){
				//	parseAlertWindWave(rs,kmaLocation,item);
				//	parseWarningWindWave(rs,kmaLocation,item);
					continue;
				}
				
				
				if (   item.indexOf(ViewerSysKeyword.KMA_RPT_TYPE_RAIN) > -1
						&& (item.indexOf(paramReportType) > -1)
						&& 	item.indexOf(ViewerSysKeyword.KMA_RPT_CLEAR) <0)
						

				 {
					String[] statusTxt = item.split(":");

					/*int alarmCount = checkCountRainAlarm(item);
					int warningCount = checkCountRainWarning(item);

					rs.setAlertCount(alarmCount > 0 ? alarmCount : rs.getAlertCount());
					rs.setWarningCount(warningCount > 0 ? warningCount : rs.getWarningCount());
					 */
	
				
					
					if (statusTxt.length > 1) {
						String temp = statusTxt[1];
						
						java.util.Stack<Integer> stack = new java.util.Stack<Integer>();

						List<String> replaceStr=new ArrayList<String>();
						
						for(int i=0;i<temp.length();i++){
							if(temp.charAt(i)=='('){
								stack.add(i);
							
							}else if(temp.charAt(i)==')'){
								int index=stack.pop();
								if(stack.size()>0){
									replaceStr.add(temp.substring(index,i+1));
								}
							}
						}
						
						for (String string : replaceStr) {
							temp=temp.replace(string, "");
						}
						
						
						Matcher regexMatcher = pattern.matcher(temp);
						while (regexMatcher.find()) {
							
							String matchText = regexMatcher.group();
							
						//	sigunguTxt.add(matchText);
							KmaInformModel _sigungu=new KmaInformModel();
							_sigungu.setKamArea(matchText);
							if(item.indexOf(paramReportType) > -1){
								_sigungu.setKmaInfotType(paramReportType);
							}
							
							
							sigunguInform.add(_sigungu);
						
							
						}
						String replaceSido=temp.replaceAll(regEx, "");
						KmaInformModel _sido=new KmaInformModel();
						_sido.setKamArea(replaceSido);
						if(item.indexOf(paramReportType) > -1){
							_sido.setKmaInfotType(paramReportType);
						}
						
						sidoInform.add(_sido);
					//	sidoTxt.add(replaceSido);
						
					}
				}
			}

			if (sidoInform.size() > 0 || sidoInform.size() > 0) {
				
				// 발령 시도
				for (KmaInformModel sidoStr : sidoInform) {
				/*	if(sidoStr.getKamArea().trim().indexOf("울릉도")>-1){
						KmaInformModel temp=new KmaInformModel();
						temp.setKamArea("울릉도");
						temp.setKmaInfotType(sidoStr.getKmaInfotType());
						sigunguInform.add(temp);
						
					}*/
					for (KmaLocDto kmaLocDto : kmaLocation) {
						if(kmaLocDto.getLoc_type().equals("1")){
							String[] spSigungu=kmaLocDto.getLoc_name().split(",");
							for (String spItem : spSigungu) {
								if(sidoStr.getKamArea().trim().indexOf(spItem)>-1){
									if(!listExt.contains(kmaLocDto.getAdm_code())){
									if(sidoStr.getKmaInfotType().equals(paramReportType)&& paramReportType.equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
										
										rs.setAlertCount(rs.getAlertCount()+1);
										
									}
									if(sidoStr.getKmaInfotType().equals(paramReportType)&& paramReportType.equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
										
										rs.setWarningCount(rs.getWarningCount()+1);
										
									}
									listExt.add(kmaLocDto.getAdm_code());
									}
									
								}
							}
						}
						if(kmaLocDto.getAdm_code().equals("99999") || !kmaLocDto.getAdm_code().substring(2,5).equals("000")){
						
							continue;
						}
						
					
						
						
						if (sidoStr.getKamArea().trim().indexOf(kmaLocDto.getLoc_name().substring(0, 3)) > -1
								|| sidoStr.getKamArea().trim().indexOf(kmaLocDto.getShort_name()) > -1
								) {
							reportArea.put(kmaLocDto.getAdm_code(), "Y");
							if(sidoStr.getKmaInfotType().equals(paramReportType)&& paramReportType.equals(ViewerSysKeyword.KMA_RPT_TYPE_YELLOW)){
								if(!rs.getAlertAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getAlertAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
							if(sidoStr.getKmaInfotType().equals(paramReportType)&& paramReportType.equals(ViewerSysKeyword.KMA_RPT_TYPE_RED)){
								if(!rs.getWaringAreaCodes().contains(kmaLocDto.getAdm_code())){
									rs.getWaringAreaCodes().add(kmaLocDto.getAdm_code());
								}
							}
						}
					}
				} // end 발령시도
					// 발령 시군구

				for (KmaInformModel sigunguStr : sigunguInform) {
					List<String> removeSigungu=new ArrayList<String>();
					for (KmaLocDto kmaLocDto : kmaLocation) {
						String[] spSigungu=kmaLocDto.getLoc_name().split(",");
						
						
						if(kmaLocDto.getAdm_code().equals("99999") || kmaLocDto.getAdm_code().substring(2,5).equals("000")){
							continue;
						}
						
						for (String spItem : spSigungu) {
						
							 int endPosition= spItem.length()>5?3:2;
						//	if (yearAreaCodeDto.getSigungu() != null && yearAreaCodeDto.getSigungu() != "") {
								if(sigunguStr.getKamArea().trim().indexOf("제외") > -1){
									if(sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition))>-1){
										removeSigungu.add(kmaLocDto.getAdm_code());
									}
									
								}else if (sigunguStr.getKamArea().trim().indexOf(spItem)>-1||sigunguStr.getKamArea().trim().indexOf(spItem.substring(0, endPosition)) > -1) {
									for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										boolean isBreak=false;
										String[] splitAmdCode=kmaLocDto.getAdm_code().split(",");
										for (String spCode : splitAmdCode) {
											if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(spCode.substring(0, 2))){
												reportArea.put(spCode, "N");
												isBreak=true;
											}
										
										}
										if(isBreak)break;
										
									}
								
								}

						}//end for spSigungu
					}
					if(removeSigungu.size()>0){
						//시도에서 제외 시군구 이외의 시군구는 모두 넣는다.ㅣ
						for (KmaLocDto kmaLocDto : kmaLocation) {
						
							if(!kmaLocDto.getAdm_code().trim().substring(2,5).equals("000")&&kmaLocDto.getAdm_code().trim().substring(0,2).equals(removeSigungu.get(0).trim().substring(0,2))){
								if(!removeSigungu.contains(kmaLocDto.getAdm_code())){
									
									//for (Map.Entry<String, String> entry : reportArea.entrySet()) {
										//if(entry.getValue().equals("Y") && entry.getKey().substring(0,2).equals(kmaLocDto.getAdm_code().substring(0, 2))){
											reportArea.put(kmaLocDto.getAdm_code(), "N");
										//	break;
									//	}
									//}
								}
							}
						}
						//removeSigungu.contains(arg0)
						//System.out.println("!");
					}
				}
				
		
				
				
				List<String> listSido = new ArrayList<String>();
				List<String> listGungu = new ArrayList<String>();
				List<String> listSea = new ArrayList<String>();
				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					if (entry.getKey() != "" && entry.getKey().length() > 1) {
						if (entry.getValue().equals("N")) {
							
							listGungu.add(entry.getKey());
						}
						
					}
				}

				for (Map.Entry<String, String> entry : reportArea.entrySet()) {
					boolean isUse = false;
					for (String item : listGungu) {
						if (entry.getKey().substring(0, 2).equals(item.substring(0, 2))) {
							isUse = true;
							break;
						}
					}
					if (!isUse) {
						listSido.add(entry.getKey());
					}
				}

				listGungu.addAll(listExt);
				rs.setReportSido(listSido);
				rs.setReportGungu(listGungu);
				

			} // end if
			
			
		}
		
	}
	
	
}
