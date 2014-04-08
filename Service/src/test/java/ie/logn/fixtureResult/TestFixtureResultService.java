package ie.logn.fixtureResult;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ie.logn.DatabaseTest;

public class TestFixtureResultService extends DatabaseTest {

	@Before
	public void setUp() throws SQLException, FileNotFoundException {
		super.setUp();
	}

	@After
	public void tearDown() throws SQLException, FileNotFoundException {
		super.tearDown();
	}

	@Test
	public void testFixtureResultServiceImpl() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSaveFixture() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetFixture() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSaveResult() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetResult() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCompetitionFixtures() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetResultForFixture() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetCompetitionResults() {
		//fail("Not yet implemented");
	}

}
