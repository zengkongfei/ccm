
package com.ccm.webservices.ows.information;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ccm.webservices.og.common.Amount;
import com.ccm.webservices.og.hotelcommon.CurrencyExchangeType;
import com.ccm.webservices.og.hotelcommon.HotelReference;


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
 *         &lt;element name="FromCurrency" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *         &lt;element name="ToCurrency" type="{http://webservices.micros.com/og/4.3/Common/}Amount"/>
 *         &lt;element name="Resort" type="{http://webservices.micros.com/og/4.3/HotelCommon/}HotelReference"/>
 *         &lt;element name="ExchangeType" type="{http://webservices.micros.com/og/4.3/HotelCommon/}CurrencyExchangeType" minOccurs="0"/>
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
    "fromCurrency",
    "toCurrency",
    "resort",
    "exchangeType"
})
@XmlRootElement(name = "CurrencyConverterRequest")
public class CurrencyConverterRequest {

    @XmlElement(name = "FromCurrency", required = true)
    protected Amount fromCurrency;
    @XmlElement(name = "ToCurrency", required = true)
    protected Amount toCurrency;
    @XmlElement(name = "Resort", required = true)
    protected HotelReference resort;
    @XmlElement(name = "ExchangeType")
    protected CurrencyExchangeType exchangeType;

    /**
     * 获取fromCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getFromCurrency() {
        return fromCurrency;
    }

    /**
     * 设置fromCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setFromCurrency(Amount value) {
        this.fromCurrency = value;
    }

    /**
     * 获取toCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getToCurrency() {
        return toCurrency;
    }

    /**
     * 设置toCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setToCurrency(Amount value) {
        this.toCurrency = value;
    }

    /**
     * 获取resort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link HotelReference }
     *     
     */
    public HotelReference getResort() {
        return resort;
    }

    /**
     * 设置resort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link HotelReference }
     *     
     */
    public void setResort(HotelReference value) {
        this.resort = value;
    }

    /**
     * 获取exchangeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CurrencyExchangeType }
     *     
     */
    public CurrencyExchangeType getExchangeType() {
        return exchangeType;
    }

    /**
     * 设置exchangeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyExchangeType }
     *     
     */
    public void setExchangeType(CurrencyExchangeType value) {
        this.exchangeType = value;
    }

}
