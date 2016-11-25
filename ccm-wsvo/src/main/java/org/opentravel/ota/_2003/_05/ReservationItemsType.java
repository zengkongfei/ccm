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
 * Travel segment (air, vehicle, hotel, rail, cruise, tour, general and package), pricing and special request information.
 * 
 * <p>Java class for ReservationItemsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReservationItemsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}TravelSegmentType">
 *                 &lt;attribute name="ItinSeqNumber" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                 &lt;attribute name="IsPassive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="IssueBoardingPass" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="BoardingPassIssued" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ItemPricing" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}ItinPricingType">
 *                 &lt;sequence>
 *                   &lt;element name="AirFareInfo" type="{http://www.opentravel.org/OTA/2003/05}BookingPriceInfoType"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SpecialRequestDetails" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}SpecialReqDetailsType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ChronoOrdered" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservationItemsType", propOrder = {
    "item",
    "itemPricing",
    "specialRequestDetails"
})
public class ReservationItemsType {

    @XmlElement(name = "Item", required = true)
    protected List<ReservationItemsType.Item> item;
    @XmlElement(name = "ItemPricing")
    protected List<ReservationItemsType.ItemPricing> itemPricing;
    @XmlElement(name = "SpecialRequestDetails")
    protected ReservationItemsType.SpecialRequestDetails specialRequestDetails;
    @XmlAttribute(name = "ChronoOrdered")
    protected Boolean chronoOrdered;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReservationItemsType.Item }
     * 
     * 
     */
    public List<ReservationItemsType.Item> getItem() {
        if (item == null) {
            item = new ArrayList<ReservationItemsType.Item>();
        }
        return this.item;
    }

    /**
     * Gets the value of the itemPricing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemPricing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemPricing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReservationItemsType.ItemPricing }
     * 
     * 
     */
    public List<ReservationItemsType.ItemPricing> getItemPricing() {
        if (itemPricing == null) {
            itemPricing = new ArrayList<ReservationItemsType.ItemPricing>();
        }
        return this.itemPricing;
    }

    /**
     * Gets the value of the specialRequestDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationItemsType.SpecialRequestDetails }
     *     
     */
    public ReservationItemsType.SpecialRequestDetails getSpecialRequestDetails() {
        return specialRequestDetails;
    }

    /**
     * Sets the value of the specialRequestDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationItemsType.SpecialRequestDetails }
     *     
     */
    public void setSpecialRequestDetails(ReservationItemsType.SpecialRequestDetails value) {
        this.specialRequestDetails = value;
    }

    /**
     * Gets the value of the chronoOrdered property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChronoOrdered() {
        return chronoOrdered;
    }

    /**
     * Sets the value of the chronoOrdered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChronoOrdered(Boolean value) {
        this.chronoOrdered = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}TravelSegmentType">
     *       &lt;attribute name="ItinSeqNumber" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *       &lt;attribute name="IsPassive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="IssueBoardingPass" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="BoardingPassIssued" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Item
        extends TravelSegmentType
    {

        @XmlAttribute(name = "ItinSeqNumber")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger itinSeqNumber;
        @XmlAttribute(name = "IsPassive")
        protected Boolean isPassive;
        @XmlAttribute(name = "IssueBoardingPass")
        protected Boolean issueBoardingPass;
        @XmlAttribute(name = "BoardingPassIssued")
        protected Boolean boardingPassIssued;

        /**
         * Gets the value of the itinSeqNumber property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getItinSeqNumber() {
            return itinSeqNumber;
        }

        /**
         * Sets the value of the itinSeqNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setItinSeqNumber(BigInteger value) {
            this.itinSeqNumber = value;
        }

        /**
         * Gets the value of the isPassive property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIsPassive() {
            return isPassive;
        }

        /**
         * Sets the value of the isPassive property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIsPassive(Boolean value) {
            this.isPassive = value;
        }

        /**
         * Gets the value of the issueBoardingPass property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIssueBoardingPass() {
            return issueBoardingPass;
        }

        /**
         * Sets the value of the issueBoardingPass property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIssueBoardingPass(Boolean value) {
            this.issueBoardingPass = value;
        }

        /**
         * Gets the value of the boardingPassIssued property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBoardingPassIssued() {
            return boardingPassIssued;
        }

        /**
         * Sets the value of the boardingPassIssued property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBoardingPassIssued(Boolean value) {
            this.boardingPassIssued = value;
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
     *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}ItinPricingType">
     *       &lt;sequence>
     *         &lt;element name="AirFareInfo" type="{http://www.opentravel.org/OTA/2003/05}BookingPriceInfoType"/>
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
        "airFareInfo"
    })
    public static class ItemPricing
        extends ItinPricingType
    {

        @XmlElement(name = "AirFareInfo", required = true)
        protected BookingPriceInfoType airFareInfo;

        /**
         * Gets the value of the airFareInfo property.
         * 
         * @return
         *     possible object is
         *     {@link BookingPriceInfoType }
         *     
         */
        public BookingPriceInfoType getAirFareInfo() {
            return airFareInfo;
        }

        /**
         * Sets the value of the airFareInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link BookingPriceInfoType }
         *     
         */
        public void setAirFareInfo(BookingPriceInfoType value) {
            this.airFareInfo = value;
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
     *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}SpecialReqDetailsType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
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
        "tpaExtensions"
    })
    public static class SpecialRequestDetails
        extends SpecialReqDetailsType
    {

        @XmlElement(name = "TPA_Extensions")
        protected TPAExtensionsType tpaExtensions;

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

    }

}
