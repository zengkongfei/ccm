//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.14 at 01:53:08  GMT+08:00 
//


package org.opentravel.ota._2003._05;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Service and vehicle information.
 * 
 * <p>Java class for GroundServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroundServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Language" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="Language" type="{http://www.w3.org/2001/XMLSchema}language" />
 *                 &lt;attribute name="PrimaryLangInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="RequestedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="ChildCarSeatInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PetFriendlyInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="FuelEfficientVehInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MaximumPassengers" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="MaximumBaggage" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="GreetingSignInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GreetingSignName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MeetAndGreetInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PersonalGreeterInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="GuideInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ShortDescription">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="0"/>
 *             &lt;maxLength value="250"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MultilingualInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroundServiceType", propOrder = {
    "language"
})
@XmlSeeAlso({
    GroundServiceDetailType.class
})
public class GroundServiceType {

    @XmlElement(name = "Language")
    protected List<GroundServiceType.Language> language;
    @XmlAttribute(name = "ChildCarSeatInd")
    protected Boolean childCarSeatInd;
    @XmlAttribute(name = "PetFriendlyInd")
    protected Boolean petFriendlyInd;
    @XmlAttribute(name = "FuelEfficientVehInd")
    protected Boolean fuelEfficientVehInd;
    @XmlAttribute(name = "MaximumPassengers")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maximumPassengers;
    @XmlAttribute(name = "MaximumBaggage")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maximumBaggage;
    @XmlAttribute(name = "GreetingSignInd")
    protected Boolean greetingSignInd;
    @XmlAttribute(name = "GreetingSignName")
    protected String greetingSignName;
    @XmlAttribute(name = "MeetAndGreetInd")
    protected Boolean meetAndGreetInd;
    @XmlAttribute(name = "PersonalGreeterInd")
    protected Boolean personalGreeterInd;
    @XmlAttribute(name = "GuideInd")
    protected Boolean guideInd;
    @XmlAttribute(name = "ShortDescription")
    protected String shortDescription;
    @XmlAttribute(name = "Notes")
    protected String notes;
    @XmlAttribute(name = "MultilingualInd")
    protected Boolean multilingualInd;

    /**
     * Gets the value of the language property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the language property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroundServiceType.Language }
     * 
     * 
     */
    public List<GroundServiceType.Language> getLanguage() {
        if (language == null) {
            language = new ArrayList<GroundServiceType.Language>();
        }
        return this.language;
    }

    /**
     * Gets the value of the childCarSeatInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChildCarSeatInd() {
        return childCarSeatInd;
    }

    /**
     * Sets the value of the childCarSeatInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChildCarSeatInd(Boolean value) {
        this.childCarSeatInd = value;
    }

    /**
     * Gets the value of the petFriendlyInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPetFriendlyInd() {
        return petFriendlyInd;
    }

    /**
     * Sets the value of the petFriendlyInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPetFriendlyInd(Boolean value) {
        this.petFriendlyInd = value;
    }

    /**
     * Gets the value of the fuelEfficientVehInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFuelEfficientVehInd() {
        return fuelEfficientVehInd;
    }

    /**
     * Sets the value of the fuelEfficientVehInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFuelEfficientVehInd(Boolean value) {
        this.fuelEfficientVehInd = value;
    }

    /**
     * Gets the value of the maximumPassengers property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumPassengers() {
        return maximumPassengers;
    }

    /**
     * Sets the value of the maximumPassengers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumPassengers(BigInteger value) {
        this.maximumPassengers = value;
    }

    /**
     * Gets the value of the maximumBaggage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumBaggage() {
        return maximumBaggage;
    }

    /**
     * Sets the value of the maximumBaggage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumBaggage(BigInteger value) {
        this.maximumBaggage = value;
    }

    /**
     * Gets the value of the greetingSignInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGreetingSignInd() {
        return greetingSignInd;
    }

    /**
     * Sets the value of the greetingSignInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGreetingSignInd(Boolean value) {
        this.greetingSignInd = value;
    }

    /**
     * Gets the value of the greetingSignName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGreetingSignName() {
        return greetingSignName;
    }

    /**
     * Sets the value of the greetingSignName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGreetingSignName(String value) {
        this.greetingSignName = value;
    }

    /**
     * Gets the value of the meetAndGreetInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMeetAndGreetInd() {
        return meetAndGreetInd;
    }

    /**
     * Sets the value of the meetAndGreetInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMeetAndGreetInd(Boolean value) {
        this.meetAndGreetInd = value;
    }

    /**
     * Gets the value of the personalGreeterInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPersonalGreeterInd() {
        return personalGreeterInd;
    }

    /**
     * Sets the value of the personalGreeterInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPersonalGreeterInd(Boolean value) {
        this.personalGreeterInd = value;
    }

    /**
     * Gets the value of the guideInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGuideInd() {
        return guideInd;
    }

    /**
     * Sets the value of the guideInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGuideInd(Boolean value) {
        this.guideInd = value;
    }

    /**
     * Gets the value of the shortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the value of the shortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the multilingualInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultilingualInd() {
        return multilingualInd;
    }

    /**
     * Sets the value of the multilingualInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultilingualInd(Boolean value) {
        this.multilingualInd = value;
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
     *       &lt;attribute name="Language" type="{http://www.w3.org/2001/XMLSchema}language" />
     *       &lt;attribute name="PrimaryLangInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="RequestedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Language {

        @XmlAttribute(name = "Language")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "language")
        protected String language;
        @XmlAttribute(name = "PrimaryLangInd")
        protected Boolean primaryLangInd;
        @XmlAttribute(name = "RequestedInd")
        protected Boolean requestedInd;

        /**
         * Gets the value of the language property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Sets the value of the language property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguage(String value) {
            this.language = value;
        }

        /**
         * Gets the value of the primaryLangInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isPrimaryLangInd() {
            return primaryLangInd;
        }

        /**
         * Sets the value of the primaryLangInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setPrimaryLangInd(Boolean value) {
            this.primaryLangInd = value;
        }

        /**
         * Gets the value of the requestedInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequestedInd() {
            return requestedInd;
        }

        /**
         * Sets the value of the requestedInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequestedInd(Boolean value) {
            this.requestedInd = value;
        }

    }

}