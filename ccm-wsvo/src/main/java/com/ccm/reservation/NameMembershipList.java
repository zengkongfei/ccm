
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of NameMembership
 * 
 * <p>NameMembershipList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NameMembershipList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameMembership" type="{http://webservices.micros.com/og/4.3/Name/}NameMembership" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameMembershipList", namespace = "http://webservices.micros.com/og/4.3/Name/", propOrder = {
    "nameMembership"
})
public class NameMembershipList {

    @XmlElement(name = "NameMembership")
    protected List<NameMembership> nameMembership;

    /**
     * Gets the value of the nameMembership property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameMembership property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameMembership().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameMembership }
     * 
     * 
     */
    public List<NameMembership> getNameMembership() {
        if (nameMembership == null) {
            nameMembership = new ArrayList<NameMembership>();
        }
        return this.nameMembership;
    }

}
