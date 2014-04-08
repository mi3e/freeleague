package ie.logn.dao;

import ie.logn.data.Competition;

public interface CompetitionDao {
	
	Competition getCompetition(long competitionId);
	
	void saveCompetition(Competition competition);
	
	void deleteCompetition(long competitionId);

}
