package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import view.Frame;

public class ControlShell {
	
	public static void openShell(String cmd) throws Exception{
		Runtime runtime=Runtime.getRuntime();
		Process process=runtime.exec(cmd);
		InputStream is=process.getInputStream();
		InputStreamReader isReader=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(isReader);
		String line;
		while((line=br.readLine())!=null)
		{
			Frame.callTextArea("[+] "+line);
		}
	}
}
