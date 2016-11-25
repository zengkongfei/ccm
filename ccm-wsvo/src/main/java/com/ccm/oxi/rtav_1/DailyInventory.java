//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.17 at 04:02:21 下午 CST 
//


package com.ccm.oxi.rtav_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DailyInventory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DailyInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomTypeInventories" type="{rtav.fidelio.2.0}RoomTypeInventories"/>
 *       &lt;/sequence>
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="houseOverbook" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailyInventory", propOrder = {
    "roomTypeInventories"
})
public class DailyInventory {

    @XmlElement(name = "RoomTypeInventories", required = true)
    protected RoomTypeInventories roomTypeInventories;
    @XmlAttribute
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAttribute
    protected Integer houseOverbook;

    /**
     * Gets the value of the roomTypeInventories property.
     * 
     * @return
     *     possible object is
     *     {@link RoomTypeInventories }
     *     
     */
    public RoomTypeInventories getRoomTypeInventories() {
        return roomTypeInventories;
    }

    /**
     * Sets the value of the roomTypeInventories property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypeInventories }
     *     
     */
    public void setRoomTypeInventories(RoomTypeInventories value) {
        this.roomTypeInventories = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the houseOverbook property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHouseOverbook() {
        return houseOverbook;
    }

    /**
     * Sets the value of the houseOverbook property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHouseOverbook(Integer value) {
        this.houseOverbook = value;
    }

}
