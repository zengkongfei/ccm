//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.06 at 12:42:44 pm CST 
//


package com.ccm.api.model.availability.res;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://webservices.micros.com/ows/5.1/Availability.wsdl}AvailabilityResponse"/>
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
    "availabilityResponse"
})
@XmlRootElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class Body {

    @XmlElement(name = "AvailabilityResponse", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected AvailabilityResponse availabilityResponse;

    /**
     * Gets the value of the availabilityResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AvailabilityResponse }
     *     
     */
    public AvailabilityResponse getAvailabilityResponse() {
        return availabilityResponse;
    }

    /**
     * Sets the value of the availabilityResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailabilityResponse }
     *     
     */
    public void setAvailabilityResponse(AvailabilityResponse value) {
        this.availabilityResponse = value;
    }

}
