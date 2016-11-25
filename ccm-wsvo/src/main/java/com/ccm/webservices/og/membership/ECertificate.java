
package com.ccm.webservices.og.membership;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ccm.webservices.og.common.Amount;
import com.ccm.webservices.og.common.UniqueID;
import com.ccm.webservices.og.hotelcommon.HotelReference;


/**
 * <p>ECertificate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ECertificate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertificateID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="VoucherNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertificateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="MembershipType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AwardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromotionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShortDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ReservationCertificateYN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertificateValue" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="CertificateCost" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="CertificateLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NameID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="ConsumedAt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumerLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumerFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumerMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumerEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumptionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ConsumedHotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *         &lt;element name="ConsumptionRefNo" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="ConsumptionRefType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsumptionLegNo" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="UserNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrintStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MembershipAwardID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID" minOccurs="0"/>
 *         &lt;element name="IssueType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IssueSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AwardPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="InActiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CertificateBeginDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CertificateEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CertificateExpriyMonths" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="WebConsumed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebAllowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PMSConsumed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Awards" type="{http://webservices.micros.com/og/4.3/Membership/}Award" minOccurs="0"/>
 *         &lt;element name="Promotions" type="{http://webservices.micros.com/og/4.3/Membership/}PromotionDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECertificate", propOrder = {
    "certificateID",
    "voucherNumber",
    "certificateNumber",
    "certificateCode",
    "hotelReference",
    "membershipType",
    "awardType",
    "promotionCode",
    "shortDescription",
    "longDescription",
    "expirationDate",
    "reservationCertificateYN",
    "certificateValue",
    "certificateCost",
    "certificateLabel",
    "nameID",
    "consumedAt",
    "consumerLastName",
    "consumerFirstName",
    "consumerMiddleName",
    "consumerEmail",
    "consumptionDate",
    "consumedHotelReference",
    "consumptionRefNo",
    "consumptionRefType",
    "consumptionLegNo",
    "userNotes",
    "status",
    "printStatus",
    "membershipAwardID",
    "issueType",
    "issueSource",
    "awardPoints",
    "inActiveDate",
    "certificateBeginDate",
    "certificateEndDate",
    "certificateExpriyMonths",
    "webConsumed",
    "webAllowed",
    "pmsConsumed",
    "awards",
    "promotions",
    "scope"
})
public class ECertificate {

    @XmlElement(name = "CertificateID")
    protected UniqueID certificateID;
    @XmlElement(name = "VoucherNumber")
    protected String voucherNumber;
    @XmlElement(name = "CertificateNumber")
    protected String certificateNumber;
    @XmlElement(name = "CertificateCode", required = true)
    protected String certificateCode;
    @XmlElement(name = "HotelReference")
    protected HotelReference hotelReference;
    @XmlElement(name = "MembershipType")
    protected String membershipType;
    @XmlElement(name = "AwardType")
    protected String awardType;
    @XmlElement(name = "PromotionCode")
    protected String promotionCode;
    @XmlElement(name = "ShortDescription")
    protected String shortDescription;
    @XmlElement(name = "LongDescription")
    protected String longDescription;
    @XmlElement(name = "ExpirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "ReservationCertificateYN")
    protected String reservationCertificateYN;
    @XmlElement(name = "CertificateValue")
    protected Amount certificateValue;
    @XmlElement(name = "CertificateCost")
    protected Amount certificateCost;
    @XmlElement(name = "CertificateLabel")
    protected String certificateLabel;
    @XmlElement(name = "NameID")
    protected UniqueID nameID;
    @XmlElement(name = "ConsumedAt")
    protected String consumedAt;
    @XmlElement(name = "ConsumerLastName")
    protected String consumerLastName;
    @XmlElement(name = "ConsumerFirstName")
    protected String consumerFirstName;
    @XmlElement(name = "ConsumerMiddleName")
    protected String consumerMiddleName;
    @XmlElement(name = "ConsumerEmail")
    protected String consumerEmail;
    @XmlElement(name = "ConsumptionDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar consumptionDate;
    @XmlElement(name = "ConsumedHotelReference")
    protected HotelReference consumedHotelReference;
    @XmlElement(name = "ConsumptionRefNo")
    protected UniqueID consumptionRefNo;
    @XmlElement(name = "ConsumptionRefType")
    protected String consumptionRefType;
    @XmlElement(name = "ConsumptionLegNo")
    protected UniqueID consumptionLegNo;
    @XmlElement(name = "UserNotes")
    protected String userNotes;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "PrintStatus")
    protected String printStatus;
    @XmlElement(name = "MembershipAwardID")
    protected UniqueID membershipAwardID;
    @XmlElement(name = "IssueType")
    protected String issueType;
    @XmlElement(name = "IssueSource")
    protected String issueSource;
    @XmlElement(name = "AwardPoints")
    protected Double awardPoints;
    @XmlElement(name = "InActiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inActiveDate;
    @XmlElement(name = "CertificateBeginDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar certificateBeginDate;
    @XmlElement(name = "CertificateEndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar certificateEndDate;
    @XmlElement(name = "CertificateExpriyMonths")
    protected Integer certificateExpriyMonths;
    @XmlElement(name = "WebConsumed")
    protected String webConsumed;
    @XmlElement(name = "WebAllowed")
    protected String webAllowed;
    @XmlElement(name = "PMSConsumed")
    protected String pmsConsumed;
    @XmlElement(name = "Awards")
    protected Award awards;
    @XmlElement(name = "Promotions")
    protected List<PromotionDetails> promotions;
    @XmlElement(name = "Scope")
    protected String scope;

    /**
     * 获取certificateID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getCertificateID() {
        return certificateID;
    }

    /**
     * 设置certificateID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setCertificateID(UniqueID value) {
        this.certificateID = value;
    }

    /**
     * 获取voucherNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * 设置voucherNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNumber(String value) {
        this.voucherNumber = value;
    }

    /**
     * 获取certificateNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * 设置certificateNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateNumber(String value) {
        this.certificateNumber = value;
    }

    /**
     * 获取certificateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateCode() {
        return certificateCode;
    }

    /**
     * 设置certificateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateCode(String value) {
        this.certificateCode = value;
    }

    /**
     * 获取hotelReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * 设置hotelReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
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
     * 获取promotionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * 设置promotionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

    /**
     * 获取shortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * 设置shortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * 获取longDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * 设置longDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongDescription(String value) {
        this.longDescription = value;
    }

    /**
     * 获取expirationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * 设置expirationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * 获取reservationCertificateYN属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationCertificateYN() {
        return reservationCertificateYN;
    }

    /**
     * 设置reservationCertificateYN属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationCertificateYN(String value) {
        this.reservationCertificateYN = value;
    }

    /**
     * 获取certificateValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCertificateValue() {
        return certificateValue;
    }

    /**
     * 设置certificateValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCertificateValue(Amount value) {
        this.certificateValue = value;
    }

    /**
     * 获取certificateCost属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCertificateCost() {
        return certificateCost;
    }

    /**
     * 设置certificateCost属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCertificateCost(Amount value) {
        this.certificateCost = value;
    }

    /**
     * 获取certificateLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateLabel() {
        return certificateLabel;
    }

    /**
     * 设置certificateLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateLabel(String value) {
        this.certificateLabel = value;
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
     * 获取consumedAt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumedAt() {
        return consumedAt;
    }

    /**
     * 设置consumedAt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumedAt(String value) {
        this.consumedAt = value;
    }

    /**
     * 获取consumerLastName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerLastName() {
        return consumerLastName;
    }

    /**
     * 设置consumerLastName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerLastName(String value) {
        this.consumerLastName = value;
    }

    /**
     * 获取consumerFirstName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerFirstName() {
        return consumerFirstName;
    }

    /**
     * 设置consumerFirstName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerFirstName(String value) {
        this.consumerFirstName = value;
    }

    /**
     * 获取consumerMiddleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerMiddleName() {
        return consumerMiddleName;
    }

    /**
     * 设置consumerMiddleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerMiddleName(String value) {
        this.consumerMiddleName = value;
    }

    /**
     * 获取consumerEmail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerEmail() {
        return consumerEmail;
    }

    /**
     * 设置consumerEmail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerEmail(String value) {
        this.consumerEmail = value;
    }

    /**
     * 获取consumptionDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConsumptionDate() {
        return consumptionDate;
    }

    /**
     * 设置consumptionDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConsumptionDate(XMLGregorianCalendar value) {
        this.consumptionDate = value;
    }

    /**
     * 获取consumedHotelReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getConsumedHotelReference() {
        return consumedHotelReference;
    }

    /**
     * 设置consumedHotelReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setConsumedHotelReference(HotelReference value) {
        this.consumedHotelReference = value;
    }

    /**
     * 获取consumptionRefNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getConsumptionRefNo() {
        return consumptionRefNo;
    }

    /**
     * 设置consumptionRefNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setConsumptionRefNo(UniqueID value) {
        this.consumptionRefNo = value;
    }

    /**
     * 获取consumptionRefType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumptionRefType() {
        return consumptionRefType;
    }

    /**
     * 设置consumptionRefType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumptionRefType(String value) {
        this.consumptionRefType = value;
    }

    /**
     * 获取consumptionLegNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getConsumptionLegNo() {
        return consumptionLegNo;
    }

    /**
     * 设置consumptionLegNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setConsumptionLegNo(UniqueID value) {
        this.consumptionLegNo = value;
    }

    /**
     * 获取userNotes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNotes() {
        return userNotes;
    }

    /**
     * 设置userNotes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNotes(String value) {
        this.userNotes = value;
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
     * 获取printStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintStatus() {
        return printStatus;
    }

    /**
     * 设置printStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintStatus(String value) {
        this.printStatus = value;
    }

    /**
     * 获取membershipAwardID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getMembershipAwardID() {
        return membershipAwardID;
    }

    /**
     * 设置membershipAwardID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setMembershipAwardID(UniqueID value) {
        this.membershipAwardID = value;
    }

    /**
     * 获取issueType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置issueType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueType(String value) {
        this.issueType = value;
    }

    /**
     * 获取issueSource属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueSource() {
        return issueSource;
    }

    /**
     * 设置issueSource属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueSource(String value) {
        this.issueSource = value;
    }

    /**
     * 获取awardPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAwardPoints() {
        return awardPoints;
    }

    /**
     * 设置awardPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAwardPoints(Double value) {
        this.awardPoints = value;
    }

    /**
     * 获取inActiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInActiveDate() {
        return inActiveDate;
    }

    /**
     * 设置inActiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInActiveDate(XMLGregorianCalendar value) {
        this.inActiveDate = value;
    }

    /**
     * 获取certificateBeginDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertificateBeginDate() {
        return certificateBeginDate;
    }

    /**
     * 设置certificateBeginDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertificateBeginDate(XMLGregorianCalendar value) {
        this.certificateBeginDate = value;
    }

    /**
     * 获取certificateEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCertificateEndDate() {
        return certificateEndDate;
    }

    /**
     * 设置certificateEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCertificateEndDate(XMLGregorianCalendar value) {
        this.certificateEndDate = value;
    }

    /**
     * 获取certificateExpriyMonths属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCertificateExpriyMonths() {
        return certificateExpriyMonths;
    }

    /**
     * 设置certificateExpriyMonths属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCertificateExpriyMonths(Integer value) {
        this.certificateExpriyMonths = value;
    }

    /**
     * 获取webConsumed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebConsumed() {
        return webConsumed;
    }

    /**
     * 设置webConsumed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebConsumed(String value) {
        this.webConsumed = value;
    }

    /**
     * 获取webAllowed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebAllowed() {
        return webAllowed;
    }

    /**
     * 设置webAllowed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebAllowed(String value) {
        this.webAllowed = value;
    }

    /**
     * 获取pmsConsumed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMSConsumed() {
        return pmsConsumed;
    }

    /**
     * 设置pmsConsumed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMSConsumed(String value) {
        this.pmsConsumed = value;
    }

    /**
     * 获取awards属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Award }
     *     
     */
    public Award getAwards() {
        return awards;
    }

    /**
     * 设置awards属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Award }
     *     
     */
    public void setAwards(Award value) {
        this.awards = value;
    }

    /**
     * Gets the value of the promotions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promotions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromotions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PromotionDetails }
     * 
     * 
     */
    public List<PromotionDetails> getPromotions() {
        if (promotions == null) {
            promotions = new ArrayList<PromotionDetails>();
        }
        return this.promotions;
    }

    /**
     * 获取scope属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置scope属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
    }

}
