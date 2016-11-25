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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Amentities and services available at the golf course (either included in the price of the tee time or available for an extra fee), such as power carts. Note, includes pricing.
 * 
 * <p>Java class for GolfAmenityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GolfAmenityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fees" type="{http://www.opentravel.org/OTA/2003/05}FeesType" maxOccurs="99" minOccurs="0"/>
 *         &lt;element name="Policy" maxOccurs="9" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PaymentGuarantee" type="{http://www.opentravel.org/OTA/2003/05}PaymentFormType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AmenityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PricingType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="PerPerson"/>
 *             &lt;enumeration value="PerAmenity"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="IncludedInTeeTimePriceInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ReservableInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PolicyInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SequentialMask">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[0-1]"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GolfAmenityType", propOrder = {
    "fees",
    "policy",
    "paymentGuarantee"
})
@XmlSeeAlso({
    org.opentravel.ota._2003._05.TeeTimeType.Amenity.class,
    org.opentravel.ota._2003._05.TeeTimeBookingType.Amenity.class,
    org.opentravel.ota._2003._05.TeeTimeAvailType.Amenity.class
})
public class GolfAmenityType {

    @XmlElement(name = "Fees")
    protected List<FeesType> fees;
    @XmlElement(name = "Policy")
    protected List<GolfAmenityType.Policy> policy;
    @XmlElement(name = "PaymentGuarantee")
    protected PaymentFormType paymentGuarantee;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "AmenityCode")
    protected String amenityCode;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Description")
    protected String description;
    @XmlAttribute(name = "PricingType")
    protected String pricingType;
    @XmlAttribute(name = "IncludedInTeeTimePriceInd")
    protected Boolean includedInTeeTimePriceInd;
    @XmlAttribute(name = "ReservableInd")
    protected Boolean reservableInd;
    @XmlAttribute(name = "PolicyInfo")
    protected String policyInfo;
    @XmlAttribute(name = "SequentialMask")
    protected String sequentialMask;

    /**
     * Gets the value of the fees property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fees property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFees().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeesType }
     * 
     * 
     */
    public List<FeesType> getFees() {
        if (fees == null) {
            fees = new ArrayList<FeesType>();
        }
        return this.fees;
    }

    /**
     * Gets the value of the policy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GolfAmenityType.Policy }
     * 
     * 
     */
    public List<GolfAmenityType.Policy> getPolicy() {
        if (policy == null) {
            policy = new ArrayList<GolfAmenityType.Policy>();
        }
        return this.policy;
    }

    /**
     * Gets the value of the paymentGuarantee property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentFormType }
     *     
     */
    public PaymentFormType getPaymentGuarantee() {
        return paymentGuarantee;
    }

    /**
     * Sets the value of the paymentGuarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentFormType }
     *     
     */
    public void setPaymentGuarantee(PaymentFormType value) {
        this.paymentGuarantee = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the amenityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityCode() {
        return amenityCode;
    }

    /**
     * Sets the value of the amenityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityCode(String value) {
        this.amenityCode = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the pricingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricingType() {
        return pricingType;
    }

    /**
     * Sets the value of the pricingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricingType(String value) {
        this.pricingType = value;
    }

    /**
     * Gets the value of the includedInTeeTimePriceInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludedInTeeTimePriceInd() {
        return includedInTeeTimePriceInd;
    }

    /**
     * Sets the value of the includedInTeeTimePriceInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludedInTeeTimePriceInd(Boolean value) {
        this.includedInTeeTimePriceInd = value;
    }

    /**
     * Gets the value of the reservableInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReservableInd() {
        return reservableInd;
    }

    /**
     * Sets the value of the reservableInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReservableInd(Boolean value) {
        this.reservableInd = value;
    }

    /**
     * Gets the value of the policyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyInfo() {
        return policyInfo;
    }

    /**
     * Sets the value of the policyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyInfo(String value) {
        this.policyInfo = value;
    }

    /**
     * Gets the value of the sequentialMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequentialMask() {
        return sequentialMask;
    }

    /**
     * Sets the value of the sequentialMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequentialMask(String value) {
        this.sequentialMask = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Policy {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "Type")
        protected String type;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
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

    }

}
