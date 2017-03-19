package com.silentlexx.ffuudbconv;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileWrite {
	private String file_name;
	private boolean isOpen=false;
	private PrintWriter file;
	Debug D;
	
	FileWrite(String fn) {
		D = Main.D;
		file_name=fn;
		Open();
	}
	
	public String getFileName() {
		if (isOpen) {
			return file_name;
			}else {
				return "";
			}
		}
		
		public boolean ifOpen() {
			return isOpen;
		}
	
		
		
		public boolean Open() {
			if (file == null) {
					try {
						file = new PrintWriter(new BufferedWriter(new FileWriter(new File (file_name),false)));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						D.p("Can't open file: "+file_name);
						e.printStackTrace();
					} finally {
						if (file != null) {
							D.p("File opened for Write: "+file_name);
							isOpen = true;
						}
					}
			
			return isOpen;
			} else return false;
			}

       public boolean Writeln(String s){
			if( file != null && isOpen && s != null ){
			 	
			file.println(s);
			return true;				
				
			} else return false;
		     
		}
		
       
		public boolean Writeln(int s){
			if( file != null && isOpen ){
			 	
			file.println(s);
			return true;				
				
			} else return false;
		     
		}
		
       
       
       
       public boolean Close() {
			if(isOpen && file != null) {
				file.close();
				file = null;
				isOpen = false;
				D.p("Writen File Closed: "+file_name);
				return true;
			} else {
				return false;
			}
			
			
		}
		
	
}
