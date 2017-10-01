package com.eeitxx.connect;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eeitxx.vm.MenuViewModel;

public class Connector implements Runnable {

	private final static Logger log = LoggerFactory.getLogger(MenuViewModel.class);
	private BufferedInputStream bis = null;
	private ServerSocket server;
	public Connector(ServerSocket server){
		this.server = server;
	}
	
	@Override
	public void run() {
		Socket client = null;
		while (true) {
			try {
				client = server.accept();
				log.debug("client["+client.getInetAddress().getHostAddress()+"] 已連線...");
				byte[] lenByte = new byte[5];
				bis = new BufferedInputStream(client.getInputStream());
				bis.read(lenByte);
				
				if("".equals(new String(lenByte))){
					continue;
				}
				int len = Integer.parseInt(new String(lenByte));
				byte[] data = new byte[len];
				int nowlen = 0;
				while((nowlen = bis.read(data)) != -1){
					System.out.println("recv Hex[" + DatatypeConverter.printHexBinary(data) + "]");
					if(len == nowlen){
						System.out.println("data["+new String(data,0,nowlen)+"]");
					}else{
						System.out.println("["+new String(data,0,nowlen)+"] 格式錯誤!!");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void destroy(){
		if(server != null){
			try {
				server.close();
				log.debug("port["+server.getLocalPort()+"] 已關閉");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
