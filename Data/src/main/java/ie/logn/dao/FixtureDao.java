package ie.logn.dao;

import java.util.List;

import ie.logn.data.Fixture;

public interface FixtureDao {

	Fixture getFixture(long fixtureId);
	
	void saveFixture(Fixture fixture);
	
	void deleteFixture(long fixtureId);
	
	void modifyFixture(Fixture fixture);
	
	List<Fixture> getFixturesInCompetition(long competitionId);
}
