
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.membership.AwardType;


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
 *         &lt;element name="PropertyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AwardType" type="{http://webservices.micros.com/og/4.3/Membership/}AwardType"/>
 *         &lt;element name="MembershipLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MembershipType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "propertyCode",
    "awardType",
    "membershipLevel",
    "membershipType"
})
@XmlRootElement(name = "AwardsSchedulesRequest")
public class AwardsSchedulesRequest {

    @XmlElement(name = "PropertyCode", required = true)
    protected String propertyCode;
    @XmlElement(name = "AwardType", required = true)
    protected AwardType awardType;
    @XmlElement(name = "MembershipLevel", required = true)
    protected String membershipLevel;
    @XmlElement(name = "MembershipType", required = true)
    protected String membershipType;

    /**
     * 获取propertyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyCode() {
        return propertyCode;
    }

    /**
     * 设置propertyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyCode(String value) {
        this.propertyCode = value;
    }

    /**
     * 获取awardType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AwardType }
     *     
     */
    public AwardType getAwardType() {
        return awardType;
    }

    /**
     * 设置awardType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AwardType }
     *     
     */
    public void setAwardType(AwardType value) {
        this.awardType = value;
    }

    /**
     * 获取membershipLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipLevel() {
        return membershipLevel;
    }

    /**
     * 设置membershipLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipLevel(String value) {
        this.membershipLevel = value;
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

}
