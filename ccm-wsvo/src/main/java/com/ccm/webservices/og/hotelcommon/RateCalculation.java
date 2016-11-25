
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RateCalculation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RateCalculation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="rateCategory" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateChangeMethod" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="calculatePercentagesIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="taxApplicationIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateCalculation")
public class RateCalculation {

    @XmlAttribute(name = "rateCategory")
    protected String rateCategory;
    @XmlAttribute(name = "rateChangeIndicator")
    protected String rateChangeIndicator;
    @XmlAttribute(name = "rateChangeMethod")
    protected String rateChangeMethod;
    @XmlAttribute(name = "calculatePercentagesIndicator")
    protected String calculatePercentagesIndicator;
    @XmlAttribute(name = "taxApplicationIndicator")
    protected String taxApplicationIndicator;

    /**
     * 获取rateCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCategory() {
        return rateCategory;
    }

    /**
     * 设置rateCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCategory(String value) {
        this.rateCategory = value;
    }

    /**
     * 获取rateChangeIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChangeIndicator() {
        return rateChangeIndicator;
    }

    /**
     * 设置rateChangeIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChangeIndicator(String value) {
        this.rateChangeIndicator = value;
    }

    /**
     * 获取rateChangeMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateChangeMethod() {
        return rateChangeMethod;
    }

    /**
     * 设置rateChangeMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateChangeMethod(String value) {
        this.rateChangeMethod = value;
    }

    /**
     * 获取calculatePercentagesIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalculatePercentagesIndicator() {
        return calculatePercentagesIndicator;
    }

    /**
     * 设置calculatePercentagesIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalculatePercentagesIndicator(String value) {
        this.calculatePercentagesIndicator = value;
    }

    /**
     * 获取taxApplicationIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxApplicationIndicator() {
        return taxApplicationIndicator;
    }

    /**
     * 设置taxApplicationIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxApplicationIndicator(String value) {
        this.taxApplicationIndicator = value;
    }

}
