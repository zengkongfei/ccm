
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * represents the breakdown of TSC numbers
 * 
 * <p>Tsc complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Tsc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="baseRevenue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="bonusRevenue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="baseNights" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="bonusNights" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="baseStay" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="bonusStay" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tsc", namespace = "http://webservices.micros.com/og/4.3/Membership/")
public class Tsc {

    @XmlAttribute(name = "baseRevenue")
    protected Double baseRevenue;
    @XmlAttribute(name = "bonusRevenue")
    protected Double bonusRevenue;
    @XmlAttribute(name = "baseNights")
    protected Integer baseNights;
    @XmlAttribute(name = "bonusNights")
    protected Integer bonusNights;
    @XmlAttribute(name = "baseStay")
    protected Integer baseStay;
    @XmlAttribute(name = "bonusStay")
    protected Integer bonusStay;

    /**
     * 获取baseRevenue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBaseRevenue() {
        return baseRevenue;
    }

    /**
     * 设置baseRevenue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBaseRevenue(Double value) {
        this.baseRevenue = value;
    }

    /**
     * 获取bonusRevenue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBonusRevenue() {
        return bonusRevenue;
    }

    /**
     * 设置bonusRevenue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBonusRevenue(Double value) {
        this.bonusRevenue = value;
    }

    /**
     * 获取baseNights属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBaseNights() {
        return baseNights;
    }

    /**
     * 设置baseNights属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBaseNights(Integer value) {
        this.baseNights = value;
    }

    /**
     * 获取bonusNights属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBonusNights() {
        return bonusNights;
    }

    /**
     * 设置bonusNights属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBonusNights(Integer value) {
        this.bonusNights = value;
    }

    /**
     * 获取baseStay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBaseStay() {
        return baseStay;
    }

    /**
     * 设置baseStay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBaseStay(Integer value) {
        this.baseStay = value;
    }

    /**
     * 获取bonusStay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBonusStay() {
        return bonusStay;
    }

    /**
     * 设置bonusStay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBonusStay(Integer value) {
        this.bonusStay = value;
    }

}
