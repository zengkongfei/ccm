//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.21 at 07:42:26 
//


package com.ccm.oxi.inventory_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TimeSpan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeSpan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numberOfTimeUnits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="timeUnitType" type="{inventory.fidelio.1.1}timeUnitType" default="DAY" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeSpan", propOrder = {
    "startTime",
    "numberOfTimeUnits"
})
public class TimeSpan {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    protected Integer numberOfTimeUnits;
    @XmlAttribute
    protected TimeUnitType timeUnitType;

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the numberOfTimeUnits property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfTimeUnits() {
        return numberOfTimeUnits;
    }

    /**
     * Sets the value of the numberOfTimeUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfTimeUnits(Integer value) {
        this.numberOfTimeUnits = value;
    }

    /**
     * Gets the value of the timeUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link TimeUnitType }
     *     
     */
    public TimeUnitType getTimeUnitType() {
        if (timeUnitType == null) {
            return TimeUnitType.DAY;
        } else {
            return timeUnitType;
        }
    }

    /**
     * Sets the value of the timeUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeUnitType }
     *     
     */
    public void setTimeUnitType(TimeUnitType value) {
        this.timeUnitType = value;
    }

}
