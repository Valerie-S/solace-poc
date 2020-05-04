package com.cgi.wealth.dp.solace.demo.web;

import com.cgi.wealth.dp.solace.demo.integration.service.MessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Message controller.
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
     * Create.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
        messageProducer.run();
    }
}
