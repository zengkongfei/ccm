//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Pickup and drop off requirement information for the tour/activity.
 * 
 * <p>Java class for TourActivityTransRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourActivityTransRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="DateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
 *       &lt;attribute name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="OtherInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PersonQty" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="PickupInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DropoffInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourActivityTransRequestType")
@XmlSeeAlso({
    org.opentravel.ota._2003._05.LoyaltyTravelInfoType.TourActivityInfo.PickupDropoff.class,
    org.opentravel.ota._2003._05.OTATourActivityResRetrieveRS.Detail.PickupDropoff.class,
    org.opentravel.ota._2003._05.OTATourActivityModifyRQ.BookingInfo.PickupDropoff.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRS.ReservationDetails.PickupDropoff.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRQ.BookingInfo.PickupDropoff.class
})
public class TourActivityTransRequestType {

    @XmlAttribute(name = "DateTime")
    protected String dateTime;
    @XmlAttribute(name = "LocationName")
    protected String locationName;
    @XmlAttribute(name = "OtherInfo")
    protected String otherInfo;
    @XmlAttribute(name = "PersonQty")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger personQty;
    @XmlAttribute(name = "PickupInd")
    protected Boolean pickupInd;
    @XmlAttribute(name = "DropoffInd")
    protected Boolean dropoffInd;

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTime(String value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the otherInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * Sets the value of the otherInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherInfo(String value) {
        this.otherInfo = value;
    }

    /**
     * Gets the value of the personQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPersonQty() {
        return personQty;
    }

    /**
     * Sets the value of the personQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPersonQty(BigInteger value) {
        this.personQty = value;
    }

    /**
     * Gets the value of the pickupInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPickupInd() {
        return pickupInd;
    }

    /**
     * Sets the value of the pickupInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPickupInd(Boolean value) {
        this.pickupInd = value;
    }

    /**
     * Gets the value of the dropoffInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDropoffInd() {
        return dropoffInd;
    }

    /**
     * Sets the value of the dropoffInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDropoffInd(Boolean value) {
        this.dropoffInd = value;
    }

}