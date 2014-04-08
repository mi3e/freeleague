package ie.logn.dao;

import ie.logn.data.Team;

import java.util.List;

public interface TeamDao {
	
	Team getTeam(long teamId);
	
	void saveTeam(Team team);
	
	void deleteTeam(long teamId);
	
	void addTeamToCompetition(long competitionId, long teamId);
	
	List<Team> getTeamsInCompetition(long competitionId);
}
