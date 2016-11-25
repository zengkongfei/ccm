
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>RegionalSearchCode complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RegionalSearchCode">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="regionalSearchCodeType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RegionalSearchCodeType" />
 *       &lt;attribute name="otherRegionalSearchCodeType" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionalSearchCode", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "value"
})
public class RegionalSearchCode {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "regionalSearchCodeType", required = true)
    protected RegionalSearchCodeType regionalSearchCodeType;
    @XmlAttribute(name = "otherRegionalSearchCodeType")
    @XmlSchemaType(name = "anySimpleType")
    protected String otherRegionalSearchCodeType;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取regionalSearchCodeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegionalSearchCodeType }
     *     
     */
    public RegionalSearchCodeType getRegionalSearchCodeType() {
        return regionalSearchCodeType;
    }

    /**
     * 设置regionalSearchCodeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegionalSearchCodeType }
     *     
     */
    public void setRegionalSearchCodeType(RegionalSearchCodeType value) {
        this.regionalSearchCodeType = value;
    }

    /**
     * 获取otherRegionalSearchCodeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRegionalSearchCodeType() {
        return otherRegionalSearchCodeType;
    }

    /**
     * 设置otherRegionalSearchCodeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRegionalSearchCodeType(String value) {
        this.otherRegionalSearchCodeType = value;
    }

}
