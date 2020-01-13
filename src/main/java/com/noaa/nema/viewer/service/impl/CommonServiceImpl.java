package com.noaa.nema.viewer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.area.dao.SectorDamageModel;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.area.service.IAreaCodeService;
import com.noaa.nema.viewer.base.ViewerSysKeyword;
import com.noaa.nema.viewer.service.ICommonService;
import com.noaa.nema.viewer.year.dme.dao.ICommonDataDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePubDto;
@Service("commonService")
public class CommonServiceImpl implements ICommonService {

	@Autowired
	private ICommonDataDao commonDataDao;
	@Autowired
	private IAreaCodeService areaCodeService;

	@Override
	public int countThissen(String paramSiguguCode) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("law_code", paramSiguguCode.substring(0, 2));
		return commonDataDao.countThissen(paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode) {

		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			//paramMap.put("damage_code", paramDemCode);

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			rs = commonDataDao.listDmeWithGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue) {

		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			//paramMap.put("damage_code", paramDemCode);

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.listDmeWithGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}

			rs = commonDataDao.listDmeWithGunguByArea(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.listDmeWithGunguByArea(paramMap);
		}
		return rs;
	}

	@Override
	public List<SectorDamageModel> listRainDemSector(List<YearDmeDto> paramList) {
		List<SectorDamageModel> rs = new ArrayList<SectorDamageModel>();
		for (int i = 0; i < 4; i++) {
			SectorDamageModel temp = new SectorDamageModel();
			if (i == 0) {
				temp.setSector(ViewerSysKeyword.RAIN_SECTION_ST_1);
				temp.setEndSector(ViewerSysKeyword.RAIN_SECTION_ST_2 - 1);
			}
			if (i == 1) {
				temp.setSector(ViewerSysKeyword.RAIN_SECTION_ST_2);
				temp.setEndSector(ViewerSysKeyword.RAIN_SECTION_ST_3 - 1);
			}
			if (i == 2) {
				temp.setSector(ViewerSysKeyword.RAIN_SECTION_ST_3);
				temp.setEndSector(ViewerSysKeyword.RAIN_SECTION_ST_4 - 1);
			}
			if (i == 3) {
				temp.setSector(ViewerSysKeyword.RAIN_SECTION_ST_4);
				temp.setEndSector(99999);
			}

			rs.add(temp);
		}
		for (SectorDamageModel sectorDamageModel : rs) {
			sectorDamageModel.setListDmePbm(new ArrayList<YearDmeDto>());
			sectorDamageModel.setListDmePub(new ArrayList<YearDmeDto>());
			sectorDamageModel.setListDmePerson(new ArrayList<YearDmeDto>());
			sectorDamageModel.setCountDamage(paramList.size());
			for (YearDmeDto yearDmeDto : paramList) {
				if (yearDmeDto.getRn_day() >= sectorDamageModel.getSector()
						&& yearDmeDto.getRn_day() <= sectorDamageModel.getEndSector()) {
					if (yearDmeDto.getCom_dme() > 0) {
						sectorDamageModel.getListDmePerson().add(yearDmeDto);
						sectorDamageModel
								.setTotalDmePerson(sectorDamageModel.getTotalDmePerson() + yearDmeDto.getCom_dme());
					}
					if (yearDmeDto.getPub_total() > 0) {
						sectorDamageModel.getListDmePub().add(yearDmeDto);
						sectorDamageModel
								.setTotalDmePub(sectorDamageModel.getTotalDmePub() + yearDmeDto.getPub_total());
					}
					if ((yearDmeDto.getPri_total() + yearDmeDto.getCom_total()) > 0) {

						sectorDamageModel.getListDmePbm().add(yearDmeDto);
						sectorDamageModel.setTotalDmePbm(sectorDamageModel.getTotalDmePbm() + yearDmeDto.getPri_total()
								+ yearDmeDto.getCom_total());

					}
				}
			}
			sectorDamageModel.setCountPerson(sectorDamageModel.getListDmePerson().size());
			sectorDamageModel.setCountPublic(sectorDamageModel.getListDmePub().size());
			sectorDamageModel.setCountPrivate(sectorDamageModel.getListDmePbm().size());
			createGroupCount(sectorDamageModel);
			sectorDamageModel.setListDmePerson(createGroupPersonSum(sectorDamageModel.getListDmePerson()));
			sectorDamageModel.setListDmePub(createGroupPublicSum(sectorDamageModel.getListDmePub()));
			sectorDamageModel.setListDmePbm(createGroupPrivateSum(sectorDamageModel.getListDmePbm()));
		}

		return rs;
	}

	private void createGroupCount(SectorDamageModel paramTarget) {
		HashMap<String, String> damageArea = new HashMap<String, String>();
		for (YearDmeDto yearDmeDto : paramTarget.getListDmePub()) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}
		for (YearDmeDto yearDmeDto : paramTarget.getListDmePbm()) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}
		for (YearDmeDto yearDmeDto : paramTarget.getListDmePerson()) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}
		paramTarget.setDamageAreaCount(damageArea.size());
	}

	private List<YearDmeDto> createGroupPersonSum(List<YearDmeDto> paramTarget) {
		HashMap<String, String> damageArea = new HashMap<String, String>();
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		for (YearDmeDto yearDmeDto : paramTarget) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}

		for (Map.Entry<String, String> entry : damageArea.entrySet()) {
			String _key = entry.getKey();
			YearDmeDto temp = new YearDmeDto();
			for (YearDmeDto yearDmeDto : paramTarget) {
				if (_key.equals(yearDmeDto.getSigungu_code())) {
					temp.setSido(yearDmeDto.getSido());
					temp.setSigungu(yearDmeDto.getSigungu());
					temp.setCount(temp.getCount() + 1);
					temp.setCom_dme(temp.getCom_dme() + yearDmeDto.getCom_dme());
				}
			}

			rs.add(temp);

		}
		return rs;
	}

	private List<YearDmeDto> createGroupPublicSum(List<YearDmeDto> paramTarget) {
		HashMap<String, String> damageArea = new HashMap<String, String>();
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		for (YearDmeDto yearDmeDto : paramTarget) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}

		for (Map.Entry<String, String> entry : damageArea.entrySet()) {
			String _key = entry.getKey();
			YearDmeDto temp = new YearDmeDto();
			for (YearDmeDto yearDmeDto : paramTarget) {
				if (_key.equals(yearDmeDto.getSigungu_code())) {
					temp.setSido(yearDmeDto.getSido());
					temp.setSigungu(yearDmeDto.getSigungu());
					temp.setCount(temp.getCount() + 1);
					temp.setPub_total(temp.getPub_total() + yearDmeDto.getPub_total());
				}
			}

			rs.add(temp);

		}
		return rs;
	}

	private List<YearDmeDto> createGroupPrivateSum(List<YearDmeDto> paramTarget) {
		HashMap<String, String> damageArea = new HashMap<String, String>();
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		for (YearDmeDto yearDmeDto : paramTarget) {
			damageArea.put(yearDmeDto.getSigungu_code(), "");
		}

		for (Map.Entry<String, String> entry : damageArea.entrySet()) {
			String _key = entry.getKey();
			YearDmeDto temp = new YearDmeDto();
			for (YearDmeDto yearDmeDto : paramTarget) {
				if (_key.equals(yearDmeDto.getSigungu_code())) {
					temp.setSido(yearDmeDto.getSido());
					temp.setSigungu(yearDmeDto.getSigungu());
					temp.setCount(temp.getCount() + 1);
					temp.setPri_total(temp.getPri_total() + yearDmeDto.getPri_total());
					temp.setCom_total(temp.getCom_total() + yearDmeDto.getCom_total());
				}
			}

			rs.add(temp);

		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode, String paramStDate, String paramEndDate,
			String paramDemCode) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramGunguCode != null && paramGunguCode.size() > 0) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("gunguCodes", paramGunguCode);
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			rs = commonDataDao.sumDamageByGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue) {

		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramGunguCode != null && paramGunguCode.size() > 0) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("gunguCodes", paramGunguCode);
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.sumDamageByGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramSidoCode != null && paramSidoCode.size() > 0) {

			for (String sido : paramSidoCode) {
				int _countThissen = countThissen(sido);
				String lawAreaYn = thissenLawAreaYn(sido);
				if(lawAreaYn==null){
					lawAreaYn=thissenLawAreaYn(sido.substring(0,2));
				}
				if (paramDemCode.equals(ViewerSysKeyword.DME_CODE_RAIN)) {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(rs.size(), listDmeWithGunguByArea(sido, paramStDate, paramEndDate,ViewerSysKeyword.DME_CODE_RAIN));
						} else {
							
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, ViewerSysKeyword.DME_CODE_RAIN));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,ViewerSysKeyword.DME_CODE_RAIN));
					}
				} else {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, paramDemCode));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode));
					}
				}

			}

		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramSidoCode != null && paramSidoCode.size() > 0) {

			for (String sido : paramSidoCode) {
				int _countThissen = countThissen(sido);
				String lawAreaYn = thissenLawAreaYn(sido);
				if(lawAreaYn==null){
					lawAreaYn=thissenLawAreaYn(sido.substring(0,2));
				}
				if (paramDemCode.equals(ViewerSysKeyword.DME_CODE_RAIN)) {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
									ViewerSysKeyword.DME_CODE_RAIN, paramStRainValue, paramEndRainValue));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, ViewerSysKeyword.DME_CODE_RAIN,
									paramStRainValue, paramEndRainValue));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
								ViewerSysKeyword.DME_CODE_RAIN, paramStRainValue, paramEndRainValue));
					}
				} else {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode,
									paramStRainValue, paramEndRainValue));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, paramDemCode, paramStRainValue,
									paramEndRainValue));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode,
								paramStRainValue, paramEndRainValue));
					}
				}

			}

		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeHisWithGungu(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramStDate.length() >= 4
				&& paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.length() < 5 ? paramSidoCode : paramSidoCode.substring(0, 5));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}

			if (paramDemCode != null) {
				String[] paramDemCodes = paramDemCode.split(",");
				if (paramDemCodes.length > 1) {
					paramMap.put("damage_codes", paramDemCodes);
				} else {
					paramMap.put("damage_code", paramDemCode);
				}
			} else {
				paramMap.put("damage_code", paramDemCode);
			}

			rs = commonDataDao.listDmeHisWithGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeHisWithGunguByArea(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramStDate.length() >= 4
				&& paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sido_code", paramSidoCode.substring(0, 2));
			paramMap.put("law_code", paramSidoCode.length() < 5 ? paramSidoCode : paramSidoCode.substring(0, 5));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			if (paramDemCode != null) {
				String[] paramDemCodes = paramDemCode.split(",");
				if (paramDemCodes.length > 1) {
					paramMap.put("damage_codes", paramDemCodes);
				} else {
					paramMap.put("damage_code", paramDemCode);
				}
			} else {
				paramMap.put("damage_code", paramDemCode);
			}

			rs = commonDataDao.listDmeHisWithGunguByArea(paramMap);
		}
		return rs;
	}

	@Override
	public YearDmeDto totalDamage(String paramSigungu, String paramStDate, String paramEndDate, String paramDemCode) {
		YearDmeDto rs = null;
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("law_code", paramSigungu.substring(0, 5));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		rs = commonDataDao.totalDamage(paramMap);
		return rs;
	}

	@Override
	public String thissenLawAreaYn(String paramGungu) {
		HashMap<String, Object> paramMap = new HashMap<>();

		String paramReGungu = (paramGungu.substring(0, 2).equals(ViewerSysKeyword.RAIN_JEJU_SIDO_AREA))
				? ViewerSysKeyword.RAIN_JEJU_GUNGU_AREA : paramGungu;

		paramMap.put("law_code", paramReGungu);
		return commonDataDao.lookingLawArea(paramMap);
	}

	@Override
	public YearDmeDto maxDamagePerson(String paramSigungu, String paramStDate, String paramEndDate,
			String paramDemCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSigungu);
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		return commonDataDao.maxDamagePersonByYear(paramMap);
	}

	@Override
	public YearDmeDto maxDamageMoney(String paramSigungu, String paramStDate, String paramEndDate,
			String paramDemCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSigungu);
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		return commonDataDao.maxDamageMoneyByYear(paramMap);
	}

	@Override
	public List<YearDmeDto> listDamagePersonTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}

		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}

		return commonDataDao.listDamagePersonTop10(paramMap);
	}

	@Override
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		return commonDataDao.listDamageMoneyTop10(paramMap);
	}

	@Override
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue, String paramDamgeName) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		paramMap.put("damage_info", paramDamgeName);
		paramMap.put("stRainValue", paramStRainValue);
		paramMap.put("endRainValue", paramEndRainValue);
		return commonDataDao.listDamageMoneyTop10(paramMap);
	}

	@Override
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		paramMap.put("stRainValue", paramStRainValue);
		paramMap.put("endRainValue", paramEndRainValue);
		return commonDataDao.listDamageMoneyTop10(paramMap);
	}

	@Override
	public List<YearDmeDto> listGunguDamageCause(String paramSido, String paramStDate, String paramEndDate) {

		SimpleDateFormat prev_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_prev_month = Calendar.getInstance();
		try {
			cal_prev_month.setTime(prev_df.parse(paramStDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_prev_month.add(Calendar.DATE, -1);

		String paramNewStDate = prev_df.format(cal_prev_month.getTime());

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);

		paramMap.put("beg_date", paramNewStDate);
		paramMap.put("end_date", paramEndDate);

		return commonDataDao.listGunguDmeCause(paramMap);
	}

	@Override
	public List<YearDmeDto> listGunguDamageCauseResultSum(String paramSido, String paramStDate, String paramEndDate) {

		SimpleDateFormat prev_df = new SimpleDateFormat("yyyyMMdd");
		Calendar cal_prev_month = Calendar.getInstance();
		try {
			cal_prev_month.setTime(prev_df.parse(paramStDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal_prev_month.add(Calendar.DATE, -1);

		String paramNewStDate = prev_df.format(cal_prev_month.getTime());

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		paramMap.put("beg_date", paramNewStDate);
		paramMap.put("end_date", paramEndDate);

		return commonDataDao.listGunguDmeCauseResultSum(paramMap);
	}

	@Override
	public YearDmeDto detailSummary(String paramStDate, String paramEndDate, String paramDmeCode) {
		HashMap<String, Object> paramMap = new HashMap<>();

		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}

		String[] paramDemCodes = paramDmeCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDmeCode);
		}

		return commonDataDao.detailSummary(paramMap);
	}

	@Override
	public List<YearDmeDto> listDamagePersonTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}

		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		paramMap.put("damage_info", paramDamageName);
		return commonDataDao.listDamagePersonTop10(paramMap);

	}

	@Override
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido == null || paramSido.isEmpty() ? null : paramSido.substring(0, 2));
		if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
			paramMap.put("beg_date", paramStDate);
			paramMap.put("end_date", paramEndDate);
		} else {
			paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
			paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
		}
		String[] paramDemCodes = paramDemCode.split(",");
		if (paramDemCodes.length > 1) {
			paramMap.put("damage_codes", paramDemCodes);
		} else {
			paramMap.put("damage_code", paramDemCode);
		}
		paramMap.put("damage_info", paramDamageName);
		return commonDataDao.listDamageMoneyTop10(paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("damage_info", paramDamageName);
			rs = commonDataDao.listDmeWithGunguByArea(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue, String paramDamageName) {
		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("damage_info", paramDamageName);
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.listDmeWithGunguByArea(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramSidoCode != null && paramSidoCode.size() > 0) {

			for (String sido : paramSidoCode) {
				int _countThissen = countThissen(sido);
				String lawAreaYn = thissenLawAreaYn(sido);

				if (paramDemCode.equals(ViewerSysKeyword.DME_CODE_RAIN)) {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
									ViewerSysKeyword.DME_CODE_RAIN, paramDamageName));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, ViewerSysKeyword.DME_CODE_RAIN,
									paramDamageName));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
								ViewerSysKeyword.DME_CODE_RAIN, paramDamageName));
					}
				} else {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode,
									paramDamageName));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, paramDemCode, paramDamageName));
						}
					} else {
						rs.addAll(
								listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode, paramDamageName));
					}
				}

			}

		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue, String paramDamageName) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramSidoCode != null && paramSidoCode.size() > 0) {

			for (String sido : paramSidoCode) {
				int _countThissen = countThissen(sido);
				String lawAreaYn = thissenLawAreaYn(sido);

				if (paramDemCode.equals(ViewerSysKeyword.DME_CODE_RAIN)) {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
									ViewerSysKeyword.DME_CODE_RAIN, paramDamageName));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, ViewerSysKeyword.DME_CODE_RAIN,
									paramDamageName));
						}
					} else {
						rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate,
								ViewerSysKeyword.DME_CODE_RAIN, paramDamageName));
					}
				} else {
					if (_countThissen > 1) {
						if (lawAreaYn == null || lawAreaYn.equals("Y")) {
							rs.addAll(listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode,
									paramDamageName));
						} else {
							rs.addAll(listDmeWithGungu(sido, paramStDate, paramEndDate, paramDemCode, paramDamageName));
						}
					} else {
						rs.addAll(
								listDmeWithGunguByArea(sido, paramStDate, paramEndDate, paramDemCode, paramDamageName));
					}
				}

			}

		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {

		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			paramMap.put("damage_code", paramDemCode);

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("damage_info", paramDamageName);
			rs = commonDataDao.listDmeWithGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue, String paramDamageName) {

		List<YearDmeDto> rs = null;
		if (paramSidoCode != null && paramStDate != null && paramEndDate != null && paramSidoCode.length() >= 2
				&& paramStDate.length() >= 4 && paramEndDate.length() >= 4) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("law_code", paramSidoCode.substring(0, 2));
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}
			paramMap.put("damage_code", paramDemCode);

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("damage_info", paramDamageName);
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.listDmeWithGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramDamageName) {
		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramGunguCode != null && paramGunguCode.size() > 0) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("gunguCodes", paramGunguCode);
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("damage_info", paramDamageName);
			rs = commonDataDao.sumDamageByGungu(paramMap);
		}
		return rs;
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode, String paramStDate, String paramEndDate,
			String paramDemCode, String paramStRainValue, String paramEndRainValue, String paramDamageName) {

		List<YearDmeDto> rs = new ArrayList<YearDmeDto>();
		if (paramGunguCode != null && paramGunguCode.size() > 0) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			paramMap.put("gunguCodes", paramGunguCode);
			if (paramStDate.length() == 8 && paramEndDate.length() == 8) {
				paramMap.put("beg_date", paramStDate);
				paramMap.put("end_date", paramEndDate);
			} else {
				paramMap.put("beg_date", paramStDate.substring(0, 4) + "0101");
				paramMap.put("end_date", paramEndDate.substring(0, 4) + "1231");
			}

			String[] paramDemCodes = paramDemCode.split(",");
			if (paramDemCodes.length > 1) {
				paramMap.put("damage_codes", paramDemCodes);
			} else {
				paramMap.put("damage_code", paramDemCode);
			}
			paramMap.put("stRainValue", paramStRainValue);
			paramMap.put("endRainValue", paramEndRainValue);
			rs = commonDataDao.sumDamageByGungu(paramMap);
		}
		return rs;
	}

}
