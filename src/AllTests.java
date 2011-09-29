/*
 * Java CSV is a stream based library for reading and writing
 * CSV and other delimited data.
 *   
 * Copyright (C) Bruce Dunwiddie bruce@csvreader.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.csvreader.CsvReader;


class Student{
	String clickerId;
	String ruId;
	String name;
	String netId;
	float grade;
}


public class AllTests {
	
	public static HashMap<String,Student> readStudentFile() throws IOException{
	
		HashMap<String,Student> h= new HashMap<String,Student>();
		
		CsvReader reader=openFile("Student Data");
		String[] headers;
		if(reader.readHeaders()){
			headers=reader.getHeaders();
			
		}
		else
			return null;
		if(headers[0].compareToIgnoreCase("user")!=0)
		{
		   System.out.println("Please Input a proper Student Clicker ID record");  		
		}
		reader.readRecord();
		while(reader.readRecord())
		{
			Student s=new Student();
			String rawID=reader.get("ID");
			while(rawID.length()<8)
			{
				rawID="0"+rawID;
			}
			s.clickerId="#"+rawID.toUpperCase();
			
			s.name=reader.get("name");
			s.ruId=reader.get("RUID");
			s.netId=reader.get("user");
		   h.put(s.clickerId,s);
		
		 
		}
		return h;

	 }
	
	public static CsvReader openFile(String kind) throws IOException{
		
		System.out.println("Enter the path of "+kind+" File : ");
		BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
		String path=buffer.readLine();
		
		CsvReader reader= new CsvReader(new  InputStreamReader(
					new FileInputStream(path), Charset.forName("UTF-8")));
			return reader;
	}
	
	public static  void readClicker(HashMap<String,Student> h,int questionCount,String[] answers) throws IOException{
		
		CsvReader reader=openFile("clicker");
		String[] headers;
		reader.readHeaders();
		if(reader.readHeaders()){
			headers=reader.getHeaders();			
		}
		else
			return;
		if(headers[0].compareToIgnoreCase("Question")!=0)
		{
		   System.out.println("Please Input a proper Clicker assessment record");  		
		}
		reader.readRecord();
		reader.readRecord();
		reader.readRecord();
		while(reader.readRecord())
		{
			Student s=null;
		 if(h.get(reader.get("Question"))!=null)
		 {
		  s= h.get(reader.get("Question"));
		 }
		 else
		 {
			 System.out.println("clicker ID not found in Student Registration:" + reader.get("Question"));
			 continue;
		 }
			 
			 
		 s.grade=8.0f;
		 for(int i=0;i<questionCount;i++)
		 {
			 if(reader.get("Question "+i).equals(answers[i]))
				 s.grade+=(2*(1.0/questionCount));
			 
		 }
		 h.put(s.clickerId, s);	 
		}
	 }
	public static  void readOnlineScores(HashMap<String,Student> h,int questionCount,String[] answers) throws IOException{
		CsvReader reader=openFile("Sakai Scores");
		String[] headers;
		if(reader.readHeaders()){
			headers=reader.getHeaders();
		}
		else
			return;
		if(headers[0].compareToIgnoreCase("user")!=0)
		{
		   System.out.println("Please Input a proper Online Scores record");  		
		}
				String matchClickerId="";
		while(reader.readRecord())
		{
			matchClickerId="";
	        Iterator iterator = h.entrySet().iterator();
	        
	        while (iterator.hasNext()) {
	            Map.Entry entry = (Map.Entry) iterator.next();
	            Student match=(Student)entry.getValue();
	            if(match.ruId.compareToIgnoreCase(reader.get("RUID"))==0){
	            	matchClickerId=match.clickerId;
	            	break;
	            }
	            else 
	            continue;
	        	}
		 Student s= h.get(matchClickerId);
		 if(s!=null && s.grade==0.0){
			 if(reader.get(7).compareTo("")!=0)
			 s.grade+=Float.parseFloat(reader.get(7));

			 h.put(s.clickerId, s);	 
		
		 }
		}
	}
			
	
	

	
	public static void main(String[] args) throws Exception {
		
		String []answers;
		int questionCount=0;
		System.out.println("Enter the No.of clicker questions in the input");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		questionCount=Integer.parseInt(br.readLine());
		
		answers=new String[questionCount];
		
		
		
		while(true){
		
		System.out.println("Enter the answers for questions one by one \n");
		for(int i=0;i<questionCount;i++)	
		{
			answers[i]=br.readLine();
		}
		System.out.println("Are the answers right? (y/n) \n");
		if(br.readLine().compareToIgnoreCase("y")==0)
		{
			break;
		}	
		
		else continue;
		}
		

		HashMap<String,Student> studentInfo=readStudentFile();

		if(studentInfo==null){
			System.exit(0);
		}

		readClicker(studentInfo,questionCount,answers);
		readOnlineScores(studentInfo,questionCount,answers);		
	

		
		  Iterator iterator = studentInfo.entrySet().iterator();
	        String matchClickerId="";
	        int i=0;
	        while (iterator.hasNext()) {
	        	Map.Entry entry = (Map.Entry) iterator.next();
	            Student match=(Student)entry.getValue();
	            System.out.println((i+1)+":"+"Student Name "+match.name+"\nClickerID "+match.clickerId+"\nRUID "+match.ruId
	            			+"\nGrade: "+match.grade);
	            i++;
	        }
	        
	} 
}	        