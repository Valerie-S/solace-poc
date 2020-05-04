package com.cgi.wealth.dp.solace.demo.integration.service;

/**
 * The interface message producer for Producer.
 *
 * @param <T> the type parameter
 */
public interface BaseMessageProducer<T> {
    /**
     * Send message to message broker.
     *
     * @param msg the msg
     */
    void sendMsg(T msg);
}
