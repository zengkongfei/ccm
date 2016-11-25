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
 *         &lt;element name="POS" type="{http://www.opentravel.org/OTA/2003/05}POS_Type" minOccurs="0"/>
 *         &lt;element name="Location">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.opentravel.org/OTA/2003/05>LocationType">
 *                 &lt;attribute name="ExtendedLocationCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to8" />
 *                 &lt;attribute name="CounterLocation" type="{http://www.opentravel.org/OTA/2003/05}OTA_CodeType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Vendor" type="{http://www.opentravel.org/OTA/2003/05}CompanyNameType" minOccurs="0"/>
 *         &lt;element name="ResponseFilter" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="LocationAddressPhoneIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="LocationInfoIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="VehiclesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="RequirementsIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="AdditionalFeesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="LiabilitiesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="ServicesOfferedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Date" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="PickUpDateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
 *                 &lt;attribute name="ReturnDateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Keyword" maxOccurs="25" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Code" type="{http://www.opentravel.org/OTA/2003/05}AlphaNumericStringLength1to8" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}OTA_PayloadStdAttributes"/>
 *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}ReqRespVersion"/>
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
    "location",
    "vendor",
    "responseFilter",
    "date",
    "keyword",
    "tpaExtensions"
})
@XmlRootElement(name = "OTA_VehLocDetailRQ")
public class OTAVehLocDetailRQ {

