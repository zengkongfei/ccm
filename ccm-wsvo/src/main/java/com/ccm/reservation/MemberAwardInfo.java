
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MemberAwardInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MemberAwardInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="awardType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="membershipID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pointsUsedForReservation" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="redemRateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stayDate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="overridePenalty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MemberAwardInfo", propOrder = {

})
public class MemberAwardInfo {

    @XmlElement(required = true)
    protected String awardType;
    protected long membershipID;
    protected Double pointsUsedForReservation;
    protected String redemRateCode;
    protected TimeSpan stayDate;
    protected String overridePenalty;

    /**
     * 获取awardType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwardType() {
        return awardType;
    }

    /**
     * 设置awardType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwardType(String value) {
        this.awardType = value;
    }

    /**
     * 获取membershipID属性的值。
     * 
     */
    public long getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     */
    public void setMembershipID(long value) {
        this.membershipID = value;
    }

    /**
     * 获取pointsUsedForReservation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPointsUsedForReservation() {
        return pointsUsedForReservation;
    }

    /**
     * 设置pointsUsedForReservation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPointsUsedForReservation(Double value) {
        this.pointsUsedForReservation = value;
    }

    /**
     * 获取redemRateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedemRateCode() {
        return redemRateCode;
    }

    /**
     * 设置redemRateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedemRateCode(String value) {
        this.redemRateCode = value;
    }

    /**
     * 获取stayDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDate() {
        return stayDate;
    }

    /**
     * 设置stayDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDate(TimeSpan value) {
        this.stayDate = value;
    }

    /**
     * 获取overridePenalty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverridePenalty() {
        return overridePenalty;
    }

    /**
     * 设置overridePenalty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverridePenalty(String value) {
        this.overridePenalty = value;
    }

}
