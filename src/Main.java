import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String fileName="A://testfile/wtmp.txt"; //need update, input by user
		String localIP="192.168.11.104"; //need update, input by user
		String serverPath="/jsp/storeLog.jsp";
		String logFileHash=""; String lastLogFileHash="13d87338d5abf7d1fb1fbffee23c6f1a6e4416fd21c6ff0c59ed5dd56122bb"; //need update
		String redirectPath="";
		
		String log[]=new String[1024];
		log=OpenLog.wtmp(fileName);
		
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		
		logFileHash=ModifyLog.getFileHash(fileName);
		System.out.println(logFileHash);
		//lastopenedLogFileHash=ModifyLog.lastFileHash(fileName); //need update
		
		if(!logFileHash.equals(lastLogFileHash))
		{
			logchain=Block.createBlock(localIP , log);

			for(BlockStructure bk : logchain)
			{
				Thread.sleep(100); //need update, block ID add
				String url="http://"+localIP+":8080"+serverPath;
				String urlParameter="ip="+bk.ip+"&log="+bk.log+"&hash="+bk.hash+"&pHash="
						+bk.allocatePreviousHash+"&localIP="+localIP+"&timeStamp="+bk.timeStamp+"&sendedFlag=1";
				//need update, sendedFlag=0
				
				URL object=new URL(url);
				HttpURLConnection con=(HttpURLConnection) object.openConnection();
				con.setRequestMethod("POST");
				con.setConnectTimeout(1000);
				con.setDoOutput(true);
				
				DataOutputStream send=new DataOutputStream(con.getOutputStream());
				send.writeBytes(urlParameter);
				send.flush();
				send.close();
				
				int responseCode=con.getResponseCode();
				System.out.println(">"+url);
				System.out.println(">"+urlParameter);
				System.out.println(">"+responseCode);
				//need update, make method.
			}
		}
		/*
		int i=1;
		for(BlockStructure lkp : logchain) {
			System.out.println("["+i+"]"+"LOG:"+lkp.log+" Hash:"+lkp.hash+" pHash:"+lkp.allocatePreviousHash+" <time:"+lkp.timeStamp+">");
			i++;
		}*/
	}
		
}

/*
 데이터베이스에 로그 파일마다 해시 값 계속해서 업데이트하는 코드. 쓰레드로.
블럭아이디 추가해서 같이 저장.
패킷보내는 코드 메소드로 따로 정리.
파일이름이랑 아이퍼, 서버경로 사용자로부터 받는걸로 수정.

 
 */