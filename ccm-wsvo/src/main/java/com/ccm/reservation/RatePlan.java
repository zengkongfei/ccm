
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RatePlan complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RatePlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RatePlanDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RatePlanShortDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="Commission" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Commission" minOccurs="0"/>
 *         &lt;element name="AdditionalDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AdditionalDetailList" minOccurs="0"/>
 *         &lt;element name="CancellationDateTime" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CancelDateTime" minOccurs="0"/>
 *         &lt;element name="DepositRequired" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DepositRequirement" minOccurs="0"/>
 *         &lt;element name="GdsFlags" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsFlags" minOccurs="0"/>
 *         &lt;element name="GuaranteeDetails" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GuaranteeDetailList" minOccurs="0"/>
 *         &lt;element name="MealPlanCodes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *         &lt;element name="Packages" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElementList" minOccurs="0"/>
 *         &lt;element name="Discount" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Discount" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ratePlanCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="hold" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="commissionYn" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="mandatoryDeposit" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="suppressRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="awardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="redemRate" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="commissionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="asbRateCycle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hasPackage" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatePlan", propOrder = {
    "ratePlanDescription",
    "ratePlanShortDescription",
    "commission",
    "additionalDetails",
    "cancellationDateTime",
    "depositRequired",
    "gdsFlags",
    "guaranteeDetails",
    "mealPlanCodes",
    "packages",
    "discount"
})
@XmlSeeAlso({
    RatePlanRoomTypes.class
})
public class RatePlan {

