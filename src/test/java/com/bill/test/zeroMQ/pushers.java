package com.bill.test.zeroMQ;

import org.zeromq.ZMQ;

/**
 * pull/push模式
 * 一个pull多个push模式
 * @author sara
 *
 */
public class pushers {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(
					new Runnable() {

						@Override
						public void run() {
							ZMQ.Context context=ZMQ.context(1);
							ZMQ.Socket push=context.socket(ZMQ.PUSH);
							
							push.connect("ipc://bill");
							
							for(int i=0;i<100;i++) {
								System.out.println("*****push端正在push中。。。。。。。*******");
								String message="hello"+i;
								push.send(message.getBytes());
								System.out.println("*******发送出去的数据："+message);
							}
							push.close();
							context.term();
						}
						
					}
					).start();
		}
	}

}
