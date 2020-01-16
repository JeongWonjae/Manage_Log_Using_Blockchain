package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OpenLog {
	
	static ArrayList<String> callLogLine(String filePath){
		
		BufferedReader oReader=null;
		String readLogLine;
		ArrayList<String> allLogArr=new ArrayList<String>();
		
		try 
		{
			File file=new File(filePath);
			oReader=new BufferedReader(new FileReader(file));
			
			while((readLogLine=oReader.readLine())!=null)
			{
				readLogLine=readLogLine.replaceAll("\n", "");
				if(!(readLogLine.equals("")))
				{
					allLogArr.add(readLogLine);
				}
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
			try 
			{
				if(oReader!=null) oReader.close();
			} catch(IOException e) 
			{
				e.printStackTrace();
			}
		}
		return allLogArr;
	}
}