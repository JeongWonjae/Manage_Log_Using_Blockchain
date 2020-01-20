package init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import controller.ConnectionMysql;

public class SettingParameter {

	public static String getLocalIP() throws Exception {
		String localIP=null;
		String settingsPath=ConnectionMysql.queryReturnString("select * from serveractivate", "settingsPath");
		BufferedReader oReader=null;
		String readLine=null;
		String[] tmp=new String[2];
		try 
		{
			File file=new File(settingsPath);
			oReader=new BufferedReader(new FileReader(file));
			
			while((readLine=oReader.readLine())!=null)
			{
				if(readLine.contains("localIP"))
				{
					tmp=readLine.split("=");
					localIP=tmp[1];
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
		return localIP;
	}
}
	

