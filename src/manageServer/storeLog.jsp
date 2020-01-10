<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*,java.text.*,java.io.*,java.sql.*" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
  String ip=request.getParameter("ip");
  String log=request.getParameter("log");
  String hash=request.getParameter("hash");
  String pHash=request.getParameter("pHash");
  //String nonce=reqeust.getParameter("nonce");
  int timeStamp=request.getParameter("timeStamp");
  int sendedFlag=0;
  String path;

  if(request.getParameter("sendedFlag"))
  {
      sendedFlag=request.getParameter("sendedFlag");
  }

  int blockID;

  String sql=null;
  Connection conn=null;
  Statement stmt=null;
  PreparedStatement pstmt=null;
  ResultSet rs=null;

  String participateNode[]={};
  int pNodeIndex=0;
  int sendingIndex=0;

  try
  {
      Class.forName("com.mysql.jdbc.Driver");
      conn=DrvierManager.getConnetion("jdbc:mysql://localhost:3306/LogBCK_Project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root")

      sql="select * from node";
      stmt=conn.createStatement();
      rs=stmt.executeQuery(sql);

      while(rs.next())
      {
        participateNode[pNodeIndex]=rs.getString("ip");
      }

      sql="insert into Logchain values()";
      stmt=conn.createStatement();
      rs=stmt.executeQuery(sql);

      if(sendedFlag==0)
      {
        sendedFlag=1;
        for(sendingIndex=0;sendingIndex<participateNode.length;sendingIndex++)
        {
          path="http://"+participateNode[sendingIndex]+":8080/server/bckLog/storeLog.jsp?" ~~;
          response.sendRedirect(path);
        }
      }

  } catch (ClassNotFoundException e)
  {
    out.println(e.getMessage()+"<br>");
  } catch (SQLException e)
  {
    out.println(e);
  }
%>

<!--
create database logBCK_Project,

create table logchain(
blockID int NOT NULL,
ip varchar(30) NOT NULL,
log varchar(50) NOT NULL,
hash varchar(20) NOT NULL,
pHash varchar(20) NOT NULL,
timeStamp int NOT NULL,
)

create table node(
ip varchar(30) NOT NULL,
pw varchar(50) NOT NULL,
desc varchar(30) NOT NULL,
PRIMARY KEY (ip)
)

insert into node values("192.168.11.104", "1234", "HR-TEAM-PC-1");
-->
