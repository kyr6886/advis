package kr.dis.std.web.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;

import kr.dis.std.advis.dao.YearDmeStaticsDto;
import kr.dis.std.advis.resultmodel.StaticsMonthlyModel;
import kr.dis.std.advis.resultmodel.StatisticsModel;
import kr.dis.std.history.dao.AccidentSocDto;
import kr.dis.std.log.dao.LogDisSearchDto;

public class StatisticsViewModel extends BaseViewModel {
	private List<StatisticsModel> listStatDisasterMonthly;
	private List<StatisticsModel> listBuildDamages;
	private List<StatisticsModel> listStatSido;
	private List<StatisticsModel> listYearDamageCount;
	private List<LogDisSearchDto> listLog;
	private StatisticsModel maxDisasterInfo;
	private List<String> listParamMonths;
	private String paramMonth;
	private String paramDamageCode;
	private String paramSidoCode;
	private String paramDamageName;
	private String paramSocDisYn;//사회재난  Y N
	private List<StaticsMonthlyModel> listMontlyRate;
	private List<StaticsMonthlyModel> lisMontlySidoDamage;
	private List<AccidentSocDto> listSocSidoDamage;;
	private List<YearDmeStaticsDto> listDamages;
	private List<YearDmeDto> listYearDme;
	private List<KmaInformDto> listKmaInform;
	private List<KmaInformDto> listKmaInformAlert;
	private List<KmaInformDto> listKmaInformWarning;
	
	private String paramStComDmeCount;
	private String paramStDamageMoney;
	private String paramStRain;
	private String paramEndComDmeCount;
	private String paramEndDamageMoney;
	private String paramEndRain;
	private String paramBegDate;
	private String paramEndDate;
	
	private List<String> paramListSelectedSidoCodes;
	
	private ArrayList<String> paramListObsId;
	private List<YearDmeStaticsDto> listSidoDmeRain;
	private String paramRnDay;
	
	
	
