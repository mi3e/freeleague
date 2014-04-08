package ie.logn.dao.springjdbc;

import static org.junit.Assert.*;
import ie.logn.DatabaseTest;
import ie.logn.LognLeaguesTestUtil;
import ie.logn.dao.PlayerDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Player;
import ie.logn.data.Team;
import ie.logn.utils.object.ReflectionUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestPlayerJdbcDao extends DatabaseTest {

	@Test
	public void testSaveGetPlayer() {
		Team team = LognLeaguesTestUtil.createTeam("SRFC");
		
		teamDao.saveTeam(team);
		
		Player player = LognLeaguesTestUtil.createPlayer(team.getId(), "Ronnie", "Wheelan");
		
		playerDao.savePlayer(player);
		
		Player playerFromDb = playerDao.getPlayer(player.getId());
		
		assertTrue(ReflectionUtils.compareObjects(player, playerFromDb));
	}
	
	@Test
	public void testDeletePlayer() {
		Team team = LognLeaguesTestUtil.createTeam("SRFC");
		
		teamDao.saveTeam(team);
		
		Player player = LognLeaguesTestUtil.createPlayer(team.getId(), "Ronnie", "Wheelan");
		
		playerDao.savePlayer(player);
		
		playerDao.deletePlayer(player.getId());
		
		assertNull(playerDao.getPlayer(player.getId()));
	}

	@Autowired
	private TeamDao teamDao;
	
	@Autowired
	private PlayerDao playerDao;
}
