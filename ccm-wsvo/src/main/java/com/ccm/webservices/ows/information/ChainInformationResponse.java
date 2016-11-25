
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.hotelcommon.ChainInfo;
import com.ccm.webservices.og.hotelcommon.GDSResultStatus;


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
 *         &lt;element name="ChainInformation" type="{http://webservices.micros.com/og/4.3/HotelCommon/}ChainInfo"/>
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
    "chainInformation"
})
@XmlRootElement(name = "ChainInformationResponse")
public class ChainInformationResponse {

    @XmlElement(name = "Result", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "ChainInformation", required = true)
    protected ChainInfo chainInformation;

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
     * 获取chainInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ChainInfo }
     *     
     */
    public ChainInfo getChainInformation() {
        return chainInformation;
    }

    /**
     * 设置chainInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ChainInfo }
     *     
     */
    public void setChainInformation(ChainInfo value) {
        this.chainInformation = value;
    }

}
