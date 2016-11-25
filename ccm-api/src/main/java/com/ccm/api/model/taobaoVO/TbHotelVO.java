package com.ccm.api.model.taobaoVO;


/**
 * 酒店淘宝请求参数
 * @author carr
 *
 */
public class TbHotelVO {
	/**
	 * 酒店ID
	 */
	private long hid;
	/**
	 * 酒店名称
	 * 如：北京饭店  。不能超过60
	 */
	private String name;  
	/**
	 * 是否国内酒店
	 * 如：true。可选值：true，false
	 */
	private Boolean domestic;
	/**
	 * 如：China  domestic为true时，固定China； domestic为false时，必须传定义的海外国家编码值。
	 */
	private String country;
	/**
	 * 省份编码
	 * 如：110000  。domestic为false时默认为0
	 */
	private long province;
	/**
	 *  城市编码
	 * 如：110100 。domestic为false时，输入对应国家的海外城市编码，可调用海外城市查询接口获取  
	 */
	private long city;
	/**
	 * 如：110101 0 区域（县级市）编码。
	 */
	private long district;
	/**
	 * 酒店地址
	 * 如：西直门  。长度不能超过120
	 */
	private String address;
	/**
	 * 酒店级别
	 * 如：A  。可选值：A,B,C,D,E,F。代表客栈公寓、经济连锁、二星级/以下、三星级/舒适、四星级/高档、五星级/豪华  
	 */
	private String level;
	/**
	 * 酒店定位
	 *  如：T。可选值：T,B。代表旅游度假、商务出行  
	 */
	private String orientation;
	/**
	 * 酒店电话
	 * 如：0086#010#12345678  。格式：国家代码（最长6位）#区号（最长4位）#电话（最长20位）。
	 * 国家代码提示：中国大陆0086、香港00852、澳门00853、台湾00886
	 */
	private String tel;
	/**
	 * 开业年份
	 * 如：2011  。长度不能超过4。
	 */
	private String openingTime;
	/**
	 * 装修年份
	 * 如：2011  。长度不能超过4。  
	 */
	private String decorateTime;
	/**
	 * 楼层数
	 * 如：10 。长度不能超过4。  
	 */
	private long storeys;
	/**
	 * 房间数
	 * 如：100  。长度不能超过4。
	 */
	private long rooms;
	/**
	 *  酒店介绍
	 *  如：北京饭店在王府井 。不超过25000个汉字  
	 */
	private String desc;
	/**
	 * 交通距离与设施服务
	 * 如：{"airportShuttle":true}。JSON格式。cityCenterDistance、railwayDistance、airportDistance分别代表距离市中心、距离火车站、距离机场公里数，为不超过3位正整数，默认-1代表无数据。其他key值true代表有此服务，false代表没有。 parking：停车场，airportShuttle：机场接送，rentCar：租车，meetingRoom：会议室，businessCenter：商务中心，swimmingPool：游泳池，fitnessClub：健身中心，laundry：洗衣服务，morningCall：叫早服务，bankCard：接受银行卡，creditCard：接受信用卡，chineseRestaurant：中餐厅，westernRestaurant：西餐厅，cafe：咖啡厅，bar：酒吧，ktv：KTV。  
	 */
	private String service;
	/**
	 * 酒店图片路径
	 *  如：我是被上传的文件内容  。最大长度:500K。支持的文件类型：gif,jpg,png 支持的文件类型：jpg,png,gif
	 */
	private String pic;
	/**
	 * 接入卖家数据主键
	 */
	private String siteParam;
	
	public long getHid() {
		return hid;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getDomestic() {
		return domestic;
	}
	public void setDomestic(Boolean domestic) {
		this.domestic = domestic;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getProvince() {
		return province;
	}
	public void setProvince(long province) {
		this.province = province;
	}
	public long getCity() {
		return city;
	}
	public void setCity(long city) {
		this.city = city;
	}
	public long getDistrict() {
		return district;
	}
	public void setDistrict(long district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getDecorateTime() {
		return decorateTime;
	}
	public void setDecorateTime(String decorateTime) {
		this.decorateTime = decorateTime;
	}
	public long getStoreys() {
		return storeys;
	}
	public void setStoreys(long storeys) {
		this.storeys = storeys;
	}
	public long getRooms() {
		return rooms;
	}
	public void setRooms(long rooms) {
		this.rooms = rooms;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getSiteParam() {
		return siteParam;
	}
	public void setSiteParam(String siteParam) {
		this.siteParam = siteParam;
	}
	
	@Override
	public String toString() {
		return "HotelVO [hid=" + hid + ", name=" + name + ", domestic="
				+ domestic + ", country=" + country + ", province=" + province
				+ ", city=" + city + ", district=" + district + ", address="
				+ address + ", level=" + level + ", orientation=" + orientation
				+ ", tel=" + tel + ", openingTime=" + openingTime
				+ ", decorateTime=" + decorateTime + ", storeys=" + storeys
				+ ", rooms=" + rooms + ", desc=" + desc + ", service="
				+ service + ", pic=" + pic + ", siteParam=" + siteParam + "]";
	}
}
