
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>UpsellRoomList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="UpsellRoomList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UpsellRoomInfo" type="{http://webservices.micros.com/og/4.3/Reservation/}UpsellRoom" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpsellRoomList", namespace = "http://webservices.micros.com/og/4.3/Reservation/", propOrder = {
    "upsellRoomInfo"
})
public class UpsellRoomList {

    @XmlElement(name = "UpsellRoomInfo", required = true)
    protected List<UpsellRoom> upsellRoomInfo;

    /**
     * Gets the value of the upsellRoomInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the upsellRoomInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpsellRoomInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpsellRoom }
     * 
     * 
     */
    public List<UpsellRoom> getUpsellRoomInfo() {
        if (upsellRoomInfo == null) {
            upsellRoomInfo = new ArrayList<UpsellRoom>();
        }
        return this.upsellRoomInfo;
    }

}
