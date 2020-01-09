
public class LogTime {
	
	static int getCurrentTimeWtmp(String readLogLine) {
		
		String divideWords[]=new String[1024];
		divideWords=SubTool.initArr(divideWords);
		String processedWords[]=new String[1024];
		processedWords=SubTool.initArr(processedWords);
		int pWsIndex=0;
		String timeArr[]=new String[4];
		String hourMin[]=new String[2]; //(tmp)
		String tmpForTime; //(tmp)
		String strres;
		int res;
		
		divideWords=readLogLine.split(" ");
		
		for(int rmvBlank=0;rmvBlank<divideWords.length;rmvBlank++)
		{
			divideWords[rmvBlank]=divideWords[rmvBlank].replace(" ", "");
			if(!divideWords[rmvBlank].isEmpty())
			{
				processedWords[pWsIndex]=divideWords[rmvBlank];
				pWsIndex++;
			}
		}
		

		for(int srchTime=0; srchTime<processedWords.length;srchTime++)
		{
			if(judgeTimeString(processedWords[srchTime]))
			{
				for(int fndTime=0;fndTime<3;fndTime++)
				{
					if(fndTime==2)
					{	
						tmpForTime=processedWords[fndTime+srchTime];
						hourMin=tmpForTime.split(":");
						timeArr[fndTime]=hourMin[0];
						timeArr[fndTime+1]=hourMin[1];
					}else 
					{
						timeArr[fndTime]=processedWords[fndTime+srchTime];
					}
				}
			}
		}

		strres=getCurrentLogTimeSub(timeArr[0]);
		for(int add=1;add<timeArr.length;add++)
		{
			if(timeArr[add].length()==1)
			{
				timeArr[add]="0"+timeArr[add];
			}
			strres+=timeArr[add];
		}
		res=Integer.parseInt(strres);
		return res;
	}
	
	static String getCurrentLogTimeSub(String time) {
		String res="";
		switch(time) {
			case "Jan": res="01"; break;
			case "Feb": res="02"; break;
			case "Mar": res="03"; break;
			case "Apr": res="04"; break;
			case "May": res="05"; break;
			case "Jun": res="06"; break;
			case "Jul": res="07"; break;
			case "Aug": res="08"; break;
			case "Sep": res="09"; break;
			case "Oct": res="10"; break;
			case "Nov": res="11"; break;
			case "Dec": res="12"; break;	
		}
		return res;
	}
	
	static boolean judgeTimeString(String time) {
		//System.out.println(">"+time);
		if(time.equals("Jan") || time.equals("Feb") || time.equals("Mar") || time.equals("Apr") 
				|| time.equals("May") || time.equals("Jun") || time.equals("Jul") || time.equals("Aug") 
				|| time.equals("Sep") || time.equals("Oct")|| time.equals("Nov") || time.equals("Dec"))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}