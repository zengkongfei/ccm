
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.Amount;


/**
 * <p>GdsTotalPricing complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPricing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalInclusiveRoomRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalExclusiveRoomRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalTax" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalSurCharge" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TaxQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchingQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPricing", propOrder = {
    "totalInclusiveRoomRate",
    "totalExclusiveRoomRate",
    "totalTax",
    "totalSurCharge",
    "taxQualifier",
    "matchingQualifier"
})
public class GdsTotalPricing {

    @XmlElement(name = "TotalInclusiveRoomRate")
    protected Amount totalInclusiveRoomRate;
    @XmlElement(name = "TotalExclusiveRoomRate")
    protected Amount totalExclusiveRoomRate;
    @XmlElement(name = "TotalTax")
    protected Amount totalTax;
    @XmlElement(name = "TotalSurCharge")
    protected Amount totalSurCharge;
    @XmlElement(name = "TaxQualifier")
    protected String taxQualifier;
    @XmlElement(name = "MatchingQualifier")
    protected String matchingQualifier;

    /**
     * 获取totalInclusiveRoomRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalInclusiveRoomRate() {
        return totalInclusiveRoomRate;
    }

    /**
     * 设置totalInclusiveRoomRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalInclusiveRoomRate(Amount value) {
        this.totalInclusiveRoomRate = value;
    }

    /**
     * 获取totalExclusiveRoomRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalExclusiveRoomRate() {
        return totalExclusiveRoomRate;
    }

    /**
     * 设置totalExclusiveRoomRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalExclusiveRoomRate(Amount value) {
        this.totalExclusiveRoomRate = value;
    }

    /**
     * 获取totalTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalTax() {
        return totalTax;
    }

    /**
     * 设置totalTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalTax(Amount value) {
        this.totalTax = value;
    }

    /**
     * 获取totalSurCharge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalSurCharge() {
        return totalSurCharge;
    }

    /**
     * 设置totalSurCharge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalSurCharge(Amount value) {
        this.totalSurCharge = value;
    }

    /**
     * 获取taxQualifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxQualifier() {
        return taxQualifier;
    }

    /**
     * 设置taxQualifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxQualifier(String value) {
        this.taxQualifier = value;
    }

    /**
     * 获取matchingQualifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchingQualifier() {
        return matchingQualifier;
    }

    /**
     * 设置matchingQualifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingQualifier(String value) {
        this.matchingQualifier = value;
    }

}
