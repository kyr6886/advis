package com.noaa.nema.viewer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.asos.dao.IObsAsosDao;
import com.noaa.nema.viewer.service.IYearDmeService;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeCodeDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeConditionDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeImagesDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePbmDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePubDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeCodeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeConditionDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeImagesDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeSummaryDto;

@Service("yearDmeService")
public class YearDmeServiceImpl implements IYearDmeService {

	@Autowired
	private IYearDmeCodeDao yearDmeCodeDao;

	@Autowired
	private IYearDmePbmDao yearDmePbmDao;

	@Autowired
	private IYearDmePubDao yearDmePubDao;

	@Autowired
	private IYearDmeDao yearDmedDao;

	@Autowired
	private IObsAsosDao obsAsosDao;
	
	@Autowired
	private IYearDmeConditionDao yearDmeConditionDao;
	
	@Autowired
	private IYearDmeImagesDao yearDmeImagesDao;


	@SuppressWarnings("null")
	@Override
	public List<YearDmeDto> listDmeSido(String paramSido, String paramStDate, String paramEndDate) {

		String lawCode = "";
		String law_compare = "";
		String stDate = "";
		String edDate = "";

		lawCode = paramSido.substring(0, 2);
		law_compare = paramSido.substring(0, 5);
		stDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate.replaceAll("-", "");
		edDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate.replaceAll("-", "");

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", lawCode);
		paramMap.put("beg_date", stDate);
		paramMap.put("end_date", edDate);

		List<YearDmeDto> listSidoTot = null;
		List<YearDmeCodeDto> listCode = null;
		List<YearDmeDto> listSidoRsTot = null;

		listSidoTot = yearDmedDao.listSidoTot(paramMap);
		listCode = yearDmeCodeDao.listCode(paramMap);

		listSidoRsTot = new ArrayList<>();
		for (YearDmeDto listGunguDto : listSidoTot) {
			if (listGunguDto.getSigungu_code().equals(law_compare)) {
				listSidoRsTot.add(listGunguDto);
			}

		}

		for (YearDmeDto listSidoDto : listSidoRsTot) {
			for (YearDmeCodeDto listCodeDto : listCode) {
				if (listSidoDto.getDamage_code().equals(listCodeDto.getCode())) {
					listSidoDto.setDamage_name(listCodeDto.getCode_name_dme());
				}
			}
		}

		return listSidoRsTot;
	}

	@Override
	public List<YearDmeDto> listDmeGungu(String paramSido, String paramStDate, String paramEndDate) {

		
		String stDate = "";
		String edDate = "";

		stDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate.replaceAll("-", "");
		edDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate.replaceAll("-", "");

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		paramMap.put("beg_date", stDate);
		paramMap.put("end_date", edDate);

		List<YearDmeDto> listGunguTot = null;
		List<YearDmeDto> listGunguRsTot = null;
		List<YearDmeCodeDto> listCode = null;

		listGunguTot = yearDmedDao.listGunguTot(paramMap);
		listCode = yearDmeCodeDao.listCode(paramMap);

		listGunguRsTot = new ArrayList<>();
		for (YearDmeDto listGunguDto : listGunguTot) {
			if (listGunguDto.getSigungu_code().equals(paramSido)) {
				listGunguRsTot.add(listGunguDto);
			}
		}

		for (YearDmeDto listGunguDto : listGunguRsTot) {
			for (YearDmeCodeDto listCodeDto : listCode) {
				if (listGunguDto.getDamage_code().equals(listCodeDto.getCode())) {
					listGunguDto.setDamage_name(listCodeDto.getCode_name_dme());
				}
			}
		}

		return listGunguRsTot;
	}

	@Override
	public List<YearDmeDto> listDmeByYear(String paramSido, String paramStDate, String paramEndDate) {
		List<YearDmeDto> rs = null;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		if ( paramStDate != null && paramEndDate != null) {
			
			paramStDate = (paramStDate.length() <= 4) ? paramStDate + "0101" : paramStDate.replaceAll("-", "");
			paramEndDate = (paramEndDate.length() <= 4) ? paramEndDate + "1231" : paramEndDate.replaceAll("-", "");
			
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
			
		}
		
	
		rs = yearDmedDao.listDamageByYear(paramMap);
		return rs;
	}

