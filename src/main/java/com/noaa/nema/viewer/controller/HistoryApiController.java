package com.noaa.nema.viewer.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.file.service.IFileService;
import com.noaa.nema.viewer.area.service.IAreaCodeService;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.service.ICommonService;
import com.noaa.nema.viewer.service.ITyphoonCastDateService;
import com.noaa.nema.viewer.service.ITyphoonCastInfoService;
import com.noaa.nema.viewer.service.IYearDmeService;
import com.noaa.nema.viewer.viewmodel.YearDmeViewModel;
import com.noaa.nema.viewer.year.dme.dao.YearDmeCodeDto;

import kr.dis.std.event.service.IEventService;

@Controller("historyApiController")
public class HistoryApiController {

	@Autowired
	private IYearDmeService yearDmeService;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private ITyphoonCastInfoService typhoonCastInfoService;
	
	@Autowired
	ITyphoonCastDateService typCastDateService;
	
	@Autowired
	private IAreaCodeService areaCodeService;
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private IEventService eventService;
	

	@RequestMapping(value = "/api/damage/history/sigungu/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listSigunguHistoryDamageAction(@RequestBody YearDmeViewModel vm, Model model, HttpServletRequest request)
			throws Exception {

		int _countThissen = commonService.countThissen(vm.getParamSido());
		String law_area = commonService.thissenLawAreaYn(vm.getParamSido());
		
		
		if (_countThissen > 1) {
			if (law_area == null || law_area.equals("Y")) {
				vm.setListDmeSido(commonService.listDmeHisWithGunguByArea(vm.getParamSido(), vm.getParamStDate(),
						vm.getParamEndDate(), null));

			} else {
				vm.setListDmeSido(commonService.listDmeHisWithGungu(vm.getParamSido(), vm.getParamStDate(),
						vm.getParamEndDate(), null));
			}
		} else {
			vm.setListDmeSido(commonService.listDmeHisWithGunguByArea(vm.getParamSido(), vm.getParamStDate(),
					vm.getParamEndDate(), null));
		}
		
		vm.setTyphoonNameList(typhoonCastInfoService.listTyphoonName(vm.getParamStDate(), vm.getParamEndDate()));
	
		return vm;
	}

	@RequestMapping(value = "/api/damage/history/year/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listYearHistoryDamageAction(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		

		vm.setListDmeGungu(yearDmeService.listDmeByYear(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate()));
		if(vm.getParamDmeCode() == null || vm.getParamDmeCode().isEmpty()){
			vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), null, vm.getParamStDate(), vm.getParamEndDate()));
		}else{
			if(vm.getParamDmeCode().equals(ViewerSysKeyword.DME_CODE_TYPHOON) || vm.getParamDmeCode().equals(ViewerSysKeyword.DME_CODE_TYPHOON_RAIN)){
				String damage_codes = ViewerSysKeyword.DME_CODE_TYPHOON+","+ViewerSysKeyword.DME_CODE_TYPHOON_RAIN;
				vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), damage_codes, vm.getParamStDate(), vm.getParamEndDate()));
			}else{
				vm.setDamageItemSum(yearDmeService.dmeItemSum(vm.getParamGungu(), vm.getParamDmeCode(), vm.getParamStDate(), vm.getParamEndDate()));
			}
		}
		
		vm.setListAreaGunguCodes(areaCodeService.listGungu(vm.getParamGungu()));
		if(vm.getParamStDate().length() > 4){
			vm.setListDamagePerson(commonService.listGunguDamageCause(vm.getParamGungu(), vm.getParamStDate(), vm.getParamEndDate()));
			vm.setListYearDmeImages(yearDmeService.listYearDmeImages(vm.getParamStDate(), vm.getParamEndDate()));
			vm.setYearDmeCondition(yearDmeService.detailCondition(vm.getParamStDate(), vm.getParamEndDate(), vm.getParamDmeCode()));
		}
				
		return vm;
	}

	
	
	@RequestMapping(value = "/api/damage/history/whole/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listComDmeAndTotalDmeByYear(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setListDmeGungu(yearDmeService.listComDmeAndTotalDmeByYear( vm.getParamStDate(), vm.getParamEndDate(), vm.getParamDmeCode(), 0, vm.getParamGungu()));
		vm.setTyphoonNameList(typhoonCastInfoService.listTyphoonName(vm.getParamStDate(), vm.getParamEndDate()));
		vm.setEventImgList(eventService.selectEventImgList(vm.getTyphoonNameList()));
		
		return vm;
	}
	

	

	@RequestMapping(value = "/api/damage/history/topDme/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listTopDmeHistoryDamageAction(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		if(vm.getParamDmeCode().equals(ViewerSysKeyword.SEARCH_HISTORY_TOP_DME)){
			vm.setListDmeGungu(yearDmeService.listComDmeTop10ByYear( vm.getParamStDate(), vm.getParamEndDate()));
		}else{
			vm.setListDmeGungu(yearDmeService.listTotalDmeTop10ByYear( vm.getParamStDate(), vm.getParamEndDate(), vm.getParamGungu()));
		}
		
		vm.setTyphoonNameList(typhoonCastInfoService.listTyphoonName(vm.getParamStDate().substring(0,4), vm.getParamEndDate().substring(0,4)));
		

		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/totDme/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listTotDmeHistoryDamageAction(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setListDmeGungu(yearDmeService.listTotalDmeTop10ByYear( vm.getParamStDate(), vm.getParamEndDate(), vm.getParamGungu()));
	    vm.setTyphoonNameList(typhoonCastInfoService.listTyphoonName(vm.getParamStDate(), vm.getParamEndDate()));

	return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/chart/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listChartHistoryDamageAction(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
	
		vm.setListDmeGungu(yearDmeService.listWholeAreaDmeChartByYear(vm.getParamStDate(), vm.getParamEndDate()));
	
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/code/list.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listComDmeCode(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setListDmeDateCode(yearDmeService.listDmeGunguAndDateCode(vm.getParamYear(), null, "HZD006"));
		vm.setListDmeGunguCode(yearDmeService.listDmeSidoCode(vm.getParamYear(), vm.getListDmeDateCode().get(0).getBeg_date(),"HZD006"));

		
		vm.setListCastTyphoonName(yearDmeService.listDmeTyphoonNameCode(vm.getParamYear(), "HZD002"));
		vm.setListDmeSidoCode(yearDmeService.listTyphoonDmeSidoCode(vm.getParamYear(), Integer.parseInt(vm.getListCastTyphoonName().get(0).getTyp_seq()), "HZD002"));
		
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/date/code.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listComDmeDateCode(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		if(vm.getParamDmeCode().equals("HZD002") || vm.getParamDmeCode().equals("HZD007")){
			vm.setListCastTyphoonName(yearDmeService.listDmeTyphoonNameCode(vm.getParamYear(), vm.getParamDmeCode()));
		}else{
			vm.setListDmeDateCode(yearDmeService.listDmeGunguAndDateCode(vm.getParamYear(), null, vm.getParamDmeCode()));
		}
		
		return vm;
	}
	

	@RequestMapping(value = "/api/damage/history/sido/code.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listComDmeSidoCode(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		if(vm.getParamDmeCode().equals("HZD002") || vm.getParamDmeCode().equals("HZD007")){
			vm.setListDmeSidoCode(yearDmeService.listTyphoonDmeSidoCode(vm.getParamYear(), vm.getParamTyphoonSeq(), vm.getParamDmeCode()));
		}else{
			vm.setListDmeSidoCode(yearDmeService.listDmeSidoCode(vm.getParamYear(), vm.getParamStDate(), vm.getParamDmeCode()));
		}
		
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/gungu/code.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listComDmeGunguCode(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setListDmeGunguCode(yearDmeService.listDmeGunguCode(vm.getParamYear(), vm.getParamStDate(), vm.getParamDmeCode(), vm.getParamSido()));
		
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/condition/update.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel updateHistoryDamage(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setUpdateCondtion(yearDmeService.updateCondition(vm.getParamSeq(), vm.getParamDescription()));
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/condition/insert.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel insertHistoryDamage(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		vm.setUpdateCondtion(yearDmeService.registCondition(vm.getParamSeq(), vm.getParamStDate(), vm.getParamEndDate(), vm.getParamDescription(), vm.getParamDmeCode(), vm.getParamCauses()));
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/images/insert.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> createFile(YearDmeViewModel vm, ModelMap model, HttpServletRequest request)
			throws Exception {
		
		
		HttpSession session = request.getSession();
	/*	LoginVO	loginVO = (LoginVO) session.getAttribute("loginVO"); */
	
    	String paramId = "USER2";
	
	//	int rs = fileService.createFile(vm.getUploadFiles(), vm.getParamStDate(), vm.getParamEndDate(), paramId, vm.getParamFileName(), "N");
		HashMap<String, String> map = new HashMap<String, String>();
	//	map.put("fileStatus", Integer.toString(rs));
		return map;

	}
	
	
	@RequestMapping(value = "/api/damage/history/images/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> deleteFile(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
	//	int rs = fileService.deleteImages(vm.getParamFileName(), vm.getParamSeq());
		HashMap<String, String> map = new HashMap<String, String>();
	//	map.put("fileStatus", Integer.toString(rs));
		return map;
	}

	@RequestMapping(value = "/api/damage/history/whole/list/sigungu/rain", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listSigunguDamageRain(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		
		int _countThissen=commonService.countThissen(vm.getParamGungu());
		String lawAreaYn=commonService.thissenLawAreaYn(vm.getParamGungu());
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
	
	@RequestMapping(value = "/api/damage/history/code/snowList.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel snowListComDmeCode(@RequestBody YearDmeViewModel vm) throws Exception {
		vm.setListDmeDateCode(yearDmeService.listDmeGunguAndDateCode(vm.getParamYear(), null, ViewerSysKeyword.DME_CODE_SNOW));
		vm.setListDmeGunguCode(yearDmeService.listDmeSidoCode(vm.getParamYear(), vm.getListDmeDateCode().get(0).getBeg_date(), ViewerSysKeyword.DME_CODE_SNOW));
		
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/code/earthquakeList.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel earthqueakeListComDmeCode(@RequestBody YearDmeViewModel vm) throws Exception {
		List<YearDmeCodeDto> listDmeDateCode = yearDmeService.listDmeGunguAndDateCode(vm.getParamYear(), null, ViewerSysKeyword.DME_CODE_EARTHQUAKE);
		vm.setListDmeDateCode(listDmeDateCode.size()>0 ? listDmeDateCode : yearDmeService.listDmeGunguAndDateCode(null, null, ViewerSysKeyword.DME_CODE_EARTHQUAKE));
		vm.setParamYear(listDmeDateCode.size()>0 ? vm.getParamYear() : vm.getListDmeDateCode().get(0).getBeg_year());
		vm.setListDmeGunguCode(yearDmeService.listDmeSidoCode(vm.getParamYear(), vm.getListDmeDateCode().get(0).getBeg_date(),ViewerSysKeyword.DME_CODE_EARTHQUAKE));
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/typhoon/dmeList.do", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listTyphoonDmeList(@RequestBody YearDmeViewModel vm) throws Exception {
		vm.setListDamage(yearDmeService.listTyphoonDmeList(vm.getParamStDate(),vm.getParamEndDate(), vm.getParamDamageName()));
		return vm;
	}
	
	@RequestMapping(value = "/api/damage/history/earthquake/list", method = RequestMethod.POST)
	public @ResponseBody YearDmeViewModel listDmeEarthquakeHistory(@RequestBody YearDmeViewModel vm, Model model)
			throws Exception {
		vm.setListDmeGungu(yearDmeService.listComDmeAndTotalDmeByYear( vm.getParamStDate(), vm.getParamEndDate(), vm.getParamDmeCode(), 0, vm.getParamGungu()));
		return vm;
	}
	
}