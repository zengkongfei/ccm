
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PropertyStatusCode complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PropertyStatusCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PropertyLevelError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="propertyStatusCode" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PropertyStatusCodeType" />
 *       &lt;attribute name="otherPropertyStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyStatusCode", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "propertyLevelError"
})
public class PropertyStatusCode {

    @XmlElement(name = "PropertyLevelError")
    protected String propertyLevelError;
    @XmlAttribute(name = "propertyStatusCode", required = true)
    protected PropertyStatusCodeType propertyStatusCode;
    @XmlAttribute(name = "otherPropertyStatusCode")
    protected String otherPropertyStatusCode;

    /**
     * 获取propertyLevelError属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyLevelError() {
        return propertyLevelError;
    }

    /**
     * 设置propertyLevelError属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyLevelError(String value) {
        this.propertyLevelError = value;
    }

    /**
     * 获取propertyStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PropertyStatusCodeType }
     *     
     */
    public PropertyStatusCodeType getPropertyStatusCode() {
        return propertyStatusCode;
    }

    /**
     * 设置propertyStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyStatusCodeType }
     *     
     */
    public void setPropertyStatusCode(PropertyStatusCodeType value) {
        this.propertyStatusCode = value;
    }

    /**
     * 获取otherPropertyStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherPropertyStatusCode() {
        return otherPropertyStatusCode;
    }

    /**
     * 设置otherPropertyStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherPropertyStatusCode(String value) {
        this.otherPropertyStatusCode = value;
    }

}
