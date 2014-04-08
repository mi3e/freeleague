package ie.logn.dao.springjdbc;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import ie.logn.DatabaseTest;
import ie.logn.LognLeaguesTestUtil;
import ie.logn.dao.CompetitionDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Competition;
import ie.logn.data.Team;
import ie.logn.utils.object.ReflectionUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCompetitionDao extends DatabaseTest {

	@Test
	public void testSaveGetCompetition() {
		
		Competition testComp = LognLeaguesTestUtil.createCompetition("dummy");
		
		competitionDao.saveCompetition(testComp);
		
		Competition testCompRet = competitionDao.getCompetition(testComp.getId());
		
		assertTrue(ReflectionUtils.compareObjects(testComp, testCompRet));
	}
	
	@Test
	public void testDeleteCompetition() {
		Competition testComp = LognLeaguesTestUtil.createCompetition("dummy");
		Team team = LognLeaguesTestUtil.createTeam("Liverpool");
		
		competitionDao.saveCompetition(testComp);
		
		competitionDao.deleteCompetition(testComp.getId());
		
		assertNull(competitionDao.getCompetition(testComp.getId()));
		
	}
	
	@Autowired
	private CompetitionDao competitionDao;
	
	@Autowired
	private TeamDao teamDao;

}
