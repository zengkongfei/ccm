
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ASBRateCycle complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ASBRateCycle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="rateCycleType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RateCycleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ASBRateCycle")
public class ASBRateCycle {

    @XmlAttribute(name = "rateCycleType", required = true)
    protected RateCycleType rateCycleType;

    /**
     * 获取rateCycleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateCycleType }
     *     
     */
    public RateCycleType getRateCycleType() {
        return rateCycleType;
    }

    /**
     * 设置rateCycleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateCycleType }
     *     
     */
    public void setRateCycleType(RateCycleType value) {
        this.rateCycleType = value;
    }

}
