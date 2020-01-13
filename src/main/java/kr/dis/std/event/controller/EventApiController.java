package kr.dis.std.event.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.switchuser.SwitchUserAuthorityChanger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.Authorize;
import com.noaa.base.BaseController;

import kr.dis.std.advis.dao.DamageAnlRainDto;
import kr.dis.std.advis.service.IDamageAnlRainService;
import kr.dis.std.category.viewmodel.CategoryViewModel;
import kr.dis.std.event.dao.EventActionDto;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.EventsDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.report.service.IReportService;
import kr.dis.std.web.viewmodel.EventViewModel;
import kr.dis.std.web.viewmodel.EventsViewModel;

@Controller("eventApiController")
public class EventApiController extends BaseController{
	
	@Autowired
	private IEventService eventService;
	
	@Autowired
	private IDamageAnlRainService damageAnlRainService;
	
	@Autowired
	private IReportService reportService;
	
	
	@Authorize
	@RequestMapping(value="/api/std/eventItem/Create",method=RequestMethod.POST)
	public @ResponseBody EventViewModel createEventItemAction(@RequestBody EventViewModel vm,Model model){
		List<EventItemDto> paramBeans=new ArrayList<EventItemDto>(); 
		EventDto event=new EventDto();
		event.setTitle(vm.getParamTitle());
		event.setDisaster_type(vm.getParamDisType());
		event.setEvt_date(vm.getParamDisDateStr());
		event.setFile_grp_id(vm.getParamFileId());
		event.setEvt_group(vm.getParamEvtGrp());
		for (String item : vm.getParamEvents()) {
			String[] val=item.split("\\|");
			EventItemDto dto=new EventItemDto();
			dto.setCtg_id(val[0]); 
			dto.setContents(val[1]);
			dto.setEvt_num(1);
			paramBeans.add(dto);
		}
		eventService.createEventFile(event,getCommonParams());
		eventService.createEventItem(paramBeans,event, getCommonParams());
		return vm;
	}
	
	
	
