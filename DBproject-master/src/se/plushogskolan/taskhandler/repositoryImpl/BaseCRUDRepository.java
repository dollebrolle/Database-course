package se.plushogskolan.taskhandler.repositoryImpl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.logger.Logger;
import se.plushogskolan.taskhandler.logger.LoggerImpl;
import se.plushogskolan.taskhandler.mysql.SQLQueries;
import se.plushogskolan.taskhandler.repository.CRUDRepository;

public abstract class BaseCRUDRepository<T> implements CRUDRepository<T> {

	private final PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator();
	protected final static String url = "jdbc:mysql://localhost:3306/ProjektUppgift?useSSL=false&user=root&password=Hejsan1";
	private Logger logger = new LoggerImpl();

	@Override
	public void create(HashMap<String, String> paramMap) throws SQLException {
		String message = new SQLQueries().create(preparedStatementCreator
				.createCreateStatement(DriverManager.getConnection(url), getTableName(), paramMap));
		logger.info(message);
	}

	@Override
	public List<T> read(HashMap<String, String> paramMap) throws SQLException {
		return read(paramMap, true);
	}

	@Override
	public List<T> read(HashMap<String, String> paramMap, boolean and) throws SQLException {
		return new SQLQueries().read(getMapper(), preparedStatementCreator
				.createSelectStatement(DriverManager.getConnection(url), getTableName(), paramMap, and));
	}

	@Override
	public void update(HashMap<String, String> paramMap, String id) throws SQLException {
		String message = new SQLQueries().update(preparedStatementCreator
				.createUpdateStatement(DriverManager.getConnection(url), getTableName(), paramMap, id));
		logger.info(message);
	}

	@Override
	public void delete(String columnName, int id) throws SQLException {
		String message = new SQLQueries().delete(
				preparedStatementCreator.createDeleteStatement(DriverManager.getConnection(url), getTableName(),columnName , id));
		logger.info(message);
	}

	protected abstract String getTableName();
	protected abstract ObjectMapper<T> getMapper();

}