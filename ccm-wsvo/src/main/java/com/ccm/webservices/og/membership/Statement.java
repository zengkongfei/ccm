
package com.ccm.webservices.og.membership;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ccm.webservices.og.common.AwardPointsToExpireList;
import com.ccm.webservices.og.common.TextList;


/**
 * <p>Statement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Statement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.micros.com/og/4.3/Membership/}MembershipTransactionList">
 *       &lt;sequence>
 *         &lt;element name="noticeText" type="{http://webservices.micros.com/og/4.3/Common/}TextList" minOccurs="0"/>
 *         &lt;element name="AwardPointsToExpires" type="{http://webservices.micros.com/og/4.3/Common/}AwardPointsToExpireList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="statementDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="statementID" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="startDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="endDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="beginningBalance" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="endingBalance" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="membershipYear" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="awardPointsEarned" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="awardPointsUsed" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Statement", propOrder = {
    "noticeText",
    "awardPointsToExpires"
})
public class Statement
    extends MembershipTransactionList
{

    protected TextList noticeText;
    @XmlElement(name = "AwardPointsToExpires")
    protected AwardPointsToExpireList awardPointsToExpires;
    @XmlAttribute(name = "statementDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar statementDate;
    @XmlAttribute(name = "statementID")
    protected BigInteger statementID;
    @XmlAttribute(name = "startDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "endDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "beginningBalance", required = true)
    protected float beginningBalance;
    @XmlAttribute(name = "endingBalance")
    protected Float endingBalance;
    @XmlAttribute(name = "membershipYear")
    protected Integer membershipYear;
    @XmlAttribute(name = "awardPointsEarned")
    protected Double awardPointsEarned;
    @XmlAttribute(name = "awardPointsUsed")
    protected Double awardPointsUsed;

    /**
     * 获取noticeText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TextList }
     *     
     */
    public TextList getNoticeText() {
        return noticeText;
    }

    /**
     * 设置noticeText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TextList }
     *     
     */
    public void setNoticeText(TextList value) {
        this.noticeText = value;
    }

    /**
     * 获取awardPointsToExpires属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AwardPointsToExpireList }
     *     
     */
    public AwardPointsToExpireList getAwardPointsToExpires() {
        return awardPointsToExpires;
    }

    /**
     * 设置awardPointsToExpires属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AwardPointsToExpireList }
     *     
     */
    public void setAwardPointsToExpires(AwardPointsToExpireList value) {
        this.awardPointsToExpires = value;
    }

    /**
     * 获取statementDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatementDate() {
        return statementDate;
    }

    /**
     * 设置statementDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatementDate(XMLGregorianCalendar value) {
        this.statementDate = value;
    }

    /**
     * 获取statementID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatementID() {
        return statementID;
    }

    /**
     * 设置statementID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatementID(BigInteger value) {
        this.statementID = value;
    }

    /**
     * 获取startDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * 获取beginningBalance属性的值。
     * 
     */
    public float getBeginningBalance() {
        return beginningBalance;
    }

    /**
     * 设置beginningBalance属性的值。
     * 
     */
    public void setBeginningBalance(float value) {
        this.beginningBalance = value;
    }

    /**
     * 获取endingBalance属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getEndingBalance() {
        return endingBalance;
    }

    /**
     * 设置endingBalance属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setEndingBalance(Float value) {
        this.endingBalance = value;
    }

    /**
     * 获取membershipYear属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMembershipYear() {
        return membershipYear;
    }

    /**
     * 设置membershipYear属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMembershipYear(Integer value) {
        this.membershipYear = value;
    }

    /**
     * 获取awardPointsEarned属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAwardPointsEarned() {
        return awardPointsEarned;
    }

    /**
     * 设置awardPointsEarned属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAwardPointsEarned(Double value) {
        this.awardPointsEarned = value;
    }

    /**
     * 获取awardPointsUsed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAwardPointsUsed() {
        return awardPointsUsed;
    }

    /**
     * 设置awardPointsUsed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAwardPointsUsed(Double value) {
        this.awardPointsUsed = value;
    }

}
