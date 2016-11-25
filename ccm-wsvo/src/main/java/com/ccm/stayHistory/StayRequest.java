
package com.ccm.stayHistory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="StayHistoryRequest" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ADSInfo" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}ADSInfoType" minOccurs="0"/>
 *                   &lt;element name="TimeSpan" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/TimeSpan/}TimeSpanType" minOccurs="0"/>
 *                   &lt;element name="StayHistoryQuery" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}StayHistoryQueryType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "stayHistoryRequest"
})
@XmlRootElement(name = "StayRequest")
public class StayRequest {

    @XmlElement(name = "StayHistoryRequest")
    protected StayRequest.StayHistoryRequest stayHistoryRequest;

    /**
     * 获取stayHistoryRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StayRequest.StayHistoryRequest }
     *     
     */
    public StayRequest.StayHistoryRequest getStayHistoryRequest() {
        return stayHistoryRequest;
    }

    /**
     * 设置stayHistoryRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StayRequest.StayHistoryRequest }
     *     
     */
    public void setStayHistoryRequest(StayRequest.StayHistoryRequest value) {
        this.stayHistoryRequest = value;
    }


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
     *         &lt;element name="ADSInfo" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}ADSInfoType" minOccurs="0"/>
     *         &lt;element name="TimeSpan" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/TimeSpan/}TimeSpanType" minOccurs="0"/>
     *         &lt;element name="StayHistoryQuery" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}StayHistoryQueryType" minOccurs="0"/>
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
        "adsInfo",
        "timeSpan",
        "stayHistoryQuery"
    })
    public static class StayHistoryRequest {

        @XmlElement(name = "ADSInfo")
        protected ADSInfoType adsInfo;
        @XmlElement(name = "TimeSpan")
        protected TimeSpanType timeSpan;
        @XmlElement(name = "StayHistoryQuery")
        protected StayHistoryQueryType stayHistoryQuery;

        /**
         * 获取adsInfo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link ADSInfoType }
         *     
         */
        public ADSInfoType getADSInfo() {
            return adsInfo;
        }

        /**
         * 设置adsInfo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link ADSInfoType }
         *     
         */
        public void setADSInfo(ADSInfoType value) {
            this.adsInfo = value;
        }

        /**
         * 获取timeSpan属性的值。
         * 
         * @return
         *     possible object is
         *     {@link TimeSpanType }
         *     
         */
        public TimeSpanType getTimeSpan() {
            return timeSpan;
        }

        /**
         * 设置timeSpan属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link TimeSpanType }
         *     
         */
        public void setTimeSpan(TimeSpanType value) {
            this.timeSpan = value;
        }

        /**
         * 获取stayHistoryQuery属性的值。
         * 
         * @return
         *     possible object is
         *     {@link StayHistoryQueryType }
         *     
         */
        public StayHistoryQueryType getStayHistoryQuery() {
            return stayHistoryQuery;
        }

        /**
         * 设置stayHistoryQuery属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link StayHistoryQueryType }
         *     
         */
        public void setStayHistoryQuery(StayHistoryQueryType value) {
            this.stayHistoryQuery = value;
        }

    }

}
