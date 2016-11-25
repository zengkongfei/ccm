
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RoomType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomTypeDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RoomTypeShortDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RoomNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RoomToChargeDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="RoomToChargeShortDescription" type="{http://webservices.micros.com/og/4.3/HotelCommon/}Paragraph" minOccurs="0"/>
 *         &lt;element name="AmenityInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AmenityInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="roomTypeCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="feature" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="upgradeFromCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="invBlockCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="numberOfUnits" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="isRoom" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="roomTypeName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="roomToChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="roomToChargeName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomType", propOrder = {
    "roomTypeDescription",
    "roomTypeShortDescription",
    "roomNumber",
    "roomToChargeDescription",
    "roomToChargeShortDescription",
    "amenityInfo"
})
public class RoomType {

    @XmlElement(name = "RoomTypeDescription")
    protected Paragraph roomTypeDescription;
    @XmlElement(name = "RoomTypeShortDescription")
    protected Paragraph roomTypeShortDescription;
    @XmlElement(name = "RoomNumber")
    protected List<String> roomNumber;
    @XmlElement(name = "RoomToChargeDescription")
    protected Paragraph roomToChargeDescription;
    @XmlElement(name = "RoomToChargeShortDescription")
    protected Paragraph roomToChargeShortDescription;
    @XmlElement(name = "AmenityInfo")
    protected AmenityInfo amenityInfo;
    @XmlAttribute(name = "roomTypeCode", required = true)
    protected String roomTypeCode;
    @XmlAttribute(name = "feature")
    protected String feature;
    @XmlAttribute(name = "upgradeFromCode")
    protected String upgradeFromCode;
    @XmlAttribute(name = "invBlockCode")
    protected String invBlockCode;
    @XmlAttribute(name = "numberOfUnits")
    protected Integer numberOfUnits;
    @XmlAttribute(name = "isRoom")
    protected Boolean isRoom;
    @XmlAttribute(name = "effectiveDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar effectiveDate;
    @XmlAttribute(name = "expirationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "roomTypeName")
    protected String roomTypeName;
    @XmlAttribute(name = "roomToChargeCode")
    protected String roomToChargeCode;
    @XmlAttribute(name = "roomToChargeName")
    protected String roomToChargeName;

    /**
     * 获取roomTypeDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomTypeDescription() {
        return roomTypeDescription;
    }

    /**
     * 设置roomTypeDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomTypeDescription(Paragraph value) {
        this.roomTypeDescription = value;
    }

    /**
     * 获取roomTypeShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomTypeShortDescription() {
        return roomTypeShortDescription;
    }

    /**
     * 设置roomTypeShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomTypeShortDescription(Paragraph value) {
        this.roomTypeShortDescription = value;
    }

    /**
     * Gets the value of the roomNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRoomNumber() {
        if (roomNumber == null) {
            roomNumber = new ArrayList<String>();
        }
        return this.roomNumber;
    }

    /**
     * 获取roomToChargeDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomToChargeDescription() {
        return roomToChargeDescription;
    }

    /**
     * 设置roomToChargeDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomToChargeDescription(Paragraph value) {
        this.roomToChargeDescription = value;
    }

    /**
     * 获取roomToChargeShortDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Paragraph }
     *     
     */
    public Paragraph getRoomToChargeShortDescription() {
        return roomToChargeShortDescription;
    }

    /**
     * 设置roomToChargeShortDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraph }
     *     
     */
    public void setRoomToChargeShortDescription(Paragraph value) {
        this.roomToChargeShortDescription = value;
    }

    /**
     * 获取amenityInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AmenityInfo }
     *     
     */
    public AmenityInfo getAmenityInfo() {
        return amenityInfo;
    }

    /**
     * 设置amenityInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AmenityInfo }
     *     
     */
    public void setAmenityInfo(AmenityInfo value) {
        this.amenityInfo = value;
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
     * 获取feature属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeature() {
        return feature;
    }

    /**
     * 设置feature属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeature(String value) {
        this.feature = value;
    }

    /**
     * 获取upgradeFromCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpgradeFromCode() {
        return upgradeFromCode;
    }

    /**
     * 设置upgradeFromCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpgradeFromCode(String value) {
        this.upgradeFromCode = value;
    }

    /**
     * 获取invBlockCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvBlockCode() {
        return invBlockCode;
    }

    /**
     * 设置invBlockCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvBlockCode(String value) {
        this.invBlockCode = value;
    }

    /**
     * 获取numberOfUnits属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    /**
     * 设置numberOfUnits属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfUnits(Integer value) {
        this.numberOfUnits = value;
    }

    /**
     * 获取isRoom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRoom() {
        return isRoom;
    }

    /**
     * 设置isRoom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRoom(Boolean value) {
        this.isRoom = value;
    }

    /**
     * 获取effectiveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置effectiveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEffectiveDate(XMLGregorianCalendar value) {
        this.effectiveDate = value;
    }

    /**
     * 获取expirationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * 设置expirationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * 获取roomTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * 设置roomTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeName(String value) {
        this.roomTypeName = value;
    }

    /**
     * 获取roomToChargeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomToChargeCode() {
        return roomToChargeCode;
    }

    /**
     * 设置roomToChargeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomToChargeCode(String value) {
        this.roomToChargeCode = value;
    }

    /**
     * 获取roomToChargeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomToChargeName() {
        return roomToChargeName;
    }

    /**
     * 设置roomToChargeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomToChargeName(String value) {
        this.roomToChargeName = value;
    }

}
