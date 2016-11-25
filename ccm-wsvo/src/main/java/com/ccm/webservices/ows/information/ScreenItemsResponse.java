
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.GDSResultStatus;
import com.ccm.webservices.og.hotelcommon.ScreenItemList;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="ScreenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScreenItems" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ScreenItemList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result",
    "screenName",
    "screenItems"
})
@XmlRootElement(name = "ScreenItemsResponse")
public class ScreenItemsResponse {

    @XmlElement(name = "Result", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "ScreenName")
    protected String screenName;
    @XmlElement(name = "ScreenItems")
    protected ScreenItemList screenItems;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * 获取screenName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScreenName() {
        return screenName;
    }

    /**
     * 设置screenName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScreenName(String value) {
        this.screenName = value;
    }

    /**
     * 获取screenItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ScreenItemList }
     *     
     */
    public ScreenItemList getScreenItems() {
        return screenItems;
    }

    /**
     * 设置screenItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ScreenItemList }
     *     
     */
    public void setScreenItems(ScreenItemList value) {
        this.screenItems = value;
    }

}
