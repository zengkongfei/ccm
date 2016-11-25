
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RatePlanCandidate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RatePlanCandidate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MealPlanCodes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualifyingIdValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="awardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="commissionCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatePlanCandidate", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "mealPlanCodes"
})
public class RatePlanCandidate {

    @XmlElement(name = "MealPlanCodes")
    protected AmenityInfo mealPlanCodes;
    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "promotionCode")
    protected String promotionCode;
    @XmlAttribute(name = "qualifyingIdType")
    protected String qualifyingIdType;
    @XmlAttribute(name = "qualifyingIdValue")
    protected String qualifyingIdValue;
    @XmlAttribute(name = "awardType")
    protected String awardType;
    @XmlAttribute(name = "commissionCode")
    protected String commissionCode;

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
