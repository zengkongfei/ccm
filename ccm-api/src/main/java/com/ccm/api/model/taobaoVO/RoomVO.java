package com.ccm.api.model.taobaoVO;

public class RoomVO {
	/**
	 * 酒店id
	 * 如：12345 。必须为数字。
	 */
	private Long hid;   
	/**
	 * 房型id
	 * 如：123456  。必须为数字。
	 */
	private Long rid; 
	/**
	 * 酒店房间商品gid
	 * 如：123456  。必须为数字。
	 */
	private Long gid;
	/**
	 * 酒店商品名称
	 * 如：北京饭店标准间  。不能超过60字节
	 */
	private String title; 
	/**
	 * 床宽
	 * 如：A  。可选值：A,B,C,D,E,F,G,H。
	 * 分别代表：A：1米及以下，B：1.1米，C：1.2米，D：1.35米，E：1.5米，F：1.8米，G：2米，H：2.2米及以上  
	 */
	private String size;  
	/**
	 * 面积
	 * 如：A  。可选值：A,B,C,D。
	 * 分别代表： A：15平米以下，B：16－30平米，C：31－50平米，D：50平米以上
	 */
	private String area; 
	/**
	 * 床型
	 * 如：A  。可选值：A,B,C,D,E,F,G,H,I。
	 * 分别代表：A：单人床，B：大床，C：双床，D：双床/大床，E：子母床，F：上下床，G：圆形床，H：多床，I：其他床型  
	 */
	private String bedType; 
	/**
	 * 楼层
	 * 如：2 。长度不超过8
	 */
	private String storey;  
	/**
	 * 早餐
	 * 如：A  。A,B,C,D,E。
	 * 分别代表： A：无早，B：单早，C：双早，D：三早，E：多早  
	 */
	private String breakfast; 
	/**
	 * 宽带服务
	 * 如：A  。A,B,C,D。
	 * 分别代表： A：无宽带，B：免费宽带，C：收费宽带，D：部分收费宽带  
	 */
	private String bbn;  
	/**
	 * 设施服务
	 * 如：{"bar":false,"catv":false,"ddd":false,"idd":false,"pubtoilet":false,"toilet":false}。
	 * JSON格式。 value值true有此服务，false没有。 bar：吧台，catv：有线电视，ddd：国内长途电话，idd：国际长途电话，toilet：独立卫生间，pubtoliet：公共卫生间。
	 */
	private String service;  
	/**
	 *  酒店商品图片路径
	 * 如：我是被上传的文件内容 。
	 * 类型:JPG,GIF;最大长度:500K。
	 * 支持的文件类型：gif,jpg,jpeg,png。发布的时候只能发布一张图片。如需再发图片，需要调用商品图片上传接口，1个商品最多5张图片。 
	 * 支持的文件类型：jpg,png,gif
	 */
	private String pic;  
	/**
	 * 支付类型
	 * 如：A  。可选值：A,B,C,D,E。
	 * 分别代表： A：全额支付，B：手续费，C：订金，D：手续费/间夜，E：前台面付
	 */
	private String paymentType;   
	/**
	 * 订金
	 * 如：10000  。0～99999900的正整数。在payment_type为订金时必须输入，存储的单位是分。不能带角分。
	 */
	private Long deposit;   
	/**
	 * 手续费
	 * 如：10000  。0～99999900的正整数。在payment_type为手续费或手续费/间夜时必须输入，存储的单位是分。不能带角分。
	 */
	private Long fee;   
	/**
	 * 购买须知
	 * 如：购买请注意  。不能超过4000个汉字（8000个字符）。  
	 */
	private String guide; 
	/**
	 * 商品描述
	 * 如：这是描述  。不能超过25000个汉字（50000个字符）。  
	 */
	private String desc;  
	/**
	 * 房态信息
	 * 如：[{"date":2011-01-28,"price":10000, "num":10},{"date":2011-01-29,"price":12000,"num":10}]  
	 * 可以传今天开始90天内的房态信息。
	 * 日期必须连续。
	 * JSON格式传递。 
	 * date：代表房态日期，格式为YYYY-MM-DD， price：代表当天房价，0～99999999，存储的单位是分， num：代表当天可售间数，0～999。
	 */
	private String roomQuotas;   
	/**
	 * 商品主图需要关联的图片空间的相对url
	 * 如：i7/T1rfxpXcVhXXXH9QcZ_033150.jpg。
	 * 这个url所对应的图片必须要属于当前用户。pic_path和image只需要传入一个,如果两个都传，默认选择pic_path
	 */
    private String picPath;    
    /**
     * 接入卖家数据主键
     * 如：123456    
     */
    private String siteParam;  
    /**
     * 价格类型
     * 如：A。可选值：A,B。分别代表：A：参考预订价，B实时预订价 。未选该参数默认为参考预订价。
     * 选择实时预订价的情况下，支付类型必须选择为A(全额支付)  
     */
    private String priceType;  
    /**
     * 如：{"A":[{"date":2010-01-28,"num":10,"price":10000},{"date":2010-01-29,"num":20,"price":12000}],"E":[{"date":2010-01-28,"num":10,"price":10000},{"date":2010-01-29,"num":20,"price":12000}]}  
     * 为到店支付卖家特殊使用，代表多种支付类型的房态。room_quotas可选，如果有值，也会处理。
     */
	private String multiRoomQuotas;  
	/**
	 * 发票类型
	 * 如：A  。A,B。分别代表： A:酒店住宿发票,B:其他  
	 */
	private String receiptType;  
	/**
	 * 旅行社发票
	 * 如：  发票类型为其他时的发票描述,不能超过30个汉字，60个字符。  
	 */
	private String receiptOtherTypeDesc;  
	/**
	 * 发票说明
	 * 如：此发票客户离店时由酒店前台开具 ，不能超过100个汉字,200个字符。  
	 */
	private String receiptInfo;  
	/**
	 * 酒店商品是否提供发票
	 * 如：true    
	 */
	private Boolean hasReceipt;
	/**
	 * 状态。
	 * 可选值1，2，3。1：上架。2：下架。3：删除。传入相应状态代表去执行相应的操作。
	 */
	private Long status;
	
