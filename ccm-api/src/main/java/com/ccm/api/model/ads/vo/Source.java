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
 *         &lt;element ref="{http://www.micros.com/2002A}UniqueId" minOccurs="0"/>
 *         &lt;element ref="{http://www.micros.com/2002A}Position" minOccurs="0"/>
 *         &lt;element ref="{http://www.micros.com/2002A}BookingChannel" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="sourceType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Agent"/>
 *             &lt;enumeration value="GDS"/>
 *             &lt;enumeration value="SignatureGDS"/>
 *             &lt;enumeration value="Switch"/>
 *             &lt;enumeration value="Web"/>
 *             &lt;enumeration value="WEB"/>
 *             &lt;enumeration value="ADS"/>
 *             &lt;enumeration value="Other"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="otherSourceType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="agentSine" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pseudoCityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="agentCity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="agentCountry" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="agentDutyCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="carrierCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="airportCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firstDepartPoint" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ERSPUserId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uniqueId",
    "position",
    "bookingChannel"
})
@XmlRootElement(name = "Source")
public class Source {

    @XmlElement(name = "UniqueId")
    protected UniqueId uniqueId;
    @XmlElement(name = "Position")
    protected Position position;
    @XmlElement(name = "BookingChannel")
    protected BookingChannel bookingChannel;
    @XmlAttribute(required = true)
    protected String sourceType;
    @XmlAttribute
    protected String otherSourceType;
    @XmlAttribute
    protected String agentSine;
    @XmlAttribute
    protected String pseudoCityCode;
    @XmlAttribute
    protected String agentCity;
    @XmlAttribute
    protected String agentCountry;
    @XmlAttribute
    protected String currency;
    @XmlAttribute
    protected String agentDutyCode;
    @XmlAttribute
    protected String carrierCode;
    @XmlAttribute
    protected String airportCode;
    @XmlAttribute
    protected String firstDepartPoint;
    @XmlAttribute(name = "ERSPUserId")
    protected String erspUserId;

    /**
     * Gets the value of the uniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link UniqueId }
     *     
     */
    public UniqueId getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets the value of the uniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueId }
     *     
     */
    public void setUniqueId(UniqueId value) {
        this.uniqueId = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link Position }
     *     
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link Position }
     *     
     */
    public void setPosition(Position value) {
        this.position = value;
    }

    /**
     * Gets the value of the bookingChannel property.
     * 
     * @return
     *     possible object is
     *     {@link BookingChannel }
     *     
     */
    public BookingChannel getBookingChannel() {
        return bookingChannel;
    }

    /**
     * Sets the value of the bookingChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookingChannel }
     *     
     */
    public void setBookingChannel(BookingChannel value) {
        this.bookingChannel = value;
    }

    /**
     * Gets the value of the sourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Sets the value of the sourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceType(String value) {
        this.sourceType = value;
    }

    /**
     * Gets the value of the otherSourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherSourceType() {
        return otherSourceType;
    }

    /**
     * Sets the value of the otherSourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherSourceType(String value) {
        this.otherSourceType = value;
    }

    /**
     * Gets the value of the agentSine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentSine() {
        return agentSine;
    }

    /**
     * Sets the value of the agentSine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentSine(String value) {
        this.agentSine = value;
    }

    /**
     * Gets the value of the pseudoCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPseudoCityCode() {
        return pseudoCityCode;
    }

    /**
     * Sets the value of the pseudoCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPseudoCityCode(String value) {
        this.pseudoCityCode = value;
    }

    /**
     * Gets the value of the agentCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentCity() {
        return agentCity;
    }

    /**
     * Sets the value of the agentCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentCity(String value) {
        this.agentCity = value;
    }

    /**
     * Gets the value of the agentCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentCountry() {
        return agentCountry;
    }

    /**
     * Sets the value of the agentCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentCountry(String value) {
        this.agentCountry = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the agentDutyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentDutyCode() {
        return agentDutyCode;
    }

    /**
     * Sets the value of the agentDutyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentDutyCode(String value) {
        this.agentDutyCode = value;
    }

    /**
     * Gets the value of the carrierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierCode() {
        return carrierCode;
    }

    /**
     * Sets the value of the carrierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierCode(String value) {
        this.carrierCode = value;
    }

    /**
     * Gets the value of the airportCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * Sets the value of the airportCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirportCode(String value) {
        this.airportCode = value;
    }

    /**
     * Gets the value of the firstDepartPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstDepartPoint() {
        return firstDepartPoint;
    }

    /**
     * Sets the value of the firstDepartPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstDepartPoint(String value) {
        this.firstDepartPoint = value;
    }

    /**
     * Gets the value of the erspUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERSPUserId() {
        return erspUserId;
    }

    /**
     * Sets the value of the erspUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERSPUserId(String value) {
        this.erspUserId = value;
    }

}
