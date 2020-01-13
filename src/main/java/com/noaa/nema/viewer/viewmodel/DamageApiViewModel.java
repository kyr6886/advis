package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.area.dao.SectorDamageModel;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.year.dme.dao.AreaBean;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDateDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;


public class DamageApiViewModel {

	/*조건 parameter*/
	private String paramStDate;
	private String paramEndDate;
	private String paramSido;
	private String paramGungu;
	private String paramStRainValue;
	private String paramEndRainValue;
	private String paramDmeCode;
	
	private List<String> listParamSido;
	private List<String> listParamGungu;
	private KmaInformDto detailKmaInform;
	/*list parameter*/
	private List<YearAreaCodeDto> listDmeSidoSum;
	private List<YearAreaCodeDto> listDmeGunguSum;
	
	private YearDmeDto detail10Years;
	private YearDmeDto detailMaxPersonYear;
	private YearDmeDto detailMaxMoneyYear;
	private List<YearDmeDto> listDamage;
	private List<YearDmeDto> listDamagePerson;
	private YearDmeDto damageItemSum;
	private List<SectorDamageModel> listSectors;
	
	private List<TyphoonCastInfoDto> typhoonNameList;
	
	public String getParamDmeCode() {
		return paramDmeCode;
	}
	public void setParamDmeCode(String paramDmeCode) {
		this.paramDmeCode = paramDmeCode;
	}
	public String getParamStRainValue() {
		return paramStRainValue;
	}
	public void setParamStRainValue(String paramStRainValue) {
		this.paramStRainValue = paramStRainValue;
	}
	public String getParamEndRainValue() {
		return paramEndRainValue;
	}
	public void setParamEndRainValue(String paramEndRainValue) {
		this.paramEndRainValue = paramEndRainValue;
	}
	public List<TyphoonCastInfoDto> getTyphoonNameList() {
		return typhoonNameList;
	}
	public void setTyphoonNameList(List<TyphoonCastInfoDto> typhoonNameList) {
		this.typhoonNameList = typhoonNameList;
	}
	public List<YearDmeDto> getListDamagePerson() {
		return listDamagePerson;
	}
	public void setListDamagePerson(List<YearDmeDto> listDamagePerson) {
		this.listDamagePerson = listDamagePerson;
	}
	public List<String> getListParamSido() {
		return listParamSido;
	}
	public void setListParamSido(List<String> listParamSido) {
		this.listParamSido = listParamSido;
	}
	public List<String> getListParamGungu() {
		return listParamGungu;
	}
	public void setListParamGungu(List<String> listParamGungu) {
		this.listParamGungu = listParamGungu;
	}
	public KmaInformDto getDetailKmaInform() {
		return detailKmaInform;
	}
	public void setDetailKmaInform(KmaInformDto detailKmaInform) {
		this.detailKmaInform = detailKmaInform;
	}
	public YearDmeDto getDamageItemSum() {
		return damageItemSum;
	}
	public void setDamageItemSum(YearDmeDto damageItemSum) {
		this.damageItemSum = damageItemSum;
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
	public List<YearAreaCodeDto> getListDmeSidoSum() {
		return listDmeSidoSum;
	}
	public void setListDmeSidoSum(List<YearAreaCodeDto> listDmeSidoSum) {
		this.listDmeSidoSum = listDmeSidoSum;
	}
	public List<YearAreaCodeDto> getListDmeGunguSum() {
		return listDmeGunguSum;
	}
	public void setListDmeGunguSum(List<YearAreaCodeDto> listDmeGunguSum) {
		this.listDmeGunguSum = listDmeGunguSum;
	}
	public YearDmeDto getDetail10Years() {
		return detail10Years;
	}
	public void setDetail10Years(YearDmeDto detail10Years) {
		this.detail10Years = detail10Years;
	}
	public YearDmeDto getDetailMaxPersonYear() {
		return detailMaxPersonYear;
	}
	public void setDetailMaxPersonYear(YearDmeDto detailMaxPersonYear) {
		this.detailMaxPersonYear = detailMaxPersonYear;
	}
	public YearDmeDto getDetailMaxMoneyYear() {
		return detailMaxMoneyYear;
	}
	public void setDetailMaxMoneyYear(YearDmeDto detailMaxMoneyYear) {
		this.detailMaxMoneyYear = detailMaxMoneyYear;
	}
	public List<YearDmeDto> getListDamage() {
		return listDamage;
	}
	public void setListDamage(List<YearDmeDto> listDamage) {
		this.listDamage = listDamage;
	}
	public List<SectorDamageModel> getListSectors() {
		return listSectors;
	}
	public void setListSectors(List<SectorDamageModel> listSectors) {
		this.listSectors = listSectors;
	}
	
	
	


}
