package com.satya.projects.jdbc_data_base_connecion;

import oracle.jdbc.pool.OracleDataSource;
import oracle.ucp.proxy.annotation.Pre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@SpringBootApplication
public class JdbcDataBaseConnecionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JdbcDataBaseConnecionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OracleDataSource oracleDataSource = new OracleDataSource();
		oracleDataSource.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		Properties properties = new Properties();
		properties.put("user", "sys");
		properties.put("password", "local_system_db");
		properties.put("internal_logon", "sysdba");
		oracleDataSource.setConnectionProperties(properties);
		Connection connection =  oracleDataSource.getConnection();

		// INSERT THE RECORD INTO A TABLE
		PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ISSUES(ISSUE_TITLE, ISSUE_BY) VALUES(?, ?)");
		insertStatement.setString(1,"Requesting project access for New Hire");
		insertStatement.setString(2,"Mark");
		insertStatement.execute();

		// UPDATE THE RECORD FROM A TABLE
		PreparedStatement updateStatement =  connection.prepareStatement("UPDATE ISSUES SET ISSUE_STATUS=? WHERE ISSUE_ID=?");
		updateStatement.setInt(1,7);
		updateStatement.setInt(2,1);
		updateStatement.execute();

		// DELETE THE RECORD FROM A TABLE
		PreparedStatement deleteStatement = connection.prepareStatement("DELETE ISSUES WHERE ISSUE_ID=?");
		deleteStatement.setInt(1,1);
		deleteStatement.execute();

		PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM ISSUES");
		ResultSet resultSet = selectStatement.executeQuery();
		while (resultSet.next()){
			System.out.println("......................");
			System.out.println(" ID: "+resultSet.getInt("ISSUE_ID"));
			System.out.println(" BY: "+resultSet.getString("ISSUE_BY"));
			System.out.println(" ON: "+resultSet.getDate("ISSUE_ON").toString());
			System.out.println(" ABT: "+resultSet.getString("ISSUE_TITLE"));
			System.out.println(" STA: "+resultSet.getInt("ISSUE_STATUS"));
		}

		// TESTING THE DB CONNECTION WITH SIMPLE STATEMENT
		PreparedStatement statement = connection.prepareStatement("SELECT 'Hello Satya' FROM dual");
		ResultSet sampleResultSet = statement.executeQuery();
		while(sampleResultSet.next()){
			System.out.println(sampleResultSet.getString(1));
		}
	}
}
