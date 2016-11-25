
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * represents room upgrade on a hotel stay
 * 
 * <p>UpgradeRoom complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UpgradeRoom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="awardType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fromRoomCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toRoomCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pointsRequired" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pointsAvailable" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpgradeRoom", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "awardType",
    "fromRoomCategory",
    "toRoomCategory",
    "pointsRequired",
    "pointsAvailable"
})
public class UpgradeRoom {

    @XmlElement(required = true)
    protected String awardType;
    protected String fromRoomCategory;
    @XmlElement(required = true)
    protected String toRoomCategory;
    protected Double pointsRequired;
    protected Double pointsAvailable;

    /**
     * 获取awardType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwardType() {
        return awardType;
    }

    /**
     * 设置awardType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwardType(String value) {
        this.awardType = value;
    }

    /**
     * 获取fromRoomCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromRoomCategory() {
        return fromRoomCategory;
    }

    /**
     * 设置fromRoomCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromRoomCategory(String value) {
        this.fromRoomCategory = value;
    }

    /**
     * 获取toRoomCategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToRoomCategory() {
        return toRoomCategory;
    }

    /**
     * 设置toRoomCategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToRoomCategory(String value) {
        this.toRoomCategory = value;
    }

    /**
     * 获取pointsRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPointsRequired() {
        return pointsRequired;
    }

    /**
     * 设置pointsRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPointsRequired(Double value) {
        this.pointsRequired = value;
    }

    /**
     * 获取pointsAvailable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPointsAvailable() {
        return pointsAvailable;
    }

    /**
     * 设置pointsAvailable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPointsAvailable(Double value) {
        this.pointsAvailable = value;
    }

}
