
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GdsTotalPricingTaxList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GdsTotalPricingTaxList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GdsTotalPricingTax" type="{http://webservices.micros.com/og/4.3/HotelCommon/}GdsTotalPricingTax" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GdsTotalPricingTaxList", propOrder = {
    "gdsTotalPricingTax"
})
public class GdsTotalPricingTaxList {

    @XmlElement(name = "GdsTotalPricingTax", required = true)
    protected List<GdsTotalPricingTax> gdsTotalPricingTax;

    /**
     * Gets the value of the gdsTotalPricingTax property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gdsTotalPricingTax property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGdsTotalPricingTax().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GdsTotalPricingTax }
     * 
     * 
     */
    public List<GdsTotalPricingTax> getGdsTotalPricingTax() {
        if (gdsTotalPricingTax == null) {
            gdsTotalPricingTax = new ArrayList<GdsTotalPricingTax>();
        }
        return this.gdsTotalPricingTax;
    }

}
