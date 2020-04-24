package com.cgi.wealth.dp.solace.demo.config;

import com.cgi.wealth.dp.solace.demo.integration.handler.ConsumerErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * Consumer configuration for JMS Connnection Factory.
 */
@EnableJms
@Configuration
public class ConsumerConfiguration {
    /**
     * Default JMS listener container factory.
     *
     * @param connectionFactory the connection factory - inject Solace Connection Factory
     * @param errorHandler      the custom error handler
     * @return the default JMS listener container factory
     */
    @Bean
    public DefaultJmsListenerContainerFactory cFactory(ConnectionFactory connectionFactory, ConsumerErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        /**
		 * pubSubDomain - "true" to consume from a Topic Endpoint, "false" to Consume from a Queue
		 * It is set to false by default to consume from Queues
		 * A topic endpoint will subscribe to one and only one topic automatically and will be destructed when the application stops
         * In contrast, Queues will remain in the list when the application stops
		*/
        factory.setPubSubDomain(true);
        return factory;
    }

}
