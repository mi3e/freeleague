package ie.logn.fixtureResult;

import ie.logn.dao.FixtureDao;
import ie.logn.dao.ResultDao;
import ie.logn.data.Fixture;
import ie.logn.data.Result;

import java.util.List;

public class FixtureResultServiceImpl implements FixtureResultService {
	
	public FixtureResultServiceImpl(final FixtureDao fixtureDao, final ResultDao resultDao)
	{
		this.fixtureDao = fixtureDao;
		this.resultDao = resultDao;
	}

	@Override
	public void saveFixture(Fixture fixture) {
		fixtureDao.saveFixture(fixture);
	}

	@Override
	public Fixture getFixture(long fixtureId) {
		return fixtureDao.getFixture(fixtureId);
	}

	@Override
	public void saveResult(Result result) {
		resultDao.saveResult(result);
	}

	@Override
	public Result getResult(long resultId) {
		return resultDao.getResult(resultId);
	}

	@Override
	public List<Fixture> getCompetitionFixtures(long competitionId) {
		return fixtureDao.getFixturesInCompetition(competitionId);
	}

	@Override
	public Result getResultForFixture(long fixtureId) {
		return resultDao.getResultForFixture(fixtureId);
	}

	@Override
	public List<Result> getCompetitionResults(long competitionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final FixtureDao fixtureDao;
	private final ResultDao resultDao;

}
