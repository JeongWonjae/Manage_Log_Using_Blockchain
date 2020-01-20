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
				+bk.allocatePreviousHash+"&timeStamp="+bk.timeStamp+"&sentFlag=0"+"&kindOfLog="+kindOfLog;
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
		
		Frame.callTextArea("---------------------------------------------------------------------------------------------------");
		Frame.callTextArea("[+] Log : "+bk.log);
		Frame.callTextArea("[+] hash : "+bk.hash);
		Frame.callTextArea("[+] pHash : "+bk.allocatePreviousHash);
		Frame.callTextArea("[+] timeStamp : "+bk.timeStamp);
		Frame.callTextArea("");
		Frame.callTextArea("[+] Send URL : "+url);
		Frame.callTextArea("[+] Send Parameter : "+urlParameter);
		Frame.callTextArea("[+] Response Code : "+responseCode);
		
		Frame.callTextArea("[+] Error check.");
		errorCheck(bk, localIP, serverPath, responseCode, kindOfLog);
	}
	
	static void errorCheck(BlockStructure bk, String localIP, String serverPath, int responseCode, String kindOfLog) throws Exception {
		if(responseCode!=200) {
			if(responseCode==500)
			{	
				Frame.callTextArea("[!] Server Inner Error.");
				System.exit(200);
			}
			Frame.callTextArea("[!] Re-send by occured Error.");
			sendServer(bk, localIP, serverPath, kindOfLog);
		}else {
			Frame.callTextArea("[+] Packet forwarding Success.");
			Frame.callTextArea("---------------------------------------------------------------------------------------------------");
			Frame.callTextArea("\n");
			view.Frame.consoleTextArea.setCaretPosition(view.Frame.consoleTextArea.getDocument().getLength());
		}
	}
}
