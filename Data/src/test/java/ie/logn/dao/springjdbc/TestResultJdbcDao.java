package ie.logn.dao.springjdbc;

import static org.junit.Assert.*;

import java.util.Calendar;

import ie.logn.DatabaseTest;
import ie.logn.LognLeaguesTestUtil;
import ie.logn.dao.CompetitionDao;
import ie.logn.dao.FixtureDao;
import ie.logn.dao.ResultDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Competition;
import ie.logn.data.Fixture;
import ie.logn.data.Result;
import ie.logn.data.Team;
import ie.logn.utils.datetime.DateTimeUtils;
import ie.logn.utils.object.ReflectionUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestResultJdbcDao extends DatabaseTest{

	@Test
	public void testSaveGetResult() {
		
		Competition competition = LognLeaguesTestUtil.createCompetition("Premier League");
		
		competitionDao.saveCompetition(competition);
		
		Team liverpool = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(liverpool);
		
		Team manUtd = LognLeaguesTestUtil.createTeam("Man Utd");
		
		teamDao.saveTeam(manUtd);
		
		Fixture livVsManu = new Fixture(competition.getId(), liverpool.getId(), manUtd.getId(), 
				DateTimeUtils.trimTimeFromCalendar(Calendar.getInstance()).getTime());
		
		fixtureDao.saveFixture(livVsManu);
		
		Result result = new Result(livVsManu.getId(), "5", "0");
		
		resultDao.saveResult(result);
		
		Result resultFromDb = resultDao.getResult(result.getId());
		
		assertTrue(ReflectionUtils.compareObjects(result, resultFromDb));
	}
	
	@Test
	public void testDeleteResult()
	{
		Competition competition = LognLeaguesTestUtil.createCompetition("Premier League");
		
		competitionDao.saveCompetition(competition);
		
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		teamDao.saveTeam(team);
		
		Team team2 = LognLeaguesTestUtil.createTeam("Man Utd");
		
		teamDao.saveTeam(team2);
		
		Fixture livVsManu = new Fixture(competition.getId(), team.getId(), team2.getId(), 
				DateTimeUtils.trimTimeFromCalendar(Calendar.getInstance()).getTime());
		
		fixtureDao.saveFixture(livVsManu);
		
		Result result = new Result(livVsManu.getId(), "5", "0");
		
		resultDao.saveResult(result);
		
		resultDao.deleteResult(result.getId());
		
		assertNull(resultDao.getResult(result.getId()));		
	}
	
	@Autowired
	private CompetitionDao competitionDao;
	
	@Autowired
	private TeamDao teamDao;

	@Autowired
	private FixtureDao fixtureDao;
	
	@Autowired
	private ResultDao resultDao;
}