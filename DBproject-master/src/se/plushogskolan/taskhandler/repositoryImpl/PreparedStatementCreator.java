package se.plushogskolan.taskhandler.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import se.plushogskolan.taskhandler.assets.StringConverter;
import se.plushogskolan.taskhandler.assets.enums.QueryType;
import se.plushogskolan.taskhandler.assets.interfaces.PrepareStatement;

public class PreparedStatementCreator implements PrepareStatement {

	@Override
	public PreparedStatement createUpdateStatement(Connection connection, String tableName,
			HashMap<String, String> paramMap, String id) throws SQLException {
		PreparedStatement statement;
		String query = "";	
		
		query = "UPDATE " + tableName + " SET ";
		query = new StringConverter().statementStringBuilder(query, QueryType.UPDATE, paramMap);
		statement = connection.prepareStatement(query);

		int counter = 1;
		for (Entry<String, String> temp : paramMap.entrySet()) {
			statement.setObject(counter, temp.getValue());
			counter++;
		}
		
		statement.setObject(counter, id);
		return statement;
	}

	@Override
	public PreparedStatement createCreateStatement(Connection connection, String tableName,
			HashMap<String, String> paramMap) throws SQLException {
		String query = "INSERT INTO " + tableName + " VALUES ";
		query = new StringConverter().statementStringBuilder(query, QueryType.INSERT, paramMap);
		PreparedStatement statement = connection.prepareStatement(query);
		int counter = 1;
		for (String temp : paramMap.values()) {
			statement.setObject(counter++, temp);
		}
		return statement;
	}

	@Override
	public PreparedStatement createDeleteStatement(Connection connection, String tableName, String columnName,
			int id) throws SQLException {
		String query = "DELETE FROM " + tableName + " WHERE %s = ?";
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put(columnName, ""+id);
		query = new StringConverter().statementStringBuilder(query, QueryType.DELETE, paramMap);
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setObject(1, id);
		return statement;
	}

	@Override
	public PreparedStatement createSelectStatement(Connection connection, String tableName,
			HashMap<String, String> paramMap, boolean and) throws SQLException {
		String query = "SELECT * FROM " + tableName;
		if(paramMap.size() != 0){
			query = new StringConverter().statementStringBuilder(query, and, QueryType.SELECT, paramMap);
		}
		PreparedStatement statement = connection.prepareStatement(query);
		int counter = 1;
		for (Entry<String, String> temp : paramMap.entrySet()) {
			statement.setObject(counter++, temp.getValue());
		}
		return statement;
	}

//	public PreparedStatement createSelectStatement(Connection connection, String tableName) throws SQLException {
//		return createSelectStatement(connection, tableName, null, true);
//	}
}