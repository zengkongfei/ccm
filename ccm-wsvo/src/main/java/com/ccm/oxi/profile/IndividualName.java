//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.10 at 03:58:32 下午 CST 
//


package com.ccm.oxi.profile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IndividualName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameFirst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameMiddle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameSur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nameSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameOrdered" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualName", propOrder = {
    "namePrefix",
    "nameFirst",
    "nameMiddle",
    "nameSur",
    "nameSuffix",
    "nameTitle",
    "nameOrdered"
})
public class IndividualName {

    protected String namePrefix;
    protected String nameFirst;
    protected String nameMiddle;
    @XmlElement(required = true)
    protected String nameSur;
    protected String nameSuffix;
    protected String nameTitle;
    protected String nameOrdered;

    /**
     * Gets the value of the namePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamePrefix() {
        return namePrefix;
    }

    /**
     * Sets the value of the namePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamePrefix(String value) {
        this.namePrefix = value;
    }

    /**
     * Gets the value of the nameFirst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * Sets the value of the nameFirst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameFirst(String value) {
        this.nameFirst = value;
    }

    /**
     * Gets the value of the nameMiddle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameMiddle() {
        return nameMiddle;
    }

    /**
     * Sets the value of the nameMiddle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameMiddle(String value) {
        this.nameMiddle = value;
    }

    /**
     * Gets the value of the nameSur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameSur() {
        return nameSur;
    }

    /**
     * Sets the value of the nameSur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameSur(String value) {
        this.nameSur = value;
    }

    /**
     * Gets the value of the nameSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * Sets the value of the nameSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameSuffix(String value) {
        this.nameSuffix = value;
    }

    /**
     * Gets the value of the nameTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameTitle() {
        return nameTitle;
    }

    /**
     * Sets the value of the nameTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameTitle(String value) {
        this.nameTitle = value;
    }

    /**
     * Gets the value of the nameOrdered property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOrdered() {
        return nameOrdered;
    }

    /**
     * Sets the value of the nameOrdered property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOrdered(String value) {
        this.nameOrdered = value;
    }

}
