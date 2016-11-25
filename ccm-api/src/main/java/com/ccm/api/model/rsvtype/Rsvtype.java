package com.ccm.api.model.rsvtype;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价日历表
 * @author carr
 *
 */
public class Rsvtype extends BaseObject {
	
	private static final long serialVersionUID = -4362818469551741076L;
	
	@Id
	private String rsvtypeId;//主键
	private boolean mcInvoke=true;   //mc 调用
	private boolean isInvSnapInvoke =false;//pms上传,不需要再次查找渠道
	/**酒店ID*/
	private String hotelid;
	private String chainCode;
	/**日期*/
	private Date date;
	/**房型ID*/
	private String type;
	private Integer outoforder;        // pms上传
	private Integer definite;
	/**价格*/
	private Double rate;
	/**渠道*/
	private String channel;
	/**
	 * 发布状态
	 * 0：未发布；1：发布
	 */
	private Integer sta;
	private String rateCode;
	private String status;  //状态:mongo表中的标识
	private Long mongoTime; //添加到mongo库中的时间
	
	private Integer houseOverbook;
	private Boolean generic;
	private Integer physicalRooms;//物理房量                                            pms上传
	private Integer roomTypeOverbook;//超卖总数  ！非超卖值         pms上传
	private Integer adultsInHouse;
	private Integer childrenInHouse;
	private Integer arrivalRooms;
	private Integer departureRooms;
	private Integer blockCount;//团队保留房                                              pms上传
	private Integer resvCount;//已买总房量                                                 pms上传
	private String hotelCode;//酒店代码
	private Integer overbookAmount;//超卖房量
	private boolean isDailyDay=false;  //是否n+1
	private Integer unavailable;//酒店已售房量                                        pms上传
	private Integer available;//酒店物理可用房 physicalRooms+roomTypeOverbook-unavailable-outoforder;
	private Integer overBooking;//总超预定量
	private Integer totalOBSold;//已超卖总房量
	
	private Integer sendAvailable;
	
	private List<RsvtypeChannel> rcList;
	
	public static final String INIT_STATUS = "0";// 为执行
    public static final String SUCCESS_STATUS = "1";// 执行成功
	
    private boolean needSendRtav=false;//房态开关表化
    private boolean forceSend=false;//是否强制推开关
    
