//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.05 at 04:34:51 下午 CST 
//


package com.ccm.oxi.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crsId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegQuery", propOrder = {
    "crsId",
    "legNumbers"
})
public class LegQuery {

    protected String crsId;
    protected String legNumbers;

    /**
     * Gets the value of the crsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrsId() {
        return crsId;
    }

    /**
     * Sets the value of the crsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrsId(String value) {
        this.crsId = value;
    }

    /**
     * Gets the value of the legNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegNumbers() {
        return legNumbers;
    }

    /**
     * Sets the value of the legNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegNumbers(String value) {
        this.legNumbers = value;
    }

}
