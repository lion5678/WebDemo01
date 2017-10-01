package com.eeitxx.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

/**
 *  Description: 開新視窗，驗証帳號權限
 *  @author hp  DateTime 2017年5月15日 上午8:58:08 
 *  @version 1.0
 */
public class AuthWindow {
	private final static Logger log = LoggerFactory.getLogger(AuthWindow.class);
	private Window subwin;
	private String id;
	private String passwd;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public void getSubwin(Component arg0, Map map){
		subwin = (Window) Executions.createComponents("/zul/authority.zul", arg0, map);
		id = "";
		passwd = "";
		subwin.doModal();
	}
	
	@Command()
	public void checkAuth(@ContextParam(ContextType.VIEW) Component view){
		Component parentwin = view.getParent();
		log.debug(view.getParent().toString());
		log.debug("id["+id+"]");
		log.debug("passwd["+passwd+"]");
		log.debug("subwin["+view+"]");
		Events.postEvent("onSubmit", parentwin, true);
//		log.debug("e["+e+"]");
		view.detach();
	}
}