    @XmlElement(name = "RatePlanDescription")
    protected Paragraph ratePlanDescription;
    @XmlElement(name = "RatePlanShortDescription")
    protected Paragraph ratePlanShortDescription;
    @XmlElement(name = "Commission")
    protected Commission commission;
    @XmlElement(name = "AdditionalDetails")
    protected AdditionalDetailList additionalDetails;
    @XmlElement(name = "CancellationDateTime")
    protected CancelDateTime cancellationDateTime;
    @XmlElement(name = "DepositRequired")
    protected DepositRequirement depositRequired;
    @XmlElement(name = "GdsFlags")
    protected GdsFlags gdsFlags;
    @XmlElement(name = "GuaranteeDetails")
    protected GuaranteeDetailList guaranteeDetails;
    @XmlElement(name = "MealPlanCodes")
    protected AmenityInfo mealPlanCodes;
    @XmlElement(name = "Packages")
    protected PackageElementList packages;
    @XmlElement(name = "Discount")
    protected Discount discount;
    @XmlAttribute(name = "ratePlanCategory")
    protected String ratePlanCategory;
    @XmlAttribute(name = "ratePlanCode", required = true)
    protected String ratePlanCode;
    @XmlAttribute(name = "promotionCode")
    protected String promotionCode;
    @XmlAttribute(name = "qualifyingIdType")
    protected String qualifyingIdType;
    @XmlAttribute(name = "qualifyingIdValue", required = true)
    protected String qualifyingIdValue;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "hold")
    protected Boolean hold;
    @XmlAttribute(name = "commissionYn")
    protected Boolean commissionYn;
    @XmlAttribute(name = "mandatoryDeposit")
    protected Boolean mandatoryDeposit;
    @XmlAttribute(name = "suppressRate")
    protected Boolean suppressRate;
    @XmlAttribute(name = "ratePlanName")
    protected String ratePlanName;
    @XmlAttribute(name = "awardType")
    protected String awardType;
    @XmlAttribute(name = "redemRate")
    protected Boolean redemRate;
    @XmlAttribute(name = "commissionCode")
    protected String commissionCode;
    @XmlAttribute(name = "asbRateCycle")
    protected String asbRateCycle;
    @XmlAttribute(name = "hasPackage")
    protected Boolean hasPackage;

    /**
     * 获取ratePlanDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRatePlanDescription() {
        return ratePlanDescription;
    }

    /**
     * 设置ratePlanDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRatePlanDescription(Paragraph value) {
        this.ratePlanDescription = value;
    }

    /**
     * 获取ratePlanShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRatePlanShortDescription() {
        return ratePlanShortDescription;
    }

    /**
     * 设置ratePlanShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRatePlanShortDescription(Paragraph value) {
        this.ratePlanShortDescription = value;
    }

    /**
     * 获取commission属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Commission }
     *     
     */
    public Commission getCommission() {
        return commission;
    }

    /**
     * 设置commission属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Commission }
     *     
     */
    public void setCommission(Commission value) {
        this.commission = value;
    }

    /**
     * 获取additionalDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDetailList }
     *     
     */
    public AdditionalDetailList getAdditionalDetails() {
        return additionalDetails;
    }

    /**
     * 设置additionalDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDetailList }
     *     
     */
    public void setAdditionalDetails(AdditionalDetailList value) {
        this.additionalDetails = value;
    }

    /**
     * 获取cancellationDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CancelDateTime }
     *     
     */
    public CancelDateTime getCancellationDateTime() {
        return cancellationDateTime;
    }

    /**
     * 设置cancellationDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CancelDateTime }
     *     
     */
    public void setCancellationDateTime(CancelDateTime value) {
        this.cancellationDateTime = value;
    }

    /**
     * 获取depositRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DepositRequirement }
     *     
     */
    public DepositRequirement getDepositRequired() {
        return depositRequired;
    }

    /**
     * 设置depositRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DepositRequirement }
     *     
     */
    public void setDepositRequired(DepositRequirement value) {
        this.depositRequired = value;
    }

    /**
     * 获取gdsFlags属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GdsFlags }
     *     
     */
    public GdsFlags getGdsFlags() {
        return gdsFlags;
    }

    /**
     * 设置gdsFlags属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GdsFlags }
     *     
     */
    public void setGdsFlags(GdsFlags value) {
        this.gdsFlags = value;
    }

    /**
     * 获取guaranteeDetails属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GuaranteeDetailList }
     *     
     */
    public GuaranteeDetailList getGuaranteeDetails() {
        return guaranteeDetails;
    }

    /**
     * 设置guaranteeDetails属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteeDetailList }
     *     
     */
    public void setGuaranteeDetails(GuaranteeDetailList value) {
        this.guaranteeDetails = value;
    }

    /**
     * 获取mealPlanCodes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AmenityInfo }
     *     
     */
    public AmenityInfo getMealPlanCodes() {
        return mealPlanCodes;
    }

    /**
     * 设置mealPlanCodes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityInfo }
     *     
     */
    public void setMealPlanCodes(AmenityInfo value) {
        this.mealPlanCodes = value;
    }

    /**
     * 获取packages属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageElementList }
     *     
     */
    public PackageElementList getPackages() {
        return packages;
    }

    /**
     * 设置packages属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageElementList }
     *     
     */
    public void setPackages(PackageElementList value) {
        this.packages = value;
    }

    /**
     * 获取discount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Discount }
     *     
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * 设置discount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Discount }
     *     
     */
    public void setDiscount(Discount value) {
        this.discount = value;
    }

    /**
     * 获取ratePlanCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCategory() {
        return ratePlanCategory;
    }

    /**
     * 设置ratePlanCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCategory(String value) {
        this.ratePlanCategory = value;
    }

    /**
     * 获取ratePlanCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    /**
     * 设置ratePlanCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanCode(String value) {
        this.ratePlanCode = value;
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
     * 获取qualifyingIdType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifyingIdType() {
        return qualifyingIdType;
    }

    /**
     * 设置qualifyingIdType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifyingIdType(String value) {
        this.qualifyingIdType = value;
    }

    /**
     * 获取qualifyingIdValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifyingIdValue() {
        return qualifyingIdValue;
    }

    /**
     * 设置qualifyingIdValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifyingIdValue(String value) {
        this.qualifyingIdValue = value;
    }

    /**
     * 获取effectiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置effectiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
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
     * 获取hold属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHold() {
        return hold;
    }

    /**
     * 设置hold属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHold(Boolean value) {
        this.hold = value;
    }

    /**
     * 获取commissionYn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommissionYn() {
        return commissionYn;
    }

    /**
     * 设置commissionYn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommissionYn(Boolean value) {
        this.commissionYn = value;
    }

    /**
     * 获取mandatoryDeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDeposit() {
        return mandatoryDeposit;
    }

    /**
     * 设置mandatoryDeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDeposit(Boolean value) {
        this.mandatoryDeposit = value;
    }

    /**
     * 获取suppressRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuppressRate() {
        return suppressRate;
    }

    /**
     * 设置suppressRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuppressRate(Boolean value) {
        this.suppressRate = value;
    }

    /**
     * 获取ratePlanName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanName() {
        return ratePlanName;
    }

    /**
     * 设置ratePlanName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanName(String value) {
        this.ratePlanName = value;
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
     * 获取redemRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRedemRate() {
        return redemRate;
    }

    /**
     * 设置redemRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRedemRate(Boolean value) {
        this.redemRate = value;
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

    /**
     * 获取asbRateCycle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsbRateCycle() {
        return asbRateCycle;
    }

    /**
     * 设置asbRateCycle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsbRateCycle(String value) {
        this.asbRateCycle = value;
    }

    /**
     * 获取hasPackage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasPackage() {
        return hasPackage;
    }

    /**
     * 设置hasPackage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasPackage(Boolean value) {
        this.hasPackage = value;
    }

}
