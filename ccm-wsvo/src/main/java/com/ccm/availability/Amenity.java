
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Used to describe both room type and property amenities.
 * 
 * <p>Amenity complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Amenity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amenityDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="amenityCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amenityType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Property"/>
 *             &lt;enumeration value="Room"/>
 *             &lt;enumeration value="Both"/>
 *             &lt;enumeration value="Nearby"/>
 *             &lt;enumeration value="Exists"/>
 *             &lt;enumeration value="Other"/>
 *             &lt;enumeration value="Parking"/>
 *             &lt;enumeration value="WheelChairAccess"/>
 *             &lt;enumeration value="Gymnasium"/>
 *             &lt;enumeration value="ConferenceRoom"/>
 *             &lt;enumeration value="BusinessCentre"/>
 *             &lt;enumeration value="Pets"/>
 *             &lt;enumeration value="RoomService"/>
 *             &lt;enumeration value="Restaurant"/>
 *             &lt;enumeration value="SwimingPool"/>
 *             &lt;enumeration value="Internet"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="otherType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="availabilityFlag">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="ConfirmableComplimentary"/>
 *             &lt;enumeration value="ConfirmableCost"/>
 *             &lt;enumeration value="OnRequestComplimentary"/>
 *             &lt;enumeration value="OnRequestCost"/>
 *             &lt;enumeration value="Exists"/>
 *             &lt;enumeration value="Other"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="otherFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Amenity", propOrder = {
    "amenityDescription"
})
public class Amenity {

    protected List<String> amenityDescription;
    @XmlAttribute(name = "amenityCode", required = true)
    protected String amenityCode;
    @XmlAttribute(name = "amenityType")
    protected String amenityType;
    @XmlAttribute(name = "otherType")
    protected String otherType;
    @XmlAttribute(name = "availabilityFlag")
    protected String availabilityFlag;
    @XmlAttribute(name = "otherFlag")
    protected String otherFlag;

    /**
     * Gets the value of the amenityDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amenityDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmenityDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAmenityDescription() {
        if (amenityDescription == null) {
            amenityDescription = new ArrayList<String>();
        }
        return this.amenityDescription;
    }

    /**
     * 获取amenityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityCode() {
        return amenityCode;
    }

    /**
     * 设置amenityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityCode(String value) {
        this.amenityCode = value;
    }

    /**
     * 获取amenityType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityType() {
        return amenityType;
    }

    /**
     * 设置amenityType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityType(String value) {
        this.amenityType = value;
    }

    /**
     * 获取otherType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherType() {
        return otherType;
    }

    /**
     * 设置otherType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherType(String value) {
        this.otherType = value;
    }

    /**
     * 获取availabilityFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityFlag() {
        return availabilityFlag;
    }

    /**
     * 设置availabilityFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityFlag(String value) {
        this.availabilityFlag = value;
    }

    /**
     * 获取otherFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherFlag() {
        return otherFlag;
    }

    /**
     * 设置otherFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherFlag(String value) {
        this.otherFlag = value;
    }

}
