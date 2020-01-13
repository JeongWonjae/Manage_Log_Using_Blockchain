import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		//input by user
		Scanner scan=new Scanner(System.in);
		System.out.print("[INFO] Input IP Address : ");
		String localIP = scan.nextLine();
		System.out.print("[INFO] Input Log Name : ");
		String logName=scan.nextLine();
		System.out.println("[INFO] Entered IP Address / Log Name : "+localIP+" / "+logName);
		System.out.println();
		scan.close();
		*/
		String localIP="192.168.11.104";
		String logName="wtmp";
		String logName2="messages";
		
		//logchain start
		kindOfLog.wtmpNmessages(logName, localIP);
		kindOfLog.wtmpNmessages(logName2, localIP);
		
	}
}

/*
(완료)블럭아이디 서버코드에 추가하기.
(완료)데이터베이스에 로그 파일마다 해시 값 계속해서 업데이트하는 코드.
(완료)패킷보내는 코드 메소드로 따로 정리.
(완료)파일이름이랑 아이피,서버경로 사용자로부터 받는걸로 수정.
(완료)로그파일마다 메소드 만들기
(완료)패킷 날렸을때 반응이 200이 아니면 다시 보내기
(완료)이전 해시 블럭가져오는 코드 추가.
(완료)문자열 걸러내는 코드 추가.
(완료)보내는 패킷에 로그 파일 종류 추가.
~진행~로그파일마다 디비 추가.
~진행~로그파일마다 메소드 채우기.
프리페얼드 서버코드 시큐어코딩하기.
GUI 화면 추가.
 */