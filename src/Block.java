import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Block {

	static ArrayList<BlockStructure> createBlock(String ip,ArrayList<String> allLogArr, String KindOfLog) throws SQLException {
		
		ArrayList<BlockStructure> bchain=new ArrayList<BlockStructure>();
		
		for(int i=0;i<allLogArr.size();i++) 
		{
			if(allLogArr.get(i)!=null)
			{
				BlockStructure block=new BlockStructure(ip, allLogArr.get(i), i, KindOfLog);
				bchain.add(block);
			}
		}
		return bchain;
	}
}

class BlockStructure{
	
	String ip;
	String log;
	String hash;
	String allocatePreviousHash;
	long timeStamp;
	int importanceLevel;
	static String previousHash="first block";
	static String lastKindOfLog="";
	
	BlockStructure(String ip, String log, int i, String kindOfLog) throws SQLException{
		this.ip=ip;
		this.log=log;
		
		if(!lastKindOfLog.equals(kindOfLog))
		{
			previousHash="first block";
		}
		
		if(ConnectionMysql.isExist("select * from logchain_"+kindOfLog, "hash", null)==true && i==0)
		{
			previousHash=ConnectionMysql.getLastPreviousHash(kindOfLog);
		}else
		{
		}
		
		this.allocatePreviousHash=previousHash;
		this.hash=Hash.makeHash(log, allocatePreviousHash);
		previousHash=hash;
		this.timeStamp=new Date().getTime();
		//this.importanceLevel=importanceLevel;
		lastKindOfLog=kindOfLog;
	}
}
//(+)take out 'previousHash' to Database
 