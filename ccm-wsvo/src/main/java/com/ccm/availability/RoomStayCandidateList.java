
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RoomStayCandidateList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RoomStayCandidateList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomStayCandidate" type="{http://webservices.micros.com/og/4.3/Availability/}RoomStayCandidate" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomStayCandidateList", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "roomStayCandidate"
})
public class RoomStayCandidateList {

    @XmlElement(name = "RoomStayCandidate", required = true)
    protected List<RoomStayCandidate> roomStayCandidate;

    /**
     * Gets the value of the roomStayCandidate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomStayCandidate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomStayCandidate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomStayCandidate }
     * 
     * 
     */
    public List<RoomStayCandidate> getRoomStayCandidate() {
        if (roomStayCandidate == null) {
            roomStayCandidate = new ArrayList<RoomStayCandidate>();
        }
        return this.roomStayCandidate;
    }

}
