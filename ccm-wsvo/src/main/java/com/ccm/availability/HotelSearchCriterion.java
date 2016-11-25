
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>HotelSearchCriterion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HotelSearchCriterion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelRef" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelSearchCriterion", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "hotelRef"
})
public class HotelSearchCriterion {

    @XmlElement(name = "HotelRef", required = true)
    protected HotelReference hotelRef;

    /**
     * 获取hotelRef属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelRef() {
        return hotelRef;
    }

    /**
     * 设置hotelRef属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelRef(HotelReference value) {
        this.hotelRef = value;
    }

}
