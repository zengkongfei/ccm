//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RateAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rateAmountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{reservation.fidelio.2.0}Amount" minOccurs="0"/>
 *         &lt;element name="rateBasisUnits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mfInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateAmount", propOrder = {
    "rateAmountCode",
    "amount",
    "rateBasisUnits"
})
public class RateAmount {

    protected String rateAmountCode;
    @XmlElement(name = "Amount")
    protected Amount amount;
    protected Integer rateBasisUnits;
    @XmlAttribute
    protected XMLGregorianCalendar mfInactiveDate;

    /**
     * Gets the value of the rateAmountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateAmountCode() {
        return rateAmountCode;
    }

    /**
     * Sets the value of the rateAmountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateAmountCode(String value) {
        this.rateAmountCode = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setAmount(Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the rateBasisUnits property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRateBasisUnits() {
        return rateBasisUnits;
    }

    /**
     * Sets the value of the rateBasisUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRateBasisUnits(Integer value) {
        this.rateBasisUnits = value;
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

}