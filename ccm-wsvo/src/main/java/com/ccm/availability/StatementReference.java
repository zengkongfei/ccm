
package com.ccm.availability;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>StatementReference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StatementReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="statementId" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="statementDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="membershipID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="locked" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatementReference", namespace = "http://webservices.micros.com/og/4.3/Membership/")
public class StatementReference {

    @XmlAttribute(name = "statementId", required = true)
    protected BigInteger statementId;
    @XmlAttribute(name = "statementDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar statementDate;
    @XmlAttribute(name = "membershipID", required = true)
    protected int membershipID;
    @XmlAttribute(name = "locked")
    protected Boolean locked;

    /**
     * 获取statementId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatementId() {
        return statementId;
    }

    /**
     * 设置statementId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatementId(BigInteger value) {
        this.statementId = value;
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
     * 获取membershipID属性的值。
     * 
     */
    public int getMembershipID() {
        return membershipID;
    }

    /**
     * 设置membershipID属性的值。
     * 
     */
    public void setMembershipID(int value) {
        this.membershipID = value;
    }

    /**
     * 获取locked属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLocked() {
        return locked;
    }

    /**
     * 设置locked属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLocked(Boolean value) {
        this.locked = value;
    }

}
