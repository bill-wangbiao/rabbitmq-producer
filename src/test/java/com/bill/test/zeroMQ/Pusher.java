package com.bill.test.zeroMQ;

import org.zeromq.ZMQ;

/**
 * push/pull模式
 * 一个pusher多个pull
 * @author sara
 *
 */
public class Pusher {

	public static void main(String[] args) {
		ZMQ.Context context=ZMQ.context(1);
		ZMQ.Socket push=context.socket(ZMQ.PUSH);
		
		push.bind("ipc://bill");
		
		for (int i = 0; i < 10; i++) {
			push.send("hello".getBytes());
			
		}
		push.close();
		context.term();
	}

}
