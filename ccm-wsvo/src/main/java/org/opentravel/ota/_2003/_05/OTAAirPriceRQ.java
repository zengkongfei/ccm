//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="POS" type="{http://www.opentravel.org/OTA/2003/05}POS_Type"/>
 *         &lt;element name="AirItinerary" type="{http://www.opentravel.org/OTA/2003/05}AirItineraryType" minOccurs="0"/>
 *         &lt;element name="TravelerInfoSummary">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}TravelerInfoSummaryType">
 *                 &lt;sequence>
 *                   &lt;element name="PaymentDetail" type="{http://www.opentravel.org/OTA/2003/05}PaymentDetailType" minOccurs="0"/>
 *                   &lt;element name="PricingPref" maxOccurs="20" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}PricingInfoGroup"/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BookingReferenceID" type="{http://www.opentravel.org/OTA/2003/05}UniqueID_Type" minOccurs="0"/>
 *         &lt;element name="FlightReference" maxOccurs="16" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}DateTimeSpanGroup"/>
 *                 &lt;attribute name="FlightRefNumber" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to64" />
 *                 &lt;attribute name="FareBasisCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
 *                 &lt;attribute name="OpenJawInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Offer" type="{http://www.opentravel.org/OTA/2003/05}AirOfferChoiceType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}OTA_PayloadStdAttributes"/>
 *       &lt;attribute name="Type" type="{http://www.opentravel.org/OTA/2003/05}AlphaNumericStringLength1to14" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "airItinerary",
    "travelerInfoSummary",
    "bookingReferenceID",
    "flightReference",
    "offer"
})
@XmlRootElement(name = "OTA_AirPriceRQ")
public class OTAAirPriceRQ {

