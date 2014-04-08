package ie.logn.team;

import ie.logn.dao.PlayerDao;
import ie.logn.dao.TeamDao;
import ie.logn.data.Player;
import ie.logn.data.Team;

public class TeamPlayerServiceImpl implements TeamPlayerService {
	
	public TeamPlayerServiceImpl(final TeamDao teamDao, final PlayerDao playerDao)
	{
		_teamDao = teamDao;
		_playerDao = playerDao;
	}

	@Override
	public void saveTeam(Team team) {
		_teamDao.saveTeam(team);

	}

	@Override
	public Team getTeam(long teamId) {
		return _teamDao.getTeam(teamId);
	}

	@Override
	public void savePlayer(Player player) {
		_playerDao.savePlayer(player);
	}

	@Override
	public Player getPlayer(long playerId) {
		return _playerDao.getPlayer(playerId);
	}
	
	private final TeamDao _teamDao;
	private final PlayerDao _playerDao;

}
