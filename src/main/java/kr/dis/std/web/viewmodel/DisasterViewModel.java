package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;

import kr.dis.std.advis.dao.TbDisManualDto;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.history.dao.AccidentSocDto;

public class DisasterViewModel extends BaseViewModel {
	
	private List<String> paramEvents;
	private String paramTitle;
	private String paramDisType;
	private String paramDisDateStr;
	private String paramFileId;
	private String paramYear;
	private String paramMonth;
	private String paramEvtId;
	private String paramCtgId;
	private int paramDepth;
	private String paramStDate;
	private String paramEndDate;
	
	private String paramSidoCode;
	private String paramSigunguCode;
	private int paramSeq;
	private String paramGrpCode;
	private String paramSysCode;
	private String paramContents;
	
	private List<CategoryDTO> ResultList;
	private List<YearAreaCodeDto> ResultSidoList;
	private List<YearAreaCodeDto> ResultSigunguList;
	private List<AccidentSocDto> ResultAccidentSocList;
	
	private List<TbDisManualDto> ResultManualList;
	private EventDto paramEvent;
	
	
	
	public EventDto getParamEvent() {
		return paramEvent;
	}
	public void setParamEvent(EventDto paramEvent) {
		this.paramEvent = paramEvent;
	}
	public String getParamContents() {
		return paramContents;
	}
	public void setParamContents(String paramContents) {
		this.paramContents = paramContents;
	}
	public String getParamGrpCode() {
		return paramGrpCode;
	}
	public void setParamGrpCode(String paramGrpCode) {
		this.paramGrpCode = paramGrpCode;
	}
	public String getParamSysCode() {
		return paramSysCode;
	}
	public void setParamSysCode(String paramSysCode) {
		this.paramSysCode = paramSysCode;
	}
	public int getParamSeq() {
		return paramSeq;
	}
	public void setParamSeq(int paramSeq) {
		this.paramSeq = paramSeq;
	}
	public List<TbDisManualDto> getResultManualList() {
		return ResultManualList;
	}
	public void setResultManualList(List<TbDisManualDto> resultManualList) {
		ResultManualList = resultManualList;
	}
	public List<AccidentSocDto> getResultAccidentSocList() {
		return ResultAccidentSocList;
	}
	public void setResultAccidentSocList(List<AccidentSocDto> resultAccidentSocList) {
		ResultAccidentSocList = resultAccidentSocList;
	}
	public String getParamSigunguCode() {
		return paramSigunguCode;
	}
	public void setParamSigunguCode(String paramSigunguCode) {
		this.paramSigunguCode = paramSigunguCode;
	}
	public String getParamSidoCode() {
		return paramSidoCode;
	}
	public void setParamSidoCode(String paramSidoCode) {
		this.paramSidoCode = paramSidoCode;
	}
	public List<YearAreaCodeDto> getResultSidoList() {
		return ResultSidoList;
	}
	public void setResultSidoList(List<YearAreaCodeDto> resultSidoList) {
		ResultSidoList = resultSidoList;
	}
	public List<YearAreaCodeDto> getResultSigunguList() {
		return ResultSigunguList;
	}
	public void setResultSigunguList(List<YearAreaCodeDto> resultSigunguList) {
		ResultSigunguList = resultSigunguList;
	}
	public List<CategoryDTO> getResultList() {
		return ResultList;
	}
	public void setResultList(List<CategoryDTO> resultList) {
		ResultList = resultList;
	}
	public List<String> getParamEvents() {
		return paramEvents;
	}
	public void setParamEvents(List<String> paramEvents) {
		this.paramEvents = paramEvents;
	}
	public String getParamTitle() {
		return paramTitle;
	}
	public void setParamTitle(String paramTitle) {
		this.paramTitle = paramTitle;
	}
	public String getParamDisType() {
		return paramDisType;
	}
	public void setParamDisType(String paramDisType) {
		this.paramDisType = paramDisType;
	}
	public String getParamDisDateStr() {
		return paramDisDateStr;
	}
	public void setParamDisDateStr(String paramDisDateStr) {
		this.paramDisDateStr = paramDisDateStr;
	}
	public String getParamFileId() {
		return paramFileId;
	}
	public void setParamFileId(String paramFileId) {
		this.paramFileId = paramFileId;
	}
	public String getParamYear() {
		return paramYear;
	}
	public void setParamYear(String paramYear) {
		this.paramYear = paramYear;
	}
	public String getParamMonth() {
		return paramMonth;
	}
	public void setParamMonth(String paramMonth) {
		this.paramMonth = paramMonth;
	}
	public String getParamEvtId() {
		return paramEvtId;
	}
	public void setParamEvtId(String paramEvtId) {
		this.paramEvtId = paramEvtId;
	}
	public String getParamCtgId() {
		return paramCtgId;
	}
	public void setParamCtgId(String paramCtgId) {
		this.paramCtgId = paramCtgId;
	}
	public int getParamDepth() {
		return paramDepth;
	}
	public void setParamDepth(int paramDepth) {
		this.paramDepth = paramDepth;
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
	
	

}
