package com.cgi.wealth.dp.solace.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * The type Reply sender configuration.
 */
@Configuration
public class ProducerConfiguration {

    private final ConnectionFactory connectionFactory;

	/**
	 * Instantiates a new Producer configuration.
	 *
	 * @param connectionFactory the connection factory
	 */
	public ProducerConfiguration(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * Jms template jms template.
	 *
	 * @return the jms template
	 */
// Example use of CachingConnectionFactory for the producer
	@Bean
	public JmsTemplate jmsTemplate() {
		CachingConnectionFactory ccf = new CachingConnectionFactory(connectionFactory);
		JmsTemplate jt = new JmsTemplate(ccf);
		jt.setPubSubDomain(false);
		jt.setDeliveryPersistent(true);
		return jt;
	}
}
