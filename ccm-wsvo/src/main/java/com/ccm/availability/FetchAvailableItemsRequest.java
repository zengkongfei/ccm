
package com.ccm.availability;

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
 *         &lt;element name="StayDateRange" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan"/>
 *         &lt;element name="InventoryItem" type="{http://webservices.micros.com/og/4.3/HotelCommon/}InventoryItemElement"/>
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
    "stayDateRange",
    "inventoryItem"
})
@XmlRootElement(name = "FetchAvailableItemsRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class FetchAvailableItemsRequest {

    @XmlElement(name = "HotelReference", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected HotelReference hotelReference;
    @XmlElement(name = "StayDateRange", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected TimeSpan stayDateRange;
    @XmlElement(name = "InventoryItem", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected InventoryItemElement inventoryItem;

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

    /**
     * 获取inventoryItem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link InventoryItemElement }
     *     
     */
    public InventoryItemElement getInventoryItem() {
        return inventoryItem;
    }

    /**
     * 设置inventoryItem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryItemElement }
     *     
     */
    public void setInventoryItem(InventoryItemElement value) {
        this.inventoryItem = value;
    }

}
