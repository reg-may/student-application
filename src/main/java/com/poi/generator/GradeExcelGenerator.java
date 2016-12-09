package com.poi.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.apache.poi.ss.usermodel.Sheet;




import com.jpa.entities.Grade;


import com.jpa.entities.Student;
import com.jpa.entities.StudentProfile;


@Named("gradeExcelGenerator")
@RequestScoped
public class GradeExcelGenerator implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3648386579092436095L;
	
	//Student Grade
    public void createExcel(List<Grade> grades){
		
    	
		System.out.println("GRADE: "+grades);
	
		HSSFWorkbook userGrade = new HSSFWorkbook(); 
		  
		HSSFSheet sheet = userGrade.createSheet("Student Grade");
		
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();

	        data.put(0, new Object[] {"GRADE ID", "SUBJECT ID", "MIDTERM SCORE", "FINAL SCORE", "FINAL GRADE"});
	        
	        
	        for(int i = 0; i < grades.size(); i++){
	        data.put(i+1, new Object[] {grades.get(i).getIdNo(), grades.get(i).getIdNo(), grades.get(i).getSubject().getIdNo(), grades.get(i).getMidtermScore(), grades.get(i).getFinalScore(), grades.get(i).getFinalGrade()});
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
	            //Write the workbook in file systemC:/Users/Rappler M/    /home/lorenzor/
	            FileOutputStream out = new FileOutputStream(new File("/C:/Users/Rappler M/studentgrade.xlsx"));
	            userGrade.write(out);
	            out.close();
	            System.out.println("studentprofile.xlsx written successfully on disk.");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	
	}
    
    
  //Student Profile
    public void createStudentAndProfile(List<Student> studentList, List<StudentProfile> studentProfileList){
		
    
			HSSFWorkbook studentbook = new HSSFWorkbook(); 
			  
			HSSFSheet sheet = studentbook.createSheet("Student Complete Data");
		
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();

	        data.put(0, new Object[] {"STUDENT ID","FIST NAME","LAST NAME","PROFILE ID", "GENDER", "BIRTHDAY", "CONTACT NO", "EMAIL", "YEAR LEVEL", "SECTION"});
	        
	        for(int i = 0; i < studentProfileList.size(); i++){
	        	
			        data.put(i+1, new Object[] {studentList.get(i).getStudentId(),studentList.get(i).getFirstName(),studentList.get(i).getLastName(),studentProfileList.get(i).getProfileId(), studentProfileList.get(i).getGender(), studentProfileList.get(i).getBirthDay(), studentProfileList.get(i).getContactNo(), studentProfileList.get(i).getEmailAdd(), studentProfileList.get(i).getYearLevel(), studentProfileList.get(i).getSection()});
			     
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
	            FileOutputStream out = new FileOutputStream(new File("/home/lorenzor/studentwithprofile.xlsx"));
	            studentbook.write(out);
	            out.close();
	            System.out.println("studentwithprofile.xlsx written successfully on disk.");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	
	}
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
     
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue();
     
        case Cell.CELL_TYPE_NUMERIC:
            return cell.getNumericCellValue();
        }
     
        return null;
    }
    
    public List<StudentProfile> readBooksFromExcelFile(String excelFilePath) throws IOException {
    	
    	
    	List<StudentProfile> listProfiles = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
     
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
     
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            StudentProfile studentProfile = new StudentProfile();
     
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();
     
                switch (columnIndex) {
                case 1:
                    studentProfile.setBirthDay((String) getCellValue(nextCell));
                    break;
                case 2:
                	 studentProfile.setContactNo((String) getCellValue(nextCell));
                    break;
                case 3:
                    studentProfile.setEmailAdd((String) getCellValue(nextCell));
                    break;
                case 4:
                    studentProfile.setGender((String) getCellValue(nextCell));
                    break;
                case 5:
                    studentProfile.setSection((String) getCellValue(nextCell));
                    break;
                case 6:
                    studentProfile.setYearLevel((String) getCellValue(nextCell));
                    break;
                }
     
     
            }
            listProfiles.add(studentProfile);
        }
     
        
        FileOutputStream fileOut = new FileOutputStream(excelFilePath);
        workbook.write(fileOut);
        fileOut.close();
     
        return listProfiles;
    }
}
