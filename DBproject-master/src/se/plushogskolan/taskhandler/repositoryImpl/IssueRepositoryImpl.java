package se.plushogskolan.taskhandler.repositoryImpl;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.model.Issue;
import se.plushogskolan.taskhandler.repository.IssueRepository;

public class IssueRepositoryImpl extends BaseCRUDRepository<Issue> implements IssueRepository {
	
	private ObjectMapper<Issue> ISSUE_MAPPER = (i) -> {
		Issue issue = new Issue(i.getString("reason"), (Integer.parseInt(i.getString("workitemid"))));
		issue.setId(Integer.parseInt(i.getString("id")));
		return issue;
	};
	
	@Override
	protected String getTableName() {
		return "Issue";
	}

	@Override
	protected ObjectMapper<Issue> getMapper() {

		return ISSUE_MAPPER;
	}

}