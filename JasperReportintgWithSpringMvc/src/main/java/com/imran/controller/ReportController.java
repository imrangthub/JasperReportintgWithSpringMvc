package com.imran.controller;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.imran.model.Book;
import com.imran.service.BookService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	
 
	@Autowired
	BookService bookService;
	
	
	@ResponseBody
	@RequestMapping(value = "/singlePrint", method = RequestMethod.GET)
	public void singlePrint(ModelMap map, HttpServletRequest reqParams, HttpServletResponse response) throws JRException, IOException {		
		     long id = Long.parseLong(reqParams.getParameter("id"));			    
		     Map<String, Object> parameterMap = new HashMap<String, Object>();
		     
		 	Book singleObj = null;
			List<Book> bookList =  bookService.getById(id);
			for(Book b: bookList){
				singleObj = b;
			}

            parameterMap.put("pageTitle", "Book List");
            parameterMap.put("pageFooter", "imranmadbar@gmail.com");
            parameterMap.put("companyName", "CS ifo tech");
            parameterMap.put("bookName", singleObj.getName());
            parameterMap.put("bookType", singleObj.getType());
            parameterMap.put("companyName", "CS ifo tech");
	    
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
			
	    File initialFile = new File("G:\\GitDown\\JasperReportintgWithSpringMvc\\JasperReportintgWithSpringMvc\\src\\main\\webapp\\resources\\report\\singleBook.jrxml");
	    InputStream jasperStream = null;
			try {
				
				jasperStream = new FileInputStream(initialFile);
				jasperReport = JasperCompileManager.compileReport(jasperStream);
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, new JREmptyDataSource());
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final OutputStream outStream = response.getOutputStream();
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		    response.getOutputStream().flush();
		    response.getOutputStream().close();
	}

	
	
	  @ResponseBody
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  HttpEntity<byte[]> getRpt1(HttpServletResponse response) throws JRException, IOException {

			List<Book> bookList =  bookService.bookList();	
		    JRDataSource jrDataSource = new JRBeanCollectionDataSource(bookList);
		    
	        Map<String, Object> parameterMap = new HashMap<String, Object>();
		   
	            parameterMap.put("title", "Book List");
	            parameterMap.put("reportFooter", "imranmadbar@gmail.com");
	            parameterMap.put("companyName", "CS ifo tech");
	            parameterMap.put("datasource", jrDataSource);
		    
			JasperReport jasperReport = null;
			JasperPrint jasperPrint = null;
				
		    File initialFile = new File("G:\\GitDown\\JasperReportintgWithSpringMvc\\JasperReportintgWithSpringMvc\\src\\main\\webapp\\resources\\report\\bookList.jrxml");
		    InputStream jasperStream = null;
				try {
					
					jasperStream = new FileInputStream(initialFile);
					jasperReport = JasperCompileManager.compileReport(jasperStream);
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        byte[] document = JasperExportManager.exportReportToPdf(jasperPrint);
		
		        HttpHeaders header = new HttpHeaders();
		        header.setContentType(new MediaType("application", "pdf"));
		        header.set("Content-Disposition", "inline; filename=outputBytedata.pdf");
		        header.setContentLength(document.length);
		
		        return new HttpEntity<byte[]>(document, header);
	  }
	
	
	
	
//	
//	 @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/pdf")
//    public @ResponseBody HttpEntity<byte[]> downloadB() throws IOException {
//        File file = new File("G:\\GitDown\\JasperReportintgWithSpringMvc\\JasperReportintgWithSpringMvc\\src\\main\\webapp\\WEB-INF\\file\\allBookList.pdf");
//        byte[] document = FileCopyUtils.copyToByteArray(file);
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(new MediaType("application", "pdf"));
//        header.set("Content-Disposition", "inline; filename=" + file.getName());
//        header.setContentLength(document.length);
//
//        return new HttpEntity<byte[]>(document, header);
//    }
//	
	
	
	
//	  @ResponseBody
//	  @RequestMapping(value = "/all", method = RequestMethod.GET)
//	  public void getRpt1(HttpServletResponse response) throws JRException, IOException {
//
//			List<Book> bookList =  bookService.bookList();	
//		    JRDataSource jrDataSource = new JRBeanCollectionDataSource(bookList);
//		    
//			JasperReport jasperReport = null;
//			JasperPrint jasperPrint = null;
//				
//		    File initialFile = new File("G:\\GitDown\\JasperReportintgWithSpringMvc\\JasperReportintgWithSpringMvc\\src\\main\\webapp\\resources\\report\\bookList.jrxml");
//		    InputStream jasperStream = null;
//				try {
//					
//					jasperStream = new FileInputStream(initialFile);
//					jasperReport = JasperCompileManager.compileReport(jasperStream);
//					jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
//					
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JRException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//	    response.setContentType("application/x-pdf");
//	    response.setHeader("Content-disposition", "inline; filename=allBookList.pdf");
//
//	    final OutputStream outStream = response.getOutputStream();
//	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
//	  }
	
	
	
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public String allPrint(ModelMap map,  HttpServletResponse response) throws IOException {		
//		
//			List<Book> bookList =  bookService.bookList();			
//		    System.out.println("Book Obj form Report controller:"+bookList);
//		    		    
//		    JRDataSource jrDataSource = new JRBeanCollectionDataSource(bookList);
//		    
//			JasperReport jasperReport = null;
//			JasperPrint jasperPrint = null;
//				
//		    File initialFile = new File("G:\\GitDown\\JasperReportintgWithSpringMvc\\JasperReportintgWithSpringMvc\\src\\main\\webapp\\resources\\report\\bookList.jrxml");
//		    InputStream jasperStream = null;
//				try {
//					
//					jasperStream = new FileInputStream(initialFile);
//					jasperReport = JasperCompileManager.compileReport(jasperStream);
//					jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
//
//			        JasperExportManager.exportReportToPdfFile(jasperPrint,
//			                "G:/GitDown/JasperReportintgWithSpringMvc/JasperReportintgWithSpringMvc/src/main/webapp/resources/report/thisOutPut.pdf");
//					
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (JRException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    
//
//		     return "redirect:/book/";
//	}
	
	 

	
	
	

	
	
	
	
	
	
	
	
	
//	
//	
//	
//	  @RequestMapping(value = "/test", method = RequestMethod.GET)
//	public void test() throws JRException {
//			List<Book> bookList = new ArrayList<Book>();			
//		    System.out.println("Book Obj form Report controller:"+bookList);
//		    
//		    
//		    
//		    JRDataSource jrDataSource = new JRBeanCollectionDataSource(bookList);
//			
//		    File initialFile = new File("G:/GitDown/JasperReportintgWithSpringMvc/JasperReportintgWithSpringMvc/src/main/webapp/resources/report/bookList.jrxml");
//		    InputStream jasperStream = null;
//				try {
//					
//					jasperStream = new FileInputStream(initialFile);
//
//										
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//		    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
//		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
//		    
//
//	        JasperExportManager.exportReportToPdfFile(jasperPrint,
//	                "G:/GitDown/JasperReportintgWithSpringMvc/JasperReportintgWithSpringMvc/src/main/webapp/resources/report/thisOutPut.pdf");
//		  
//		
//	}
//	
//
//	
//	
	
	
	


 
 
}//ReportController
