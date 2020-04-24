package com.cgi.wealth.dp.solace.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Producer configuration for JMS Connnection Factory.
 */
@Configuration
public class ProducerConfiguration {

	private final ConnectionFactory connectionFactory;

	/**
	 * Instantiates a new producer configuration.
	 *
	 * @param connectionFactory the connection factory - inject Solace Connection Factory
	 */
	public ProducerConfiguration(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	/**
	 * JMS template - Example use of CachingConnectionFactory for the producer
	 *
	 * @return the JMS template
	 */
	@Bean
	public JmsTemplate jmsTemplate() {
		CachingConnectionFactory ccf = new CachingConnectionFactory(connectionFactory);
		JmsTemplate jt = new JmsTemplate(ccf);
		/**
		 * pubSubDomain - "true" for the Publish/Subscribe domain (Topics), "false" for the Point-to-Point domain (Queues)
		 * It is set to false by default and no need for specification while publishing to Queues
		 * The pubsub messsaing needs to publish to topics here 
		*/
		jt.setPubSubDomain(true);
		return jt;
	}
}
