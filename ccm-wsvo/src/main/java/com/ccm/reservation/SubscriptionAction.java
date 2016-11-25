
package com.ccm.reservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SubscriptionAction的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="SubscriptionAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="SUBSCRIBE"/>
 *     &lt;enumeration value="UNSUBSCRIBE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SubscriptionAction", namespace = "http://webservices.micros.com/og/4.3/Common/")
@XmlEnum
public enum SubscriptionAction {

    OTHER,
    SUBSCRIBE,
    UNSUBSCRIBE;

    public String value() {
        return name();
    }

    public static SubscriptionAction fromValue(String v) {
        return valueOf(v);
    }

}
