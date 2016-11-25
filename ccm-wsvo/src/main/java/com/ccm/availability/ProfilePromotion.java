
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProfilePromotion complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProfilePromotion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Promotion" type="{http://webservices.micros.com/og/4.3/Membership/}Promotion"/>
 *       &lt;/sequence>
 *       &lt;attribute name="membershipID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="membershipType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="membershipNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfilePromotion", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "promotion"
})
public class ProfilePromotion {

    @XmlElement(name = "Promotion", required = true)
    protected Promotion promotion;
    @XmlAttribute(name = "membershipID", required = true)
    protected int membershipID;
    @XmlAttribute(name = "membershipType", required = true)
    protected String membershipType;
    @XmlAttribute(name = "membershipNumber", required = true)
    protected String membershipNumber;

    /**
     * 获取promotion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Promotion }
     *     
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * 设置promotion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Promotion }
     *     
     */
    public void setPromotion(Promotion value) {
        this.promotion = value;
    }

    /**
     * 获取membershipID属性的值。
     * 
     */
    public int getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     */
    public void setMembershipID(int value) {
        this.membershipID = value;
    }

    /**
     * 获取membershipType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * 设置membershipType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipType(String value) {
        this.membershipType = value;
    }

    /**
     * 获取membershipNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipNumber() {
        return membershipNumber;
    }

    /**
     * 设置membershipNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipNumber(String value) {
        this.membershipNumber = value;
    }

}
