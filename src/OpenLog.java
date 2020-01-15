import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OpenLog {
	
	static ArrayList<String> wtmpNmessages(String fileName) {
		
		String starttime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
		int currentTime=0;
		BufferedReader oReader=null;
		//store part of log (tmp var)
		String readLogLine;
		//store all of log
		ArrayList<String> allLogArr=new ArrayList<String>();
		
		try 
		{
			File file=new File(fileName);
			oReader=new BufferedReader(new FileReader(file));
			
			while((readLogLine=oReader.readLine())!=null)
			{
				if(readLogLine.contains(":"))
				{
					currentTime=LogTime.getCurrentTimewtmpNmessages(readLogLine);
					 if(SubTool.toInt(starttime)<currentTime) //need update
					 {
						allLogArr.add(readLogLine);
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
	
	static ArrayList<String> boot(String fileName){
		
		BufferedReader oReader=null;
		String readLogLine;
		ArrayList<String> allLogArr=new ArrayList<String>();
		
		try 
		{
			File file=new File(fileName);
			oReader=new BufferedReader(new FileReader(file));
			
			while((readLogLine=oReader.readLine())!=null)
			{
				allLogArr.add(readLogLine);
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