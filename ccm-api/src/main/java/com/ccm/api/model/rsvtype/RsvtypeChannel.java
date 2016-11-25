package com.ccm.api.model.rsvtype;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;


/**
 * 渠道销售房量
 *
 */
public class RsvtypeChannel extends Rsvtype  implements Cloneable{

	@Override
	public RsvtypeChannel clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (RsvtypeChannel)super.clone();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 881852205974626437L;
	@Id
	private String rsvtypeChannelId;
	private String channelId;
	private Integer allotment; //渠道保留房
	private Integer allotmentSold;//已卖保留房
	private Boolean freeSell;  //可售房开关
	private Integer obSold;    //已售超预定
	private Integer sold;      //已售房量
	private Integer channelAvailable; //可用房量
	
	private Integer allotmentAvailable;//可用保留房
	private Integer obAvailable;   //可用ob房量
	
	private String channelCode;//仅做,mongo,显示用
	private String errMsg;
	private Integer cutOffDays;
	private Date cutOffDate;
	private String ratePlanCodes;
	private Integer hasBlock;
	public List<RsvchanBlock> getRsvchanBlockList() {
		return rsvchanBlockList;
	}
	public void setRsvchanBlockList(List<RsvchanBlock> rsvchanBlockList) {
		this.rsvchanBlockList = rsvchanBlockList;
	}

	private List <RsvchanBlock> rsvchanBlockList;
	
	public Integer getHasBlock() {
		return hasBlock;
	}
	public void setHasBlock(Integer hasBlock) {
		this.hasBlock = hasBlock;
	}
	public String getRatePlanCodes() {
		return ratePlanCodes;
	}
	public void setRatePlanCodes(String ratePlanCodes) {
		this.ratePlanCodes = ratePlanCodes;
	}
	public Date getCutOffDate() {
		return cutOffDate;
	}
	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
	}
	public Integer getCutOffDays() {
		return cutOffDays;
	}
	public void setCutOffDays(Integer cutOffDays) {
		this.cutOffDays = cutOffDays;
	}

	/**发送渠道状态*/
	private String sendStatus;
	public static final String SEND_INIT_STATUS = "0";// 未发送
    public static final String SEND_SUCCESS_STATUS = "1";// 1 发送成功
    public static final String SEND_IGNORE_STATUS = "2";// 2 不予发送，未匹配或对象已关闭
    public static final String SEND_ERROR_STATUS = "9";// 发送失败
    
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getChannelCode() {
        return channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getRsvtypeChannelId() {
		return rsvtypeChannelId;
	}
	public void setRsvtypeChannelId(String rsvtypeChannelId) {
		this.rsvtypeChannelId = rsvtypeChannelId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Integer getAllotment() {
		return allotment;
	}
	public void setAllotment(Integer allotment) {
		this.allotment = allotment;
	}
	public Integer getAllotmentSold() {
		return allotmentSold;
	}
	public void setAllotmentSold(Integer allotmentSold) {
		this.allotmentSold = allotmentSold;
	}
	
	public Boolean getFreeSell() {
        return freeSell;
    }
    public void setFreeSell(Boolean freeSell) {
        this.freeSell = freeSell;
    }
    public Integer getObSold() {
		return obSold;
	}
	public void setObSold(Integer obSold) {
		this.obSold = obSold;
	}
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	
    public Integer getChannelAvailable() {
        return channelAvailable;
    }
    public void setChannelAvailable(Integer channelAvailable) {
        this.channelAvailable = channelAvailable;
    }
    public Integer getAllotmentAvailable() {
        return allotmentAvailable;
    }
    public void setAllotmentAvailable(Integer allotmentAvailable) {
        this.allotmentAvailable = allotmentAvailable;
    }
    public Integer getObAvailable() {
        return obAvailable;
    }
    public void setObAvailable(Integer obAvailable) {
        this.obAvailable = obAvailable;
    }
    
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		return "RsvtypeChannel [rsvtypeChannelId=" + rsvtypeChannelId
				+ ", channelId=" + channelId + ", allotment=" + allotment
				+ ", allotmentSold=" + allotmentSold + ", freeSell=" + freeSell
				+ ", obSold=" + obSold + ", sold=" + sold
				+ ", channelAvailable=" + channelAvailable
				+ ", allotmentAvailable=" + allotmentAvailable
				+ ", obAvailable=" + obAvailable + ", channelCode="
				+ channelCode +", cutOffDate="+cutOffDate+" ,cutOffDays="+cutOffDays+ ", hasBlock="+hasBlock+", errMsg=" + errMsg  
				+ ", sendStatus=" + sendStatus + "]";
	}
}
