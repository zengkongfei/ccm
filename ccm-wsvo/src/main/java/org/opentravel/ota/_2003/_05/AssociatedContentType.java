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
 * Associated content is content (items) associated with the itinerary, but not being part of an itinerary.  Examples of itinerary associated items are, travel insurance, maps, and city information.
 * 
 * <p>Java class for AssociatedContentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssociatedContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssocItems">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssocItem" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AssocItemRef" type="{http://www.opentravel.org/OTA/2003/05}UniqueID_Type"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="RPH" use="required" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
 *                           &lt;attribute name="DateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
 *                           &lt;attribute name="Type" use="required" type="{http://www.opentravel.org/OTA/2003/05}OTA_CodeType" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Pricing" type="{http://www.opentravel.org/OTA/2003/05}ItinPricingType" minOccurs="0"/>
 *                   &lt;element name="SpecialRequestDetails">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.opentravel.org/OTA/2003/05}SpecialReqDetailsType">
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Pricing" type="{http://www.opentravel.org/OTA/2003/05}ItinPricingType" minOccurs="0"/>
 *         &lt;element name="SpecialRequestDetails">
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
 *         &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociatedContentType", propOrder = {
    "assocItems",
    "pricing",
    "specialRequestDetails",
    "tpaExtensions"
})
public class AssociatedContentType {

    @XmlElement(name = "AssocItems", required = true)
    protected AssociatedContentType.AssocItems assocItems;
    @XmlElement(name = "Pricing")
    protected ItinPricingType pricing;
    @XmlElement(name = "SpecialRequestDetails", required = true)
    protected AssociatedContentType.SpecialRequestDetails specialRequestDetails;
    @XmlElement(name = "TPA_Extensions")
    protected TPAExtensionsType tpaExtensions;

    /**
     * Gets the value of the assocItems property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedContentType.AssocItems }
     *     
     */
    public AssociatedContentType.AssocItems getAssocItems() {
        return assocItems;
    }

    /**
     * Sets the value of the assocItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedContentType.AssocItems }
     *     
     */
    public void setAssocItems(AssociatedContentType.AssocItems value) {
        this.assocItems = value;
    }

    /**
     * Gets the value of the pricing property.
     * 
     * @return
     *     possible object is
     *     {@link ItinPricingType }
     *     
     */
    public ItinPricingType getPricing() {
        return pricing;
    }

    /**
     * Sets the value of the pricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItinPricingType }
     *     
     */
    public void setPricing(ItinPricingType value) {
        this.pricing = value;
    }

    /**
     * Gets the value of the specialRequestDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedContentType.SpecialRequestDetails }
     *     
     */
    public AssociatedContentType.SpecialRequestDetails getSpecialRequestDetails() {
        return specialRequestDetails;
    }

    /**
     * Sets the value of the specialRequestDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedContentType.SpecialRequestDetails }
     *     
     */
    public void setSpecialRequestDetails(AssociatedContentType.SpecialRequestDetails value) {
        this.specialRequestDetails = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="AssocItem" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AssocItemRef" type="{http://www.opentravel.org/OTA/2003/05}UniqueID_Type"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="RPH" use="required" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
     *                 &lt;attribute name="DateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
     *                 &lt;attribute name="Type" use="required" type="{http://www.opentravel.org/OTA/2003/05}OTA_CodeType" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Pricing" type="{http://www.opentravel.org/OTA/2003/05}ItinPricingType" minOccurs="0"/>
     *         &lt;element name="SpecialRequestDetails">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.opentravel.org/OTA/2003/05}SpecialReqDetailsType">
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
        "assocItem",
        "pricing",
        "specialRequestDetails"
    })
    public static class AssocItems {

        @XmlElement(name = "AssocItem", required = true)
        protected List<AssociatedContentType.AssocItems.AssocItem> assocItem;
        @XmlElement(name = "Pricing")
        protected ItinPricingType pricing;
        @XmlElement(name = "SpecialRequestDetails", required = true)
        protected AssociatedContentType.AssocItems.SpecialRequestDetails specialRequestDetails;

        /**
         * Gets the value of the assocItem property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assocItem property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssocItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AssociatedContentType.AssocItems.AssocItem }
         * 
         * 
         */
        public List<AssociatedContentType.AssocItems.AssocItem> getAssocItem() {
            if (assocItem == null) {
                assocItem = new ArrayList<AssociatedContentType.AssocItems.AssocItem>();
            }
            return this.assocItem;
        }

        /**
         * Gets the value of the pricing property.
         * 
         * @return
         *     possible object is
         *     {@link ItinPricingType }
         *     
         */
        public ItinPricingType getPricing() {
            return pricing;
        }

        /**
         * Sets the value of the pricing property.
         * 
         * @param value
         *     allowed object is
         *     {@link ItinPricingType }
         *     
         */
        public void setPricing(ItinPricingType value) {
            this.pricing = value;
        }

        /**
         * Gets the value of the specialRequestDetails property.
         * 
         * @return
         *     possible object is
         *     {@link AssociatedContentType.AssocItems.SpecialRequestDetails }
         *     
         */
        public AssociatedContentType.AssocItems.SpecialRequestDetails getSpecialRequestDetails() {
            return specialRequestDetails;
        }

        /**
         * Sets the value of the specialRequestDetails property.
         * 
         * @param value
         *     allowed object is
         *     {@link AssociatedContentType.AssocItems.SpecialRequestDetails }
         *     
         */
        public void setSpecialRequestDetails(AssociatedContentType.AssocItems.SpecialRequestDetails value) {
            this.specialRequestDetails = value;
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
         *         &lt;element name="AssocItemRef" type="{http://www.opentravel.org/OTA/2003/05}UniqueID_Type"/>
         *       &lt;/sequence>
         *       &lt;attribute name="RPH" use="required" type="{http://www.opentravel.org/OTA/2003/05}RPH_Type" />
         *       &lt;attribute name="DateTime" type="{http://www.opentravel.org/OTA/2003/05}DateOrDateTimeType" />
         *       &lt;attribute name="Type" use="required" type="{http://www.opentravel.org/OTA/2003/05}OTA_CodeType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "assocItemRef"
        })
        public static class AssocItem {

            @XmlElement(name = "AssocItemRef", required = true)
            protected UniqueIDType assocItemRef;
            @XmlAttribute(name = "RPH", required = true)
            protected String rph;
            @XmlAttribute(name = "DateTime")
            protected String dateTime;
            @XmlAttribute(name = "Type", required = true)
            protected String type;

            /**
             * Gets the value of the assocItemRef property.
             * 
             * @return
             *     possible object is
             *     {@link UniqueIDType }
             *     
             */
            public UniqueIDType getAssocItemRef() {
                return assocItemRef;
            }

            /**
             * Sets the value of the assocItemRef property.
             * 
             * @param value
             *     allowed object is
             *     {@link UniqueIDType }
             *     
             */
            public void setAssocItemRef(UniqueIDType value) {
                this.assocItemRef = value;
            }

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
             * Gets the value of the dateTime property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateTime() {
                return dateTime;
            }

            /**
             * Sets the value of the dateTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateTime(String value) {
                this.dateTime = value;
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


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}SpecialReqDetailsType">
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class SpecialRequestDetails
            extends SpecialReqDetailsType
        {


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
