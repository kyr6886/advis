package com.noaa.nema.viewer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.area.dao.IAreaCodeDao;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.asos.dao.IObsAsosDao;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.service.ICommonService;
import com.noaa.nema.viewer.service.ITyphoonCastInfoService;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonCastInfoDao;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonDamageDao;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonWorldInfoDao;
import com.noaa.nema.viewer.typhoon.dao.IYearDmeTyphoonDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonDamageDto;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeConditionDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeImagesDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeConditionDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;

@Service("typhoonCastInfoService")
public class TyphoonCatInfoServiceImpl implements ITyphoonCastInfoService {

	@Autowired
	ITyphoonCastInfoDao typhoonCastInfoDao;
	
	@Autowired
	ITyphoonWorldInfoDao typhoonWorldInfoDao;

	@Autowired
	private IYearDmeDao yearDmedDao;
	@Autowired
	private IYearDmeConditionDao yearDmeConditionDao;
	
	@Autowired
	private IAreaCodeDao areaCodeDao;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IYearDmeImagesDao yearDmeImagesDao;
	
	@Autowired
	private IYearDmeTyphoonDao yearDmeTyphoonDao;
	@Autowired
	private IObsAsosDao obsAsosDao;
	@Autowired
	private ITyphoonDamageDao typhoonDamageDao;
	@Override
	public List<TyphoonCastInfoDto> currentTyphoonInfoList(String paramYear, String paramMonth, String paramSeq) {

		List<TyphoonCastInfoDto> prevRs = null;
		List<TyphoonCastInfoDto> currentRs = null;
		List<TyphoonCastInfoDto> nextRs = null;
		List<TyphoonCastInfoDto> rs = null;
		
		String rebuild_date = paramYear + paramMonth + "01";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_month",paramMonth);
		paramMap.put("typ_seq", paramSeq);
		currentRs = typhoonCastInfoDao.listTyphoonByCurrentTime(paramMap);
		if(currentRs==null || currentRs.size()==0){
			currentRs=typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);
		}
		
