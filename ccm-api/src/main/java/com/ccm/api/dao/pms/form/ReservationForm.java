package com.ccm.api.dao.pms.form;

public class ReservationForm {
	private String isNew;
	private String accnt;
	private String roomNo; // 房间号
	private double roomPrice; // 房间价格
	private String sta; // 预定状态
	private String osta = ""; // 原来是旧的预定状态,现在是taobao的订单状态
	private boolean isSecret = false; // 是否私密房
	private String name; // 姓名
	private String phone = ""; // 电话号码
	private int pepSum = 1; // 入住人数
	private String arrDate; // 入住日期
	private String depDate; // 离店日期
	private String checkModel; // 入住模式
	private String cardType = "R"; // 证件类型
	private String cardNum = ""; // 证件号码
	private boolean isAddBed = false;// 是否加床
	private String remark = ""; // 备注
	private String rateList; // 每日房费的详细列表
	private double deposit = 0.00d; // 押金
	private double roomRate = 0.00d; // 房费应收
	private double charge = 0.00d; // 总共应收
	private double already = 0.00d; // 已经向客户收取
	private double credit = 0.00d;
	private String payType = "CA"; // 支付类型 CA现金、CR刷卡、AR合作伙伴收款、CL挂账收款。
	private String channel;
	private String sex = "1";
	private String type;
	private String tbAccnt = "";
	private String pcrec = "";
	private String name2 = "";// 记录 淘宝 旺旺号
	private String ressta = "0"; // 记录是否是新订单，1 为新订单，0为旧订单
	private boolean isPayMent = false;
	/** 一下是取消预订用的 **/
	private String cancelReason;
	private String cancelRef = "";
	private String returnPirce = "";
	/** 下面这个参数是给房态中心做判断用的 **/
	private String isFromMain = "false";
	private String hotelid = "";
	/*** 公共参数 ***/
	private String number = "0";
	private String orderName = "";

	private String haccnt;// profileId

	public boolean isSecret() {
		return isSecret;
	}

	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPepSum() {
		return pepSum;
	}

	public void setPepSum(int pepSum) {
		this.pepSum = pepSum;
	}

	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(String checkModel) {
		this.checkModel = checkModel;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public boolean isAddBed() {
		return isAddBed;
	}

	public void setAddBed(boolean isAddBed) {
		this.isAddBed = isAddBed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRateList() {
		return rateList;
	}

	public void setRateList(String rateList) {
		this.rateList = rateList;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(double roomRate) {
		this.roomRate = roomRate;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getAlready() {
		return already;
	}

	public void setAlready(double already) {
		this.already = already;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getAccnt() {
		return accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelRef() {
		return cancelRef;
	}

	public void setCancelRef(String cancelRef) {
		this.cancelRef = cancelRef;
	}

	public String getOsta() {
		return osta;
	}

	public void setOsta(String osta) {
		this.osta = osta;
	}

	public String getTbAccnt() {
		return tbAccnt;
	}

	public void setTbAccnt(String tbAccnt) {
		this.tbAccnt = tbAccnt;
	}

	public String getIsFromMain() {
		return isFromMain;
	}

	public void setIsFromMain(String isFromMain) {
		this.isFromMain = isFromMain;
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

	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getRessta() {
		return ressta;
	}

	public void setRessta(String ressta) {
		this.ressta = ressta;
	}

	public String getReturnPirce() {
		return returnPirce;
	}

	public void setReturnPirce(String returnPirce) {
		this.returnPirce = returnPirce;
	}

	public boolean isPayMent() {
		return isPayMent;
	}

	public void setPayMent(boolean isPayMent) {
		this.isPayMent = isPayMent;
	}

	public String getHaccnt() {
		return haccnt;
	}

	public void setHaccnt(String haccnt) {
		this.haccnt = haccnt;
	}

}