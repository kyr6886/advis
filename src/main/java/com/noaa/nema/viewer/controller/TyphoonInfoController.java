package com.noaa.nema.viewer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.service.ICommonService;
import com.noaa.nema.viewer.service.ITyphoonCastDateService;
import com.noaa.nema.viewer.service.ITyphoonCastInfoService;
import com.noaa.nema.viewer.service.IYearDmeService;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;
import com.noaa.nema.viewer.viewmodel.DataAnlViewModel;
import com.noaa.nema.viewer.viewmodel.ParamViewModel;
import com.noaa.nema.viewer.viewmodel.TyphoonApiViewModel;
import com.noaa.nema.viewer.viewmodel.TyphoonSummaryViewModel;

/**
 * @author dev
 *
 */
@Controller("typhoonController")
public class TyphoonInfoController {
	@Autowired
	ITyphoonCastInfoService typhoonCastInfoService;

	@Autowired
	ITyphoonCastDateService typCastDateService;

	@Autowired
	ICommonService commonService;

	@RequestMapping(value = "/typhoon/past.do", method = RequestMethod.GET)
	public String introDamageRainView(DataAnlViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/typhoon/pastTyphoon";
	}
	
	@RequestMapping(value = "/typhoon/find.do", method = RequestMethod.GET)
	public String findTyphoonView(TyphoonSummaryViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/typhoon/findTyphoon";
	}
	
	@RequestMapping(value = "/typhoon/multi/viewer.do", method = RequestMethod.GET)
	public String viewerMultiTyphoonView(TyphoonSummaryViewModel vm, Model model) {
		if(vm.getParams()!=null && !vm.getParams().isEmpty()){
			String[] paramSplit= vm.getParams().split("-");
			vm.setListSimilarTypParams(new ArrayList<ParamViewModel>());
			
			
			for (String str : paramSplit) {
				
				if(str!=null && !str.isEmpty()){
					String _begDate=str.substring(0,6);
					String _seq=str.substring(6);
					ParamViewModel temp=new ParamViewModel();
					temp.setParamBegDate(_begDate);
					temp.setParamSeq(_seq);
					vm.getListSimilarTypParams().add(temp);
				}
			}
		}
		model.addAttribute("vm", vm);
	
		return "/viewer/typhoon/multiViewer";
	}
	@RequestMapping(value = "/typhoon/multi/detail/viewer.do", method = RequestMethod.GET)
	public String viewerDetailMultiTyphoonView(TyphoonSummaryViewModel vm, Model model) {
		if(vm.getParams()!=null && !vm.getParams().isEmpty()){
			String[] paramSplit= vm.getParams().split("-");
			vm.setListSimilarTypParams(new ArrayList<ParamViewModel>());
			
			
			for (String str : paramSplit) {
				
				if(str!=null && !str.isEmpty()){
					String _begDate=str.substring(0,6);
					String _seq=str.substring(6);
					ParamViewModel temp=new ParamViewModel();
					temp.setParamBegDate(_begDate);
					temp.setParamSeq(_seq);
					vm.getListSimilarTypParams().add(temp);
				}
			}
		}
		model.addAttribute("vm", vm);
	
		return "/viewer/typhoon/multiDetailViewer";
	}
	
