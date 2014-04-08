package ie.logn.team;

import ie.logn.data.Player;
import ie.logn.data.Team;

public interface TeamPlayerService {
	
	void saveTeam(Team team);
	
	Team getTeam(long teamId);
	
	void savePlayer(Player player);
	
	Player getPlayer(long playerId);

}
