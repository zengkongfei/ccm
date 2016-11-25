
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>HotelInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="HotelInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText">
 *       &lt;attribute name="hotelInfoType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelInfoType" />
 *       &lt;attribute name="otherHotelInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelInfo")
public class HotelInfo
    extends DescriptiveText
{

    @XmlAttribute(name = "hotelInfoType", required = true)
    protected HotelInfoType hotelInfoType;
    @XmlAttribute(name = "otherHotelInfoType")
    protected String otherHotelInfoType;

    /**
     * 获取hotelInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelInfoType }
     *     
     */
    public HotelInfoType getHotelInfoType() {
        return hotelInfoType;
    }

    /**
     * 设置hotelInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInfoType }
     *     
     */
    public void setHotelInfoType(HotelInfoType value) {
        this.hotelInfoType = value;
    }

    /**
     * 获取otherHotelInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherHotelInfoType() {
        return otherHotelInfoType;
    }

    /**
     * 设置otherHotelInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherHotelInfoType(String value) {
        this.otherHotelInfoType = value;
    }

}
