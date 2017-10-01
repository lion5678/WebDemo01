package com.eeitxx.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ServerCloneException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eeitxx.connect.BaseConnect;

public class BaseServer {
	private static final Logger log = LoggerFactory.getLogger(BaseConnect.class);
	private static final int THREAD_COUNT = 10;
	private static final Executor exec = Executors.newFixedThreadPool(THREAD_COUNT);
	private ServerSocket server;
    
	public BaseServer(int port){
		try {
			server = new ServerSocket(port);
			log.debug("port [" + port + "] Listen...");
			while (true) {
				exec.execute(new BaseConnectDemo(server.accept()));
			}
		} catch (IOException e) {
			log.error("port [" + port + "] 被佔用...");
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ServerSocket getServer(){
		return server;
	}
	
//	@Override
//	public void run() {
//		try {
//			while (true) {
//				exec.execute(new BaseConnectDemo(server.accept()));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				server.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
