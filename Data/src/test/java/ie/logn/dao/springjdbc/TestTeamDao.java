package ie.logn.dao.springjdbc;

import static org.junit.Assert.*;

import java.util.List;

import ie.logn.dao.CompetitionDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Competition;
import ie.logn.data.Team;
import ie.logn.utils.object.ReflectionUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ie.logn.DatabaseTest;
import ie.logn.LognLeaguesTestUtil;

public class TestTeamDao extends DatabaseTest {

	@Test
	public void testSaveGetTeam() {
		
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		Team teamFromDb = teamDao.getTeam(team.getId());
		
		assertTrue(ReflectionUtils.compareObjects(team, teamFromDb));
	}
	
	@Test
	public void testAddTeamToCompetition() {
		Competition competition = LognLeaguesTestUtil.createCompetition("Premier league");
		
		competitionDao.saveCompetition(competition);
		
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		teamDao.addTeamToCompetition(competition.getId(), team.getId());
		
		List<Team> teams = teamDao.getTeamsInCompetition(competition.getId());
		
		assertEquals(1, teams.size());
		
		assertTrue(ReflectionUtils.compareObjects(teams.get(0), team));
	}
	
	@Test
	public void testDeleteTeam()
	{
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		teamDao.deleteTeam(team.getId());
		
		assertNull(teamDao.getTeam(team.getId()));
	}
	
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private CompetitionDao competitionDao;

}
