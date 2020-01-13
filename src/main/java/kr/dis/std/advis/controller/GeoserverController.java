package kr.dis.std.advis.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeoserverController {
	@RequestMapping(value="/geoserver/proxy",method=RequestMethod.GET)
	public ModelAndView Connecting(ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response ,
			HttpSession session
			) throws Exception {
		System.out.println("mapController");
		model.setViewName("/advis/map/proxy");
		
		return model;
	}
}
