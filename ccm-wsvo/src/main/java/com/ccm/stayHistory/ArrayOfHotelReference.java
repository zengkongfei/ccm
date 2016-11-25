
package com.ccm.stayHistory;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfHotelReference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfHotelReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HotelReference" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/}StayInfo" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="hotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfHotelReference", propOrder = {
    "hotelReference"
})
public class ArrayOfHotelReference {

    @XmlElement(name = "HotelReference", nillable = true)
    protected List<ArrayOfHotelReference.HotelReference> hotelReference;

    /**
     * Gets the value of the hotelReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hotelReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHotelReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayOfHotelReference.HotelReference }
     * 
     * 
     */
    public List<ArrayOfHotelReference.HotelReference> getHotelReference() {
        if (hotelReference == null) {
            hotelReference = new ArrayList<ArrayOfHotelReference.HotelReference>();
        }
        return this.hotelReference;
    }


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
     *         &lt;element ref="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/}StayInfo" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="hotelCode" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stayInfo"
    })
    public static class HotelReference {

        @XmlElement(name = "StayInfo", namespace = "http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/")
        protected ArrayOfStayInfoDetail stayInfo;
        @XmlAttribute(name = "hotelCode")
        protected String hotelCode;

        /**
         * 获取stayInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfStayInfoDetail }
         *     
         */
        public ArrayOfStayInfoDetail getStayInfo() {
            return stayInfo;
        }

        /**
         * 设置stayInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfStayInfoDetail }
         *     
         */
        public void setStayInfo(ArrayOfStayInfoDetail value) {
            this.stayInfo = value;
        }

        /**
         * 获取hotelCode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHotelCode() {
            return hotelCode;
        }

        /**
         * 设置hotelCode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHotelCode(String value) {
            this.hotelCode = value;
        }

    }

}
