
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * represents room upsell on a hotel stay
 * 
 * <p>UpsellRoom complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UpsellRoom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="UpsellOfferId" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ToRateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToRoomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ToRoomName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ToDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ToShortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UpsellAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="UpsellTotalAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="UpsellRankingOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MembershipType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MembershipLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpsellRoom", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "roomCategory",
    "firstAmount",
    "totalAmount",
    "upsellOfferId",
    "toRateCode",
    "toRoomCategory",
    "toRoomName",
    "toDescription",
    "toShortDescription",
    "upsellAmount",
    "upsellTotalAmount",
    "upsellRankingOrder",
    "membershipType",
    "membershipLevel"
})
public class UpsellRoom {

    @XmlElement(name = "RoomCategory", required = true)
    protected String roomCategory;
    @XmlElement(name = "FirstAmount")
    protected Amount firstAmount;
    @XmlElement(name = "TotalAmount")
    protected Amount totalAmount;
    @XmlElement(name = "UpsellOfferId")
    protected Double upsellOfferId;
    @XmlElement(name = "ToRateCode")
    protected String toRateCode;
    @XmlElement(name = "ToRoomCategory", required = true)
    protected String toRoomCategory;
    @XmlElement(name = "ToRoomName", required = true)
    protected String toRoomName;
    @XmlElement(name = "ToDescription")
    protected String toDescription;
    @XmlElement(name = "ToShortDescription")
    protected String toShortDescription;
    @XmlElement(name = "UpsellAmount")
    protected Amount upsellAmount;
    @XmlElement(name = "UpsellTotalAmount")
    protected Amount upsellTotalAmount;
    @XmlElement(name = "UpsellRankingOrder")
    protected Integer upsellRankingOrder;
    @XmlElement(name = "MembershipType")
    protected String membershipType;
    @XmlElement(name = "MembershipLevel")
    protected String membershipLevel;

    /**
     * 获取roomCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomCategory() {
        return roomCategory;
    }

    /**
     * 设置roomCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomCategory(String value) {
        this.roomCategory = value;
    }

    /**
     * 获取firstAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getFirstAmount() {
        return firstAmount;
    }

    /**
     * 设置firstAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setFirstAmount(Amount value) {
        this.firstAmount = value;
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
     * 获取upsellOfferId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUpsellOfferId() {
        return upsellOfferId;
    }

    /**
     * 设置upsellOfferId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUpsellOfferId(Double value) {
        this.upsellOfferId = value;
    }

    /**
     * 获取toRateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToRateCode() {
        return toRateCode;
    }

    /**
     * 设置toRateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToRateCode(String value) {
        this.toRateCode = value;
    }

    /**
     * 获取toRoomCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToRoomCategory() {
        return toRoomCategory;
    }

    /**
     * 设置toRoomCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToRoomCategory(String value) {
        this.toRoomCategory = value;
    }

    /**
     * 获取toRoomName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToRoomName() {
        return toRoomName;
    }

    /**
     * 设置toRoomName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToRoomName(String value) {
        this.toRoomName = value;
    }

    /**
     * 获取toDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToDescription() {
        return toDescription;
    }

    /**
     * 设置toDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToDescription(String value) {
        this.toDescription = value;
    }

    /**
     * 获取toShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToShortDescription() {
        return toShortDescription;
    }

    /**
     * 设置toShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToShortDescription(String value) {
        this.toShortDescription = value;
    }

    /**
     * 获取upsellAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getUpsellAmount() {
        return upsellAmount;
    }

    /**
     * 设置upsellAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setUpsellAmount(Amount value) {
        this.upsellAmount = value;
    }

    /**
     * 获取upsellTotalAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getUpsellTotalAmount() {
        return upsellTotalAmount;
    }

    /**
     * 设置upsellTotalAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setUpsellTotalAmount(Amount value) {
        this.upsellTotalAmount = value;
    }

    /**
     * 获取upsellRankingOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUpsellRankingOrder() {
        return upsellRankingOrder;
    }

    /**
     * 设置upsellRankingOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUpsellRankingOrder(Integer value) {
        this.upsellRankingOrder = value;
    }

    /**
     * 获取membershipType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * 设置membershipType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipType(String value) {
        this.membershipType = value;
    }

    /**
     * 获取membershipLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipLevel() {
        return membershipLevel;
    }

    /**
     * 设置membershipLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipLevel(String value) {
        this.membershipLevel = value;
    }

}
