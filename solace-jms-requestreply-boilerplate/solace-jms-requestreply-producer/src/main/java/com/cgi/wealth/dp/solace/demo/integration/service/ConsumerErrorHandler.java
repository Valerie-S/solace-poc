package com.cgi.wealth.dp.solace.demo.integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * The type Consumer error handler.
 */
@Service
public class ConsumerErrorHandler implements ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerErrorHandler.class);
    public void handleError(Throwable t) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        t.printStackTrace(ps);
        try {
            String output = os.toString("UTF8");
            logger.error("============= Error processing message: " + t.getMessage()+"\n"+output);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}