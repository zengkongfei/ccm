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
 * Contains references to the DynamicPkgGuest the air search is for.
 * 
 * <p>Java class for TravelRefSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TravelRefSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PassengerTypeQuantities" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PassengerTypeQuantity" type="{http://www.opentravel.org/OTA/2003/05}PassengerTypeQuantityType" maxOccurs="10" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TravelerRefNumbers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TravelerRefNumber" maxOccurs="99" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}TravelerRefNumberGroup"/>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PriceRequestInformation" type="{http://www.opentravel.org/OTA/2003/05}PriceRequestInformationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TravelRefSummaryType", propOrder = {
    "passengerTypeQuantities",
    "travelerRefNumbers",
    "priceRequestInformation"
})
public class TravelRefSummaryType {

    @XmlElement(name = "PassengerTypeQuantities")
    protected TravelRefSummaryType.PassengerTypeQuantities passengerTypeQuantities;
    @XmlElement(name = "TravelerRefNumbers")
    protected TravelRefSummaryType.TravelerRefNumbers travelerRefNumbers;
    @XmlElement(name = "PriceRequestInformation")
    protected PriceRequestInformationType priceRequestInformation;

    /**
     * Gets the value of the passengerTypeQuantities property.
     * 
     * @return
     *     possible object is
     *     {@link TravelRefSummaryType.PassengerTypeQuantities }
     *     
     */
    public TravelRefSummaryType.PassengerTypeQuantities getPassengerTypeQuantities() {
        return passengerTypeQuantities;
    }

    /**
     * Sets the value of the passengerTypeQuantities property.
     * 
     * @param value
     *     allowed object is
     *     {@link TravelRefSummaryType.PassengerTypeQuantities }
     *     
     */
    public void setPassengerTypeQuantities(TravelRefSummaryType.PassengerTypeQuantities value) {
        this.passengerTypeQuantities = value;
    }

    /**
     * Gets the value of the travelerRefNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link TravelRefSummaryType.TravelerRefNumbers }
     *     
     */
    public TravelRefSummaryType.TravelerRefNumbers getTravelerRefNumbers() {
        return travelerRefNumbers;
    }

    /**
     * Sets the value of the travelerRefNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link TravelRefSummaryType.TravelerRefNumbers }
     *     
     */
    public void setTravelerRefNumbers(TravelRefSummaryType.TravelerRefNumbers value) {
        this.travelerRefNumbers = value;
    }

    /**
     * Gets the value of the priceRequestInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PriceRequestInformationType }
     *     
     */
    public PriceRequestInformationType getPriceRequestInformation() {
        return priceRequestInformation;
    }

    /**
     * Sets the value of the priceRequestInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceRequestInformationType }
     *     
     */
    public void setPriceRequestInformation(PriceRequestInformationType value) {
        this.priceRequestInformation = value;
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
     *         &lt;element name="PassengerTypeQuantity" type="{http://www.opentravel.org/OTA/2003/05}PassengerTypeQuantityType" maxOccurs="10" minOccurs="0"/>
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
        "passengerTypeQuantity"
    })
    public static class PassengerTypeQuantities {

        @XmlElement(name = "PassengerTypeQuantity")
        protected List<PassengerTypeQuantityType> passengerTypeQuantity;

        /**
         * Gets the value of the passengerTypeQuantity property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the passengerTypeQuantity property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPassengerTypeQuantity().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PassengerTypeQuantityType }
         * 
         * 
         */
        public List<PassengerTypeQuantityType> getPassengerTypeQuantity() {
            if (passengerTypeQuantity == null) {
                passengerTypeQuantity = new ArrayList<PassengerTypeQuantityType>();
            }
            return this.passengerTypeQuantity;
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
     *         &lt;element name="TravelerRefNumber" maxOccurs="99" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}TravelerRefNumberGroup"/>
     *               &lt;/restriction>
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
        "travelerRefNumber"
    })
    public static class TravelerRefNumbers {

        @XmlElement(name = "TravelerRefNumber")
        protected List<TravelRefSummaryType.TravelerRefNumbers.TravelerRefNumber> travelerRefNumber;

        /**
         * Gets the value of the travelerRefNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the travelerRefNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTravelerRefNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TravelRefSummaryType.TravelerRefNumbers.TravelerRefNumber }
         * 
         * 
         */
        public List<TravelRefSummaryType.TravelerRefNumbers.TravelerRefNumber> getTravelerRefNumber() {
            if (travelerRefNumber == null) {
                travelerRefNumber = new ArrayList<TravelRefSummaryType.TravelerRefNumbers.TravelerRefNumber>();
            }
            return this.travelerRefNumber;
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
         *       &lt;attGroup ref="{http://www.opentravel.org/OTA/2003/05}TravelerRefNumberGroup"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class TravelerRefNumber {

            @XmlAttribute(name = "RPH")
            protected String rph;
            @XmlAttribute(name = "SurnameRefNumber")
            protected String surnameRefNumber;

            /**
             * Gets the value of the rph property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRPH() {
                return rph;
            }

            /**
             * Sets the value of the rph property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRPH(String value) {
                this.rph = value;
            }

            /**
             * Gets the value of the surnameRefNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSurnameRefNumber() {
                return surnameRefNumber;
            }

            /**
             * Sets the value of the surnameRefNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSurnameRefNumber(String value) {
                this.surnameRefNumber = value;
            }

        }

    }

}