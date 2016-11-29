package se.plushogskolan.taskhandler;

import java.sql.SQLException;

import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repositoryImpl.IssueRepositoryImpl;
import se.plushogskolan.taskhandler.repositoryImpl.UserRepositoryImpl;
import se.plushogskolan.taskhandler.repositoryImpl.WorkItemRepositoryImpl;
import se.plushogskolan.taskhandler.service.IssueService;
import se.plushogskolan.taskhandler.service.TeamService;
import se.plushogskolan.taskhandler.service.UserService;
import se.plushogskolan.taskhandler.service.WorkItemService;

public final class Main {
	
	private static UserService userService;
	private static TeamService teamService;
	private static WorkItemService workItemService;
	private static IssueService issueService;

	
	public static void main(String[] args) throws SQLException {
//		HashMap<SearchUser, String> userMap = new MapBuilder<SearchUser>().append(SearchUser.FIRSTNAME, "Ronald")
//												.append(SearchUser.LASTNAME, "Berry").build();
//		List<User> userList = new UserService(new UserRepositoryImpl()).getAllUserInTeam(2);
//		User user = new UserService(new UserRepositoryImpl()).getUserById(3);
//		System.err.println(userList.size());
//		teamService = new TeamService(new TeamRepositoryImpl());
//		HashMap<SearchTeam, String> teamMap = new MapBuilder<SearchTeam>().append(SearchTeam.TEAMNAME, "DARKBLUE").build();
//		new TeamService(new TeamRepositoryImpl()).changeOrAddUserToTeam(1, 3);
//		
	
//		MapBuilder<SearchIssue> mapBuilder = new MapBuilder<>();
//		HashMap<SearchIssue, String> issueMap = mapBuilder.append(SearchIssue.REASON, "KNARKARE").build();
//		issueService = new IssueService(new IssueRepositoryImpl());
//		List<WorkItem> issueList = issueService.getAllWorkItemByIssue();
//		System.out.println(issueList.size());
//		HashMap<SearchUser, String> userMap = new MapBuilder<SearchUser>()
//				.append(SearchUser.FIRSTNAME, "Karl")
//				.append(SearchUser.LASTNAME, "karlsson").build();
//		new UserService(new UserRepositoryImpl()).updateUser(userMap, 7);
//		
		
	}
}
