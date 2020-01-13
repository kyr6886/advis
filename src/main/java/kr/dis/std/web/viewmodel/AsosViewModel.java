package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.typhoon.dao.TyphoonDamageDto;

import kr.dis.std.advis.dao.DamageAnlRainDto;

public class AsosViewModel {


	private List<DamageAnlRainDto> listAsos;
	
	public List<DamageAnlRainDto> getListAsos() {
		return listAsos;
	}
	public void setListAsos(List<DamageAnlRainDto> listAsos) {
		this.listAsos = listAsos;
	}
}
