import java.util.ArrayList;

public class kindOfLog {

	// /var/log/wtmp : last && /var/log/messages
	static void wtmpNmessages(String KindOfLog, String localIP) throws Exception { 
		
		String fileName=ConnectionMysql.getFileName(KindOfLog);
		String currentFileHash=ModifyLog.getFileHash(fileName);
		String lastFileHash="";
		ArrayList<String> log=new ArrayList<String>();
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		String serverPath=ConnectionMysql.getServerPath(localIP);
		
		//compare file hash.
		if(ConnectionMysql.isExist("select * from fileHash", "fileName", fileName)==true)
		{
			lastFileHash=ModifyLog.lastFileHash(fileName);
		}else
		{
			lastFileHash=ModifyLog.getFileHash(fileName);
		}
		
		//remove later, for testing
		lastFileHash="";
		
		log=OpenLog.wtmpNmessages(fileName);
		
		//check file hash
		if(!currentFileHash.equals(lastFileHash))
		{
			logchain=Block.createBlock(localIP, log, KindOfLog);
			
			for(BlockStructure bk : logchain)
			{
				ForwardPacket.sendServer(bk , localIP, serverPath, KindOfLog);
			}
			
			//save file hash
			if(ConnectionMysql.isExist("select * from filehash", "fileName", fileName)==true)
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
	
	static void utmp() { // /var/run/utmp : finger
		
	}
	
	static void boot() { // /var/log/boot.log
		
	}
	
	static void btmp() { // /var/log/btmp : lastb
		
	}
}
