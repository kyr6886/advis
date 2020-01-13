package kr.dis.std.advis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;

import kr.dis.std.advis.dao.IStatisticsDao;
import kr.dis.std.advis.dao.IYearDmeStaticsDao;
import kr.dis.std.advis.dao.YearDmeStaticsDto;
import kr.dis.std.advis.resultmodel.StatisticsModel;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.dao.ICategoryDao;
import kr.dis.std.history.dao.AccidentSocDto;


@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

	@Autowired
	private ICategoryDao categoryDao;
	@Autowired
	private IYearDmeDao yearDmeDao;
	@Autowired
	private IStatisticsDao statisticsDao;
	@Autowired
	private IYearDmeStaticsDao yearDmeStaticsDao;
	@Override
	public List<StatisticsModel> listDisMonthlyCount() {
		
	
		
		
		List<StatisticsModel> rs=new ArrayList<StatisticsModel>();
		CategoryDTO paramDto=new CategoryDTO();
		paramDto.setDepth(1);
		List<CategoryDTO> listCategory=  categoryDao.listDiscode(paramDto);
		List<YearDmeDto> listYearDme=yearDmeDao.listDisasterMonthlyCount(null);
		 
		for (CategoryDTO categoryDTO : listCategory) {
			
			StatisticsModel model=new StatisticsModel();
			model.setListYearDme(new ArrayList<YearDmeDto>());
			model.setCodeName(categoryDTO.getTitle());
			model.setCode(categoryDTO.getCtg_id());
	
			
			for (YearDmeDto yearDmeDto : listYearDme) {
				if(yearDmeDto.getDis_category().indexOf(categoryDTO.getCtg_id().trim())>-1){
					model.setDamagePerson((float)(model.getDamagePerson()+yearDmeDto.getCom_dme()));
					model.setDamagePublic((float)(model.getDamagePublic()+yearDmeDto.getPub_total()));
					model.setDamagePrivate((float)(model.getDamagePrivate()+yearDmeDto.getPri_total()));
					model.setDamageTotal((float)(model.getDamageTotal()+model.getDamagePrivate()+model.getDamagePublic()));
					model.setCount(model.getCount()+yearDmeDto.getCount());
					 model.getListYearDme().add(yearDmeDto);
				}
			}
		   rs.add(model);
		}
		
		return rs;
	}
	@Override
	public List<StatisticsModel> listDisMonthlyCount(List<String> paramMonth,String paramStYear,String paramEndYear) {
		
	
		
		
		List<StatisticsModel> rs=new ArrayList<StatisticsModel>();
		CategoryDTO paramDto=new CategoryDTO();
		paramDto.setDepth(1);
		List<CategoryDTO> listCategory=  categoryDao.listDiscode(paramDto);
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("targetMonth", paramMonth);
		paramMap.put("begYear",paramStYear);
		paramMap.put("endYear",paramEndYear);
		List<YearDmeDto> listYearDme=yearDmeDao.listDisasterMonthlyCount(paramMap);
		
		for (CategoryDTO categoryDTO : listCategory) {
			
			StatisticsModel model=new StatisticsModel();
			model.setListYearDme(new ArrayList<YearDmeDto>());
			model.setCodeName(categoryDTO.getTitle());
			model.setCode(categoryDTO.getCtg_id());
	
			
			for (YearDmeDto yearDmeDto : listYearDme) {
				if(yearDmeDto.getDamage_name().indexOf(categoryDTO.getTitle().trim())>-1){
					model.setDamagePerson((float)(model.getDamagePerson()+yearDmeDto.getCom_dme()));
					model.setDamagePublic((float)(model.getDamagePublic()+yearDmeDto.getPub_total()));
					model.setDamagePrivate((float)(model.getDamagePrivate()+yearDmeDto.getPri_total()));
					model.setDamageTotal((float)(model.getDamageTotal()+model.getDamagePrivate()+model.getDamagePublic()));
					model.setCount(model.getCount()+yearDmeDto.getCount());
					 model.getListYearDme().add(yearDmeDto);
				}
			}
		   rs.add(model);
		}
		
		return rs;
	}
	@Override
	public StatisticsModel maxDisasterInfoByMonth(String paramMonthMM) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("targetMonth",paramMonthMM);
		YearDmeDto maxDis=yearDmeDao.maxDisasterByMonth(paramMap);
		StatisticsModel rs=new StatisticsModel();
		rs.setCode(maxDis.getDamage_code());
		rs.setCodeName(maxDis.getDamage_name());
		rs.setCount(maxDis.getCount());
		
		return rs;
	}
	@Override
	public List<StatisticsModel> listAreaDamageSum(String paramStYear, String paramEndYear,String paramDamageCode) {
		List<StatisticsModel> rs=new ArrayList<StatisticsModel>();
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("begYear",paramStYear);
		paramMap.put("endYear",paramEndYear);
		paramMap.put("damageCategoryCode",paramDamageCode);
		List<YearDmeDto> listYearDmeDto=yearDmeDao.listAreaDamageSum(paramMap);
		for (YearDmeDto yearDmeDto : listYearDmeDto) {
			StatisticsModel temp=new StatisticsModel();
			temp.setCode(yearDmeDto.getSido_code());
			temp.setCodeName(yearDmeDto.getSido());
			temp.setDamagePerson(yearDmeDto.getCom_dme());
			temp.setDamagePublic((float)yearDmeDto.getPub_total());
			temp.setDamagePrivate((float)yearDmeDto.getPri_total());
			temp.setDamageTotal(temp.getDamagePublic()+temp.getDamagePrivate());
			rs.add(temp);
		}
		return rs;
	}
	@Override
	public List<StatisticsModel> listEachYearCount(String paramStYear, String paramEndYear) {
		List<StatisticsModel> rs=new ArrayList<StatisticsModel>();

		CategoryDTO paramDto=new CategoryDTO();
		paramDto.setDepth(1);
		List<CategoryDTO> listCategory=  categoryDao.listDiscode(paramDto);
		
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("begYear",paramStYear);
		paramMap.put("endYear",paramEndYear);

		List<YearDmeDto> listYearDme=yearDmeDao.listYearCount(paramMap);
			
			for (YearDmeDto yearDmeDto : listYearDme) {
				boolean isUse=false;
				StatisticsModel model=new StatisticsModel();
				for (CategoryDTO categoryDTO : listCategory) {
					if(categoryDTO.getCtg_id()!=null &&yearDmeDto.getDis_category()!=null &&yearDmeDto.getDis_category().indexOf(categoryDTO.getCtg_id().trim())>-1){
						isUse=true;
						break;
					}		
				}
			if(isUse){
				model.setCode(yearDmeDto.getDamage_code());
				model.setCodeName(yearDmeDto.getDamage_code());
				model.setYear(yearDmeDto.getBeg_year());
				model.setCount(yearDmeDto.getCount());
				rs.add(model);
			}
				
		   
		}
		
		
	
		return rs;
	}
	
	@Override
	public StatisticsModel maxDisasterInfoByTyphoon(KmaInformDto dto) {
		StatisticsModel rs = new StatisticsModel();
		rs.setCodeName("태풍");
		return rs;
	}
	
	
	@Override
	public List<AccidentSocDto> listAccidentSocGroup(AccidentSocDto paramBean) {
		
		if(paramBean.getObz_dt()!=null){
			if(paramBean.getObz_dt().equals("")) paramBean.setObz_dt(null);
		}
		
		if(paramBean.getAddr_code()!=null){
			if(paramBean.getAddr_code().length()==2) paramBean.setLaw_sido(paramBean.getAddr_code());
			if(paramBean.getAddr_code().length()==5) paramBean.setLaw_sigungu(paramBean.getAddr_code());
		}
		
		return statisticsDao.listAccidentSocGroup(paramBean);
	}
	
	@Override
	public List<AccidentSocDto> listAccidentSocAll(AccidentSocDto paramBean) {
		
		if(paramBean.getObz_dt()!=null){
			if(paramBean.getObz_dt().equals("")) paramBean.setObz_dt(null);
		}
		
		if(paramBean.getAddr_code()!=null){
			if(paramBean.getAddr_code().length()==2) paramBean.setLaw_sido(paramBean.getAddr_code());
			if(paramBean.getAddr_code().length()==5) paramBean.setLaw_sigungu(paramBean.getAddr_code());
		}
		return statisticsDao.listAccidentSocAll(paramBean);
	}
	
	@Override
	public List<AccidentSocDto> searchListAccidentSocGroup(String paramMonth, String paramSidoCode, String paramText) {
		AccidentSocDto paramBean = new AccidentSocDto();
		paramBean.setObz_dt(paramMonth);
		paramBean.setAddr_code(paramSidoCode);
		paramBean.setDi_inf_name(paramText);
		return statisticsDao.searchListAccidentSocGroup(paramBean);
	}
	
	@Override
	public List<AccidentSocDto> listAccidentSocCountByMonthly(AccidentSocDto paramBean) {
		
		if(paramBean.getAddr_code()!=null){
			if(paramBean.getAddr_code().equals("")) paramBean.setAddr_code(null);
		}
		List<AccidentSocDto> rs = statisticsDao.listAccidentSocCountByMonthly(paramBean);
		
		CategoryDTO paramCtg=new CategoryDTO();
		paramCtg.setDepth(1);
		paramCtg.setCtg_id("S");
		List<CategoryDTO> ctgList = categoryDao.listCategory(paramCtg);
		
		List<AccidentSocDto> paramSoc = new ArrayList<>();
		for(int i=0; i<ctgList.size(); i++){
			int cnt = 0;
			for(int j=0; j<rs.size(); j++){
				if(ctgList.get(i).getCtg_id().equals(rs.get(j).getCtg_id())){
					cnt++;
				}
			}
			if(cnt == 0){
				AccidentSocDto temp = new AccidentSocDto();
				temp.setCtg_id(ctgList.get(i).getCtg_id());
				temp.setDi_inf_name(ctgList.get(i).getTitle());
				temp.setAddr_code(paramBean.getAddr_code());
				paramSoc.add(temp);
			}
		}
		
		for(int i=0; i<paramSoc.size(); i++){
			List<AccidentSocDto> tempList = statisticsDao.searchListAccidentSocCountByMonthly(paramSoc.get(i));
			for(int j=0; j<tempList.size(); j++){
				AccidentSocDto paramDto = new AccidentSocDto();
				paramDto.setCtg_id(paramSoc.get(i).getCtg_id());
				paramDto.setObz_dt(tempList.get(j).getObz_dt());
				paramDto.setCnt(tempList.get(j).getCnt());
				rs.add(paramDto);
			}
		}
		
		return rs;
	}
	
	@Override
	public List<AccidentSocDto> searchListAccidentSocCountByMonthly(AccidentSocDto paramBean) {
		return statisticsDao.searchListAccidentSocCountByMonthly(paramBean);
	}
	
	@Override
	public List<YearDmeStaticsDto> listYearDmeStatics(String paramDamageType,String paramStDate, String paramEndDate,
			String paramStTotalDamageMoney, String paramEndTotalDamageMoney, String paramStComDmeCount,
			String paramEndComDmeCount, String paramStRain, String paramEndRain) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("dis_category", paramDamageType);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("st_pri_total", paramStTotalDamageMoney);
		paramMap.put("st_rn_day", paramStRain);
		paramMap.put("st_com_dme", paramStComDmeCount);
		paramMap.put("end_pri_total", paramEndTotalDamageMoney);
		paramMap.put("end_rn_day", paramEndRain);
		paramMap.put("end_com_dme", paramEndComDmeCount);
	
		return yearDmeStaticsDao.list(paramMap);
	}
	@Override
	public List<YearDmeStaticsDto> listYearDmeStatics(String paramDamageType, String paramStDate, String paramEndDate,
			String paramStTotalDamageMoney, String paramEndTotalDamageMoney, String paramStComDmeCount,
			String paramEndComDmeCount, String paramStRain, String paramEndRain, String paramSidoCode) {
		paramSidoCode=paramSidoCode.isEmpty()?null:paramSidoCode.substring(0,2);
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("dis_category", paramDamageType);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		paramMap.put("st_pri_total", paramStTotalDamageMoney);
		paramMap.put("st_rn_day", paramStRain);
		paramMap.put("st_com_dme", paramStComDmeCount);
		paramMap.put("end_pri_total", paramEndTotalDamageMoney);
		paramMap.put("end_rn_day", paramEndRain);
		paramMap.put("end_com_dme", paramEndComDmeCount);
		paramMap.put("sido_code", paramSidoCode);
		return yearDmeStaticsDao.list(paramMap);
	}
	
	@Override
	public List<YearDmeStaticsDto> listSidoDmeRain(ArrayList<String> paramListObsId, String paramRnDay) {
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("listObsId", paramListObsId);
		paramMap.put("paramRnDay", paramRnDay);
		return yearDmeStaticsDao.listSidoDmeRain(paramMap);
	}
}

