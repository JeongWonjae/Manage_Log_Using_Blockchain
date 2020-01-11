<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*,java.text.*,java.io.*,java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
  String ip=request.getParameter("ip");
  String log=request.getParameter("log");
  String hash=request.getParameter("hash");
  String pHash=request.getParameter("pHash");
  //String nonce=reqeust.getParameter("nonce"); //need update
  String timeStamp_c=request.getParameter("timeStamp");
  int timeStamp=Integer.parseInt(timeStamp_c);
  String path;
  String sendedFlag_c=request.getParameter("sendedFlag");
  int sendedFlag=Integer.parseInt(sendedFlag_c);

  out.println(sendedFlag);

  int blockID=1; //need update
  String sql=null;
  Connection conn=null;

  Statement stmt_callIP=null;
  PreparedStatement pstmt_callIP=null;
  ResultSet rs_callIP=null;

  Statement stmt_addBlock=null;

  String[] participateNode=new String[1024];
  int pNodeIndex=0;
  int sendingIndex=0;

  try
  {
      Class.forName("com.mysql.jdbc.Driver");
      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root");
      sql="select * from node";
      stmt_callIP=conn.createStatement();
      rs_callIP=stmt_callIP.executeQuery(sql);
      while(rs_callIP.next())
      {
        participateNode[pNodeIndex]=rs_callIP.getString("ip");
        pNodeIndex+=1;
      }

      sql="insert into Logchain values("+blockID+",'"+ip+"'"+",'"+log+"'"+",'"+hash+"'"+",'"+pHash+"'"+","+timeStamp+")";
      stmt_addBlock=conn.createStatement();
      stmt_addBlock.executeUpdate(sql);

      if(sendedFlag==0)
      {
        sendedFlag=1;
        for(sendingIndex=0;sendingIndex<participateNode.length;sendingIndex++)
        {
          //자기는 제외해야함
          path="http://"+participateNode[0]+":8080/jsp/storeLog.jsp?ip="+ip+"&log="+log+"&hash="+hash
				        +"&pHash="+pHash+"&timeStamp="+timeStamp+"&sendedFlag="+sendedFlag; //need update
          response.sendRedirect(path);
        }
      }
  } catch (ClassNotFoundException e)
  {
    out.println(e.getMessage()+"<br>");
  }
%>

<!--
create database logBCK_Project;

create table logchain(
blockID int NOT NULL,
ip varchar(30) NOT NULL,
log varchar(50) NOT NULL,
hash varchar(20) NOT NULL,
pHash varchar(20) NOT NULL,
timeStamp int NOT NULL,
PRIMARY KEY (ip)
);

create table node(
ip varchar(30) NOT NULL,
pw varchar(50) NOT NULL,
description varchar(30),
PRIMARY KEY (ip)
);

insert into node values("192.168.11.104", "1234", "HR-TEAM-PC-1");
-->
