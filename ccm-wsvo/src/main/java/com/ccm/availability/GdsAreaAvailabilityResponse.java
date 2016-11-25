
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
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="AreaAvailableProperties" type="{http://webservices.micros.com/og/4.3/Availability/}AreaAvailableProperties" minOccurs="0"/>
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
    "areaAvailableProperties"
})
@XmlRootElement(name = "GdsAreaAvailabilityResponse", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class GdsAreaAvailabilityResponse {

    @XmlElement(name = "Result", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "AreaAvailableProperties", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected AreaAvailableProperties areaAvailableProperties;

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
     * 获取areaAvailableProperties属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AreaAvailableProperties }
     *     
     */
    public AreaAvailableProperties getAreaAvailableProperties() {
        return areaAvailableProperties;
    }

    /**
     * 设置areaAvailableProperties属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AreaAvailableProperties }
     *     
     */
    public void setAreaAvailableProperties(AreaAvailableProperties value) {
        this.areaAvailableProperties = value;
    }

}
