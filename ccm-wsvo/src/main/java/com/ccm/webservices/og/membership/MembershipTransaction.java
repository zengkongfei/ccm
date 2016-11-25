
package com.ccm.webservices.og.membership;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * represents a transaction against a membership account.  This could be a points award, points consumption, bonus, etc.
 * 
 * <p>MembershipTransaction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MembershipTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Points" type="{http://webservices.micros.com/og/4.3/Membership/}Points"/>
 *         &lt;element name="Tsc" type="{http://webservices.micros.com/og/4.3/Membership/}Tsc" minOccurs="0"/>
 *         &lt;element name="AwardLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransferDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PointsBreakups" type="{http://webservices.micros.com/og/4.3/Membership/}PointsBreakupList" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute name="postingDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="transactionTypeCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="source" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="referenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="startDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="endDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="pointsCalculated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="statementId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="crsReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="adjustment" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="resort" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="posCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="stayRecord" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipTransaction", propOrder = {

})
public class MembershipTransaction {

    @XmlElement(name = "Points", required = true)
    protected Points points;
    @XmlElement(name = "Tsc")
    protected Tsc tsc;
    @XmlElement(name = "AwardLabel")
    protected String awardLabel;
    @XmlElement(name = "TransferDescription")
    protected String transferDescription;
    @XmlElement(name = "UserNotes")
    protected String userNotes;
    @XmlElement(name = "PointsBreakups")
    protected PointsBreakupList pointsBreakups;
    @XmlAttribute(name = "postingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar postingDate;
    @XmlAttribute(name = "transactionTypeCode", required = true)
    protected String transactionTypeCode;
    @XmlAttribute(name = "source")
    protected String source;
    @XmlAttribute(name = "referenceNumber")
    protected String referenceNumber;
    @XmlAttribute(name = "startDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "endDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlAttribute(name = "pointsCalculated")
    protected Boolean pointsCalculated;
    @XmlAttribute(name = "statementId")
    protected String statementId;
    @XmlAttribute(name = "transactionId")
    protected String transactionId;
    @XmlAttribute(name = "crsReferenceNumber")
    protected String crsReferenceNumber;
    @XmlAttribute(name = "adjustment")
    protected Boolean adjustment;
    @XmlAttribute(name = "resort")
    protected String resort;
    @XmlAttribute(name = "posCode")
    protected String posCode;
    @XmlAttribute(name = "stayRecord")
    protected Boolean stayRecord;
    @XmlAttribute(name = "transactionDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionDate;

    /**
     * 获取points属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Points }
     *     
     */
    public Points getPoints() {
        return points;
    }

    /**
     * 设置points属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Points }
     *     
     */
    public void setPoints(Points value) {
        this.points = value;
    }

    /**
     * 获取tsc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Tsc }
     *     
     */
    public Tsc getTsc() {
        return tsc;
    }

    /**
     * 设置tsc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Tsc }
     *     
     */
    public void setTsc(Tsc value) {
        this.tsc = value;
    }

    /**
     * 获取awardLabel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwardLabel() {
        return awardLabel;
    }

    /**
     * 设置awardLabel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwardLabel(String value) {
        this.awardLabel = value;
    }

    /**
     * 获取transferDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferDescription() {
        return transferDescription;
    }

    /**
     * 设置transferDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferDescription(String value) {
        this.transferDescription = value;
    }

    /**
     * 获取userNotes属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNotes() {
        return userNotes;
    }

    /**
     * 设置userNotes属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNotes(String value) {
        this.userNotes = value;
    }

    /**
     * 获取pointsBreakups属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PointsBreakupList }
     *     
     */
    public PointsBreakupList getPointsBreakups() {
        return pointsBreakups;
    }

    /**
     * 设置pointsBreakups属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PointsBreakupList }
     *     
     */
    public void setPointsBreakups(PointsBreakupList value) {
        this.pointsBreakups = value;
    }

    /**
     * 获取postingDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * 设置postingDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * 获取transactionTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    /**
     * 设置transactionTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeCode(String value) {
        this.transactionTypeCode = value;
    }

    /**
     * 获取source属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置source属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * 获取referenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * 设置referenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
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
     * 获取pointsCalculated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPointsCalculated() {
        return pointsCalculated;
    }

    /**
     * 设置pointsCalculated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPointsCalculated(Boolean value) {
        this.pointsCalculated = value;
    }

    /**
     * 获取statementId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatementId() {
        return statementId;
    }

    /**
     * 设置statementId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatementId(String value) {
        this.statementId = value;
    }

    /**
     * 获取transactionId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置transactionId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * 获取crsReferenceNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrsReferenceNumber() {
        return crsReferenceNumber;
    }

    /**
     * 设置crsReferenceNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrsReferenceNumber(String value) {
        this.crsReferenceNumber = value;
    }

    /**
     * 获取adjustment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdjustment() {
        return adjustment;
    }

    /**
     * 设置adjustment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdjustment(Boolean value) {
        this.adjustment = value;
    }

    /**
     * 获取resort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResort() {
        return resort;
    }

    /**
     * 设置resort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResort(String value) {
        this.resort = value;
    }

    /**
     * 获取posCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosCode() {
        return posCode;
    }

    /**
     * 设置posCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosCode(String value) {
        this.posCode = value;
    }

    /**
     * 获取stayRecord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStayRecord() {
        return stayRecord;
    }

    /**
     * 设置stayRecord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStayRecord(Boolean value) {
        this.stayRecord = value;
    }

    /**
     * 获取transactionDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * 设置transactionDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
    }

}
