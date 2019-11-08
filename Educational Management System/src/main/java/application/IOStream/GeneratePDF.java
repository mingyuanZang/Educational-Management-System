package application.IOStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.model.Course;
import application.model.Student;


public class GeneratePDF implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8254127293666452534L;
	int colNum = 4;
	String [] head = {"Student Name","Student Number","Student Email", "Programme"};
	
	 public static PdfPTable createTable(int colNumber) {
		 PdfPTable table = new PdfPTable(colNumber);
		 table.setWidthPercentage(100); // Width 100%
		 table.setSpacingBefore(10f); // Space before table
		 table.setSpacingAfter(10f); // Space after table
		 return table;
	 }
	 
	 public void generateClassList(Course course) {
		 try {
			 Document document = new Document();
			 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PDF.pdf"));

//				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/pc/Desktop/test.pdf"));
	         document.open();

	         ArrayList<Student> participantNameList = new ArrayList<Student>(course.getClassList());
	         Collections.sort(participantNameList, new Comparator<Student>() {
	 			@Override
	 			public int compare(Student o1, Student o2) {
	 				Student s1 = o1;
	 				Student s2 = o2;
	 				int temp = s1.getStudentNumber() - s2.getStudentNumber();
	 				return temp;
	 			}
	 		});
	         
	         document.add(new Paragraph("Participation List"));
	         document.add(new Paragraph("Course: " + course.getCourseNumber() + " " + course.getCourseName()));
	         PdfPTable table = createTable(colNum);

	         for (int i = 0; i < colNum; i++) {
	         	table.addCell(head[i]);
	         }
	         
	         if(participantNameList.isEmpty()) {
	        	 document.add(new Paragraph("Empty Participation List. No one has enrolled in the course yet."));
	         } else {
	        	 int size = participantNameList.size();
				  for(int i = 0 ; i < size; i++) {
					  Student student = participantNameList.get(i);
					  for(int j = 0 ; j < colNum ; j ++) {
						  if(j == 0) {
							table.addCell(student.getFirstName() + " " + student.getLastName());
						  } else if (j == 1) {
							 table.addCell(Integer.toString(student.getStudentNumber()));
						  } else if (j == 2) {
								 table.addCell(student.getEmail());
						  } else {
							  if (student.getProgramme() != null) {
								  table.addCell(student.getProgramme().getProgrammeName() + " " + student.getProgramme().getType().toString());
							  } else {
								  table.addCell("No programme");
							  }
						  }
					  }  
				  }
	         }
	         document.add(table);
	         document.close();
	         writer.close();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
}