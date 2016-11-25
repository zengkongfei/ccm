
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AvailResponseSegment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AvailResponseSegment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RoomStayList" type="{http://webservices.micros.com/og/4.3/HotelCommon/}RoomStayList"/>
 *         &lt;element name="AdditionalInformation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="moreDataEchoToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailResponseSegment", namespace = "http://webservices.micros.com/og/4.3/Availability/", propOrder = {
    "roomStayList",
    "additionalInformation"
})
public class AvailResponseSegment {

    @XmlElement(name = "RoomStayList", required = true)
    protected RoomStayList roomStayList;
    @XmlElement(name = "AdditionalInformation")
    protected String additionalInformation;
    @XmlAttribute(name = "moreDataEchoToken")
    protected String moreDataEchoToken;

    /**
     * 获取roomStayList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RoomStayList }
     *     
     */
    public RoomStayList getRoomStayList() {
        return roomStayList;
    }

    /**
     * 设置roomStayList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RoomStayList }
     *     
     */
    public void setRoomStayList(RoomStayList value) {
        this.roomStayList = value;
    }

    /**
     * 获取additionalInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * 设置additionalInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalInformation(String value) {
        this.additionalInformation = value;
    }

    /**
     * 获取moreDataEchoToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoreDataEchoToken() {
        return moreDataEchoToken;
    }

    /**
     * 设置moreDataEchoToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoreDataEchoToken(String value) {
        this.moreDataEchoToken = value;
    }

}
