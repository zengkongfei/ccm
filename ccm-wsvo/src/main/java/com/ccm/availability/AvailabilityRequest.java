
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Availability/}AvailRequestSegmentList">
 *       &lt;attribute name="summaryOnly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "AvailabilityRequest", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class AvailabilityRequest
    extends AvailRequestSegmentList
{

    @XmlAttribute(name = "summaryOnly", required = true)
    protected boolean summaryOnly;

    /**
     * 获取summaryOnly属性的值。
     * 
     */
    public boolean isSummaryOnly() {
        return summaryOnly;
    }

    /**
     * 设置summaryOnly属性的值。
     * 
     */
    public void setSummaryOnly(boolean value) {
        this.summaryOnly = value;
    }

}
