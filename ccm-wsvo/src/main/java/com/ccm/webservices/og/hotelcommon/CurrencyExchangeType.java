
package com.ccm.webservices.og.hotelcommon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CurrencyExchangeType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyExchangeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="POSTING"/>
 *     &lt;enumeration value="EXCHANGE"/>
 *     &lt;enumeration value="TA_COMMISSION"/>
 *     &lt;enumeration value="FORECAST"/>
 *     &lt;enumeration value="SETTLEMENT"/>
 *     &lt;enumeration value="EXCHANGE_CHECK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CurrencyExchangeType")
@XmlEnum
public enum CurrencyExchangeType {

    POSTING,
    EXCHANGE,
    TA_COMMISSION,
    FORECAST,
    SETTLEMENT,
    EXCHANGE_CHECK;

    public String value() {
        return name();
    }

    public static CurrencyExchangeType fromValue(String v) {
        return valueOf(v);
    }

}
