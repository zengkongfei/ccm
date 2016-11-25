
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.GDSResultStatus;
import com.ccm.webservices.og.membership.AwardInformationList;


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
 *         &lt;element name="AwardInformation" type="{http://webservices.micros.com/og/4.3/Membership/}AwardInformationList" minOccurs="0"/>
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
    "awardInformation"
})
@XmlRootElement(name = "AwardsSchedulesResponse")
public class AwardsSchedulesResponse {

    @XmlElement(name = "Result", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "AwardInformation")
    protected AwardInformationList awardInformation;

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
     * 获取awardInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AwardInformationList }
     *     
     */
    public AwardInformationList getAwardInformation() {
        return awardInformation;
    }

    /**
     * 设置awardInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AwardInformationList }
     *     
     */
    public void setAwardInformation(AwardInformationList value) {
        this.awardInformation = value;
    }

}
