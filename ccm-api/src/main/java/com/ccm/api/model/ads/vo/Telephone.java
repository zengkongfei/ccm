//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.18 at 04:07:49 pmCST 
//


package com.ccm.api.model.ads.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Wrapper to BaseTelephone to provide type and privacy data.
 * 
 * <p>Java class for Telephone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Telephone">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.micros.com/2002A}BaseTelephone">
 *       &lt;attGroup ref="{http://www.micros.com/2002A}PrivacyAttributes"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Telephone")
public class Telephone
    extends BaseTelephone
{

    @XmlAttribute
    protected String privacyIndicator;
    @XmlAttribute
    protected String privacyMarketingIndicator;
    @XmlAttribute
    protected String privacySynchIndicator;

    /**
     * Gets the value of the privacyIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacyIndicator() {
        return privacyIndicator;
    }

    /**
     * Sets the value of the privacyIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacyIndicator(String value) {
        this.privacyIndicator = value;
    }

    /**
     * Gets the value of the privacyMarketingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacyMarketingIndicator() {
        return privacyMarketingIndicator;
    }

    /**
     * Sets the value of the privacyMarketingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacyMarketingIndicator(String value) {
        this.privacyMarketingIndicator = value;
    }

    /**
     * Gets the value of the privacySynchIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacySynchIndicator() {
        return privacySynchIndicator;
    }

    /**
     * Sets the value of the privacySynchIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacySynchIndicator(String value) {
        this.privacySynchIndicator = value;
    }

}
