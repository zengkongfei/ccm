
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>StayHistoryData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StayHistoryData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastStay" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TotalNumberOfStays" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="LastRoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastRate" type="{http://webservices.micros.com/og/4.3/Common/}Amount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StayHistoryData", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "lastStay",
    "totalNumberOfStays",
    "lastRoomNumber",
    "lastRate"
})
public class StayHistoryData {

    @XmlElement(name = "LastStay")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastStay;
    @XmlElement(name = "TotalNumberOfStays")
    @XmlSchemaType(name = "unsignedInt")
    protected Long totalNumberOfStays;
    @XmlElement(name = "LastRoomNumber")
    protected String lastRoomNumber;
    @XmlElement(name = "LastRate")
    protected Amount lastRate;

    /**
     * 获取lastStay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastStay() {
        return lastStay;
    }

    /**
     * 设置lastStay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastStay(XMLGregorianCalendar value) {
        this.lastStay = value;
    }

    /**
     * 获取totalNumberOfStays属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalNumberOfStays() {
        return totalNumberOfStays;
    }

    /**
     * 设置totalNumberOfStays属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalNumberOfStays(Long value) {
        this.totalNumberOfStays = value;
    }

    /**
     * 获取lastRoomNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastRoomNumber() {
        return lastRoomNumber;
    }

    /**
     * 设置lastRoomNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastRoomNumber(String value) {
        this.lastRoomNumber = value;
    }

    /**
     * 获取lastRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getLastRate() {
        return lastRate;
    }

    /**
     * 设置lastRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setLastRate(Amount value) {
        this.lastRate = value;
    }

}
