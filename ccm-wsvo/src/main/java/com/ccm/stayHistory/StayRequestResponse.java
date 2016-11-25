
package com.ccm.stayHistory;

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
 *         &lt;element name="StayRequestResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Result" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="resultStatusFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;choice>
 *                     &lt;element name="StayInfoList" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}ArrayOfHotelReference" minOccurs="0"/>
 *                     &lt;element name="Errors" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/ErrorCommon/}ErrorInfoType" minOccurs="0"/>
 *                   &lt;/choice>
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
    "stayRequestResult"
})
@XmlRootElement(name = "StayRequestResponse")
public class StayRequestResponse {

    @XmlElement(name = "StayRequestResult")
    protected StayRequestResponse.StayRequestResult stayRequestResult;

    /**
     * 获取stayRequestResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StayRequestResponse.StayRequestResult }
     *     
     */
    public StayRequestResponse.StayRequestResult getStayRequestResult() {
        return stayRequestResult;
    }

    /**
     * 设置stayRequestResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StayRequestResponse.StayRequestResult }
     *     
     */
    public void setStayRequestResult(StayRequestResponse.StayRequestResult value) {
        this.stayRequestResult = value;
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
     *         &lt;element name="Result" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="resultStatusFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;choice>
     *           &lt;element name="StayInfoList" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/StayHistory/}ArrayOfHotelReference" minOccurs="0"/>
     *           &lt;element name="Errors" type="{http://www.chinaonline.net.cn/COL_WS/WS/1.0/ErrorCommon/}ErrorInfoType" minOccurs="0"/>
     *         &lt;/choice>
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
        "result",
        "stayInfoList",
        "errors"
    })
    public static class StayRequestResult {

        @XmlElement(name = "Result")
        protected StayRequestResponse.StayRequestResult.Result result;
        @XmlElement(name = "StayInfoList")
        protected ArrayOfHotelReference stayInfoList;
        @XmlElement(name = "Errors")
        protected ErrorInfoType errors;

        /**
         * 获取result属性的值。
         * 
         * @return
         *     possible object is
         *     {@link StayRequestResponse.StayRequestResult.Result }
         *     
         */
        public StayRequestResponse.StayRequestResult.Result getResult() {
            return result;
        }

        /**
         * 设置result属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link StayRequestResponse.StayRequestResult.Result }
         *     
         */
        public void setResult(StayRequestResponse.StayRequestResult.Result value) {
            this.result = value;
        }

        /**
         * 获取stayInfoList属性的值。
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfHotelReference }
         *     
         */
        public ArrayOfHotelReference getStayInfoList() {
            return stayInfoList;
        }

        /**
         * 设置stayInfoList属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfHotelReference }
         *     
         */
        public void setStayInfoList(ArrayOfHotelReference value) {
            this.stayInfoList = value;
        }

        /**
         * 获取errors属性的值。
         * 
         * @return
         *     possible object is
         *     {@link ErrorInfoType }
         *     
         */
        public ErrorInfoType getErrors() {
            return errors;
        }

        /**
         * 设置errors属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link ErrorInfoType }
         *     
         */
        public void setErrors(ErrorInfoType value) {
            this.errors = value;
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
         *       &lt;attribute name="resultStatusFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Result {

            @XmlAttribute(name = "resultStatusFlag")
            protected String resultStatusFlag;

            /**
             * 获取resultStatusFlag属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResultStatusFlag() {
                return resultStatusFlag;
            }

            /**
             * 设置resultStatusFlag属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResultStatusFlag(String value) {
                this.resultStatusFlag = value;
            }

        }

    }

}
