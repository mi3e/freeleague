package ie.logn.league;

import ie.logn.dao.CompetitionDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Competition;
import ie.logn.data.Team;

import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
	
	public CompetitionServiceImpl(
			CompetitionDao competitionDao,
			TeamDao teamDao)
	{ 
		this.competitionDao = competitionDao;
		this.teamDao = teamDao;
	}

	@Override
	public void addCompetition(Competition competition) {
		competitionDao.saveCompetition(competition);
	}

	@Override
	public void deleteCompetition(long competitionId) {
		//Who is responsible for deleting the teams/fixtures etc.. Foreign keys?
		competitionDao.deleteCompetition(competitionId);
	}

	@Override
	public List<Team> getTeamsInCompetition(long competitionId) {
		return teamDao.getTeamsInCompetition(competitionId);
	}
	
	private final CompetitionDao competitionDao;
	private final TeamDao teamDao;

}
