import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;


public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String time;
		/*
		time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmm"));
		System.out.println(time);
		
		System.out.println(LogTime.getCurrentTimeWtmp("root     :1           :1               Tue Jan  7 01:41 - crash  02:53"));

		int blockID=1;
		String serverPath="/jsp/storeLog.jsp";
		String ip="1.1.1.1";
		String log="this is log";
		String hash="4a5s4e1d7a6e2";
		String pHash="15451427dd47we7";
		int timeStamp=11123224;
		String[] participateNode=new String[10];
		int sendingIndex=0;
		participateNode[0]="192.168.11.104";
		int sendedFlag=1;
		
		String sql="insert into Logchain values("+blockID+",'"+ip+"'"+",'"+log+"'"+",'"+hash+"'"+",'"+pHash+"'"+","+timeStamp+")";
		System.out.println(sql);
		
		String path="http://"+participateNode[0]+":8080"+serverPath+"?ip="+ip+"&log="+log+"&hash="+hash
				+"&pHash="+pHash+"&timeStamp="+timeStamp+"&sendedFlag="+sendedFlag;
		System.out.println(path);*/
		
		String url="http://localhost:8080/jsp/test2.jsp";
		String urlParameter="a=hi";
		
		URL obj=new URL(url);
		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setConnectTimeout(1000);
		con.setDoOutput(true);
		
		DataOutputStream wr=new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameter);
		wr.flush();
		wr.close();
		
		int responseCode=con.getResponseCode();
		System.out.println(">"+url);
		System.out.println(">"+responseCode);
	}
}
