import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMysql {

	static void queryNotReturn(String query) throws SQLException {
		
		java.sql.Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);	
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
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
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
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&charaterEncoding=euckr&useSSL=false", "root", "root");
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
	
}
