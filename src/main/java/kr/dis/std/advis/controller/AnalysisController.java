package kr.dis.std.advis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.kma.service.IKmaService;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.service.ITyphoonCastInfoService;

import kr.dis.std.advis.service.IDamageAnlRainService;
import kr.dis.std.common.service.ICommonService;
import kr.dis.std.web.viewmodel.AnalysisViewModel;
import kr.dis.std.web.viewmodel.AsosViewModel;

@Controller("analysisController")
public class AnalysisController extends BaseController{
	@Autowired
	private IDamageAnlRainService damageAnlRainServiceImpl;
	@Autowired
	private ITyphoonCastInfoService typhoonCastInfoService;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IKmaService kmaService;
	
	@RequestMapping(value="/analysis/typhoon/path",method=RequestMethod.GET)
	public String similarPathTyphoonView(AnalysisViewModel vm,Model model){
		
		model.addAttribute("vm",vm);
		return "/analysis/typhoon/path";
	}
	@RequestMapping(value="/analysis/damage",method=RequestMethod.GET)
	public String damageAnalysisView(AnalysisViewModel vm,Model model){
		model.addAttribute("vm",vm);
		return "/analysis/index";
	}
	
	@RequestMapping(value="/analysis/rain/damage",method=RequestMethod.GET)
	public String damageRainAnalysisView(AnalysisViewModel vm,Model model){
		model.addAttribute("vm",vm);
		return "/analysis/rain";
	}
	@RequestMapping(value="/analysis/earthquake/damage",method=RequestMethod.GET)
	public String damageEarthquakeAnalysisView(AnalysisViewModel vm,Model model){
		model.addAttribute("vm",vm);
		return "/analysis/earthquake";
	}
	@RequestMapping(value="/analysis/ai/damage",method=RequestMethod.GET)
	public String damageAiAnalysisView(AnalysisViewModel vm,Model model){
		model.addAttribute("vm",vm);
		return "/analysis/ai";
	}
	
	@RequestMapping(value="/api/analysis/typhoon/damage/list",method=RequestMethod.POST) 
	public @ResponseBody AnalysisViewModel listDamageTyphoonAnalysisAction(@RequestBody AnalysisViewModel vm,Model model){
		vm.setListTyphoonDamageInfo( commonService.listDamageTyphoon(vm.getParamStDamage(), vm.getParamEndDamage(), vm.getParamStDamagePerson(), vm.getParamEndDamagePerson()));
		return vm;
	}
	
	@RequestMapping(value="/api/analysis/asos/{begDate}/{endDate}",method=RequestMethod.POST)
	public @ResponseBody AsosViewModel  listAsosAction(@PathVariable(value="begDate") String paramBegDate,@PathVariable(value="endDate") String paramEndDate){
		AsosViewModel vm=new AsosViewModel();
		vm.setListAsos(damageAnlRainServiceImpl.searchListDailyMaxRain(paramBegDate, paramEndDate));
		
		return vm;
	}
	@RequestMapping(value="/api/analysis/typhoon/damage/build/list",method=RequestMethod.POST)
	public @ResponseBody AnalysisViewModel  listBuildDamageTyphoonAction(@RequestBody AnalysisViewModel vm,Model model){
		List<String> paramDamageCodes=new ArrayList<String>();
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON);
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_TYPHOON_RAIN);
		
		vm.setListStatistics(commonService.listBuildDamges(paramDamageCodes, vm.getParamStartDate(), vm.getParamEndDate(),vm.getParamDamageName()));
		return vm;
	}
	
	@RequestMapping(value="/api/analysis/rain/damage/build/list",method=RequestMethod.POST)
	public @ResponseBody AnalysisViewModel  listBuildDamageRainAction(@RequestBody AnalysisViewModel vm,Model model){
		List<String> paramDamageCodes=new ArrayList<String>();
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_RAIN);
		
		vm.setListStatistics(commonService.listBuildDamges(paramDamageCodes, vm.getParamStartDate(), vm.getParamEndDate()));
		return vm;
	}
	///api/typhoon/cast/inform/list.do
	
	@RequestMapping(value="/api/analysis/earthquake/damage/build/list",method=RequestMethod.POST)
	public @ResponseBody AnalysisViewModel  listBuildDamageearthquakeAction(@RequestBody AnalysisViewModel vm,Model model){
		List<String> paramDamageCodes=new ArrayList<String>();
		paramDamageCodes.add(ViewerSysKeyword.DME_CODE_EARTHQUAKE);
		
		vm.setListStatistics(commonService.listBuildDamges(paramDamageCodes, vm.getParamStartDate(), vm.getParamEndDate()));
		return vm;
	}
	
	
}
