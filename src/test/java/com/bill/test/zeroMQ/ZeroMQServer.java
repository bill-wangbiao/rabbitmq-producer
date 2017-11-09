package com.bill.test.zeroMQ;

import java.io.UnsupportedEncodingException;

import org.zeromq.ZMQ;
/**
 * Request/Subscribe 模式
 * @author sara
 *
 */
public class ZeroMQServer {

	public static void main(String[] args) {
		/**
		 * 创建一个IO线程
		 */
		ZMQ.Context context=ZMQ.context(1);
		/**创建一个response类型的socket，用于接收request发送的请求**/
		ZMQ.Socket socket=context.socket(ZMQ.REP);
		
		/**绑定5555的端口**/
		socket.bind("tcp://*:5555");
		
		int i=0;
		int number=0;
		
		while(!Thread.currentThread().isInterrupted()) {
			i++;
			if(i==10000) {
				i=0;
				System.out.println("***********number+1"+(++number));
			}
			/**获取request发送的数据**/
			byte[] request=socket.recv();
			String rep="";
			try {
				rep=new String(request,"utf-8");
				System.out.println(""+rep);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String response=rep+"world";
			/**向request端发送响应结果：必须返回否则，socket.recv将会出错，可以理解为强制走完request/response流程**/
			socket.send(response.getBytes());
		}
		/**关闭socket，当前线程上下文**/
		socket.close();
		context.term();
	}

}
