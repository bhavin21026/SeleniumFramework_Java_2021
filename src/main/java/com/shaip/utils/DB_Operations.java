package com.shaip.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DB_Operations {

	public synchronized HashMap<String, String> getSqlResultInMap(String sql) {
		HashMap<String, String> data_map = new HashMap<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://qa-shaipcloud-rds.cl9hyikgjrnp.us-east-1.rds.amazonaws.com/", "dml_user",
					"qa_shaipcloud@2021!");

			System.out.println("DB Connection done");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i <= md.getColumnCount(); i++) {
					data_map.put(md.getColumnName(i), rs.getString(i));
				}
			}
			System.out.println(data_map);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return data_map;
	}

	public  synchronized void resetSqlPassword(String password,String username) {  

			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://qa-shaipcloud-rds.cl9hyikgjrnp.us-east-1.rds.amazonaws.com/","dml_user","qa_shaipcloud@2021!");
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String descrypt=passwordEncoder.encode(password);
				
				System.out.println("DB Connection done");
				Statement stmt=con.createStatement();  
				stmt.executeUpdate("UPDATE shaip_security.user_ SET password= '"+descrypt+"' WHERE (userName ='"+username+"')");

	            
		}  catch (Exception e) {
			System.out.println(e);
		}


		}
	
	public  synchronized void resetAllPassword(String password) {  

		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://qa-shaipcloud-rds.cl9hyikgjrnp.us-east-1.rds.amazonaws.com/","dml_user","qa_shaipcloud@2021!");
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String descrypt=passwordEncoder.encode(password);
			
			System.out.println("DB Connection done");
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("UPDATE shaip_security.user_ SET password= '"+descrypt+"' WHERE userName LIKE 'appiumQA%'");

            
	}  catch (Exception e) {
		System.out.println(e);
	}


	}

	
	
}