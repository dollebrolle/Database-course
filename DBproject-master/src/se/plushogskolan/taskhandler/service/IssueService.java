package se.plushogskolan.taskhandler.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import se.plushogskolan.taskhandler.assets.enums.IssueColumns;
import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.model.Issue;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.IssueRepository;

public class IssueService {

	private WorkItemService workItemService;
	private IssueRepository issueRepository;
	private HashMap<String, String> paramMap;

	
	public IssueService(IssueRepository issueRepository, WorkItemService workItemService) {
		this.workItemService = workItemService;
		paramMap = new LinkedHashMap<>();
		this.issueRepository = issueRepository;

	}
	
	public void createIssue(Issue issue) throws SQLException {
		if(workItemService.getWorkItemById(issue.getWorkItemId()).getStatus().equals(WorkItemStatus.DONE)){
			workItemService.changeWorkItemStatus(WorkItemStatus.UNSTARTED, issue.getWorkItemId());
			issueRepository.create(issue.getVariableMap());
		}
	}
	
	public void deleteIssueByWorkItemId(int id) throws SQLException {
		issueRepository.delete(IssueColumns.WORKITEMID.name(), id);
	}


	public void updateIssue(HashMap<IssueColumns, String> issueMap, int id) throws SQLException {
		for(Entry<IssueColumns, String> entrySet : issueMap.entrySet()){
			paramMap.put(entrySet.getKey().name(), entrySet.getValue());
		}
		issueRepository.update(paramMap, ""+id);
	}
	
	public List<WorkItem> getAllWorkItemByIssue() throws SQLException{
		List<Issue> issueList = issueRepository.read(paramMap);		
		List<WorkItem> workItemList = new ArrayList<>();
		for(Issue issue : issueList){
			if(issue.getWorkItemId() > 0){
				WorkItem workItem = workItemService.getWorkItemById(issue.getId());
				workItemList.add(workItem);
			}			
		}
		return workItemList;
	}

}