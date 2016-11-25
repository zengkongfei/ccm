//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.18 at 04:07:49 pmCST 
//


package com.ccm.api.model.ads.vo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchCriterionMatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchCriterionMatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="searchCriterionRPH" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="match" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="relevance" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;maxInclusive value="100.00"/>
 *             &lt;minInclusive value="0.00"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchCriterionMatch")
public class SearchCriterionMatch {

    @XmlAttribute(required = true)
    protected int searchCriterionRPH;
    @XmlAttribute(required = true)
    protected boolean match;
    @XmlAttribute(required = true)
    protected BigDecimal relevance;

    /**
     * Gets the value of the searchCriterionRPH property.
     * 
     */
    public int getSearchCriterionRPH() {
        return searchCriterionRPH;
    }

    /**
     * Sets the value of the searchCriterionRPH property.
     * 
     */
    public void setSearchCriterionRPH(int value) {
        this.searchCriterionRPH = value;
    }

    /**
     * Gets the value of the match property.
     * 
     */
    public boolean isMatch() {
        return match;
    }

    /**
     * Sets the value of the match property.
     * 
     */
    public void setMatch(boolean value) {
        this.match = value;
    }

    /**
     * Gets the value of the relevance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRelevance() {
        return relevance;
    }

    /**
     * Sets the value of the relevance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRelevance(BigDecimal value) {
        this.relevance = value;
    }

}
