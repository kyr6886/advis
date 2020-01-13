package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeCodeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeConditionDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeImagesDto;

import kr.dis.std.event.dao.EventImgDto;

public class YearDmeViewModel {
	private String paramSido;
	private String paramGungu;
	private String paramStDate;
	private String paramEndDate;
	private String paramYear;
	private String paramDmeCode;
	private String paramFileName;
	private String paramCauses;
    private String paramDamageName;
	private List<MultipartFile> uploadFiles;



	private String paramDescription;
	private int paramSeq;
	private int paramTyphoonSeq;
	private String dataTypeCode;
	private boolean isDisasterCode;
	private int paramOffset; 
	private List<YearDmeDto> listDmeSido;
	private List<YearDmeDto> listDmeGungu;


	private List<YearDmeCodeDto> listDmeDateCode;
	

	private List<YearDmeCodeDto> listDmeSidoCode;
	private List<YearDmeCodeDto> listDmeGunguCode;

	private List<YearDmeDto> listDmeAreaChart;

	private List<YearDmeDto> listDamagePerson;
	private List<TyphoonCastInfoDto> typhoonNameList;
	private List<YearAreaCodeDto> listAreaGunguCodes;
	private List<YearDmeImagesDto> listYearDmeImages;
	private List<YearDmeCodeDto> listYearDmeCode;


	private List<YearDmeCodeDto> listCastTyphoonName;
	
	private YearDmeDto damageItemSum;


	private YearDmeConditionDto yearDmeCondition;

	private int updateCondtion;


	private List<EventImgDto> eventImgList;
	
	private List<YearDmeDto> listDamage;
	
	
	

	public List<YearDmeDto> getListDamage() {
		return listDamage;
	}

	public void setListDamage(List<YearDmeDto> listDamage) {
		this.listDamage = listDamage;
	}

	public List<EventImgDto> getEventImgList() {
		return eventImgList;
	}

	public void setEventImgList(List<EventImgDto> eventImgList) {
		this.eventImgList = eventImgList;
	}

	public String getParamDamageName() {
		return paramDamageName;
	}

	public void setParamDamageName(String paramDamageName) {
		this.paramDamageName = paramDamageName;
	}

	public String getParamSido() {
		return paramSido;
	}

	public void setParamSido(String paramSido) {
		this.paramSido = paramSido;
	}

	public String getParamGungu() {
		return paramGungu;
	}

	public void setParamGungu(String paramGungu) {
		this.paramGungu = paramGungu;
	}

	public String getParamStDate() {
		return paramStDate;
	}

	public void setParamStDate(String paramStDate) {
		this.paramStDate = paramStDate;
	}

	public String getParamEndDate() {
		return paramEndDate;
	}

	public void setParamEndDate(String paramEndDate) {
		this.paramEndDate = paramEndDate;
	}

	public String getParamYear() {
		return paramYear;
	}

	public void setParamYear(String paramYear) {
		this.paramYear = paramYear;
	}

	public String getParamDmeCode() {
		return paramDmeCode;
	}

	public void setParamDmeCode(String paramDmeCode) {
		this.paramDmeCode = paramDmeCode;
	}

	public int getParamTyphoonSeq() {
		return paramTyphoonSeq;
	}

	public void setParamTyphoonSeq(int paramTyphoonSeq) {
		this.paramTyphoonSeq = paramTyphoonSeq;
	}

	public String getDataTypeCode() {
		return dataTypeCode;
	}

	public void setDataTypeCode(String dataTypeCode) {
		this.dataTypeCode = dataTypeCode;
	}

	public boolean isDisasterCode() {
		return isDisasterCode;
	}

	public void setDisasterCode(boolean isDisasterCode) {
		this.isDisasterCode = isDisasterCode;
	}

	public int getParamOffset() {
		return paramOffset;
	}

	public void setParamOffset(int paramOffset) {
		this.paramOffset = paramOffset;
	}

	public List<YearDmeDto> getListDmeSido() {
		return listDmeSido;
	}

	public void setListDmeSido(List<YearDmeDto> listDmeSido) {
		this.listDmeSido = listDmeSido;
	}

