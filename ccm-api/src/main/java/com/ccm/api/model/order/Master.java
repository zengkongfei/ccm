/**
 * 
 */
package com.ccm.api.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
public class Master extends MasterOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6004426576784132517L;

	private Boolean isMC=false;//是否为CCM-MC操作
	
	private String sta; // 预定状态，OrderStatus
	private String osta = ""; // 新订单，修改订单，取消订单标志，
	private String downPmsMsgStatus;// 下传到PMS的消息状态,主要用于查询订单报表使用

	private String name; // firstName,名
	private String name2 = "";// lastName,OXI中nameSur,姓
	private String name4;// 中文名

	private String customName;// custom表中name字段 OXI中的nameSuc和genericName
	private String invoiceTitle;// 发票抬头

	private String sex = "0";// 0－先生，1－女士,2－未知,gender

	private String mobile = ""; // 手机号码
	private Date arr; // 入住日期
	private Date ocheckinDate; // 原入住日期
	private Date dep; // 离店日期
	private Date ocheckoutDate; // 原离店日期
	private Integer gstno; // 入住总人数，包括小孩
	private Integer ogstno;// 原总人数
	private Integer ochild;// 原小孩数
	private Integer onumberOfRooms; // 原房间数量
	private String roomno = ""; // 房间号
	private String type;// 房型代码,roomTypeCode,roomInventoryCode
	private String otype;// 原房型代码,roomTypeCode,roomInventoryCode

	private BigDecimal charge; // 总共应收,订单总金额
	private BigDecimal rmrate;// 包价总和
	private BigDecimal setrate;// 房价总和
	private BigDecimal staticPackage;// 静态包价总和
	private BigDecimal deduct = BigDecimal.ZERO;// 取消时扣款金额

	private String payment;// 支付类型(付款方式),担保类型guaranteeType
	private String channel;// 渠道代码
	private String ochannel;// 原渠道代码
	private String ref;// 备注

	private String cby;// 创建者
	private Date changed;// 创建时间

	private String haccnt;// profileId,档案号
	private String membershipNumber;// 会员卡号
	private String restype;// reservationStatusType

	private String ochainCode;// 原集团代码
	private String hotelId;
	private String ohotelCode;// 原酒店代码
	private String crsno;// 渠道订单号

	private String market;// 市场代码
	private String source;// 来源代码
	private String currencyCode;// 币种代码
	private String lang;// 语言代码

	private String channelRateId;// 渠道房价档案号
	private Date channelRateStart;// 渠道房价生效时间
	private Date channelRateEnd;// 渠道房价失效时间
	private String mfNameCode;// custom中corp/IATANo
	private String dateFormat;// 酒店日期格式

	private String ratePlanId;
	private String oratePlanCode;// 原房价代码

	// WBE新增字段
	private String ratePlanDesc;// 如果无名称,则取描述
	private String roomTypeName;
	private String transportID;// 航班号/列车号
	private Date transportTime;// 到达时间
	private String guaranteeDesc;
	private Boolean hotelConfirm = false;// 是否需要酒店确认
	private String channelId;
	private Integer confirmMethod;// 确认方式
	private String srqs;// 特殊需求

	// TB使用字段新增
	private String rpid;// TB价格计划id
	private String rid;// TB房型id
	private String gid;// TB酒店商品ID
	private String roomTypeOuterId;// 对应rid的房型ID
	private String roomTypeId;
	private String outerHotelId;// 对应hid的酒店ID
	private String hid;// TB酒店ID
	private String createToken;// 单次请求tokenTB
	private String username;// 用户名TB
	private String password;// 密码TB
	private String contactor;// 联系人姓名
	private String pcrec = "";// 父订单,为0时表示主订单信息
	private String resno;// 预订号，用来自增订单号

	// 支付流程水,支付金额,渠道代码
	private String paymentTransaction;
	private Float prepaidAmount;
	private String bookingSource;

	private String idcls;// 证件类型
	private String ident;// 证件号码

	private String createBY;// CCM WEB UI订单中的创建着
	private String accountMsgId;// 入帐消息号
	private Boolean autoDeposit;// custom表中自动入帐押金
	private String transactionCode;// custom表中入帐代码

	private String cancelPmsId;// 取消订单,取消时，给默认值为RemindStatus.order_cancel，pms 上传后更新为pms上传的pmsid

	private String traceDept;// custom表中的TRACE DEPT
	private String paymentRemark;// rate_plan表中的payment
	private Boolean isDiscount;//是否优惠

	// 房价，包价，取消规则明细默认是删除原来的再添加，若不更新需手动设置为NULL
	private List<MasterRate> mrList = new ArrayList<MasterRate>();// 房价明细
	private List<MasterPackage> mpList = new ArrayList<MasterPackage>();// 包价明细
	private List<MasterCancel> mcList = new ArrayList<MasterCancel>();// 取消规则明细
	private List<MasterProfile> mprofile = new ArrayList<MasterProfile>();

	/*
	 * 以下用于查询或传值使用，非数据库字段
	 */
	private String action;// 消息类型
	private BigDecimal chargeAfterTax;// 税后订单总金额（不存）
	private BigDecimal setrateAfterTax;// 税后房价总和（不存）
	private BigDecimal firstDateRate; // 首日价

	// stayHistory使用
	private String checkInDate;
	private String checkOutDate;
	private String createdDate;
	// 订单下传到PMS的消息状态,非数据库字段
	private String msgStatus2Pms;
	// mc 订单新增使用
	private String reservedRoomId;// 保留房id
	private String promotionCode;// 公司/促销代码
	private String accessCode;
	private Boolean isHardCancelOfHotel = false;
	private Boolean isHardCancelOfMaster = false;
	private Boolean isSupperRateCode = false;// 超级价格
	private String alerts;

	private String traces;

	private Boolean sendOccupyFreeSellAvai=false;//有保留房，占用酒店自由房量
	
	public Boolean getSendOccupyFreeSellAvai() {
		return sendOccupyFreeSellAvai;
	}

	public void setSendOccupyFreeSellAvai(Boolean sendOccupyFreeSellAvai) {
		this.sendOccupyFreeSellAvai = sendOccupyFreeSellAvai;
	}

	public BigDecimal getChargeAfterTax() {
		return chargeAfterTax;
	}

	public void setChargeAfterTax(BigDecimal chargeAfterTax) {
		this.chargeAfterTax = chargeAfterTax;
	}

	public BigDecimal getSetrateAfterTax() {
		return setrateAfterTax;
	}

	public void setSetrateAfterTax(BigDecimal setrateAfterTax) {
		this.setrateAfterTax = setrateAfterTax;
	}

	public Boolean getIsSupperRateCode() {
		return isSupperRateCode;
	}

	public void setIsSupperRateCode(Boolean isSupperRateCode) {
		this.isSupperRateCode = isSupperRateCode;
	}

	public Boolean getIsHardCancelOfHotel() {
		return isHardCancelOfHotel;
	}

	public void setIsHardCancelOfHotel(Boolean isHardCancelOfHotel) {
		this.isHardCancelOfHotel = isHardCancelOfHotel;
	}

	public Boolean getIsHardCancelOfMaster() {
		return isHardCancelOfMaster;
	}

	public void setIsHardCancelOfMaster(Boolean isHardCancelOfMaster) {
		this.isHardCancelOfMaster = isHardCancelOfMaster;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getDays() {
		return DateUtil.dateDiff(arr, dep);
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getOsta() {
		return osta;
	}

	public void setOsta(String osta) {
		this.osta = osta;
	}

	public String getDownPmsMsgStatus() {
		return downPmsMsgStatus;
	}

	public void setDownPmsMsgStatus(String downPmsMsgStatus) {
		this.downPmsMsgStatus = downPmsMsgStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcls() {
		return idcls;
	}

	public void setIdcls(String idcls) {
		this.idcls = idcls;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Date getOcheckinDate() {
		return ocheckinDate;
	}

	public void setOcheckinDate(Date ocheckinDate) {
		this.ocheckinDate = ocheckinDate;
	}

	public Date getOcheckoutDate() {
		return ocheckoutDate;
	}

	public void setOcheckoutDate(Date ocheckoutDate) {
		this.ocheckoutDate = ocheckoutDate;
	}

	public Integer getOnumberOfRooms() {
		return onumberOfRooms;
	}

	public void setOnumberOfRooms(Integer onumberOfRooms) {
		this.onumberOfRooms = onumberOfRooms;
	}

	public String getOtype() {
		return otype;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getRmrate() {
		return rmrate;
	}

	public void setRmrate(BigDecimal rmrate) {
		this.rmrate = rmrate;
	}

	public BigDecimal getSetrate() {
		return setrate;
	}

	public void setSetrate(BigDecimal setrate) {
		this.setrate = setrate;
	}

	public Integer getGstno() {
		return gstno;
	}

	public void setGstno(Integer gstno) {
		this.gstno = gstno;
	}

	public BigDecimal getCharge() {
		return charge;
	}

	public BigDecimal getCharge2Scale() {
		if (charge != null) {
			return charge.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			return BigDecimal.ZERO;
		}
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCby() {
		return cby;
	}

	public void setCby(String cby) {
		this.cby = cby;
	}

	public Date getChanged() {
		return changed;
	}

	public void setChanged(Date changed) {
		this.changed = changed;
	}

	public String getCrsno() {
		return crsno;
	}

	public void setCrsno(String crsno) {
		this.crsno = crsno;
	}

	public String getPcrec() {
		return pcrec;
	}

	public void setPcrec(String pcrec) {
		this.pcrec = pcrec;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getHaccnt() {
		return haccnt;
	}

	public void setHaccnt(String haccnt) {
		this.haccnt = haccnt;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<MasterRate> getMrList() {
		return mrList;
	}

	public void setMrList(List<MasterRate> mrList) {
		this.mrList = mrList;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getChannelRateId() {
		return channelRateId;
	}

	public void setChannelRateId(String channelRateId) {
		this.channelRateId = channelRateId;
	}

	public Date getChannelRateStart() {
		return channelRateStart;
	}

	public void setChannelRateStart(Date channelRateStart) {
		this.channelRateStart = channelRateStart;
	}

	public Date getChannelRateEnd() {
		return channelRateEnd;
	}

	public void setChannelRateEnd(Date channelRateEnd) {
		this.channelRateEnd = channelRateEnd;
	}

	public String getRpid() {
		return rpid;
	}

	public void setRpid(String rpid) {
		this.rpid = rpid;
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

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getRoomTypeOuterId() {
		return roomTypeOuterId;
	}

	public void setRoomTypeOuterId(String roomTypeOuterId) {
		this.roomTypeOuterId = roomTypeOuterId;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getOuterHotelId() {
		return outerHotelId;
	}

	public void setOuterHotelId(String outerHotelId) {
		this.outerHotelId = outerHotelId;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getCreateToken() {
		return createToken;
	}

	public void setCreateToken(String createToken) {
		this.createToken = createToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getMfNameCode() {
		return mfNameCode;
	}

	public void setMfNameCode(String mfNameCode) {
		this.mfNameCode = mfNameCode;
	}

	public List<MasterCancel> getMcList() {
		return mcList;
	}

	public void setMcList(List<MasterCancel> mcList) {
		this.mcList = mcList;
	}

	public List<MasterPackage> getMpList() {
		return mpList;
	}

	public void setMpList(List<MasterPackage> mpList) {
		this.mpList = mpList;
	}

	public List<MasterProfile> getMprofile() {
		return mprofile;
	}

	public void setMprofile(List<MasterProfile> mprofile) {
		this.mprofile = mprofile;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getRatePlanDesc() {
		return ratePlanDesc;
	}

	public void setRatePlanDesc(String ratePlanDesc) {
		this.ratePlanDesc = ratePlanDesc;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getTransportID() {
		return transportID;
	}

	public void setTransportID(String transportID) {
		this.transportID = transportID;
	}

	public Date getTransportTime() {
		return transportTime;
	}

	public void setTransportTime(Date transportTime) {
		this.transportTime = transportTime;
	}

	public String getGuaranteeDesc() {
		return guaranteeDesc;
	}

	public void setGuaranteeDesc(String guaranteeDesc) {
		this.guaranteeDesc = guaranteeDesc;
	}

	public Boolean getHotelConfirm() {
		return hotelConfirm;
	}

	public void setHotelConfirm(Boolean hotelConfirm) {
		this.hotelConfirm = hotelConfirm;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public BigDecimal getStaticPackage() {
		return staticPackage;
	}

	public void setStaticPackage(BigDecimal staticPackage) {
		this.staticPackage = staticPackage;
	}

	public BigDecimal getDeduct() {
		return deduct;
	}

	public void setDeduct(BigDecimal deduct) {
		this.deduct = deduct;
	}

	public Integer getConfirmMethod() {
		return confirmMethod;
	}

	public void setConfirmMethod(Integer confirmMethod) {
		this.confirmMethod = confirmMethod;
	}

	public String getSrqs() {
		return srqs;
	}

	public void setSrqs(String srqs) {
		this.srqs = srqs;
	}

	public BigDecimal getFirstDateRate() {
		return firstDateRate;
	}

	public void setFirstDateRate(BigDecimal firstDateRate) {
		this.firstDateRate = firstDateRate;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getMsgStatus2Pms() {
		return msgStatus2Pms;
	}

	public void setMsgStatus2Pms(String msgStatus2Pms) {
		this.msgStatus2Pms = msgStatus2Pms;
	}

	public Integer getOgstno() {
		return ogstno;
	}

	public void setOgstno(Integer ogstno) {
		this.ogstno = ogstno;
	}

	public Integer getOchild() {
		return ochild;
	}

	public void setOchild(Integer ochild) {
		this.ochild = ochild;
	}

	public String getOchannel() {
		return ochannel;
	}

	public void setOchannel(String ochannel) {
		this.ochannel = ochannel;
	}

	public String getOchainCode() {
		return ochainCode;
	}

	public void setOchainCode(String ochainCode) {
		this.ochainCode = ochainCode;
	}

	public String getOhotelCode() {
		return ohotelCode;
	}

	public void setOhotelCode(String ohotelCode) {
		this.ohotelCode = ohotelCode;
	}

	public String getOratePlanCode() {
		return oratePlanCode;
	}

	public void setOratePlanCode(String oratePlanCode) {
		this.oratePlanCode = oratePlanCode;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getReservedRoomId() {
		return reservedRoomId;
	}

	public void setReservedRoomId(String reservedRoomId) {
		this.reservedRoomId = reservedRoomId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(String paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	public Float getPrepaidAmount() {
		return prepaidAmount;
	}

	public void setPrepaidAmount(Float prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}

	public String getBookingSource() {
		return bookingSource;
	}

	public void setBookingSource(String bookingSource) {
		this.bookingSource = bookingSource;
	}

	public String getCreateBY() {
		return createBY;
	}

	public void setCreateBY(String createBY) {
		this.createBY = createBY;
	}

	public String getAccountMsgId() {
		return accountMsgId;
	}

	public void setAccountMsgId(String accountMsgId) {
		this.accountMsgId = accountMsgId;
	}

	public Boolean getAutoDeposit() {
		return autoDeposit;
	}

	public void setAutoDeposit(Boolean autoDeposit) {
		this.autoDeposit = autoDeposit;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	/**
	 * @return the traceDept
	 */
	public String getTraceDept() {
		return traceDept;
	}

	/**
	 * @param traceDept
	 *            the traceDept to set
	 */
	public void setTraceDept(String traceDept) {
		this.traceDept = traceDept;
	}

	/**
	 * @return the paymentRemark
	 */
	public String getPaymentRemark() {
		return paymentRemark;
	}

	/**
	 * @param paymentRemark
	 *            the paymentRemark to set
	 */
	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}

	public String getAlerts() {
		return alerts;
	}

	public void setAlerts(String alerts) {
		this.alerts = alerts;
	}

	public String getCancelPmsId() {
		return cancelPmsId;
	}

	public void setCancelPmsId(String cancelPmsId) {
		this.cancelPmsId = cancelPmsId;
	}

	/**
	 * @return the traces
	 */
	public String getTraces() {
		return traces;
	}

	/**
	 * @param traces
	 *            the traces to set
	 */
	public void setTraces(String traces) {
		this.traces = traces;
	}

	public Boolean getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	public Boolean getIsMC() {
		return isMC;
	}

	public void setIsMC(Boolean isMC) {
		this.isMC = isMC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Master [sta=" + sta + ", osta=" + osta + ", downPmsMsgStatus=" + downPmsMsgStatus + ", name=" + name + ", name2=" + name2 + ", name4=" + name4 + ", customName=" + customName + ", invoiceTitle=" + invoiceTitle + ", sex=" + sex + ", mobile=" + mobile + ", arr=" + arr + ", ocheckinDate=" + ocheckinDate + ", dep=" + dep + ", ocheckoutDate=" + ocheckoutDate + ", gstno=" + gstno + ", ogstno=" + ogstno + ", ochild=" + ochild + ", onumberOfRooms=" + onumberOfRooms + ", roomno=" + roomno + ", type=" + type + ", otype=" + otype + ", charge=" + charge + ", rmrate=" + rmrate + ", setrate=" + setrate + ", staticPackage=" + staticPackage + ", deduct=" + deduct + ", payment=" + payment + ", channel=" + channel + ", ochannel=" + ochannel + ", ref=" + ref + ", cby=" + cby + ", changed="
				+ changed + ", haccnt=" + haccnt + ", membershipNumber=" + membershipNumber + ", restype=" + restype + ", ochainCode=" + ochainCode + ", hotelId=" + hotelId + ", ohotelCode=" + ohotelCode + ", crsno=" + crsno + ", market=" + market + ", source=" + source + ", currencyCode=" + currencyCode + ", lang=" + lang + ", channelRateId=" + channelRateId + ", channelRateStart=" + channelRateStart + ", channelRateEnd=" + channelRateEnd + ", mfNameCode=" + mfNameCode + ", dateFormat=" + dateFormat + ", ratePlanId=" + ratePlanId + ", oratePlanCode=" + oratePlanCode + ", ratePlanDesc=" + ratePlanDesc + ", roomTypeName=" + roomTypeName + ", transportID=" + transportID + ", transportTime=" + transportTime + ", guaranteeDesc=" + guaranteeDesc + ", hotelConfirm=" + hotelConfirm
				+ ", channelId=" + channelId + ", confirmMethod=" + confirmMethod + ", srqs=" + srqs + ", rpid=" + rpid + ", rid=" + rid + ", gid=" + gid + ", roomTypeOuterId=" + roomTypeOuterId + ", roomTypeId=" + roomTypeId + ", outerHotelId=" + outerHotelId + ", hid=" + hid + ", createToken=" + createToken + ", username=" + username + ", password=" + password + ", contactor=" + contactor + ", pcrec=" + pcrec + ", resno=" + resno + ", paymentTransaction=" + paymentTransaction + ", prepaidAmount=" + prepaidAmount + ", bookingSource=" + bookingSource + ", idcls=" + idcls + ", ident=" + ident + ", createBY=" + createBY + ", accountMsgId=" + accountMsgId + ", autoDeposit=" + autoDeposit + ", transactionCode=" + transactionCode + ", cancelPmsId=" + cancelPmsId + ", traceDept=" + traceDept
				+ ", paymentRemark=" + paymentRemark + ", isDiscount=" + isDiscount + ", mrList=" + mrList + ", mpList=" + mpList + ", mcList=" + mcList + ", mprofile=" + mprofile + ", action=" + action + ", chargeAfterTax=" + chargeAfterTax + ", setrateAfterTax=" + setrateAfterTax + ", firstDateRate=" + firstDateRate + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", createdDate=" + createdDate + ", msgStatus2Pms=" + msgStatus2Pms + ", reservedRoomId=" + reservedRoomId + ", promotionCode=" + promotionCode + ", accessCode=" + accessCode + ", isHardCancelOfHotel=" + isHardCancelOfHotel + ", isHardCancelOfMaster=" + isHardCancelOfMaster + ", isSupperRateCode=" + isSupperRateCode + ", alerts=" + alerts + ", traces=" + traces + "]";
	}

}
