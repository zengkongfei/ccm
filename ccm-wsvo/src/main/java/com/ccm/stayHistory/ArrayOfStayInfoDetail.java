
package com.ccm.stayHistory;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ArrayOfStayInfoDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStayInfoDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StayInfoDetail" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/}StayInfoDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStayInfoDetail", namespace = "http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayInfo/", propOrder = {
    "stayInfoDetail"
})
public class ArrayOfStayInfoDetail {

    @XmlElement(name = "StayInfoDetail", nillable = true)
    protected List<StayInfoDetail> stayInfoDetail;

    /**
     * Gets the value of the stayInfoDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stayInfoDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStayInfoDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StayInfoDetail }
     * 
     * 
     */
    public List<StayInfoDetail> getStayInfoDetail() {
        if (stayInfoDetail == null) {
            stayInfoDetail = new ArrayList<StayInfoDetail>();
        }
        return this.stayInfoDetail;
    }

}
