
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PromotionIssueType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="PromotionIssueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ASSIGNED"/>
 *     &lt;enumeration value="OPTIN"/>
 *     &lt;enumeration value="PURCHASED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PromotionIssueType")
@XmlEnum
public enum PromotionIssueType {

    ASSIGNED,
    OPTIN,
    PURCHASED;

    public String value() {
        return name();
    }

    public static PromotionIssueType fromValue(String v) {
        return valueOf(v);
    }

}
