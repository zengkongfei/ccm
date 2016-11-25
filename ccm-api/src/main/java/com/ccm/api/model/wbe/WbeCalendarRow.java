package com.ccm.api.model.wbe;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.channel.Rateplan;

public class WbeCalendarRow extends BaseObject {

	/**
	 * 
	 */
	public final static String INVENT_ALLOT = "ALLOT";
	public final static String INVENT_FREESELL = "FREESELL";

	private static final long serialVersionUID = 2284867495670846103L;

	private String inventType;// 房量类型：ALLOT&FREESELL

	private String roomTypeCode;// 房型code
	private String roomTypeName;// 房型name

	private Integer rowNumberOfUnits;// 几人价

	private String ratePlanCode;
	private String ratePlanId;

	private Rateplan rateplan;

	private Date arrDate;// 入住时间

	private Date depDate;// 离店时间

	private Map<Date, WbeCalendarCell> CellMap = new HashMap<Date, WbeCalendarCell>();// key:日期
																						// value:单元格对象(WbeCalendarCell)
	
	private List<EffectiveGuarrntee> guarrnteeList;//担保

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public String getInventType() {
		return inventType;
	}

	public void setInventType(String inventType) {
		this.inventType = inventType;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public Integer getRowNumberOfUnits() {
		return rowNumberOfUnits;
	}

	public void setRowNumberOfUnits(Integer rowNumberOfUnits) {
		this.rowNumberOfUnits = rowNumberOfUnits;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public Rateplan getRateplan() {
		return rateplan;
	}

	public void setRateplan(Rateplan rateplan) {
		this.rateplan = rateplan;
	}

	public Map<Date, WbeCalendarCell> getCellMap() {
		return CellMap;
	}

	public void setCellMap(Map<Date, WbeCalendarCell> cellMap) {
		CellMap = cellMap;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public List<EffectiveGuarrntee> getGuarrnteeList() {
		return guarrnteeList;
	}

	public void setGuarrnteeList(List<EffectiveGuarrntee> guarrnteeList) {
		this.guarrnteeList = guarrnteeList;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	@Override
	public String toString() {
		return "WbeCalendarRow [inventType=" + inventType + ", roomTypeCode="
				+ roomTypeCode + ", roomTypeName=" + roomTypeName
				+ ", rowNumberOfUnits=" + rowNumberOfUnits + ", ratePlanCode="
				+ ratePlanCode + ", ratePlanId=" + ratePlanId + ", rateplan="
				+ rateplan + ", arrDate=" + arrDate + ", depDate=" + depDate
				+ ", CellMap=" + CellMap + ", guarrnteeList=" + guarrnteeList
				+ "]";
	}



}
