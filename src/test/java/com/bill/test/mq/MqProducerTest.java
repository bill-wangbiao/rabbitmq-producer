package com.bill.test.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqProducerTest {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		for(int i=0;i<10;i++) {
			channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes("UTF-8"));
		}
		
		System.out.println("发送完成"+message);

		channel.close();
		connection.close();
	}
}
