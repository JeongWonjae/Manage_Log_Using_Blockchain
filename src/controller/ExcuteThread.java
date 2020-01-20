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
	
}
