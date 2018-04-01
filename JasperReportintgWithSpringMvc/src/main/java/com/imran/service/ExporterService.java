package com.imran.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.springframework.stereotype.Service;

@Service
public class ExporterService {
	
	public static final String MEDIA_TYPE_EXCEL = "application/vnd.ms-excel";
	public static final String MEDIA_TYPE_PDF = "application/pdf";
	public static final String EXTENSION_TYPE_EXCEL = "xls";
	public static final String EXTENSION_TYPE_PDF = "pdf";
	
	public HttpServletResponse export(String type,JasperPrint jp, HttpServletResponse response, ByteArrayOutputStream baos) throws IOException {
		
		if (type.equalsIgnoreCase(EXTENSION_TYPE_PDF)) {
			// Export to output stream
			exportPdf(jp, baos);
			 
			// Set our response properties
			// Here you can declare a custom filename
			String fileName = "UserReport.pdf";
			response.setHeader("Content-Disposition", "inline; filename="+ fileName);
			
			// Set content type
			response.setContentType(MEDIA_TYPE_PDF);
			response.setContentLength(baos.size());
			
			return response;
			
		} 
		
		throw new RuntimeException("No type set for type " + type);
	}
	

	
	public void exportPdf(JasperPrint jasperPrint, ByteArrayOutputStream baos) throws IOException {
		// Create a JRXlsExporter instance
		JRPdfExporter exporter = new JRPdfExporter();
		 
		// Here we assign the parameters jp and baos to the exporter
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		HttpServletResponse response = null;
		try {
			
		    response.setContentType("application/x-pdf");
		    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		    final OutputStream outStream = response.getOutputStream();
			
			 JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
