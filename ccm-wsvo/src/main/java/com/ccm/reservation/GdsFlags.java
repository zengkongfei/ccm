
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GdsFlags complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GdsFlags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LateArrivalTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancellationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarCancelFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsFlags", propOrder = {
    "lateArrivalTime",
    "cancellationCode",
    "guarCancelFlag"
})
public class GdsFlags {

    @XmlElement(name = "LateArrivalTime")
    protected String lateArrivalTime;
    @XmlElement(name = "CancellationCode")
    protected String cancellationCode;
    @XmlElement(name = "GuarCancelFlag")
    protected String guarCancelFlag;

    /**
     * 获取lateArrivalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateArrivalTime() {
        return lateArrivalTime;
    }

    /**
     * 设置lateArrivalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateArrivalTime(String value) {
        this.lateArrivalTime = value;
    }

    /**
     * 获取cancellationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancellationCode() {
        return cancellationCode;
    }

    /**
     * 设置cancellationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancellationCode(String value) {
        this.cancellationCode = value;
    }

    /**
     * 获取guarCancelFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarCancelFlag() {
        return guarCancelFlag;
    }

    /**
     * 设置guarCancelFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarCancelFlag(String value) {
        this.guarCancelFlag = value;
    }

}
