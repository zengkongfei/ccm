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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Origin and Destination location, and time information for the request. Also includes the ability to specify a connection location for the search.
 * 
 * <p>Java class for OriginDestinationInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OriginDestinationInformationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}TravelDateTimeType">
 *       &lt;sequence>
 *         &lt;element name="OriginLocation">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>LocationType">
 *                 &lt;attribute name="MultiAirportCityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="AlternateLocationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DestinationLocation">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>LocationType">
 *                 &lt;attribute name="MultiAirportCityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="AlternateLocationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ConnectionLocations" type="{http://www.opentravel.org/OTA/2003/05}ConnectionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginDestinationInformationType", propOrder = {
    "originLocation",
    "destinationLocation",
    "connectionLocations"
})
@XmlSeeAlso({
    org.opentravel.ota._2003._05.OTAAirGetOfferRQ.RequestCriterion.OriginDestination.class,
    org.opentravel.ota._2003._05.AirPricedOfferType.OriginDestination.class,
    org.opentravel.ota._2003._05.OTAAirLowFareSearchRQ.OriginDestinationInformation.class,
    org.opentravel.ota._2003._05.OTAAirScheduleRQ.OriginDestinationInformation.class,
    org.opentravel.ota._2003._05.OTAAirFareDisplayRQ.OriginDestinationInformation.class,
    org.opentravel.ota._2003._05.OTADynamicPkgAvailRS.SearchResults.AirResults.OriginDestinationInformation.class,
    org.opentravel.ota._2003._05.OTAAirAvailRQ.OriginDestinationInformation.class,
    org.opentravel.ota._2003._05.OTAAirAvailRS.OriginDestinationInformation.class
})
public class OriginDestinationInformationType
    extends TravelDateTimeType
{

    @XmlElement(name = "OriginLocation", required = true)
    protected OriginDestinationInformationType.OriginLocation originLocation;
    @XmlElement(name = "DestinationLocation", required = true)
    protected OriginDestinationInformationType.DestinationLocation destinationLocation;
    @XmlElement(name = "ConnectionLocations")
    protected ConnectionType connectionLocations;

    /**
     * Gets the value of the originLocation property.
     * 
     * @return
     *     possible object is
     *     {@link OriginDestinationInformationType.OriginLocation }
     *     
     */
    public OriginDestinationInformationType.OriginLocation getOriginLocation() {
        return originLocation;
    }

    /**
     * Sets the value of the originLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginDestinationInformationType.OriginLocation }
     *     
     */
    public void setOriginLocation(OriginDestinationInformationType.OriginLocation value) {
        this.originLocation = value;
    }

    /**
     * Gets the value of the destinationLocation property.
     * 
     * @return
     *     possible object is
     *     {@link OriginDestinationInformationType.DestinationLocation }
     *     
     */
    public OriginDestinationInformationType.DestinationLocation getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * Sets the value of the destinationLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link OriginDestinationInformationType.DestinationLocation }
     *     
     */
    public void setDestinationLocation(OriginDestinationInformationType.DestinationLocation value) {
        this.destinationLocation = value;
    }

    /**
     * Gets the value of the connectionLocations property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionType }
     *     
     */
    public ConnectionType getConnectionLocations() {
        return connectionLocations;
    }

    /**
     * Sets the value of the connectionLocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionType }
     *     
     */
    public void setConnectionLocations(ConnectionType value) {
        this.connectionLocations = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>LocationType">
     *       &lt;attribute name="MultiAirportCityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="AlternateLocationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DestinationLocation
        extends LocationType
    {

        @XmlAttribute(name = "MultiAirportCityInd")
        protected Boolean multiAirportCityInd;
        @XmlAttribute(name = "AlternateLocationInd")
        protected Boolean alternateLocationInd;

        /**
         * Gets the value of the multiAirportCityInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isMultiAirportCityInd() {
            return multiAirportCityInd;
        }

        /**
         * Sets the value of the multiAirportCityInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setMultiAirportCityInd(Boolean value) {
            this.multiAirportCityInd = value;
        }

        /**
         * Gets the value of the alternateLocationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAlternateLocationInd() {
            return alternateLocationInd;
        }

        /**
         * Sets the value of the alternateLocationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAlternateLocationInd(Boolean value) {
            this.alternateLocationInd = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>LocationType">
     *       &lt;attribute name="MultiAirportCityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="AlternateLocationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class OriginLocation
        extends LocationType
    {

        @XmlAttribute(name = "MultiAirportCityInd")
        protected Boolean multiAirportCityInd;
        @XmlAttribute(name = "AlternateLocationInd")
        protected Boolean alternateLocationInd;

        /**
         * Gets the value of the multiAirportCityInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isMultiAirportCityInd() {
            return multiAirportCityInd;
        }

        /**
         * Sets the value of the multiAirportCityInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setMultiAirportCityInd(Boolean value) {
            this.multiAirportCityInd = value;
        }

        /**
         * Gets the value of the alternateLocationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAlternateLocationInd() {
            return alternateLocationInd;
        }

        /**
         * Sets the value of the alternateLocationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAlternateLocationInd(Boolean value) {
            this.alternateLocationInd = value;
        }

    }

}