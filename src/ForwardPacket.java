import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ForwardPacket {
	
	static void sendServer(BlockStructure bk, String localIP, String serverPath, String kindOfLog) throws Exception {
		
		Thread.sleep(300);
		String encodeLog=URLEncoder.encode(bk.log, "UTF-8");
		String url="http://"+localIP+":8080"+serverPath;
		
		String urlParameter="ip="+bk.ip+"&log="+encodeLog+"&hash="+bk.hash+"&pHash="
				+bk.allocatePreviousHash+"&timeStamp="+bk.timeStamp+"&sendedFlag=1"+"&kindOfLog="+kindOfLog;
		//need update, senndedFlag=0
		
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
		System.out.println("[INFO] Send URL : "+url);
		System.out.println("[INFO] Send Parameter : "+urlParameter);
		System.out.println("[INFO] Response Code : "+responseCode);
		//need update, make method.
		
		System.out.println("[INFO] Error check...");
		errorCheck(bk, localIP, serverPath, responseCode, kindOfLog);
	}
	
	static void errorCheck(BlockStructure bk, String localIP, String serverPath, int responseCode, String kindOfLog) throws Exception {
		if(responseCode!=200) {
			if(responseCode==500)
			{
				System.out.println("[ERROR] Server Inner Error.");
				System.exit(200);
			}
			Thread.sleep(200);
			System.out.println("[ERROR] Re-send by occured Error.");
			sendServer(bk, localIP, serverPath, kindOfLog);
		}else {
			System.out.println("[INFO] Packet forwarding Success!");
			System.out.println();
		}
	}
}
