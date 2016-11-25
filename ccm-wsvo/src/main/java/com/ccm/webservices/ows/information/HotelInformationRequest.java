
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.HotelReference;


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
 *         &lt;element name="HotelInformationQuery" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
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
    "hotelInformationQuery"
})
@XmlRootElement(name = "HotelInformationRequest")
public class HotelInformationRequest {

    @XmlElement(name = "HotelInformationQuery", required = true)
    protected HotelReference hotelInformationQuery;

    /**
     * 获取hotelInformationQuery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getHotelInformationQuery() {
        return hotelInformationQuery;
    }

    /**
     * 设置hotelInformationQuery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setHotelInformationQuery(HotelReference value) {
        this.hotelInformationQuery = value;
    }

}
