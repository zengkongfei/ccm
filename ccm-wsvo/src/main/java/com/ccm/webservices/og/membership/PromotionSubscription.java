
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PromotionSubscription complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PromotionSubscription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="MembershipId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Promotion" type="{http://webservices.micros.com/og/4.3/Membership/}Promotion"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromotionSubscription", propOrder = {

})
public class PromotionSubscription {

    @XmlElement(name = "MembershipId")
    protected int membershipId;
    @XmlElement(name = "Promotion", required = true)
    protected Promotion promotion;

    /**
     * 获取membershipId属性的值。
     * 
     */
    public int getMembershipId() {
        return membershipId;
    }

    /**
     * 设置membershipId属性的值。
     * 
     */
    public void setMembershipId(int value) {
        this.membershipId = value;
    }

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

}
