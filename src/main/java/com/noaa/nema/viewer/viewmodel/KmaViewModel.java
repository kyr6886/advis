package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.kmaInform.dao.KmaAwsDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaWaterDto;

public class KmaViewModel extends BaseViewModel{
	/* 특보 */
	private KmaInformDto detailKmaInform;
	private String paramTyphoonYn;
	/* aws */
	private List<KmaAwsDto> kmaAwsBrnchList;
	private List<KmaAwsDto> kmaAwsGunguList;

	/*수위*/
	private List<KmaWaterDto> kmaWaterObsList;
	private List<KmaWaterDto> kmaWaterObservationList;
	private List<KmaWaterDto> kmaWaterDamList;
	private String paramDamCode;
	private String paramSido;
	private String paramMaxYmdh;
	


	private KmaAwsDto kmaAwsThrList;
	private KmaAwsDto kmaAwsDayList;


	private String paramEndDate;

	public String getParamTyphoonYn() {
		return paramTyphoonYn;
	}

	public void setParamTyphoonYn(String paramTyphoonYn) {
		this.paramTyphoonYn = paramTyphoonYn;
	}

	public KmaInformDto getDetailKmaInform() {
		return detailKmaInform;
	}

	public void setDetailKmaInform(KmaInformDto detailKmaInform) {
		this.detailKmaInform = detailKmaInform;
	}
	
	public List<KmaAwsDto> getKmaAwsBrnchList() {
		return kmaAwsBrnchList;
	}

	public void setKmaAwsBrnchList(List<KmaAwsDto> kmaAwsBrnchList) {
		this.kmaAwsBrnchList = kmaAwsBrnchList;
	}
	public String getParamSido() {
		return paramSido;
	}

	public void setParamSido(String paramSido) {
		this.paramSido = paramSido;
	}
	public List<KmaAwsDto> getKmaAwsGunguList() {
		return kmaAwsGunguList;
	}

	public void setKmaAwsGunguList(List<KmaAwsDto> kmaAwsGunguList) {
		this.kmaAwsGunguList = kmaAwsGunguList;
	}
	public String getParamMaxYmdh() {
		return paramMaxYmdh;
	}

	public void setParamMaxYmdh(String paramMaxYmdh) {
		this.paramMaxYmdh = paramMaxYmdh;
	}
	public List<KmaWaterDto> getKmaWaterObsList() {
		return kmaWaterObsList;
	}

	public void setKmaWaterObsList(List<KmaWaterDto> kmaWaterObsList) {
		this.kmaWaterObsList = kmaWaterObsList;
	}


	public String getParamDamCode() {
		return paramDamCode;
	}

	public void setParamDamCode(String paramDamCode) {
		this.paramDamCode = paramDamCode;
	}
	public String getParamEndDate() {
		return paramEndDate;
	}

	public void setParamEndDate(String paramEndDate) {
		this.paramEndDate = paramEndDate;
	}
	public List<KmaWaterDto> getKmaWaterDamList() {
		return kmaWaterDamList;
	}

	public void setKmaWaterDamList(List<KmaWaterDto> kmaWaterDamList) {
		this.kmaWaterDamList = kmaWaterDamList;
	}
	public List<KmaWaterDto> getKmaWaterObservationList() {
		return kmaWaterObservationList;
	}

	public void setKmaWaterObservationList(List<KmaWaterDto> kmaWaterObservationList) {
		this.kmaWaterObservationList = kmaWaterObservationList;
	}
	public KmaAwsDto getKmaAwsThrList() {
		return kmaAwsThrList;
	}

	public void setKmaAwsThrList(KmaAwsDto kmaAwsThrList) {
		this.kmaAwsThrList = kmaAwsThrList;
	}

	public KmaAwsDto getKmaAwsDayList() {
		return kmaAwsDayList;
	}

	public void setKmaAwsDayList(KmaAwsDto kmaAwsDayList) {
		this.kmaAwsDayList = kmaAwsDayList;
	}


}
