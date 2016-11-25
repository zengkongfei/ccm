
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CalendarDailyDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CalendarDailyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Rates" type="{http://webservices.micros.com/og/4.3/Availability/}CalendarRate" minOccurs="0"/>
 *         &lt;element name="Occupancy" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomTypeInventoryList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarDailyDetail", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "rates",
    "occupancy"
})
public class CalendarDailyDetail {

    @XmlElement(name = "Rates")
    protected CalendarRate rates;
    @XmlElement(name = "Occupancy", required = true)
    protected RoomTypeInventoryList occupancy;
    @XmlAttribute(name = "Date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * 获取rates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CalendarRate }
     *     
     */
    public CalendarRate getRates() {
        return rates;
    }

    /**
     * 设置rates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarRate }
     *     
     */
    public void setRates(CalendarRate value) {
        this.rates = value;
    }

    /**
     * 获取occupancy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomTypeInventoryList }
     *     
     */
    public RoomTypeInventoryList getOccupancy() {
        return occupancy;
    }

    /**
     * 设置occupancy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomTypeInventoryList }
     *     
     */
    public void setOccupancy(RoomTypeInventoryList value) {
        this.occupancy = value;
    }

    /**
     * 获取date属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * 设置date属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
