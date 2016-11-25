
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="HotelReference" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
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
    "hotelReference",
    "itemCode",
    "quantity",
    "stayDateRange"
})
@XmlRootElement(name = "CreateItemHoldRequest", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
public class CreateItemHoldRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "ItemCode", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected String itemCode;
    @XmlElement(name = "Quantity", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl")
    protected int quantity;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Reservation.wsdl", required = true)
    protected TimeSpan stayDateRange;

    /**
     * 获取hotelReference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelReference() {
        return hotelReference;
    }

    /**
     * 设置hotelReference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelReference(HotelReference value) {
        this.hotelReference = value;
    }

    /**
     * 获取itemCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置itemCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * 获取quantity属性的值。
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性的值。
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * 获取stayDateRange属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getStayDateRange() {
        return stayDateRange;
    }

    /**
     * 设置stayDateRange属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setStayDateRange(TimeSpan value) {
        this.stayDateRange = value;
    }

}
