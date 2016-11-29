package se.plushogskolan.taskhandler.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import se.plushogskolan.taskhandler.assets.enums.TeamColumns;
import se.plushogskolan.taskhandler.assets.enums.UserColumns;
import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.logger.Logger;
import se.plushogskolan.taskhandler.logger.LoggerImpl;
import se.plushogskolan.taskhandler.model.Team;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.TeamRepository;
import se.plushogskolan.taskhandler.repositoryImpl.UserRepositoryImpl;

public class TeamService {

	private TeamRepository teamRepository;
	private UserService userService;
	private Logger logger = new LoggerImpl();
	private HashMap<String, String> paramMap;

	public TeamService(TeamRepository teamRepository, UserService userService) {
		this.teamRepository = teamRepository;
		this.userService = userService;
		paramMap = new LinkedHashMap<>();
	}
	
	public void createTeam(Team team) throws SQLException {
		paramMap = team.getVariableMap();
		teamRepository.create(paramMap);
	}
	
	public void setTeamStatus(boolean status, int id) throws SQLException{
		String statusString = status == true ? "1" : "0";
		paramMap.put(TeamColumns.ISTEAMACTIVE.name(), statusString);
		teamRepository.update(paramMap, ""+id);
	}

	public void updateTeam(HashMap<TeamColumns, String> teamMap, int id) throws SQLException {
		for(Entry<TeamColumns, String> entrySet : teamMap.entrySet()){
			paramMap.put(entrySet.getKey().name(), entrySet.getValue());
		}
		teamRepository.update(paramMap, "" + id);
	}

	public boolean isTeamActive(String id) throws SQLException {
		paramMap.put(TeamColumns.ID.name(), id);
		List<Team> teamList = teamRepository.read(paramMap);
		if (teamList.size() == 0) {
			return false;
		}
		return teamList.get(0).isTeamActive();
	}

	public List<Team> getAllTeams() throws SQLException {
		return teamRepository.read(paramMap);
	}

	public int getTeamSize(int teamId) throws SQLException {
		List<User> teamList = userService.getAllUserInTeam(teamId);
		return teamList.size();
	}

	public void changeOrAddUserToTeam(int userId, int teamId) throws SQLException {
		if (getTeamSize(teamId) < 10) {
			HashMap<UserColumns, String> userMap = new HashMap<>();
			userMap.put(UserColumns.TEAMID, ""+teamId);
			userService.updateUser(userMap, userId);
		} else {
			logger.info("Team is full!");
		}
	}

}