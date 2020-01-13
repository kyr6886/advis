package com.noaa.base.open.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noaa.base.BaseController;
import com.noaa.base.BaseViewModel;
import com.noaa.base.global.SysKeyword;
import com.noaa.base.open.rss.service.IWetherRssService;
import com.noaa.base.open.viewmodel.RequestOpenApiViewModel;
import com.noaa.base.open.viewmodel.WetherViewModel;
@Controller("openApiController")
public class OpenApiController extends BaseController {
	@Autowired
	private IWetherRssService wetherRssService;
	
	@RequestMapping(value="/api/rss/wether/detail/{id}" , method = RequestMethod.GET)
	public @ResponseBody WetherViewModel detailWetherAction(WetherViewModel vm,Model model){
		vm.setListWetherRss(wetherRssService.list(vm.getId()));
		return vm;
	}
	@RequestMapping(value="/api/kakao/get/search" , method = RequestMethod.POST)
	public @ResponseBody String searchGetKakao(@RequestBody RequestOpenApiViewModel vm){
		 String rs="";
		 
		 HttpsURLConnection conn;
		 InputStreamReader insReader=null;
    	 BufferedReader bufferedReader=null;
		 try{
			 
			 String query="?query="+URLEncoder.encode(vm.getParamQuery(), "UTF-8");
			 query+="&sort="+vm.getParamSort();
			 query+="&size="+vm.getParamSize();
			 query+="&page="+vm.getParamPage();
			 
			 URL url = new URL(vm.getParamUrl()+query);
	         conn = (HttpsURLConnection) url.openConnection();
	         conn.setRequestMethod(HttpMethod.GET.toString());
	         conn.setRequestProperty("Authorization", "KakaoAK "+vm.getParamKey());
	         conn.setRequestProperty("Content-Type", "application/json");
	         conn.setRequestProperty("charset", "utf-8");
	         int responseCode=conn.getResponseCode();
	         if(responseCode==200){
	        	  insReader=new InputStreamReader(conn.getInputStream(),"UTF-8");
	        	  bufferedReader=new BufferedReader(insReader);
	        	  StringBuffer  resultBuffer= new StringBuffer();
	        	 
	        	 while(true){
	        		 String result=bufferedReader.readLine();
	        		 if(result==null) break;
	        		 resultBuffer.append(result);
	        	 }
	        	 rs=resultBuffer.toString();
	         }
	         
		 }catch(Exception ex){
			 System.out.println(ex.getMessage());
		 }finally{
			 if(bufferedReader!=null){
				 try{
					 bufferedReader.close();
				 }catch(Exception ex){
					 System.out.println(ex.getMessage());
				 }
			 }
			 if(insReader!=null){
				 try{
					 insReader.close();
				 }catch(Exception ex){
					 System.out.println(ex.getMessage());
				 }
			 }
		 }
		return rs;
	}
}
