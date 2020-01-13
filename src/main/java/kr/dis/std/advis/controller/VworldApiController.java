package kr.dis.std.advis.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import kr.dis.std.event.dao.EventLocationDto;
import kr.dis.std.event.service.IEventService;
import kr.dis.std.web.viewmodel.AnalysisViewModel;
import kr.dis.std.web.viewmodel.VWorldViewModel;

@Controller("vworldApiController")
public class VworldApiController extends BaseController{
	
	@Autowired
	private IEventService eventService;
	@RequestMapping(value="/api/vworld/find/bird/root",method=RequestMethod.POST)
	public @ResponseBody VWorldViewModel  rootBirdAction(@RequestBody VWorldViewModel vm,Model model){
			List<EventLocationDto> listEventLocations=null;// eventService.listEventLocation("VAI", "20170101", "20171231");
		
			StringBuilder test = new StringBuilder();
		StringBuilder body = new StringBuilder();
		List<String> rs=new ArrayList<String>();
		try {
			
				
				String key="A0D9DD95-A3F1-37ED-B898-E6716A7B6E84";
				//http://api.vworld.kr/req/address?service=address&request=getCoord&type=PARCEL&key=A0D9DD95-A3F1-37ED-B898-E6716A7B6E84&address=
				StringBuilder sb=new StringBuilder();
				sb.append("http://api.vworld.kr/req/search?service=search&request=search&type=PLACE&page=1&size=1000");
				sb.append("&key=");
				sb.append(key);
				sb.append("&query=");
				sb.append(URLEncoder.encode("철새도래지", StandardCharsets.UTF_8.toString()));
			
					URL url = new URL(sb.toString());
					URLConnection urlConnection = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(
							urlConnection.getInputStream(),"UTF-8"));
	
					boolean loop = true;
					while (loop) {
						String data = in.readLine();
						if (data != null) {
							body.append(data);
							
						} else {
							loop = false;
						}
					}
					in.close();
					rs.add(body.toString());
					body=null;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			body = null;
		}

		 vm.setResponse("["+StringUtils.join(rs,",")+"]");
		return vm;
	}
	@RequestMapping(value="/api/vworld/find/address/coord",method=RequestMethod.POST)
	public @ResponseBody VWorldViewModel  coordAddressFindAction(@RequestBody VWorldViewModel vm,Model model){
		List<EventLocationDto> listEventLocations=null;// eventService.listEventLocation("VAI", "20170101", "20171231");
		
		
		StringBuilder body = new StringBuilder();
		List<String> rs=new ArrayList<String>();
		try {
			for (EventLocationDto addr : listEventLocations) {
				Thread.sleep(5000);
				String key="A0D9DD95-A3F1-37ED-B898-E6716A7B6E84";
				//http://api.vworld.kr/req/address?service=address&request=getCoord&type=PARCEL&key=A0D9DD95-A3F1-37ED-B898-E6716A7B6E84&address=
				StringBuilder sb=new StringBuilder();
				sb.append("http://api.vworld.kr/req/address?service=address&request=getCoord&type=PARCEL");
				sb.append("&key=");
				sb.append(key);
				sb.append("&address=");
				sb.append(URLEncoder.encode(addr.getAddr(), StandardCharsets.UTF_8.toString()));
			
					URL url = new URL(sb.toString());
					URLConnection urlConnection = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(
							urlConnection.getInputStream()));
	
					boolean loop = true;
					while (loop) {
						String data = in.readLine();
						if (data != null) {
							body.append(data);
						} else {
							loop = false;
						}
					}
					in.close();
					rs.add(body.toString());
					System.out.println(body.toString());
					body=null;
					body=new StringBuilder();
				
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			body = null;
		}

		
		 vm.setResponse("["+StringUtils.join(rs,",")+"]");

		
		return vm;
	}
}
