package com.eeitxx.vm;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import com.eeitxx.report.Report;

import net.sf.jasperreports.engine.JRException;

public class ReportDemo01ViewModel {


	private static final Logger log = LogManager.getLogger(ReportDemo01ViewModel.class);
	private Media media;
	
//	private String[] addr1={"臺北市大安區金山南路1段55號","臺北市大安區金山南路2段55號","臺北市大安區金山南路3段55號","臺北市大安區金山南路4段55號","臺北市大安區金山南路5段55號"};
//	private String[] addr2={"臺北市大安區金山南路1段50號","臺北市大安區金山南路2段51號","臺北市大安區金山南路3段52號","臺北市大安區金山南路4段53號","臺北市大安區金山南路5段54號"};
//	private String[] addr3={"臺北市大安區金山南路1段1號","臺北市大安區金山南路2段2號","臺北市大安區金山南路3段3號","臺北市大安區金山南路4段4號","臺北市大安區金山南路5段5號"};
	private String[] addr1={"台北市內湖區瑞光路399號13樓"};
	private String[] addr2={"地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地"};
	private String[] addr3={"地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地址地"};
	
	@Command
	@NotifyChange("media")
	public void getReport(){
		String sourceFileName = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/WEB-INF/jrxml/Blank_A6.jrxml");
//		String sourceFileName = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/WEB-INF/jrxml/Blank_A4.jasper");
		log.debug(sourceFileName);
		Map<String, Object> params = new HashMap<>();
//		String[] array2list = {"Data1","Data2","Data3","Data4","Data5"};
		ArrayList list = new ArrayList<>();
		ByteArrayOutputStream baos = null;
		
		for(int i=0;i<5;i++){
			UserDemo bean = new UserDemo();
			bean.setName("X大明X");
			String id = String.format("1%05d1001561022000%d", i+1, i);
			bean.setId(id);
			bean.setAddr1(addr1[0]);
			bean.setPostalCode("22000");
			bean.setCif(String.format("012345678%d", i+4));
			list.add(bean);
		}
		
		try {
			baos = Report.getReport(sourceFileName, params, list);
//			log.debug(baos);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		AMedia amedia = new AMedia("PDFDemo", "pdf","application/pdf", baos.toByteArray());
		HashMap<String, Object> subMap = new HashMap<>();
		subMap.put("media", media);
		subMap.put("title", "MyFirstReportDemo");
//		Window win = (Window)Executions.createComponents("/WEB-INF/reportTemplate.zul", null, subMap);
//		Iframe iframe = new Iframe();
//		iframe.setContent(amedia);
//		win.appendChild(iframe);
////		media = amedia;
//		win.doModal();
	}
	
	
	public class UserDemo{
		private String name;
		private String addr1;
		private String addr2;
		private String addr3;
		private String cif;
		private String postalCode;
		private String id;
		
//		10603
		public String getAddr1() {
			return addr1;
		}
		public void setAddr1(String addr1) {
			this.addr1 = addr1;
		}
		public String getAddr2() {
			return addr2;
		}
		public void setAddr2(String addr2) {
			this.addr2 = addr2;
		}
		public String getAddr3() {
			return addr3;
		}
		public void setAddr3(String addr3) {
			this.addr3 = addr3;
		}
		public String getCif() {
			return cif;
		}
		public void setCif(String cif) {
			this.cif = cif;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
	}
}
