package com.imran.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperReportHelper {

	public JasperPrint settingUpJasper(String reportNameWithPath, Map parameterMap, JRDataSource jrDataSource) {
		String fileName = "G:/GitDown/jasperPro/JasperReportintgWithSpringMvc/JasperReportintgWithSpringMvc/src/main\\webapp/resources/report/testReport.jrxml";
		System.out.println("String file path:" + reportNameWithPath);

		File f = new File(fileName);
		//InputStream jasperStream = JRLoader.getResourceInputStream(reportNameWithPath);
		System.out.println("Name : "+f.getName());
		
		InputStream jasperStream = null;
		try {
			jasperStream = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// InputStream jasperStream =
		 //this.getClass().getResourceAsStream(reportNameWithPath);

		// System.out.println("Input Strim :"+jasperStream);

		JasperDesign jasperDesign = null;
		JasperReport jasperReport = null;
		try {
			// JasperReport jr =
			// JasperCompileManager.compileReport("G:/GitDown/jasperPro/JasperReportintgWithSpringMvc/JasperReportintgWithSpringMvc/src/main\\webapp/resources/report/testReport.jrxml");
			// System.out.println("Input Strim :"+jr);
			jasperDesign = JRXmlLoader.load(jasperStream);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperReport.getTitle();
			jasperDesign.setTitle(jasperReport.getTitle());
			JasperPrint jasperPrint;
			if (jrDataSource == null) {
				
				jasperPrint = JasperFillManager.fillReport(jasperReport, null);
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
			}
			return jasperPrint;
			//return  jasperReport;
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

}
