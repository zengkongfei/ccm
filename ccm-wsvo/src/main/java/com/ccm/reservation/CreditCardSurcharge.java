
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CreditCardSurcharge complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CreditCardSurcharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SurchargeThreshold" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="SurchargeAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *         &lt;element name="TaxAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *         &lt;element name="TotalBillAmount" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CreditCardType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SurchargePercentage" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardSurcharge", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "surchargeThreshold",
    "surchargeAmount",
    "taxAmount",
    "totalBillAmount"
})
public class CreditCardSurcharge {

    @XmlElement(name = "SurchargeThreshold")
    protected Amount surchargeThreshold;
    @XmlElement(name = "SurchargeAmount", required = true)
    protected Amount surchargeAmount;
    @XmlElement(name = "TaxAmount")
    protected Amount taxAmount;
    @XmlElement(name = "TotalBillAmount", required = true)
    protected Amount totalBillAmount;
    @XmlAttribute(name = "CreditCardType", required = true)
    protected String creditCardType;
    @XmlAttribute(name = "SurchargePercentage")
    protected Float surchargePercentage;

    /**
     * 获取surchargeThreshold属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getSurchargeThreshold() {
        return surchargeThreshold;
    }

    /**
     * 设置surchargeThreshold属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setSurchargeThreshold(Amount value) {
        this.surchargeThreshold = value;
    }

    /**
     * 获取surchargeAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getSurchargeAmount() {
        return surchargeAmount;
    }

    /**
     * 设置surchargeAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setSurchargeAmount(Amount value) {
        this.surchargeAmount = value;
    }

    /**
     * 获取taxAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTaxAmount() {
        return taxAmount;
    }

    /**
     * 设置taxAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTaxAmount(Amount value) {
        this.taxAmount = value;
    }

    /**
     * 获取totalBillAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalBillAmount() {
        return totalBillAmount;
    }

    /**
     * 设置totalBillAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalBillAmount(Amount value) {
        this.totalBillAmount = value;
    }

    /**
     * 获取creditCardType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * 设置creditCardType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardType(String value) {
        this.creditCardType = value;
    }

    /**
     * 获取surchargePercentage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getSurchargePercentage() {
        return surchargePercentage;
    }

    /**
     * 设置surchargePercentage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setSurchargePercentage(Float value) {
        this.surchargePercentage = value;
    }

}
