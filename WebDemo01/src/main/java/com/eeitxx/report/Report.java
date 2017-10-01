package com.eeitxx.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

import com.eeitxx.bean.ReportForm;
import com.eeitxx.vm.MenuViewModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Report {
	// 使用slf4j
		private final static Logger log = LoggerFactory.getLogger(Report.class);
	/*
	 * 1.开发人员开发报表设计文件，也就是定义一个*.jrxml文件（就是最开始的那个XML
文件）。
2.使用 JasperReports 提供 API 中的 JasperCompileManager 类编译*.jrxml 文
件，编译后生成一个*.jasper 文件。
3.使用JasperReports提供API中的JasperFillManager类填充编译后的*.jasper
文件，填充后生成一个*.jrprint 文件。
4.使用导出管理器（JasperExportManager）或 者各种格式的文件导出器
（JRXxxExporter）将*.jrprit 文件导出成各种格式的报表文件。也可以使用 JRViewer
工具类来直接浏览报表。

	 */
		public static ByteArrayOutputStream getReport(String sourceFileName, Map<String, Object> params, List<?> list) throws JRException{
			File file = new File(sourceFileName);
			if(!file.exists()){
				throw new JRException("file["+ file +"] not found");
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String extension = sourceFileName.substring(sourceFileName.lastIndexOf("."));
			JasperReport jasperReport = null;
			if(".jasper".equalsIgnoreCase(extension)){
				jasperReport = (JasperReport) JRLoader.loadObject(file);
			}else if(".jrxml".equalsIgnoreCase(extension)){
				jasperReport = JasperCompileManager.compileReport(sourceFileName);
//				log.debug("sourceFileName["+sourceFileName);
//				log.debug("jasperReport["+JasperCompileManager.compileReportToFile(sourceFileName));
			}else{
				throw new JRException("檔案["+sourceFileName+"]異常");
			}
			
			JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(list));
			
			JasperExportManager.exportReportToPdfStream(jp, baos);
			return baos;
		}
	
	
	/**
	 * 取得report的OutputStream
	 * @param sourceFileName *.jasper
	 * @param params
	 * @param list
	 * @return
	 * @throws JRException
	 */
	private ByteArrayOutputStream getReport(ReportForm reportform) throws JRException{
		String localPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/")
				+ "WEB-INF" + File.separator + "jrxml" + File.separator+ reportform.getFilePath();
		File reportFile = new File(localPath);
		log.debug("file[" + localPath + "]");
		if (!reportFile.exists()) {
			throw new JRRuntimeException("file not found.");
		}
		Map params = reportform.getParamMap();
		List<Object> dataList = reportform.getDataList();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		JasperReport jasperReport = JasperCompileManager.compileReport(localPath);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
		
		JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(dataList));
		JasperExportManager.exportReportToPdfStream(jp, baos);
		return baos;
	}
	
	/**
	 * 開新視窗顯示pdf
	 * @param title
	 * @param baos
	 */
	private void display(String title, ByteArrayOutputStream baos){
		AMedia amedia = new AMedia(title, "pdf","application/pdf", baos.toByteArray());
		final Window win = (Window) Executions.createComponents("report/reportView.zul", null, null);
        Iframe report = (Iframe)win.getFellow("report"); 
        report.setContent(amedia);				
        win.doModal();
        
        log.debug("Done jasperReport Output");
	}
}
