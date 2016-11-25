//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.21 at 04:13:21 下午 CST 
//


package com.ccm.oxi.rate_2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RateDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="roomTypeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="marketCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packages" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rate1" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="rate2" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="rate3" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="rate4" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="rate5" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendRate1" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendRate2" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendRate3" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendRate4" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendRate5" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendChild" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child1RateParentRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child2RateParentRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child3RateParentRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child1RateOwnRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child2RateOwnRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child3RateOwnRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="child4RateOwnRoom" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="crib" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendCrib" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="extraBed" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="weekendExtraBed" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="stayAdjustment" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="personAdjustment" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="nightAdjustment" type="{rate.fidelio.2.0}fixed144" minOccurs="0"/>
 *         &lt;element name="RateDaysOfWeek" type="{rate.fidelio.2.0}RateDaysOfWeek" minOccurs="0"/>
 *         &lt;element name="tierMinLos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tierMaxLos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="YieldAdjustments" type="{rate.fidelio.2.0}YieldAdjustments" minOccurs="0"/>
 *         &lt;element name="rateSetId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="externalRateSetId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "RateDetail", propOrder = {
    "rateCode",
    "startDate",
    "endDate",
    "roomTypeList",
    "marketCode",
    "sourceCode",
    "packages",
    "rate1",
    "rate2",
    "rate3",
    "rate4",
    "rate5",
    "weekendRate1",
    "weekendRate2",
    "weekendRate3",
    "weekendRate4",
    "weekendRate5",
    "child",
    "weekendChild",
    "child1RateParentRoom",
    "child2RateParentRoom",
    "child3RateParentRoom",
    "child1RateOwnRoom",
    "child2RateOwnRoom",
    "child3RateOwnRoom",
    "child4RateOwnRoom",
    "crib",
    "weekendCrib",
    "extraBed",
    "weekendExtraBed",
    "stayAdjustment",
    "personAdjustment",
    "nightAdjustment",
    "rateDaysOfWeek",
    "tierMinLos",
    "tierMaxLos",
    "yieldAdjustments",
    "rateSetId",
    "externalRateSetId"
})
public class RateDetail {

    @XmlElement(required = true)
    protected String rateCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected XMLGregorianCalendar endDate;
    protected List<String> roomTypeList;
    protected String marketCode;
    protected String sourceCode;
    protected List<String> packages;
    protected BigDecimal rate1;
    protected BigDecimal rate2;
    protected BigDecimal rate3;
    protected BigDecimal rate4;
    protected BigDecimal rate5;
    protected BigDecimal weekendRate1;
    protected BigDecimal weekendRate2;
    protected BigDecimal weekendRate3;
    protected BigDecimal weekendRate4;
    protected BigDecimal weekendRate5;
    protected BigDecimal child;
    protected BigDecimal weekendChild;
    protected BigDecimal child1RateParentRoom;
    protected BigDecimal child2RateParentRoom;
    protected BigDecimal child3RateParentRoom;
    protected BigDecimal child1RateOwnRoom;
    protected BigDecimal child2RateOwnRoom;
    protected BigDecimal child3RateOwnRoom;
    protected BigDecimal child4RateOwnRoom;
    protected BigDecimal crib;
    protected BigDecimal weekendCrib;
    protected BigDecimal extraBed;
    protected BigDecimal weekendExtraBed;
    protected BigDecimal stayAdjustment;
    protected BigDecimal personAdjustment;
    protected BigDecimal nightAdjustment;
    @XmlElement(name = "RateDaysOfWeek")
    protected RateDaysOfWeek rateDaysOfWeek;
    protected Integer tierMinLos;
    protected Integer tierMaxLos;
    @XmlElement(name = "YieldAdjustments")
    protected YieldAdjustments yieldAdjustments;
    protected Integer rateSetId;
    protected Integer externalRateSetId;
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mfInactiveDate;

