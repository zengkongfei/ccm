
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PointsBreakup complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PointsBreakup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="pointsType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="points" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="pointsDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointsBreakup", namespace = "http://webservices.micros.com/og/4.3/Membership/")
public class PointsBreakup {

    @XmlAttribute(name = "pointsType")
    protected String pointsType;
    @XmlAttribute(name = "points", required = true)
    protected double points;
    @XmlAttribute(name = "pointsDescription")
    protected String pointsDescription;

    /**
     * 获取pointsType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsType() {
        return pointsType;
    }

    /**
     * 设置pointsType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsType(String value) {
        this.pointsType = value;
    }

    /**
     * 获取points属性的值。
     * 
     */
    public double getPoints() {
        return points;
    }

    /**
     * 设置points属性的值。
     * 
     */
    public void setPoints(double value) {
        this.points = value;
    }

    /**
     * 获取pointsDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsDescription() {
        return pointsDescription;
    }

    /**
     * 设置pointsDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsDescription(String value) {
        this.pointsDescription = value;
    }

}
