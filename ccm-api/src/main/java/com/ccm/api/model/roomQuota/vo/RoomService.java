package com.ccm.api.model.roomQuota.vo;

/**
 * 酒店服务设施
 * @author carr
 *
 */
public class RoomService {
	/**吧台*/
	private boolean bar;
	/**有线电视*/
	private boolean catv;
	/**国内长途电话*/
	private boolean ddd;
	/**国际长途电话*/
	private boolean idd;
	/**独立卫生间*/
	private boolean toilet;
	/**公共卫生间*/
	private boolean pubtoliet;
	
	public boolean isBar() {
		return bar;
	}

	public void setBar(boolean bar) {
		this.bar = bar;
	}

	public boolean isCatv() {
		return catv;
	}

	public void setCatv(boolean catv) {
		this.catv = catv;
	}

	public boolean isDdd() {
		return ddd;
	}

	public void setDdd(boolean ddd) {
		this.ddd = ddd;
	}

	public boolean isIdd() {
		return idd;
	}

	public void setIdd(boolean idd) {
		this.idd = idd;
	}

	public boolean isToilet() {
		return toilet;
	}

	public void setToilet(boolean toilet) {
		this.toilet = toilet;
	}

	public boolean isPubtoliet() {
		return pubtoliet;
	}

	public void setPubtoliet(boolean pubtoliet) {
		this.pubtoliet = pubtoliet;
	}

	@Override
	public String toString() {
		return "RoomService [bar=" + bar + ", catv=" + catv + ", ddd=" + ddd
				+ ", idd=" + idd + ", toilet=" + toilet + ", pubtoliet="
				+ pubtoliet + "]";
	}
}
