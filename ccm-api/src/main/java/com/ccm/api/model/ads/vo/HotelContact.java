//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.18 at 04:07:49 pmCST 
//


package com.ccm.api.model.ads.vo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Basic contact information for Hotel Property. 
 * 
 * <p>Java class for HotelContact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelContact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Addresses" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Address" type="{http://www.micros.com/2002A}AddressData" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EMails" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EMail" type="{http://www.micros.com/2002A}BaseEMail" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ContactPhoneNumbers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ContactPhoneNumber" type="{http://www.micros.com/2002A}BaseTelephone" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HotelInformation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HotelInfo" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.micros.com/2002A}DescriptiveText">
 *                           &lt;attribute name="infoType">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="Directions"/>
 *                                 &lt;enumeration value="CheckInInfo"/>
 *                                 &lt;enumeration value="CheckOutInfo"/>
 *                                 &lt;enumeration value="Other"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="otherInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="URLs" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="URL" type="{http://www.micros.com/2002A}BaseURL"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.micros.com/2002A}Vector" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelContact", propOrder = {
    "addresses",
    "eMails",
    "contactPhoneNumbers",
    "hotelInformation",
    "urLs",
    "vector"
})
public class HotelContact {

    @XmlElement(name = "Addresses")
    protected HotelContact.Addresses addresses;
    @XmlElement(name = "EMails")
    protected HotelContact.EMails eMails;
    @XmlElement(name = "ContactPhoneNumbers")
    protected HotelContact.ContactPhoneNumbers contactPhoneNumbers;
    @XmlElement(name = "HotelInformation")
    protected HotelContact.HotelInformation hotelInformation;
    @XmlElement(name = "URLs")
    protected HotelContact.URLs urLs;
    @XmlElement(name = "Vector")
    protected Vector vector;

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact.Addresses }
     *     
     */
    public HotelContact.Addresses getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact.Addresses }
     *     
     */
    public void setAddresses(HotelContact.Addresses value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the eMails property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact.EMails }
     *     
     */
    public HotelContact.EMails getEMails() {
        return eMails;
    }

    /**
     * Sets the value of the eMails property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact.EMails }
     *     
     */
    public void setEMails(HotelContact.EMails value) {
        this.eMails = value;
    }

    /**
     * Gets the value of the contactPhoneNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact.ContactPhoneNumbers }
     *     
     */
    public HotelContact.ContactPhoneNumbers getContactPhoneNumbers() {
        return contactPhoneNumbers;
    }

    /**
     * Sets the value of the contactPhoneNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact.ContactPhoneNumbers }
     *     
     */
    public void setContactPhoneNumbers(HotelContact.ContactPhoneNumbers value) {
        this.contactPhoneNumbers = value;
    }

    /**
     * Gets the value of the hotelInformation property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact.HotelInformation }
     *     
     */
    public HotelContact.HotelInformation getHotelInformation() {
        return hotelInformation;
    }

    /**
     * Sets the value of the hotelInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact.HotelInformation }
     *     
     */
    public void setHotelInformation(HotelContact.HotelInformation value) {
        this.hotelInformation = value;
    }

    /**
     * Gets the value of the urLs property.
     * 
     * @return
     *     possible object is
     *     {@link HotelContact.URLs }
     *     
     */
    public HotelContact.URLs getURLs() {
        return urLs;
    }

    /**
     * Sets the value of the urLs property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelContact.URLs }
     *     
     */
    public void setURLs(HotelContact.URLs value) {
        this.urLs = value;
    }

    /**
     * Gets the value of the vector property.
     * 
     * @return
     *     possible object is
     *     {@link Vector }
     *     
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * Sets the value of the vector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vector }
     *     
     */
    public void setVector(Vector value) {
        this.vector = value;
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
     *         &lt;element name="Address" type="{http://www.micros.com/2002A}AddressData" maxOccurs="unbounded"/>
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
        "address"
    })
    public static class Addresses {

        @XmlElement(name = "Address", required = true)
        protected List<AddressData> address;

        /**
         * Gets the value of the address property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the address property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAddress().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddressData }
         * 
         * 
         */
        public List<AddressData> getAddress() {
            if (address == null) {
                address = new ArrayList<AddressData>();
            }
            return this.address;
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
     *         &lt;element name="ContactPhoneNumber" type="{http://www.micros.com/2002A}BaseTelephone" maxOccurs="unbounded"/>
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
        "contactPhoneNumber"
    })
    public static class ContactPhoneNumbers {

        @XmlElement(name = "ContactPhoneNumber", required = true)
        protected List<BaseTelephone> contactPhoneNumber;

        /**
         * Gets the value of the contactPhoneNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contactPhoneNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContactPhoneNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BaseTelephone }
         * 
         * 
         */
        public List<BaseTelephone> getContactPhoneNumber() {
            if (contactPhoneNumber == null) {
                contactPhoneNumber = new ArrayList<BaseTelephone>();
            }
            return this.contactPhoneNumber;
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
     *         &lt;element name="EMail" type="{http://www.micros.com/2002A}BaseEMail" maxOccurs="unbounded"/>
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
        "eMail"
    })
    public static class EMails {

        @XmlElement(name = "EMail", required = true)
        protected List<BaseEMail> eMail;

        /**
         * Gets the value of the eMail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the eMail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEMail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BaseEMail }
         * 
         * 
         */
        public List<BaseEMail> getEMail() {
            if (eMail == null) {
                eMail = new ArrayList<BaseEMail>();
            }
            return this.eMail;
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
     *         &lt;element name="HotelInfo" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.micros.com/2002A}DescriptiveText">
     *                 &lt;attribute name="infoType">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="Directions"/>
     *                       &lt;enumeration value="CheckInInfo"/>
     *                       &lt;enumeration value="CheckOutInfo"/>
     *                       &lt;enumeration value="Other"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="otherInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "hotelInfo"
    })
    public static class HotelInformation {

        @XmlElement(name = "HotelInfo", required = true)
        protected List<HotelContact.HotelInformation.HotelInfo> hotelInfo;

        /**
         * Gets the value of the hotelInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hotelInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHotelInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HotelContact.HotelInformation.HotelInfo }
         * 
         * 
         */
        public List<HotelContact.HotelInformation.HotelInfo> getHotelInfo() {
            if (hotelInfo == null) {
                hotelInfo = new ArrayList<HotelContact.HotelInformation.HotelInfo>();
            }
            return this.hotelInfo;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.micros.com/2002A}DescriptiveText">
         *       &lt;attribute name="infoType">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="Directions"/>
         *             &lt;enumeration value="CheckInInfo"/>
         *             &lt;enumeration value="CheckOutInfo"/>
         *             &lt;enumeration value="Other"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="otherInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class HotelInfo
            extends DescriptiveText
        {

            @XmlAttribute
            protected String infoType;
            @XmlAttribute
            protected String otherInfoType;

            /**
             * Gets the value of the infoType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInfoType() {
                return infoType;
            }

            /**
             * Sets the value of the infoType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInfoType(String value) {
                this.infoType = value;
            }

            /**
             * Gets the value of the otherInfoType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOtherInfoType() {
                return otherInfoType;
            }

            /**
             * Sets the value of the otherInfoType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOtherInfoType(String value) {
                this.otherInfoType = value;
            }

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
     *         &lt;element name="URL" type="{http://www.micros.com/2002A}BaseURL"/>
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
        "url"
    })
    public static class URLs {

        @XmlElement(name = "URL", required = true)
        protected BaseURL url;

        /**
         * Gets the value of the url property.
         * 
         * @return
         *     possible object is
         *     {@link BaseURL }
         *     
         */
        public BaseURL getURL() {
            return url;
        }

        /**
         * Sets the value of the url property.
         * 
         * @param value
         *     allowed object is
         *     {@link BaseURL }
         *     
         */
        public void setURL(BaseURL value) {
            this.url = value;
        }

    }

}
