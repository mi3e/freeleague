package ie.logn.dao.springjdbc;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import ie.logn.DatabaseTest;
import ie.logn.LognLeaguesTestUtil;
import ie.logn.dao.CompetitionDao;
import ie.logn.dao.FixtureDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Competition;
import ie.logn.data.Fixture;
import ie.logn.data.Team;
import ie.logn.utils.datetime.DateTimeUtils;
import ie.logn.utils.object.ReflectionUtils;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestFixtureJdbcDao extends DatabaseTest {

	@Test
	public void testSaveGetFixture() {
		
		Competition competition = LognLeaguesTestUtil.createCompetition("Premier League");
		
		competitionDao.saveCompetition(competition);
		
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		Team team2 = LognLeaguesTestUtil.createTeam("'Man utd");
		
		teamDao.saveTeam(team2);
		
		Fixture livVsManu = new Fixture(competition.getId(), team.getId(), team2.getId(), 
				DateTimeUtils.trimTimeFromCalendar(Calendar.getInstance()).getTime());
		
		fixtureDao.saveFixture(livVsManu);
		
		Fixture livManu2 = fixtureDao.getFixture(livVsManu.getId());
		
		assertTrue(ReflectionUtils.compareObjects(livManu2, livVsManu));
	}
	
	@Test
	public void testDeleteFixture()
	{
		Competition competition = LognLeaguesTestUtil.createCompetition("Premier League");
		
		competitionDao.saveCompetition(competition);
		
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		Team team2 = LognLeaguesTestUtil.createTeam("'Man utd");
		
		teamDao.saveTeam(team2);
		
		Fixture livVsManu = new Fixture(competition.getId(), team.getId(), team2.getId(), 
				DateTimeUtils.trimTimeFromCalendar(Calendar.getInstance()).getTime());
		
		fixtureDao.saveFixture(livVsManu);
		
		fixtureDao.deleteFixture(livVsManu.getId());
		
		assertNull(fixtureDao.getFixture(livVsManu.getId()));
	}
	
	@Autowired
	private CompetitionDao competitionDao;
	
	@Autowired
	private TeamDao teamDao;

	@Autowired
	private FixtureDao fixtureDao;
}
