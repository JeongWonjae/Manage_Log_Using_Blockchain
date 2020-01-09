import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OpenLog {
	
	static String[] wtmp(String fileName) {
		
		String starttime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
		int currentTime=0;
		BufferedReader oReader=null;
		//store part of log (tmp var)
		String readLogLine;
		//store all of log
		String allLogArr[]=new String[1024];
		
		//init allLogArr
		for(int i=0;i<allLogArr.length;i++) {
			allLogArr[i]=null;
		}
		
		try 
		{
			File file=new File(fileName);
			oReader=new BufferedReader(new FileReader(file));
			
			int allLogArrIndex=0;
			while((readLogLine=oReader.readLine())!=null)
			{
				if(readLogLine.contains(":"))
				{
					currentTime=LogTime.getCurrentTimeWtmp(readLogLine);
					 if(SubTool.toInt(starttime)<currentTime)
					 {
					 	allLogArr[allLogArrIndex]=readLogLine;
					 	allLogArrIndex+=1;
					 	//(+)year problem...
					 }
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