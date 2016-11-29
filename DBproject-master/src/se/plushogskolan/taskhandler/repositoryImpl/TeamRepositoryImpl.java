package se.plushogskolan.taskhandler.repositoryImpl;

import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.model.Team;
import se.plushogskolan.taskhandler.repository.TeamRepository;

public class TeamRepositoryImpl extends BaseCRUDRepository<Team> implements TeamRepository {

	private ObjectMapper<Team> TEAM_MAPPER = (t) -> {
		Team team = new Team(t.getString("teamName"), t.getString("isTeamActive"));
		team.setId(Integer.parseInt(t.getString("id")));
		return team;
	};
	
	@Override
	protected String getTableName() {
		return "Team";
	}

	@Override
	protected ObjectMapper<Team> getMapper() {
		return TEAM_MAPPER;
	}

}