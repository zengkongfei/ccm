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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information about baggage associated with a traveler for allowing the offer engine to determine baggage services.
 * 
 * <p>Java class for BaggageQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaggageQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AirlineCarrier" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>CompanyNameType">
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Code">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="OriginCityCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to8" />
 *       &lt;attribute name="OriginCodeContext" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to32" />
 *       &lt;attribute name="DestinationCityCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to8" />
 *       &lt;attribute name="DestinationCodeContext" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to32" />
 *       &lt;attribute name="TravelerRPH" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
 *       &lt;attribute name="ItinerarySegmentRPH" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaggageQueryType", propOrder = {
    "airlineCarrier"
})
public class BaggageQueryType {

    @XmlElement(name = "AirlineCarrier")
    protected BaggageQueryType.AirlineCarrier airlineCarrier;
    @XmlAttribute(name = "Code")
    protected String code;
    @XmlAttribute(name = "OriginCityCode")
    protected String originCityCode;
    @XmlAttribute(name = "OriginCodeContext")
    protected String originCodeContext;
    @XmlAttribute(name = "DestinationCityCode")
    protected String destinationCityCode;
    @XmlAttribute(name = "DestinationCodeContext")
    protected String destinationCodeContext;
    @XmlAttribute(name = "TravelerRPH")
    protected String travelerRPH;
    @XmlAttribute(name = "ItinerarySegmentRPH")
    protected String itinerarySegmentRPH;

    /**
     * Gets the value of the airlineCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link BaggageQueryType.AirlineCarrier }
     *     
     */
    public BaggageQueryType.AirlineCarrier getAirlineCarrier() {
        return airlineCarrier;
    }

    /**
     * Sets the value of the airlineCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaggageQueryType.AirlineCarrier }
     *     
     */
    public void setAirlineCarrier(BaggageQueryType.AirlineCarrier value) {
        this.airlineCarrier = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the originCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCityCode() {
        return originCityCode;
    }

    /**
     * Sets the value of the originCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCityCode(String value) {
        this.originCityCode = value;
    }

    /**
     * Gets the value of the originCodeContext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginCodeContext() {
        return originCodeContext;
    }

    /**
     * Sets the value of the originCodeContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginCodeContext(String value) {
        this.originCodeContext = value;
    }

    /**
     * Gets the value of the destinationCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationCityCode() {
        return destinationCityCode;
    }

    /**
     * Sets the value of the destinationCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationCityCode(String value) {
        this.destinationCityCode = value;
    }

    /**
     * Gets the value of the destinationCodeContext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationCodeContext() {
        return destinationCodeContext;
    }

    /**
     * Sets the value of the destinationCodeContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationCodeContext(String value) {
        this.destinationCodeContext = value;
    }

    /**
     * Gets the value of the travelerRPH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelerRPH() {
        return travelerRPH;
    }

    /**
     * Sets the value of the travelerRPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelerRPH(String value) {
        this.travelerRPH = value;
    }

    /**
     * Gets the value of the itinerarySegmentRPH property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItinerarySegmentRPH() {
        return itinerarySegmentRPH;
    }

    /**
     * Sets the value of the itinerarySegmentRPH property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItinerarySegmentRPH(String value) {
        this.itinerarySegmentRPH = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>CompanyNameType">
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class AirlineCarrier
        extends CompanyNameType
    {


    }

}
