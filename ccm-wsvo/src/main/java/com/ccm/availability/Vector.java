
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Measure of distance, direction and scale from one location to another.
 * 
 * <p>Vector complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Vector">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Direction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDirection" minOccurs="0"/>
 *         &lt;element name="Distance" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDistance" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vector", propOrder = {
    "direction",
    "distance"
})
public class Vector {

    @XmlElement(name = "Direction")
    protected VectorDirection direction;
    @XmlElement(name = "Distance")
    protected VectorDistance distance;

    /**
     * 获取direction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VectorDirection }
     *     
     */
    public VectorDirection getDirection() {
        return direction;
    }

    /**
     * 设置direction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDirection }
     *     
     */
    public void setDirection(VectorDirection value) {
        this.direction = value;
    }

    /**
     * 获取distance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VectorDistance }
     *     
     */
    public VectorDistance getDistance() {
        return distance;
    }

    /**
     * 设置distance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDistance }
     *     
     */
    public void setDistance(VectorDistance value) {
        this.distance = value;
    }

}
