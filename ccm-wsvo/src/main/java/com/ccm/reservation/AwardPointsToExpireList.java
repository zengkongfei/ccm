
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AwardPointsToExpireList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AwardPointsToExpireList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AwardPointsToExpire" type="{http://webservices.micros.com/og/4.3/Common/}AwardPointsToExpire" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AwardPointsToExpireList", namespace = "http://webservices.micros.com/og/4.3/Common/", propOrder = {
    "awardPointsToExpire"
})
public class AwardPointsToExpireList {

    @XmlElement(name = "AwardPointsToExpire")
    protected List<AwardPointsToExpire> awardPointsToExpire;

    /**
     * Gets the value of the awardPointsToExpire property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the awardPointsToExpire property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAwardPointsToExpire().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AwardPointsToExpire }
     * 
     * 
     */
    public List<AwardPointsToExpire> getAwardPointsToExpire() {
        if (awardPointsToExpire == null) {
            awardPointsToExpire = new ArrayList<AwardPointsToExpire>();
        }
        return this.awardPointsToExpire;
    }

}
