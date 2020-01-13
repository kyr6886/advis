package com.noaa.nema.viewer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.CommonsDbcpNativeJdbcExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.kma.service.IKmaService;
import com.noaa.nema.viewer.service.ICommonService;
import com.noaa.nema.viewer.service.IRainAnlService;
import com.noaa.nema.viewer.service.ITyphoonCastInfoService;
import com.noaa.nema.viewer.service.IYearDmeService;
import com.noaa.nema.viewer.viewmodel.DamageApiViewModel;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePubDto;


@Controller("damageApiController")
public class DamageApiController {
	
	
	@Autowired
	private IYearDmeService yearDmeService;
	@Autowired
	private IRainAnlService rainAnlService;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IKmaService kmaService;
	@Autowired
	private ITyphoonCastInfoService typhoonCastInfoService;
	@RequestMapping(value="/api/damage/rain/area/kma/summary.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel summaryKmaAreaRainDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		Calendar cal=Calendar.getInstance();
	//	String stYear=Integer.toString(cal.get(cal.YEAR)-10);
	//	String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		List<YearDmeDto> rs=new ArrayList<YearDmeDto>();
		
		
		rs.addAll(commonService.listDmeWithGungu(vm.getListParamGungu(),vm.getParamStDate(), vm.getParamEndDate(),ViewerSysKeyword.DME_CODE_RAIN,vm.getParamStRainValue(),vm.getParamEndRainValue()));
		rs.addAll(commonService.listDmeWithGunguByArea(vm.getListParamSido(),vm.getParamStDate(), vm.getParamEndDate(),ViewerSysKeyword.DME_CODE_RAIN,vm.getParamStRainValue(),vm.getParamEndRainValue()));
		vm.setListDamage(rs);
		
		
		return vm;
	}
	@RequestMapping(value="/api/damage/typhoon/area/kma/summary.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel summaryKmaAreaTyphoonDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		String paramDamageCode=ViewerSysKeyword.DME_CODE_TYPHOON+","+ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
		//	Calendar cal=Calendar.getInstance();
	//	String stYear=Integer.toString(cal.get(cal.YEAR)-10);
	//	String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		List<YearDmeDto> rs=new ArrayList<YearDmeDto>();
		

		rs.addAll(commonService.listDmeWithGungu(vm.getListParamGungu(),vm.getParamStDate(), vm.getParamEndDate(),paramDamageCode));
		rs.addAll(commonService.listDmeWithGunguByArea(vm.getListParamSido(),vm.getParamStDate(), vm.getParamEndDate(),paramDamageCode));
		vm.setListDamage(rs);
		
		
		return vm;
	}
	@RequestMapping(value="/api/damage/rain/area/summary.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel summaryAreaRainDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
	   
		
		if(vm.getParamSido()==null||vm.getParamSido().isEmpty()){
			vm.setListDamage(commonService.listDamageMoneyTop10(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN));
			vm.setListDamagePerson(commonService.listDamagePersonTop10(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN));
		}else{
			int _countThissen=commonService.countThissen(vm.getParamSido());
			String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamSido());
			if(_countThissen>1){
				if(lawAreaYn==null || lawAreaYn.equals("Y")){
					vm.setListDamage(commonService.listDmeWithGunguByArea(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN,vm.getParamStRainValue(),vm.getParamEndRainValue()));
				}else{
				 vm.setListDamage(commonService.listDmeWithGungu(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN,vm.getParamStRainValue(),vm.getParamEndRainValue()));
				}
			}else{
				vm.setListDamage(commonService.listDmeWithGunguByArea(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN,vm.getParamStRainValue(),vm.getParamEndRainValue()));
			}
		}
		return vm;
	}
	
	@RequestMapping(value="/api/damage/typhoon/rain/area/summary.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel summaryAreaRainTyphoonDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
	   
		String paramDamageCode=ViewerSysKeyword.DME_CODE_TYPHOON+","+ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
		if(vm.getParamSido()==null||vm.getParamSido().isEmpty()){
			vm.setListDamage(commonService.listDamageMoneyTop10(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
			vm.setListDamagePerson(commonService.listDamagePersonTop10(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
		}else{
			int _countThissen=commonService.countThissen(vm.getParamSido());
			String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamSido());
		
			if(_countThissen>1){
				if(lawAreaYn==null || lawAreaYn.equals("Y")){
					vm.setListDamage(commonService.listDmeWithGunguByArea(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
				}else{
				 vm.setListDamage(commonService.listDmeWithGungu(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
				}
			}else{
				vm.setListDamage(commonService.listDmeWithGunguByArea(vm.getParamSido(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
			}
		}
		return vm;
	}
	
	
	@RequestMapping(value="/api/damage/rain/area/detail.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel detailAreaDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		int _countThissen=commonService.countThissen(vm.getParamGungu());
		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamGungu());
		
		Calendar cal=Calendar.getInstance();
		String stYear=Integer.toString(cal.get(cal.YEAR)-10);
		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		
		vm.setDetail10Years(commonService.totalDamage(vm.getParamGungu(), stYear,endYear, ViewerSysKeyword.DME_CODE_RAIN));
		vm.setDetailMaxPersonYear(commonService.maxDamagePerson(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_RAIN));
		vm.setDetailMaxMoneyYear(commonService.maxDamageMoney(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_RAIN));
		vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), ViewerSysKeyword.DME_CODE_RAIN, vm.getParamStDate(), vm.getParamEndDate()));
		if(_countThissen>1){
			if(lawAreaYn==null || lawAreaYn.equals("Y")){
				vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN));
			}else{
				vm.setListDamage(commonService.listDmeHisWithGungu(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN));		
			}
		
		}else{
			vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_RAIN));
		}
		return vm;
	}
	
	@RequestMapping(value="/api/damage/typhoon/area/detail.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel detailAreaTyphoonDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		String paramDamageCode=ViewerSysKeyword.DME_CODE_TYPHOON+","+ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
		int _countThissen=commonService.countThissen(vm.getParamGungu());
		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamGungu());
		
		Calendar cal=Calendar.getInstance();
		String stYear=Integer.toString(cal.get(cal.YEAR)-10);
		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		
		vm.setDetail10Years(commonService.totalDamage(vm.getParamGungu(), stYear,endYear, paramDamageCode));
		vm.setDetailMaxPersonYear(commonService.maxDamagePerson(vm.getParamGungu(), "1985",endYear, paramDamageCode));
		vm.setDetailMaxMoneyYear(commonService.maxDamageMoney(vm.getParamGungu(), "1985",endYear, paramDamageCode));
		vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), paramDamageCode, vm.getParamStDate(), vm.getParamEndDate()));
		
		if(_countThissen>1){
			if(lawAreaYn==null || lawAreaYn.equals("Y")){
				vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(),paramDamageCode));
			}else{
				vm.setListDamage(commonService.listDmeHisWithGungu(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(),paramDamageCode));		
			}
		
		}else{
			vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), paramDamageCode));
		}
		
		vm.setTyphoonNameList(typhoonCastInfoService.listTyphoonName(vm.getParamStDate(), vm.getParamEndDate()));
		return vm;
	}
	
	
	@RequestMapping(value="/api/damage/rain/sector/area.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel areaSectorRainDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		int _countThissen=commonService.countThissen(vm.getParamSido());
		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamSido());
		
		Calendar cal=Calendar.getInstance();
		String stYear=ViewerSysKeyword.DME_FIRST_YEAR;
		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		List<YearDmeDto> _listDamage=null;
		if(_countThissen>1){
			if(lawAreaYn==null || lawAreaYn.equals("Y")){
				_listDamage=commonService.listDmeHisWithGunguByArea(vm.getParamSido().substring(0, 2),stYear, endYear, ViewerSysKeyword.DME_CODE_RAIN);
			}else{
				_listDamage=commonService.listDmeHisWithGungu(vm.getParamSido().substring(0, 2), stYear, endYear, ViewerSysKeyword.DME_CODE_RAIN);		
			}
		
		}else{
			_listDamage=commonService.listDmeHisWithGunguByArea(vm.getParamSido().substring(0, 2), stYear,endYear, ViewerSysKeyword.DME_CODE_RAIN);
		}
		vm.setListSectors(commonService.listRainDemSector(_listDamage));
		return vm;
	}
	@RequestMapping(value="/api/damage/typhoon/sector/area.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel areaSectorTyphoonDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		String paramDamageCode=ViewerSysKeyword.DME_CODE_TYPHOON+","+ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
		int _countThissen=commonService.countThissen(vm.getParamSido());
		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamSido());
		
		Calendar cal=Calendar.getInstance();
		String stYear=ViewerSysKeyword.DME_FIRST_YEAR;
		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
		List<YearDmeDto> _listDamage=null;
		if(_countThissen>1){
			if(lawAreaYn==null || lawAreaYn.equals("Y")){
				_listDamage=commonService.listDmeHisWithGunguByArea(vm.getParamSido().substring(0, 2),stYear, endYear, paramDamageCode);
			}else{
				_listDamage=commonService.listDmeHisWithGungu(vm.getParamSido().substring(0, 2), stYear, endYear, paramDamageCode);		
			}
		
		}else{
			_listDamage=commonService.listDmeHisWithGunguByArea(vm.getParamSido().substring(0, 2), stYear,endYear, paramDamageCode);
		}
		vm.setListSectors(commonService.listRainDemSector(_listDamage));
		return vm;
	}
	@RequestMapping(value="/api/damage/rain/cause/area.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel areaCauseRainDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
		vm.setListDamagePerson(commonService.listGunguDamageCauseResultSum(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate()));
		return vm;
	}
	
	@RequestMapping(value="/api/damage/snow/area/detail.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel detailAreaSnowDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
//		int _countThissen=commonService.countThissen(vm.getParamGungu());
//		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamGungu());
//		
//		Calendar cal=Calendar.getInstance();
//		String stYear=Integer.toString(cal.get(cal.YEAR)-10);
//		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
//		
//		vm.setDetail10Years(commonService.totalDamage(vm.getParamGungu(), stYear,endYear, ViewerSysKeyword.DME_CODE_SNOW));
//		vm.setDetailMaxPersonYear(commonService.maxDamagePerson(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_SNOW));
//		vm.setDetailMaxMoneyYear(commonService.maxDamageMoney(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_SNOW));
//		vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), ViewerSysKeyword.DME_CODE_SNOW, vm.getParamStDate(), vm.getParamEndDate()));
//		if(_countThissen>1){
//			if(lawAreaYn==null || lawAreaYn.equals("Y")){
//				vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_SNOW));
//			}else{
				vm.setListDamage(commonService.listDmeHisWithGungu(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_SNOW));		
//			}
//		}else{
//			vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_SNOW));
//		}
		return vm;
	}
	
	@RequestMapping(value="/api/damage/earthquake/area/detail.do",method=RequestMethod.POST)
	public @ResponseBody DamageApiViewModel detailAreaEarthquakeDamageAction(@RequestBody DamageApiViewModel vm, Model model)throws Exception{
//		int _countThissen=commonService.countThissen(vm.getParamGungu());
//		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamGungu());
//		
//		Calendar cal=Calendar.getInstance();
//		String stYear=Integer.toString(cal.get(cal.YEAR)-10);
//		String endYear=Integer.toString(cal.get(cal.YEAR)-1);
//		
//		vm.setDetail10Years(commonService.totalDamage(vm.getParamGungu(), stYear,endYear, ViewerSysKeyword.DME_CODE_EARTHQUAKE));
//		vm.setDetailMaxPersonYear(commonService.maxDamagePerson(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_EARTHQUAKE));
//		vm.setDetailMaxMoneyYear(commonService.maxDamageMoney(vm.getParamGungu(), "1985",endYear, ViewerSysKeyword.DME_CODE_EARTHQUAKE));
//		vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), ViewerSysKeyword.DME_CODE_EARTHQUAKE, vm.getParamStDate(), vm.getParamEndDate()));
//		if(_countThissen>1){
//			if(lawAreaYn==null || lawAreaYn.equals("Y")){
//				vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_EARTHQUAKE));
//			}else{
				vm.setListDamage(commonService.listDmeHisWithGungu(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_EARTHQUAKE));		
//			}
//		}else{
//			vm.setListDamage(commonService.listDmeHisWithGunguByArea(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate(), ViewerSysKeyword.DME_CODE_EARTHQUAKE));
//		}
		return vm;
	}
}