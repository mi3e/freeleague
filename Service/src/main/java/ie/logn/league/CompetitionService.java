package ie.logn.league;

import java.util.List;

import ie.logn.data.Competition;
import ie.logn.data.Team;

public interface CompetitionService {
	
	void addCompetition(Competition league);
	
	void deleteCompetition(long competitionId);
	
	List<Team> getTeamsInCompetition(long competitionId);
}
