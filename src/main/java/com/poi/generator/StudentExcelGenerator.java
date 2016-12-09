package com.poi.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.jpa.entities.Student;

@Named("studentExcelGenerator")
@RequestScoped
public class StudentExcelGenerator implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3648386579092436095L;

	public void createExcel(List<Student> studentList){
		
		
		System.out.println("STUDENT LIST: "+studentList);
		HSSFWorkbook studentbook = new HSSFWorkbook(); 
		  
		HSSFSheet sheet = studentbook.createSheet("Student Data");
		
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();

	        data.put(0, new Object[] {"STUDENT ID", "FIRST NAME", "LAST NAME"});
	        
	        for(int i = 0; i < studentList.size(); i++){
	        data.put(i+1, new Object[] {studentList.get(i).getStudentId(), studentList.get(i).getFirstName(), studentList.get(i).getLastName()});
	        System.out.println( i+ "STUDENT APPLICATION");
	        }
	        
	        //Iterate over data and write to sheet
	        Set<Integer> keyset = data.keySet();
	        int rownum = 0;
	        for (Integer key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	               Cell cell = row.createCell(cellnum++);
	               if(obj instanceof String)
	                    cell.setCellValue((String)obj);
	                else if(obj instanceof Integer)
	                    cell.setCellValue((Integer)obj);
	            }
	        }
	        try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File("/home/lorenzor/student.xlsx"));
	            studentbook.write(out);
	            out.close();
	            System.out.println("student.xlsx written successfully on disk.");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	
	}
}

