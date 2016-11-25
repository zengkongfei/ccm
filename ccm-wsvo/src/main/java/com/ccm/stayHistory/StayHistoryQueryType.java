
package com.ccm.stayHistory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StayHistoryQueryType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StayHistoryQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="ConfirmationIDList" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}ConfirmationIDListType" minOccurs="0"/>
 *           &lt;element name="HotelReferenceList" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}HotelReferenceListType" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StayHistoryQueryType", propOrder = {
    "confirmationIDList",
    "hotelReferenceList"
})
public class StayHistoryQueryType {

    @XmlElement(name = "ConfirmationIDList")
    protected ConfirmationIDListType confirmationIDList;
    @XmlElement(name = "HotelReferenceList")
    protected HotelReferenceListType hotelReferenceList;

    /**
     * 获取confirmationIDList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ConfirmationIDListType }
     *     
     */
    public ConfirmationIDListType getConfirmationIDList() {
        return confirmationIDList;
    }

    /**
     * 设置confirmationIDList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ConfirmationIDListType }
     *     
     */
    public void setConfirmationIDList(ConfirmationIDListType value) {
        this.confirmationIDList = value;
    }

    /**
     * 获取hotelReferenceList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReferenceListType }
     *     
     */
    public HotelReferenceListType getHotelReferenceList() {
        return hotelReferenceList;
    }

    /**
     * 设置hotelReferenceList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReferenceListType }
     *     
     */
    public void setHotelReferenceList(HotelReferenceListType value) {
        this.hotelReferenceList = value;
    }

}
