
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PromotionSubscriptionList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PromotionSubscriptionList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PromotionSubscription" type="{http://webservices.micros.com/og/4.3/Membership/}Promotion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MembershipId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromotionSubscriptionList", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "promotionSubscription"
})
public class PromotionSubscriptionList {

    @XmlElement(name = "PromotionSubscription", required = true)
    protected List<Promotion> promotionSubscription;
    @XmlAttribute(name = "MembershipId", required = true)
    protected int membershipId;

    /**
     * Gets the value of the promotionSubscription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promotionSubscription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromotionSubscription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Promotion }
     * 
     * 
     */
    public List<Promotion> getPromotionSubscription() {
        if (promotionSubscription == null) {
            promotionSubscription = new ArrayList<Promotion>();
        }
        return this.promotionSubscription;
    }

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

}
