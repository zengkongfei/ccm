//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 04:23:24GMT+08:00 
//


package _0._2.fidelio.udf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import _0._2.fidelio.udfdefinition.UdfDefinition;


/**
 * <p>Java class for Udf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Udf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{udfdefinition.fidelio.2.0}UdfDefinition"/>
 *         &lt;element name="udfValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Udf", propOrder = {
    "udfDefinition",
    "udfValue"
})
public class Udf {

    @XmlElement(name = "UdfDefinition", namespace = "udfdefinition.fidelio.2.0", required = true)
    protected UdfDefinition udfDefinition;
    protected String udfValue;

    /**
     * Gets the value of the udfDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link UdfDefinition }
     *     
     */
    public UdfDefinition getUdfDefinition() {
        return udfDefinition;
    }

    /**
     * Sets the value of the udfDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link UdfDefinition }
     *     
     */
    public void setUdfDefinition(UdfDefinition value) {
        this.udfDefinition = value;
    }

    /**
     * Gets the value of the udfValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdfValue() {
        return udfValue;
    }

    /**
     * Sets the value of the udfValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdfValue(String value) {
        this.udfValue = value;
    }

}