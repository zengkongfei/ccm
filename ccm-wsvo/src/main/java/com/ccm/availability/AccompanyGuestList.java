
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of AccompanyGuest
 * 
 * <p>AccompanyGuestList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AccompanyGuestList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccompanyGuest" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AccompanyGuest" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccompanyGuestList", propOrder = {
    "accompanyGuest"
})
public class AccompanyGuestList {

    @XmlElement(name = "AccompanyGuest", required = true)
    protected List<AccompanyGuest> accompanyGuest;

    /**
     * Gets the value of the accompanyGuest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accompanyGuest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccompanyGuest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccompanyGuest }
     * 
     * 
     */
    public List<AccompanyGuest> getAccompanyGuest() {
        if (accompanyGuest == null) {
            accompanyGuest = new ArrayList<AccompanyGuest>();
        }
        return this.accompanyGuest;
    }

}
