
package com.ccm.reservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProductAwardList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProductAwardList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductAwardInfo" type="{http://webservices.micros.com/og/4.3/Membership/}ProductAward" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductAwardList", namespace = "http://webservices.micros.com/og/4.3/Membership/", propOrder = {
    "productAwardInfo"
})
public class ProductAwardList {

    @XmlElement(name = "ProductAwardInfo", required = true)
    protected List<ProductAward> productAwardInfo;

    /**
     * Gets the value of the productAwardInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAwardInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAwardInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductAward }
     * 
     * 
     */
    public List<ProductAward> getProductAwardInfo() {
        if (productAwardInfo == null) {
            productAwardInfo = new ArrayList<ProductAward>();
        }
        return this.productAwardInfo;
    }

}
