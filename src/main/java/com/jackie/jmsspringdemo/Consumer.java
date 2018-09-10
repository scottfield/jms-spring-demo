package com.jackie.jmsspringdemo;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            String id = message.getStringProperty("id");
            String errorMessage = "consume message failed,message id:" + id;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage);
        } catch (JMSException e) {

        }

    }
}
