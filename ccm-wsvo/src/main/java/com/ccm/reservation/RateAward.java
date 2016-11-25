
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * represents a membership award in the form of a rate code for a hotel stay
 * 
 * <p>RateAward complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RateAward">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="membershipType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="awardType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pointsRequired" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="roomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyDays" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyCharge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="issueKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueKeyRaw" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="RateDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RoomDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DailyPoints" type="{http://webservices.micros.com/og/4.3/Membership/}PointsChangeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateAward", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "membershipType",
    "awardType",
    "resort",
    "rateCode",
    "pointsRequired",
    "roomCategory",
    "beginDate",
    "endDate",
    "cancelPenaltyDays",
    "cancelPenaltyCharge",
    "cancelPenaltyPoints",
    "issueKey",
    "issueKeyRaw",
    "rateDescription",
    "roomDescription",
    "dailyPoints"
})
public class RateAward {

    @XmlElement(required = true)
    protected String membershipType;
    @XmlElement(required = true)
    protected String awardType;
    @XmlElement(required = true)
    protected String resort;
    @XmlElement(required = true)
    protected String rateCode;
    protected double pointsRequired;
    @XmlElement(required = true)
    protected String roomCategory;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar beginDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    protected Double cancelPenaltyDays;
    protected Double cancelPenaltyCharge;
    protected Double cancelPenaltyPoints;
    protected String issueKey;
    protected byte[] issueKeyRaw;
    @XmlElement(name = "RateDescription", required = true)
    protected String rateDescription;
    @XmlElement(name = "RoomDescription", required = true)
    protected String roomDescription;
    @XmlElement(name = "DailyPoints")
    protected PointsChangeList dailyPoints;

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
     * 获取resort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResort() {
        return resort;
    }

    /**
     * 设置resort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResort(String value) {
        this.resort = value;
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
     * 获取pointsRequired属性的值。
     * 
     */
    public double getPointsRequired() {
        return pointsRequired;
    }

    /**
     * 设置pointsRequired属性的值。
     * 
     */
    public void setPointsRequired(double value) {
        this.pointsRequired = value;
    }

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
     * 获取beginDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginDate() {
        return beginDate;
    }

    /**
     * 设置beginDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginDate(XMLGregorianCalendar value) {
        this.beginDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * 获取cancelPenaltyDays属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCancelPenaltyDays() {
        return cancelPenaltyDays;
    }

    /**
     * 设置cancelPenaltyDays属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCancelPenaltyDays(Double value) {
        this.cancelPenaltyDays = value;
    }

    /**
     * 获取cancelPenaltyCharge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCancelPenaltyCharge() {
        return cancelPenaltyCharge;
    }

    /**
     * 设置cancelPenaltyCharge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCancelPenaltyCharge(Double value) {
        this.cancelPenaltyCharge = value;
    }

    /**
     * 获取cancelPenaltyPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCancelPenaltyPoints() {
        return cancelPenaltyPoints;
    }

    /**
     * 设置cancelPenaltyPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCancelPenaltyPoints(Double value) {
        this.cancelPenaltyPoints = value;
    }

    /**
     * 获取issueKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueKey() {
        return issueKey;
    }

    /**
     * 设置issueKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueKey(String value) {
        this.issueKey = value;
    }

    /**
     * 获取issueKeyRaw属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIssueKeyRaw() {
        return issueKeyRaw;
    }

    /**
     * 设置issueKeyRaw属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIssueKeyRaw(byte[] value) {
        this.issueKeyRaw = value;
    }

    /**
     * 获取rateDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateDescription() {
        return rateDescription;
    }

    /**
     * 设置rateDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateDescription(String value) {
        this.rateDescription = value;
    }

    /**
     * 获取roomDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * 设置roomDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomDescription(String value) {
        this.roomDescription = value;
    }

    /**
     * 获取dailyPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PointsChangeList }
     *     
     */
    public PointsChangeList getDailyPoints() {
        return dailyPoints;
    }

    /**
     * 设置dailyPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PointsChangeList }
     *     
     */
    public void setDailyPoints(PointsChangeList value) {
        this.dailyPoints = value;
    }

}
