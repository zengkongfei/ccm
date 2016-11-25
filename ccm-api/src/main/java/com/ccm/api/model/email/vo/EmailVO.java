/**
 * 
 */
package com.ccm.api.model.email.vo;

import java.io.Serializable;

/**
 * @author Jenny Liao
 *
 */
public class EmailVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3983589962087664835L;
	
	private String from;  
    private String to;  
    private String[] toArray;
    private String subject;  
    private String content;  
    private String templateName;  
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}  
    
    /**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the toArray
	 */
	public String[] getToArray() {
		return toArray;
	}

	/**
	 * @param toArray the toArray to set
	 */
	public void setToArray(String[] toArray) {
		this.toArray = toArray;
	}
    
}
