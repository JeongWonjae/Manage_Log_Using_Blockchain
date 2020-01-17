package view;

import controller.SubTool;
import controller.kindOfLog;
import java.util.ArrayList;

public class Control {
	
	public static void start(String localIP, String flag) throws Exception {
		String[] selectedLogArr=new String[10];
		int selectedLogArrIndex=0;
		selectedLogArr=controller.SubTool.initArr(selectedLogArr);
		selectedLogArr=flag.split("/");
		
		for(selectedLogArrIndex=0;selectedLogArrIndex<selectedLogArr.length;selectedLogArrIndex++)
		{
			kindOfLog.EnrollLogBlock(selectedLogArr[selectedLogArrIndex], localIP);
		}
	}
	
	public static void retry() {
	}
	
	public static void server() {
		
	}
}
