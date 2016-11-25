
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Voucher complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Voucher">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="voucherNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="voucherIssuedBy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="voucherValidDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voucher")
public class Voucher {

    @XmlAttribute(name = "voucherNumber")
    protected String voucherNumber;
    @XmlAttribute(name = "voucherIssuedBy")
    protected String voucherIssuedBy;
    @XmlAttribute(name = "voucherValidDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar voucherValidDate;

    /**
     * 获取voucherNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * 设置voucherNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherNumber(String value) {
        this.voucherNumber = value;
    }

    /**
     * 获取voucherIssuedBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoucherIssuedBy() {
        return voucherIssuedBy;
    }

    /**
     * 设置voucherIssuedBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoucherIssuedBy(String value) {
        this.voucherIssuedBy = value;
    }

    /**
     * 获取voucherValidDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVoucherValidDate() {
        return voucherValidDate;
    }

    /**
     * 设置voucherValidDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVoucherValidDate(XMLGregorianCalendar value) {
        this.voucherValidDate = value;
    }

}
