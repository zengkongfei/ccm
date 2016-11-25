
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Award complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Award">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AwardCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PointsRequired" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RuleSchedule" type="{http://webservices.micros.com/og/4.3/Membership/}RuleSchedule" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Award", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "awardCode",
    "pointsRequired",
    "ruleSchedule"
})
public class Award {

    @XmlElement(name = "AwardCode")
    protected String awardCode;
    @XmlElement(name = "PointsRequired")
    protected Integer pointsRequired;
    @XmlElement(name = "RuleSchedule")
    protected RuleSchedule ruleSchedule;

    /**
     * 获取awardCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwardCode() {
        return awardCode;
    }

    /**
     * 设置awardCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwardCode(String value) {
        this.awardCode = value;
    }

    /**
     * 获取pointsRequired属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsRequired() {
        return pointsRequired;
    }

    /**
     * 设置pointsRequired属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsRequired(Integer value) {
        this.pointsRequired = value;
    }

    /**
     * 获取ruleSchedule属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RuleSchedule }
     *     
     */
    public RuleSchedule getRuleSchedule() {
        return ruleSchedule;
    }

    /**
     * 设置ruleSchedule属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RuleSchedule }
     *     
     */
    public void setRuleSchedule(RuleSchedule value) {
        this.ruleSchedule = value;
    }

}
