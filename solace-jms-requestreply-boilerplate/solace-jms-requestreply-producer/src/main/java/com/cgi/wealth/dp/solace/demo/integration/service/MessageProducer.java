package com.cgi.wealth.dp.solace.demo.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.Queue;

/**
 * Message producer.
 */
@Service
public class MessageProducer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;

    private int numOfMessages = 1000;

    @Value("${solace.jms.sendQueue}")
    private String sendQueue;

    @Value("${solace.jms.replyQueue}")
    private String reply;

    /**
     * Instantiates a new Message producer.
     *
     * @param jmsTemplate the jms template
     */
    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void run(String... args) throws Exception{
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);
        Session session = jmsMessagingTemplate.getConnectionFactory().createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        // create replyQueue by ourselves since there is no spring boot magic here
        Queue replyQueue = session.createQueue(reply);

        for(int i = 0; i < numOfMessages; i ++) {
            Message message = session.createMessage();
            message.setJMSCorrelationID(UUID.randomUUID().toString());
            String content = "Sending messsage from producer. Waiting for response.";
            logger.info("Sending message to new/send");
            message.setStringProperty("message", content);
            //set destination for the replies
            message.setJMSReplyTo(replyQueue);
            if (logger.isDebugEnabled()) {
                String output = String.format("============= You will be able to view the responses at %s", String.valueOf(message.getJMSReplyTo()));
                logger.info(output);
            }
            this.jmsMessagingTemplate.convertAndSend(sendQueue, message);
        }
    }
}
