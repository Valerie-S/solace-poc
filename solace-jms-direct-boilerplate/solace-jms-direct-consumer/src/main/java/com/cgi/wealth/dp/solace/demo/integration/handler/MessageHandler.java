package com.cgi.wealth.dp.solace.demo.integration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Message handler for Consumer.
 */
@Component
public class MessageHandler implements BaseMessageHandler<String>{
    
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    /**
     * Process message from message broker. Retrieve the name of the queue from the application.properties file.
     * Modify the number of concurrencies to enable multi-threading
     * To consume messages from all threads, go to the Queue tab in Solace admin UI and change the access type of your queue to Non-exclusive
     *
     * @param msg the message
     */
    @Override
    @JmsListener(destination = "${solace.jms.demoTopicEndpoint}", containerFactory = "cFactory", concurrency = "1")
    public void processMsg(Message<String> msg) {
        StringBuilder msgAsStr = new StringBuilder("============= Received \nHeaders:");
        MessageHeaders hdrs = msg.getHeaders();
        msgAsStr.append("\nUUID: "+hdrs.getId());
        msgAsStr.append("\nTimestamp: "+hdrs.getTimestamp());
        Iterator<String> keyIter = hdrs.keySet().iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            msgAsStr.append("\n"+key+": "+hdrs.get(key));
        }
        msgAsStr.append("\nPayload: "+msg.getPayload());
        if (logger.isDebugEnabled()) {
            logger.debug(msgAsStr.toString());
        }
    }
}
