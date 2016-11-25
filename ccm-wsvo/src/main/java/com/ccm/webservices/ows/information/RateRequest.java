
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
 *       &lt;sequence>
 *         &lt;element name="RateQuery" type="{http://webservices.micros.com/ows/5.1/Information.wsdl}RateQueryType"/>
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
    "rateQuery"
})
@XmlRootElement(name = "RateRequest")
public class RateRequest {

    @XmlElement(name = "RateQuery", required = true)
    protected RateQueryType rateQuery;

    /**
     * 获取rateQuery属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RateQueryType }
     *     
     */
    public RateQueryType getRateQuery() {
        return rateQuery;
    }

    /**
     * 设置rateQuery属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RateQueryType }
     *     
     */
    public void setRateQuery(RateQueryType value) {
        this.rateQuery = value;
    }

}
