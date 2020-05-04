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
        return factory;
    }

}
