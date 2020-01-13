package kr.dis.std.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;

import kr.dis.std.data.dao.IDataManageDao;
import kr.dis.std.data.dao.TobKmaInformDTO;
import kr.dis.std.data.dao.TobKmaTyphoon5dsDTO;

@Service("dataManageService")
public class DataManageServiceImpl extends BaseService implements IDataManageService{
	@Autowired
	private IDataManageDao dataManageDao;

	@Override
	public List<TobKmaTyphoon5dsDTO> listTyphoon(int pageNo, int pageSize) {
		TobKmaTyphoon5dsDTO dto = new TobKmaTyphoon5dsDTO();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		
		return dataManageDao.listTyphoon(dto);
	}
	
	@Override
	public int createTyphoon(String stn_id, String tm_fc, String typ_seq,
									String typ_name, String typ_en, String typ_lat,
									String typ_lon, String typ_ws, String typ_ps, String typ_15,
									String ft1_lat, String ft1_lon, String ft1_ws,
									String ft1_ps, String ft2_lat, String ft2_lon,
									String ft2_ws, String ft2_ps, String ft3_lat,
									String ft3_lon, String ft3_ws, String ft3_ps){
		int rs = 0;
		
		TobKmaTyphoon5dsDTO dto = new TobKmaTyphoon5dsDTO();
		dto.setTm_fc(tm_fc);
		dto.setTyp_seq(typ_seq);
		
		if(dataManageDao.countTyphoon(dto) == 0){
			dto.setStn_id(stn_id);
			dto.setTyp_name(typ_name);
			dto.setTyp_en(typ_en);
			dto.setTm_seq(String.valueOf(dataManageDao.countMaxTmSeq(dto)+1));
			dto.setTyp_lat(typ_lat);
			dto.setTyp_lon(typ_lon);
			dto.setTyp_sp(typ_ws);
			dto.setTyp_ps(typ_ps);
			dto.setTyp_15(typ_15);
			dto.setFt1_lat(ft1_lat);
			dto.setFt1_lon(ft1_lon);
			dto.setFt1_sp(ft1_ws);
			dto.setFt1_ps(ft1_ps);
			dto.setFt2_lat(ft2_lat);
			dto.setFt2_lon(ft2_lon);
			dto.setFt2_sp(ft2_ws);
			dto.setFt2_ps(ft2_ps);
			dto.setFt3_lat(ft3_lat);
			dto.setFt3_lon(ft3_lon);
			dto.setFt3_sp(ft3_ws);
			dto.setFt3_ps(ft3_ps);
			
			rs = dataManageDao.insertTyphoon(dto);
		}
		
		return rs;
	}

	@Override
	public int deleteTyphoon(String tm_fc) {
		TobKmaTyphoon5dsDTO dto = new TobKmaTyphoon5dsDTO();
		dto.setTm_fc(tm_fc);
		
		return dataManageDao.deleteTyphoon(dto);
	}
	
	@Override
	public List<TobKmaInformDTO> listInform(int pageNo, int pageSize) {
		TobKmaInformDTO dto = new TobKmaInformDTO();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		
		return dataManageDao.listInform(dto);
	}
	
	@Override
	public int createInform(String cd_stn, String dt_tm_fc, String nm_man_fc,
									String etc_ttl, String sect_area_txt, String dt_tm_ef_txt){
		int rs = 0;
		
		TobKmaInformDTO dto = new TobKmaInformDTO();
		dto.setCd_stn(cd_stn);
		dto.setDt_tm_fc(dt_tm_fc);
		
		if(dataManageDao.countInform(dto) == 0){
			dto.setNm_man_fc(nm_man_fc);
			dto.setEtc_ttl(etc_ttl);
			dto.setSect_area_txt(sect_area_txt);
			dto.setDt_tm_ef_txt(dt_tm_ef_txt);
			
			rs = dataManageDao.insertInform(dto);
		}
		
		return rs;
	}

	@Override
	public int deleteInform(String dt_regt) {
		TobKmaInformDTO dto = new TobKmaInformDTO();
		dto.setDt_regt(dt_regt);
		
		return dataManageDao.deleteInform(dto);
	}

	@Override
	public int countTyphoon() {
		return dataManageDao.countTyphoon(new TobKmaTyphoon5dsDTO());
	}

	@Override
	public int countInform() {
		return dataManageDao.countInform(new TobKmaInformDTO());
	}
}