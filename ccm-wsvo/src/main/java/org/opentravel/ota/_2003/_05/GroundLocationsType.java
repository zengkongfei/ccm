//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Pickup, interim stops and drop-off details.
 * 
 * <p>Java class for GroundLocationsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroundLocationsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pickup" type="{http://www.opentravel.org/OTA/2003/05}GroundLocationType"/>
 *         &lt;element name="Stops" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Stop" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.opentravel.org/OTA/2003/05}GroundLocationType">
 *                           &lt;attribute name="Sequence" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Dropoff" type="{http://www.opentravel.org/OTA/2003/05}GroundLocationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroundLocationsType", propOrder = {
    "pickup",
    "stops",
    "dropoff"
})
public class GroundLocationsType {

    @XmlElement(name = "Pickup", required = true)
    protected GroundLocationType pickup;
    @XmlElement(name = "Stops")
    protected GroundLocationsType.Stops stops;
    @XmlElement(name = "Dropoff", required = true)
    protected GroundLocationType dropoff;

    /**
     * Gets the value of the pickup property.
     * 
     * @return
     *     possible object is
     *     {@link GroundLocationType }
     *     
     */
    public GroundLocationType getPickup() {
        return pickup;
    }

    /**
     * Sets the value of the pickup property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroundLocationType }
     *     
     */
    public void setPickup(GroundLocationType value) {
        this.pickup = value;
    }

    /**
     * Gets the value of the stops property.
     * 
     * @return
     *     possible object is
     *     {@link GroundLocationsType.Stops }
     *     
     */
    public GroundLocationsType.Stops getStops() {
        return stops;
    }

    /**
     * Sets the value of the stops property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroundLocationsType.Stops }
     *     
     */
    public void setStops(GroundLocationsType.Stops value) {
        this.stops = value;
    }

    /**
     * Gets the value of the dropoff property.
     * 
     * @return
     *     possible object is
     *     {@link GroundLocationType }
     *     
     */
    public GroundLocationType getDropoff() {
        return dropoff;
    }

    /**
     * Sets the value of the dropoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroundLocationType }
     *     
     */
    public void setDropoff(GroundLocationType value) {
        this.dropoff = value;
    }


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
     *         &lt;element name="Stop" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}GroundLocationType">
     *                 &lt;attribute name="Sequence" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "stop"
    })
    public static class Stops {

        @XmlElement(name = "Stop", required = true)
        protected List<GroundLocationsType.Stops.Stop> stop;

        /**
         * Gets the value of the stop property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stop property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStop().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroundLocationsType.Stops.Stop }
         * 
         * 
         */
        public List<GroundLocationsType.Stops.Stop> getStop() {
            if (stop == null) {
                stop = new ArrayList<GroundLocationsType.Stops.Stop>();
            }
            return this.stop;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}GroundLocationType">
         *       &lt;attribute name="Sequence" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Stop
            extends GroundLocationType
        {

            @XmlAttribute(name = "Sequence")
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger sequence;

            /**
             * Gets the value of the sequence property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getSequence() {
                return sequence;
            }

            /**
             * Sets the value of the sequence property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setSequence(BigInteger value) {
                this.sequence = value;
            }

        }

    }

}