    @XmlElement(name = "POS")
    protected POSType pos;
    @XmlElement(name = "Location", required = true)
    protected OTAVehLocDetailRQ.Location location;
    @XmlElement(name = "Vendor")
    protected CompanyNameType vendor;
    @XmlElement(name = "ResponseFilter")
    protected OTAVehLocDetailRQ.ResponseFilter responseFilter;
    @XmlElement(name = "Date")
    protected OTAVehLocDetailRQ.Date date;
    @XmlElement(name = "Keyword")
    protected List<OTAVehLocDetailRQ.Keyword> keyword;
    @XmlElement(name = "TPA_Extensions")
    protected TPAExtensionsType tpaExtensions;
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
    @XmlAttribute(name = "ReqRespVersion")
    protected String reqRespVersion;

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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link OTAVehLocDetailRQ.Location }
     *     
     */
    public OTAVehLocDetailRQ.Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link OTAVehLocDetailRQ.Location }
     *     
     */
    public void setLocation(OTAVehLocDetailRQ.Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link CompanyNameType }
     *     
     */
    public CompanyNameType getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyNameType }
     *     
     */
    public void setVendor(CompanyNameType value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the responseFilter property.
     * 
     * @return
     *     possible object is
     *     {@link OTAVehLocDetailRQ.ResponseFilter }
     *     
     */
    public OTAVehLocDetailRQ.ResponseFilter getResponseFilter() {
        return responseFilter;
    }

    /**
     * Sets the value of the responseFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link OTAVehLocDetailRQ.ResponseFilter }
     *     
     */
    public void setResponseFilter(OTAVehLocDetailRQ.ResponseFilter value) {
        this.responseFilter = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link OTAVehLocDetailRQ.Date }
     *     
     */
    public OTAVehLocDetailRQ.Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link OTAVehLocDetailRQ.Date }
     *     
     */
    public void setDate(OTAVehLocDetailRQ.Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the keyword property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyword property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyword().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OTAVehLocDetailRQ.Keyword }
     * 
     * 
     */
    public List<OTAVehLocDetailRQ.Keyword> getKeyword() {
        if (keyword == null) {
            keyword = new ArrayList<OTAVehLocDetailRQ.Keyword>();
        }
        return this.keyword;
    }

    /**
     * Gets the value of the tpaExtensions property.
     * 
     * @return
     *     possible object is
     *     {@link TPAExtensionsType }
     *     
     */
    public TPAExtensionsType getTPAExtensions() {
        return tpaExtensions;
    }

    /**
     * Sets the value of the tpaExtensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPAExtensionsType }
     *     
     */
    public void setTPAExtensions(TPAExtensionsType value) {
        this.tpaExtensions = value;
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
     * Gets the value of the reqRespVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqRespVersion() {
        return reqRespVersion;
    }

    /**
     * Sets the value of the reqRespVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqRespVersion(String value) {
        this.reqRespVersion = value;
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
     *       &lt;attribute name="PickUpDateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
     *       &lt;attribute name="ReturnDateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Date {

        @XmlAttribute(name = "PickUpDateTime")
        protected String pickUpDateTime;
        @XmlAttribute(name = "ReturnDateTime")
        protected String returnDateTime;

        /**
         * Gets the value of the pickUpDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPickUpDateTime() {
            return pickUpDateTime;
        }

        /**
         * Sets the value of the pickUpDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPickUpDateTime(String value) {
            this.pickUpDateTime = value;
        }

        /**
         * Gets the value of the returnDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReturnDateTime() {
            return returnDateTime;
        }

        /**
         * Sets the value of the returnDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReturnDateTime(String value) {
            this.returnDateTime = value;
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
     *       &lt;attribute name="Code" type="{http://www.opentravel.org/OTA/2003/05}AlphaNumericStringLength1to8" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Keyword {

        @XmlAttribute(name = "Code")
        protected String code;

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
     *       &lt;attribute name="ExtendedLocationCode" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to8" />
     *       &lt;attribute name="CounterLocation" type="{http://www.opentravel.org/OTA/2003/05}OTA_CodeType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Location
        extends LocationType
    {

        @XmlAttribute(name = "ExtendedLocationCode")
        protected String extendedLocationCode;
        @XmlAttribute(name = "CounterLocation")
        protected String counterLocation;

        /**
         * Gets the value of the extendedLocationCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExtendedLocationCode() {
            return extendedLocationCode;
        }

        /**
         * Sets the value of the extendedLocationCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExtendedLocationCode(String value) {
            this.extendedLocationCode = value;
        }

        /**
         * Gets the value of the counterLocation property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCounterLocation() {
            return counterLocation;
        }

        /**
         * Sets the value of the counterLocation property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCounterLocation(String value) {
            this.counterLocation = value;
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
     *       &lt;attribute name="LocationAddressPhoneIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="LocationInfoIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="VehiclesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="RequirementsIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="AdditionalFeesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="LiabilitiesIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="ServicesOfferedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ResponseFilter {

        @XmlAttribute(name = "LocationAddressPhoneIndicator")
        protected Boolean locationAddressPhoneIndicator;
        @XmlAttribute(name = "LocationInfoIndicator")
        protected Boolean locationInfoIndicator;
        @XmlAttribute(name = "VehiclesIndicator")
        protected Boolean vehiclesIndicator;
        @XmlAttribute(name = "RequirementsIndicator")
        protected Boolean requirementsIndicator;
        @XmlAttribute(name = "AdditionalFeesIndicator")
        protected Boolean additionalFeesIndicator;
        @XmlAttribute(name = "LiabilitiesIndicator")
        protected Boolean liabilitiesIndicator;
        @XmlAttribute(name = "ServicesOfferedIndicator")
        protected Boolean servicesOfferedIndicator;

        /**
         * Gets the value of the locationAddressPhoneIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isLocationAddressPhoneIndicator() {
            return locationAddressPhoneIndicator;
        }

        /**
         * Sets the value of the locationAddressPhoneIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setLocationAddressPhoneIndicator(Boolean value) {
            this.locationAddressPhoneIndicator = value;
        }

        /**
         * Gets the value of the locationInfoIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isLocationInfoIndicator() {
            return locationInfoIndicator;
        }

        /**
         * Sets the value of the locationInfoIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setLocationInfoIndicator(Boolean value) {
            this.locationInfoIndicator = value;
        }

        /**
         * Gets the value of the vehiclesIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isVehiclesIndicator() {
            return vehiclesIndicator;
        }

        /**
         * Sets the value of the vehiclesIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setVehiclesIndicator(Boolean value) {
            this.vehiclesIndicator = value;
        }

        /**
         * Gets the value of the requirementsIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequirementsIndicator() {
            return requirementsIndicator;
        }

        /**
         * Sets the value of the requirementsIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequirementsIndicator(Boolean value) {
            this.requirementsIndicator = value;
        }

        /**
         * Gets the value of the additionalFeesIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAdditionalFeesIndicator() {
            return additionalFeesIndicator;
        }

        /**
         * Sets the value of the additionalFeesIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAdditionalFeesIndicator(Boolean value) {
            this.additionalFeesIndicator = value;
        }

        /**
         * Gets the value of the liabilitiesIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isLiabilitiesIndicator() {
            return liabilitiesIndicator;
        }

        /**
         * Sets the value of the liabilitiesIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setLiabilitiesIndicator(Boolean value) {
            this.liabilitiesIndicator = value;
        }

        /**
         * Gets the value of the servicesOfferedIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isServicesOfferedIndicator() {
            return servicesOfferedIndicator;
        }

        /**
         * Sets the value of the servicesOfferedIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setServicesOfferedIndicator(Boolean value) {
            this.servicesOfferedIndicator = value;
        }

    }

}