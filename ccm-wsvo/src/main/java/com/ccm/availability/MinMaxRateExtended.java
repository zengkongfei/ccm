
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MinMaxRateExtended complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MinMaxRateExtended">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *         &lt;element name="RateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCodeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCodeShortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RateCodeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomTypeShortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancellationPolicy" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="IsRateChange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TotalAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MinMaxRateType" use="required" type="{http://webservices.micros.com/og/4.3/Availability/}MinMaxRateIndicator" />
 *       &lt;attribute name="CommissionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinMaxRateExtended", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "amount",
    "rateCode",
    "rateCodeDescription",
    "rateCodeShortDescription",
    "rateCodeName",
    "roomType",
    "roomTypeDescription",
    "roomTypeShortDescription",
    "roomTypeName",
    "cancellationPolicy",
    "isRateChange",
    "totalAmount"
})
public class MinMaxRateExtended {

    @XmlElement(name = "Amount", required = true)
    protected Amount amount;
    @XmlElement(name = "RateCode")
    protected String rateCode;
    @XmlElement(name = "RateCodeDescription")
    protected String rateCodeDescription;
    @XmlElement(name = "RateCodeShortDescription")
    protected String rateCodeShortDescription;
    @XmlElement(name = "RateCodeName")
    protected String rateCodeName;
    @XmlElement(name = "RoomType")
    protected String roomType;
    @XmlElement(name = "RoomTypeDescription")
    protected String roomTypeDescription;
    @XmlElement(name = "RoomTypeShortDescription")
    protected String roomTypeShortDescription;
    @XmlElement(name = "RoomTypeName")
    protected String roomTypeName;
    @XmlElement(name = "CancellationPolicy")
    protected Paragraph cancellationPolicy;
    @XmlElement(name = "IsRateChange")
    protected Boolean isRateChange;
    @XmlElement(name = "TotalAmount")
    protected Amount totalAmount;
    @XmlAttribute(name = "MinMaxRateType", required = true)
    protected MinMaxRateIndicator minMaxRateType;
    @XmlAttribute(name = "CommissionCode")
    protected String commissionCode;

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
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
     * 获取rateCodeDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCodeDescription() {
        return rateCodeDescription;
    }

    /**
     * 设置rateCodeDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCodeDescription(String value) {
        this.rateCodeDescription = value;
    }

    /**
     * 获取rateCodeShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCodeShortDescription() {
        return rateCodeShortDescription;
    }

    /**
     * 设置rateCodeShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCodeShortDescription(String value) {
        this.rateCodeShortDescription = value;
    }

    /**
     * 获取rateCodeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCodeName() {
        return rateCodeName;
    }

    /**
     * 设置rateCodeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCodeName(String value) {
        this.rateCodeName = value;
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
     * 获取roomTypeDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeDescription() {
        return roomTypeDescription;
    }

    /**
     * 设置roomTypeDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeDescription(String value) {
        this.roomTypeDescription = value;
    }

    /**
     * 获取roomTypeShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeShortDescription() {
        return roomTypeShortDescription;
    }

    /**
     * 设置roomTypeShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeShortDescription(String value) {
        this.roomTypeShortDescription = value;
    }

    /**
     * 获取roomTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * 设置roomTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeName(String value) {
        this.roomTypeName = value;
    }

    /**
     * 获取cancellationPolicy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getCancellationPolicy() {
        return cancellationPolicy;
    }

    /**
     * 设置cancellationPolicy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setCancellationPolicy(Paragraph value) {
        this.cancellationPolicy = value;
    }

    /**
     * 获取isRateChange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRateChange() {
        return isRateChange;
    }

    /**
     * 设置isRateChange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRateChange(Boolean value) {
        this.isRateChange = value;
    }

    /**
     * 获取totalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置totalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalAmount(Amount value) {
        this.totalAmount = value;
    }

    /**
     * 获取minMaxRateType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MinMaxRateIndicator }
     *     
     */
    public MinMaxRateIndicator getMinMaxRateType() {
        return minMaxRateType;
    }

    /**
     * 设置minMaxRateType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MinMaxRateIndicator }
     *     
     */
    public void setMinMaxRateType(MinMaxRateIndicator value) {
        this.minMaxRateType = value;
    }

    /**
     * 获取commissionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommissionCode() {
        return commissionCode;
    }

    /**
     * 设置commissionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommissionCode(String value) {
        this.commissionCode = value;
    }

}
