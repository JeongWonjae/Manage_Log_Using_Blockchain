
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName="A://testfile/wtmp.txt";
		String logFileHash=""; String lastLogFileHash="";
		
		String log[]=new String[1024];
		log=OpenLog.wtmp(fileName);
		
		//logFileHash=ModifyLog.getFileHash(fileName);
		//lastopenedLogFileHash=ModifyLog.lastFileHash(fileName);
		/*
		 * if(logFileHash.equals(lastopenedLogFileHash){
		 * 		Block.createBlock("192.168.11.104", log);
		 * }
		 * 
		 */
		
		Block.createBlock("192.168.11.104", log);
	}
}
