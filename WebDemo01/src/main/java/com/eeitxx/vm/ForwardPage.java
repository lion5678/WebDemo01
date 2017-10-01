package com.eeitxx.vm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

/**
 * 
 *  Description: 全域導頁用
 *  @author hp  DateTime 2017年6月8日 上午11:59:17 
 *  @version 1.0
 */
public class ForwardPage {
	private static final Logger log = LogManager.getLogger(ForwardPage.class);
	private String page = "main.zul";
	
	public void setPage(String page) {
		this.page = page;
	}

	public String getPage() {
		log.debug("page: "+page);
		return page;
	}
	
	@GlobalCommand
	@NotifyChange("page")
	public void forward(@BindingParam("data1") String data){
		log.debug("forward..."+page);
		this.page = data;
		log.debug("forward..."+page);
	}
}
