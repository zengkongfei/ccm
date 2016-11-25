package com.ccm.api.model.hotel.vo;

import java.util.ArrayList;
import java.util.List;

import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.ChainI18n;

public class ChainVO extends Chain{

	private static final long serialVersionUID = 2205174719321394194L;

	private String chainMId;//集团多语言主键        
	private String language;//语言           
	private String chainName;//集团名称      
	private String description;//集团简介    
	private List<ChainI18n> chainI18nList;
	private List<HotelVO> hotelVos; //集团下的酒店列表
	
	public String getChainMId() {
		return chainMId;
	}
	public void setChainMId(String chainMId) {
		this.chainMId = chainMId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<ChainI18n> getChainI18nList() {
		return chainI18nList;
	}
	public void setChainI18nList(List<ChainI18n> chainI18nList) {
		this.chainI18nList = chainI18nList;
	}
	
	public List<HotelVO> getHotelVos() {
		if(hotelVos == null){
			hotelVos = new ArrayList<HotelVO>();
		}
		return hotelVos;
	}
	public void setHotelVos(List<HotelVO> hotelVos) {
		this.hotelVos = hotelVos;
	}
	
	@Override
	public String toString() {
		return "ChainI18n [chainMId=" + chainMId
				+ ", language=" + language + ", chainName=" + chainName
				+ ", description=" + description + "]";
	}
}
