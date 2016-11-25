
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CalendarRate complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CalendarRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RestrictionList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Restriction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Restriction" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RateList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Rate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomRate" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarRate", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "restrictionList",
    "rateList"
})
public class CalendarRate {

    @XmlElement(name = "RestrictionList")
    protected CalendarRate.RestrictionList restrictionList;
    @XmlElement(name = "RateList", required = true)
    protected CalendarRate.RateList rateList;

    /**
     * 获取restrictionList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CalendarRate.RestrictionList }
     *     
     */
    public CalendarRate.RestrictionList getRestrictionList() {
        return restrictionList;
    }

    /**
     * 设置restrictionList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarRate.RestrictionList }
     *     
     */
    public void setRestrictionList(CalendarRate.RestrictionList value) {
        this.restrictionList = value;
    }

    /**
     * 获取rateList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CalendarRate.RateList }
     *     
     */
    public CalendarRate.RateList getRateList() {
        return rateList;
    }

    /**
     * 设置rateList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarRate.RateList }
     *     
     */
    public void setRateList(CalendarRate.RateList value) {
        this.rateList = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Rate" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomRate" maxOccurs="unbounded"/>
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
        "rate"
    })
    public static class RateList {

        @XmlElement(name = "Rate", namespace = "http://webservices.micros.com/og/4.3/Availability/", required = true)
        protected List<RoomRate> rate;

        /**
         * Gets the value of the rate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RoomRate }
         * 
         * 
         */
        public List<RoomRate> getRate() {
            if (rate == null) {
                rate = new ArrayList<RoomRate>();
            }
            return this.rate;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Restriction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Restriction" maxOccurs="unbounded" minOccurs="0"/>
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
        "restriction"
    })
    public static class RestrictionList {

        @XmlElement(name = "Restriction", namespace = "http://webservices.micros.com/og/4.3/Availability/")
        protected List<Restriction> restriction;

        /**
         * Gets the value of the restriction property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the restriction property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRestriction().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Restriction }
         * 
         * 
         */
        public List<Restriction> getRestriction() {
            if (restriction == null) {
                restriction = new ArrayList<Restriction>();
            }
            return this.restriction;
        }

    }

}
