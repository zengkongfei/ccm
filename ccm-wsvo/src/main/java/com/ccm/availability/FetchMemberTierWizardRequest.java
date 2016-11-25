
package com.ccm.availability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="MembershipID" type="{http://webservices.micros.com/og/4.3/Common/}UniqueID"/>
 *       &lt;/sequence>
 *       &lt;attribute name="evaluationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="evaluationType" use="required" type="{http://webservices.micros.com/og/4.3/Membership/}EvaluationTypes" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "membershipID"
})
@XmlRootElement(name = "FetchMemberTierWizardRequest", namespace = "http://webservices.micros.com/og/4.3/Membership/")
public class FetchMemberTierWizardRequest {

    @XmlElement(name = "MembershipID", namespace = "http://webservices.micros.com/og/4.3/Membership/", required = true)
    protected UniqueID membershipID;
    @XmlAttribute(name = "evaluationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar evaluationDate;
    @XmlAttribute(name = "evaluationType", required = true)
    protected EvaluationTypes evaluationType;

    /**
     * 获取membershipID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UniqueID }
     *     
     */
    public UniqueID getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UniqueID }
     *     
     */
    public void setMembershipID(UniqueID value) {
        this.membershipID = value;
    }

    /**
     * 获取evaluationDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEvaluationDate() {
        return evaluationDate;
    }

    /**
     * 设置evaluationDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEvaluationDate(XMLGregorianCalendar value) {
        this.evaluationDate = value;
    }

    /**
     * 获取evaluationType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EvaluationTypes }
     *     
     */
    public EvaluationTypes getEvaluationType() {
        return evaluationType;
    }

    /**
     * 设置evaluationType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EvaluationTypes }
     *     
     */
    public void setEvaluationType(EvaluationTypes value) {
        this.evaluationType = value;
    }

}
