
package com.ccm.webservices.og.hotelcommon;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RoomTypeInventory complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomTypeInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomTypeRestriction" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Restriction" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="roomTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="totalRooms" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="overBookingLimit" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="soldDeductible" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="soldNonDeductible" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="totalAvailableRooms" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomTypeInventory", propOrder = {
    "roomTypeRestriction"
})
public class RoomTypeInventory {

    @XmlElement(name = "RoomTypeRestriction")
    protected List<Restriction> roomTypeRestriction;
    @XmlAttribute(name = "roomTypeCode")
    protected String roomTypeCode;
    @XmlAttribute(name = "totalRooms")
    protected BigInteger totalRooms;
    @XmlAttribute(name = "overBookingLimit")
    protected BigInteger overBookingLimit;
    @XmlAttribute(name = "soldDeductible")
    protected BigInteger soldDeductible;
    @XmlAttribute(name = "soldNonDeductible")
    protected BigInteger soldNonDeductible;
    @XmlAttribute(name = "totalAvailableRooms")
    protected BigInteger totalAvailableRooms;

    /**
     * Gets the value of the roomTypeRestriction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomTypeRestriction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomTypeRestriction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Restriction }
     * 
     * 
     */
    public List<Restriction> getRoomTypeRestriction() {
        if (roomTypeRestriction == null) {
            roomTypeRestriction = new ArrayList<Restriction>();
        }
        return this.roomTypeRestriction;
    }

    /**
     * 获取roomTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    /**
     * 设置roomTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeCode(String value) {
        this.roomTypeCode = value;
    }

    /**
     * 获取totalRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRooms() {
        return totalRooms;
    }

    /**
     * 设置totalRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRooms(BigInteger value) {
        this.totalRooms = value;
    }

    /**
     * 获取overBookingLimit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOverBookingLimit() {
        return overBookingLimit;
    }

    /**
     * 设置overBookingLimit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOverBookingLimit(BigInteger value) {
        this.overBookingLimit = value;
    }

    /**
     * 获取soldDeductible属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSoldDeductible() {
        return soldDeductible;
    }

    /**
     * 设置soldDeductible属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSoldDeductible(BigInteger value) {
        this.soldDeductible = value;
    }

    /**
     * 获取soldNonDeductible属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSoldNonDeductible() {
        return soldNonDeductible;
    }

    /**
     * 设置soldNonDeductible属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSoldNonDeductible(BigInteger value) {
        this.soldNonDeductible = value;
    }

    /**
     * 获取totalAvailableRooms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalAvailableRooms() {
        return totalAvailableRooms;
    }

    /**
     * 设置totalAvailableRooms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalAvailableRooms(BigInteger value) {
        this.totalAvailableRooms = value;
    }

}
