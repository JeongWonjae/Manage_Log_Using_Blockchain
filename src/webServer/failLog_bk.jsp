<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.DataOutputStream,java.util.*,java.text.*,java.io.*,java.sql.*,java.math.*,java.net.HttpURLConnection,java.net.URL" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

  String blockID_s=request.getParameter("blockID");
  BigInteger blockID=new BigInteger(blockID_s);
  String kindOfLog=request.getParameter("kindOfLog");

  String sql=null;
  Connection conn=null;
  Statement stmt_setValid=null;
  Statement stmt_checkValid=null;
  ResultSet rs_checkValid=null;

  try
  {
    //connect database
    Class.forName("com.mysql.jdbc.Driver");
    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root");

    //set valid 1->0
    sql="update logchain_"+kindOfLog+" set valid=0 where "+"blockID="+blockID;
    stmt_setValid=conn.createStatement();
    stmt_setValid.executeUpdate(sql);

    //get valid value
    sql="select valid from logchain_"+kindOfLog+" where blockID="+blockID;
    stmt_checkValid=conn.createStatement();
    rs_checkValid=stmt_checkValid.executeQuery(sql);
    while(rs_checkValid.next())
    {
      if(Integer.parseInt(rs_checkValid.getString("valid"))==1) //success
      {
        response.setHeader("modifyConfirm", "ACK");
      }else //fail
      {
        response.setHeader("modifyConfirm", "NAK");
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
