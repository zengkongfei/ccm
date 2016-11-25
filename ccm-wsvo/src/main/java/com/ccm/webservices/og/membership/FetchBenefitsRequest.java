
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="membershipID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="membershipType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "FetchBenefitsRequest")
public class FetchBenefitsRequest {

    @XmlAttribute(name = "membershipID", required = true)
    protected int membershipID;
    @XmlAttribute(name = "membershipType")
    protected String membershipType;

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

}
