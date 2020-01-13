package com.noaa.nema.viewer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.nema.viewer.kma.service.IKmaService;

import com.noaa.nema.viewer.viewmodel.KmaViewModel;

@Controller("kmaApiController")
public class KmaApiController {
	
	@Autowired
	private IKmaService kmaService;
	
	@RequestMapping(value="/api/kma/report/current.do",method=RequestMethod.POST)
	public @ResponseBody KmaViewModel currentReportKmaAction(@RequestBody KmaViewModel vm, Model model){
		SimpleDateFormat simpleDate = new SimpleDateFormat ( "yyyyMMdd", Locale.KOREA );
		Date currentTime = new Date ();
		String today = simpleDate.format (currentTime);
		
		if(vm.getParamDummyDate()!=null && !vm.getParamDummyDate().isEmpty()){
			today=vm.getParamDummyDate();
		}
		
		//특보가 있는지 찾는다.
		if(vm.getParamTyphoonYn()==null){
			vm.setDetailKmaInform(kmaService.detailKmaStatusRainReport(today, "201910031000"));
//			vm.setDetailKmaInform(kmaService.detailKmaStatusRainReport(today, null));
		
		}else if(vm.getParamTyphoonYn().equals("Y")){
//			vm.setDetailKmaInform(kmaService.detailKmaStatusTyphoonReport(today,today,"201909221600"));
			vm.setDetailKmaInform(kmaService.detailKmaStatusTyphoonReport(today,today,vm.getParamDummyDate()));
		}
		return vm;
	}
	
	
	@RequestMapping(value="/api/kma/brnch/list.do",method=RequestMethod.POST)
	public @ResponseBody KmaViewModel listBrnchKmaAction(@RequestBody KmaViewModel vm, Model model){
		
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHH");
		Date time = new Date();
		
		String current_time = format1.format(time);
		String mxDate = kmaService.awsMaxYmdh();
		String today = (vm.getParamDummyDateEnd()!=null && !vm.getParamDummyDateEnd().isEmpty()) ? "2016100511" : mxDate;
	
		vm.setParamMaxYmdh(today);
		vm.setKmaAwsBrnchList(kmaService.kmaAwsBrnchList(vm.getParamSido(), today));
		vm.setKmaAwsGunguList(kmaService.kmaAwsGunguList(vm.getParamSido(), today));
		return vm;
	}
	
	@RequestMapping(value="/api/kma/obs/list.do",method=RequestMethod.POST)
	public @ResponseBody KmaViewModel listWaterObsKmaAction(@RequestBody KmaViewModel vm, Model model){
		

		String mxDate = kmaService.obsWaterMaxYmdh();
		vm.setParamMaxYmdh(mxDate);
		vm.setKmaWaterObsList(kmaService.kmaWaterObsList(mxDate));
		return vm;
	}
	
	@RequestMapping(value="/api/kma/dam/list.do",method=RequestMethod.POST)
	public @ResponseBody KmaViewModel listWaterDamKmaAction(@RequestBody KmaViewModel vm, Model model){
		
		String today = kmaService.obsDamMaxYmdh(vm.getParamEndDate());
		vm.setParamMaxYmdh(today);
		vm.setKmaWaterDamList(kmaService.kmaWaterDamList(vm.getParamDamCode(), today));
		
		String mxDate = kmaService.awsMaxYmdh();
		String td = (vm.getParamDummyDateEnd()!=null && !vm.getParamDummyDateEnd().isEmpty()) ? "2016100511" : mxDate;
	
		vm.setParamMaxYmdh(td);
		
		vm.setKmaAwsThrList(kmaService.obsWaterThrAwsDataList(vm.getParamSido(), td));
		vm.setKmaAwsDayList(kmaService.obsWaterDayAwsDataList(vm.getParamSido(), td));
		return vm;
	}
	
	@RequestMapping(value="/api/kma/report/detail.do",method=RequestMethod.POST)
	public @ResponseBody KmaViewModel currentReportKmaDetailAction(@RequestBody KmaViewModel vm, Model model){
		
		vm.setDetailKmaInform(kmaService.detailKmaStatusTyphoonReport(null, null, vm.getParamDummyDate()));
		return vm;
	}

	
}