	@Override
	public YearDmeDto dmeItemSum(String paramGungu, String paramDmeCode, String paramStYear, String paramEndYear) {
		YearDmeDto rs=null;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		
		
		if(paramDmeCode == null){
			paramMap.put("damage_code",null);
			paramMap.put("damage_codes",null);
		}else{
			String[] paramDemCodes= paramDmeCode.split(",");
			if(paramDemCodes.length>1){
				paramMap.put("damage_codes",paramDemCodes);
			}else{
				paramMap.put("damage_code",paramDmeCode);
			}	
		}
		
		if ( paramStYear != null && paramEndYear != null) {
			
			paramStYear = (paramStYear.length() <= 4) ? paramStYear + "0101" : paramStYear.replaceAll("-", "");
			paramEndYear = (paramEndYear.length() <= 4) ? paramEndYear + "1231" : paramEndYear.replaceAll("-", "");
			
			
			
		}
		
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		rs = yearDmedDao.damageItemSum(paramMap);
		return rs;
	}

	@Override
	public List<YearDmeDto> listComDmeTop10ByYear( String paramStYear, String paramEndYear) {
		List<YearDmeDto> rs = null;
		List<YearDmeDto> sido = null;
		List<YearDmeCodeDto> listCode = null;

		
		paramStYear = (paramStYear.length() <= 4) ? paramStYear + "0101" : paramStYear.replaceAll("-", "");
		paramEndYear = (paramEndYear.length() <= 4) ? paramEndYear + "1231" : paramEndYear.replaceAll("-", "");
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		
		rs = yearDmedDao.listComDmeGunguTop10(paramMap);
		sido = yearDmedDao.listComDmeRainSidoJoinByGungu(paramMap);
	    listCode = yearDmeCodeDao.listCode(paramMap);
		
	    if(sido.size() > 0){
	    	for(int i=0; i<rs.size(); i++){
	    		for(int j=0; j<sido.size(); j++){
	    			if(rs.get(i).getRn_day() <= 0){
	    				if(rs.get(i).getSigungu_code().equals(sido.get(j).getSigungu_code())
	    				   && rs.get(i).getBeg_date().equals(sido.get(j).getBeg_date())){
	    					rs.get(i).setRn_day(sido.get(j).getRn_day());
	    					rs.get(i).setRn_60m_max(sido.get(j).getRn_60m_max());
	    				}
	    			}
	    		}
	    	}
	    }
	    	    
		for (YearDmeDto listGunguDto : rs) {
			for (YearDmeCodeDto listCodeDto : listCode) {
				if (listGunguDto.getDamage_code().equals(listCodeDto.getCode())) {
					listGunguDto.setDamage_name(listCodeDto.getCode_name_dme());
				}
			}
		}
		
		return rs;
	}

	@Override
	public List<YearDmeDto> listTotalDmeTop10ByYear(String paramStYear, String paramEndYear, String paramGungu) {
		List<YearDmeDto> rs = null;
		List<YearDmeDto> sido = null;
		List<YearDmeCodeDto> listCode = null;
		
		paramStYear = (paramStYear.length() <= 4) ? paramStYear + "0101" : paramStYear.replaceAll("-", "");
		paramEndYear = (paramEndYear.length() <= 4) ? paramEndYear + "1231" : paramEndYear.replaceAll("-", "");
	
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		paramMap.put("law_code", paramGungu);
		
		rs = yearDmedDao.listTotalDmeTop10(paramMap);
		sido = yearDmedDao.listTotalDmeRainSidoJoinByGungu(paramMap);
		listCode = yearDmeCodeDao.listCode(paramMap);
		
		
	    if(sido.size() > 0){
	    	for(int i=0; i<rs.size(); i++){
	    		for(int j=0; j<sido.size(); j++){
	    			if(rs.get(i).getRn_day() <= 0){
	    				if(rs.get(i).getSigungu_code().equals(sido.get(j).getSigungu_code())
	    				   && rs.get(i).getBeg_date().equals(sido.get(j).getBeg_date())){
	    					rs.get(i).setRn_day(sido.get(j).getRn_day());
	    					rs.get(i).setRn_60m_max(sido.get(j).getRn_60m_max());
	    				}
	    			}
	    		}
	    	}
	    }
		
		for (YearDmeDto listGunguDto : rs) {
			for (YearDmeCodeDto listCodeDto : listCode) {
				if (listGunguDto.getDamage_code().equals(listCodeDto.getCode())) {
					listGunguDto.setDamage_name(listCodeDto.getCode_name_dme());
				}
			}
		}
		
		return rs;
	}

