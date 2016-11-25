
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RateCodeInformation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RateCodeInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ratePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="corporateCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ratePlanStatusCode" type="{http://webservices.micros.com/og/4.3/Availability/}StatusCodeIndicator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateCodeInformation", namespace = "http://webservices.micros.com/og/4.3/Availability/")
public class RateCodeInformation {

    @XmlAttribute(name = "ratePlanCode")
    protected String ratePlanCode;
    @XmlAttribute(name = "ratePlanIndicator")
    protected String ratePlanIndicator;
    @XmlAttribute(name = "corporateCode")
    protected String corporateCode;
    @XmlAttribute(name = "ratePlanStatusCode")
    protected StatusCodeIndicator ratePlanStatusCode;

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
     * 获取ratePlanIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanIndicator() {
        return ratePlanIndicator;
    }

    /**
     * 设置ratePlanIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanIndicator(String value) {
        this.ratePlanIndicator = value;
    }

    /**
     * 获取corporateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorporateCode() {
        return corporateCode;
    }

    /**
     * 设置corporateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorporateCode(String value) {
        this.corporateCode = value;
    }

    /**
     * 获取ratePlanStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StatusCodeIndicator }
     *     
     */
    public StatusCodeIndicator getRatePlanStatusCode() {
        return ratePlanStatusCode;
    }

    /**
     * 设置ratePlanStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StatusCodeIndicator }
     *     
     */
    public void setRatePlanStatusCode(StatusCodeIndicator value) {
        this.ratePlanStatusCode = value;
    }

}
