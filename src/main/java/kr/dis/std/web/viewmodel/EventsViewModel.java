package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventsDto;

public class EventsViewModel extends BaseViewModel {
	private String disType ;
	private String disName ;
	private String disDate ;

	
	List<EventDto> listEventDTO;
		
	public List<EventDto> getListEventDTO() {
		return listEventDTO;
	}
	public void setListEventDTO(List<EventDto> listEventDTO) {
		this.listEventDTO = listEventDTO;
	}
	public String getDisType() {
		return disType;
	}
	public void setDisType(String disType) {
		this.disType = disType;
	}
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public String getDisDate() {
		return disDate;
	}
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	
	
}