	@Override
	public List<YearDmeDto> listWholeAreaDmeChartByYear(String paramStYear, String paramEndYear) {
		List<YearDmeDto> rs = null;
		paramStYear = (paramStYear.length() <= 4) ? paramStYear + "0101" : paramStYear.replaceAll("-", "");
		paramEndYear = (paramEndYear.length() <= 4) ? paramEndYear + "1231" : paramEndYear.replaceAll("-", "");
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		
		rs = yearDmedDao.listTotalDmeChartByYear(paramMap);
		
		return rs;
	}

	@Override
	public List<YearDmeDto> listComDmeAndTotalDmeByYear(String paramStYear, String paramEndYear, String paramDmeCode, int paramOffset, String paramGugnu) {
		List<YearDmeDto> rs = null;
		
		if(paramStYear != null) paramStYear = (paramStYear.length() <= 4) ? paramStYear + "0101" : paramStYear.replaceAll("-", "");
		if(paramEndYear != null) paramEndYear = (paramEndYear.length() <= 4) ? paramEndYear + "1231" : paramEndYear.replaceAll("-", ""); 
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		paramMap.put("dme_code", paramDmeCode);
		paramMap.put("offset", paramOffset);
		paramMap.put("law_code", paramGugnu);
		
		rs = yearDmedDao.listComDmeAndTotalDmeByYear(paramMap);
		
		return rs;
	}

	@Override
	public YearDmeConditionDto detailCondition(String paramStDate, String paramEndDate, String paramDmeCode) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("damage_type",paramDmeCode);
		
