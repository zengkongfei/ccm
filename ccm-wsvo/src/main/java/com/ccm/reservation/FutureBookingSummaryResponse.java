
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
 *         &lt;element name="HotelReservations" type="{http://webservices.micros.com/og/4.3/Reservation/}HotelReservationList" minOccurs="0"/>
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
    "hotelReservations"
})
@XmlRootElement(name = "FutureBookingSummaryResponse", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class FutureBookingSummaryResponse {

    @XmlElement(name = "Result", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "HotelReservations", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected HotelReservationList hotelReservations;

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
     * 获取hotelReservations属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReservationList }
     *     
     */
    public HotelReservationList getHotelReservations() {
        return hotelReservations;
    }

    /**
     * 设置hotelReservations属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReservationList }
     *     
     */
    public void setHotelReservations(HotelReservationList value) {
        this.hotelReservations = value;
    }

}
