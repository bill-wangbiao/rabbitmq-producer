package com.bill.test.zeroMQ;

import org.zeromq.ZMQ;
/**
 * publisher/subscriber模式
 * @author sara
 *
 */
public class Publisher {
	public static void main(String[] args) {
		ZMQ.Context context=ZMQ.context(1);
		ZMQ.Socket publisher=context.socket(ZMQ.PUB);
		
		publisher.bind("tcp://*:5555");
		
		while(!Thread.currentThread().isInterrupted()) {
			String message="bill hello";
			publisher.send(message.getBytes());
		}
		
		publisher.close();
		context.term();
		
	}
}