	public String getParamRnDay() {
		return paramRnDay;
	}
	public void setParamRnDay(String paramRnDay) {
		this.paramRnDay = paramRnDay;
	}
	public List<YearDmeStaticsDto> getListSidoDmeRain() {
		return listSidoDmeRain;
	}
	public void setListSidoDmeRain(List<YearDmeStaticsDto> listSidoDmeRain) {
		this.listSidoDmeRain = listSidoDmeRain;
	}
	public ArrayList<String> getParamListObsId() {
		return paramListObsId;
	}
	public void setParamListObsId(ArrayList<String> paramListObsId) {
		this.paramListObsId = paramListObsId;
	}
	public List<String> getParamListSelectedSidoCodes() {
		return paramListSelectedSidoCodes;
	}
	public void setParamListSelectedSidoCodes(List<String> paramListSelectedSidoCodes) {
		this.paramListSelectedSidoCodes = paramListSelectedSidoCodes;
	}
	public List<StatisticsModel> getListStatDisasterMonthly() {
		return listStatDisasterMonthly;
	}
	public void setListStatDisasterMonthly(List<StatisticsModel> listStatDisasterMonthly) {
		this.listStatDisasterMonthly = listStatDisasterMonthly;
	}
	public List<StatisticsModel> getListBuildDamages() {
		return listBuildDamages;
	}
	public void setListBuildDamages(List<StatisticsModel> listBuildDamages) {
		this.listBuildDamages = listBuildDamages;
	}
	public List<StatisticsModel> getListStatSido() {
		return listStatSido;
	}
	public void setListStatSido(List<StatisticsModel> listStatSido) {
		this.listStatSido = listStatSido;
	}
	public List<StatisticsModel> getListYearDamageCount() {
		return listYearDamageCount;
	}
	public void setListYearDamageCount(List<StatisticsModel> listYearDamageCount) {
		this.listYearDamageCount = listYearDamageCount;
	}
	public List<LogDisSearchDto> getListLog() {
		return listLog;
	}
	public void setListLog(List<LogDisSearchDto> listLog) {
		this.listLog = listLog;
	}
	public StatisticsModel getMaxDisasterInfo() {
		return maxDisasterInfo;
	}
	public void setMaxDisasterInfo(StatisticsModel maxDisasterInfo) {
		this.maxDisasterInfo = maxDisasterInfo;
	}
	public List<String> getListParamMonths() {
		return listParamMonths;
	}
	public void setListParamMonths(List<String> listParamMonths) {
		this.listParamMonths = listParamMonths;
	}
	public String getParamMonth() {
		return paramMonth;
	}
	public void setParamMonth(String paramMonth) {
		this.paramMonth = paramMonth;
	}
	public String getParamDamageCode() {
		return paramDamageCode;
	}
	public void setParamDamageCode(String paramDamageCode) {
		this.paramDamageCode = paramDamageCode;
	}
	public String getParamSidoCode() {
		return paramSidoCode;
	}
	public void setParamSidoCode(String paramSidoCode) {
		this.paramSidoCode = paramSidoCode;
	}
	public String getParamDamageName() {
		return paramDamageName;
	}
	public void setParamDamageName(String paramDamageName) {
		this.paramDamageName = paramDamageName;
	}
	public String getParamSocDisYn() {
		return paramSocDisYn;
	}
	public void setParamSocDisYn(String paramSocDisYn) {
		this.paramSocDisYn = paramSocDisYn;
	}
	public List<StaticsMonthlyModel> getListMontlyRate() {
		return listMontlyRate;
	}
	public void setListMontlyRate(List<StaticsMonthlyModel> listMontlyRate) {
		this.listMontlyRate = listMontlyRate;
	}
	public List<StaticsMonthlyModel> getLisMontlySidoDamage() {
		return lisMontlySidoDamage;
	}
	public void setLisMontlySidoDamage(List<StaticsMonthlyModel> lisMontlySidoDamage) {
		this.lisMontlySidoDamage = lisMontlySidoDamage;
	}
	public List<AccidentSocDto> getListSocSidoDamage() {
		return listSocSidoDamage;
	}
	public void setListSocSidoDamage(List<AccidentSocDto> listSocSidoDamage) {
		this.listSocSidoDamage = listSocSidoDamage;
	}
	public List<YearDmeStaticsDto> getListDamages() {
		return listDamages;
	}
	public void setListDamages(List<YearDmeStaticsDto> listDamages) {
		this.listDamages = listDamages;
	}
	public List<YearDmeDto> getListYearDme() {
		return listYearDme;
	}
	public void setListYearDme(List<YearDmeDto> listYearDme) {
		this.listYearDme = listYearDme;
	}
	public List<KmaInformDto> getListKmaInform() {
		return listKmaInform;
	}
	public void setListKmaInform(List<KmaInformDto> listKmaInform) {
		this.listKmaInform = listKmaInform;
	}
	public List<KmaInformDto> getListKmaInformAlert() {
		return listKmaInformAlert;
	}
	public void setListKmaInformAlert(List<KmaInformDto> listKmaInformAlert) {
		this.listKmaInformAlert = listKmaInformAlert;
	}
	public List<KmaInformDto> getListKmaInformWarning() {
		return listKmaInformWarning;
	}
	public void setListKmaInformWarning(List<KmaInformDto> listKmaInformWarning) {
		this.listKmaInformWarning = listKmaInformWarning;
	}
	public String getParamStComDmeCount() {
		return paramStComDmeCount;
	}
	public void setParamStComDmeCount(String paramStComDmeCount) {
		this.paramStComDmeCount = paramStComDmeCount;
	}
	public String getParamStDamageMoney() {
		return paramStDamageMoney;
	}
	public void setParamStDamageMoney(String paramStDamageMoney) {
		this.paramStDamageMoney = paramStDamageMoney;
	}
	public String getParamStRain() {
		return paramStRain;
	}
	public void setParamStRain(String paramStRain) {
		this.paramStRain = paramStRain;
	}
	public String getParamEndComDmeCount() {
		return paramEndComDmeCount;
	}
	public void setParamEndComDmeCount(String paramEndComDmeCount) {
		this.paramEndComDmeCount = paramEndComDmeCount;
	}
	public String getParamEndDamageMoney() {
		return paramEndDamageMoney;
	}
	public void setParamEndDamageMoney(String paramEndDamageMoney) {
		this.paramEndDamageMoney = paramEndDamageMoney;
	}
	public String getParamEndRain() {
		return paramEndRain;
	}
	public void setParamEndRain(String paramEndRain) {
		this.paramEndRain = paramEndRain;
	}
	public String getParamBegDate() {
		return paramBegDate;
	}
	public void setParamBegDate(String paramBegDate) {
		this.paramBegDate = paramBegDate;
	}
	public String getParamEndDate() {
		return paramEndDate;
	}
	public void setParamEndDate(String paramEndDate) {
		this.paramEndDate = paramEndDate;
	}
	
	
	
	
}
