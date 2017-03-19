package com.silentlexx.ffuudbconv;

public class Debug {
   final String fn = "debug.log";
   int enable = 0;
   int tmp = 0;
   FileWrite log;
   
	Debug(int b) {
		enable = b;
	}
	
	public void Init(){
		if (enable == 1) p("Debug system activated on term!");
		if (enable == 2){
			enable = 1;
			log = new FileWrite(fn);
			log.Open();			
			enable = 2;
			p("Debug log started in file: "+fn);
		}
	}
	
	public void p(String s) {
		System.out.println(s);
		if (enable == 2)
		log.Writeln(s);
	}

	public void p(int s) {
		System.out.println(s);
		if (enable == 2)
		log.Writeln(s);
	}
	
	public void End() {
		
		if (enable == 2) {
			
		enable = 1;
		log.Close();
			
			
		}
	}
}
