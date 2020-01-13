package kr.dis.std.advis.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.kma.service.IKmaService;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;

import kr.dis.std.advis.dao.YearDmeStaticsDto;
import kr.dis.std.advis.service.IStatisticsService;
import kr.dis.std.common.service.ICommonService;
import kr.dis.std.history.dao.AccidentSocDto;
import kr.dis.std.log.service.ILogDisSearchService;
import kr.dis.std.web.viewmodel.StatisticsViewModel;

@Controller("statisticsApiController")
public class StatisticsApiController extends BaseController {
	
	@Autowired
	private IStatisticsService statisticsService;
	@Autowired
	private ILogDisSearchService logDisSearchService;
	@Autowired
	private ICommonService stdCommonService;
	@Autowired
	private IKmaService kmaService; 
	
	@RequestMapping(value="/api/statistics/summary",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel summaryStatisticsAction(@RequestBody StatisticsViewModel vm,Model model){
		
		vm.setListStatDisasterMonthly(statisticsService.listDisMonthlyCount(vm.getListParamMonths(),vm.getParamStartDate(),vm.getParamEndDate()));
		//vm.setMaxDisasterInfo(statisticsService.maxDisasterInfoByMonth(vm.getParamMonth()));
		vm.setListStatSido(statisticsService.listAreaDamageSum(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamDamageCode()));
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		Calendar cal=Calendar.getInstance();
		String currentDay=dateFormat.format(cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String preDay=dateFormat.format(cal.getTime());
		vm.setListMontlyRate(stdCommonService.listDisRateMonthly("", vm.getParamMonth(), vm.getParamListSelectedSidoCodes()));
		vm.setListLog(logDisSearchService.list(preDay, currentDay));
		return vm;
	}	
	
	@RequestMapping(value="/api/statistics/damage/area",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel areaDamageStatisticsAction(@RequestBody StatisticsViewModel vm,Model model){
		vm.setListStatSido(statisticsService.listAreaDamageSum(vm.getParamStartDate(),vm.getParamEndDate(),vm.getParamDamageCode()));
		
		return vm;
	}	
	@RequestMapping(value="/api/statistics/damage/sido/monthly/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel listMonthlySidoDamageAction(@RequestBody StatisticsViewModel vm,Model model){
		if(vm.getParamSocDisYn().equals("Y")){
			
			AccidentSocDto paramBean = new AccidentSocDto();
			paramBean.setCtg_id(vm.getParamDamageCode());
			paramBean.setAddr_code(vm.getParamSidoCode());
			paramBean.setObz_dt(vm.getParamMonth());
			 vm.setListSocSidoDamage(statisticsService.listAccidentSocGroup(paramBean));
			 if(vm.getListSocSidoDamage()==null ||vm.getListSocSidoDamage().size()==0){
				 vm.setListSocSidoDamage(statisticsService.searchListAccidentSocGroup(vm.getParamMonth(), vm.getParamSidoCode(), vm.getParamDamageName()));
			 }
			}else{
				vm.setLisMontlySidoDamage(stdCommonService.listDisSidoMonthly(vm.getParamSidoCode(),vm.getParamMonth(),vm.getParamDamageCode()));
			}
			return vm;
	}
	
	@RequestMapping(value="/api/statistics/damage/build/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel listBuildDamageAction(@RequestBody StatisticsViewModel vm,Model model){
		
			List<String> paramDamageCodes=new ArrayList<String>();
			paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON);
			paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON_RAIN);
			
			vm.setListBuildDamages(stdCommonService.listBuildDamges(paramDamageCodes, vm.getParamBegDate(), vm.getParamEndDate()));
			return vm;
	}
	

	@RequestMapping(value="/api/statistics/kma/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel  listKmaReportAction(@RequestBody StatisticsViewModel vm,Model model){
		List<KmaInformDto> listKmaInformAlert=kmaService.listKmaInform(ViewerSysKeyword.KMA_RPT_TYPE_RAIN, vm.getParamStartDate(), vm.getParamEndDate(),ViewerSysKeyword.KMA_RPT_TYPE_YELLOW);
		List<KmaInformDto> listKmaInformWarining=kmaService.listKmaInform(ViewerSysKeyword.KMA_RPT_TYPE_RAIN, vm.getParamStartDate(), vm.getParamEndDate(),ViewerSysKeyword.KMA_RPT_TYPE_RED);
		vm.setListKmaInformAlert(listKmaInformAlert);
		vm.setListKmaInformWarning(listKmaInformWarining);
		return vm;
	}
	@RequestMapping(value="/api/statistics/rain/damage/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel  listDamageAction(@RequestBody StatisticsViewModel vm,Model model){
		List<YearDmeStaticsDto> listYearDmeStatics=statisticsService.listYearDmeStatics(
				vm.getParamDamageCode()
				,vm.getParamStartDate()
				,vm.getParamEndDate()
				, vm.getParamStDamageMoney()
				, vm.getParamEndDamageMoney()
				, vm.getParamStComDmeCount()
				, vm.getParamEndComDmeCount()
				, vm.getParamStRain()
				, vm.getParamEndRain()
				,vm.getParamSidoCode()
				);
		vm.setListDamages(listYearDmeStatics);
		
		return vm;
	}
	
	@RequestMapping(value="/api/statistics/earthquake/damage/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel  listDamageEarthquakeAction(@RequestBody StatisticsViewModel vm,Model model){
		List<YearDmeStaticsDto> listYearDmeStatics=statisticsService.listYearDmeStatics(
				vm.getParamDamageCode()
				,vm.getParamStartDate()
				,vm.getParamEndDate()
				, vm.getParamStDamageMoney()
				, vm.getParamEndDamageMoney()
				, vm.getParamStComDmeCount()
				, vm.getParamEndComDmeCount()
				, vm.getParamStRain()
				, vm.getParamEndRain()
				
				);
		vm.setListDamages(listYearDmeStatics);
		
		return vm;
	}
	
	@RequestMapping(value="/api/statistics/sido/rain/damage/list",method=RequestMethod.POST)
	public @ResponseBody StatisticsViewModel  listSidoDmeRainAction(@RequestBody StatisticsViewModel vm,Model model){
		
		vm.setListSidoDmeRain(statisticsService.listSidoDmeRain(vm.getParamListObsId(), vm.getParamRnDay()));
		
		return vm;
	}
	
}
