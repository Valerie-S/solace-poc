package com.cgi.wealth.dp.solace.demo.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;
import javax.jms.JMSException;
import javax.jms.Message;


/**
 * The type Message handler.
 */
@Component
public class MessageHandler{

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);


    /**
     * On message.
     *
     * @param msg the msg
     * @throws JMSException the jms exception
     */
    /* Retrieve the name of the queue from the application.properties file
     * Modify the number of concurrencies to enable multi-threading
     * To consume messages from all threads, go to the Queue tab in Solace admin UI 
     * and change the access type of your queue to Non-exclusive
     */
    @JmsListener(destination = "${solace.jms.replyQueue}", containerFactory = "cFactory", selector = "",  concurrency = "1")
    public void processMsg(Message msg) {
        logger.info("Response received");
    }
}