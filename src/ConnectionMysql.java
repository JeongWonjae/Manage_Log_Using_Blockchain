import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMysql {

	static void queryNotReturn(String query) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			stmt.executeQuery(query);	
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		
	}
	
	static String queryReturnString(String query, String findAttribute) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String res="";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				res=rs.getString(findAttribute);
			}
	
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		return res;
	}
	
	static int queryReturnInt(String query, String findAttribute) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		int res=0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				res=rs.getInt(findAttribute);
			}
	
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		return res;
	}
	
	static void insertIntoFileHash(String fileName,String fileHash) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		
		String query="insert into fileHash values("+"'"+fileName+"'"+",'"+fileHash+"')";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			stmt.executeUpdate(query);
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
	}
	
	static void updateSetFileHash(String fileName, String fileHash) throws SQLException{
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		
		String query="update fileHash set hash='"+fileHash+"' where fileName='"+fileName+"'";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			stmt.executeUpdate(query);
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		
	}
	
	static boolean isExist(String query, String findAttributeName, String findAttributeValue) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		query=query+" where "+findAttributeName+"='"+findAttributeValue+"'";
		int is=0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				is=1;
			}else
			{
				is=0;
			}
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		if(is==1)
		{
			return true;
		}else if(is==0)
		{
			return false;
		}
		return false;
	}
	
	static String getServerPath(String ip) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String res="";
		String query="select * from node where ip='"+ip+"'";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				res=rs.getString("serverPath");
			}
	
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		return res;
	}
	
static String getFileName(String kindOfLog) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String res="";
		String query="select * from filehash where fileName like '%"+kindOfLog+"%'";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/logbck_project?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				res=rs.getString("fileName");
			}
	
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
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
		return res;
	}
}
