package com.ccm.api.jms.impl;


import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.ccm.api.jms.OTAJmsManager;
import com.ccm.api.model.log.SendOTALog;

@Service("otaJmsManager")
public class OTAJmsManagerImpl implements OTAJmsManager {
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination otaQueue;
	
	@Override
	public void sendOTAmsg(final SendOTALog sendOTALog) {
		// TODO Auto-generated method stub
		this.jmsTemplate.send(this.otaQueue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(sendOTALog);
				return message;
			}
		});
		log.info("send ok");
	}

}
