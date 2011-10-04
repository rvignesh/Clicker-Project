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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import edu.rutgers.ClickerIdUtil;


class Student{
	String clickerId;
	String ruId;
	String name;
	String netId;
	float grade;
	boolean invalid_clicker_id;
}


public class AllTests {

	

		  public static float Round(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return (float)tmp/p;
		  }
		
	
	public static HashMap<String,Student> readStudentFile(BufferedReader in) throws IOException{
	
		HashMap<String,Student> h= new HashMap<String,Student>();
		
		CsvReader reader=openFile("Student Data",in);
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
			String rawID=reader.get("ID").trim();
			while(rawID.length()<8)
			{
				rawID="0"+rawID;
			}
			s.clickerId="#"+rawID.toUpperCase();
			if(!ClickerIdUtil.isValidClickerId(s.clickerId)){
			 s.invalid_clicker_id=true;
			}
			
			s.name=reader.get("name");
			s.ruId=reader.get("RUID");
			s.netId=reader.get("user");
		   h.put(s.clickerId,s);
		
		 
		}
		return h;

	 }
	
	public static CsvReader openFile(String kind,BufferedReader  buffer) throws IOException{
		
		System.out.println("Enter the path of "+kind+" File : ");
	//	BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
	//	System.setIn(in);
		String path=buffer.readLine();
		
		CsvReader reader= new CsvReader(new  InputStreamReader(
					new FileInputStream(path), Charset.forName("UTF-8")));
			return reader;
	}
	
	public static  ArrayList<String> readClicker(HashMap<String,Student> h,int questionCount,String[] answers,BufferedReader in) throws IOException{
		
		ArrayList<String> invalid_id=new ArrayList<String>();
		CsvReader reader=openFile("clicker",in);
		String[] headers;
		reader.readHeaders();
		if(reader.readHeaders()){
			headers=reader.getHeaders();			
		}
		else
			return null;
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
			boolean flag=false;
		 if(h.get(reader.get("Question"))!=null)
		 {
		  s= h.get(reader.get("Question").trim());
		  flag=true;
		 }
		 else
		 {

				String sx=reader.get("Question").trim();
		        Iterator iterator = h.keySet().iterator();
			     while (iterator.hasNext()) {		         
		            String match=(String)iterator.next();
	
		             if(ClickerIdUtil.translate(match).compareToIgnoreCase(sx)==0 ||
		            		ClickerIdUtil.translate(sx).compareToIgnoreCase(match)==0 ||
		            		ClickerIdUtil.translate(match).compareToIgnoreCase(
		            				ClickerIdUtil.translate(sx))==0){
		            	s= h.get(match);
		            	flag=true;
		            	break;
		            }
		            
		            else 
		            continue;
		            
		        	}
			 if(!flag){
		      invalid_id.add(reader.get("Question"));
		      continue;
			 }
			 
		 }
			 
			 
		 s.grade=8.0f;
		 for(int i=0;i<questionCount;i++)
		 { boolean flagk=false;
			 for(int j=0;j<answers[i].length();j++)
				 if(answers[i].substring(j,j+1).compareToIgnoreCase(reader.get("Question "+(i+1)))==0)
					flagk=true;
			 
			 		if(flagk)
					 s.grade+=(2*(1.0/questionCount));
			 
		 }
		 h.put(s.clickerId, s);	 
		}
		return invalid_id;
	 }
	public static  void readOnlineScores(HashMap<String,Student> h,int questionCount,String[] answers,BufferedReader in) throws IOException{
		CsvReader reader=openFile("Sakai Scores", in);
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
		String ass_no;
		 BufferedInputStream in = new BufferedInputStream(new FileInputStream(
	        "input.txt"));
		   PrintStream out = new PrintStream(new BufferedOutputStream(
			        new FileOutputStream("test.out")));
			    System.setIn(in);
			 //   System.setOut(out);
			   // System.setErr(out);			    
		System.out.println("Enter the Assesment Number");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ass_no=br.readLine();
		System.out.println("Enter the No.of clicker questions in the input");
		
		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
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
		

		HashMap<String,Student> studentInfo=readStudentFile(br);

		if(studentInfo==null){
			System.exit(0);
		}

		ArrayList<String> invalid=readClicker(studentInfo,questionCount,answers,br);
		readOnlineScores(studentInfo,questionCount,answers,br);		
		
		System.out.println("Enter the name of output file");
		String output=br.readLine();
		CsvWriter xWriter=new CsvWriter(new FileWriter(output),',');
		
		xWriter.writeRecord(new String[]{"Student ID","Clicker ID Assesment "+ass_no+" [10]"} );
		
		  Iterator iterator = studentInfo.entrySet().iterator();
	        String matchClickerId="";
	        int i=0;
	        while (iterator.hasNext()) {
	        	Map.Entry entry = (Map.Entry) iterator.next();
	            Student match=(Student)entry.getValue();
	            xWriter.writeRecord(new String[]{match.netId,Float.toString(Round(match.grade,2))});
	            System.out.println((i+1)+":"+"Student Name "+match.name+"\nClickerID "+match.clickerId+"\nRUID "+match.ruId
	            			+"\nGrade: "+Round(match.grade,2));
	            i++;
	        }
	        xWriter.writeRecord(new String[]{"Invalid Ids",""});
	        for(int l=0;l<invalid.size();l++)
	        {
	        	xWriter.writeRecord(new String[]{"",invalid.get(l)});
	        }
	        xWriter.close();
	        out.close();
	} 
}	        