	@RequestMapping(value = "/typhoon/viewer.do", method = RequestMethod.GET)
	public String viewerTyphoonView(TyphoonSummaryViewModel vm, Model model) {
		if(vm.getParams()!=null && !vm.getParams().isEmpty()){
			String[] paramSplit= vm.getParams().split("-");
			vm.setListSimilarTypParams(new ArrayList<ParamViewModel>());
			
			
			for (String str : paramSplit) {
				
				if(str!=null && !str.isEmpty()){
					String _begDate=str.substring(0,6);
					String _seq=str.substring(6);
					ParamViewModel temp=new ParamViewModel();
					temp.setParamBegDate(_begDate);
					temp.setParamSeq(_seq);
					vm.getListSimilarTypParams().add(temp);
				}
			}
		}
		model.addAttribute("vm", vm);
	
		return "/viewer/typhoon/viewerTyphoon";
	}
	
	
	@RequestMapping(value = "/api/typhoon/viewer.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonSummaryViewModel viewerTyphoonAction(@RequestBody TyphoonSummaryViewModel vm, Model model) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar prevDay = Calendar.getInstance();
		Calendar nextDay = Calendar.getInstance();
		try {
			prevDay.setTime(_dateFormat.parse(vm.getParamStDate()));
			prevDay.add(Calendar.DATE, -1);
			nextDay.setTime(_dateFormat.parse(vm.getParamEndDate()));
			nextDay.add(Calendar.DATE, 1);
			
			vm.setParamStDate(_dateFormat.format(prevDay.getTime()));
			vm.setParamEndDate(_dateFormat.format(nextDay.getTime()));
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//todo:지울것
		//vm.setParamStDate("20181003");
		//vm.setParamEndDate("20181007");
	    vm.setListWorldTyphoonsPosition(typhoonCastInfoService.worldTyphoonForecastList(vm.getParamStDate(), vm.getParamEndDate(), null,vm.getParamName()));
		
	    
	    
	    List<TyphoonCastInfoDto> typhoons=typhoonCastInfoService.listTyphoonCurrentSummaryInfos(vm.getParamStDate(),vm.getParamEndDate());
	
		if(typhoons!=null && typhoons.size()>0){
		for (TyphoonCastInfoDto typhoonCastInfoDto : typhoons) {
			if(typhoonCastInfoDto.getTyp_en().trim().indexOf(vm.getParamName().substring(0,3))>-1){
				String paramData =typhoonCastInfoDto.getBeg_date().substring(0,6);
				vm.setListTyphoonPosition(typhoonCastInfoService.currentTyphoonInfoList(paramData.substring(0,4), paramData.substring(4,6),
						typhoonCastInfoDto.getTyp_seq()));
				break;
			}
		}
			
		}
		
		List<ParamViewModel> listSimilarTyphoons= vm.getListSimilarTypParams();
		vm.setListSimilarTyphoons(new ArrayList<TyphoonCastInfoDto>());
		List<YearDmeTyphoonDto> yearDmeTyps=typhoonCastInfoService.listAllYearDmeTyphoon();
		for (ParamViewModel typhoonCastInfoDto : listSimilarTyphoons) {
			TyphoonCastInfoDto temp=new TyphoonCastInfoDto();

			List<TyphoonCastInfoDto> points= typhoonCastInfoService.currentTyphoonInfoList(typhoonCastInfoDto.getParamYear(), typhoonCastInfoDto.getParamMonth(),typhoonCastInfoDto.getParamSeq());
			if(points!=null && points.size()>0){
			
				//태풍 시작 종료일 재맵핑
				YearDmeTyphoonDto mapping=typhoonCastInfoService.typhoonDateMapping(points, yearDmeTyps);
				temp.setTyp_name(mapping.getTyp_name());
				temp.setBeg_date(mapping.getBeg_date());
				temp.setEnd_date(mapping.getEnd_date());
				temp.setListPosition(points);
				typhoonCastInfoService.typhoonDamageMapping(temp);
				vm.getListSimilarTyphoons().add(temp);
			}
		}
		
		
		
	    return vm;
	}
	
	@RequestMapping(value = "/typhoon/search/date.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonSummaryViewModel dateSearchTyphoonAction(TyphoonSummaryViewModel vm, Model model) {
		return vm;
	}
	
	@RequestMapping(value = "/typhoon/world/forecast.do", method = RequestMethod.GET)
	public String forecastTyphoonView(TyphoonApiViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/typhoon/forecastTyphoon";
	}
	@RequestMapping(value = "/typhoon/current.do", method = RequestMethod.GET)
	public String currentTyphoonView(DataAnlViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/typhoon/currentTyphoon";
	}
	@RequestMapping(value = "/typhoon/similar.do", method = RequestMethod.GET)
	public String similarTyphoonView(DataAnlViewModel vm, Model model) {
		model.addAttribute("vm", vm);
		return "/viewer/typhoon/similarTyphoon";
	}
	
	
	/**
	 * 전체 태풍목록(총 피해액,인명피해 포함)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/find.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonSummaryViewModel findTyphoonAction(TyphoonSummaryViewModel vm, Model model) {
		vm.setListYearDmeTyphoon(typhoonCastInfoService.listAllYearDmeTyphoon());
		
		return vm;
	}
	
	
	
	/**
	 * 태풍 이름으로 검색(상세내역 포함)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/search/position.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonSummaryViewModel positionSearchTyphoonAction(@RequestBody TyphoonSummaryViewModel vm, Model model) {
		String paramData = vm.getParamYear() + vm.getParamMonth();
		
		List<TyphoonCastInfoDto> listTyphoonCastInfo=null;
		List<TyphoonCastInfoDto> rs=new ArrayList<TyphoonCastInfoDto>();
		if (Integer.parseInt(paramData) > ViewerSysKeyword.TYPHOON_PAST_DATE_ST) {
			listTyphoonCastInfo=typhoonCastInfoService.currentTyphoonInfoList(vm.getParamYear(), vm.getParamMonth(),null);
		} else {
			listTyphoonCastInfo=typhoonCastInfoService.pastTyphoonInfoList(vm.getParamYear(), vm.getParamMonth(),null);
		}
		for (TyphoonCastInfoDto typhoonCastInfoDto : listTyphoonCastInfo) {
			if(typhoonCastInfoDto.getTyp_name().trim().indexOf(vm.getParamName().trim())>-1){
				rs.add(typhoonCastInfoDto);
			}
		}
		
		
		vm.setListTyphoonPosition(rs);
		TyphoonCastInfoDto info=new TyphoonCastInfoDto();
		info.setBeg_date(vm.getBegDate());
		info.setEnd_date(vm.getEndDate());
		typhoonCastInfoService.typhoonDamageMapping(info);
		vm.setDetailTyphoonInfo(info);	
		return vm;
	}
	
	
	/**
	 * 검색기간 각국태풍 목록(경로 미포함)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/info.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel forecastTyphoonByPeriodAction(@RequestBody TyphoonApiViewModel vm, Model model) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar prevDay = Calendar.getInstance();
		Calendar nextDay = Calendar.getInstance();
		try {
			prevDay.setTime(_dateFormat.parse(vm.getParamStDate()));
			prevDay.add(Calendar.DATE, -1);
			nextDay.setTime(_dateFormat.parse(vm.getParamEndDate()));
			nextDay.add(Calendar.DATE, 1);
			
			vm.setParamStDate(_dateFormat.format(prevDay.getTime()));
			vm.setParamEndDate(_dateFormat.format(nextDay.getTime()));
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//TODO:지월것
		//vm.setParamStDate("20181003");
		//vm.setParamEndDate("20181007");
		vm.setTyphoonList(typhoonCastInfoService.listTyphoonCurrentSummaryInfos(vm.getParamStDate(), vm.getParamEndDate()));
		return vm;
	}
	
	/**
	 * 각국태풍 예상경로 목록 
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/world/forecast.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel forecastTyphoonAction(@RequestBody TyphoonApiViewModel vm, Model model) {
	/*	SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar prevDay = Calendar.getInstance();
		Calendar nextDay = Calendar.getInstance();
		try {
			prevDay.setTime(_dateFormat.parse(vm.getParamStDate()));
			prevDay.add(Calendar.DATE, -1);
			nextDay.setTime(_dateFormat.parse(vm.getParamEndDate()));
			nextDay.add(Calendar.DATE, 1);
			
			vm.setParamStDate(_dateFormat.format(prevDay.getTime()));
			vm.setParamEndDate(_dateFormat.format(nextDay.getTime()));
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}*/
		
		//TODO:지울것
		//vm.setParamStDate("20181003");
		//vm.setParamEndDate("20181007");
	    vm.setTyphoonList(typhoonCastInfoService.worldTyphoonForecastList(vm.getParamStDate(), vm.getParamEndDate(), null,vm.getParamTyphoonName()));
		model.addAttribute("vm", vm);
		return vm;
	}


	
	@RequestMapping(value = "/api/typhoon/list/similar.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listSimilarTyphoonAction(@RequestBody TyphoonApiViewModel vm, Model model) {
	    vm.setSimilarTyphoonList(typhoonCastInfoService.listSimilarTyphoonByPosition(vm.getParamYear(),vm.getParamMonth(),Integer.parseInt(vm.getParamSeq())));
		vm.setTyphoonList(typhoonCastInfoService.currentTyphoonInfoList(vm.getParamYear(),vm.getParamMonth(),vm.getParamSeq()));
		return vm;
	}
	
