# JDBC Data Base Connection

For Connectiong to the Database we used `OracleDataSource` class 

### Data Source Connection
* Set the datasource url using `oracleDataSource.setURL("jdbc:oracle:thin:@<host>:<port>:<SID>");`
* Create `Properties` object and set the following values
  * `properties.put("user", "<username>");`
  * `properties.put("password", "<password>");`
  * `properties.put("internal_logon", "<role>");`
* Once you created the properties and set it to the datasource as properties connection
  * `oracleDataSource.setConnectionProperties(properties);`
* Get the connection instance
  * `Connection connection =  oracleDataSource.getConnection();`

### SQL Prepared Statements
* Once you have created the connection create PreparedStatements and person SQL executions like these
  #### INSERT
    ``` SQL
    PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ISSUES(ISSUE_TITLE, ISSUE_BY) VALUES(?, ?)");
    insertStatement.setString(1,"Requesting project access for New Hire");
    insertStatement.setString(2,"Mark");
    insertStatement.execute();
    ```
  #### UPDATE
    ``` SQL
    PreparedStatement updateStatement =  connection.prepareStatement("UPDATE ISSUES SET ISSUE_STATUS=? WHERE ISSUE_ID=?");
		updateStatement.setInt(1,7);
		updateStatement.setInt(2,1);
		updateStatement.execute();
    ```
  #### DELETE
    ```
    PreparedStatement deleteStatement = connection.prepareStatement("DELETE ISSUES WHERE ISSUE_ID=?");
		deleteStatement.setInt(1,1);
		deleteStatement.execute();
    ```
  #### SELECT and Print the records
    ```
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
    ```