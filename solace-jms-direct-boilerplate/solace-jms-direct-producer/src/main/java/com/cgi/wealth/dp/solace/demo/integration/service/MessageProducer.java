package com.cgi.wealth.dp.solace.demo.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Message producer.
 */
@Service
public class MessageProducer implements CommandLineRunner, BaseMessageProducer<String> {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    private final JmsTemplate jmsTemplate;
    private int numOfMessages = 1000;
    @Value("${solace.jms.testTopic}")
    private String myTopic;

    /**
     * Instantiates a new Message producer.
     *
     * @param jmsTemplate the jms template
     */
    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args){
        for(int i = 0; i < numOfMessages; i ++) {
            String msg = String.format("Producer sent message %s", i);
            if (logger.isDebugEnabled()) {
                String output = String.format("============= %s", msg);
                logger.info(output);
            }
            sendMsg(msg);
        }
    }

    @Override
    public void sendMsg(String msg) {
        this.jmsTemplate.convertAndSend(myTopic, msg);
    }
}