    /**
     * Gets the value of the rateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * Sets the value of the rateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

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
     * Gets the value of the roomTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoomTypeList() {
        if (roomTypeList == null) {
            roomTypeList = new ArrayList<String>();
        }
        return this.roomTypeList;
    }

    /**
     * Gets the value of the marketCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * Sets the value of the marketCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketCode(String value) {
        this.marketCode = value;
    }

    /**
     * Gets the value of the sourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Sets the value of the sourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceCode(String value) {
        this.sourceCode = value;
    }

    /**
     * Gets the value of the packages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPackages() {
        if (packages == null) {
            packages = new ArrayList<String>();
        }
        return this.packages;
    }

    /**
     * Gets the value of the rate1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate1() {
        return rate1;
    }

    /**
     * Sets the value of the rate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate1(BigDecimal value) {
        this.rate1 = value;
    }

    /**
     * Gets the value of the rate2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate2() {
        return rate2;
    }

    /**
     * Sets the value of the rate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate2(BigDecimal value) {
        this.rate2 = value;
    }

    /**
     * Gets the value of the rate3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate3() {
        return rate3;
    }

    /**
     * Sets the value of the rate3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate3(BigDecimal value) {
        this.rate3 = value;
    }

    /**
     * Gets the value of the rate4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate4() {
        return rate4;
    }

    /**
     * Sets the value of the rate4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate4(BigDecimal value) {
        this.rate4 = value;
    }

    /**
     * Gets the value of the rate5 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate5() {
        return rate5;
    }

    /**
     * Sets the value of the rate5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate5(BigDecimal value) {
        this.rate5 = value;
    }

    /**
     * Gets the value of the weekendRate1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendRate1() {
        return weekendRate1;
    }

    /**
     * Sets the value of the weekendRate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendRate1(BigDecimal value) {
        this.weekendRate1 = value;
    }

    /**
     * Gets the value of the weekendRate2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendRate2() {
        return weekendRate2;
    }

    /**
     * Sets the value of the weekendRate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendRate2(BigDecimal value) {
        this.weekendRate2 = value;
    }

    /**
     * Gets the value of the weekendRate3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendRate3() {
        return weekendRate3;
    }

    /**
     * Sets the value of the weekendRate3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendRate3(BigDecimal value) {
        this.weekendRate3 = value;
    }

    /**
     * Gets the value of the weekendRate4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendRate4() {
        return weekendRate4;
    }

    /**
     * Sets the value of the weekendRate4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendRate4(BigDecimal value) {
        this.weekendRate4 = value;
    }

    /**
     * Gets the value of the weekendRate5 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendRate5() {
        return weekendRate5;
    }

    /**
     * Sets the value of the weekendRate5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendRate5(BigDecimal value) {
        this.weekendRate5 = value;
    }

    /**
     * Gets the value of the child property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild() {
        return child;
    }

    /**
     * Sets the value of the child property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild(BigDecimal value) {
        this.child = value;
    }

    /**
     * Gets the value of the weekendChild property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendChild() {
        return weekendChild;
    }

    /**
     * Sets the value of the weekendChild property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendChild(BigDecimal value) {
        this.weekendChild = value;
    }

    /**
     * Gets the value of the child1RateParentRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild1RateParentRoom() {
        return child1RateParentRoom;
    }

    /**
     * Sets the value of the child1RateParentRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild1RateParentRoom(BigDecimal value) {
        this.child1RateParentRoom = value;
    }

    /**
     * Gets the value of the child2RateParentRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild2RateParentRoom() {
        return child2RateParentRoom;
    }

    /**
     * Sets the value of the child2RateParentRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild2RateParentRoom(BigDecimal value) {
        this.child2RateParentRoom = value;
    }

    /**
     * Gets the value of the child3RateParentRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild3RateParentRoom() {
        return child3RateParentRoom;
    }

    /**
     * Sets the value of the child3RateParentRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild3RateParentRoom(BigDecimal value) {
        this.child3RateParentRoom = value;
    }

    /**
     * Gets the value of the child1RateOwnRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild1RateOwnRoom() {
        return child1RateOwnRoom;
    }

    /**
     * Sets the value of the child1RateOwnRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild1RateOwnRoom(BigDecimal value) {
        this.child1RateOwnRoom = value;
    }

    /**
     * Gets the value of the child2RateOwnRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild2RateOwnRoom() {
        return child2RateOwnRoom;
    }

    /**
     * Sets the value of the child2RateOwnRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild2RateOwnRoom(BigDecimal value) {
        this.child2RateOwnRoom = value;
    }

    /**
     * Gets the value of the child3RateOwnRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild3RateOwnRoom() {
        return child3RateOwnRoom;
    }

    /**
     * Sets the value of the child3RateOwnRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild3RateOwnRoom(BigDecimal value) {
        this.child3RateOwnRoom = value;
    }

    /**
     * Gets the value of the child4RateOwnRoom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChild4RateOwnRoom() {
        return child4RateOwnRoom;
    }

    /**
     * Sets the value of the child4RateOwnRoom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChild4RateOwnRoom(BigDecimal value) {
        this.child4RateOwnRoom = value;
    }

    /**
     * Gets the value of the crib property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCrib() {
        return crib;
    }

    /**
     * Sets the value of the crib property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCrib(BigDecimal value) {
        this.crib = value;
    }

    /**
     * Gets the value of the weekendCrib property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendCrib() {
        return weekendCrib;
    }

    /**
     * Sets the value of the weekendCrib property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendCrib(BigDecimal value) {
        this.weekendCrib = value;
    }

    /**
     * Gets the value of the extraBed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExtraBed() {
        return extraBed;
    }

    /**
     * Sets the value of the extraBed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExtraBed(BigDecimal value) {
        this.extraBed = value;
    }

    /**
     * Gets the value of the weekendExtraBed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeekendExtraBed() {
        return weekendExtraBed;
    }

    /**
     * Sets the value of the weekendExtraBed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeekendExtraBed(BigDecimal value) {
        this.weekendExtraBed = value;
    }

    /**
     * Gets the value of the stayAdjustment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStayAdjustment() {
        return stayAdjustment;
    }

    /**
     * Sets the value of the stayAdjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStayAdjustment(BigDecimal value) {
        this.stayAdjustment = value;
    }

    /**
     * Gets the value of the personAdjustment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPersonAdjustment() {
        return personAdjustment;
    }

    /**
     * Sets the value of the personAdjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPersonAdjustment(BigDecimal value) {
        this.personAdjustment = value;
    }

    /**
     * Gets the value of the nightAdjustment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNightAdjustment() {
        return nightAdjustment;
    }

    /**
     * Sets the value of the nightAdjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNightAdjustment(BigDecimal value) {
        this.nightAdjustment = value;
    }

    /**
     * Gets the value of the rateDaysOfWeek property.
     * 
     * @return
     *     possible object is
     *     {@link RateDaysOfWeek }
     *     
     */
    public RateDaysOfWeek getRateDaysOfWeek() {
        return rateDaysOfWeek;
    }

