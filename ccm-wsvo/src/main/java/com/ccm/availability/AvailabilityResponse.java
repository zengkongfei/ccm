
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GDSResultStatus"/>
 *         &lt;element name="AlternateDates" type="{http://webservices.micros.com/og/4.3/HotelCommon/}TimeSpan" minOccurs="0"/>
 *         &lt;element name="AvailResponseSegments" type="{http://webservices.micros.com/og/4.3/Availability/}AvailResponseSegmentList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="summaryOnly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="alternateDatesSpecified" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result",
    "alternateDates",
    "availResponseSegments"
})
@XmlRootElement(name = "AvailabilityResponse", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
public class AvailabilityResponse {

    @XmlElement(name = "Result", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl", required = true)
    protected GDSResultStatus result;
    @XmlElement(name = "AlternateDates", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected TimeSpan alternateDates;
    @XmlElement(name = "AvailResponseSegments", namespace = "http://webservices.micros.com/ows/5.1/Availability.wsdl")
    protected AvailResponseSegmentList availResponseSegments;
    @XmlAttribute(name = "summaryOnly", required = true)
    protected boolean summaryOnly;
    @XmlAttribute(name = "alternateDatesSpecified")
    protected Boolean alternateDatesSpecified;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GDSResultStatus }
     *     
     */
    public GDSResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GDSResultStatus }
     *     
     */
    public void setResult(GDSResultStatus value) {
        this.result = value;
    }

    /**
     * 获取alternateDates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeSpan }
     *     
     */
    public TimeSpan getAlternateDates() {
        return alternateDates;
    }

    /**
     * 设置alternateDates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeSpan }
     *     
     */
    public void setAlternateDates(TimeSpan value) {
        this.alternateDates = value;
    }

    /**
     * 获取availResponseSegments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AvailResponseSegmentList }
     *     
     */
    public AvailResponseSegmentList getAvailResponseSegments() {
        return availResponseSegments;
    }

    /**
     * 设置availResponseSegments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AvailResponseSegmentList }
     *     
     */
    public void setAvailResponseSegments(AvailResponseSegmentList value) {
        this.availResponseSegments = value;
    }

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

    /**
     * 获取alternateDatesSpecified属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAlternateDatesSpecified() {
        return alternateDatesSpecified;
    }

    /**
     * 设置alternateDatesSpecified属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlternateDatesSpecified(Boolean value) {
        this.alternateDatesSpecified = value;
    }

}
