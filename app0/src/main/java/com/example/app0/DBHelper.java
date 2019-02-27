package com.example.app0;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The DBHelper class connects to a database and has some methods to run queries
 * This class was written with mysql in mind and will need modification to work with another database
 * The address of the database is hardcoded as this should not change often 
 * and now we don't need to keep sending it when we connect
 * this should probably be changed in future
 */
public class DBHelper {
	private String username;
	private String password;
	private Connection con;
	
	// this string represents the url of the database we are connecting to
	// I've used mysql which runs by default on port 3306
	// at the time of writing this I'm using a database called ttdb
	private String address = "jdbc:mysql://localhost:3306/ttdb";
	
	/**
	 * Constructor for the DBHelper class
	 * 
	 * @param username the username used to access the database
	 * @param password the password used to access the database
	 */
	public DBHelper(String username, String password) {
		this.username = username;
		this.password = password;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection(this.address,this.username,this.password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * @param login_id the username you want to check
	 * @param login_pw the password for the username
	 * @return a boolean representing whether the login is correct or not
	 */
	public boolean check_login(String login_id,String login_pw){  
    	boolean result = false;
		try{   
			Statement stmt = this.con.createStatement();  
			ResultSet rs   = stmt.executeQuery("SELECT * FROM users WHERE id = " + login_id + " AND password = " + login_pw);  
			if(!rs.next())  
			{
				result =  false;
			}
			else
			{
				result = true;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			result = false;
		}  
		
		return result;
	}
    
    
    /**
     * closes and reopens the connection to the database
     * intended to be used in conjunction with set_address()
     * @return a boolean  value representing whether the connection was successful
     */
    public boolean reset_connection(){
    	close_connection();
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection(this.address,this.username,this.password);
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
    }
    /**
     * closes the database connection
     * it is important to use this to reduce resource usage
     */
    public void close_connection(){
    	try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * overwrites the default database connection string 
     * format of the string should be jdbc:db_provider:db_address/db_name
     * 
     * only use this if you know what you're doing
     * 
     * @param new_address
     */
    public void set_address(String new_address) {
    	this.address = new_address;
    }

}
