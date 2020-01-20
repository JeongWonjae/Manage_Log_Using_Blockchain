package controller;

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
