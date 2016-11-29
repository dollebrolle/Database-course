package se.plushogskolan.taskhandler.servicetest;

import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import se.plushogskolan.taskhandler.assets.enums.TeamColumns;
import se.plushogskolan.taskhandler.assets.enums.UserColumns;
import se.plushogskolan.taskhandler.model.Team;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.TeamRepository;
import se.plushogskolan.taskhandler.service.TeamService;
import se.plushogskolan.taskhandler.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

	@Mock
	UserService userService;
	
	@Mock
	TeamRepository teamRepository;
	
	@InjectMocks
	TeamService teamService;
	
	@Test
	public void testCreateTeam() throws SQLException {
		Team team = new Team("bluteamxD", "1");
		teamService.createTeam(team);
		verify(teamRepository).create(team.getVariableMap());
	}
	
	@Test
	public void testSetTeamStatus() throws SQLException {
		boolean active = false;
		int id = 3;
		teamService.setTeamStatus(active, id);
		
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(TeamColumns.ISTEAMACTIVE.name(), "0");
		verify(teamRepository).update(testMap, ""+id);
	}
	
	@Test
	public void testUpdateTeam() throws SQLException {
		int id = 1;
		HashMap<String, String> testMap = new HashMap<>();
		HashMap<TeamColumns, String> testMap2 = new HashMap<>();

		testMap2.put(TeamColumns.TEAMNAME, "Testing");
		teamService.updateTeam(testMap2, id);
		
		testMap2.forEach((key, value) -> {
			testMap.put(key.name(), value);
		});
		verify(teamRepository).update(testMap, ""+id);
	}
	
	@Test
	public void testIsTeamActive() throws SQLException {
		int id = 1;
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(TeamColumns.ID.name(), ""+id);
		
		teamService.isTeamActive(""+id);
		
		verify(teamRepository).read(testMap);
	}
	
	@Test
	public void testGetAllTeams() throws SQLException {
		HashMap<String, String> testMap = new HashMap<>();

		teamService.getAllTeams();
		
		verify(teamRepository).read(testMap);
	}
	
	@Test
	public void testGetTeamSize() throws SQLException {
		int id = 1;

		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(TeamColumns.ID.name(), ""+id);

		teamService.getTeamSize(id);
		
		verify(userService).getAllUserInTeam(id);
	}
	
	@Test
	public void testChangeOrAddUserToTeam() throws SQLException {
		int teamId = 1;
		int userId = 2;
		ArrayList<User> list = new ArrayList<>();
		list.add(new User("I GOT A", "PEN", "I GOT AN APPLE", "APPLEPEN", "1", 0));
		Mockito.when(userService.getAllUserInTeam(teamId)).thenReturn(list);
		
		HashMap<UserColumns, String> testMap = new HashMap<>();
		testMap.put(UserColumns.TEAMID, ""+teamId);
		
		teamService.changeOrAddUserToTeam(userId, teamId);
		
		verify(userService).updateUser(testMap, userId);
		
	}
}