	/**
	 * 유사태풍 정보(경로,개황,피해내역 포함)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/list/similar/forcast.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel forcastListSimilarTyphoonAction(@RequestBody TyphoonApiViewModel vm, Model model) {
	   
	    vm.setSimilarTyphoonList(typhoonCastInfoService.listSimilarTyphoonByForcast(vm.getParamYear(),vm.getParamMonth(),(int)(Float.parseFloat(vm.getParamSeq())),(int)(Float.parseFloat(vm.getParamTimeSeq()))));
		return vm;
	}
	
	
	
	@RequestMapping(value = "/api/typhoon/code/year/list.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listYearTypCodesAction(@RequestBody TyphoonApiViewModel vm, Model model) {

		vm.setTypYearList(typCastDateService.listCastTyphoonYear());
		vm.setTypMonthList(
				typCastDateService.listCastTyphoonMonth(typCastDateService.listCastTyphoonYear().get(0).getBeg_year()));
		vm.setTypNameList(
				typCastDateService
						.listCastTyphoonName(typCastDateService.listCastTyphoonYear().get(0).getBeg_year(),
								typCastDateService
										.listCastTyphoonMonth(
												typCastDateService.listCastTyphoonYear().get(0).getBeg_year())
										.get(0).getBeg_month()));
		return vm;
	}

	@RequestMapping(value = "/api/typhoon/code/month/list.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listMonthTypCodesAction(@RequestBody TyphoonApiViewModel vm, Model model) {

		vm.setTypMonthList(typCastDateService.listCastTyphoonMonth(vm.getParamYear()));
		
		if(vm.getParamMonth()!=null){
			vm.setTypNameList(typCastDateService.listCastTyphoonName(vm.getParamYear(),	vm.getParamMonth()));
		}else{
			vm.setTypNameList(typCastDateService.listCastTyphoonName(vm.getParamYear(),	typCastDateService.listCastTyphoonMonth(vm.getParamYear()).get(0).getBeg_month()));
		}
		return vm;
	}

	@RequestMapping(value = "/api/typhoon/code/name/list.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listNameTypCodesAction(@RequestBody TyphoonApiViewModel vm, Model model) {

		vm.setTypNameList(typCastDateService.listCastTyphoonName(vm.getParamYear(), vm.getParamMonth()));

		return vm;
	}

	
	/**
	 * 선택 태풍 경로 정보(태풍 seq 필수)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/cast/inform/list.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listTypInformCastAction(@RequestBody TyphoonApiViewModel vm, Model model) {

		String paramData = vm.getParamYear() + vm.getParamMonth();
		
		if(vm.getParamTyphoonName()!=null){
			TyphoonCastInfoDto detail=typhoonCastInfoService.detailLastPosition(vm.getParamYear(), vm.getParamMonth(), vm.getParamTyphoonName());
			if(detail!=null){
				vm.setParamSeq(detail.getTyp_seq());
		
			}
				
		}
		if(vm.getParamSeq()!=null){
			if (Integer.parseInt(paramData) > ViewerSysKeyword.TYPHOON_PAST_DATE_ST) {
				vm.setTyphoonList(typhoonCastInfoService.currentTyphoonInfoList(vm.getParamYear(), vm.getParamMonth(),
						vm.getParamSeq()));
			} else {
				vm.setTyphoonList(typhoonCastInfoService.pastTyphoonInfoList(vm.getParamYear(), vm.getParamMonth(),
						vm.getParamSeq()));
			}
			if(vm.getTyphoonList()!=null &&vm.getTyphoonList().size()>0){
				YearDmeTyphoonDto temp=new YearDmeTyphoonDto();
				temp.setBeg_date(vm.getTyphoonList().get(0).getTm_fc().substring(0, 8));
				temp.setEnd_date(vm.getTyphoonList().get(vm.getTyphoonList().size()-1).getTm_fc().substring(0, 8));
				temp.setTyp_name(vm.getTyphoonList().get(0).getTyp_name());
				vm.setTyphoonInfo(temp);
			}
		}
		
		return vm;
	}

	
	/**
	 * 현재태풍 상세 정보(경로 포함)
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/typhoon/current/inform/list.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listInformCurrentTyphoonAction(@RequestBody TyphoonApiViewModel vm,
			Model model) {
		//태풍명 테이블 동기화
		//typhoonCastInfoService.createCurrentTyphoonInfo();
		SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar prevDay = Calendar.getInstance();
		Calendar nextDay = Calendar.getInstance();
	
			//prevDay.setTime(_dateFormat.parse(vm.getParamStDate()));
			prevDay.add(Calendar.DATE, -180);
			
			nextDay.add(Calendar.DATE, 30);
			
			vm.setParamStDate(_dateFormat.format(prevDay.getTime()));
			vm.setParamEndDate(_dateFormat.format(nextDay.getTime()));
			
	
		if(vm.getParamDummyDate()!=null && !vm.getParamDummyDate().isEmpty()){
			vm.setParamStDate(vm.getParamDummyDate().substring(0,8));
			vm.setParamEndDate(vm.getParamDummyDateEnd().substring(0,8));
		}
																 
		//태풍정보
		List<TyphoonCastInfoDto> typhoons=typhoonCastInfoService.listTyphoonCurrentSummaryInfos(vm.getParamStDate(),vm.getParamEndDate());
		vm.setListCurrentTyphoons(typhoons);
		if(typhoons!=null && typhoons.size()>0){
			
			
			
			String paramData =typhoons.get(0).getBeg_date().substring(0,6);
			vm.setTyphoonList(typhoonCastInfoService.currentTyphoonInfoList(paramData.substring(0,4), paramData.substring(4,6),
					typhoons.get(0).getTyp_seq()));
			
			List<YearDmeTyphoonDto> yearDmeTyphoonDto=typhoonCastInfoService.listAllYearDmeTyphoon();
		    vm.setTyphoonInfo(typhoonCastInfoService.typhoonDateMapping(vm.getTyphoonList(), yearDmeTyphoonDto));
		    
		}

		return vm;
	}
	
	
	/**
	 * 검색기간 각국태풍 목록(경로 미포함) 
	 * tob_kma_typhoon_5ds, tob_kma_typhoon
	 * @param vm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/api/period/typhoon/info.do", method = RequestMethod.POST)
	public @ResponseBody TyphoonApiViewModel listTyphoonByPeriodAction(@RequestBody TyphoonApiViewModel vm, Model model) {

		vm.setTyphoonList(typhoonCastInfoService.listTyphoonByPeriodAction(vm.getParamStDate()));
		return vm;
	}

	
}
