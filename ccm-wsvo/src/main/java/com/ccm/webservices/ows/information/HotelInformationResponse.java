
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.ExtendedHotelInfo;
import com.ccm.webservices.og.hotelcommon.GDSResultStatus;
import com.ccm.webservices.og.hotelcommon.HotelContact;
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
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="HotelInformation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
 *                   &lt;element name="HotelContactInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelContact" minOccurs="0"/>
 *                   &lt;element name="HotelExtendedInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ExtendedHotelInfo" minOccurs="0"/>
 *                 &lt;/sequence>
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
@XmlType(name = "", propOrder = {
    "result",
    "hotelInformation"
})
@XmlRootElement(name = "HotelInformationResponse")
public class HotelInformationResponse {

    @XmlElement(name = "Result", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "HotelInformation", required = true)
    protected HotelInformationResponse.HotelInformation hotelInformation;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * 获取hotelInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelInformationResponse.HotelInformation }
     *     
     */
    public HotelInformationResponse.HotelInformation getHotelInformation() {
        return hotelInformation;
    }

    /**
     * 设置hotelInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInformationResponse.HotelInformation }
     *     
     */
    public void setHotelInformation(HotelInformationResponse.HotelInformation value) {
        this.hotelInformation = value;
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
     *         &lt;element name="HotelInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference" minOccurs="0"/>
     *         &lt;element name="HotelContactInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelContact" minOccurs="0"/>
     *         &lt;element name="HotelExtendedInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ExtendedHotelInfo" minOccurs="0"/>
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
        "hotelInformation",
        "hotelContactInformation",
        "hotelExtendedInformation"
    })
    public static class HotelInformation {

        @XmlElement(name = "HotelInformation")
        protected HotelReference hotelInformation;
        @XmlElement(name = "HotelContactInformation")
        protected HotelContact hotelContactInformation;
        @XmlElement(name = "HotelExtendedInformation")
        protected ExtendedHotelInfo hotelExtendedInformation;

        /**
         * 获取hotelInformation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link HotelReference }
         *     
         */
        public HotelReference getHotelInformation() {
            return hotelInformation;
        }

        /**
         * 设置hotelInformation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link HotelReference }
         *     
         */
        public void setHotelInformation(HotelReference value) {
            this.hotelInformation = value;
        }

        /**
         * 获取hotelContactInformation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link HotelContact }
         *     
         */
        public HotelContact getHotelContactInformation() {
            return hotelContactInformation;
        }

        /**
         * 设置hotelContactInformation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link HotelContact }
         *     
         */
        public void setHotelContactInformation(HotelContact value) {
            this.hotelContactInformation = value;
        }

        /**
         * 获取hotelExtendedInformation属性的值。
         * 
         * @return
         *     possible object is
         *     {@link ExtendedHotelInfo }
         *     
         */
        public ExtendedHotelInfo getHotelExtendedInformation() {
            return hotelExtendedInformation;
        }

        /**
         * 设置hotelExtendedInformation属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link ExtendedHotelInfo }
         *     
         */
        public void setHotelExtendedInformation(ExtendedHotelInfo value) {
            this.hotelExtendedInformation = value;
        }

    }

}
