package se.plushogskolan.taskhandler.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;

public class SQLQueries {
		
	public SQLQueries (){
		
	}

	public <T> List<T> read(ObjectMapper<T> mapper, PreparedStatement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery();
		List<T> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(mapper.mapObject(resultSet));
		}
		resultSet.close();
		return list;
	}

	public String update(PreparedStatement statement) {
		try {
			statement.executeUpdate();
			statement.close();
			return "Update done!";
		} catch (SQLException e) {
			return "Update failed! " + e;
		}
	}

	public String create(PreparedStatement statement) {
		try {
			statement.executeUpdate();
			statement.close();
			return "Object inserted!";
		} catch (SQLException e) {
			return "Error! " + e;
		}
	}

	public String delete(PreparedStatement statement) {
		try {
			statement.executeUpdate();
			statement.close();
			return "Successfully deleted!";
		} catch (SQLException e) {
			return "Something went wrong while trying to delete object!";
		}
	}
	
}