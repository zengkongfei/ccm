
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the geographic boundaries within which to search for available hotels.
 * 
 * <p>RegionalSearch complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RegionalSearch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="GeoSearch" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GeoPosition"/>
 *         &lt;element name="CitySearch" type="{http://webservices.micros.com/og/4.3/Availability/}CityCode"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegionalSearch", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "geoSearch",
    "citySearch"
})
public class RegionalSearch {

    @XmlElement(name = "GeoSearch")
    protected GeoPosition geoSearch;
    @XmlElement(name = "CitySearch")
    protected CityCode citySearch;

    /**
     * 获取geoSearch属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoPosition }
     *     
     */
    public GeoPosition getGeoSearch() {
        return geoSearch;
    }

    /**
     * 设置geoSearch属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoPosition }
     *     
     */
    public void setGeoSearch(GeoPosition value) {
        this.geoSearch = value;
    }

    /**
     * 获取citySearch属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CityCode }
     *     
     */
    public CityCode getCitySearch() {
        return citySearch;
    }

    /**
     * 设置citySearch属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CityCode }
     *     
     */
    public void setCitySearch(CityCode value) {
        this.citySearch = value;
    }

}
