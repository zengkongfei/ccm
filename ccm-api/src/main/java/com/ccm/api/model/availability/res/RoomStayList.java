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
 *         &lt;element ref="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomStay"/>
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
    "roomStay"
})
@XmlRootElement(name = "RoomStayList", namespace = "http://webservices.micros.com/og/4.3/Availability/")
public class RoomStayList {

    @XmlElement(name = "RoomStay", required = true)
    protected RoomStay roomStay;

    /**
     * Gets the value of the roomStay property.
     * 
     * @return
     *     possible object is
     *     {@link RoomStay }
     *     
     */
    public RoomStay getRoomStay() {
        return roomStay;
    }

    /**
     * Sets the value of the roomStay property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomStay }
     *     
     */
    public void setRoomStay(RoomStay value) {
        this.roomStay = value;
    }

}