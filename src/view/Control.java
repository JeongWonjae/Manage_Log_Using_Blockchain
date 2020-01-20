package view;
import controller.ExcuteThread;
import controller.ExcuteThread.StartServerThread;
import controller.kindOfLog;

public class Control {
	
	public static void start(String localIP, String flag) throws Exception {
		String[] selectedLogArr=new String[10];
		int selectedLogArrIndex=0;
		selectedLogArr=controller.SubTool.initArr(selectedLogArr);
		selectedLogArr=flag.split("/");
		
		for(selectedLogArrIndex=0;selectedLogArrIndex<selectedLogArr.length;selectedLogArrIndex++)
		{
			Frame.callTextArea("[INFO] Send Packet about "+selectedLogArr[selectedLogArrIndex]);
			kindOfLog.EnrollLogBlock(selectedLogArr[selectedLogArrIndex], localIP);
		}
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
}
