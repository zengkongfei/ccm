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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Summary information about one or more ground service reservations.
 * 
 * <p>Java class for GroundReservationSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroundReservationSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Confirmation" type="{http://www.opentravel.org/OTA/2003/05}UniqueID_Type" minOccurs="0"/>
 *         &lt;element name="Contact" type="{http://www.opentravel.org/OTA/2003/05}ContactPersonType" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="Service" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="ServiceType" type="{http://www.opentravel.org/OTA/2003/05}GroundServiceDetailType" minOccurs="0"/>
 *                     &lt;element name="Location" type="{http://www.opentravel.org/OTA/2003/05}GroundLocationsType" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="Shuttle" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="ServiceLocation" maxOccurs="unbounded" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;extension base="{http://www.opentravel.org/OTA/2003/05}GroundLocationType">
 *                             &lt;attribute name="OriginInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                             &lt;attribute name="DestinationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;/extension>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;element name="Comments" type="{http://www.opentravel.org/OTA/2003/05}FreeTextType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroundReservationSummaryType", propOrder = {
    "confirmation",
    "contact",
    "service",
    "shuttle",
    "comments"
})
public class GroundReservationSummaryType {

    @XmlElement(name = "Confirmation")
    protected UniqueIDType confirmation;
    @XmlElement(name = "Contact")
    protected ContactPersonType contact;
    @XmlElement(name = "Service")
    protected GroundReservationSummaryType.Service service;
    @XmlElement(name = "Shuttle")
    protected GroundReservationSummaryType.Shuttle shuttle;
    @XmlElement(name = "Comments")
    protected List<FreeTextType> comments;

    /**
     * Gets the value of the confirmation property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueIDType }
     *     
     */
    public UniqueIDType getConfirmation() {
        return confirmation;
    }

    /**
     * Sets the value of the confirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueIDType }
     *     
     */
    public void setConfirmation(UniqueIDType value) {
        this.confirmation = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactPersonType }
     *     
     */
    public ContactPersonType getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactPersonType }
     *     
     */
    public void setContact(ContactPersonType value) {
        this.contact = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link GroundReservationSummaryType.Service }
     *     
     */
    public GroundReservationSummaryType.Service getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroundReservationSummaryType.Service }
     *     
     */
    public void setService(GroundReservationSummaryType.Service value) {
        this.service = value;
    }

    /**
     * Gets the value of the shuttle property.
     * 
     * @return
     *     possible object is
     *     {@link GroundReservationSummaryType.Shuttle }
     *     
     */
    public GroundReservationSummaryType.Shuttle getShuttle() {
        return shuttle;
    }

    /**
     * Sets the value of the shuttle property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroundReservationSummaryType.Shuttle }
     *     
     */
    public void setShuttle(GroundReservationSummaryType.Shuttle value) {
        this.shuttle = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FreeTextType }
     * 
     * 
     */
    public List<FreeTextType> getComments() {
        if (comments == null) {
            comments = new ArrayList<FreeTextType>();
        }
        return this.comments;
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
     *         &lt;element name="ServiceType" type="{http://www.opentravel.org/OTA/2003/05}GroundServiceDetailType" minOccurs="0"/>
     *         &lt;element name="Location" type="{http://www.opentravel.org/OTA/2003/05}GroundLocationsType" minOccurs="0"/>
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
        "serviceType",
        "location"
    })
    public static class Service {

        @XmlElement(name = "ServiceType")
        protected GroundServiceDetailType serviceType;
        @XmlElement(name = "Location")
        protected GroundLocationsType location;

        /**
         * Gets the value of the serviceType property.
         * 
         * @return
         *     possible object is
         *     {@link GroundServiceDetailType }
         *     
         */
        public GroundServiceDetailType getServiceType() {
            return serviceType;
        }

        /**
         * Sets the value of the serviceType property.
         * 
         * @param value
         *     allowed object is
         *     {@link GroundServiceDetailType }
         *     
         */
        public void setServiceType(GroundServiceDetailType value) {
            this.serviceType = value;
        }

        /**
         * Gets the value of the location property.
         * 
         * @return
         *     possible object is
         *     {@link GroundLocationsType }
         *     
         */
        public GroundLocationsType getLocation() {
            return location;
        }

        /**
         * Sets the value of the location property.
         * 
         * @param value
         *     allowed object is
         *     {@link GroundLocationsType }
         *     
         */
        public void setLocation(GroundLocationsType value) {
            this.location = value;
        }

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
     *         &lt;element name="ServiceLocation" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}GroundLocationType">
     *                 &lt;attribute name="OriginInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="DestinationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
        "serviceLocation"
    })
    public static class Shuttle {

        @XmlElement(name = "ServiceLocation")
        protected List<GroundReservationSummaryType.Shuttle.ServiceLocation> serviceLocation;

        /**
         * Gets the value of the serviceLocation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceLocation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceLocation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroundReservationSummaryType.Shuttle.ServiceLocation }
         * 
         * 
         */
        public List<GroundReservationSummaryType.Shuttle.ServiceLocation> getServiceLocation() {
            if (serviceLocation == null) {
                serviceLocation = new ArrayList<GroundReservationSummaryType.Shuttle.ServiceLocation>();
            }
            return this.serviceLocation;
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
         *       &lt;attribute name="OriginInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="DestinationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ServiceLocation
            extends GroundLocationType
        {

            @XmlAttribute(name = "OriginInd")
            protected Boolean originInd;
            @XmlAttribute(name = "DestinationInd")
            protected Boolean destinationInd;

            /**
             * Gets the value of the originInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isOriginInd() {
                return originInd;
            }

            /**
             * Sets the value of the originInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setOriginInd(Boolean value) {
                this.originInd = value;
            }

            /**
             * Gets the value of the destinationInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDestinationInd() {
                return destinationInd;
            }

            /**
             * Sets the value of the destinationInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDestinationInd(Boolean value) {
                this.destinationInd = value;
            }

        }

    }

}
