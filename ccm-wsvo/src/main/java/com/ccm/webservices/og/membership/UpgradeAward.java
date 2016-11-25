
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * represents a membership award in the form of an room upgrade on a hotel stay
 * 
 * <p>UpgradeAward complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UpgradeAward">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="membershipType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="awardType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromRoomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toRoomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromRoomCategoryLabel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toRoomCategoryLabel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pointsRequired" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="beginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyDays" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyCharge" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cancelPenaltyPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="issueKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueKeyRaw" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpgradeAward", propOrder = {
    "membershipType",
    "awardType",
    "resort",
    "fromRoomCategory",
    "toRoomCategory",
    "fromRoomCategoryLabel",
    "toRoomCategoryLabel",
    "pointsRequired",
    "beginDate",
    "endDate",
    "cancelPenaltyDays",
    "cancelPenaltyCharge",
    "cancelPenaltyPoints",
    "issueKey",
    "issueKeyRaw"
})
public class UpgradeAward {

    @XmlElement(required = true)
    protected String membershipType;
    @XmlElement(required = true)
    protected String awardType;
    @XmlElement(required = true)
    protected String resort;
    @XmlElement(required = true)
    protected String fromRoomCategory;
    @XmlElement(required = true)
    protected String toRoomCategory;
    @XmlElement(required = true)
    protected String fromRoomCategoryLabel;
    @XmlElement(required = true)
    protected String toRoomCategoryLabel;
    protected double pointsRequired;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar beginDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    protected Double cancelPenaltyDays;
    protected Double cancelPenaltyCharge;
    protected Double cancelPenaltyPoints;
    protected String issueKey;
    protected byte[] issueKeyRaw;

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
     * 获取fromRoomCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromRoomCategory() {
        return fromRoomCategory;
    }

    /**
     * 设置fromRoomCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromRoomCategory(String value) {
        this.fromRoomCategory = value;
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
     * 获取fromRoomCategoryLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromRoomCategoryLabel() {
        return fromRoomCategoryLabel;
    }

    /**
     * 设置fromRoomCategoryLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromRoomCategoryLabel(String value) {
        this.fromRoomCategoryLabel = value;
    }

    /**
     * 获取toRoomCategoryLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToRoomCategoryLabel() {
        return toRoomCategoryLabel;
    }

    /**
     * 设置toRoomCategoryLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToRoomCategoryLabel(String value) {
        this.toRoomCategoryLabel = value;
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

}
