package se.plushogskolan.taskhandler.servicetest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.plushogskolan.taskhandler.assets.enums.IssueColumns;
import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.model.Issue;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.IssueRepository;
import se.plushogskolan.taskhandler.service.IssueService;
import se.plushogskolan.taskhandler.service.WorkItemService;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {
	
	@Mock
	WorkItemService workItemService;
	
	@Mock
	private IssueRepository issueRepository;
	
	@InjectMocks
	private IssueService issueService;

	@Test
	public void testCreateIssue() throws SQLException{
		when(workItemService.getWorkItemById(Matchers.anyInt()))
		.thenReturn(new WorkItem("testName", WorkItemStatus.DONE, 7));
		Issue issue = new Issue("TestReason",7);
		issueService.createIssue(issue);
		verify(issueRepository).create(issue.getVariableMap());
	}
	
	@Test
	public void testDeleteIssueByWorkItem() throws SQLException {
		int workItemId = 2;
		issueService.deleteIssueByWorkItemId(workItemId);
		verify(issueRepository).delete(IssueColumns.WORKITEMID.name(), workItemId);
	}
	
	
	@Test
	public void testUpdateIssue() throws SQLException{
		int issueId = 2;
		HashMap<IssueColumns, String> testMap = new HashMap<>();
		HashMap<String, String> testMap2 = new HashMap<>();
		
		testMap.put(IssueColumns.REASON, "YEYO");
		testMap.put(IssueColumns.WORKITEMID, ""+3);
		
		testMap.forEach((k, v) -> {
			testMap2.put(k.toString(), v);
		});
		issueService.updateIssue(testMap, issueId);
		
		issueRepository.update(testMap2, ""+issueId);
	}
	
	@Test
	public void testGetAllWorkItemByIssue() throws SQLException{
		HashMap<String, String> testMap = new HashMap<>();
		List<Issue> issueList = new ArrayList<>();
		issueList.add(new Issue("HERROW", 3));
		when(issueRepository.read(testMap)).thenReturn(issueList);
		when(workItemService.getWorkItemById(3)).thenReturn(new WorkItem("HEYO", WorkItemStatus.STARTED, 4));
		issueService.getAllWorkItemByIssue();
		verify(issueRepository).read(testMap);
	}
}
