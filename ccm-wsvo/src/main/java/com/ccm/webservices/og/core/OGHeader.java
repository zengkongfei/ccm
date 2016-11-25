package com.ccm.webservices.og.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.util.StringUtils;

/**
 * <p>
 * OGHeader complex type的 Java 类。
 * 
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OGHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Origin" type="{http://webservices.micros.com/og/4.3/Core/}EndPoint"/>
 *         &lt;element name="Destination" type="{http://webservices.micros.com/og/4.3/Core/}EndPoint"/>
 *         &lt;element name="Intermediaries" type="{http://webservices.micros.com/og/4.3/Core/}EndPointList" minOccurs="0"/>
 *         &lt;element name="Authentication" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="UserCredentials">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="UserPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SecurityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Licence" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Key" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;pattern value="\{[\dA-Fa-f]{8}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{12}\}"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="transactionID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="authToken" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="primaryLangID" default="E">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
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
@XmlType(name = "OGHeader", propOrder = { "origin", "destination", "intermediaries", "authentication" })
public class OGHeader {

	@XmlElement(name = "Origin", required = true)
	protected EndPoint origin;
	@XmlElement(name = "Destination", required = true)
	protected EndPoint destination;
	@XmlElement(name = "Intermediaries")
	protected EndPointList intermediaries;
	@XmlElement(name = "Authentication")
	protected OGHeader.Authentication authentication;
	@XmlAttribute(name = "transactionID")
	protected String transactionID;
	@XmlAttribute(name = "authToken")
	protected String authToken;
	@XmlAttribute(name = "timeStamp")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar timeStamp;
	@XmlAttribute(name = "primaryLangID")
	protected String primaryLangID;

	/**
	 * 获取origin属性的值。
	 * 
	 * @return possible object is {@link EndPoint }
	 * 
	 */
	public EndPoint getOrigin() {
		return origin;
	}

	/**
	 * 设置origin属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link EndPoint }
	 * 
	 */
	public void setOrigin(EndPoint value) {
		this.origin = value;
	}

	/**
	 * 获取destination属性的值。
	 * 
	 * @return possible object is {@link EndPoint }
	 * 
	 */
	public EndPoint getDestination() {
		return destination;
	}

	/**
	 * 设置destination属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link EndPoint }
	 * 
	 */
	public void setDestination(EndPoint value) {
		this.destination = value;
	}

	/**
	 * 获取intermediaries属性的值。
	 * 
	 * @return possible object is {@link EndPointList }
	 * 
	 */
	public EndPointList getIntermediaries() {
		return intermediaries;
	}

	/**
	 * 设置intermediaries属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link EndPointList }
	 * 
	 */
	public void setIntermediaries(EndPointList value) {
		this.intermediaries = value;
	}

	/**
	 * 获取authentication属性的值。
	 * 
	 * @return possible object is {@link OGHeader.Authentication }
	 * 
	 */
	public OGHeader.Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * 设置authentication属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link OGHeader.Authentication }
	 * 
	 */
	public void setAuthentication(OGHeader.Authentication value) {
		this.authentication = value;
	}

	/**
	 * 获取transactionID属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * 设置transactionID属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransactionID(String value) {
		this.transactionID = value;
	}

	/**
	 * 获取authToken属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * 设置authToken属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAuthToken(String value) {
		this.authToken = value;
	}

	/**
	 * 获取timeStamp属性的值。
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTimeStamp() {
		return timeStamp;
	}

	/**
	 * 设置timeStamp属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTimeStamp(XMLGregorianCalendar value) {
		this.timeStamp = value;
	}

	/**
	 * 获取primaryLangID属性的值。
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrimaryLangID() {
		if (primaryLangID == null) {
			// return "E";
			return "zh_CN";
		} else {
			return primaryLangID;
		}
	}

	/**
	 * 设置primaryLangID属性的值。
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPrimaryLangID(String value) {
		this.primaryLangID = value;
	}

	/**
	 * <p>
	 * anonymous complex type的 Java 类。
	 * 
	 * <p>
	 * 以下模式片段指定包含在此类中的预期内容。
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="UserCredentials">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="UserPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="Domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="SecurityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="Licence" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="Key" minOccurs="0">
	 *                     &lt;simpleType>
	 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
	 *                         &lt;pattern value="\{[\dA-Fa-f]{8}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{12}\}"/>
	 *                       &lt;/restriction>
	 *                     &lt;/simpleType>
	 *                   &lt;/element>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "userCredentials", "licence" })
	public static class Authentication {

		@XmlElement(name = "UserCredentials", required = true)
		protected OGHeader.Authentication.UserCredentials userCredentials;
		@XmlElement(name = "Licence")
		protected OGHeader.Authentication.Licence licence;

		/**
		 * 获取userCredentials属性的值。
		 * 
		 * @return possible object is
		 *         {@link OGHeader.Authentication.UserCredentials }
		 * 
		 */
		public OGHeader.Authentication.UserCredentials getUserCredentials() {
			return userCredentials;
		}

		/**
		 * 设置userCredentials属性的值。
		 * 
		 * @param value
		 *            allowed object is
		 *            {@link OGHeader.Authentication.UserCredentials }
		 * 
		 */
		public void setUserCredentials(OGHeader.Authentication.UserCredentials value) {
			this.userCredentials = value;
		}

		/**
		 * 获取licence属性的值。
		 * 
		 * @return possible object is {@link OGHeader.Authentication.Licence }
		 * 
		 */
		public OGHeader.Authentication.Licence getLicence() {
			return licence;
		}

		/**
		 * 设置licence属性的值。
		 * 
		 * @param value
		 *            allowed object is {@link OGHeader.Authentication.Licence }
		 * 
		 */
		public void setLicence(OGHeader.Authentication.Licence value) {
			this.licence = value;
		}

		/**
		 * <p>
		 * anonymous complex type的 Java 类。
		 * 
		 * <p>
		 * 以下模式片段指定包含在此类中的预期内容。
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="Key" minOccurs="0">
		 *           &lt;simpleType>
		 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
		 *               &lt;pattern value="\{[\dA-Fa-f]{8}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{4}\-[\dA-Fa-f]{12}\}"/>
		 *             &lt;/restriction>
		 *           &lt;/simpleType>
		 *         &lt;/element>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "key" })
		public static class Licence {

			@XmlElement(name = "Key")
			protected String key;

			/**
			 * 获取key属性的值。
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getKey() {
				return key;
			}

			/**
			 * 设置key属性的值。
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setKey(String value) {
				this.key = value;
			}

		}

		/**
		 * <p>
		 * anonymous complex type的 Java 类。
		 * 
		 * <p>
		 * 以下模式片段指定包含在此类中的预期内容。
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="UserPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="Domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="SecurityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "userName", "userPassword", "domain", "securityId" })
		public static class UserCredentials {

			@XmlElement(name = "UserName", required = true)
			protected String userName;
			@XmlElement(name = "UserPassword", required = true)
			protected String userPassword;
			@XmlElement(name = "Domain", required = true)
			protected String domain;
			@XmlElement(name = "SecurityId")
			protected String securityId;

			/**
			 * 获取userName属性的值。
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getUserName() {
				return userName;
			}

			/**
			 * 设置userName属性的值。
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setUserName(String value) {
				this.userName = value;
			}

			/**
			 * 获取userPassword属性的值。
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getUserPassword() {
				return userPassword;
			}

			/**
			 * 设置userPassword属性的值。
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setUserPassword(String value) {
				this.userPassword = value;
			}

			/**
			 * 获取domain属性的值。
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDomain() {
				return domain;
			}

			/**
			 * 设置domain属性的值。
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setDomain(String value) {
				this.domain = value;
			}

			/**
			 * 获取securityId属性的值。
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSecurityId() {
				return securityId;
			}

			/**
			 * 设置securityId属性的值。
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setSecurityId(String value) {
				this.securityId = value;
			}

		}

	}

	/**
	 * 获取渠道代码
	 * 
	 * @return
	 */
	public String getChannelCode() {
		if (origin != null && StringUtils.hasText(origin.getEntityID())) {
			return origin.getEntityID();
		}
		return "";
	}

	@Override
	public String toString() {
		return "OGHeader [origin=" + origin + ", destination=" + destination + ", intermediaries=" + intermediaries + ", authentication=" + authentication + ", transactionID=" + transactionID + ", authToken=" + authToken + ", timeStamp=" + timeStamp + ", primaryLangID=" + primaryLangID + "]";
	}

}
