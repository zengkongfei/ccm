
package com.ccm.availability;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PromotionSubscriptionStatusType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="PromotionSubscriptionStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="INACTIVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PromotionSubscriptionStatusType", namespace = "http://webservices.micros.com/og/4.3/Membership/")
@XmlEnum
public enum PromotionSubscriptionStatusType {

    ACTIVE,
    INACTIVE;

    public String value() {
        return name();
    }

    public static PromotionSubscriptionStatusType fromValue(String v) {
        return valueOf(v);
    }

}
