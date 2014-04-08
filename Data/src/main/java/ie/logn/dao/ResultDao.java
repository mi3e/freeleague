package ie.logn.dao;

import ie.logn.data.Result;

public interface ResultDao {
	
	void saveResult(Result result);
	
	Result getResult(long resultId);
	
	void deleteResult(long resultId);
	
	void modifyResult(Result result);
	
	Result getResultForFixture(long fixtureId);

}