	public boolean isForceSend() {
		return forceSend;
	}
	public void setForceSend(boolean forceSend) {
		this.forceSend = forceSend;
	}
	public boolean isNeedSendRtav() {
		return needSendRtav;
	}
	public void setNeedSendRtav(boolean needSendRtav) {
		this.needSendRtav = needSendRtav;
	}
	public List<RsvtypeChannel> getRcList() {
        return rcList;
    }
    public void setRcList(List<RsvtypeChannel> rcList) {
        this.rcList = rcList;
    }
    public String getRsvtypeId() {
		return rsvtypeId;
	}
	public void setRsvtypeId(String rsvtypeId) {
		this.rsvtypeId = rsvtypeId;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOutoforder() {
		return outoforder;
	}
	public void setOutoforder(Integer outoforder) {
		this.outoforder = outoforder;
	}
	public Integer getDefinite() {
		return definite;
	}
	public void setDefinite(Integer definite) {
		this.definite = definite;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getSta() {
		return sta;
	}
	public void setSta(Integer sta) {
		this.sta = sta;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public Integer getHouseOverbook() {
		return houseOverbook;
	}
	public void setHouseOverbook(Integer houseOverbook) {
		this.houseOverbook = houseOverbook;
	}
	public Boolean getGeneric() {
		return generic;
	}
	public void setGeneric(Boolean generic) {
		this.generic = generic;
	}
	public Integer getPhysicalRooms() {
		return physicalRooms;
	}
	public void setPhysicalRooms(Integer physicalRooms) {
		this.physicalRooms = physicalRooms;
	}
	public Integer getRoomTypeOverbook() {
		return roomTypeOverbook;
	}
	public void setRoomTypeOverbook(Integer roomTypeOverbook) {
		this.roomTypeOverbook = roomTypeOverbook;
	}
	public Integer getAdultsInHouse() {
		return adultsInHouse;
	}
	public void setAdultsInHouse(Integer adultsInHouse) {
		this.adultsInHouse = adultsInHouse;
	}
	public Integer getChildrenInHouse() {
		return childrenInHouse;
	}
	public void setChildrenInHouse(Integer childrenInHouse) {
		this.childrenInHouse = childrenInHouse;
	}
	public Integer getArrivalRooms() {
		return arrivalRooms;
	}
	public void setArrivalRooms(Integer arrivalRooms) {
		this.arrivalRooms = arrivalRooms;
	}
	public Integer getDepartureRooms() {
		return departureRooms;
	}
	public void setDepartureRooms(Integer departureRooms) {
		this.departureRooms = departureRooms;
	}
	public Integer getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(Integer blockCount) {
		this.blockCount = blockCount;
	}
	public Integer getResvCount() {
		return resvCount;
	}
	public void setResvCount(Integer resvCount) {
		this.resvCount = resvCount;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Integer getOverbookAmount() {
		return overbookAmount;
	}
	public void setOverbookAmount(Integer overbookAmount) {
		this.overbookAmount = overbookAmount;
	}
	public Integer getUnavailable() {
		return unavailable;
	}
	public void setUnavailable(Integer unavailable) {
		this.unavailable = unavailable;
	}
	
	public boolean isMcInvoke() {
        return mcInvoke;
    }
    public void setMcInvoke(boolean mcInvoke) {
        this.mcInvoke = mcInvoke;
    }
    
    public String getChainCode() {
        return chainCode;
    }
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }
    
    public boolean getIsDailyDay() {
        return isDailyDay;
    }
    public void setIsDailyDay(boolean isDailyDay) {
        this.isDailyDay = isDailyDay;
    }
	public Integer getOverBooking() {
		return overBooking;
	}
	public void setOverBooking(Integer overBooking) {
		this.overBooking = overBooking;
	}
	public Integer getTotalOBSold() {
		return totalOBSold;
	}
	public void setTotalOBSold(Integer totalOBSold) {
		this.totalOBSold = totalOBSold;
	}
	
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	
    public Integer getSendAvailable() {
        return sendAvailable;
    }
    public void setSendAvailable(Integer sendAvailable) {
        this.sendAvailable = sendAvailable;
    }
    public boolean isInvSnapInvoke() {
		return isInvSnapInvoke;
	}
	public void setInvSnapInvoke(boolean isInvSnapInvoke) {
		this.isInvSnapInvoke = isInvSnapInvoke;
	}
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMongoTime() {
		return mongoTime;
	}
	public void setMongoTime(Long mongoTime) {
		this.mongoTime = mongoTime;
	}
	@Override
	public String toString() {
		return "Rsvtype [rsvtypeId=" + rsvtypeId + ", mcInvoke=" + mcInvoke
				+ ", hotelid=" + hotelid + ", chainCode=" + chainCode
				+ ", date=" + date + ", type=" + type + ", outoforder="
				+ outoforder + ", definite=" + definite + ", rate=" + rate
				+ ", channel=" + channel + ", sta=" + sta + ", rateCode="
				+ rateCode + ", houseOverbook=" + houseOverbook + ", generic="
				+ generic + ", physicalRooms=" + physicalRooms
				+ ", roomTypeOverbook=" + roomTypeOverbook + ", adultsInHouse="
				+ adultsInHouse + ", childrenInHouse=" + childrenInHouse
				+ ", arrivalRooms=" + arrivalRooms + ", departureRooms="
				+ departureRooms + ", blockCount=" + blockCount
				+ ", resvCount=" + resvCount + ", hotelCode=" + hotelCode
				+ ", overbookAmount=" + overbookAmount + ", isDailyDay="
				+ isDailyDay + ", unavailable=" + unavailable
				+ ", overBooking=" + overBooking + ", totalOBSold="
				+ totalOBSold + "]";
	}
}
