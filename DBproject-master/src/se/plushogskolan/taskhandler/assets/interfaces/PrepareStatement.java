package se.plushogskolan.taskhandler.assets.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public interface PrepareStatement {

	PreparedStatement createDeleteStatement(Connection connection, String tableName, String columnName, int id)
			throws SQLException;

	PreparedStatement createUpdateStatement(Connection connection, String tableName, HashMap<String, String> paramMap, String id)
			throws SQLException;

	PreparedStatement createCreateStatement(Connection connection, String tableName, HashMap<String, String> paramMap)
			throws SQLException;

	PreparedStatement createSelectStatement(Connection connection, String tableName, HashMap<String, String> paramMap, boolean and)
			throws SQLException;

}