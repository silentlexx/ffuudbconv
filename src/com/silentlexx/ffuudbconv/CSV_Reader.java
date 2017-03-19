package com.silentlexx.ffuudbconv;

public class CSV_Reader {
	final String SQL_Con = "";
	int lines = 0;
	int fields = 0;
	FieldsInfo fields_info;
	FileRead csv_file;
	String csv_filename;
	Debug D;
	
	CSV_Reader(String f,FieldsInfo fi) {
		D = Main.D;
		csv_filename = f;
		fields_info = fi;
		FileRead ft = new FileRead(csv_filename);
			ft.Open();
			fields_info.parseCVS(ft.Readln());	
		/*
			while (!ft.isEOF()) {
				ft.Readln();
				lines++;
			}
			fields_info.setLines(lines);
		
			D.p("Found of Lines: "+lines);
		*/
			ft.Close();
			
	}
	
	
	
}
