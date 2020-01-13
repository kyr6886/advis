package kr.dis.std.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dis.std.advis.dao.DamageTyphoonDto;
import kr.dis.std.advis.dao.IDamageTyphoonDao;
import kr.dis.std.advis.resultmodel.StaticsMonthlyModel;
import kr.dis.std.advis.resultmodel.StatisticsModel;
import kr.dis.std.common.dao.ICommonDao;
@Service("stdCommonService")
public class CommonServiceImpl implements ICommonService {
	@Autowired
	private ICommonDao stdCommonDao;
	@Autowired
	private IDamageTyphoonDao damageTyphoonDao;
	@Override
	public List<StaticsMonthlyModel> listDisRateMonthly(String paramSidoCode, String paramBegMonthMM, List<String> paramListSidoCodes) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		if(!paramSidoCode.equals("")){
			paramMap.put("sido_code",paramSidoCode);
		}else{
			paramMap.put("sido_code",paramListSidoCodes);
		}
		
		paramMap.put("beg_month", paramBegMonthMM);
		
		List<StaticsMonthlyModel> rs=stdCommonDao.listDisRateMonthly(paramMap);
		return rs;
	}
	@Override
	public List<StaticsMonthlyModel> listDisSidoMonthly(String paramSidoCode, String paramBegMonthMM,
			String paramDamageCode) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("sido_code",paramSidoCode);
		paramMap.put("damage_month", paramBegMonthMM);
		paramMap.put("damage_code", paramDamageCode);
		List<StaticsMonthlyModel> rs=stdCommonDao.listDisSidoMonthly(paramMap);
		return rs;
	}
	@Override
	public List<StatisticsModel> listBuildDamges(List<String> paramDamageCode, String paramBegDate,
			String paramEndDate) {
		 List<StatisticsModel> rs=null;
		HashMap<String,Object> paramMap=new HashMap<>();
		paramMap.put("damage_codes", paramDamageCode);
		paramMap.put("beg_date", paramBegDate);
		paramMap.put("end_date", paramEndDate);
		rs=stdCommonDao.listDisBuildType(paramMap);
		return rs;
	}
	@Override
	public DamageTyphoonDto detailDamageTyphoon(String paramYear, String paramName) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("typ_name",paramName);
		return damageTyphoonDao.detail(paramMap);
	}
	@Override
	public List<DamageTyphoonDto> listDamageTyphoon(double paramStDamageMoney,double paramEndDamageMoney,double paramStDamagePerson,double paramEndDamagePerson) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		if(paramEndDamageMoney>0){
			paramMap.put("st_total_damage", paramStDamageMoney*100);   
			paramMap.put("end_total_damage", paramEndDamageMoney*100);
		}
		if(paramEndDamagePerson>0){
			paramMap.put("st_com_dme", paramStDamagePerson);
			paramMap.put("end_com_dme", paramEndDamagePerson);
		}
		return damageTyphoonDao.list(paramMap);
	}
	@Override
	public List<StatisticsModel> listBuildDamges(List<String> paramDamageCode, String paramBegDate, String paramEndDate,
			String paramDamgeName) {
		 List<StatisticsModel> rs=null;
			HashMap<String,Object> paramMap=new HashMap<>();
			paramMap.put("damage_codes", paramDamageCode);
			paramMap.put("beg_date", paramBegDate);
			paramMap.put("end_date", paramEndDate);
			paramMap.put("damage_name", paramDamgeName);
			rs=stdCommonDao.listDisBuildType(paramMap);
			return rs;
	}

}
