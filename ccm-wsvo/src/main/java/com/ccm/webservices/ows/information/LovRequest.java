
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="LovQuery" type="{http://webservices.micros.com/ows/5.1/Information.wsdl}LovQueryType" minOccurs="0"/>
 *         &lt;element name="LovQuery2" type="{http://webservices.micros.com/ows/5.1/Information.wsdl}LovQueryType2" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lovQuery",
    "lovQuery2"
})
@XmlRootElement(name = "LovRequest")
public class LovRequest {

    @XmlElement(name = "LovQuery")
    protected LovQueryType lovQuery;
    @XmlElement(name = "LovQuery2")
    protected LovQueryType2 lovQuery2;

    /**
     * 获取lovQuery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LovQueryType }
     *     
     */
    public LovQueryType getLovQuery() {
        return lovQuery;
    }

    /**
     * 设置lovQuery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LovQueryType }
     *     
     */
    public void setLovQuery(LovQueryType value) {
        this.lovQuery = value;
    }

    /**
     * 获取lovQuery2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LovQueryType2 }
     *     
     */
    public LovQueryType2 getLovQuery2() {
        return lovQuery2;
    }

    /**
     * 设置lovQuery2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LovQueryType2 }
     *     
     */
    public void setLovQuery2(LovQueryType2 value) {
        this.lovQuery2 = value;
    }

}
