//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.06 at 12:42:44 pm CST 
//


package com.ccm.api.model.availability.res;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://webservices.micros.com/og/4.3/HotelCommon/}RatePlans"/>
 *         &lt;element ref="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomTypes"/>
 *         &lt;element ref="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomRates"/>
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
    "ratePlans",
    "roomTypes",
    "roomRates"
})
@XmlRootElement(name = "RoomStay")
public class RoomStay {

    @XmlElement(name = "RatePlans", required = true)
    protected RatePlans ratePlans;
    @XmlElement(name = "RoomTypes", required = true)
    protected RoomTypes roomTypes;
    @XmlElement(name = "RoomRates", required = true)
    protected RoomRates roomRates;

    /**
     * Gets the value of the ratePlans property.
     * 
     * @return
     *     possible object is
     *     {@link RatePlans }
     *     
     */
    public RatePlans getRatePlans() {
        return ratePlans;
    }

    /**
     * Sets the value of the ratePlans property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatePlans }
     *     
     */
    public void setRatePlans(RatePlans value) {
        this.ratePlans = value;
    }

    /**
     * Gets the value of the roomTypes property.
     * 
     * @return
     *     possible object is
     *     {@link RoomTypes }
     *     
     */
    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    /**
     * Sets the value of the roomTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypes }
     *     
     */
    public void setRoomTypes(RoomTypes value) {
        this.roomTypes = value;
    }

    /**
     * Gets the value of the roomRates property.
     * 
     * @return
     *     possible object is
     *     {@link RoomRates }
     *     
     */
    public RoomRates getRoomRates() {
        return roomRates;
    }

    /**
     * Sets the value of the roomRates property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomRates }
     *     
     */
    public void setRoomRates(RoomRates value) {
        this.roomRates = value;
    }

}