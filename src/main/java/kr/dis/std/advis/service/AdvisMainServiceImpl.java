package kr.dis.std.advis.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.noaa.base.BaseService;

import kr.dis.std.advis.dao.AdvisMainDao;

@Service("advisMainService")
public class AdvisMainServiceImpl extends BaseService implements IAdvisMainService {

	@Autowired
	private AdvisMainDao MainDao;


	
}
