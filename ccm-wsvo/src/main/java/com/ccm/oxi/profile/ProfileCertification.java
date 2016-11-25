//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ProfileCertification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProfileCertification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="certificationType" use="required" type="{profile.fidelio.2.0}certificationType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfileCertification", propOrder = {
    "certificationID"
})
public class ProfileCertification {

    protected String certificationID;
    @XmlAttribute
    protected XMLGregorianCalendar mfInactiveDate;
    @XmlAttribute(required = true)
    protected CertificationType certificationType;

    /**
     * Gets the value of the certificationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationID() {
        return certificationID;
    }

    /**
     * Sets the value of the certificationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationID(String value) {
        this.certificationID = value;
    }

    /**
     * Gets the value of the mfInactiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMfInactiveDate() {
        return mfInactiveDate;
    }

    /**
     * Sets the value of the mfInactiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMfInactiveDate(XMLGregorianCalendar value) {
        this.mfInactiveDate = value;
    }

    /**
     * Gets the value of the certificationType property.
     * 
     * @return
     *     possible object is
     *     {@link CertificationType }
     *     
     */
    public CertificationType getCertificationType() {
        return certificationType;
    }

    /**
     * Sets the value of the certificationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificationType }
     *     
     */
    public void setCertificationType(CertificationType value) {
        this.certificationType = value;
    }

}
