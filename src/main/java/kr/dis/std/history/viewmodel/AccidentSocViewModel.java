package kr.dis.std.history.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.meta.dao.MetaLawDTO;

import kr.dis.std.history.dao.AccidentSocDto;

public class AccidentSocViewModel extends BaseViewModel{
	private String paramMonth;
	
	private List<String> paramList;
	
	private List<AccidentSocDto> listAccidentSoc;
	private List<AccidentSocDto> listAccidentGroupCount;
	private List<AccidentSocDto> listSelectedSidoCodes;
	
	private List<MetaLawDTO> listAllSidoCodes;
	
	private int countAccidentSocPaging;
	
	public String getParamMonth() {
		return paramMonth;
	}
	public void setParamMonth(String paramMonth) {
		this.paramMonth = paramMonth;
	}
	public List<String> getParamList() {
		return paramList;
	}
	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}
	public List<AccidentSocDto> getListAccidentSoc() {
		return listAccidentSoc;
	}
	public void setListAccidentSoc(List<AccidentSocDto> listAccidentSoc) {
		this.listAccidentSoc = listAccidentSoc;
	}
	public List<AccidentSocDto> getListAccidentGroupCount() {
		return listAccidentGroupCount;
	}
	public void setListAccidentGroupCount(List<AccidentSocDto> listAccidentGroupCount) {
		this.listAccidentGroupCount = listAccidentGroupCount;
	}
	public int getCountAccidentSocPaging() {
		return countAccidentSocPaging;
	}
	public void setCountAccidentSocPaging(int countAccidentSocPaging) {
		this.countAccidentSocPaging = countAccidentSocPaging;
	}
	public List<AccidentSocDto> getListSelectedSidoCodes() {
		return listSelectedSidoCodes;
	}
	public void setListSelectedSidoCodes(List<AccidentSocDto> listSelectedSidoCodes) {
		this.listSelectedSidoCodes = listSelectedSidoCodes;
	}
	public List<MetaLawDTO> getListAllSidoCodes() {
		return listAllSidoCodes;
	}
	public void setListAllSidoCodes(List<MetaLawDTO> listAllSidoCodes) {
		this.listAllSidoCodes = listAllSidoCodes;
	}
}