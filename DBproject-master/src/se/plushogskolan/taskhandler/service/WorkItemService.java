package se.plushogskolan.taskhandler.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.plushogskolan.taskhandler.assets.enums.WorkItemColumns;
import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.WorkitemRepository;
import se.plushogskolan.taskhandler.repositoryImpl.IssueRepositoryImpl;
import se.plushogskolan.taskhandler.repositoryImpl.UserRepositoryImpl;

public class WorkItemService {

	private WorkitemRepository workItemRepository;
	private IssueService issueService;
	private UserService userService;

	private HashMap<String, String> paramMap;

	public WorkItemService(WorkitemRepository workItemRepository, IssueService issueService, UserService userService) {
		this.workItemRepository = workItemRepository;
		this.issueService = issueService;
		this.userService = userService;
		paramMap = new HashMap<>();
	}

	public void createWorkItem(WorkItem workItem) throws SQLException {
		workItemRepository.create(workItem.getVariableMap());
	}

	public void changeWorkItemStatus(WorkItemStatus workItemStatus, int id) throws SQLException {
		paramMap.put(WorkItemColumns.STATUS.name(), workItemStatus.name());
		workItemRepository.update(paramMap, "" + id);
	}

	public void changeWorkItemStatusByUserId(WorkItemStatus workItemStatus, int userId) throws SQLException {
		List<WorkItem> itemList = getAllWorkItemByUser(userId);
		for (WorkItem workItem : itemList) {
			paramMap.put(WorkItemColumns.STATUS.name(), workItemStatus.name());
			workItemRepository.update(paramMap, "" + workItem.getId());
		}
	}

	public WorkItem getWorkItemById(int id) throws SQLException {
		paramMap.put(WorkItemColumns.ID.name(), "" + id);
		List<WorkItem> workItemList = workItemRepository.read(paramMap);
		if (workItemList.size() != 0) {
			return workItemList.get(0);
		} else {
			return null;
		}
	}

	public List<WorkItem> getWorkItemsByStatus(WorkItemStatus workItemStatus) throws SQLException {
		paramMap.put(WorkItemColumns.STATUS.name(), workItemStatus.name());
		return workItemRepository.read(paramMap);
	}

	public void deleteWorkItem(int id) throws SQLException {
		issueService.deleteIssueByWorkItemId(id);
		workItemRepository.delete(WorkItemColumns.ID.name(), id);
	}

	public List<WorkItem> getWorkItemByTeam(int teamId) throws SQLException {
		List<User> userList = userService.getAllUserInTeam(teamId);
		List<WorkItem> workItemList = new ArrayList<>();
		List<WorkItem> finalWorkItemList = new ArrayList<>();
		for (User user : userList) {
			paramMap.put(WorkItemColumns.USERID.name(), "" + user.getId());
			workItemList = workItemRepository.read(paramMap);
			for (WorkItem workItem : workItemList) {
				finalWorkItemList.add(workItem);
			}
		}
		return finalWorkItemList;
	}

	public List<WorkItem> getAllWorkItemByUser(int userId) throws SQLException {
		paramMap.put(WorkItemColumns.USERID.name(), "" + userId);
		return workItemRepository.read(paramMap);
	}

	public void assignWorkItemToUser(int userId, int workItemId) throws SQLException {
		List<WorkItem> itemList = getAllWorkItemByUser(userId);
		if (userService.isActive(userId) && itemList.size() < 5) {
			paramMap.put(WorkItemColumns.USERID.name(), "" + userId);
			workItemRepository.update(paramMap, "" + workItemId);
		}
	}
}