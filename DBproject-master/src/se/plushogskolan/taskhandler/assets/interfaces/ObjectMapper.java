package se.plushogskolan.taskhandler.assets.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper<T> {

	T mapObject(ResultSet resultSet) throws SQLException;

}