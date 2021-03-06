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
  BigInteger timeStamp=new BigInteger(timeStamp_c);
  String kindOfLog=request.getParameter("kindOfLog");
  String path;
  String sentFlag_c=request.getParameter("sentFlag");
  int sentFlag=Integer.parseInt(sentFlag_c);

  int blockID=0;
  String blockID_s="";
  String sql=null;
  Connection conn=null;

  Statement stmt_callIP=null;
  ResultSet rs_callIP=null;

  Statement stmt_callIPError=null;
  PreparedStatement pstmt_callIPError=null;
  ResultSet rs_callIPError=null;

  Statement stmt_callBlockID=null;
  ResultSet rs_callBlockID=null;

  Statement stmt_addBlock=null;
  PreparedStatement pstmt_addBlock=null;

  HashMap<String, String> participateNode=new HashMap<String, String>();
  HashMap<String, String> participateNodeError=new HashMap<String, String>();
  String key=null;
  String serverPath=null;
  String errorPath=null;

  String url="";
  String urlParameter="";

  ArrayList<String> addBlockConfirm=new ArrayList<String>();
  int addBlockConfirmFlag=0;
  int modifyConfirmFlag=1;

  try
  {
      //connect database
      Class.forName("com.mysql.jdbc.Driver");
      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false","root","root");

      //get participateNode, (ip, serverPath)
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

      //from other host
      if(sentFlag==1)
      {
        //check condition
        String checkCondition=hash.substring(0,2);

        if(checkCondition.equals("00"))
        {
          //set response header
          response.setHeader("addBlockConfirm", "ACK");

          //get blockID
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

          //add block
          sql="insert into logchain_"+kindOfLog+" values(?, ?, ?, ?, ?, ?, 1)";
          pstmt_addBlock=conn.prepareStatement(sql);
          pstmt_addBlock.setBigDecimal(1, new BigDecimal(blockID));
          pstmt_addBlock.setString(2, ip);
          pstmt_addBlock.setString(3, log);
          pstmt_addBlock.setString(4, hash);
          pstmt_addBlock.setString(5, pHash);
          pstmt_addBlock.setBigDecimal(6, new BigDecimal(timeStamp));
          pstmt_addBlock.executeUpdate();
        }
      //from local host
      }else if(sentFlag==0)
      {
        sentFlag=1;

        Set setNode=participateNode.keySet();
        Iterator iterator=setNode.iterator();

        while(iterator.hasNext())
        {
          key=(String)iterator.next();
          serverPath=participateNode.get(key);

          try
          {
            //send to other hosts
            Thread.sleep(100);
            url="http://"+key+":8080"+serverPath;
            urlParameter="ip="+ip+"&log="+log+"&hash="+hash+"&pHash="+pHash+"&timeStamp="+timeStamp+"&sentFlag="+sentFlag+"&kindOfLog="+kindOfLog;

            //setup option
            URL object=new URL(url);
          	HttpURLConnection con=(HttpURLConnection) object.openConnection();
          	con.setRequestMethod("POST");
          	con.setConnectTimeout(1000);
          	con.setDoOutput(true);

            //send
          	DataOutputStream send=new DataOutputStream(con.getOutputStream());
          	send.writeBytes(urlParameter);
          	send.flush();
          	send.close();

            //remove later, for testing
            int responseCode=con.getResponseCode();
        	  out.println("[Send URL = "+url);
        		out.println(" * Send Parameter = "+urlParameter+" * ");
        		out.println("Response Code = "+responseCode+"]");
            out.println("<br>");

            //check response value
            Map addBlockConfirmHeader=con.getHeaderFields();
            if(addBlockConfirmHeader.containsKey("addBlockConfirm"))
            {
              //sent accept by other hosts
              addBlockConfirm.add((addBlockConfirmHeader.get("addBlockConfirm")).toString());
            }
            con.disconnect();
          } catch (Exception e)
          {
            e.printStackTrace();
          }
        }
        for(int addBlockConfirmIndex=0; addBlockConfirmIndex<addBlockConfirm.size(); addBlockConfirmIndex++)
        {
          if((addBlockConfirm.get(addBlockConfirmIndex)).equals("[ACK]"))
          {
            addBlockConfirmFlag=1;
          }else
          {
            addBlockConfirmFlag=0;
          }
        }

        if(addBlockConfirmFlag==1)
        {
          //get blockID
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

          //add block
          sql="insert into logchain_"+kindOfLog+" values(?, ?, ?, ?, ?, ?, 1)";
          pstmt_addBlock=conn.prepareStatement(sql);
          pstmt_addBlock.setBigDecimal(1, new BigDecimal(blockID));
          pstmt_addBlock.setString(2, ip);
          pstmt_addBlock.setString(3, log);
          pstmt_addBlock.setString(4, hash);
          pstmt_addBlock.setString(5, pHash);
          pstmt_addBlock.setBigDecimal(6, new BigDecimal(timeStamp));
          pstmt_addBlock.executeUpdate();
        }else //occured error by other hosts
        {
          //get blockID
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

          //get errorPath

          sql="select * from node";
          stmt_callIPError=conn.createStatement();
          rs_callIPError=stmt_callIPError.executeQuery(sql);
          while(rs_callIPError.next())
          {
            if(localIP.equals(rs_callIPError.getString("ip")))
            {
            } else
            {
              participateNodeError.put(rs_callIPError.getString("ip"), rs_callIPError.getString("errorPath"));
            }
          }

          Set setNodeError=participateNodeError.keySet();
          Iterator iteratorRe=setNodeError.iterator();

          //re-send to other host for modify valid value of occured error log
          while(iteratorRe.hasNext())
          {
            key=(String)iteratorRe.next();
            errorPath=participateNodeError.get(key);
            try
            {
              //re-send to other hosts
              Thread.sleep(100);
              url="http://"+key+":8080"+errorPath;
              urlParameter="blockID="+blockID+"&kindOfLog="+kindOfLog;

              //setup option
              URL object=new URL(url);
            	HttpURLConnection con=(HttpURLConnection) object.openConnection();
            	con.setRequestMethod("POST");
            	con.setConnectTimeout(1000);
            	con.setDoOutput(true);

              //re-send
            	DataOutputStream send=new DataOutputStream(con.getOutputStream());
            	send.writeBytes(urlParameter);
            	send.flush();
            	send.close();

              //remove later, for testing
              int responseCode=con.getResponseCode();
          	  out.println("[Re-Send URL = "+url);
          		out.println(" * Re-Send Parameter = "+urlParameter+" * ");
          		out.println("Response Code = "+responseCode+"]");
              out.println("<br>");

              //check response value
              Map modifyConfirmHeader=con.getHeaderFields();
              if(modifyConfirmHeader.containsKey("modifyConfirm"))
              {
                if((modifyConfirmHeader.get("modifyConfirm")).equals("[NAK]"))
                {
                    modifyConfirmFlag=0;
                }
              }
              con.disconnect();
            } catch (Exception e)
            {
              e.printStackTrace();
            }
        }
      //modify fail log block
        if(modifyConfirmFlag==1)
        {
          sql="insert into logchain_"+kindOfLog+" values(?, ?, ?, ?, ?, ?, 0)";
          pstmt_addBlock=conn.prepareStatement(sql);
          pstmt_addBlock.setBigDecimal(1, new BigDecimal(blockID));
          pstmt_addBlock.setString(2, ip);
          pstmt_addBlock.setString(3, log);
          pstmt_addBlock.setString(4, hash);
          pstmt_addBlock.setString(5, pHash);
          pstmt_addBlock.setBigDecimal(6, new BigDecimal(timeStamp));
          pstmt_addBlock.executeUpdate();
        }else if(modifyConfirmFlag==0)
        {
          sql="insert into logchain_"+kindOfLog+" values(?, ?, ?, ?, ?, ?, 4)";
          pstmt_addBlock=conn.prepareStatement(sql);
          pstmt_addBlock.setBigDecimal(1, new BigDecimal(blockID));
          pstmt_addBlock.setString(2, ip);
          pstmt_addBlock.setString(3, log);
          pstmt_addBlock.setString(4, hash);
          pstmt_addBlock.setString(5, pHash);
          pstmt_addBlock.setBigDecimal(6, new BigDecimal(timeStamp));
          pstmt_addBlock.executeUpdate();
        }
      }
    }
  } catch (ClassNotFoundException e)
  { //error check driver
    out.println(e.getMessage()+"<br>");
  } finally
  {
    if(conn!=null)
    { //close driver
      try
      {
        conn.close();
      } catch(Exception e)
      {
      }
    }
  }
%>
