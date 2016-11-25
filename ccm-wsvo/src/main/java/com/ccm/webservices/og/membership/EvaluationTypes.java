
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EvaluationTypes的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="EvaluationTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UPGRADE"/>
 *     &lt;enumeration value="DOWNGRADE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EvaluationTypes")
@XmlEnum
public enum EvaluationTypes {

    UPGRADE,
    DOWNGRADE;

    public String value() {
        return name();
    }

    public static EvaluationTypes fromValue(String v) {
        return valueOf(v);
    }

}
