package com.jackie.jmsspringdemo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.CollectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;
import javax.jms.Session;

@SpringBootApplication
public class JmsSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsSpringDemoApplication.class, args);
    }

    @Bean
    public MessageListenerContainer container(ConnectionFactory connectionFactory, MessageListener listener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(listener);
        container.setDestinationName("testqueue");
        container.setSessionTransacted(true);
        container.setErrorHandler(t -> {
            throw new RuntimeException(t);
        });
        return container;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        factory.setUserName("admin");
        factory.setPassword("admin");
        return factory;
    }
}
