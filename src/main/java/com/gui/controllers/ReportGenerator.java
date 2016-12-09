package com.gui.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.util.FacesUtil;



@ViewScoped
@Named("reportGenerator")
public class ReportGenerator extends HttpServlet implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3743218582056536771L;
	

	   private static final int DEFAULT_BUFFER_SIZE = 10240;  
	   private String filePath = "C:\\Users\\Rappler M\\student.xlsx";  
	         
	        public void downLoad() throws IOException {  
	             FacesContext context = FacesContext.getCurrentInstance();  
	            HttpServletResponse response = (HttpServletResponse) context  
	                         .getExternalContext().getResponse();  
	               File file = new File(filePath);  
	               
	            if (!file.exists()) {  
	                   response.sendError(HttpServletResponse.SC_NOT_FOUND);  
	                    return;  
	              }  
	               response.reset();  
	               response.setBufferSize(DEFAULT_BUFFER_SIZE);  
	               response.setContentType("application/octet-stream");  
	               response.setHeader("Content-Length", String.valueOf(file.length()));  
	               response.setHeader("Content-Disposition", "attachment;filename=\""  
	                      + file.getName() + "\"");  
	               
	              BufferedInputStream input = null;  
	              BufferedOutputStream output = null;  
	             try {  
	                   input = new BufferedInputStream(new FileInputStream(file),  
	                           DEFAULT_BUFFER_SIZE);  
	                 output = new BufferedOutputStream(response.getOutputStream(),  
	                              DEFAULT_BUFFER_SIZE);  
	                    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];  
	                    int length;  
	                    while ((length = input.read(buffer)) > 0) {  
	                        output.write(buffer, 0, length);  
	                    }  
	             } finally {  
	                 	input.close();  
	                    output.close();  
	         }  
	             context.responseComplete();  
	     }  
}