	/**
	 * 退款规则
	 * {"t":3,"p":[{"d":3,"r":10},{"d":2,"r":20},{"d":1,"r":30}]}
	 */
	private String refundPolicyInfo;
	
	/**酒店名称*/
	private String hname;
	
	/**房型名称*/
	private String rname;
	
	/**商品名称*/
	private String gname;
	
	/**房态ID*/
	private String roomQuotaId;
	
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Long getHid() {
		return hid;
	}
	public void setHid(Long hid) {
		this.hid = hid;
	}
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getStorey() {
		return storey;
	}
	public void setStorey(String storey) {
		this.storey = storey;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getBbn() {
		return bbn;
	}
	public void setBbn(String bbn) {
		this.bbn = bbn;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Long getDeposit() {
		return deposit;
	}
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRoomQuotas() {
		return roomQuotas;
	}
	public void setRoomQuotas(String roomQuotas) {
		this.roomQuotas = roomQuotas;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getSiteParam() {
		return siteParam;
	}
	public void setSiteParam(String siteParam) {
		this.siteParam = siteParam;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getMultiRoomQuotas() {
		return multiRoomQuotas;
	}
	public void setMultiRoomQuotas(String multiRoomQuotas) {
		this.multiRoomQuotas = multiRoomQuotas;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getReceiptOtherTypeDesc() {
		return receiptOtherTypeDesc;
	}
	public void setReceiptOtherTypeDesc(String receiptOtherTypeDesc) {
		this.receiptOtherTypeDesc = receiptOtherTypeDesc;
	}
	public String getReceiptInfo() {
		return receiptInfo;
	}
	public void setReceiptInfo(String receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	public Boolean getHasReceipt() {
		return hasReceipt;
	}
	public void setHasReceipt(Boolean hasReceipt) {
		this.hasReceipt = hasReceipt;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getRefundPolicyInfo() {
		return refundPolicyInfo;
	}
	public void setRefundPolicyInfo(String refundPolicyInfo) {
		this.refundPolicyInfo = refundPolicyInfo;
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
	public String getRoomQuotaId() {
		return roomQuotaId;
	}
	public void setRoomQuotaId(String roomQuotaId) {
		this.roomQuotaId = roomQuotaId;
	}
	@Override
	public String toString() {
		return "RoomVO [hid=" + hid + ", rid=" + rid + ", gid=" + gid
				+ ", title=" + title + ", size=" + size + ", area=" + area
				+ ", bedType=" + bedType + ", storey=" + storey
				+ ", breakfast=" + breakfast + ", bbn=" + bbn + ", service="
				+ service + ", pic=" + pic + ", paymentType=" + paymentType
				+ ", deposit=" + deposit + ", fee=" + fee + ", guide=" + guide
				+ ", desc=" + desc + ", roomQuotas=" + roomQuotas
				+ ", picPath=" + picPath + ", siteParam=" + siteParam
				+ ", priceType=" + priceType + ", multiRoomQuotas="
				+ multiRoomQuotas + ", receiptType=" + receiptType
				+ ", receiptOtherTypeDesc=" + receiptOtherTypeDesc
				+ ", receiptInfo=" + receiptInfo + ", hasReceipt=" + hasReceipt
				+ ", status=" + status + ", refundPolicyInfo="
				+ refundPolicyInfo + ", hname=" + hname + ", rname=" + rname
				+ ", gname=" + gname + ", roomQuotaId=" + roomQuotaId + "]";
	}
}
