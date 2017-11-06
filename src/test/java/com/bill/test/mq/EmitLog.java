package com.bill.test.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 消息producer发布订阅：
 * 1、交换器的使用，规则有：直连，主题，分发，标题
 * 2、临时队列
 * 3、绑定
 * 
 * @author sara
 *
 */
public class EmitLog {
	private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.129");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        /**这里使用的是分发交换器，MQserver服务器上可用的交换器有：
         * direct  直连
         * topic   主题
         * headers 标题
         * fanout  分发**/
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

//      分发消息
        for(int i = 0 ; i < 10; i++){
            String message = "Hello World! " + i;
             channel.basicPublish(EXCHANGE_NAME, "hello", null, message.getBytes());
             System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
