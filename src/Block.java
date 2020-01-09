import java.util.ArrayList;
import java.util.Date;

public class Block {

	static void createBlock(String ip,String allLogArr[]) {
		
		ArrayList<BlockStructure> bchain=new ArrayList<BlockStructure>();
		
		for(int i=0;i<allLogArr.length;i++) 
		{
			if(allLogArr[i]!=null)
			{
				BlockStructure block=new BlockStructure(ip, allLogArr[i]);
				bchain.add(block);
			}
		}
		
		
		int i=1;
		for(BlockStructure lkp : bchain) {
			System.out.println("["+i+"]"+"LOG:"+lkp.log+" Hash:"+lkp.hash+" pHash:"+lkp.allocatePreviousHash+" <time:"+lkp.timeStamp+">");
			i++;
		}
	}
}

class BlockStructure{
	
	int blockID;
	String ip;
	String log;
	String hash;
	String allocatePreviousHash;
	long timeStamp;
	int importanceLevel;
	static String previousHash="first block";
	
	BlockStructure(String ip, String log){
		this.ip=ip;
		this.log=log;
		this.allocatePreviousHash=previousHash;
		this.hash=Hash.makeHash(log, allocatePreviousHash);
		previousHash=hash;
		this.timeStamp=new Date().getTime();
		//this.importanceLevel=importanceLevel;
	}
}
//(+)take out 'blockID' to Database
//(+)take out 'previousHash' to Database
 