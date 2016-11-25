
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>VectorDistance complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="VectorDistance">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>double">
 *       &lt;attribute name="distanceUnit" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}DistanceUnitType" />
 *       &lt;attribute name="otherDistanceUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VectorDistance", propOrder = {
    "value"
})
public class VectorDistance {

    @XmlValue
    protected double value;
    @XmlAttribute(name = "distanceUnit", required = true)
    protected DistanceUnitType distanceUnit;
    @XmlAttribute(name = "otherDistanceUnit")
    protected String otherDistanceUnit;

    /**
     * 获取value属性的值。
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * 获取distanceUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DistanceUnitType }
     *     
     */
    public DistanceUnitType getDistanceUnit() {
        return distanceUnit;
    }

    /**
     * 设置distanceUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DistanceUnitType }
     *     
     */
    public void setDistanceUnit(DistanceUnitType value) {
        this.distanceUnit = value;
    }

    /**
     * 获取otherDistanceUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherDistanceUnit() {
        return otherDistanceUnit;
    }

    /**
     * 设置otherDistanceUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherDistanceUnit(String value) {
        this.otherDistanceUnit = value;
    }

}
