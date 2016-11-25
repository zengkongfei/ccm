
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.DescriptiveText;


/**
 * <p>AttractionInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AttractionInfo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Common/}DescriptiveText">
 *       &lt;attribute name="attractionInfoType" use="required" type="{http://webservices.micros.com/og/4.3/HotelCommon/}AttractionInfoType" />
 *       &lt;attribute name="otherAttractionInfoType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttractionInfo")
public class AttractionInfo
    extends DescriptiveText
{

    @XmlAttribute(name = "attractionInfoType", required = true)
    protected AttractionInfoType attractionInfoType;
    @XmlAttribute(name = "otherAttractionInfoType")
    protected String otherAttractionInfoType;

    /**
     * 获取attractionInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AttractionInfoType }
     *     
     */
    public AttractionInfoType getAttractionInfoType() {
        return attractionInfoType;
    }

    /**
     * 设置attractionInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AttractionInfoType }
     *     
     */
    public void setAttractionInfoType(AttractionInfoType value) {
        this.attractionInfoType = value;
    }

    /**
     * 获取otherAttractionInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherAttractionInfoType() {
        return otherAttractionInfoType;
    }

    /**
     * 设置otherAttractionInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherAttractionInfoType(String value) {
        this.otherAttractionInfoType = value;
    }

}
