//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Participant lodging information if required by a tour supplier/ operator.
 * 
 * <p>Java class for TourActivityLodgingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourActivityLodgingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="HotelName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HotelLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourActivityLodgingType")
@XmlSeeAlso({
    org.opentravel.ota._2003._05.OTATourActivityResRetrieveRS.Detail.ParticipantInfo.LodgingInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityModifyRQ.BookingInfo.ParticipantInfo.LodgingInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRS.ReservationDetails.ParticipantInfo.LodgingInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRQ.BookingInfo.ParticipantInfo.LodgingInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityCancelRS.Reservation.ReservationInfo.ParticipantInfo.LodgingInfo.class
})
public class TourActivityLodgingType {

    @XmlAttribute(name = "HotelName")
    protected String hotelName;
    @XmlAttribute(name = "HotelLocation")
    protected String hotelLocation;
    @XmlAttribute(name = "RoomNumber")
    protected String roomNumber;

    /**
     * Gets the value of the hotelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Sets the value of the hotelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelName(String value) {
        this.hotelName = value;
    }

    /**
     * Gets the value of the hotelLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelLocation() {
        return hotelLocation;
    }

    /**
     * Sets the value of the hotelLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelLocation(String value) {
        this.hotelLocation = value;
    }

    /**
     * Gets the value of the roomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the value of the roomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

}