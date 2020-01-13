<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.DataOutputStream,java.util.*,java.text.*,java.io.*,java.sql.*,java.math.*,java.net.HttpURLConnection,java.net.URL" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
  //static value per client
  String localIP="192.168.11.104";

  String ip=request.getParameter("ip");
  String log=request.getParameter("log");
  String hash=request.getParameter("hash");
  String pHash=request.getParameter("pHash");
  //String nonce=reqeust.getParameter("nonce"); //need update
  String timeStamp_c=request.getParameter("timeStamp");
  String kindOfLog=request.getParameter("kindOfLog");
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

  String url="";
  String urlParameter="";

  try
  {
      Class.forName("com.mysql.jdbc.Driver");
      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root");
      sql="select * from node";
      stmt_callIP=conn.createStatement();
      rs_callIP=stmt_callIP.executeQuery(sql);
      while(rs_callIP.next())
      {
        if(localIP.equals(rs_callIP.getString("ip")))
        {
        } else
        {
          participateNode.put(rs_callIP.getString("ip"), rs_callIP.getString("serverPath"));
        }
      }

      sql="select max(blockID) as count from logchain_"+kindOfLog;
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

      sql="insert into logchain_"+kindOfLog+" values("+blockID+",'"+ip+"'"+",'"+log+"'"+",'"+hash+"'"+",'"+pHash+"'"+","+timeStamp+")";
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

          try
          {
            Thread.sleep(100);
            url="http://"+key+":8080"+serverPath;
            urlParameter="ip="+ip+"&log="+log+"&hash="+hash+"&pHash="+pHash+"&timeStamp="+timeStamp+"&sendedFlag="+sendedFlag+"&kindOfLog="+kindOfLog;

            URL object=new URL(url);
          	HttpURLConnection con=(HttpURLConnection) object.openConnection();
          	con.setRequestMethod("POST");
          	con.setConnectTimeout(1000);
          	con.setDoOutput(true);

          	DataOutputStream send=new DataOutputStream(con.getOutputStream());
          	send.writeBytes(urlParameter);
          	send.flush();
          	send.close();

            //need update, add check error code
            int responseCode=con.getResponseCode();
        	  out.println("[INFO] Send URL : "+url);
        		out.println("[INFO] Send Parameter : "+urlParameter);
        		out.println("[INFO] Response Code : "+responseCode);
            out.println("//////");

          } catch (Exception e)
          {
            e.printStackTrace();
          }

        }
      }
  } catch (ClassNotFoundException e)
  {
    out.println(e.getMessage()+"<br>");
  } finally
  {
    if(conn!=null)
    {
      try
      {
        conn.close();
      } catch(Exception e)
      {
      }
    }
  }

%>

<!--
create database logbck_project;

create table logchain_wtmp(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
PRIMARY KEY (hash, pHash)
);

create table logchain_messages(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
PRIMARY KEY (hash, pHash)
);

create table node(
ip varchar(30) NOT NULL,
pw varchar(50) NOT NULL,
description varchar(30),
serverPath varchar(30) NOT NULL,
PRIMARY KEY (ip)
);

create table filehash(
fileName varchar(30) NOT NULL,
hash varchar(100) NOT NULL,
PRIMARY KEY (fileName)
);

insert into node values("192.168.11.104", "1234", "HR-TEAM-PC-1", "/jsp/storeLog.jsp");
insert into node values("192.168.11.105", "randd", "R&D-TEAM-PC-1", "/serv/storeLog.jsp");
insert into node values("192.168.11.8", "1111", "HR-TEAM-PC-2", "/bckProject/storeLog.jsp");


alter table node add column serverPath varchar(30) NOT NULL;
alter table logchain modify timeStamp bigint;
update node set serverPath="/jsp/storeLog.jsp" where ip='192.168.11.104';
insert into filehash values("A://logfile/messages.txt", "1")
-->
