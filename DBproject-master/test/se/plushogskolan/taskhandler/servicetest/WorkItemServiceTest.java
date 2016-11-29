package se.plushogskolan.taskhandler.servicetest;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import se.plushogskolan.taskhandler.assets.enums.WorkItemColumns;
import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.WorkitemRepository;
import se.plushogskolan.taskhandler.service.IssueService;
import se.plushogskolan.taskhandler.service.UserService;
import se.plushogskolan.taskhandler.service.WorkItemService;

@RunWith(MockitoJUnitRunner.class)
public class WorkItemServiceTest {
	
	@Mock
	IssueService issueService;
	
	@Mock
	UserService userService;
	
	@Mock 
	WorkitemRepository workItemRepository;
	
	@InjectMocks 
	WorkItemService workItemService;
	
	@Test 
	public void testCreateWorkItem() throws SQLException {
		WorkItem workItem = new WorkItem("HERE WE GO", WorkItemStatus.DONE, 1);
		workItemService.createWorkItem(workItem);
		
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.USERID.name(), "1");
		testMap.put(WorkItemColumns.STATUS.name(), WorkItemStatus.DONE.name());
		testMap.put(WorkItemColumns.NAME.name(), "HERE WE GO");
		
		verify(workItemRepository).create(testMap);
	}
	
	@Test
	public void testChangeWorkItemStatus() throws SQLException {
		workItemService.changeWorkItemStatus(WorkItemStatus.DONE, 1);
		
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.STATUS.name(), WorkItemStatus.DONE.name());
		
		verify(workItemRepository).update(testMap, "1");
	}
	
	@Test
	public void testChangeWorkItemStatusByUserId() throws SQLException {
		List<WorkItem> workItemList = new ArrayList<WorkItem>();
		workItemList.add(new WorkItem("KALLA", WorkItemStatus.STARTED, 1));
		Mockito.when(workItemService.getAllWorkItemByUser(anyInt())).thenReturn(workItemList);
	
		workItemService.changeWorkItemStatusByUserId(WorkItemStatus.UNSTARTED, 1);
		
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.STATUS.name(), WorkItemStatus.UNSTARTED.name());
		testMap.put(WorkItemColumns.USERID.name(), "1");
		
		verify(workItemRepository).update(testMap, "0");
	}
	
	@Test
	public void testGetWorkItemById() throws SQLException {
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.ID.name(), "1");
		
		workItemService.getWorkItemById(1);
		
		verify(workItemRepository).read(testMap);
	}
	
	@Test
	public void testGetWorkItemsByStatus() throws SQLException {
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.STATUS.name(), WorkItemStatus.DONE.name());
		
		workItemService.getWorkItemsByStatus(WorkItemStatus.DONE);
		
		verify(workItemRepository).read(testMap);
	}
	
	
	@Test
	public void testDeleteWorkItem() throws SQLException {
		workItemService.deleteWorkItem(1);
		verify(workItemRepository).delete(WorkItemColumns.ID.name(), 1);
	}
	
	
	@Test
	public void testGetWorkItemByTeam() throws SQLException {
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.USERID.name(), "1");
		
		ArrayList<User> userList = new ArrayList<User>();
		User user = new User("FIRSTNAME", "LASTNAME", "USERNAME", "PASSWORD", "1", 0);
		user.setId(1);
		userList.add(user);
		
		Mockito.when(userService.getAllUserInTeam(anyInt())).thenReturn(userList);
		workItemService.getWorkItemByTeam(1);
		
		verify(workItemRepository).read(testMap);
	}
	
	@Test
	public void testGetAllWorkItemByUser() throws SQLException {
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.USERID.name(), "1");
		
		workItemService.getAllWorkItemByUser(1);
		
		verify(workItemRepository).read(testMap);
	}
	
	@Test
	public void testAssignWorkItemToUser() throws SQLException {
		List<WorkItem> workItemList = new ArrayList<WorkItem>();
		workItemList.add(new WorkItem("TESTIARO", WorkItemStatus.DONE, 1));
		Mockito.when(workItemService.getAllWorkItemByUser(anyInt())).thenReturn(workItemList);
		Mockito.when(userService.isActive(anyInt())).thenReturn(true);
		
		workItemService.assignWorkItemToUser(1, 2);
		
		HashMap<String, String> testMap = new HashMap<>();
		testMap.put(WorkItemColumns.USERID.name(), "1");
		verify(workItemRepository).update(testMap, "2");
	}
	
	
}