		try {
			cal.setTime(dateFormat.parse(rebuild_date));
			cal.add(Calendar.MONTH, -1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		String preDate = dateFormat.format(cal.getTime());
		paramMap.put("beg_year", preDate.substring(0, 4));
		paramMap.put("beg_month", preDate.substring(4, 6));
		paramMap.put("typ_seq", paramSeq);
		
		prevRs = typhoonCastInfoDao.listTyphoonByCurrentTime(paramMap);

		if(prevRs==null || prevRs.size()==0){
			prevRs=typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);
		}
		
		
		
		try {
			cal.setTime(dateFormat.parse(rebuild_date));
			cal.add(Calendar.MONTH, 1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	

		String aftDate = dateFormat.format(cal.getTime());
		paramMap.put("beg_year", aftDate.substring(0, 4));
		paramMap.put("beg_month", aftDate.substring(4, 6));
		paramMap.put("typ_seq", paramSeq);

		nextRs = typhoonCastInfoDao.listTyphoonByCurrentTime(paramMap);
		if(nextRs==null || nextRs.size()==0){
			nextRs=typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);
		}
		rs = new ArrayList<>();

	
		if (prevRs.size() > 0) {
			rs.addAll(prevRs);
		}
		rs.addAll(currentRs);
		if (nextRs.size() > 0) {
			rs.addAll(nextRs);
		}
	
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> pastTyphoonInfoList(String paramYear, String paramMonth, String paramSeq) {

		List<TyphoonCastInfoDto> prevRs = null;
		List<TyphoonCastInfoDto> nextRs = null;
		List<TyphoonCastInfoDto> rs = null;

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_month", paramMonth);
		paramMap.put("typ_seq", paramSeq);

		prevRs = typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);

		String rebuild_date = paramYear + paramMonth + "01";

		SimpleDateFormat after_df = new SimpleDateFormat("yyyyMMdd");

		Calendar cal_after_month = Calendar.getInstance();
		try {
			cal_after_month.setTime(after_df.parse(rebuild_date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_after_month.add(Calendar.MONTH, 1);

		String paramNewDate = after_df.format(cal_after_month.getTime());

		String paramNextYear = paramNewDate.substring(0, 4);
		String paramNextMonth = paramNewDate.substring(4, 6);

		paramMap.put("beg_year", paramNextYear);
		paramMap.put("beg_month", paramNextMonth);
		paramMap.put("typ_seq", paramSeq);

		nextRs = typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);
		rs = new ArrayList<>();

		rs.addAll(prevRs);

		if (nextRs.size() > 0) {
			rs.addAll(nextRs);
		}

		return rs;

	}

	@Override
	public List<YearDmeDto> listTyphoonCastDmeArea(String paramStDate, String paramEndDate) {
		List<YearDmeDto> rsSido = null;
		List<YearDmeDto> rsGungu = null;
		List<YearAreaCodeDto> sigungu = null;

		List<YearDmeDto> rs = null;
		String law_code = "";

		SimpleDateFormat prev_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_prev_month = Calendar.getInstance();
		try {
			cal_prev_month.setTime(prev_df.parse(paramStDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_prev_month.add(Calendar.DATE, -1);

		String paramNewStDate = prev_df.format(cal_prev_month.getTime());

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", law_code);
		paramMap.put("beg_date", paramNewStDate);
		paramMap.put("end_date", paramEndDate);

		rsSido = yearDmedDao.listSidoTot(paramMap);
		rsGungu = yearDmedDao.listGunguTot(paramMap);
		sigungu = areaCodeDao.listSiGungu(null);

		for (YearDmeDto dme : rsSido) {
			for (YearAreaCodeDto code : sigungu) {
				if (dme.getSigungu_code().substring(0, 2).equals(code.getCode().substring(0, 2))) {
					dme.setSido(code.getSido());
					if (dme.getSigungu_code().equals(code.getCode())) {
						dme.setSigungu(code.getSigungu());
					}

				}
			}
		}

		for (YearDmeDto dme : rsGungu) {
			for (YearAreaCodeDto code : sigungu) {
				if (dme.getSigungu_code().equals(code.getCode())) {
					dme.setSido(code.getSido());
					dme.setSigungu(code.getSigungu());
				}
			}
		}
		rs = new ArrayList<>();
		rs.addAll(rsSido);
		rs.addAll(rsGungu);

		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> currentSimilarTyphoonListByPeriod(String paramStDate, String paramEndDate,
			String paramSeq) {

		List<TyphoonCastInfoDto> rs = null;

		SimpleDateFormat prev_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_prev_month = Calendar.getInstance();
		try {
			cal_prev_month.setTime(prev_df.parse(paramStDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_prev_month.add(Calendar.MONTH, -1);

		SimpleDateFormat after_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_after_month = Calendar.getInstance();
		try {
			cal_after_month.setTime(after_df.parse(paramEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal_after_month.add(Calendar.MONTH, 1);

		String paramNewStDate = prev_df.format(cal_prev_month.getTime());
		String paramNewEndDate = after_df.format(cal_after_month.getTime());

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramNewStDate);
		paramMap.put("end_date", paramNewEndDate);

		rs = typhoonCastInfoDao.listCurrentSimialarTyphoonByPeriod(paramMap);

		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> pastSimilarTyphoonListByPeriod(String paramStDate, String paramEndDate,
			String paramSeq) {

		List<TyphoonCastInfoDto> rs = null;

		SimpleDateFormat prev_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_prev_month = Calendar.getInstance();
		try {
			cal_prev_month.setTime(prev_df.parse(paramStDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_prev_month.add(Calendar.MONTH, -1);

		SimpleDateFormat after_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_after_month = Calendar.getInstance();
		try {
			cal_after_month.setTime(after_df.parse(paramEndDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal_after_month.add(Calendar.MONTH, 1);

		String paramNewStDate = prev_df.format(cal_prev_month.getTime());
		String paramNewEndDate = after_df.format(cal_after_month.getTime());

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramNewStDate);
		paramMap.put("end_date", paramNewEndDate);

		rs = typhoonCastInfoDao.listPastSimialarTyphoonByPeriod(paramMap);

		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> listTyphoonName(String paramStYear, String paramEndYear) {

		List<TyphoonCastInfoDto> rs = null;
		
		paramStYear = (paramStYear.length() > 4) ? paramStYear.substring(0,4) : paramStYear;
		paramEndYear = (paramEndYear.length() > 4) ? paramEndYear.substring(0,4) : paramEndYear;

		HashMap<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("beg_year", paramStYear);
		paramMap.put("end_year", paramEndYear);

		rs = typhoonCastInfoDao.listTyphoonName(paramMap);

		return rs;
	}

	@Override
	public TyphoonCastInfoDto detailCurrentTyphoon() {
		TyphoonCastInfoDto rs = null;
		TyphoonCastInfoDto lastTyphoon = typhoonCastInfoDao.last();

		Calendar yesterDay = new GregorianCalendar();
		yesterDay.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 날짜 포맷
		float yesterDayNum = Float.parseFloat(sdf.format(yesterDay.getTime()) + "0000"); // String으로
																							// 저장

		Calendar toDay = new GregorianCalendar();
		float toDayNum = Float.parseFloat(sdf.format(toDay.getTime()) + "2359"); // String으로
		

		if (lastTyphoon != null) {
			
			float lastTime = Float.parseFloat(lastTyphoon.getTm_fc());
			//todo:배포시에 주석 풀것
			if(lastTime>=yesterDayNum && lastTime<=toDayNum){
			rs = lastTyphoon;
			}
		}
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> listSimilarTyphoonByPosition(String paramYear, String paramMonth,
			int paramTyphoonSeq) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("typ_seq", paramTyphoonSeq);
		List<TyphoonCastInfoDto> rs = new ArrayList<TyphoonCastInfoDto>();

		List<TyphoonCastInfoDto> targetTyphoon = null;
		if (Integer.parseInt(paramYear + paramMonth) > ViewerSysKeyword.TYPHOON_PAST_DATE_ST) {
			targetTyphoon = currentTyphoonInfoList(paramYear, paramMonth, Integer.toString(paramTyphoonSeq));
		} else {
			targetTyphoon = pastTyphoonInfoList(paramYear, paramMonth, Integer.toString(paramTyphoonSeq));
			;
		}

		// int positionSize=targetTyphoon.size()/5;
		if (targetTyphoon.size() < 10)
			return rs;

		// List<TyphoonCastInfoDto>
		// listInArea=typhoonCastInfoDao.listTyphoonInTargetArea(paramMap);
		List<TyphoonCastInfoDto> simiralTyphoon = new ArrayList<TyphoonCastInfoDto>();
		for (int i = 0; i < targetTyphoon.size(); i++) {
			
			// and typ_lon::numeric>=122 and typ_lat>31 and typ_len<133 and
			// typ_lat<42
			if (Float.parseFloat(targetTyphoon.get(i).getTyp_lon()) > 120
					&& Float.parseFloat(targetTyphoon.get(i).getTyp_lat()) > 31
					&& Float.parseFloat(targetTyphoon.get(i).getTyp_lon()) < 135
					&& Float.parseFloat(targetTyphoon.get(i).getTyp_lat()) < 42) {

				paramMap.clear();
				// int z=i+positionSize-1;
				// if(z>=targetTyphoon.size()){
				// z=targetTyphoon.size()-1;
				// }
				String beg_date = targetTyphoon.get(i).getTm_fc();
				// String end_date=beg_date;
				paramMap.put("beg_year", paramYear);
				paramMap.put("typ_seq", paramTyphoonSeq);
				paramMap.put("beg_date", beg_date);
				// paramMap.put("end_date",end_date);
				// for (TyphoonCastInfoDto typhoonCastInfoDto : listInArea) {
				// paramMap.put("similar_typ_seq",typhoonCastInfoDto.getTyp_seq());
				// paramMap.put("similar_beg_year",typhoonCastInfoDto.getBeg_year());
				List<TyphoonCastInfoDto> _temp = typhoonCastInfoDao.listTyphoonInTargetArea(paramMap);
				if (_temp != null && _temp.size() > 0) {
					for (TyphoonCastInfoDto typhoonCastInfoDto2 : _temp) {
						setSimilarTyphoonCount(simiralTyphoon, typhoonCastInfoDto2,i+1);
					}

				}
				// }

			}
		}

		for (TyphoonCastInfoDto typhoonCastInfoDto : simiralTyphoon) {
			if (typhoonCastInfoDto.getCountPosition() >= 10) {

				String seq = typhoonCastInfoDto.getTyp_seq().split("\\.")[0];
				List<TyphoonCastInfoDto> temp = currentTyphoonInfoList(typhoonCastInfoDto.getBeg_year(),
						typhoonCastInfoDto.getTm_fc().substring(4, 6), seq);
				if (temp != null && temp.size() > 0) {
					typhoonCastInfoDto.setBeg_date(temp.get(0).getTm_fc().substring(0, 8));
					typhoonCastInfoDto.setEnd_date(temp.get(temp.size() - 1).getTm_fc().substring(0, 8));
					typhoonCastInfoDto.setListPosition(temp);
					rs.add(typhoonCastInfoDto);
				}

			}
		}
	
		//재해연보 조회를 위해서는 태풍 시작 종료일을 태퐁정보 테이블 데이터로 다시 맵핑해야 한다.
		List<YearDmeTyphoonDto> yearDmeTyphoons=listAllYearDmeTyphoon();
		
		for (TyphoonCastInfoDto typhoonCastInfoDto : rs) {
			YearDmeTyphoonDto mapping= typhoonDateMapping(typhoonCastInfoDto.getListPosition(), yearDmeTyphoons);
			typhoonCastInfoDto.setBeg_date(mapping.getBeg_date());
			typhoonCastInfoDto.setEnd_date(mapping.getEnd_date());
		}
		
		
		
		setListSimilarTyphoonAndDamage(rs);

		return rs;
	}

	private void setSimilarTyphoonCount(List<TyphoonCastInfoDto> paramSimiralTyphoon, TyphoonCastInfoDto paramTarget,int n) {
		boolean isUse = false;
		for (TyphoonCastInfoDto typhoonCastInfoDto : paramSimiralTyphoon) {
			if (typhoonCastInfoDto.getBeg_year().equals(paramTarget.getBeg_year())
					&& typhoonCastInfoDto.getTyp_seq().equals(paramTarget.getTyp_seq())) {
				isUse = true;

				typhoonCastInfoDto.setCountPosition(typhoonCastInfoDto.getCountPosition() +n);
			}
		}

		if (!isUse) {
			paramTarget.setCountPosition(1);
			paramSimiralTyphoon.add(paramTarget);
		}
	}


	
	private void setListSimilarTyphoonAndDamage(List<TyphoonCastInfoDto> paramSimilarTyphoons) {
		
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		for (TyphoonCastInfoDto typhoonCastInfoDto : paramSimilarTyphoons) {
			String paramDamageCode = ViewerSysKeyword.DME_CODE_TYPHOON + "," + ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
			String stDate = typhoonCastInfoDto.getBeg_date().substring(0, 8);
			String endDate = typhoonCastInfoDto.getEnd_date().substring(0, 8);
			
			if(typhoonCastInfoDto.getTyp_name()!=null&&typhoonCastInfoDto.getTyp_name().indexOf(",")>-1){
				typhoonCastInfoDto.setTyp_name(typhoonCastInfoDto.getTyp_name().split(",")[0]);
			}
			
			typhoonCastInfoDto.setMoneyDamage(commonService.listDamageMoneyTop10(null, stDate, endDate, paramDamageCode,typhoonCastInfoDto.getTyp_name()));
			typhoonCastInfoDto.setPersonDamage(commonService.listDamagePersonTop10(null, stDate, endDate, paramDamageCode,typhoonCastInfoDto.getTyp_name()));
			
			paramMap.clear();
			paramMap.put("beg_date", stDate);
			paramMap.put("end_date", endDate);
			paramMap.put("damage_type", ViewerSysKeyword.DME_CODE_TYPHOON);
			typhoonCastInfoDto.setDetailCondition(yearDmeConditionDao.detail(paramMap));
			if(typhoonCastInfoDto.getDetailCondition()!=null){
				YearDmeConditionDto item=typhoonCastInfoDto.getDetailCondition();
				if(item.getCauses()!=null){
					item.setCauses(item.getCauses().replace("\r\n","<br>"));
					item.setCauses(item.getCauses().replace("\n","<br>"));
				}
				if(item.getDamages()!=null){
					item.setDamages(item.getDamages().replace("\r\n","<br>"));
					item.setDamages(item.getDamages().replace("\n","<br>"));
				}
				
			}
			
			typhoonCastInfoDto.setListImages(yearDmeImagesDao.list(paramMap));
			typhoonCastInfoDto.setDetailSummary(commonService.detailSummary(stDate, endDate, paramDamageCode));
			if(typhoonCastInfoDto.getDetailSummary()!=null){
			typhoonCastInfoDto.getDetailSummary().setAsosInfo(obsAsosDao.maxObsRnDay(paramMap));
			}
			
		}

	}

	@Override
	public List<TyphoonCastInfoDto> listSimilarTyphoonByForcast(String paramYear, String paramMonth,
			int paramTyphoonSeq, int paramTyphoonTmSeq) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("typ_seq", paramTyphoonSeq);
		paramMap.put("tm_seq", paramTyphoonTmSeq);
		List<TyphoonCastInfoDto> rs = new ArrayList<TyphoonCastInfoDto>();

		List<TyphoonCastInfoDto> targetTyphoon = null;
		List<TyphoonCastInfoDto> convertTolist = new ArrayList<TyphoonCastInfoDto>();
		if (Integer.parseInt(paramYear + paramMonth) > ViewerSysKeyword.TYPHOON_PAST_DATE_ST) {
			targetTyphoon = currentTyphoonInfoList(paramYear, paramMonth, Integer.toString(paramTyphoonSeq));
		} else {
			targetTyphoon = pastTyphoonInfoList(paramYear, paramMonth, Integer.toString(paramTyphoonSeq));
			;
		}

		TyphoonCastInfoDto targetForcast = null;
		int index=0;
	//	int forCastIndex=0;
		for (TyphoonCastInfoDto typhoonCastInfoDto : targetTyphoon) {
		//	if(index%3==0)
				convertTolist.add(typhoonCastInfoDto);
			
			if (((int) Float.parseFloat(typhoonCastInfoDto.getTm_seq())) == paramTyphoonTmSeq) {
				targetForcast = typhoonCastInfoDto;
				break;
				
			}
			
		
			
			index++;
		//	forCastIndex++;
			
		}
		int forcastCount=0;
		// int positionSize=targetTyphoon.size()/5;
		if (targetForcast == null)
			return rs;

		if(targetForcast.getFt1_lon()!=null&&targetForcast.getFt1_lat()!=null&&Float.parseFloat(targetForcast.getFt1_lon())>0 &&Float.parseFloat(targetForcast.getFt1_lon())<128&& Float.parseFloat(targetForcast.getFt1_lat())>0){
			TyphoonCastInfoDto ft1 = new TyphoonCastInfoDto();
			ft1.setTyp_lon(targetForcast.getFt1_lon());
			ft1.setTyp_lat(targetForcast.getFt1_lat());
			convertTolist.add(ft1);
			forcastCount++;
		}
		if(targetForcast.getFt2_lon()!=null&&targetForcast.getFt2_lat()!=null&&Float.parseFloat(targetForcast.getFt2_lon())>0 && Float.parseFloat(targetForcast.getFt2_lon())<128&&Float.parseFloat(targetForcast.getFt2_lat())>0){
			TyphoonCastInfoDto ft2 = new TyphoonCastInfoDto();
			ft2.setTyp_lon(targetForcast.getFt2_lon());
			ft2.setTyp_lat(targetForcast.getFt2_lat());
			convertTolist.add(ft2);
			forcastCount++;
		}
		if(targetForcast.getFt3_lon()!=null&&targetForcast.getFt3_lat()!=null&&Float.parseFloat(targetForcast.getFt3_lon())>0 && Float.parseFloat(targetForcast.getFt3_lon())<128&&Float.parseFloat(targetForcast.getFt3_lat())>0){
			TyphoonCastInfoDto ft3 = new TyphoonCastInfoDto();
			ft3.setTyp_lon(targetForcast.getFt3_lon());
			ft3.setTyp_lat(targetForcast.getFt3_lat());
			convertTolist.add(ft3);
			forcastCount++;
		}
		if(targetForcast.getFt4_lon()!=null&&targetForcast.getFt4_lat()!=null&&Float.parseFloat(targetForcast.getFt4_lon())>0 &&Float.parseFloat(targetForcast.getFt4_lon())<128&& Float.parseFloat(targetForcast.getFt4_lat())>0){
			TyphoonCastInfoDto ft4 = new TyphoonCastInfoDto();
			ft4.setTyp_lon(targetForcast.getFt4_lon());
			ft4.setTyp_lat(targetForcast.getFt4_lat());
			convertTolist.add(ft4);
			forcastCount++;
		}
		TyphoonCastInfoDto ft5 = new TyphoonCastInfoDto();
		if(targetForcast.getFt5_lon()!=null&&targetForcast.getFt5_lat()!=null&&Float.parseFloat(targetForcast.getFt5_lon())>0 &&Float.parseFloat(targetForcast.getFt5_lon())<128&& Float.parseFloat(targetForcast.getFt5_lat())>0){
			ft5.setTyp_lon(targetForcast.getFt5_lon());
			ft5.setTyp_lat(targetForcast.getFt5_lat());
			convertTolist.add(ft5);
			forcastCount++;
		}
	
		int endCount=Float.parseFloat(convertTolist.get(convertTolist.size()-1).getTyp_lat())<38?convertTolist.size()-1:convertTolist.size()-forcastCount;
		int ftCount=0;
		List<TyphoonCastInfoDto> simiralTyphoon = new ArrayList<TyphoonCastInfoDto>();
		List<TyphoonCastInfoDto> pointArea = new ArrayList<TyphoonCastInfoDto>();
		List<TyphoonCastInfoDto> first = new ArrayList<TyphoonCastInfoDto>();
		List<TyphoonCastInfoDto> second = new ArrayList<TyphoonCastInfoDto>();
		float startX=Float.parseFloat(convertTolist.get(0).getTyp_lon());
		float startY=Float.parseFloat(convertTolist.get(0).getTyp_lat());
		float middleX=Float.parseFloat(convertTolist.get(convertTolist.size()/2).getTyp_lon());
		float middleY=Float.parseFloat(convertTolist.get(convertTolist.size()/2).getTyp_lat());
		float endX=Float.parseFloat(convertTolist.get(convertTolist.size()-2).getTyp_lon());
		float endY=Float.parseFloat(convertTolist.get(convertTolist.size()-2).getTyp_lat());
		
		for(int i=0;i<3;i++){
			float leftBottomX=0f;
			float rightBottomX=0f;
			float rightBottomY=0f;
			float rightTopY=0f;
			if(i==0){
				 leftBottomX=startX-1.5f;
				 rightBottomX=startX+1.5f;
				 rightBottomY=startY;
				 rightTopY=startY+5f;
			}else if(i==1){
				 leftBottomX=middleX-1f;
				 rightBottomX=middleX+1.5f;
				 rightBottomY=middleY-2.5f;
				 rightTopY=middleY+2.5f;
			}else{
				 leftBottomX=endX-0.5f;
				 rightBottomX=endX+1.5f;
				 rightBottomY=endY-1.5f;
				 rightTopY=endY+1.5f;
			}
			
			paramMap.clear();
			paramMap.put("beg_year", paramYear);
			paramMap.put("typ_seq", paramTyphoonSeq);
			paramMap.put("typ_sp",targetForcast.getTyp_sp()==null?0:Float.parseFloat(targetForcast.getTyp_sp()));
			paramMap.put("typ_ws",targetForcast.getTyp_ws()==null?0:Float.parseFloat(targetForcast.getTyp_ws()));
			paramMap.put("typ_ps",targetForcast.getTyp_ws()==null?0:Float.parseFloat(targetForcast.getTyp_ps()));
			paramMap.put("leftBottomX",leftBottomX);
			paramMap.put("rightBottomX", rightBottomX);
			paramMap.put("rightBottomY",rightBottomY);
			paramMap.put("rightTopY",rightTopY );
			if(i==0||i==1)
				first.addAll(typhoonCastInfoDao.listTyphoonInTargetAreaByForcast(paramMap));
			else
				second.addAll(typhoonCastInfoDao.listTyphoonInTargetAreaByForcast(paramMap));
		
		}
		
		for (TyphoonCastInfoDto secondItem : second) {
			
			for (TyphoonCastInfoDto firstItem : first) {
				if(secondItem.getTyp_name().equals(firstItem.getTyp_name())&&secondItem.getTyp_seq().equals(firstItem.getTyp_seq()) ){
					pointArea.add(secondItem);
					break;
				}
				
			}
		}
		
		
		if(pointArea==null || pointArea.size()==0) return rs;
		
		List<String> paramTypInfo=new ArrayList<String>();
		for (TyphoonCastInfoDto typhoonCastInfoDto : pointArea) {
			if(typhoonCastInfoDto.getTyp_name()!=null &&typhoonCastInfoDto.getBeg_year()!=null){
				String keyName=typhoonCastInfoDto.getTyp_name()+"-"+typhoonCastInfoDto.getBeg_year();
				paramTypInfo.add(keyName);
			}
			
		}
		
		
		paramMap.clear();
		paramMap.put("typNmae_year", paramTypInfo);
		pointArea=typhoonCastInfoDao.listSearchTyphoonsByNameYear(paramMap);
		

		//List<TyphoonCastInfoDto> validSimilars = typhoonCastInfoDao.listTyphoonSearchByXY(paramMap);
		 HashMap<String, Float> map = new HashMap<>(); 
		 index=0;
		for (TyphoonCastInfoDto convertTyphoon : convertTolist) {
			//if(convertTolist.size()/2<=index){
			for (TyphoonCastInfoDto typhoonCastInfoDto : pointArea) {
				if(Float.parseFloat(convertTyphoon.getTyp_lat())>38) break;
				
				float x1=Float.parseFloat(convertTyphoon.getTyp_lon()) -(1.5f);
				float x2=Float.parseFloat(convertTyphoon.getTyp_lon()) +(1.5f);
				float y1=Float.parseFloat(convertTyphoon.getTyp_lat())-(2f);
				float y2=Float.parseFloat(convertTyphoon.getTyp_lat()) +(2f);
				float tx=Float.parseFloat(typhoonCastInfoDto.getTyp_lon());
				float ty=Float.parseFloat(typhoonCastInfoDto.getTyp_lat());
				String keyName=typhoonCastInfoDto.getTyp_name()+"-"+typhoonCastInfoDto.getTm_fc().substring(0,4)+"-"+typhoonCastInfoDto.getTyp_seq();
				if(typhoonCastInfoDto.getTyp_name()!=null && !typhoonCastInfoDto.getTyp_name().isEmpty() ){
					float m=Math.abs(Float.parseFloat(convertTyphoon.getTyp_lon())-tx);
					if(ty>25 && ty<45 ){
						if(!map.containsKey(keyName)){
							map.put(keyName,m);
						}else{
							float t=map.get(keyName);
							map.put(keyName,t+=m);
						}
					}
				}
			//}
			}
			index++;
		}
		
		for(Map.Entry<String, Float> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String[] nameAndYear=key.split("-");
		   if( entry.getValue()>0){
			   TyphoonCastInfoDto temp=new TyphoonCastInfoDto();
			   temp.setListPosition(new ArrayList<TyphoonCastInfoDto>());
			   temp.setTyp_name(nameAndYear[0]);
			   temp.setBeg_year(nameAndYear[1]);
			   temp.setTyp_seq(nameAndYear[2]);
			   temp.setDistance(entry.getValue());
			   for (TyphoonCastInfoDto typhoonCastInfoDto : pointArea) {
				   String name=typhoonCastInfoDto.getTyp_name();
				   String year=typhoonCastInfoDto.getTm_fc().substring(0,4);
				   if(name.equals(nameAndYear[0]) && year.equals(nameAndYear[1])){
					   temp.getListPosition().add(typhoonCastInfoDto);
				   }
				   if(temp.getListPosition().size()>0){
					   
					   Collections.sort(temp.getListPosition(), new Comparator<TyphoonCastInfoDto>() {
						   @Override
						   public int compare(TyphoonCastInfoDto u1, TyphoonCastInfoDto u2) {
						     return u1.getTm_fc().compareTo(u2.getTm_fc());
						   }
						 });
					   
					   temp.setBeg_date(temp.getListPosition().get(0).getTm_fc().substring(0, 8));
					   temp.setEnd_date(temp.getListPosition().get(temp.getListPosition().size() - 1).getTm_fc().substring(0, 8));
				   }
				   
			   }
			   if(temp.getListPosition().size()>0&&Float.parseFloat(temp.getListPosition().get(temp.getListPosition().size()-1).getTyp_lat())>34
					
					   ){
			   simiralTyphoon.add(temp);
			   }
		   };

		    // do what you have to do here
		    // In your case, another loop.
		}
		
	//	int startIndex=convertTolist.size()<3?0:convertTolist.size()/3;
	/*	for (int i =0; i < convertTolist.size(); i++) {
			
			paramMap.clear();

			if (convertTolist.get(i).getTyp_lon() != null && convertTolist.get(i).getTyp_lat() != null && Float.parseFloat(convertTolist.get(i).getTyp_lon())>0 && Float.parseFloat(convertTolist.get(i).getTyp_lat())>0) {
				ftCount++;
				// String end_date=beg_date;
				paramMap.put("beg_year", paramYear);
				paramMap.put("typ_seq", paramTyphoonSeq);
				paramMap.put("typ_sp",targetForcast.getTyp_sp()==null?0:Float.parseFloat(targetForcast.getTyp_sp()));
				paramMap.put("typ_ws",targetForcast.getTyp_ws()==null?0:Float.parseFloat(targetForcast.getTyp_ws()));
				paramMap.put("leftBottomX", Float.parseFloat(convertTolist.get(i).getTyp_lon()) -0.5);
				paramMap.put("rightBottomX", Float.parseFloat(convertTolist.get(i).getTyp_lon()) + 0.5);
				paramMap.put("rightBottomY", Float.parseFloat(convertTolist.get(i).getTyp_lat())-2.5);
				paramMap.put("rightTopY", Float.parseFloat(convertTolist.get(i).getTyp_lat())+2.5 );

				List<TyphoonCastInfoDto> _temp = typhoonCastInfoDao.listTyphoonInTargetAreaByForcast(paramMap);
				if (_temp != null && _temp.size() > 0) {
					for (TyphoonCastInfoDto typhoonCastInfoDto2 : _temp) {
						
						setSimilarTyphoonCount(simiralTyphoon, typhoonCastInfoDto2,ftCount*3);
					
					}

				}
				// }
			}
			
		}*/
		
		if(simiralTyphoon.size()==0){
			return rs;
		}
		
		rs=simiralTyphoon;
		/*int avgCount=0;
		int totalCount=0;
		for (TyphoonCastInfoDto typhoonCastInfoDto : simiralTyphoon) {
			if(typhoonCastInfoDto.getCountPosition()>0){
				totalCount+=typhoonCastInfoDto.getCountPosition();
				avgCount++;
			}
		}
		
		totalCount=totalCount/avgCount;

		for (TyphoonCastInfoDto typhoonCastInfoDto : simiralTyphoon) {
			if (typhoonCastInfoDto.getCountPosition()>0) {

				String seq = typhoonCastInfoDto.getTyp_seq().split("\\.")[0];
				List<TyphoonCastInfoDto> temp = currentTyphoonInfoList(typhoonCastInfoDto.getBeg_year(),
						typhoonCastInfoDto.getTm_fc().substring(4, 6), seq);
				if (temp != null && temp.size() > 0  ) {
					typhoonCastInfoDto.setBeg_date(temp.get(0).getTm_fc().substring(0, 8));
					typhoonCastInfoDto.setEnd_date(temp.get(temp.size() - 1).getTm_fc().substring(0, 8));
					typhoonCastInfoDto.setListPosition(temp);
					rs.add(typhoonCastInfoDto);
				}

			}
		}
		*/
		//재해연보 조회를 위해서는 태풍 시작 종료일을 태퐁정보 테이블 데이터로 다시 맵핑해야 한다.
		//List<YearDmeTyphoonDto> yearDmeTyphoons=listAllYearDmeTyphoon();
		
		List<TyphoonCastInfoDto> temp=new ArrayList<TyphoonCastInfoDto>();
		Collections.sort(rs, new Comparator<TyphoonCastInfoDto>() {
		    @Override
		    public int compare(TyphoonCastInfoDto lhs, TyphoonCastInfoDto rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return lhs.getDistance() > rhs.getDistance() ? -1 : (lhs.getDistance() < rhs.getDistance()) ? 1 : 0;
		    }
		});
		
		/*int i=0;
		for (TyphoonCastInfoDto typhoonCastInfoDto : rs) {
			if(i<10){
				YearDmeTyphoonDto mapping= typhoonDateMapping(typhoonCastInfoDto.getListPosition(), yearDmeTyphoons);
				typhoonCastInfoDto.setBeg_date(mapping.getBeg_date());
				typhoonCastInfoDto.setEnd_date(mapping.getEnd_date());
				temp.add(typhoonCastInfoDto);
				i++;
			}
		}
		
		
	   rs=temp;*/
		
		setListSimilarTyphoonAndDamage(rs);
		List<TyphoonCastInfoDto> listTyp30Info=typhoonCastInfoDao.listLat30Info();
		
		for (TyphoonCastInfoDto typhoonCastInfoDto : rs) {
			String yyyymm=typhoonCastInfoDto.getEnd_date().substring(0,6);
		
			for (TyphoonCastInfoDto typ30Info : listTyp30Info) {
				String ym=typ30Info.getTm_fc().substring(0,4);
				if(yyyymm.substring(0,4).equals(ym) && typhoonCastInfoDto.getTyp_name().equals(typ30Info.getTyp_name())){
					typhoonCastInfoDto.setTyp_ps(typ30Info.getTyp_ps());
					typhoonCastInfoDto.setTyp_ws(typ30Info.getTyp_ws());
					typhoonCastInfoDto.setTyp_sp(typ30Info.getTyp_sp());
					typhoonCastInfoDto.setTyp_15(typ30Info.getTyp_15());
					typhoonCastInfoDto.setPw_kor(typ30Info.getPw_kor());
					typhoonCastInfoDto.setTyp_size_kor(typ30Info.getTyp_size_kor());
					break;
				}
			}
		}
		
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> worldTyphoonHistoryList(String paramStDate, String paramEndDate,
			String paramNatinalWeatherName, String paramTyphoonName) {
		
		paramStDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate;
		paramEndDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate;
		paramTyphoonName = (paramTyphoonName.length() <= 2) ? paramTyphoonName : paramTyphoonName.substring(0,2);
		List<TyphoonCastInfoDto> rs = null;
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("typ_name", paramTyphoonName);
		paramMap.put("national_weather_nm", paramNatinalWeatherName);

		rs = typhoonWorldInfoDao.worldTyphoonHistoryList(paramMap);
		
		
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> worldTyphoonForecastList(String paramStDate, String paramEndDate,
			String paramNatinalWeatherName, String paramTyphoonName) {
		paramStDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate;
		paramEndDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate;
		
		List<TyphoonCastInfoDto> rs = null;
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("typ_name", paramTyphoonName);
		paramMap.put("national_weather_nm", paramNatinalWeatherName);

		rs = typhoonWorldInfoDao.worldTyphoonForecastList(paramMap);
	
		
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> worldTyphoonForecastByPeriod(String paramStDate, String paramEndDate) {
		List<TyphoonCastInfoDto> rs=null;
		paramStDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate;
		paramEndDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		rs = typhoonWorldInfoDao.worldTyphoonForecastList(paramMap);
		return rs;
	}

	@Override
	public List<TyphoonCastInfoDto> listTyphoonCurrentSummaryInfos(String paramStDate, String paramEndDate) {
		List<TyphoonCastInfoDto> rs=null;
		paramStDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate;
		paramEndDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		rs = typhoonCastInfoDao.listCurrentTyphoonInfos(paramMap);
		for (TyphoonCastInfoDto typhoonCastInfoDto : rs) {
			paramMap.clear();
			paramMap.put("beg_year",typhoonCastInfoDto.getBeg_date().subSequence(0, 4));
			paramMap.put("beg_date",typhoonCastInfoDto.getBeg_date().substring(0,8));
			paramMap.put("end_date",typhoonCastInfoDto.getEnd_date().substring(0,8));
			paramMap.put("typ_seq",typhoonCastInfoDto.getTyp_seq());
			typhoonCastInfoDto.setListPosition(typhoonCastInfoDao.listTyphoonByCurrentTime(paramMap));
		}
		return rs;
	}
	@Override
	public YearDmeTyphoonDto typhoonDateMapping(List<TyphoonCastInfoDto> paramTargetPosition,List<YearDmeTyphoonDto> paramInfos){
		YearDmeTyphoonDto rs=null;
		int min=99999999;
		int max=0;
		String typhoonName=paramTargetPosition.get(0).getTyp_name();
		String targetBegDate="";
		for (TyphoonCastInfoDto targetPosition : paramTargetPosition) {
			if(targetPosition.getTm_fc()!=null && !targetPosition.getTm_fc().trim().equals("")){
				int item=Integer.parseInt(targetPosition.getTm_fc().substring(0, 8));
				if(max<=item){
					max=item;
				}
				
				if(min>=item){
					min=item;
				}
				
			}
		}
		targetBegDate=Integer.toString(min);
		
		for (YearDmeTyphoonDto yearDmeTyphoonDto : paramInfos) {
			if(yearDmeTyphoonDto.getTyp_name().indexOf(typhoonName)>-1 && yearDmeTyphoonDto.getBeg_date().indexOf(targetBegDate)>-1){
				int begDate=Integer.parseInt(yearDmeTyphoonDto.getBeg_date().substring(0, 8));
				int endDate=Integer.parseInt(yearDmeTyphoonDto.getEnd_date().substring(0, 8));
				if(begDate>min){
					yearDmeTyphoonDto.setBeg_date(Integer.toString(min));
				}
				
				if(endDate<max){
					yearDmeTyphoonDto.setEnd_date(Integer.toString(max));
				}
				rs=yearDmeTyphoonDto;
				break;
			}
		}
		if(rs==null){
			rs=new YearDmeTyphoonDto();
			rs.setBeg_date(targetBegDate);
			rs.setEnd_date(Integer.toString(max));
			rs.setTyp_name(typhoonName);
		}
	return rs;
	}

	@Override
	public List<YearDmeTyphoonDto> listAllYearDmeTyphoon() {
		//태풍이동경로 날짜와 실제 발생일 종료일 날짜가 일치하지 않아 별도로 태풍정보를 가져온다.
		return yearDmeTyphoonDao.listAll();
	}
	
	//별도로 가져온 태풍정보의 발생 종료일을 이동경로의 발생 종료일과 비교한다.
	//이 작업은 재해연보 조회를 위해 필요
	private YearDmeTyphoonDto typhoonDateMapping(TyphoonCastInfoDto paramTarget,List<YearDmeTyphoonDto> paramInfos){
		YearDmeTyphoonDto rs=null;
		
		String typhoonName=paramTarget.getTyp_name();
		int min=Integer.parseInt(paramTarget.getBeg_date());
		int max=Integer.parseInt(paramTarget.getEnd_date());
		
		String targetBegDate=paramTarget.getBeg_date();
		
		
		
		for (YearDmeTyphoonDto yearDmeTyphoonDto : paramInfos) {
			if(yearDmeTyphoonDto.getTyp_name().indexOf(typhoonName)>-1 && yearDmeTyphoonDto.getBeg_date().indexOf(targetBegDate)>-1){
				int begDate=Integer.parseInt(yearDmeTyphoonDto.getBeg_date().substring(0, 8));
				int endDate=Integer.parseInt(yearDmeTyphoonDto.getEnd_date().substring(0, 8));
				if(begDate>min){
					yearDmeTyphoonDto.setBeg_date(Integer.toString(min));
				}
				
				if(endDate<max){
					yearDmeTyphoonDto.setEnd_date(Integer.toString(max));
				}
				rs=yearDmeTyphoonDto;
				break;
			}
		}
	return rs;
	}

	@Override
	public void typhoonDamageMapping(TyphoonCastInfoDto paramTarget) {
		List<TyphoonCastInfoDto> target=new ArrayList<TyphoonCastInfoDto>();
		target.add(paramTarget);
		setListSimilarTyphoonAndDamage(target);
	
		
	}
	@Override
	public void createCurrentTyphoonInfo(){
		int rs=yearDmeTyphoonDao.create();
		if(rs==0)
		yearDmeTyphoonDao.update();
	}

	@Override
	public List<YearDmeDto> listDamageTyphoon(String stDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TyphoonDamageDto> listDamageTyphoon(double paramStDamageMoney,double paramEndDamageMoney,double paramStDamagePerson,double paramEndDamagePerson){
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		if(paramEndDamageMoney>0){
			paramMap.put("st_total_damage", paramStDamageMoney*100000);
			paramMap.put("end_total_damage", paramEndDamageMoney*100000);
		}
		if(paramEndDamagePerson>0){
			paramMap.put("st_com_dme", paramStDamagePerson);
			paramMap.put("end_com_dme", paramEndDamagePerson);
		}
		return typhoonDamageDao.list(paramMap);
	}
	
	@Override
	public TyphoonCastInfoDto detailLastPosition(String paramYear, String paramMonth, String paramName) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("end_year", paramYear);
		paramMap.put("end_month", paramMonth);
		paramMap.put("typ_name", paramName);
		return typhoonCastInfoDao.detailInfo(paramMap);
	}

	@Override
	public List<TyphoonCastInfoDto> listTyphoonByPeriodAction(String paramStDate) {
		
		List<TyphoonCastInfoDto> rs = new ArrayList<TyphoonCastInfoDto>();
		String paramStYear = "";
		String paramStMonth = "";
		paramStYear = (paramStDate.length() > 4) ? paramStDate.substring(0,4) : paramStDate;
		paramStMonth = (paramStDate.length() > 4) ? paramStDate.substring(4,6) : paramStDate;

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramStYear);
		paramMap.put("beg_month", paramStMonth);
		rs = typhoonCastInfoDao.listTyphoonByCurrentTime(paramMap);
		
		List<TyphoonCastInfoDto> pastList = typhoonCastInfoDao.listPastTyphoonByPastTime(paramMap);
		for (TyphoonCastInfoDto typhoonCastInfoDto : pastList) {
			rs.add(typhoonCastInfoDto);
		}
		
		return rs;
	}
	
	
}
