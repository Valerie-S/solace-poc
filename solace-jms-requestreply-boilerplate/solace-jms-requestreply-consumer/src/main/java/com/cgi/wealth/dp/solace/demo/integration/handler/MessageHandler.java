package com.cgi.wealth.dp.solace.demo.integration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import javax.jms.Session;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;


/**
 * The type Message handler.
 */
@Component
public class MessageHandler implements SessionAwareMessageListener<Message>{

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);


    /**
     * On message.
     *
     * @param msg        the msg
     * @param session    the session
     * @throws JMSException the jms exception
     */
    /* Retrieve the name of the queue from the application.properties file
     * Modify the number of concurrencies to enable multi-threading
     * To consume messages from all threads, go to the Queue tab in Solace admin UI 
     * and change the access type of your queue to Non-exclusive
     */
    @JmsListener(destination = "${solace.jms.sendQueue}", containerFactory = "cFactory", selector = "",  concurrency = "1")
    public void onMessage(Message msg, Session session) throws JMSException {
        Message replyMsg = session.createMessage();
        replyMsg.setJMSCorrelationID(msg.getJMSCorrelationID());
        String replyContent = "Received message " + msg.getJMSCorrelationID() + ". Sending the response.";
        replyMsg.setStringProperty("response", replyContent);

        // send responses with a new producer everytime when the listener receives a new message
        final MessageProducer producer = session.createProducer(msg.getJMSReplyTo());
        logger.info("Sending reply message to queue " + String.valueOf(msg.getJMSReplyTo()));
        
        producer.send(replyMsg);
    }
}