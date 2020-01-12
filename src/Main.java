import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String fileName="A://testfile/wtmp.txt"; //need update, input by user
		String localIP="192.168.11.104"; //need update, input by user
		String serverPath="/jsp/storeLog.jsp";
		
		String currentFileHash=ModifyLog.getFileHash(fileName);
		String lastFileHash="";
		String log[]=new String[1024];
		ArrayList<BlockStructure> logchain=new ArrayList<BlockStructure>();
		
		//for testing. remove later//
		String lastLogFileHash="13d87338d5abf7d1fb1fbffee23c6f1a6e4416fd21c6ff0c59ed5dd56122bb";
		
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
}

/*
(완료)블럭아이디 서버코드에 추가하기.
(완료)데이터베이스에 로그 파일마다 해시 값 계속해서 업데이트하는 코드.
(완료)패킷보내는 코드 메소드로 따로 정리.
파일이름이랑 아이퍼, 서버경로 사용자로부터 받는걸로 수정.
프리페얼드 서버코드 시큐어코딩하기.
(완료)패킷 날렸을때 반응이 200이 아니면 다시 보내기
 */


/*
int i=1;
for(BlockStructure lkp : logchain) {
	System.out.println("["+i+"]"+"LOG:"+lkp.log+" Hash:"+lkp.hash+" pHash:"+lkp.allocatePreviousHash+" <time:"+lkp.timeStamp+">");
	i++;
}*/