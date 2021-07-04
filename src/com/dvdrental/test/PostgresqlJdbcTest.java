package com.dvdrental.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresqlJdbcTest {

	public static void main(String[] args) {
		try {
	    	 String driver="org.postgresql.Driver";
	    	 String jdbc_url="jdbc:postgresql://localhost/dvdrental";
	    	 String user="admin";
	    	 String pwd="admin";
	    	 Class.forName(driver);
	    	 System.out.println("loading==");
	    	 
	    	 
	    	 Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/dvdrental?user=postgres&password=admin");
	    	 System.out.println("con=="+con);
	    	 
	    	 Statement st = con.createStatement();
	    	
	    	  String sqlQuery="select * from country";
	    	  boolean flag= false;
	    	  ResultSet rs =st.executeQuery(sqlQuery);
	    	  System.out.println("CountyId\tCountry\tLastUpdate");
	    	  System.out.println("--------------------------------------");
	    	  while(rs.next())
	    	  {
	    	  flag=true;
	           System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDate(3));
	    	  }
	    	  if(flag==false)
	    	  {
	    	  System.out.println("No Records found");
	    	  }
	    	  con.close(); 
	    	}catch(Exception e) {
	    		System.out.println(e);
	    	}finally {
	    		System.out.println("finally");
	    	}
	}

}
