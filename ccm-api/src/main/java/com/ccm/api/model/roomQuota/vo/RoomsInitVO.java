package com.ccm.api.model.roomQuota.vo;

/**
 * 房态初始化列表展示
 * @author carr
 *
 */
public class RoomsInitVO {
	
	/**
	 * 商品ID
	 * 渠道商品表，渠道商品主键
	 */
	private String roomId;
	/**淘宝酒店ID*/
	private String hid;
	/**淘宝房型ID*/
	private String rid;
	/**淘宝商品ID*/
	private String gid;
	/**淘宝商品item_id*/
	private String iid;
	/**酒店名称*/
	private String hname;
	/**房型名称*/
	private String rname;
	/**商品名称*/
	private String gname;
	/**酒店ID*/
	private String hotelId;
	/**渠道ID*/
	private String channelId;
	/**房型ID*/
	private String guestRoomId;
	/**宝贝显示状态*/
	private String flag;
	/**房价日历表中数据*/
	private int countRsvtype;
	/**
	 * 房型发布状态
	 * 0:未发布;1:上架;2:下架;3:删除
	 */
	private String status;
	/**价格日历中房型代码*/
	private String roomTypeCode;
	/**可用房量*/
	private Integer available;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getGuestRoomId() {
		return guestRoomId;
	}
	public void setGuestRoomId(String guestRoomId) {
		this.guestRoomId = guestRoomId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public int getCountRsvtype() {
		return countRsvtype;
	}
	public void setCountRsvtype(int countRsvtype) {
		this.countRsvtype = countRsvtype;
	}
	@Override
	public String toString() {
		return "RoomsInitVO [roomId=" + roomId + ", hid=" + hid + ", rid="
				+ rid + ", gid=" + gid + ", iid=" + iid + ", hname=" + hname
				+ ", rname=" + rname + ", gname=" + gname + ", hotelId="
				+ hotelId + ", channelId=" + channelId + ", guestRoomId="
				+ guestRoomId + ", flag=" + flag + ", countRsvtype="
				+ countRsvtype + ", status=" + status + ", roomTypeCode="
				+ roomTypeCode + ", available=" + available + "]";
	}
}
