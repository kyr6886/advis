package kr.dis.std.data.service;

import java.util.List;

import kr.dis.std.data.dao.TobKmaInformDTO;
import kr.dis.std.data.dao.TobKmaTyphoon5dsDTO;

public interface IDataManageService {
	List<TobKmaTyphoon5dsDTO> listTyphoon(int pageNo, int pageSize);
	List<TobKmaInformDTO> listInform(int pageNo, int pageSize);
	int createTyphoon(String stn_id, String tm_fc, String typ_seq,
						String typ_name, String typ_en, String typ_lat,
						String typ_lon, String typ_ws, String typ_ps, String typ_15,
						String ft1_lat, String ft1_lon, String ft1_ws,
						String ft1_ps, String ft2_lat, String ft2_lon,
						String ft2_ws, String ft2_ps, String ft3_lat,
						String ft3_lon, String ft3_ws, String ft3_ps
	);
	int createInform(String cd_stn, String dt_tm_fc, String nm_man_fc,
						String etc_ttl, String sect_area_txt, String dt_tm_ef_txt);
	int deleteTyphoon(String tm_fc);
	int deleteInform(String dt_regt);
	int countTyphoon();
	int countInform();
}