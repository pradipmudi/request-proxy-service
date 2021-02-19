package com.fw.https.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fw.https.constants.QueueProperties;
import com.fw.https.model.RequestStatus;

@Component
public class MessageConsumer {
	
	@RabbitListener(queues = QueueProperties.QUEUE)
	public void consumeMessageFromQueue(RequestStatus requestStatus, Message message) {
		System.out.println("Message received from queue : "+requestStatus.toString());
	}

}
