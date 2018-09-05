package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import wrappers.GenericWrappers;

/**
 * @author nmohan
 * Database connection class
 */
public class DataBaseConnection extends GenericWrappers
{

	//Database details
	private static String dbConnectionUrl;
	private static String dbUserName;
	private static String dbPassword;
	
	//Method to Database connectivity and to return the query result
	
	/**
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 */
	public String getSingleColumnDBResult(String sqlQuery) throws Exception
	{
		//Loading database details from property file
		Properties property = new Properties();
				try
				{
					property.load(new FileInputStream(new File("./src/main/resources/config.properties")));
					dbConnectionUrl = property.getProperty("DBCONNECTIONURL");
					dbUserName = property.getProperty("DBUSERNAME");
					dbPassword = property.getProperty("DBPASSWORD");
					
				}
				catch(IOException e) 
				{
					//Log4j_Utilities.error("No property file found");
				}	
				
		//Initializing the Connection variables
		Connection connection = null;
		ResultSet rs;
		String resultValue = "";
		
		//Driver connection
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
        }
		catch(ClassNotFoundException e) 
		{
			System.out.println(e);
        }
		
		//Establishing connection to DB
		try
		{
			connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbPassword);
			if(connection!=null)
			{
				System.out.println("Connection established successfully");
			}
			else
			{
				System.out.println("Connection failed");
			}
		}
			catch(SQLException e)
			{
				System.out.println(e);
			}
		
		//Executing the SQl Query through the connection
		Statement stmnt = connection.createStatement();
		rs = stmnt.executeQuery(sqlQuery);
		
		//Query returning Single value and printing
		try
		{
			while(rs.next())
			resultValue = rs.getString(1).toString();
			System.out.println("Result of a query" + resultValue);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		//Closing the established connection
		connection.close();
	
		//Returning the result value
		return resultValue;
	}
	
	/**
	 * @param sqlQuery
	 * @param verificationValue1
	 * @return
	 * @throws Exception
	 */
	public boolean getDBResult(String sqlQuery, String verificationValue1) throws Exception
	{
		
		//Loading database details from property file
		Properties property = new Properties();
		try
		{
			property.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			dbConnectionUrl = property.getProperty("DBCONNECTIONURL");
			dbUserName = property.getProperty("DBUSERNAME");
			dbPassword = property.getProperty("DBPASSWORD");
			
		}
		catch(IOException e) 
		{
			//Log4j_Utilities.error("No property file found");
		}		
		//Initializing the Connection variables
		Connection connection = null;
		ResultSet rs;
		
		//Driver connection
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
        }
		catch(ClassNotFoundException e) 
		{
			System.out.println(e);
        }
		
		//Establishing connection to DB
		try
		{
			connection = DriverManager.getConnection(dbConnectionUrl, dbUserName, dbPassword);
			if(connection!=null)
			{
				System.out.println("Connection established successfully");
			}
			else
			{
				System.out.println("Connection failed");
			}
		}
			catch(SQLException e)
			{
				System.out.println(e);
			}
		
		//Executing the SQl Query through the connection
		Statement stmnt = connection.createStatement();
		rs = stmnt.executeQuery(sqlQuery);
		
		//Query returning Single row and iterating each column and printing
		ResultSetMetaData rsmd =  rs.getMetaData();
		//int rsmd_ColumnCount = rsmd.getColumnCount();
		List<Map> dbRecords = new ArrayList<Map>();
		boolean recordFound = false;
		
		Map<String, String> Map = new HashMap<String, String>();
		try
		{
			while(rs.next())
			{
				for (int i=1;i<=rsmd.getColumnCount();i++) 
				{					
					Map.put(rsmd.getColumnName(i), rs.getString(i));
				}
				dbRecords.add(Map);					
			}
			
			//List<Map> verificationRecord = dbConnection.getDBResult(sqlQuery);
			//Iterating DB result and verifying the created transaction
			
			for (Map<String, String> map : dbRecords) 
			{
			    for (Entry<String, String> entry : map.entrySet()) 
			    {		        
			        String dbValue = (String) entry.getValue();
			      if(verificationValue1 != null && verificationValue1.equals(dbValue))
			      {
			    	  recordFound = true;
			      }
			    }
			}
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(NullPointerException nullError)
		{
			nullError.printStackTrace();
		}
		
		//Closing the established connection
		connection.close();
	
		//Returning the result value
		return recordFound;
	}
	
	
	
	
	
	
	

}
