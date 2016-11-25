//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ElectronicAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ElectronicAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="electronicAddressType" use="required" type="{profile.fidelio.2.0}electronicAddressType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElectronicAddress", propOrder = {
    "eAddress"
})
public class ElectronicAddress {

    @XmlElement(required = true)
    protected String eAddress;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mfInactiveDate;
    @XmlAttribute(required = true)
    protected ElectronicAddressType electronicAddressType;

    /**
     * Gets the value of the eAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEAddress() {
        return eAddress;
    }

    /**
     * Sets the value of the eAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEAddress(String value) {
        this.eAddress = value;
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
     * Gets the value of the electronicAddressType property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicAddressType }
     *     
     */
    public ElectronicAddressType getElectronicAddressType() {
        return electronicAddressType;
    }

    /**
     * Sets the value of the electronicAddressType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicAddressType }
     *     
     */
    public void setElectronicAddressType(ElectronicAddressType value) {
        this.electronicAddressType = value;
    }

}