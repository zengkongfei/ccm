
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Provides detailed information regarding attractions.
 * 
 * <p>AttractionsType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AttractionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Attraction" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AttractionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="AttractionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="AttractionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="AttractionInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AttractionInfoList" minOccurs="0"/>
 *                   &lt;element name="Distance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="DistanceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DrivingTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="HoursOfOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="PriceRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DisplaySequence" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "AttractionsType", propOrder = {
    "attraction"
})
public class AttractionsType {

    @XmlElement(name = "Attraction")
    protected List<AttractionsType.Attraction> attraction;

    /**
     * Gets the value of the attraction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attraction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttraction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttractionsType.Attraction }
     * 
     * 
     */
    public List<AttractionsType.Attraction> getAttraction() {
        if (attraction == null) {
            attraction = new ArrayList<AttractionsType.Attraction>();
        }
        return this.attraction;
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
     *         &lt;element name="AttractionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="AttractionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="AttractionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="AttractionInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AttractionInfoList" minOccurs="0"/>
     *         &lt;element name="Distance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="DistanceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DrivingTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="HoursOfOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="PriceRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DisplaySequence" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
        "attractionCode",
        "attractionName",
        "attractionType",
        "attractionInformation",
        "distance",
        "distanceType",
        "drivingTime",
        "city",
        "state",
        "zipCode",
        "hoursOfOperation",
        "latitude",
        "longitude",
        "priceRange",
        "displaySequence"
    })
    public static class Attraction {

        @XmlElement(name = "AttractionCode")
        protected String attractionCode;
        @XmlElement(name = "AttractionName")
        protected String attractionName;
        @XmlElement(name = "AttractionType")
        protected String attractionType;
        @XmlElement(name = "AttractionInformation")
        protected AttractionInfoList attractionInformation;
        @XmlElement(name = "Distance")
        protected Double distance;
        @XmlElement(name = "DistanceType")
        protected String distanceType;
        @XmlElement(name = "DrivingTime")
        protected String drivingTime;
        @XmlElement(name = "City")
        protected String city;
        @XmlElement(name = "State")
        protected String state;
        @XmlElement(name = "ZipCode")
        protected String zipCode;
        @XmlElement(name = "HoursOfOperation")
        protected String hoursOfOperation;
        @XmlElement(name = "Latitude")
        protected Double latitude;
        @XmlElement(name = "Longitude")
        protected Double longitude;
        @XmlElement(name = "PriceRange")
        protected String priceRange;
        @XmlElement(name = "DisplaySequence")
        protected Integer displaySequence;

        /**
         * 获取attractionCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttractionCode() {
            return attractionCode;
        }

        /**
         * 设置attractionCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttractionCode(String value) {
            this.attractionCode = value;
        }

        /**
         * 获取attractionName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttractionName() {
            return attractionName;
        }

        /**
         * 设置attractionName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttractionName(String value) {
            this.attractionName = value;
        }

        /**
         * 获取attractionType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAttractionType() {
            return attractionType;
        }

        /**
         * 设置attractionType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAttractionType(String value) {
            this.attractionType = value;
        }

        /**
         * 获取attractionInformation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link AttractionInfoList }
         *     
         */
        public AttractionInfoList getAttractionInformation() {
            return attractionInformation;
        }

        /**
         * 设置attractionInformation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link AttractionInfoList }
         *     
         */
        public void setAttractionInformation(AttractionInfoList value) {
            this.attractionInformation = value;
        }

        /**
         * 获取distance属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getDistance() {
            return distance;
        }

        /**
         * 设置distance属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setDistance(Double value) {
            this.distance = value;
        }

        /**
         * 获取distanceType属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDistanceType() {
            return distanceType;
        }

        /**
         * 设置distanceType属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDistanceType(String value) {
            this.distanceType = value;
        }

        /**
         * 获取drivingTime属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDrivingTime() {
            return drivingTime;
        }

        /**
         * 设置drivingTime属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDrivingTime(String value) {
            this.drivingTime = value;
        }

        /**
         * 获取city属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCity() {
            return city;
        }

        /**
         * 设置city属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCity(String value) {
            this.city = value;
        }

        /**
         * 获取state属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getState() {
            return state;
        }

        /**
         * 设置state属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setState(String value) {
            this.state = value;
        }

        /**
         * 获取zipCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZipCode() {
            return zipCode;
        }

        /**
         * 设置zipCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZipCode(String value) {
            this.zipCode = value;
        }

        /**
         * 获取hoursOfOperation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHoursOfOperation() {
            return hoursOfOperation;
        }

        /**
         * 设置hoursOfOperation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHoursOfOperation(String value) {
            this.hoursOfOperation = value;
        }

        /**
         * 获取latitude属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getLatitude() {
            return latitude;
        }

        /**
         * 设置latitude属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setLatitude(Double value) {
            this.latitude = value;
        }

        /**
         * 获取longitude属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getLongitude() {
            return longitude;
        }

        /**
         * 设置longitude属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setLongitude(Double value) {
            this.longitude = value;
        }

        /**
         * 获取priceRange属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPriceRange() {
            return priceRange;
        }

        /**
         * 设置priceRange属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPriceRange(String value) {
            this.priceRange = value;
        }

        /**
         * 获取displaySequence属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getDisplaySequence() {
            return displaySequence;
        }

        /**
         * 设置displaySequence属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setDisplaySequence(Integer value) {
            this.displaySequence = value;
        }

    }

}
