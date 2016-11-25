
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Tax complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Tax">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaxDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" type="{http://webservices.micros.com/og/4.3/HotelCommon/}FeeTaxType" />
 *       &lt;attribute name="otherType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tax", propOrder = {
    "taxDescription"
})
public class Tax {

    @XmlElement(name = "TaxDescription")
    protected Paragraph taxDescription;
    @XmlAttribute(name = "type")
    protected FeeTaxType type;
    @XmlAttribute(name = "otherType")
    protected String otherType;
    @XmlAttribute(name = "taxCode")
    protected String taxCode;
    @XmlAttribute(name = "amount")
    protected Double amount;
    @XmlAttribute(name = "currencyCode")
    protected String currencyCode;

    /**
     * 获取taxDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getTaxDescription() {
        return taxDescription;
    }

    /**
     * 设置taxDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setTaxDescription(Paragraph value) {
        this.taxDescription = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FeeTaxType }
     *     
     */
    public FeeTaxType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FeeTaxType }
     *     
     */
    public void setType(FeeTaxType value) {
        this.type = value;
    }

    /**
     * 获取otherType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherType() {
        return otherType;
    }

    /**
     * 设置otherType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherType(String value) {
        this.otherType = value;
    }

    /**
     * 获取taxCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * 设置taxCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
