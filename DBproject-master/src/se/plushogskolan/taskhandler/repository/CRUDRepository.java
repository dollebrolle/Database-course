package se.plushogskolan.taskhandler.repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;

public interface CRUDRepository<T> {

	void create(HashMap<String, String> paramMap) throws SQLException;

	List<T> read(HashMap<String, String> parameter) throws SQLException;

	List<T> read(HashMap<String, String> parameter, boolean and) throws SQLException;

	void update(HashMap<String, String> paramMap, String id) throws SQLException;

	void delete(String columnName, int id) throws SQLException;

}