
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PackageDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PackageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PackageInfo" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageElement"/>
 *         &lt;element name="ExpectedCharges" type="{http://webservices.micros.com/og/4.3/HotelCommon/}PackageCharges" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PackageDetail", propOrder = {
    "packageInfo",
    "expectedCharges"
})
public class PackageDetail {

    @XmlElement(name = "PackageInfo", required = true)
    protected PackageElement packageInfo;
    @XmlElement(name = "ExpectedCharges")
    protected PackageCharges expectedCharges;

    /**
     * 获取packageInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageElement }
     *     
     */
    public PackageElement getPackageInfo() {
        return packageInfo;
    }

    /**
     * 设置packageInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageElement }
     *     
     */
    public void setPackageInfo(PackageElement value) {
        this.packageInfo = value;
    }

    /**
     * 获取expectedCharges属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PackageCharges }
     *     
     */
    public PackageCharges getExpectedCharges() {
        return expectedCharges;
    }

    /**
     * 设置expectedCharges属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PackageCharges }
     *     
     */
    public void setExpectedCharges(PackageCharges value) {
        this.expectedCharges = value;
    }

}