    @XmlElement(name = "POS", required = true)
    protected POSType pos;
    @XmlElement(name = "AirItinerary")
    protected AirItineraryType airItinerary;
    @XmlElement(name = "TravelerInfoSummary", required = true)
    protected OTAAirPriceRQ.TravelerInfoSummary travelerInfoSummary;
    @XmlElement(name = "BookingReferenceID")
    protected UniqueIDType bookingReferenceID;
    @XmlElement(name = "FlightReference")
    protected List<OTAAirPriceRQ.FlightReference> flightReference;
    @XmlElement(name = "Offer")
    protected AirOfferChoiceType offer;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "EchoToken")
    protected String echoToken;
    @XmlAttribute(name = "TimeStamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlAttribute(name = "Target")
    protected String target;
    @XmlAttribute(name = "TargetName")
    protected String targetName;
    @XmlAttribute(name = "Version", required = true)
    protected BigDecimal version;
    @XmlAttribute(name = "TransactionIdentifier")
    protected String transactionIdentifier;
    @XmlAttribute(name = "SequenceNmbr")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger sequenceNmbr;
    @XmlAttribute(name = "TransactionStatusCode")
    protected String transactionStatusCode;
    @XmlAttribute(name = "RetransmissionIndicator")
    protected Boolean retransmissionIndicator;
    @XmlAttribute(name = "CorrelationID")
    protected String correlationID;
    @XmlAttribute(name = "PrimaryLangID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String primaryLangID;
    @XmlAttribute(name = "AltLangID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String altLangID;

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link POSType }
     *     
     */
    public POSType getPOS() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link POSType }
     *     
     */
    public void setPOS(POSType value) {
        this.pos = value;
    }

    /**
     * Gets the value of the airItinerary property.
     * 
     * @return
     *     possible object is
     *     {@link AirItineraryType }
     *     
     */
    public AirItineraryType getAirItinerary() {
        return airItinerary;
    }

    /**
     * Sets the value of the airItinerary property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirItineraryType }
     *     
     */
    public void setAirItinerary(AirItineraryType value) {
        this.airItinerary = value;
    }

    /**
     * Gets the value of the travelerInfoSummary property.
     * 
     * @return
     *     possible object is
     *     {@link OTAAirPriceRQ.TravelerInfoSummary }
     *     
     */
    public OTAAirPriceRQ.TravelerInfoSummary getTravelerInfoSummary() {
        return travelerInfoSummary;
    }

    /**
     * Sets the value of the travelerInfoSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link OTAAirPriceRQ.TravelerInfoSummary }
     *     
     */
    public void setTravelerInfoSummary(OTAAirPriceRQ.TravelerInfoSummary value) {
        this.travelerInfoSummary = value;
    }

    /**
     * Gets the value of the bookingReferenceID property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueIDType }
     *     
     */
    public UniqueIDType getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * Sets the value of the bookingReferenceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueIDType }
     *     
     */
    public void setBookingReferenceID(UniqueIDType value) {
        this.bookingReferenceID = value;
    }

    /**
     * Gets the value of the flightReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTAAirPriceRQ.FlightReference }
     * 
     * 
     */
    public List<OTAAirPriceRQ.FlightReference> getFlightReference() {
        if (flightReference == null) {
            flightReference = new ArrayList<OTAAirPriceRQ.FlightReference>();
        }
        return this.flightReference;
    }

    /**
     * Gets the value of the offer property.
     * 
     * @return
     *     possible object is
     *     {@link AirOfferChoiceType }
     *     
     */
    public AirOfferChoiceType getOffer() {
        return offer;
    }

    /**
     * Sets the value of the offer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AirOfferChoiceType }
     *     
     */
    public void setOffer(AirOfferChoiceType value) {
        this.offer = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the echoToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoToken() {
        return echoToken;
    }

    /**
     * Sets the value of the echoToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoToken(String value) {
        this.echoToken = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the targetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * Sets the value of the targetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetName(String value) {
        this.targetName = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

    /**
     * Gets the value of the transactionIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionIdentifier() {
        return transactionIdentifier;
    }

    /**
     * Sets the value of the transactionIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionIdentifier(String value) {
        this.transactionIdentifier = value;
    }

    /**
     * Gets the value of the sequenceNmbr property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequenceNmbr() {
        return sequenceNmbr;
    }

    /**
     * Sets the value of the sequenceNmbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequenceNmbr(BigInteger value) {
        this.sequenceNmbr = value;
    }

    /**
     * Gets the value of the transactionStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    /**
     * Sets the value of the transactionStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionStatusCode(String value) {
        this.transactionStatusCode = value;
    }

    /**
     * Gets the value of the retransmissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRetransmissionIndicator() {
        return retransmissionIndicator;
    }

    /**
     * Sets the value of the retransmissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetransmissionIndicator(Boolean value) {
        this.retransmissionIndicator = value;
    }

    /**
     * Gets the value of the correlationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * Sets the value of the correlationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * Gets the value of the primaryLangID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryLangID() {
        return primaryLangID;
    }

    /**
     * Sets the value of the primaryLangID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryLangID(String value) {
        this.primaryLangID = value;
    }

    /**
     * Gets the value of the altLangID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltLangID() {
        return altLangID;
    }

    /**
     * Sets the value of the altLangID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltLangID(String value) {
        this.altLangID = value;
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
     *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}DateTimeSpanGroup"/>
     *       &lt;attribute name="FlightRefNumber" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to64" />
     *       &lt;attribute name="FareBasisCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to16" />
     *       &lt;attribute name="OpenJawInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class FlightReference {

        @XmlAttribute(name = "FlightRefNumber")
        protected String flightRefNumber;
        @XmlAttribute(name = "FareBasisCode")
        protected String fareBasisCode;
        @XmlAttribute(name = "OpenJawInd")
        protected Boolean openJawInd;
        @XmlAttribute(name = "Start")
        protected String start;
        @XmlAttribute(name = "Duration")
        protected String duration;
        @XmlAttribute(name = "End")
        protected String end;

        /**
         * Gets the value of the flightRefNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFlightRefNumber() {
            return flightRefNumber;
        }

        /**
         * Sets the value of the flightRefNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFlightRefNumber(String value) {
            this.flightRefNumber = value;
        }

        /**
         * Gets the value of the fareBasisCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFareBasisCode() {
            return fareBasisCode;
        }

        /**
         * Sets the value of the fareBasisCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFareBasisCode(String value) {
            this.fareBasisCode = value;
        }

        /**
         * Gets the value of the openJawInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOpenJawInd() {
            return openJawInd;
        }

        /**
         * Sets the value of the openJawInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOpenJawInd(Boolean value) {
            this.openJawInd = value;
        }

        /**
         * Gets the value of the start property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStart() {
            return start;
        }

        /**
         * Sets the value of the start property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStart(String value) {
            this.start = value;
        }

        /**
         * Gets the value of the duration property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDuration() {
            return duration;
        }

        /**
         * Sets the value of the duration property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDuration(String value) {
            this.duration = value;
        }

        /**
         * Gets the value of the end property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEnd() {
            return end;
        }

        /**
         * Sets the value of the end property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEnd(String value) {
            this.end = value;
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
     *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}TravelerInfoSummaryType">
     *       &lt;sequence>
     *         &lt;element name="PaymentDetail" type="{http://www.opentravel.org/OTA/2003/05}PaymentDetailType" minOccurs="0"/>
     *         &lt;element name="PricingPref" maxOccurs="20" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}PricingInfoGroup"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "paymentDetail",
        "pricingPref"
    })
    public static class TravelerInfoSummary
        extends TravelerInfoSummaryType
    {

        @XmlElement(name = "PaymentDetail")
        protected PaymentDetailType paymentDetail;
        @XmlElement(name = "PricingPref")
        protected List<OTAAirPriceRQ.TravelerInfoSummary.PricingPref> pricingPref;

        /**
         * Gets the value of the paymentDetail property.
         * 
         * @return
         *     possible object is
         *     {@link PaymentDetailType }
         *     
         */
        public PaymentDetailType getPaymentDetail() {
            return paymentDetail;
        }

        /**
         * Sets the value of the paymentDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link PaymentDetailType }
         *     
         */
        public void setPaymentDetail(PaymentDetailType value) {
            this.paymentDetail = value;
        }

        /**
         * Gets the value of the pricingPref property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pricingPref property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPricingPref().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OTAAirPriceRQ.TravelerInfoSummary.PricingPref }
         * 
         * 
         */
        public List<OTAAirPriceRQ.TravelerInfoSummary.PricingPref> getPricingPref() {
            if (pricingPref == null) {
                pricingPref = new ArrayList<OTAAirPriceRQ.TravelerInfoSummary.PricingPref>();
            }
            return this.pricingPref;
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
         *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}PricingInfoGroup"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class PricingPref {

            @XmlAttribute(name = "Type", required = true)
            protected String type;
            @XmlAttribute(name = "ExcludeInd")
            protected Boolean excludeInd;
            @XmlAttribute(name = "Qualifier")
            protected String qualifier;

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the excludeInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isExcludeInd() {
                return excludeInd;
            }

            /**
             * Sets the value of the excludeInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setExcludeInd(Boolean value) {
                this.excludeInd = value;
            }

            /**
             * Gets the value of the qualifier property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getQualifier() {
                return qualifier;
            }

            /**
             * Sets the value of the qualifier property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setQualifier(String value) {
                this.qualifier = value;
            }

        }

    }

}