package kr.dis.std.category.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.advis.dao.SbjRptOcrDto;
import kr.dis.std.category.dao.CategoryDTO;
import kr.dis.std.category.dao.TbOcrFileDTO;

public class CategoryViewModel extends BaseViewModel {
	private List<CategoryDTO> list;
	private List<TbOcrFileDTO> ocrList;
	private List<SbjRptOcrDto> listSbjRptOct;
	private int ResultCount;
	private String paramCategorys;
	private String paramCtgId;
	private String paramTitle;
	private int paramDepth;
	private String paramPrintId;
	private CategoryDTO param;
	private String paramEvtId;
	
	
	public List<SbjRptOcrDto> getListSbjRptOct() {
		return listSbjRptOct;
	}
	public void setListSbjRptOct(List<SbjRptOcrDto> listSbjRptOct) {
		this.listSbjRptOct = listSbjRptOct;
	}
	public String getParamEvtId() {
		return paramEvtId;
	}
	public void setParamEvtId(String paramEvtId) {
		this.paramEvtId = paramEvtId;
	}
	public String getParamPrintId() {
		return paramPrintId;
	}
	public void setParamPrintId(String paramPrintId) {
		this.paramPrintId = paramPrintId;
	}
	public CategoryDTO getParam() {
		return param;
	}
	public void setParam(CategoryDTO param) {
		this.param = param;
	}
	public int getParamDepth() {
		return paramDepth;
	}
	public void setParamDepth(int paramDepth) {
		this.paramDepth = paramDepth;
	}
	public String getParamCtgId() {
		return paramCtgId;
	}
	public void setParamCtgId(String paramCtgId) {
		this.paramCtgId = paramCtgId;
	}
	public String getParamTitle() {
		return paramTitle;
	}
	public void setParamTitle(String paramTitle) {
		this.paramTitle = paramTitle;
	}
	public List<CategoryDTO> getList() {
		return list;
	}
	public void setList(List<CategoryDTO> list) {
		this.list = list;
	}
	public String getParamCategorys() {
		return paramCategorys;
	}
	public void setParamCategorys(String paramCategorys) {
		this.paramCategorys = paramCategorys;
	}
	public int getResultCount() {
		return ResultCount;
	}
	public void setResultCount(int resultCount) {
		ResultCount = resultCount;
	}
	public List<TbOcrFileDTO> getOcrList() {
		return ocrList;
	}
	public void setOcrList(List<TbOcrFileDTO> ocrList) {
		this.ocrList = ocrList;
	}
}
