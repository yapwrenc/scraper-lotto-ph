package com.kqtp2t;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LottoJdbc {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "lotto";
	private static final String DB_PASSWORD = "password123";
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	
	public void insertToDb(String type, String pick, String pickDate) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO DBUSER"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "('"+type+"','"+pick+"','" + "to_date('"+ pickDate  
				+ "', 'yyyy/mm/dd hh24:mi:ss'), to_date('"+ getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(insertTableSQL);
			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
	private Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(
                               DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	private String getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());
	}
}