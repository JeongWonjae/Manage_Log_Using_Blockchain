package view;

public class Control {
	
	public static void start(String localIP, String flag) throws Exception {
		String[] selectedLogArr=new String[10];
		int selectedLogArrIndex=0;
		selectedLogArr=controller.SubTool.initArr(selectedLogArr);
		selectedLogArr=flag.split("/");
		
		for(selectedLogArrIndex=0;selectedLogArrIndex<selectedLogArr.length;selectedLogArrIndex++)
		{
			Frame.callTextArea("[+] Send Packet about "+selectedLogArr[selectedLogArrIndex]);
			controller.ExcuteThread.LogBlockThread start=new controller.ExcuteThread.LogBlockThread(selectedLogArr[selectedLogArrIndex], localIP);
			Thread thread=new Thread(start, selectedLogArr[selectedLogArrIndex]);
			thread.start();
		}
		Frame.callTextArea("");
	}
	
	public static void retry() {
		
	}
	
	public static void serverStart() throws Exception {
		String filePath=controller.ConnectionMysql.getServerActivatePath("startup");
		controller.ExcuteThread.StartServerThread start=new controller.ExcuteThread.StartServerThread(filePath);
		Thread thread=new Thread(start,"Start Server Thread");
		thread.start();
	}
	
	public static void serverStop() throws Exception {
		String filePath=controller.ConnectionMysql.getServerActivatePath("shutdown");
		controller.ExcuteThread.StopServerThread start=new controller.ExcuteThread.StopServerThread(filePath);
		Thread thread=new Thread(start,"Stop Server Thread");
		thread.start();
	}
	
	public static void updateLog() {
		controller.ExcuteThread.updateLog  start=new controller.ExcuteThread.updateLog();
		Thread thread=new Thread(start,"updateLog");
		thread.start();
	}
}
