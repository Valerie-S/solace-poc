package com.cgi.wealth.dp.solace.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Wealth spring boot solace jms pubsub consumer application.
 */
@SpringBootApplication
public class WealthSpringBootSolaceJmsPubsubConsumerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WealthSpringBootSolaceJmsPubsubConsumerApplication.class, args);
    }

}
