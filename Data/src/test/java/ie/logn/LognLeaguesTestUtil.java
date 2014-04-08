package ie.logn;

import ie.logn.data.Competition;
import ie.logn.data.CompetitionType;
import ie.logn.data.Player;
import ie.logn.data.Team;

public class LognLeaguesTestUtil {
	
	public static Competition createCompetition(String name)
	{
		return new Competition(name, CompetitionType.league);
	}

	public static Team createTeam(String name) {
		
		return new Team(name);
	}
	
	public static Player createPlayer(long teamId, String firstName, String lastName) {
		return new Player(teamId, firstName, lastName, "0800123445");
	}

}
