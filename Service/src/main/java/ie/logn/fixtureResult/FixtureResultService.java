package ie.logn.fixtureResult;

import java.util.List;

import ie.logn.data.Fixture;
import ie.logn.data.Result;

public interface FixtureResultService {
	
	void saveFixture(Fixture fixture);
	
	Fixture getFixture(long fixtureId);
	
	void saveResult(Result result);
	
	Result getResult(long resultId);
	
	List<Fixture> getCompetitionFixtures(long competitionId);
	
	Result getResultForFixture(long fixtureId);
	
	List<Result> getCompetitionResults(long competitionId);
}
