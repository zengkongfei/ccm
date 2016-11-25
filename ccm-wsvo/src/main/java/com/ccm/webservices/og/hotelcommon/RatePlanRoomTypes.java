
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RatePlanRoomTypes complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RatePlanRoomTypes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/HotelCommon/}RatePlan">
 *       &lt;sequence>
 *         &lt;element name="RoomTypes" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomTypeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RatePlanRoomTypes", propOrder = {
    "roomTypes"
})
public class RatePlanRoomTypes
    extends RatePlan
{

    @XmlElement(name = "RoomTypes")
    protected RoomTypeList roomTypes;

    /**
     * 获取roomTypes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomTypeList }
     *     
     */
    public RoomTypeList getRoomTypes() {
        return roomTypes;
    }

    /**
     * 设置roomTypes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypeList }
     *     
     */
    public void setRoomTypes(RoomTypeList value) {
        this.roomTypes = value;
    }

}
