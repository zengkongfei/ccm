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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for StayDateRange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StayDateRange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="HardDateRange">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="startDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="endDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="los" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SoftDateRange">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="minLos" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="maxLos" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="earliestEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="latestEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="earliestStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="latestStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                 &lt;attribute name="arrivalDOW" type="{http://www.micros.com/2002A}DOW" />
 *                 &lt;attribute name="departureDOW" type="{http://www.micros.com/2002A}DOW" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StayDateRange", propOrder = {
    "hardDateRange",
    "softDateRange"
})
public class StayDateRange {

    @XmlElement(name = "HardDateRange")
    protected StayDateRange.HardDateRange hardDateRange;
    @XmlElement(name = "SoftDateRange")
    protected StayDateRange.SoftDateRange softDateRange;

    /**
     * Gets the value of the hardDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link StayDateRange.HardDateRange }
     *     
     */
    public StayDateRange.HardDateRange getHardDateRange() {
        return hardDateRange;
    }

    /**
     * Sets the value of the hardDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayDateRange.HardDateRange }
     *     
     */
    public void setHardDateRange(StayDateRange.HardDateRange value) {
        this.hardDateRange = value;
    }

    /**
     * Gets the value of the softDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link StayDateRange.SoftDateRange }
     *     
     */
    public StayDateRange.SoftDateRange getSoftDateRange() {
        return softDateRange;
    }

    /**
     * Sets the value of the softDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayDateRange.SoftDateRange }
     *     
     */
    public void setSoftDateRange(StayDateRange.SoftDateRange value) {
        this.softDateRange = value;
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
     *       &lt;attribute name="startDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="endDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="los" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class HardDateRange {

        @XmlAttribute(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar startDate;
        @XmlAttribute(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar endDate;
        @XmlAttribute
        protected Integer los;

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getStartDate() {
            return startDate;
        }

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setStartDate(XMLGregorianCalendar value) {
            this.startDate = value;
        }

        /**
         * Gets the value of the endDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEndDate() {
            return endDate;
        }

        /**
         * Sets the value of the endDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEndDate(XMLGregorianCalendar value) {
            this.endDate = value;
        }

        /**
         * Gets the value of the los property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getLos() {
            return los;
        }

        /**
         * Sets the value of the los property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setLos(Integer value) {
            this.los = value;
        }

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
     *       &lt;attribute name="minLos" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="maxLos" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="earliestEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="latestEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="earliestStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="latestStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *       &lt;attribute name="arrivalDOW" type="{http://www.micros.com/2002A}DOW" />
     *       &lt;attribute name="departureDOW" type="{http://www.micros.com/2002A}DOW" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SoftDateRange {

        @XmlAttribute
        protected Integer minLos;
        @XmlAttribute
        protected Integer maxLos;
        @XmlAttribute
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar earliestEndDate;
        @XmlAttribute
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar latestEndDate;
        @XmlAttribute
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar earliestStartDate;
        @XmlAttribute
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar latestStartDate;
        @XmlAttribute
        protected DOW arrivalDOW;
        @XmlAttribute
        protected DOW departureDOW;

        /**
         * Gets the value of the minLos property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getMinLos() {
            return minLos;
        }

        /**
         * Sets the value of the minLos property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMinLos(Integer value) {
            this.minLos = value;
        }

        /**
         * Gets the value of the maxLos property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getMaxLos() {
            return maxLos;
        }

        /**
         * Sets the value of the maxLos property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMaxLos(Integer value) {
            this.maxLos = value;
        }

        /**
         * Gets the value of the earliestEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEarliestEndDate() {
            return earliestEndDate;
        }

        /**
         * Sets the value of the earliestEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEarliestEndDate(XMLGregorianCalendar value) {
            this.earliestEndDate = value;
        }

        /**
         * Gets the value of the latestEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getLatestEndDate() {
            return latestEndDate;
        }

        /**
         * Sets the value of the latestEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setLatestEndDate(XMLGregorianCalendar value) {
            this.latestEndDate = value;
        }

        /**
         * Gets the value of the earliestStartDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEarliestStartDate() {
            return earliestStartDate;
        }

        /**
         * Sets the value of the earliestStartDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEarliestStartDate(XMLGregorianCalendar value) {
            this.earliestStartDate = value;
        }

        /**
         * Gets the value of the latestStartDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getLatestStartDate() {
            return latestStartDate;
        }

        /**
         * Sets the value of the latestStartDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setLatestStartDate(XMLGregorianCalendar value) {
            this.latestStartDate = value;
        }

        /**
         * Gets the value of the arrivalDOW property.
         * 
         * @return
         *     possible object is
         *     {@link DOW }
         *     
         */
        public DOW getArrivalDOW() {
            return arrivalDOW;
        }

        /**
         * Sets the value of the arrivalDOW property.
         * 
         * @param value
         *     allowed object is
         *     {@link DOW }
         *     
         */
        public void setArrivalDOW(DOW value) {
            this.arrivalDOW = value;
        }

        /**
         * Gets the value of the departureDOW property.
         * 
         * @return
         *     possible object is
         *     {@link DOW }
         *     
         */
        public DOW getDepartureDOW() {
            return departureDOW;
        }

        /**
         * Sets the value of the departureDOW property.
         * 
         * @param value
         *     allowed object is
         *     {@link DOW }
         *     
         */
        public void setDepartureDOW(DOW value) {
            this.departureDOW = value;
        }

    }

}
