package com.bill.test.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MqProducerTest {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.129");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		/**消息持久化：durable和下面的MessageProperties.PERSISTENT_TEXT_PLAIN配置
		 * 作用：保证消息在mqserver宕机的情况下，消息不丢失；
		 * 实现原理：把消息保存在磁盘上，记住消息的状态和内容**/
		boolean durable=true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		String message = "Hello World!";
		for(int i=0;i<10;i++) {
			//MessageProperties.PERSISTENT_TEXT_PLAIN
			channel.basicPublish("", QUEUE_NAME,null , (message+i).getBytes("UTF-8"));
			System.out.println("发送完成"+message+i);

		}
		channel.close();
		connection.close();
	}
}
