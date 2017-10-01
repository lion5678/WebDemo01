package com.eeitxx.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eeitxx.connect.BaseConnect;

public class BaseConnectDemo implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(BaseConnectDemo.class);
	private Socket client;
	
	public BaseConnectDemo(Socket client){
		this.client = client;
	}
	
	
	@Override
	public void run() {
		 InputStream is = null;
		try {
			log.debug("client ["+client.getInetAddress().getHostAddress()+"] connect...");
			is = client.getInputStream();
			byte[] b = new byte[4];
			is.read(b, 0, 4);
			String str = new String(b);
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}
