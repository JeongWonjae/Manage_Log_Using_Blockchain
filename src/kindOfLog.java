import java.util.ArrayList;

public class kindOfLog {

	static void wtmp(String KindOfLog, String localIP) throws Exception { // /var/log/wtmp : last
		
		String fileName=ConnectionMysql.getFileName(KindOfLog);
		String currentFileHash=ModifyLog.getFileHash(fileName);
		String lastFileHash="";
		String log[]=new String[1024];
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		
		//for testing. remove later//
		String lastLogFileHash="13d87338d5abf7d1fb1fbffee23c6f1a6e4416fd21c6ff0c59ed5dd56122bb";
		String serverPath=ConnectionMysql.getServerPath(localIP);
		
		//compare file hash.
		/*
		if(ConnectionMysql.isExist("select * from fileHash", "fileName", fileName)==true)
		{
			lastFileHash=ModifyLog.lastFileHash(fileName);
		}else
		{
			lastFileHash=ModifyLog.getFileHash(fileName);
		}
		*/
		
		log=OpenLog.wtmp(fileName);
		
		//check file hash
		if(!currentFileHash.equals(lastFileHash))
		{
			logchain=Block.createBlock(localIP , log);
			
			for(BlockStructure bk : logchain)
			{
				ForwardPacket.sendServer(bk , localIP, serverPath);
			}
			
			//save file hash
			if(ConnectionMysql.isExist("select * from fileHash", "fileName", fileName)==true)
			{
				ModifyLog.updateFileHash(fileName, currentFileHash);
			}else
			{
				ModifyLog.saveFileHash(fileName, currentFileHash);
			}
		}
	}
	
	static void secure() { // /var/log/secure
		
	}
	
	static void messages() { // /var/log/message
		
	}
	
	static void utmp() { // /var/run/utmp : finger
		
	}
	
	static void boot() { // /var/log/boot.log
		
	}
	
	static void btmp() { // /var/log/btmp : lastb
		
	}
}
