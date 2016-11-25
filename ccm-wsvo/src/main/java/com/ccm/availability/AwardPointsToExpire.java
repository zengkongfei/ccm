
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>AwardPointsToExpire complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AwardPointsToExpire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="expirationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="totalToExpire" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="expireByDate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AwardPointsToExpire", namespace = "http://webservices.micros.com/og/4.3/Common/")
public class AwardPointsToExpire {

    @XmlAttribute(name = "expirationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationDate;
    @XmlAttribute(name = "totalToExpire", required = true)
    protected double totalToExpire;
    @XmlAttribute(name = "expireByDate")
    protected Double expireByDate;

    /**
     * 获取expirationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * 设置expirationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * 获取totalToExpire属性的值。
     * 
     */
    public double getTotalToExpire() {
        return totalToExpire;
    }

    /**
     * 设置totalToExpire属性的值。
     * 
     */
    public void setTotalToExpire(double value) {
        this.totalToExpire = value;
    }

    /**
     * 获取expireByDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExpireByDate() {
        return expireByDate;
    }

    /**
     * 设置expireByDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExpireByDate(Double value) {
        this.expireByDate = value;
    }

}
