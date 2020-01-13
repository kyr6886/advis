package com.noaa.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("baseVWorldController")
public class BaseVWorldController extends BaseController{
	@RequestMapping(value="/api/vwolrd/search/poi")
	public @ResponseBody String searchPoi(@RequestBody BaseVWolrdViewModel vm, Model model){
		StringBuilder rs=new StringBuilder();
		StringBuilder urlString=new StringBuilder();
		urlString.append("http://map.vworld.kr/search.do?apiKey="+vm.getParamKey());
		urlString.append("&q="+vm.getParamArea());
		urlString.append("&output=json&category=Poi");
		urlString.append("&pageUnit="+vm.getPageUnit());
		urlString.append("&pageIndex="+vm.getPageIndex());
		URL url = null;
		
		
		 
        try{
        	 // Get방식으로 전송 하기
            url = new URL(urlString.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null) { // response를 차례대로 출력
            	rs.append(line);
            }

        }catch(Exception ex){
        	
        }
		return rs.toString();
	}
}
