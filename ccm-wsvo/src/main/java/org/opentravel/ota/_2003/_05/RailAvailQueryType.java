//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines criteria for a rail availability query.
 * 
 * <p>Java class for RailAvailQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RailAvailQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AvailBaseQueryCriteria" type="{http://www.opentravel.org/OTA/2003/05}RailAvailScheduleQueryType" maxOccurs="99"/>
 *         &lt;element name="PassengerType" type="{http://www.opentravel.org/OTA/2003/05}RailPassengerCategoryType" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="ReturnInfo" type="{http://www.opentravel.org/OTA/2003/05}TravelDateTimeType" minOccurs="0"/>
 *         &lt;element name="RailPrefs" type="{http://www.opentravel.org/OTA/2003/05}RailAvailPrefsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RailAvailQueryType", propOrder = {
    "availBaseQueryCriteria",
    "passengerType",
    "returnInfo",
    "railPrefs"
})
@XmlSeeAlso({
    org.opentravel.ota._2003._05.OTARailAvailRQ.RailAvailQuery.class
})
public class RailAvailQueryType {

    @XmlElement(name = "AvailBaseQueryCriteria", required = true)
    protected List<RailAvailScheduleQueryType> availBaseQueryCriteria;
    @XmlElement(name = "PassengerType")
    protected List<RailPassengerCategoryType> passengerType;
    @XmlElement(name = "ReturnInfo")
    protected TravelDateTimeType returnInfo;
    @XmlElement(name = "RailPrefs")
    protected RailAvailPrefsType railPrefs;

    /**
     * Gets the value of the availBaseQueryCriteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availBaseQueryCriteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailBaseQueryCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RailAvailScheduleQueryType }
     * 
     * 
     */
    public List<RailAvailScheduleQueryType> getAvailBaseQueryCriteria() {
        if (availBaseQueryCriteria == null) {
            availBaseQueryCriteria = new ArrayList<RailAvailScheduleQueryType>();
        }
        return this.availBaseQueryCriteria;
    }

    /**
     * Gets the value of the passengerType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the passengerType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPassengerType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RailPassengerCategoryType }
     * 
     * 
     */
    public List<RailPassengerCategoryType> getPassengerType() {
        if (passengerType == null) {
            passengerType = new ArrayList<RailPassengerCategoryType>();
        }
        return this.passengerType;
    }

    /**
     * Gets the value of the returnInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TravelDateTimeType }
     *     
     */
    public TravelDateTimeType getReturnInfo() {
        return returnInfo;
    }

    /**
     * Sets the value of the returnInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TravelDateTimeType }
     *     
     */
    public void setReturnInfo(TravelDateTimeType value) {
        this.returnInfo = value;
    }

    /**
     * Gets the value of the railPrefs property.
     * 
     * @return
     *     possible object is
     *     {@link RailAvailPrefsType }
     *     
     */
    public RailAvailPrefsType getRailPrefs() {
        return railPrefs;
    }

    /**
     * Sets the value of the railPrefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link RailAvailPrefsType }
     *     
     */
    public void setRailPrefs(RailAvailPrefsType value) {
        this.railPrefs = value;
    }

}
