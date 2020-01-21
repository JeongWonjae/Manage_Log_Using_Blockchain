package controller;

public class Main {
//before start program
//setup -> Init.SettingParameter.Setting.init Path	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		view.Frame.run();
	}
}

/*
< Progress >
(완료) 블럭아이디 서버코드에 추가.
(완료) 데이터베이스에 로그 파일마다 해시 값 계속해서 업데이트하는 코드.
(완료) 패킷보내는 코드 메소드로 따로 정리.
(완료) 파일이름이랑 아이피,서버경로 사용자로부터 받는걸로 수정.
(완료) 로그파일마다 메소드 만들기
(완료) 패킷 날렸을때 반응이 200이 아니면 다시 보내기
(완료) 이전 해시 블럭가져오는 코드 추가.
(완료) 문자열 걸러내는 코드 추가.
(완료) 보내는 패킷에 로그 파일 종류 추가.
(완료) 로그 추가할 때 응답 기다리기
(완료) 로그파일마다 디비 추가.
(완료) 로그파일마다 메소드 채우기.
(완료) fileName->filePath 로 수정.
(완료) timeStamp구하는방식 변경.
(완료) preparedStatement 서버코드 시큐어코딩.
(완료) GUI 화면 추가.
(완료) 서버코드에서 sendedFlag->sentFlag로 수정하기.
(완료) 쓰레드 시간 멈춤 수정하기
(완료) 블록체인 로그 한 줄씩 출력화면 추가
(완료)gui 서버 시작/정지 함수 작성
(완료)서버코드에서 검증하는 부분 추가.
(완료)옵션파일 .setup 파일 연동 추가
(완료)로그 복사 파일 계속해서 업데이트해주는 코드 추가.
gui 리트라이 함수 작성
~진행중~정리, 테스트
 */