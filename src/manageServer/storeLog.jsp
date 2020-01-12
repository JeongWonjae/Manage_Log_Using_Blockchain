<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*,java.text.*,java.io.*,java.sql.*,java.math.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
  String ip=request.getParameter("ip");
  String log=request.getParameter("log");
  String hash=request.getParameter("hash");
  String pHash=request.getParameter("pHash");
  String localIP=request.getParameter("localIP");
  //String nonce=reqeust.getParameter("nonce"); //need update
  String timeStamp_c=request.getParameter("timeStamp");
  BigInteger timeStamp=new BigInteger(timeStamp_c);
  String path;
  String sendedFlag_c=request.getParameter("sendedFlag");
  int sendedFlag=Integer.parseInt(sendedFlag_c);

  int blockID=0;
  String blockID_s="";
  String sql=null;
  Connection conn=null;

  Statement stmt_callIP=null;
  PreparedStatement pstmt_callIP=null;
  ResultSet rs_callIP=null;

  Statement stmt_callBlockID=null;
  ResultSet rs_callBlockID=null;

  Statement stmt_addBlock=null;

  HashMap<String, String> participateNode=new HashMap<String, String>();
  String key=null;
  String serverPath=null;

  try
  {
      Class.forName("com.mysql.jdbc.Driver");
      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root");
      sql="select * from node";
      stmt_callIP=conn.createStatement();
      rs_callIP=stmt_callIP.executeQuery(sql);
      while(rs_callIP.next())
      {
        if(!(localIP.equals(rs_callIP.getString("ip"))))
        participateNode.put(rs_callIP.getString("ip"), rs_callIP.getString("serverPath"));
      }

      sql="select max(blockID) as count from Logchain";
      stmt_callBlockID=conn.createStatement();
      rs_callBlockID=stmt_callBlockID.executeQuery(sql);
      if(rs_callBlockID.next() && rs_callBlockID.getString("count")!=null)
      {
        blockID_s=rs_callBlockID.getString("count");
        blockID=Integer.parseInt(blockID_s);
        blockID++;
      }else
      {
        blockID=1;
      }

      sql="insert into Logchain values("+blockID+",'"+ip+"'"+",'"+log+"'"+",'"+hash+"'"+",'"+pHash+"'"+","+timeStamp+")";
      stmt_addBlock=conn.createStatement();
      stmt_addBlock.executeUpdate(sql);

      if(sendedFlag==0)
      {
        sendedFlag=1;

        Set setNode=participateNode.keySet();
        Iterator iterator=setNode.iterator();

        while(iterator.hasNext())
        {
          key=(String)iterator.next();
          serverPath=participateNode.get(key);
          path="http://"+key+":8080"+serverPath+"?ip="+ip+"&log="+log+"&hash="+hash
				        +"&pHash="+pHash+"&timeStamp="+timeStamp+"&sendedFlag="+sendedFlag; //need update
          response.sendRedirect(path); //need update
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
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log varchar(100) NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
PRIMARY KEY (ip)
);

create table node(
ip varchar(30) NOT NULL,
pw varchar(50) NOT NULL,
description varchar(30),
serverPath varchar(30) NOT NULL,
PRIMARY KEY (ip)
);

insert into node values("192.168.11.104", "1234", "HR-TEAM-PC-1");


alter table node add column serverPath varchar(30) NOT NULL;
alter table logchain modify timeStamp bigint;
update node set serverPath="/jsp/storeLog.jsp" where ip='192.168.11.104';

create table fileHash(
fileName varchar(30) NOT NULL,
hash varchar(100) NOT NULL,
PRIMARY KEY (fileName)
)
-->
