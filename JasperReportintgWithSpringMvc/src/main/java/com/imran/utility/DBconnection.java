package com.imran.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {       //Singleton pattern for database connection 
	
	static DBconnection dbConnectionObj=null;
	
	private DBconnection(){
		
	}
	public static synchronized DBconnection getInstance(){
		if(dbConnectionObj==null){
			dbConnectionObj=new DBconnection();
			return dbConnectionObj;
		}else
		return dbConnectionObj;
	}
	
	public Connection getConnection(){
		Connection conn=null;
		
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost:3306/simple";
		final String USER="root";
		final String PASSWRD="root";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL,USER,PASSWRD);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}

