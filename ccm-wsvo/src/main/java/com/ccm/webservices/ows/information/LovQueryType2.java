
package com.ccm.webservices.ows.information;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LovQueryType2 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LovQueryType2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LovIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LovQueryQualifier" type="{http://webservices.micros.com/ows/5.1/Information.wsdl}LovQueryQualifierType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LovQueryType2", propOrder = {
    "lovIdentifier",
    "lovQueryQualifier"
})
public class LovQueryType2 {

    @XmlElement(name = "LovIdentifier", required = true)
    protected String lovIdentifier;
    @XmlElement(name = "LovQueryQualifier", required = true)
    protected List<LovQueryQualifierType> lovQueryQualifier;

    /**
     * 获取lovIdentifier属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLovIdentifier() {
        return lovIdentifier;
    }

    /**
     * 设置lovIdentifier属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLovIdentifier(String value) {
        this.lovIdentifier = value;
    }

    /**
     * Gets the value of the lovQueryQualifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lovQueryQualifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLovQueryQualifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LovQueryQualifierType }
     * 
     * 
     */
    public List<LovQueryQualifierType> getLovQueryQualifier() {
        if (lovQueryQualifier == null) {
            lovQueryQualifier = new ArrayList<LovQueryQualifierType>();
        }
        return this.lovQueryQualifier;
    }

}
