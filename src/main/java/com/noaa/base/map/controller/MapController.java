package com.noaa.base.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noaa.base.BaseController;

@Controller("mapController")
public class MapController extends BaseController {
	@RequestMapping(value="/map/select/position",method=RequestMethod.GET)
	public String posSelectorView(){
		return "/base/map/posSelector";
	}
}
