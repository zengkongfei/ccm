
package com.ccm.webservices.og.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * represents the information regarding the claims submitted
 * 
 * <p>Claim complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Claim">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaimId" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ClaimDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ClaimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecordType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaimStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinalStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaimInformation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CloseDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ClaimSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CallerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NameID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="MembershipID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="MembershipType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MembershipCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PmsReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrsReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaimComments" type="{http://webservices.micros.com/og/4.3/Common/}ClaimCommentsList" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Claim", propOrder = {
    "claimId",
    "claimDate",
    "claimType",
    "recordType",
    "resort",
    "claimStatus",
    "finalStatus",
    "claimInformation",
    "closeDate",
    "claimSource",
    "callerName",
    "owner",
    "nameID",
    "membershipID",
    "membershipType",
    "membershipCardNumber",
    "pmsReferenceNumber",
    "orsReferenceNumber",
    "claimComments",
    "startDate",
    "endDate"
})
public class Claim {

    @XmlElement(name = "ClaimId")
    protected Double claimId;
    @XmlElement(name = "ClaimDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar claimDate;
    @XmlElement(name = "ClaimType")
    protected String claimType;
    @XmlElement(name = "RecordType")
    protected String recordType;
    @XmlElement(name = "Resort")
    protected String resort;
    @XmlElement(name = "ClaimStatus")
    protected String claimStatus;
    @XmlElement(name = "FinalStatus")
    protected String finalStatus;
    @XmlElement(name = "ClaimInformation", required = true)
    protected String claimInformation;
    @XmlElement(name = "CloseDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar closeDate;
    @XmlElement(name = "ClaimSource")
    protected String claimSource;
    @XmlElement(name = "CallerName")
    protected String callerName;
    @XmlElement(name = "Owner")
    protected String owner;
    @XmlElement(name = "NameID")
    protected UniqueID nameID;
    @XmlElement(name = "MembershipID")
    protected UniqueID membershipID;
    @XmlElement(name = "MembershipType")
    protected String membershipType;
    @XmlElement(name = "MembershipCardNumber")
    protected String membershipCardNumber;
    @XmlElement(name = "PmsReferenceNumber")
    protected String pmsReferenceNumber;
    @XmlElement(name = "OrsReferenceNumber")
    protected String orsReferenceNumber;
    @XmlElement(name = "ClaimComments")
    protected ClaimCommentsList claimComments;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;

    /**
     * 获取claimId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getClaimId() {
        return claimId;
    }

    /**
     * 设置claimId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setClaimId(Double value) {
        this.claimId = value;
    }

    /**
     * 获取claimDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClaimDate() {
        return claimDate;
    }

    /**
     * 设置claimDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClaimDate(XMLGregorianCalendar value) {
        this.claimDate = value;
    }

    /**
     * 获取claimType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimType() {
        return claimType;
    }

    /**
     * 设置claimType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimType(String value) {
        this.claimType = value;
    }

    /**
     * 获取recordType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置recordType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordType(String value) {
        this.recordType = value;
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
     * 获取claimStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimStatus() {
        return claimStatus;
    }

    /**
     * 设置claimStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimStatus(String value) {
        this.claimStatus = value;
    }

    /**
     * 获取finalStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalStatus() {
        return finalStatus;
    }

    /**
     * 设置finalStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalStatus(String value) {
        this.finalStatus = value;
    }

    /**
     * 获取claimInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimInformation() {
        return claimInformation;
    }

    /**
     * 设置claimInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimInformation(String value) {
        this.claimInformation = value;
    }

    /**
     * 获取closeDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCloseDate() {
        return closeDate;
    }

    /**
     * 设置closeDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCloseDate(XMLGregorianCalendar value) {
        this.closeDate = value;
    }

    /**
     * 获取claimSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaimSource() {
        return claimSource;
    }

    /**
     * 设置claimSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaimSource(String value) {
        this.claimSource = value;
    }

    /**
     * 获取callerName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallerName() {
        return callerName;
    }

    /**
     * 设置callerName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallerName(String value) {
        this.callerName = value;
    }

    /**
     * 获取owner属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置owner属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * 获取nameID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getNameID() {
        return nameID;
    }

    /**
     * 设置nameID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setNameID(UniqueID value) {
        this.nameID = value;
    }

    /**
     * 获取membershipID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setMembershipID(UniqueID value) {
        this.membershipID = value;
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
     * 获取membershipCardNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipCardNumber() {
        return membershipCardNumber;
    }

    /**
     * 设置membershipCardNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipCardNumber(String value) {
        this.membershipCardNumber = value;
    }

    /**
     * 获取pmsReferenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmsReferenceNumber() {
        return pmsReferenceNumber;
    }

    /**
     * 设置pmsReferenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmsReferenceNumber(String value) {
        this.pmsReferenceNumber = value;
    }

    /**
     * 获取orsReferenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrsReferenceNumber() {
        return orsReferenceNumber;
    }

    /**
     * 设置orsReferenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrsReferenceNumber(String value) {
        this.orsReferenceNumber = value;
    }

    /**
     * 获取claimComments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ClaimCommentsList }
     *     
     */
    public ClaimCommentsList getClaimComments() {
        return claimComments;
    }

    /**
     * 设置claimComments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ClaimCommentsList }
     *     
     */
    public void setClaimComments(ClaimCommentsList value) {
        this.claimComments = value;
    }

    /**
     * 获取startDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
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

}
