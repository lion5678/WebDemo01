package com.eeitxx.vm;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;


public class ReportTemplateVM {
	private static final Logger log = LogManager.getLogger(ReportDemo01ViewModel.class);
	private Media media;
	private String title;
	
	public Media getMedia() {
		return media;
	}

//	public void setMedia(Media media) {
//		this.media = media;
//	}

	public String getTitle() {
		return title;
	}

	// 初始化執行 @ExecutionArgParam 接收 Executions.createComponents 傳入的參數
	@Init
	public void getInitParams(@ExecutionArgParam("media") Media media, @ExecutionArgParam("title") String title){
		log.debug(media+", title["+title+"]");
		this.media = media;
		this.title = title;
	}
}
