package ie.logn.dao;

import ie.logn.data.Player;

public interface PlayerDao {
	
	Player getPlayer(long playerId);
	
	void savePlayer(Player player);
	
	void deletePlayer(long playerId);
	
	void modifyPlayer(Player player);

}
