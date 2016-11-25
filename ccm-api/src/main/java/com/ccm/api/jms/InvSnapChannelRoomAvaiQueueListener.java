package com.ccm.api.jms;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InvSnapChannelRoomAvaiQueueListener implements MessageListener{
    @Resource
    private RoomJmsManager roomJmsManager;
    
    protected final Log log = LogFactory.getLog(getClass());
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage objMessage = (ObjectMessage)message;
                Object obj = objMessage.getObjectProperty("rsvMap");
                if(obj!=null){
                	roomJmsManager.invSnapChannelRoomAvai((Map<String, String>)obj);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                log.info(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
            log.error("Message must be of type MapMessage");
        }
    }

}
