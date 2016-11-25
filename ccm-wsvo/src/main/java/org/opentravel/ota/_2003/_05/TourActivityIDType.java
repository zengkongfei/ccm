//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Unique identification for the tour/activity, including name, supplier ID and trading partner ID.
 * 
 * <p>Java class for TourActivityID_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TourActivityID_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opentravel.org/OTA/2003/05}TPA_Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SupplierProductCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PartnerProductCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SupplierBrandCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to64" />
 *       &lt;attribute name="ShortName" type="{http://www.opentravel.org/OTA/2003/05}StringLength1to64" />
 *       &lt;attribute name="TourActivityID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TourActivityID_Type", propOrder = {
    "tpaExtensions"
})
@XmlSeeAlso({
    org.opentravel.ota._2003._05.OTATourActivityAvailRS.TourActivityInfo.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityAvailRQ.TourActivity.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityResRetrieveRS.Summary.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityResRetrieveRS.Detail.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRS.ReservationDetails.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityBookRQ.BookingInfo.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityCancelRQ.BasicInfo.class,
    org.opentravel.ota._2003._05.OTATourActivityCancelRS.Reservation.ReservationInfo.BasicInfo.class
})
public class TourActivityIDType {

    @XmlElement(name = "TPA_Extensions")
    protected TPAExtensionsType tpaExtensions;
    @XmlAttribute(name = "SupplierProductCode")
    protected String supplierProductCode;
    @XmlAttribute(name = "PartnerProductCode")
    protected String partnerProductCode;
    @XmlAttribute(name = "SupplierBrandCode")
    protected String supplierBrandCode;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "ShortName")
    protected String shortName;
    @XmlAttribute(name = "TourActivityID")
    protected String tourActivityID;

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
     * Gets the value of the supplierProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierProductCode() {
        return supplierProductCode;
    }

    /**
     * Sets the value of the supplierProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierProductCode(String value) {
        this.supplierProductCode = value;
    }

    /**
     * Gets the value of the partnerProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerProductCode() {
        return partnerProductCode;
    }

    /**
     * Sets the value of the partnerProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerProductCode(String value) {
        this.partnerProductCode = value;
    }

    /**
     * Gets the value of the supplierBrandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierBrandCode() {
        return supplierBrandCode;
    }

    /**
     * Sets the value of the supplierBrandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierBrandCode(String value) {
        this.supplierBrandCode = value;
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
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the tourActivityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTourActivityID() {
        return tourActivityID;
    }

    /**
     * Sets the value of the tourActivityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTourActivityID(String value) {
        this.tourActivityID = value;
    }

}