		return yearDmeConditionDao.detail(paramMap);
	}

	@Override
	public List<YearDmeImagesDto> listYearDmeImages(String paramStDate, String paramEndDate) {
		List<YearDmeImagesDto> rs = null;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
	
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);		
		
		rs = yearDmeImagesDao.list(paramMap);
		return rs;
	}

	@Override
	public List<YearDmeCodeDto> listDmeCode() {
		HashMap<String, Object> paramMap = new HashMap<>();
		return yearDmeCodeDao.listCode(paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeGunguAndDateCode(String paramYear, String paramStDate, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("damage_code", paramDmeCode);
		return yearDmeCodeDao.listDmeCode(paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeSidoCode(String paramYear, String paramStDate, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("damage_code", paramDmeCode);
		return yearDmeCodeDao.listDmeSidoCode(paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeGunguCode(String paramYear, String paramStDate, String paramDmeCode, String paramSidoCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("damage_code", paramDmeCode);
		paramMap.put("law_code", paramSidoCode);

		return yearDmeCodeDao.listDmeGunguCode(paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listTyphoonDmeSidoCode(String paramYear, int paramTypSeq, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("typ_seq", paramTypSeq);
		paramMap.put("dme_code", paramDmeCode);
		return yearDmeCodeDao.listTyphoonDmeSidoCode(paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeTyphoonNameCode(String paramYear, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("typ_seq", null);
		paramMap.put("dme_code", paramDmeCode);
		return yearDmeCodeDao.listTyphoonDmeSidoCode(paramMap);
	}

	@Override
	public int registCondition(int paramSeq, String paramStDate
								, String paramEndDate, String paramDescription, String paramDamageType, String paramCauses) {
		
		YearDmeConditionDto paramDto = new YearDmeConditionDto();
		int result = 0;
		if(paramSeq != 0){
			paramDto.setDescription(paramDescription);
			paramDto.setCauses(paramCauses);
			paramDto.setSeq(paramSeq);
			result  = yearDmeConditionDao.updateCondition(paramDto);
		}else{
			
			paramDto.setBeg_year(paramStDate.substring(0,4));
			paramDto.setBeg_month(paramStDate.substring(4,6));
			paramDto.setBeg_day(paramStDate.substring(6,8));
			paramDto.setEnd_year(paramEndDate.substring(0,4));
			paramDto.setEnd_month(paramEndDate.substring(4,6));
			paramDto.setEnd_day(paramEndDate.substring(6,8));
			paramDto.setDescription(paramDescription);
			paramDto.setCauses(paramCauses);
			paramDto.setDamage_type(paramDamageType);
			result = yearDmeConditionDao.registCondition(paramDto);
		}
		return result;
	}

	@Override
	public int updateCondition(int paramSeq, String paramDescription) {
		
		YearDmeConditionDto paramDto = new YearDmeConditionDto();
		paramDto.setDescription(paramDescription);
		paramDto.setSeq(paramSeq);
		
		int result  = yearDmeConditionDao.updateCondition(paramDto);
		return result;
	}

	@Override
	public int registImages(String paramStDate, String paramEndDate, String paramFileName, String paramId) {

		YearDmeImagesDto paramDto = new YearDmeImagesDto();
		paramDto.setBeg_date(paramStDate);
		paramDto.setEnd_date(paramEndDate);
		paramDto.setFile_path(null);
		paramDto.setFile_name(paramFileName);
		paramDto.setRegist_user(paramId);
		int result = yearDmeImagesDao.registImages(paramDto);
		return result;
	}

	

	@Override
	public int deleteImages(int paramSeq) {
		YearDmeImagesDto paramDto = new YearDmeImagesDto();
		paramDto.setSeq(paramSeq);
		int result = yearDmeImagesDao.deleteImages(paramDto);
		return result;
	}



	@Override
	public List<YearDmeDto> listAllSidoDamageAndRain(String paramStDate, String paramEndDate){
		List<YearDmeDto> rs = null;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		rs = yearDmedDao.listAllSidoDamageAndRain(paramMap);
		return rs;
	}

	@Override
	public int insertSummerySidoList(String paramStDate, String paramEndDate) {
		int rs = 0;
		List<YearDmeDto> list = listAllSidoDamageAndRain(paramStDate, paramEndDate);
		for(int i=0; i<list.size(); i++){
			YearDmeSummaryDto dto = new YearDmeSummaryDto();
			dto.setSigungu(list.get(i).getSigungu_code());
			dto.setDamage_code(list.get(i).getDamage_code());
			dto.setBeg_date(list.get(i).getBeg_date());
			dto.setEnd_date(list.get(i).getEnd_date());
			dto.setPerson_dmage(list.get(i).getCom_dem());
			dto.setCom_damage(list.get(i).getCom_total());
			dto.setPri_damage(list.get(i).getPri_total());
			dto.setPublic_damage(list.get(i).getPub_total());
			dto.setRain_day(list.get(i).getRn_day());
			dto.setRain_60(list.get(i).getRn_60m_max());
			dto.setRain_10(list.get(i).getRn_10m_max());
			dto.setSido_yn("Y");
			try{
				rs = yearDmedDao.insertSummeryList(dto);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return rs;
	}

	@Override
	public int insertSummerySigunguList(String paramStDate, String paramEndDate) {
		int rs = 0;
		List<YearDmeDto> list = listAllSigunguDamageAndRain(paramStDate, paramEndDate);
		for(int i=0; i<list.size(); i++){
			YearDmeSummaryDto dto = new YearDmeSummaryDto();
			dto.setSigungu(list.get(i).getSigungu_code());
			dto.setDamage_code(list.get(i).getDamage_code());
			dto.setBeg_date(list.get(i).getBeg_date());
			dto.setEnd_date(list.get(i).getEnd_date());
			dto.setPerson_dmage(list.get(i).getCom_dem());
			dto.setCom_damage(list.get(i).getCom_total());
			dto.setPri_damage(list.get(i).getPri_total());
			dto.setPublic_damage(list.get(i).getPub_total());
			dto.setRain_day(list.get(i).getRn_day());
			dto.setRain_60(list.get(i).getRn_60m_max());
			dto.setRain_10(list.get(i).getRn_10m_max());
			dto.setSido_yn("N");
			try{
				rs = yearDmedDao.insertSummeryList(dto);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listAllSigunguDamageAndRain(String paramStDate, String paramEndDate){
		List<YearDmeDto> rs = null;
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		rs = yearDmedDao.listAllSigunguDamageAndRain(paramMap);
		return rs;
	}

	@Override
	public List<YearDmeDto> listTyphoonDmeList(String paramStDate, String paramEndDate, String typhoonName) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("typhoonName", typhoonName);
		return yearDmedDao.listTyphoonDmeList(paramMap);
	}

	@Override
	public List<YearDmeDto> listYearDme(String paramStDate, String paramEndDate, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("damage_code", paramDmeCode);
		return yearDmedDao.listYearDme(paramMap);
	}
}