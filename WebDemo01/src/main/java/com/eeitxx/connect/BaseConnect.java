package com.eeitxx.connect;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseConnect{

	

	private static final Logger log = LoggerFactory.getLogger(BaseConnect.class);
	private int port;
	private String ip;
	private String type;
	private ServerSocket server;
	private static final int THREAD_COUNT = 1;
    private static final Executor exec = Executors.newFixedThreadPool(THREAD_COUNT);
    
	public int getPort() {
		return port;
	}
//	public void setPort(int port) {
//		this.port = port;
//	}
	public String getIp() {
		return ip;
	}
//	public void setIp(String ip) {
//		this.ip = ip;
//	}
	public ServerSocket getServer() {
		return server;
	}
	
	public BaseConnect(int port, String ip, String type) {
		super();
		this.port = port;
		this.ip = ip;
		this.type = type;
		if("server".equalsIgnoreCase(type)){
			try {
				server = new ServerSocket(port);
				log.debug("port["+server.getLocalPort()+"] 開始監聽");
			} catch (IOException e) {
				log.error("port["+server.getLocalPort()+"] 已被佔用...");
				e.printStackTrace();
			}
		}else if("client".equalsIgnoreCase(type)){
//			while(){
//				
//			}
			try {
				Socket socket = new Socket(ip, port);
			} catch (IOException e) {
				log.debug("",e);
				e.printStackTrace();
			}
		}
	}

//	public void init() throws IOException{
//		Socket socket = server.accept();
//		Runnable task = new Runnable() {
//			@Override
//			public void run() {
//				try {
//					handleReq(socket);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		exec.execute(task);
//	}

//	public static void handleReq(Socket socket) throws IOException{
//		log.debug(socket.getInetAddress().getHostAddress()+" connected...");
//		InputStream is = new BufferedInputStream(socket.getInputStream());
//		byte[] b = new byte[5];
//		is.read(b);
//		System.out.println(new String(b));
//	}
//	@Override
//	public void run() {
//		while(true){
//			try {
//				log.debug("等待連線中...");
//				ss.accept();
//				log.debug(socket.getInetAddress().getHostAddress()+" connected...");
//				InputStream is = new BufferedInputStream(socket.getInputStream());
//				byte[] b = new byte[5];
//				is.read(b);
//				System.out.println(new String(b));
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	public void destroy(){
		if(server != null){
			try {
				server.close();
				log.debug("port["+server.getLocalPort()+"] 已關閉");
			} catch (IOException e) {
				log.error("",e);
				e.printStackTrace();
			}
		}
	}
}