	@Authorize
	@RequestMapping(value="/api/std/event/create",method=RequestMethod.POST)
	public @ResponseBody EventViewModel createEventAction(@RequestBody EventViewModel vm,Model model){
		EventDto param=new EventDto();
		param.setCtg_id(vm.getParamCtgId());
		param.setTitle(vm.getParamTitle());
		param.setEvt_date(vm.getParamDisDateStr());
		param.setIs_root_yn(vm.getParamIsRoot());
		param.setIs_cs_yn(vm.getParamIsCs());
		//eventService.createEvent(param, getCommonParams());
		vm.setResultCount(eventService.createEvent(param, getCommonParams()));
		
		return vm;
	}
	@Authorize
	@RequestMapping(value="/api/std/event/group/delete",method=RequestMethod.POST)
	public @ResponseBody EventViewModel deleteGroupEventAction(@RequestBody EventViewModel vm,Model model){
	
		vm.setResultCount(eventService.deleteEventGroup(vm.getParamEvtGrp()));
		
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/api/std/event/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listEventGroupAction(@RequestBody EventViewModel vm,Model model){
	
		vm.setEventList(eventService.listEventGroupInfos(vm.getParamCtgId(),vm.getParamIsRoot(), vm.getParamIsCs()));
		return vm;
	}
	
	
	
	@RequestMapping(value="/api/std/tree/event/searchList",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listEventListAction(@RequestBody EventViewModel vm,Model model){
		EventDto dto=new EventDto();
		dto.setCtg_id(vm.getParamCtgId());
		vm.setResultList(eventService.searchItemList(dto));
		return vm;
	}
	
	@RequestMapping(value="/api/std/event/file/fileList",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listEventFileListAction(@RequestBody EventViewModel vm,Model model){
		EventDto dto=new EventDto();
		dto.setEvt_id(vm.getParamEvtId());
		vm.setResultList(eventService.listEventFileListAction(dto));
		return vm;
	}
	
	@RequestMapping(value="/api/std/event/all/delete",method=RequestMethod.POST)
	public @ResponseBody EventViewModel deleteAllEventAction(@RequestBody EventViewModel vm,Model model){
		EventDto dto=new EventDto();
		dto.setEvt_id(vm.getParamEvtId());
		vm.setResultCount(eventService.deleteAllEventAction(dto));
		
		return vm;
	}
	
	@RequestMapping(value="/api/std/event/responseStatusList",method=RequestMethod.POST)
	public @ResponseBody EventViewModel ListDisEventResponseStatus(@RequestBody EventViewModel vm, Model model)throws Exception{
		
		EventDto dto=new EventDto();
		dto.setDamage_code(vm.getParamDisType()); //HZD002, HZD006
		dto.setEvt_year(vm.getParamYear());
		dto.setBeg_date(vm.getParamStDate());
		dto.setEnd_date(vm.getParamEndDate());
		dto.setContents(vm.getParamTitle());
		vm.setEventList(eventService.getEventListByDate(dto));
		
		vm.setResultEventInfo(eventService.getListEventItemByLastEvent(vm.getEventList())); //마지막 dis_event의 대응현황 list
		return vm;
	}
	
	@RequestMapping(value="/advis/dis/eventActionView/{paramEvtGrp}",method=RequestMethod.GET)
	public String getDisEventActionView(EventViewModel vm,Model model, HttpServletResponse response,HttpServletRequest request)throws Exception{
		model.addAttribute("vm",vm);
		return "/advis/event/action";
	}
	
	@RequestMapping(value="/api/advis/dis/eventActionList",method=RequestMethod.POST)
	public @ResponseBody EventViewModel selectListDisEventAction(@RequestBody EventViewModel vm, Model model)throws Exception{
		
		vm.setResultList(eventService.selectListDisEventActionByGroup(vm.getParamCtgId(), vm.getParamEvtGrp()));
		//List<EventDto> selectListDisEventActionByDate = eventService.selectListDisEventActionByDate(vm.getParamCtgId(), vm.getParamYear(), vm.getParamMonth(), vm.getParamDay(), vm.getParamHour());
		
		vm.setEventList(eventService.selectListDisEventByEvtGroup(vm.getParamCtgId(), vm.getParamEvtGrp()));
		return vm;
	}
	@RequestMapping(value="/api/advis/dis/event/groupid/info",method=RequestMethod.POST)
	public @ResponseBody EventViewModel infoGroupIdDisEventAction(@RequestBody EventViewModel vm, Model model)throws Exception{
		vm.setResultEventInfo(eventService.selectDisEventGrpId(vm.getParamCtgId(), vm.getParamStartDate(),vm.getParamEndDate(), vm.getParamTitle()));
		if(vm.getResultEventInfo()!=null ){
			vm.setEventList(eventService.selectListDisEventByEvtGroup(vm.getResultEventInfo().getCtg_id(),vm.getResultEventInfo().getEvt_group()));
			vm.setResultList(eventService.selectListDisEventActionByGroup(vm.getResultEventInfo().getCtg_id(),vm.getResultEventInfo().getEvt_group()));
		}
		
		return vm;
	}
	
	@RequestMapping(value="/api/advis/dis/event/location/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listLocationEventAction(@RequestBody EventViewModel vm, Model model)throws Exception{
		vm.setListEventLocation(eventService.listEventLocation(vm.getParamDisType(),vm.getParamStartDate(),vm.getParamEndDate()));
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/api/advis/dis/event/report/create",method=RequestMethod.POST)
	public @ResponseBody EventViewModel createDisReportAction(@RequestBody EventViewModel vm,Model model){
		
		List<EventItemDto> paramBeans=new ArrayList<EventItemDto>(); 
		EventDto event=new EventDto();
		
		String eventID=vm.getParamDisType()+"-"+vm.getParamDisDateStr()+"-"+1;
		event.setTitle(vm.getParamTitle());
		event.setCtg_id(vm.getParamDisType());
		event.setEvt_date(vm.getParamDisDateStr());
		event.setEvt_id(eventID);
		event.setEvt_group(vm.getParamEvtGrp());
		event.setEvt_num(1);
		event.setCom_1(vm.getParamCom_1());
		event.setCom_2(vm.getParamCom_2());
		event.setCom_3(vm.getParamCom_3());
		event.setArea_codes(vm.getParamAreaCodes());
		event.setIs_root_yn(vm.getParamIsRoot());
		event.setIs_cs_yn(vm.getParamIsCs());
		event.setDis_level_code(vm.getParamStatus());
		
		for (String item : vm.getParamEvents()) {
			String[] val=item.split("\\|");
			EventItemDto dto=new EventItemDto();
			dto.setEvt_id(eventID);
			dto.setCtg_id(val[0]); 
			dto.setContents(val[1]);
			dto.setEvt_num(1);
			paramBeans.add(dto);
		}
		
		List<EventActionDto> paramActionBeans = new ArrayList<EventActionDto>();
		int index = 1;
		for (String item : vm.getParamActionList()) {
			String[] val=item.split("\\|");
			EventActionDto dto=new EventActionDto();
			
			dto.setCtg_id(vm.getParamDisType()); 
			dto.setEvt_group(vm.getParamEvtGrp());
			dto.setYear(vm.getParamDisDateStr().substring(0,4));
			dto.setMonth(vm.getParamDisDateStr().substring(4,6));
			dto.setDay(vm.getParamDisDateStr().substring(6,8));
			dto.setHour(vm.getParamDisDateStr().substring(8,10));
			dto.setDis_level_code(vm.getParamStatus());
			dto.setSort(index);
			for(int i=0; i<val.length; i++){
				if(i==0 && val[i]!=""){ dto.setDis_act_date(val[0]);}
				else if(i==1 && val[i]!=""){ dto.setDis_org(val[1]);}
				else if(i==2 && val[i]!=""){ dto.setDis_act_lv1(val[2]);}
				else if(i==3 && val[i]!=""){ dto.setDis_act_lv2(val[3]);}
				else if(i==4 && val[i]!=""){ dto.setDis_act_lv3(val[4]);}
				else if(i==5 && val[i]!=""){ dto.setDis_act_lv4(val[5]);}
				else if(i==6 && val[i]!=""){ dto.setDis_act_lv5(val[6]);}
				else if(i==7 && val[i]!=""){ dto.setDis_act_lv6(val[7]);}
			}
			paramActionBeans.add(dto);
			index++;
		}
		
		eventService.deleteAllEventAction(event);
		vm.setResultCount(reportService.createReport(paramBeans, event, paramActionBeans, getCommonParams()));
		
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/api/advis/dis/event/report/list",method=RequestMethod.POST)
	public @ResponseBody EventViewModel listDisReportAction(@RequestBody EventViewModel vm,Model model){
		vm.setResultList(reportService.listReportByCtgId(vm.getParamCtgId(), vm.getParamIsRoot(), vm.getParamIsCs()));
		return vm;
	}
	
	@Authorize
	@RequestMapping(value="/api/advis/dis/event/report/detail",method=RequestMethod.POST)
	public @ResponseBody EventViewModel detailDisReportAction(@RequestBody EventViewModel vm,Model model){

		EventDto dto = eventService.detailEventByEvtId(vm.getParamEvtId());
		if(dto!=null){
			vm.setResultEventInfo(dto);
			EventItemDto paramItemDto = new EventItemDto();
			paramItemDto.setEvt_id(vm.getParamEvtId());
			vm.setEventItemList(eventService.selectEventItemList(paramItemDto));
			vm.setResultList(eventService.selectListDisEventActionByDate(dto.getCtg_id(), dto.getEvt_date().substring(0,4), dto.getEvt_date().substring(4,6), 
					dto.getEvt_date().substring(6,8), dto.getEvt_date().substring(8,10), dto.getEvt_group()));
		}
		
		return vm;
	}
	
	
}

