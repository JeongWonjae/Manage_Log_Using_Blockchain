package controller;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import view.Frame;

public class ForwardPacket {
	
	static void sendServer(BlockStructure bk, String localIP, String serverPath, String kindOfLog) throws Exception {
		
		Thread.sleep(300);
		bk.log=(bk.log).replaceAll("'", "");
		String encodeLog=URLEncoder.encode(bk.log, "UTF-8");
		String url="http://"+localIP+":8080"+serverPath;
		
		String urlParameter="ip="+bk.ip+"&log="+encodeLog+"&hash="+bk.hash+"&pHash="
				+bk.allocatePreviousHash+"&timeStamp="+bk.timeStamp+"&sentFlag=1"+"&kindOfLog="+kindOfLog;
		//need update, senndedFlag=0
		
		URL object=new URL(url);
		HttpURLConnection con=(HttpURLConnection) object.openConnection();
		con.setRequestMethod("POST");
		con.setConnectTimeout(5000);
		con.setDoOutput(true);
		
		DataOutputStream send=new DataOutputStream(con.getOutputStream());
		send.writeBytes(urlParameter);
		send.flush();
		send.close();
		
		int responseCode=con.getResponseCode();
		
		Frame.consoleTextArea.append("[INFO] Send URL : "+url+"\n"); //need update
		Frame.consoleTextArea.append("[INFO] Send Parameter : "+urlParameter+"\n");
		Frame.consoleTextArea.append("[INFO] Response Code : "+responseCode+"\n");
	
		Frame.consoleTextArea.append("[INFO] Error check..."+"\n");
		errorCheck(bk, localIP, serverPath, responseCode, kindOfLog);
	}
	
	static void errorCheck(BlockStructure bk, String localIP, String serverPath, int responseCode, String kindOfLog) throws Exception {
		if(responseCode!=200) {
			if(responseCode==500)
			{
				Frame.consoleTextArea.append("[ERROR] Server Inner Error."+"\n");
				System.exit(200);
			}
			Thread.sleep(200);
			Frame.consoleTextArea.append("[ERROR] Re-send by occured Error."+"\n");
			sendServer(bk, localIP, serverPath, kindOfLog);
		}else {
			Frame.consoleTextArea.append("[INFO] Packet forwarding Success!"+"\n");
			Frame.consoleTextArea.append("\n");
		}
	}
}
