
package com.ccm.stayHistory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>StayInfoDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StayInfoDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrsResID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConfirmationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PmsResID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuestID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GuestFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuestNativeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuestLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Arrival" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Departure" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RoomType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="RoomRev" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="RoomNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Channel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StayInfoDetail", namespace = "http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/", propOrder = {
    "hotelCode",
    "crsResID",
    "confirmationNo",
    "pmsResID",
    "status",
    "guestID",
    "guestFirstName",
    "guestNativeName",
    "guestLastName",
    "arrival",
    "departure",
    "roomType",
    "roomDesc",
    "rateCode",
    "rate",
    "roomRev",
    "roomNo",
    "channel",
    "resDate",
    "remark"
})
public class StayInfoDetail {

    @XmlElement(name = "HotelCode")
    protected String hotelCode;
    @XmlElement(name = "CrsResID")
    protected String crsResID;
    @XmlElement(name = "ConfirmationNo")
    protected String confirmationNo;
    @XmlElement(name = "PmsResID")
    protected String pmsResID;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "GuestID")
    protected int guestID;
    @XmlElement(name = "GuestFirstName")
    protected String guestFirstName;
    @XmlElement(name = "GuestNativeName")
    protected String guestNativeName;
    @XmlElement(name = "GuestLastName")
    protected String guestLastName;
    @XmlElement(name = "Arrival", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrival;
    @XmlElement(name = "Departure", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departure;
    @XmlElement(name = "RoomType")
    protected String roomType;
    @XmlElement(name = "RoomDesc")
    protected String roomDesc;
    @XmlElement(name = "RateCode")
    protected String rateCode;
    @XmlElement(name = "Rate")
    protected float rate;
    @XmlElement(name = "RoomRev")
    protected float roomRev;
    @XmlElement(name = "RoomNo")
    protected String roomNo;
    @XmlElement(name = "Channel")
    protected String channel;
    @XmlElement(name = "ResDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resDate;
    @XmlElement(name = "Remark")
    protected String remark;

    /**
     * 获取hotelCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelCode() {
        return hotelCode;
    }

    /**
     * 设置hotelCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelCode(String value) {
        this.hotelCode = value;
    }

    /**
     * 获取crsResID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrsResID() {
        return crsResID;
    }

    /**
     * 设置crsResID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrsResID(String value) {
        this.crsResID = value;
    }

    /**
     * 获取confirmationNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationNo() {
        return confirmationNo;
    }

    /**
     * 设置confirmationNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationNo(String value) {
        this.confirmationNo = value;
    }

    /**
     * 获取pmsResID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmsResID() {
        return pmsResID;
    }

    /**
     * 设置pmsResID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmsResID(String value) {
        this.pmsResID = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * 获取guestID属性的值。
     * 
     */
    public int getGuestID() {
        return guestID;
    }

    /**
     * 设置guestID属性的值。
     * 
     */
    public void setGuestID(int value) {
        this.guestID = value;
    }

    /**
     * 获取guestFirstName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuestFirstName() {
        return guestFirstName;
    }

    /**
     * 设置guestFirstName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuestFirstName(String value) {
        this.guestFirstName = value;
    }

    /**
     * 获取guestNativeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuestNativeName() {
        return guestNativeName;
    }

    /**
     * 设置guestNativeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuestNativeName(String value) {
        this.guestNativeName = value;
    }

    /**
     * 获取guestLastName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuestLastName() {
        return guestLastName;
    }

    /**
     * 设置guestLastName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuestLastName(String value) {
        this.guestLastName = value;
    }

    /**
     * 获取arrival属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrival() {
        return arrival;
    }

    /**
     * 设置arrival属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrival(XMLGregorianCalendar value) {
        this.arrival = value;
    }

    /**
     * 获取departure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeparture() {
        return departure;
    }

    /**
     * 设置departure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeparture(XMLGregorianCalendar value) {
        this.departure = value;
    }

    /**
     * 获取roomType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 设置roomType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomType(String value) {
        this.roomType = value;
    }

    /**
     * 获取roomDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * 设置roomDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomDesc(String value) {
        this.roomDesc = value;
    }

    /**
     * 获取rateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * 设置rateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

    /**
     * 获取rate属性的值。
     * 
     */
    public float getRate() {
        return rate;
    }

    /**
     * 设置rate属性的值。
     * 
     */
    public void setRate(float value) {
        this.rate = value;
    }

    /**
     * 获取roomRev属性的值。
     * 
     */
    public float getRoomRev() {
        return roomRev;
    }

    /**
     * 设置roomRev属性的值。
     * 
     */
    public void setRoomRev(float value) {
        this.roomRev = value;
    }

    /**
     * 获取roomNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * 设置roomNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNo(String value) {
        this.roomNo = value;
    }

    /**
     * 获取channel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置channel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * 获取resDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResDate() {
        return resDate;
    }

    /**
     * 设置resDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResDate(XMLGregorianCalendar value) {
        this.resDate = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
