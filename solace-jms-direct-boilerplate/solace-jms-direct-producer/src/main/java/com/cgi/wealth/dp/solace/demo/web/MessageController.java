package com.cgi.wealth.dp.solace.demo.web;

import com.cgi.wealth.dp.solace.demo.integration.service.MessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The Message controller.
 */
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private MessageProducer messageProducer;

    /**
     * Instantiates a new Message controller.
     *
     * @param messageProducer the message producer
     */
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * Create a message to be delivered to message broker via REST API
     *
     * @param msg the msg
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody String msg) {
        messageProducer.sendMsg(msg);
    }
}