	public List<YearDmeDto> getListDmeGungu() {
		return listDmeGungu;
	}

	public void setListDmeGungu(List<YearDmeDto> listDmeGungu) {
		this.listDmeGungu = listDmeGungu;
	}

	public List<YearDmeCodeDto> getListDmeDateCode() {
		return listDmeDateCode;
	}

	public void setListDmeDateCode(List<YearDmeCodeDto> listDmeDateCode) {
		this.listDmeDateCode = listDmeDateCode;
	}

	public List<YearDmeCodeDto> getListDmeSidoCode() {
		return listDmeSidoCode;
	}

	public void setListDmeSidoCode(List<YearDmeCodeDto> listDmeSidoCode) {
		this.listDmeSidoCode = listDmeSidoCode;
	}

	public List<YearDmeCodeDto> getListDmeGunguCode() {
		return listDmeGunguCode;
	}

	public void setListDmeGunguCode(List<YearDmeCodeDto> listDmeGunguCode) {
		this.listDmeGunguCode = listDmeGunguCode;
	}

	public List<YearDmeDto> getListDmeAreaChart() {
		return listDmeAreaChart;
	}

	public void setListDmeAreaChart(List<YearDmeDto> listDmeAreaChart) {
		this.listDmeAreaChart = listDmeAreaChart;
	}

	public List<YearDmeDto> getListDamagePerson() {
		return listDamagePerson;
	}

	public void setListDamagePerson(List<YearDmeDto> listDamagePerson) {
		this.listDamagePerson = listDamagePerson;
	}

	public List<TyphoonCastInfoDto> getTyphoonNameList() {
		return typhoonNameList;
	}

	public void setTyphoonNameList(List<TyphoonCastInfoDto> typhoonNameList) {
		this.typhoonNameList = typhoonNameList;
	}

	public List<YearAreaCodeDto> getListAreaGunguCodes() {
		return listAreaGunguCodes;
	}

	public void setListAreaGunguCodes(List<YearAreaCodeDto> listAreaGunguCodes) {
		this.listAreaGunguCodes = listAreaGunguCodes;
	}

	public List<YearDmeImagesDto> getListYearDmeImages() {
		return listYearDmeImages;
	}

	public void setListYearDmeImages(List<YearDmeImagesDto> listYearDmeImages) {
		this.listYearDmeImages = listYearDmeImages;
	}

	public List<YearDmeCodeDto> getListYearDmeCode() {
		return listYearDmeCode;
	}

	public void setListYearDmeCode(List<YearDmeCodeDto> listYearDmeCode) {
		this.listYearDmeCode = listYearDmeCode;
	}

	public List<YearDmeCodeDto> getListCastTyphoonName() {
		return listCastTyphoonName;
	}

	public void setListCastTyphoonName(List<YearDmeCodeDto> listCastTyphoonName) {
		this.listCastTyphoonName = listCastTyphoonName;
	}

	public YearDmeDto getDamageItemSum() {
		return damageItemSum;
	}

	public void setDamageItemSum(YearDmeDto damageItemSum) {
		this.damageItemSum = damageItemSum;
	}

	public YearDmeConditionDto getYearDmeCondition() {
		return yearDmeCondition;
	}

	public void setYearDmeCondition(YearDmeConditionDto yearDmeCondition) {
		this.yearDmeCondition = yearDmeCondition;
	}
	public int getUpdateCondtion() {
		return updateCondtion;
	}

	public void setUpdateCondtion(int updateCondtion) {
		this.updateCondtion = updateCondtion;
	}
	public int getParamSeq() {
		return paramSeq;
	}

	public void setParamSeq(int paramSeq) {
		this.paramSeq = paramSeq;
	}
	public String getParamDescription() {
		return paramDescription;
	}

	public void setParamDescription(String paramDescription) {
		this.paramDescription = paramDescription;
	}
	public String getParamFileName() {
		return paramFileName;
	}

	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
	}
	public List<MultipartFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<MultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	public String getParamCauses() {
		return paramCauses;
	}

	public void setParamCauses(String paramCauses) {
		this.paramCauses = paramCauses;
	}

}
