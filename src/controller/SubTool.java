package controller;

public class SubTool {

	static int toInt(String str) {
		return Integer.parseInt(str);
	}

	public static String[] initArr(String[] arr) {
		for(int i=0;i<arr.length;i++)
		{
			arr[i]="";
		}
		return arr;
	}
}
