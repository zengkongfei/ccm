package com.ccm.api.model.wbe;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.ows.vo.OrderDailyRateVO;
import com.ccm.api.util.DateUtil;

/**
 * 
 * @author chay.huang
 *
 */
public class WbeOrderVO extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8985891822982885194L;
	private String sta; // 预定状态
	private String restype; 
	private String staDesp; // 预定状态描述   如：创建成功，取消成功
	private String masterId;// 订单号
	private String hotelId;// 酒店id
	private String chainCode;// 酒店集团code
	private String channelCode;// 渠道代码
	private String channelId;// 渠道id
	private String hotelCode;// 酒店编码
	private String hotelName;// 酒店名称
	private Integer numberOfUnits;// 几人价
	private Date arr; // 入住日期
	private Date dep; // 离店日期
	private int number; // 几间房
	private String roomTypeCode;// 房型代码
	private String roomTypeName;// 房型代码
	private String ratePlanCode;// 房价编码
	private String ratePlanId;// 房价id
	private String accessCodeType;// 订单来源类型,目前为旅行社或公司  type
	private String accessCode;// 旅行社或公司值   accesscode
	private String inventType;// 房量类型：ALLOT&FREESELL
	private String payment;// 支付类型(付款方式),担保类型guaranteeType
	private String cardCode;// 信用卡类型
	private String cardHolderName;// 持卡人姓名
	private String cardNumber;// 信用卡号
	private String expirationDate;// 有效期
	private String currencyCode;//币种
	
	private BigDecimal charge; // 总共应收,订单总金额
	private BigDecimal oneRoomCharge; // 每间总价
	private String cancelReason;//取消原因
	
	private String createUDFBy;//创建人，登入系统的用户名
	
	private List<WbeGuestVO> wbeGuestVoList;
	private List<String> dateList;//日期行程，日住日期到离店日期之间的所有日期，不包含离店日期
	private List<OrderDailyRateVO> dailRateList;//每日价格
	private WbeGuestVO wbeGuestVO;
	
	private String crsno;//渠道订单号
	
	public String getCrsno() {
		return crsno;
	}
	public void setCrsno(String crsno) {
		this.crsno = crsno;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public Date getArr() {
		return arr;
	}
	public void setArr(Date arr) {
		this.arr = arr;
	}
	public Date getDep() {
		return dep;
	}
	public void setDep(Date dep) {
		this.dep = dep;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getAccessCodeType() {
		return accessCodeType;
	}
	public void setAccessCodeType(String accessCodeType) {
		this.accessCodeType = accessCodeType;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getInventType() {
		return inventType;
	}
	public void setInventType(String inventType) {
		this.inventType = inventType;
	}
	public BigDecimal getCharge() {
		return charge;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<WbeGuestVO> getWbeGuestVoList() {
		return wbeGuestVoList;
	}
	public void setWbeGuestVoList(List<WbeGuestVO> wbeGuestVoList) {
		this.wbeGuestVoList = wbeGuestVoList;
	}
	
	public List<String> getDateList() {
		if(dateList==null){
			if(arr!=null&&dep!=null){
				dateList = DateUtil.getDays(arr, dep);
				dateList.remove(dateList.size()-1);
			}
		}
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public List<OrderDailyRateVO> getDailRateList() {
		return dailRateList;
	}
	public void setDailRateList(List<OrderDailyRateVO> dailRateList) {
		this.dailRateList = dailRateList;
	}
	public WbeGuestVO getWbeGuestVO() {
		return wbeGuestVO;
	}
	public void setWbeGuestVO(WbeGuestVO wbeGuestVO) {
		this.wbeGuestVO = wbeGuestVO;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public BigDecimal getOneRoomCharge() {
		return oneRoomCharge;
	}
	public void setOneRoomCharge(BigDecimal oneRoomCharge) {
		this.oneRoomCharge = oneRoomCharge;
	}
	public String getStaDesp() {
		return staDesp;
	}
	public void setStaDesp(String staDesp) {
		this.staDesp = staDesp;
	}
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getCreateUDFBy() {
		return createUDFBy;
	}
	public void setCreateUDFBy(String createUDFBy) {
		this.createUDFBy = createUDFBy;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Override
	public String toString() {
		return "WbeOrderVO [sta=" + sta + ", restype=" + restype + ", staDesp="
				+ staDesp + ", masterId=" + masterId + ", hotelId=" + hotelId
				+ ", chainCode=" + chainCode + ", channelCode=" + channelCode
				+ ", channelId=" + channelId + ", hotelCode=" + hotelCode
				+ ", hotelName=" + hotelName + ", numberOfUnits="
				+ numberOfUnits + ", arr=" + arr + ", dep=" + dep + ", number="
				+ number + ", roomTypeCode=" + roomTypeCode + ", roomTypeName="
				+ roomTypeName + ", ratePlanCode=" + ratePlanCode
				+ ", ratePlanId=" + ratePlanId + ", accessCodeType="
				+ accessCodeType + ", accessCode=" + accessCode
				+ ", inventType=" + inventType + ", payment=" + payment
				+ ", cardCode=" + cardCode + ", cardHolderName="
				+ cardHolderName + ", cardNumber=" + cardNumber
				+ ", expirationDate=" + expirationDate + ", currencyCode="
				+ currencyCode + ", charge=" + charge + ", oneRoomCharge="
				+ oneRoomCharge + ", cancelReason=" + cancelReason
				+ ", createUDFBy=" + createUDFBy + ", wbeGuestVoList="
				+ wbeGuestVoList + ", dateList=" + dateList + ", dailRateList="
				+ dailRateList + ", wbeGuestVO=" + wbeGuestVO + "]";
	}
	
	
	
}