    /**
     * Sets the value of the rateDaysOfWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateDaysOfWeek }
     *     
     */
    public void setRateDaysOfWeek(RateDaysOfWeek value) {
        this.rateDaysOfWeek = value;
    }

    /**
     * Gets the value of the tierMinLos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTierMinLos() {
        return tierMinLos;
    }

    /**
     * Sets the value of the tierMinLos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTierMinLos(Integer value) {
        this.tierMinLos = value;
    }

    /**
     * Gets the value of the tierMaxLos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTierMaxLos() {
        return tierMaxLos;
    }

    /**
     * Sets the value of the tierMaxLos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTierMaxLos(Integer value) {
        this.tierMaxLos = value;
    }

    /**
     * Gets the value of the yieldAdjustments property.
     * 
     * @return
     *     possible object is
     *     {@link YieldAdjustments }
     *     
     */
    public YieldAdjustments getYieldAdjustments() {
        return yieldAdjustments;
    }

    /**
     * Sets the value of the yieldAdjustments property.
     * 
     * @param value
     *     allowed object is
     *     {@link YieldAdjustments }
     *     
     */
    public void setYieldAdjustments(YieldAdjustments value) {
        this.yieldAdjustments = value;
    }

    /**
     * Gets the value of the rateSetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRateSetId() {
        return rateSetId;
    }

    /**
     * Sets the value of the rateSetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRateSetId(Integer value) {
        this.rateSetId = value;
    }

    /**
     * Gets the value of the externalRateSetId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExternalRateSetId() {
        return externalRateSetId;
    }

    /**
     * Sets the value of the externalRateSetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExternalRateSetId(Integer value) {
        this.externalRateSetId = value;
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
