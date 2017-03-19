package com.silentlexx.ffuudbconv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileRead {
	private final String dummy = null;
	private String file_name;
	private boolean isOpen=false;
	private boolean isEOF =true;
	private BufferedReader file;
	Debug D;
	
		FileRead(String fn) {
			D = Main.D;
			file_name=fn;
			Open();
		}
		
		public String getFileName() {
		if (isOpen) {
			return file_name;
			}else {
				return dummy;
			}
		}
		
		public boolean ifOpen() {
			return isOpen;
		}
		
		public boolean Open() {
		if (file == null) { 
		try {
		    	//  BufferedReader 
		    	  file =  new BufferedReader(new InputStreamReader(new FileInputStream(new File(file_name).toString()), Main.iocharset));
		      } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
		    	  D.p("Unsupported Encoding in File: "+file_name);
		    	isOpen=false;
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				D.p("File Not Found: "+file_name);
				isOpen=false;
				e.printStackTrace();
			} finally {
		    	  if (file != null) {
		    		  D.p("File Opened for Read: "+file_name);
		    		  isOpen=true;
		    		  isEOF =false;
		    	  }
			}
		return isOpen;
		} else return false;
		}

		public boolean Close() {
			if(isOpen && file != null) {
				try {
					file.close();
					file = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					D.p("Can't Close File: "+file_name);
					e.printStackTrace();
					return false;
				}
				D.p("Reading File Closed: "+file_name);
				isOpen = false;
				isEOF  = true;
				return true;
			} else {
				return false;
			}
			
		}
		
		public String Readln() {
			String line;
			if(isOpen && file != null) {
				try {
					if ((line = file.readLine()) != null) {
						return line;
					} else
					{
						isEOF = true;
						return dummy;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					D.p("Can't Read from File: "+file_name);
					isEOF = true;
					e.printStackTrace();
					return dummy;
				}
				
			} else {
				return dummy;
			}
			
		}
		
		public boolean isEOF() {
			return isEOF;
		}
		
	
}
