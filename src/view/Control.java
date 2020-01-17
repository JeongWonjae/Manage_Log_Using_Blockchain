package view;

import controller.kindOfLog;

public class Control {
	
	public static void start(String localIP, String flag) throws Exception {
		String[] selectedLogArr=new String[10];
		int selectedLogArrIndex=0;
		selectedLogArr=controller.SubTool.initArr(selectedLogArr);
		selectedLogArr=flag.split("/");
		
		for(selectedLogArrIndex=0;selectedLogArrIndex<selectedLogArr.length;selectedLogArrIndex++)
		{
			Frame.consoleTextArea.append("[INFO] Send Packet about "+selectedLogArr[selectedLogArrIndex]+"\n");
			kindOfLog.EnrollLogBlock(selectedLogArr[selectedLogArrIndex], localIP);
		}
	}
	
	public static void retry() {
	}
	
	public static void server() {
		
	}
}
