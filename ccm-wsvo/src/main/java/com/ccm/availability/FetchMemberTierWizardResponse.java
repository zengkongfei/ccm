
package com.ccm.availability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Result" type="{http://webservices.micros.com/og/4.3/Common/}ResultStatus"/>
 *         &lt;element name="TierWizards" type="{http://webservices.micros.com/og/4.3/Membership/}TierWizard" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "result",
    "tierWizards"
})
@XmlRootElement(name = "FetchMemberTierWizardResponse", namespace = "http://webservices.micros.com/og/4.3/Membership/")
public class FetchMemberTierWizardResponse {

    @XmlElement(name = "Result", namespace = "http://webservices.micros.com/og/4.3/Membership/", required = true)
    protected ResultStatus result;
    @XmlElement(name = "TierWizards", namespace = "http://webservices.micros.com/og/4.3/Membership/")
    protected List<TierWizard> tierWizards;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResultStatus }
     *     
     */
    public ResultStatus getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResultStatus }
     *     
     */
    public void setResult(ResultStatus value) {
        this.result = value;
    }

    /**
     * Gets the value of the tierWizards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tierWizards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTierWizards().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TierWizard }
     * 
     * 
     */
    public List<TierWizard> getTierWizards() {
        if (tierWizards == null) {
            tierWizards = new ArrayList<TierWizard>();
        }
        return this.tierWizards;
    }

}
