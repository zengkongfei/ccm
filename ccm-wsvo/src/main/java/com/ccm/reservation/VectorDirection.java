
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>VectorDirection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="VectorDirection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="vectorDirection" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}VectorDirectionType" />
 *       &lt;attribute name="otherVectorDirection" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VectorDirection")
public class VectorDirection {

    @XmlAttribute(name = "vectorDirection", required = true)
    protected VectorDirectionType vectorDirection;
    @XmlAttribute(name = "otherVectorDirection")
    protected String otherVectorDirection;

    /**
     * 获取vectorDirection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VectorDirectionType }
     *     
     */
    public VectorDirectionType getVectorDirection() {
        return vectorDirection;
    }

    /**
     * 设置vectorDirection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VectorDirectionType }
     *     
     */
    public void setVectorDirection(VectorDirectionType value) {
        this.vectorDirection = value;
    }

    /**
     * 获取otherVectorDirection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherVectorDirection() {
        return otherVectorDirection;
    }

    /**
     * 设置otherVectorDirection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherVectorDirection(String value) {
        this.otherVectorDirection = value;
    }

}
