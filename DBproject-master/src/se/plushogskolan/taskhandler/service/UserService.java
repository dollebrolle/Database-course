package se.plushogskolan.taskhandler.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import se.plushogskolan.taskhandler.assets.enums.UserColumns;
import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.logger.Logger;
import se.plushogskolan.taskhandler.logger.LoggerImpl;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.UserRepository;

public class UserService {

	private UserRepository userRepository;
	private WorkItemService workItemService;
	private LinkedHashMap<String, String> paramMap;
	private Logger logger = new LoggerImpl();

	public UserService(UserRepository userRepository, WorkItemService workItemService) {
		paramMap = new LinkedHashMap<>();
		this.workItemService = workItemService;
		this.userRepository = userRepository;
	}
	public UserService(UserRepository userRepository) {
		paramMap = new LinkedHashMap<>();
		this.userRepository = userRepository;
	}
	

	public List<User> getUserByAnyVariable(HashMap<UserColumns, String> variableMap) throws SQLException {
		for (Entry<UserColumns, String> entrySet : variableMap.entrySet()) {
			paramMap.put(entrySet.getKey().name(), entrySet.getValue());
		}
		return userRepository.read(paramMap, false);
	}

	public List<User> getUserByVariables(HashMap<UserColumns, String> variableMap) throws SQLException {
		for (Entry<UserColumns, String> entrySet : variableMap.entrySet()) {
			paramMap.put(entrySet.getKey().name(), entrySet.getValue());
		}
		return userRepository.read(paramMap);
	}

	public void createUser(User user) throws SQLException {
		if (user.getUserName().length() < 10) {
			logger.info("Username too short!");
			return;
		}
		paramMap = user.getVariableMap();
		userRepository.create(paramMap);
	}

	public void updateUser(HashMap<UserColumns, String> variableMap, int id) throws SQLException {
		if (variableMap.keySet().contains(UserColumns.ID)) {
			logger.info("Can not update ID!");
			return;
		}
		for (Entry<UserColumns, String> entrySet : variableMap.entrySet()) {
			paramMap.put(entrySet.getKey().name(), entrySet.getValue());
		}
		userRepository.update(paramMap, "" + id);
	}

	public void setUserStatus(boolean status, int id) throws SQLException {
		if (!status) {
			workItemService.changeWorkItemStatusByUserId(WorkItemStatus.UNSTARTED, id);
		}
		String statusString = status == true ? "1" : "0";
		paramMap.put(UserColumns.ISACTIVE.name(), statusString);
		userRepository.update(paramMap, "" + id);
	}
	
	public boolean isActive(int id) throws SQLException {
		paramMap.put(UserColumns.ID.name(), ""+id);
		List<User> userList = userRepository.read(paramMap);
		if (userList.size() == 0) {
			return false;
		}
		return userList.get(0).isActive();
	}

	public User getUserById(int id) throws SQLException {
		paramMap.put(UserColumns.ID.name(), "" + id);
		List<User> userList = userRepository.read(paramMap);
		if(userList.size() != 0){
		return userRepository.read(paramMap).get(0);
		}
		return null;
	}


	public List<User> getAllUserInTeam(int teamid) throws SQLException {
		paramMap.put(UserColumns.TEAMID.name(), "" + teamid);
		return userRepository.read(paramMap);
	}

}