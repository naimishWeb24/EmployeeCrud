package com.employeecrud.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
		private static Connection connection = null;
		public static Connection getConnect() {
			try {	
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SampleDb","root","root");		
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return connection;
		}
}
