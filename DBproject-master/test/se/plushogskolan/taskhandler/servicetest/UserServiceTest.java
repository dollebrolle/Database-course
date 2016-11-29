package se.plushogskolan.taskhandler.servicetest;

import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.plushogskolan.taskhandler.assets.enums.UserColumns;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.UserRepository;
import se.plushogskolan.taskhandler.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
	
	@Test
	public void testGetUserByAnyVariabeMethod() throws SQLException {
		HashMap<UserColumns, String> testMap = new HashMap<>();
		HashMap<String, String> testMap2 = new HashMap<>();
		testMap.put(UserColumns.FIRSTNAME, "Navid");
		testMap.put(UserColumns.LASTNAME, "Nayeri");
		testMap.put(UserColumns.USERNAME, "Jurassicpark");

		userService.getUserByAnyVariable(testMap);

		testMap.forEach((k, v) -> {
			testMap2.put(k.name(), v);
		});

		verify(userRepository).read(testMap2, false);
	}

	@Test
	public void testGetUserByVariablesMethod() throws SQLException {
		HashMap<UserColumns, String> testMap = new HashMap<>();
		HashMap<String, String> testMap2 = new HashMap<>();
		testMap.put(UserColumns.FIRSTNAME, "Navid");
		testMap.put(UserColumns.LASTNAME, "Nayeri");
		testMap.put(UserColumns.USERNAME, "Jurassicpark");

		userService.getUserByVariables(testMap);

		testMap.forEach((k, v) -> {
			testMap2.put(k.name(), v);
		});
		verify(userRepository).read(testMap2);
	}

	@Test
	public void testCreateUser() throws SQLException {
		User user = new User("Navid", "Nayeri", "K1ll3rsl4y3r0fd00m", "lol123", "1", 5);
		userService.createUser(user);
		
		verify(userRepository).create(user.getVariableMap());
	}
	
	@Test
	public void testUpdateUser() throws SQLException{
		HashMap<UserColumns, String> testMap = new HashMap<>();
		HashMap<String, String> testMap2 = new HashMap<>();
		testMap.put(UserColumns.FIRSTNAME, "Navid");
		testMap.put(UserColumns.LASTNAME, "Nayeri");
		testMap.put(UserColumns.USERNAME, "Jurassicpark");
		userService.updateUser(testMap, 8);
		
		testMap.forEach((k, v) -> {
			testMap2.put(k.name(), v);
		});
		
		verify(userRepository).update(testMap2, ""+8);
	}
	
	@Test
	public void testSetUserStatus() throws SQLException{
		int userId = 6;
		HashMap<String, String> testMap = new HashMap<>();
		userService.setUserStatus(true, userId);
		testMap.put(UserColumns.ISACTIVE.name(), "1");
		verify(userRepository).update(testMap, ""+userId);
	}
	
	@Test
	public void testIsActive() throws SQLException{
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(UserColumns.ID.name(), "1");
		userService.isActive(1);
		verify(userRepository).read(testMap);
	}
	
	@Test
	public void testGetUserById() throws SQLException{
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(UserColumns.ID.name(), "1");
		userService.getUserById(1);
		verify(userRepository).read(testMap);
	}
	
	@Test
	public void testGetAllUserInTeam() throws SQLException{
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(UserColumns.TEAMID.name(), "1");
		userService.getAllUserInTeam(1);
		verify(userRepository).read(testMap);
	}
	

}
