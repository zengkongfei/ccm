
package com.ccm.webservices.og.membership;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.name.NameMembership;


/**
 * represents a list of transcations made against a membership account
 * 
 * <p>MembershipTransactionList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MembershipTransactionList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardInfo" type="{http://webservices.micros.com/og/4.3/Name/}NameMembership"/>
 *         &lt;element name="MembershipTransaction" type="{http://webservices.micros.com/og/4.3/Membership/}MembershipTransaction" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipTransactionList", propOrder = {
    "cardInfo",
    "membershipTransaction"
})
@XmlSeeAlso({
    Statement.class
})
public class MembershipTransactionList {

    @XmlElement(name = "CardInfo", required = true)
    protected NameMembership cardInfo;
    @XmlElement(name = "MembershipTransaction", required = true)
    protected List<MembershipTransaction> membershipTransaction;

    /**
     * 获取cardInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NameMembership }
     *     
     */
    public NameMembership getCardInfo() {
        return cardInfo;
    }

    /**
     * 设置cardInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NameMembership }
     *     
     */
    public void setCardInfo(NameMembership value) {
        this.cardInfo = value;
    }

    /**
     * Gets the value of the membershipTransaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the membershipTransaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembershipTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MembershipTransaction }
     * 
     * 
     */
    public List<MembershipTransaction> getMembershipTransaction() {
        if (membershipTransaction == null) {
            membershipTransaction = new ArrayList<MembershipTransaction>();
        }
        return this.membershipTransaction;
    }

}
