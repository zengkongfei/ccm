
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>StatementReferenceList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="StatementReferenceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatementReferenceInfo" type="{http://webservices.micros.com/og/4.3/Membership/}StatementReference" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatementReferenceList", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "statementReferenceInfo"
})
public class StatementReferenceList {

    @XmlElement(name = "StatementReferenceInfo", required = true)
    protected List<StatementReference> statementReferenceInfo;

    /**
     * Gets the value of the statementReferenceInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statementReferenceInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatementReferenceInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatementReference }
     * 
     * 
     */
    public List<StatementReference> getStatementReferenceInfo() {
        if (statementReferenceInfo == null) {
            statementReferenceInfo = new ArrayList<StatementReference>();
        }
        return this.statementReferenceInfo;
    }

}
