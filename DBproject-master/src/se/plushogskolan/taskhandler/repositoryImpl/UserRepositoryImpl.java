package se.plushogskolan.taskhandler.repositoryImpl;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.UserRepository;

public class UserRepositoryImpl extends BaseCRUDRepository<User> implements UserRepository {
	
	private ObjectMapper<User> USER_MAPPER = (u) -> {
		User user = new User(u.getString("firstName"), u.getString("lastName"), u.getString("userName"),
				u.getString("password"), u.getString("isActive"), Integer.parseInt(u.getString("teamid")));
		user.setId(Integer.parseInt(u.getString("id")));
		return user;
	};

	@Override
	protected String getTableName() {
		return "User";
	}

	@Override
	protected ObjectMapper<User> getMapper() {
		return USER_MAPPER;
	}
	
}