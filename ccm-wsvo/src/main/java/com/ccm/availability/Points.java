
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * represents the breakdown of points
 * 
 * <p>Points complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Points">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>double">
 *       &lt;attribute name="basePoints" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="bonusPoints" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="miscPoints" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Points", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "value"
})
public class Points {

    @XmlValue
    protected double value;
    @XmlAttribute(name = "basePoints")
    protected Double basePoints;
    @XmlAttribute(name = "bonusPoints")
    protected Double bonusPoints;
    @XmlAttribute(name = "miscPoints")
    protected Double miscPoints;

    /**
     * 获取value属性的值。
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * 获取basePoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBasePoints() {
        return basePoints;
    }

    /**
     * 设置basePoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBasePoints(Double value) {
        this.basePoints = value;
    }

    /**
     * 获取bonusPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBonusPoints() {
        return bonusPoints;
    }

    /**
     * 设置bonusPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBonusPoints(Double value) {
        this.bonusPoints = value;
    }

    /**
     * 获取miscPoints属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMiscPoints() {
        return miscPoints;
    }

    /**
     * 设置miscPoints属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMiscPoints(Double value) {
        this.miscPoints = value;
    }

}
