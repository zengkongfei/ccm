
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GeoPosition complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GeoPosition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Radius" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDistance"/>
 *         &lt;element name="Position" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GeoCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeoPosition", propOrder = {
    "radius",
    "position"
})
public class GeoPosition {

    @XmlElement(name = "Radius", required = true)
    protected VectorDistance radius;
    @XmlElement(name = "Position", required = true)
    protected GeoCode position;

    /**
     * 获取radius属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VectorDistance }
     *     
     */
    public VectorDistance getRadius() {
        return radius;
    }

    /**
     * 设置radius属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDistance }
     *     
     */
    public void setRadius(VectorDistance value) {
        this.radius = value;
    }

    /**
     * 获取position属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCode }
     *     
     */
    public GeoCode getPosition() {
        return position;
    }

    /**
     * 设置position属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCode }
     *     
     */
    public void setPosition(GeoCode value) {
        this.position = value;
    }

}
