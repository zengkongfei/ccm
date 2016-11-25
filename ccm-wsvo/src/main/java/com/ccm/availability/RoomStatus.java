
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RoomStatus complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="NextReservationDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="RoomStatus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomStatusToDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="FrontOfficeStatus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomStatusFromDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="OccupancyCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HouseKeepingStatus" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HouseKeepingInspectionFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TurnDownYn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ServiceStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoomType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomStatus")
public class RoomStatus {

    @XmlAttribute(name = "NextReservationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar nextReservationDate;
    @XmlAttribute(name = "RoomStatus", required = true)
    protected String roomStatus;
    @XmlAttribute(name = "RoomStatusToDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar roomStatusToDate;
    @XmlAttribute(name = "FrontOfficeStatus", required = true)
    protected String frontOfficeStatus;
    @XmlAttribute(name = "RoomStatusFromDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar roomStatusFromDate;
    @XmlAttribute(name = "OccupancyCondition")
    protected String occupancyCondition;
    @XmlAttribute(name = "HouseKeepingStatus", required = true)
    protected String houseKeepingStatus;
    @XmlAttribute(name = "HouseKeepingInspectionFlag")
    protected String houseKeepingInspectionFlag;
    @XmlAttribute(name = "TurnDownYn")
    protected String turnDownYn;
    @XmlAttribute(name = "ServiceStatus")
    protected String serviceStatus;
    @XmlAttribute(name = "RoomNumber", required = true)
    protected String roomNumber;
    @XmlAttribute(name = "RoomType", required = true)
    protected String roomType;

    /**
     * 获取nextReservationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextReservationDate() {
        return nextReservationDate;
    }

    /**
     * 设置nextReservationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextReservationDate(XMLGregorianCalendar value) {
        this.nextReservationDate = value;
    }

    /**
     * 获取roomStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomStatus() {
        return roomStatus;
    }

    /**
     * 设置roomStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomStatus(String value) {
        this.roomStatus = value;
    }

    /**
     * 获取roomStatusToDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRoomStatusToDate() {
        return roomStatusToDate;
    }

    /**
     * 设置roomStatusToDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRoomStatusToDate(XMLGregorianCalendar value) {
        this.roomStatusToDate = value;
    }

    /**
     * 获取frontOfficeStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrontOfficeStatus() {
        return frontOfficeStatus;
    }

    /**
     * 设置frontOfficeStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrontOfficeStatus(String value) {
        this.frontOfficeStatus = value;
    }

    /**
     * 获取roomStatusFromDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRoomStatusFromDate() {
        return roomStatusFromDate;
    }

    /**
     * 设置roomStatusFromDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRoomStatusFromDate(XMLGregorianCalendar value) {
        this.roomStatusFromDate = value;
    }

    /**
     * 获取occupancyCondition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupancyCondition() {
        return occupancyCondition;
    }

    /**
     * 设置occupancyCondition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupancyCondition(String value) {
        this.occupancyCondition = value;
    }

    /**
     * 获取houseKeepingStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseKeepingStatus() {
        return houseKeepingStatus;
    }

    /**
     * 设置houseKeepingStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseKeepingStatus(String value) {
        this.houseKeepingStatus = value;
    }

    /**
     * 获取houseKeepingInspectionFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseKeepingInspectionFlag() {
        return houseKeepingInspectionFlag;
    }

    /**
     * 设置houseKeepingInspectionFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseKeepingInspectionFlag(String value) {
        this.houseKeepingInspectionFlag = value;
    }

    /**
     * 获取turnDownYn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurnDownYn() {
        return turnDownYn;
    }

    /**
     * 设置turnDownYn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurnDownYn(String value) {
        this.turnDownYn = value;
    }

    /**
     * 获取serviceStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /**
     * 设置serviceStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceStatus(String value) {
        this.serviceStatus = value;
    }

    /**
     * 获取roomNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 设置roomNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * 获取roomType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 设置roomType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomType(String value) {
        this.roomType = value;
    }

}
