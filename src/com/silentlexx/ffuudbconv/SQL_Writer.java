package com.silentlexx.ffuudbconv;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SQL_Writer {
	final static int max = 32;
	FieldsInfo fields_info;
	FileRead csv_file;
	FileWrite sql_file;
	Debug D;
	
	SQL_Writer(String sql_filename, String csv_filename,  FieldsInfo fi) {
		D = Main.D;
		fields_info = fi;
		sql_file = new FileWrite(sql_filename);
		sql_file.Open();
		csv_file = new FileRead(csv_filename);
		csv_file.Open();	
		doWrite();
		sql_file.Close();
		csv_file.Close();
		Main.Quit();
	}
	
	
	void doWrite() {
		int i = 0;
		String b;
		csv_file.Readln();
		if(Main.fap) sql_file.Writeln(getHeaders());
		while (!csv_file.isEOF()){
			b = csv_file.Readln();
			D.p(i+" Read: "+b);
			if (b != null ) sql_file.Writeln(doLine(i,b));
			i++;
		}
		
	}
	
	private String getHeaders() {
		FileRead f = new FileRead(Main.headers_f);
		String s = f.Readln();
		f.Close();
		return s.replace("\n","");
	}


	String doLine(int n, String s) {
	
		String[] bs;
		Map<String,String> replacements = new HashMap<String,String>();
		bs = s.split(fields_info.PATERN);
		int in = 0;
		String bb;
		for (int i=0;i<fields_info.getNumSQL();i++){
			in = fields_info.getSC(i);
			if (in > -1 && in < bs.length) {
				bb = bs[in];
			} else {
				bb = "";
			}
			D.p(n + "'" + fields_info.getSQL(i) + "' replase '"+ bb +"' -- "+in);
			replacements.put(fields_info.getSQL(i),bb);
			
		}

        Pattern pattern = Pattern.compile(fields_info.KEY);
        Matcher matcher = pattern.matcher(fields_info.getTrafaret());
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
                String replacement = replacements.get(matcher.group(1));
                if (replacement != null) {
                 //    matcher.appendReplacement(buffer, replacement);
                        // see comment 
                        matcher.appendReplacement(buffer, "");
                        buffer.append(replacement);
                }
        }
        matcher.appendTail(buffer);
		
       
		return buffer.toString().replaceAll("\n","");	
	}
	
//	String Var(String s){
//		return "${"+s+"}";
//	}
	
}
