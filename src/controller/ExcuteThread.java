package controller;

import java.util.HashMap;
import java.util.Set;

import init.SettingParameter;
import view.Frame;

public class ExcuteThread {

	public static class StartServerThread implements Runnable{
		
		String filePath;
		
		public StartServerThread(String filePath)
		{
			this.filePath=filePath;
		}
		@Override
		public void run() {
			try {
				ControlShell.openShell(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class StopServerThread implements Runnable{
		
		String filePath;
		
		public StopServerThread(String filePath)
		{
			this.filePath=filePath;
		}
		@Override
		public void run() {
			try {
				ControlShell.openShell(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class LogBlockThread implements Runnable{
		
		String logType;
		String localIP;
		
		public LogBlockThread(String logType, String localIP)
		{
			this.logType=logType;
			this.localIP=localIP;
		}
		@Override
		public void run() {
			try {
				kindOfLog.EnrollLogBlock(logType, localIP);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
public static class updateLog implements Runnable{
		
		@Override
		public void run() {
			String updatePath;
			HashMap<String, String> logPathName=new HashMap();
			try {
				logPathName=SettingParameter.getLogPath();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Set<String> set=logPathName.keySet();

			try {
				while(Frame.updateLogButton.getText().equals("Stop Log Update"))
				{
					try {
						updatePath=SettingParameter.getUpdatePath();
						//if(lastfilehash!=crtfilehash){}
						for(String key : set)
						{
							ControlShell.openShell("cp "+logPathName.get(key)+" "+updatePath+"/"+key+".log");
						}
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
