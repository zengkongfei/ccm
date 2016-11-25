
package com.ccm.reservation;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RoomSetup complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomSetup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph"/>
 *         &lt;element name="RoomShortDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RoomType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SuiteType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaximumOccupancy" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" />
 *       &lt;attribute name="HouseKeepingSectionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RateCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RackRate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="SmokingPreference" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomSetup", propOrder = {
    "roomDescription",
    "roomShortDescription"
})
public class RoomSetup {

    @XmlElement(name = "RoomDescription", required = true)
    protected Paragraph roomDescription;
    @XmlElement(name = "RoomShortDescription", required = true)
    protected Paragraph roomShortDescription;
    @XmlAttribute(name = "RoomType", required = true)
    protected String roomType;
    @XmlAttribute(name = "RoomNumber", required = true)
    protected String roomNumber;
    @XmlAttribute(name = "SuiteType")
    protected String suiteType;
    @XmlAttribute(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlAttribute(name = "MaximumOccupancy")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maximumOccupancy;
    @XmlAttribute(name = "HouseKeepingSectionCode")
    protected String houseKeepingSectionCode;
    @XmlAttribute(name = "RateCode")
    protected String rateCode;
    @XmlAttribute(name = "RackRate")
    protected BigDecimal rackRate;
    @XmlAttribute(name = "SmokingPreference")
    protected String smokingPreference;

    /**
     * 获取roomDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomDescription() {
        return roomDescription;
    }

    /**
     * 设置roomDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomDescription(Paragraph value) {
        this.roomDescription = value;
    }

    /**
     * 获取roomShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomShortDescription() {
        return roomShortDescription;
    }

    /**
     * 设置roomShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomShortDescription(Paragraph value) {
        this.roomShortDescription = value;
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
     * 获取roomNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 设置roomNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * 获取suiteType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuiteType() {
        return suiteType;
    }

    /**
     * 设置suiteType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuiteType(String value) {
        this.suiteType = value;
    }

    /**
     * 获取phoneNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置phoneNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * 获取maximumOccupancy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaximumOccupancy() {
        return maximumOccupancy;
    }

    /**
     * 设置maximumOccupancy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaximumOccupancy(Long value) {
        this.maximumOccupancy = value;
    }

    /**
     * 获取houseKeepingSectionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseKeepingSectionCode() {
        return houseKeepingSectionCode;
    }

    /**
     * 设置houseKeepingSectionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseKeepingSectionCode(String value) {
        this.houseKeepingSectionCode = value;
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
     * 获取rackRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRackRate() {
        return rackRate;
    }

    /**
     * 设置rackRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRackRate(BigDecimal value) {
        this.rackRate = value;
    }

    /**
     * 获取smokingPreference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmokingPreference() {
        return smokingPreference;
    }

    /**
     * 设置smokingPreference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmokingPreference(String value) {
        this.smokingPreference = value;
    }

}
