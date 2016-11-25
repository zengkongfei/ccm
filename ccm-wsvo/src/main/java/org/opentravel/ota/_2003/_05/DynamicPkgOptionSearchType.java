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
 * A collection of package option search criteria.
 * 
 * <p>Java class for DynamicPkgOptionSearchType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DynamicPkgOptionSearchType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opentravel.org/OTA/2003/05}DynamicPkgSearchType">
 *       &lt;sequence>
 *         &lt;element name="OptionSearchCriteria" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Criterion" type="{http://www.opentravel.org/OTA/2003/05}PackageOptionSearchCriterionType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="AvailableOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RequestType" type="{http://www.opentravel.org/OTA/2003/05}PackageOptionComponentSearchType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicPkgOptionSearchType", propOrder = {
    "optionSearchCriteria",
    "tpaExtensions"
})
public class DynamicPkgOptionSearchType
    extends DynamicPkgSearchType
{

    @XmlElement(name = "OptionSearchCriteria")
    protected DynamicPkgOptionSearchType.OptionSearchCriteria optionSearchCriteria;
    @XmlElement(name = "TPA_Extensions")
    protected TPAExtensionsType tpaExtensions;
    @XmlAttribute(name = "RequestType")
    protected PackageOptionComponentSearchType requestType;

    /**
     * Gets the value of the optionSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link DynamicPkgOptionSearchType.OptionSearchCriteria }
     *     
     */
    public DynamicPkgOptionSearchType.OptionSearchCriteria getOptionSearchCriteria() {
        return optionSearchCriteria;
    }

    /**
     * Sets the value of the optionSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link DynamicPkgOptionSearchType.OptionSearchCriteria }
     *     
     */
    public void setOptionSearchCriteria(DynamicPkgOptionSearchType.OptionSearchCriteria value) {
        this.optionSearchCriteria = value;
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
     * Gets the value of the requestType property.
     * 
     * @return
     *     possible object is
     *     {@link PackageOptionComponentSearchType }
     *     
     */
    public PackageOptionComponentSearchType getRequestType() {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageOptionComponentSearchType }
     *     
     */
    public void setRequestType(PackageOptionComponentSearchType value) {
        this.requestType = value;
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
     *         &lt;element name="Criterion" type="{http://www.opentravel.org/OTA/2003/05}PackageOptionSearchCriterionType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *       &lt;attribute name="AvailableOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "criterion"
    })
    public static class OptionSearchCriteria {

        @XmlElement(name = "Criterion", required = true)
        protected List<PackageOptionSearchCriterionType> criterion;
        @XmlAttribute(name = "AvailableOnlyIndicator")
        protected Boolean availableOnlyIndicator;

        /**
         * Gets the value of the criterion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the criterion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCriterion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PackageOptionSearchCriterionType }
         * 
         * 
         */
        public List<PackageOptionSearchCriterionType> getCriterion() {
            if (criterion == null) {
                criterion = new ArrayList<PackageOptionSearchCriterionType>();
            }
            return this.criterion;
        }

        /**
         * Gets the value of the availableOnlyIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isAvailableOnlyIndicator() {
            return availableOnlyIndicator;
        }

        /**
         * Sets the value of the availableOnlyIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAvailableOnlyIndicator(Boolean value) {
            this.availableOnlyIndicator = value;
        }

    }

}
