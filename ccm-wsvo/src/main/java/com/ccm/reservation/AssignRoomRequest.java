
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
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ResvNameId" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *         &lt;element name="RoomNoRequested" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hotelReference",
    "resvNameId",
    "roomNoRequested",
    "stationID"
})
@XmlRootElement(name = "AssignRoomRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class AssignRoomRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "ResvNameId", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected UniqueID resvNameId;
    @XmlElement(name = "RoomNoRequested", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected String roomNoRequested;
    @XmlElement(name = "StationID", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected String stationID;

    /**
     * 获取hotelReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * 设置hotelReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * 获取resvNameId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getResvNameId() {
        return resvNameId;
    }

    /**
     * 设置resvNameId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setResvNameId(UniqueID value) {
        this.resvNameId = value;
    }

    /**
     * 获取roomNoRequested属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNoRequested() {
        return roomNoRequested;
    }

    /**
     * 设置roomNoRequested属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNoRequested(String value) {
        this.roomNoRequested = value;
    }

    /**
     * 获取stationID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationID() {
        return stationID;
    }

    /**
     * 设置stationID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationID(String value) {
        this.stationID = value;
    }

}
