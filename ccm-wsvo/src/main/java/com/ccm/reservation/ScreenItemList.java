
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ScreenItemList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ScreenItemList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScreenItem" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ScreenItemElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScreenItemList", propOrder = {
    "screenItem"
})
public class ScreenItemList {

    @XmlElement(name = "ScreenItem")
    protected List<ScreenItemElement> screenItem;

    /**
     * Gets the value of the screenItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the screenItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScreenItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScreenItemElement }
     * 
     * 
     */
    public List<ScreenItemElement> getScreenItem() {
        if (screenItem == null) {
            screenItem = new ArrayList<ScreenItemElement>();
        }
        return this.screenItem;
    }

}
