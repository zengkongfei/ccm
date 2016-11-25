
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResultStatus complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ResultStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Text" type="{http://webservices.micros.com/og/4.3/Common/}TextList" minOccurs="0"/>
 *         &lt;element name="IDs" type="{http://webservices.micros.com/og/4.3/Common/}IDPairList" minOccurs="0"/>
 *         &lt;element name="OperaErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resultStatusFlag" type="{http://webservices.micros.com/og/4.3/Common/}ResultStatusFlag" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultStatus", namespace = "http://webservices.micros.com/og/4.3/Common/", propOrder = {
    "text",
    "iDs",
    "operaErrorCode"
})
@XmlSeeAlso({
    GDSResultStatus.class
})
public class ResultStatus {

    @XmlElement(name = "Text")
    protected TextList text;
    @XmlElement(name = "IDs")
    protected IDPairList iDs;
    @XmlElement(name = "OperaErrorCode")
    protected String operaErrorCode;
    @XmlAttribute(name = "resultStatusFlag")
    protected ResultStatusFlag resultStatusFlag;

    /**
     * 获取text属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TextList }
     *     
     */
    public TextList getText() {
        return text;
    }

    /**
     * 设置text属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TextList }
     *     
     */
    public void setText(TextList value) {
        this.text = value;
    }

    /**
     * 获取iDs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IDPairList }
     *     
     */
    public IDPairList getIDs() {
        return iDs;
    }

    /**
     * 设置iDs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IDPairList }
     *     
     */
    public void setIDs(IDPairList value) {
        this.iDs = value;
    }

    /**
     * 获取operaErrorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperaErrorCode() {
        return operaErrorCode;
    }

    /**
     * 设置operaErrorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperaErrorCode(String value) {
        this.operaErrorCode = value;
    }

    /**
     * 获取resultStatusFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResultStatusFlag }
     *     
     */
    public ResultStatusFlag getResultStatusFlag() {
        return resultStatusFlag;
    }

    /**
     * 设置resultStatusFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResultStatusFlag }
     *     
     */
    public void setResultStatusFlag(ResultStatusFlag value) {
        this.resultStatusFlag = value;
    }

}
