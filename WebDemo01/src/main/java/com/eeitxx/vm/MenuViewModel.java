package com.eeitxx.vm;



import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.security.auth.login.FailedLoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.eeitxx.connect.BaseConnect;


public class MenuViewModel {

//	List treeModel;
	
	// 使用log4j
//	private static final Logger log = LogManager.getLogger(MenuViewModel.class);
	// 使用slf4j
	private final static Logger log = LoggerFactory.getLogger(MenuViewModel.class);
	

	
	
	@Command("getTree")
	public void getTreeModel(){
		
	}
	
	
	@Command
	public void callFunctin(@BindingParam("data1") String data){
		log.debug("callFunctin {}", data);
		
//		log.debug("ip[{}],port[{}]", hostConnector.getIp(), hostConnector.getPort());
	}
	
	@Command("callConnect")
	public void connectToHost(){
//		log.debug("connectToHost ...");
//		System.out.println("connect...");
		
	}
	

}
