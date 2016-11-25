package com.ccm.api.util;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.ProcessingInstruction;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

public class XMLUtil {

	protected static final Log log = LogFactory.getLog(XMLUtil.class);

	private static final String DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	/**
	 * xml转换成bean
	 * 
	 * @throws JAXBException
	 */
	@SuppressWarnings("rawtypes")
	public static Object JAXBParseToBean(String xmlStr, Class cls, String charsetName) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(cls);
		Unmarshaller um = jaxbContext.createUnmarshaller();
		if (StringUtils.hasText(charsetName)) {
			try {
				return um.unmarshal(new ByteArrayInputStream(xmlStr.getBytes(charsetName)));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return um.unmarshal(new ByteArrayInputStream(xmlStr.getBytes()));
		}
	}

	/**
	 * bean转换成xml
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String JAXBParseToXml(Object obj) throws JAXBException {
		Marshaller m = createMarshaller(obj, DECLARATION);
		StringWriter sw = new StringWriter();
		sw.append(DECLARATION);
		m.marshal(obj, sw);
		return sw.toString();
	}

	/**
	 * 
	 * @param obj
	 * @param JAXBElement
	 *            <Object> jaxb
	 * @return
	 * @throws JAXBException
	 */
	public static String JAXBParseToXmlNoXmlRoot(Object obj, Object jaxb) throws JAXBException {
		Marshaller m = createMarshaller(obj, DECLARATION);
		StringWriter sw = new StringWriter();
		sw.append(DECLARATION);
		m.marshal(jaxb, sw);
		return sw.toString();
	}

	/**
	 * bean转换成 xml,添加自定义标签
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String JAXBParseToXmlNoSA(Object obj, String label) throws JAXBException {
		Marshaller m = createMarshaller(obj, DECLARATION);
		StringWriter sw = new StringWriter();
		sw.append(DECLARATION);
		if (StringUtils.hasText(label)) {
			sw.append(label);
		}
		m.marshal(obj, sw);
		return sw.toString();
	}

	private static Marshaller createMarshaller(Object obj, String declaration) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller m = context.createMarshaller();
		// 设置XML声明
		if (StringUtils.hasText(declaration)) {
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		}
		return m;
	}

	/**
	 * 获取xml指令信息 <?Label ZOLTAR|RAVL|2024404|SUCCESS?>
	 */
	@SuppressWarnings("unchecked")
	public static String[] getXmlLabel(String xmlMessage) {
		String[] xmlLabel = {};
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new ByteArrayInputStream(xmlMessage.getBytes()));
			List<ProcessingInstruction> list = document.processingInstructions("Label");
			if (!list.isEmpty()) {
				ProcessingInstruction attribute = list.get(0);
				String label = attribute.getText();
				xmlLabel = label.split("\\|");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlLabel;
	}

	/**
	 * 获取xml的NamespaceURI 如：rtav.fidelio.1.0
	 */
	public static String getNamespaceURI(String xmlMessage) {
		String url = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new ByteArrayInputStream(xmlMessage.getBytes()));
			url = document.getRootElement().getNamespaceURI();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String getNamespacePrefix(String xmlMessage, String nameSpace) {
		String url = "";
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new ByteArrayInputStream(xmlMessage.getBytes()));
			url = document.getRootElement().getNamespaceForURI(nameSpace).getPrefix();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 获取XML中的编码
	 * 
	 * @param xmlMessage
	 * @return
	 */
	public static Document getXmlDocument(String xmlMessage) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new StringReader(xmlMessage));
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatXml(String str) throws Exception {
		if (str == null) {
			return null;
		}
		Document document = null;
		document = DocumentHelper.parseText(str);
		// 格式化输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		StringWriter writer = new StringWriter();
		// 格式化输出流
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		// 将document写入到输出流
		xmlWriter.write(document);
		xmlWriter.close();
		return writer.toString();
	}

	/**
	 * 实现dom4j向org.w3c.dom.Document的转换
	 * 
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static org.w3c.dom.Document parse(Document doc) throws Exception {
		if (doc == null) {
			return (null);
		}
		java.io.StringReader reader = new java.io.StringReader(doc.asXML());
		org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
		javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		return (documentBuilder.parse(source));
	}

	public static Document getXmlDocumentXpath(String xml, String namespace) {
		SAXReader saxReader = new SAXReader();
		Map<String, String> map = new HashMap<String, String>();
		map.put("xmlns", namespace);
		saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
		try {
			return saxReader.read(new StringReader(xml));
		} catch (DocumentException e) {
			log.error(e);
		}
		return null;
	}

}
