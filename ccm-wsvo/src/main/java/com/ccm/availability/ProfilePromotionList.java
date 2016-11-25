
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProfilePromotionList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProfilePromotionList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProfilePromotion" type="{http://webservices.micros.com/og/4.3/Membership/}ProfilePromotion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProfilePromotionList", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "profilePromotion"
})
public class ProfilePromotionList {

    @XmlElement(name = "ProfilePromotion", required = true)
    protected List<ProfilePromotion> profilePromotion;

    /**
     * Gets the value of the profilePromotion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profilePromotion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfilePromotion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProfilePromotion }
     * 
     * 
     */
    public List<ProfilePromotion> getProfilePromotion() {
        if (profilePromotion == null) {
            profilePromotion = new ArrayList<ProfilePromotion>();
        }
        return this.profilePromotion;
    }

}
