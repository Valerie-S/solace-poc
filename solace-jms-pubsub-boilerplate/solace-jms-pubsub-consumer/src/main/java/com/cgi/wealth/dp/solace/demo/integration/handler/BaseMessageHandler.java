package com.cgi.wealth.dp.solace.demo.integration.handler;

import org.springframework.messaging.Message;

/**
 * The interface message handler for Consumer.
 *
 * @param <T> the type parameter
 */
public interface BaseMessageHandler<T> {
    /**
     * Process message from message broker. Retrieve the name of the queue from the application.properties file.
     *
     * @param msg the msg
     */
    void processMsg(Message<T> msg);
}
