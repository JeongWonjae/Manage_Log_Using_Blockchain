package controller;
import java.util.ArrayList;

import view.Frame;

public class kindOfLog {

	/*
	/var/run/utmp : finger
	/var/log/wtmp : last
	/var/log/btmp : lastb
	/var/log/messages
	/var/log/auth
	/var/log/daemon
	/var/log/user
	/var/log/boot.log
	/var/log/dpkg
	*/
	public static void EnrollLogBlock(String KindOfLog, String localIP) throws Exception {
		
		String filePath=ConnectionMysql.getFilePath(KindOfLog);
		String currentFileHash=ModifyLog.getFileHash(filePath);
		String lastFileHash="";
		ArrayList<String> log=new ArrayList<String>();
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		String serverPath=ConnectionMysql.getServerPath(localIP);
		
		//value for compare already enrolled log
		int startEnrollmentFlag=0;
		int compareEndFlag=0;
		String compareCurrentLog="";
		String compareLastLog=ConnectionMysql.getLastPreviousStringValue(KindOfLog, "log");
		
		//if first log
		if(compareLastLog=="")
		{
			startEnrollmentFlag=1;
			compareEndFlag=1;
		}
		
		//get last file hash
		if(ConnectionMysql.isExist("select * from filehash", "filePath", filePath)==true)
		{
			lastFileHash=ModifyLog.lastFileHash(filePath);
		}else
		{
			lastFileHash="";
		}
		
		//remove later, for testing
		lastFileHash="";
		
		//open log
		log=OpenLog.callLogLine(filePath);
		
		//compare file hash
		if(!currentFileHash.equals(lastFileHash))
		{
			//create block
			logchain=Block.createBlock(localIP, log, KindOfLog);
			
			//loop block
			for(BlockStructure cp : logchain)
			{
				compareCurrentLog=(cp.log).replaceAll("'", "");
				if(compareCurrentLog.equals(compareLastLog))
				{
					compareEndFlag=1;
				}
			}
			
			if(compareEndFlag==1)
			{
				for(BlockStructure bk : logchain)
				{
					compareCurrentLog=(bk.log).replaceAll("'", "");
					
					if(compareCurrentLog.equals(compareLastLog))
					{
						startEnrollmentFlag=1;
					}else if(startEnrollmentFlag==1)
					{
						ForwardPacket.sendServer(bk , localIP, serverPath, KindOfLog);
					}else
					{
						Frame.callTextArea("[+] Sending Failed. Already enrolled this log block"+bk.log);
						Frame.callTextArea("[+] Log : "+bk.log);
						Frame.callTextArea("");
					}
				}
			}else {
				for(BlockStructure bk : logchain)
				{
					ForwardPacket.sendServer(bk , localIP, serverPath, KindOfLog);
				}
			}
			
			//save file hash
			if(ConnectionMysql.isExist("select * from filehash", "filePath", filePath)==true)
			{
				ModifyLog.updateFileHash(filePath, currentFileHash);
			}else
			{
				ModifyLog.saveFileHash(filePath, currentFileHash);
			}
		}
	}
	
	/*
	/var/log/secure
	 */
	static void secure() {
		
	}
	
}
