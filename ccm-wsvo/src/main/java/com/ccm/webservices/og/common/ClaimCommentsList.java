
package com.ccm.webservices.og.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ClaimCommentsList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ClaimCommentsList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaimCommentsInfo" type="{http://webservices.micros.com/og/4.3/Common/}ClaimComment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimCommentsList", propOrder = {
    "claimCommentsInfo"
})
public class ClaimCommentsList {

    @XmlElement(name = "ClaimCommentsInfo")
    protected List<ClaimComment> claimCommentsInfo;

    /**
     * Gets the value of the claimCommentsInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claimCommentsInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaimCommentsInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaimComment }
     * 
     * 
     */
    public List<ClaimComment> getClaimCommentsInfo() {
        if (claimCommentsInfo == null) {
            claimCommentsInfo = new ArrayList<ClaimComment>();
        }
        return this.claimCommentsInfo;
    }

}
