
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.ResultStatus;


/**
 * Extends "regular" ResultStatus object to include the possiblity of regurning various predefined error codes and responses.  Used primarily for ADS / GDS interfaces.
 * 
 * <p>GDSResultStatus complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GDSResultStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}ResultStatus">
 *       &lt;sequence>
 *         &lt;element name="GDSError" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GDSResultStatus", propOrder = {
    "gdsError"
})
public class GDSResultStatus
    extends ResultStatus
{

    @XmlElement(name = "GDSError")
    protected GDSError gdsError;

    /**
     * 获取gdsError属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSError }
     *     
     */
    public GDSError getGDSError() {
        return gdsError;
    }

    /**
     * 设置gdsError属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSError }
     *     
     */
    public void setGDSError(GDSError value) {
        this.gdsError = value;
    }

}
