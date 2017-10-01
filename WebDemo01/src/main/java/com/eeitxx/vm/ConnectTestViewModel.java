package com.eeitxx.vm;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.eeitxx.connect.BaseConnect;
import com.eeitxx.connect.Connector;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ConnectTestViewModel {
	private final static Logger log = LoggerFactory.getLogger(ConnectTestViewModel.class);
	private StringBuffer label;
	private String ipaddr;
	private static final int THREAD_COUNT = 1;
    private static final Executor exec = Executors.newFixedThreadPool(THREAD_COUNT);
    
//	private ServerSocket server = null;
	
	@WireVariable
	private BaseConnect hostConnector;
	
	public void setLabel(StringBuffer label) {
		this.label = label;
	}

	public StringBuffer getLabel() {
		return label;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	@Command("conntest")
	@NotifyChange({"label", "ipaddr"})
	public void connect_test() throws IOException{
		ServerSocket server = hostConnector.getServer();
		exec.execute(new Connector(server));
		
	}
}
