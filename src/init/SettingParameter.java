package init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
	
	public static HashMap<String,String> getLogPath() throws Exception {
		String settingsPath=ConnectionMysql.queryReturnString("select * from serveractivate", "settingsPath");
		BufferedReader oReader=null;
		String readLine=null;
		String[] getLogPath=new String[2];
		String[] getLogType=new String[10];
		int getLogTypeIndex=0;
		int pointIndex=0;
		HashMap<String, String> logPathName=new HashMap();
		String key=null;
		String value=null;

		try 
		{
			File file=new File(settingsPath);
			oReader=new BufferedReader(new FileReader(file));
			
			while((readLine=oReader.readLine())!=null)
			{
				if(readLine.contains("LogPath"))
				{
					getLogPath=readLine.split("=");
					getLogType=getLogPath[1].split("/");
					for(getLogTypeIndex=0;getLogTypeIndex<getLogType.length;getLogTypeIndex++)
					{
						if(getLogType[getLogTypeIndex].contains("."))
						{
							pointIndex=getLogType[getLogTypeIndex].indexOf(".");
							key=getLogType[getLogTypeIndex].substring(0, pointIndex);
						}
					}
					value=getLogPath[1];
					logPathName.put(key, value);
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
		return logPathName;
	}
	
	public static String getUpdatePath() throws Exception {
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
				if(readLine.contains("UpdatePath"))
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
	

