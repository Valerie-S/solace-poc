package com.cgi.wealth.dp.solace.demo.integration.handler;

import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

/**
 * The type Message handler.
 */
@Component
public class MessageHandler implements BaseMessageHandler<String>{

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    /* Retrieve the name of the queue from the application.properties file
     * Modify the number of concurrencies to enable multi-threading
     * To consume messages from all threads, go to the Queue tab in Solace admin UI 
     * and change the access type of your queue to Non-exclusive
     */
    @JmsListener(destination = "${solace.jms.demoQueueName}", containerFactory = "cFactory", concurrency = "1")
    public void processMsg(Message<String> msg) {
        StringBuffer msgAsStr = new StringBuffer("============= Received \nHeaders:");
        MessageHeaders hdrs = msg.getHeaders();
        msgAsStr.append("\nUUID: "+hdrs.getId());
        msgAsStr.append("\nTimestamp: "+hdrs.getTimestamp());
        Iterator<String> keyIter = hdrs.keySet().iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            msgAsStr.append("\n"+key+": "+hdrs.get(key));
        }
        msgAsStr.append("\nPayload: "+msg.getPayload());
        logger.info(msgAsStr.toString());
    }
}