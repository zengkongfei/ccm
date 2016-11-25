
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="UpsellRooms" type="{http://webservices.micros.com/og/4.3/Reservation/}UpsellRoomList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result",
    "upsellRooms"
})
@XmlRootElement(name = "FetchAvailableOffersResponse", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class FetchAvailableOffersResponse {

    @XmlElement(name = "Result", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "UpsellRooms", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected UpsellRoomList upsellRooms;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * 获取upsellRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UpsellRoomList }
     *     
     */
    public UpsellRoomList getUpsellRooms() {
        return upsellRooms;
    }

    /**
     * 设置upsellRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UpsellRoomList }
     *     
     */
    public void setUpsellRooms(UpsellRoomList value) {
        this.upsellRooms = value;
    }

}
