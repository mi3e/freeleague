package ie.logn;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {
		"file:../Web/src/main/webapp/WEB-INF/datasource.xml",
		"file:../Web/src/main/webapp/WEB-INF/data.xml",
		"file:../Web/src/test/webapp/WEB-INF/test-beans.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class DatabaseTest {

	@Autowired
	protected DataSource dataSource;

	@Before
	public void setUp() throws SQLException, FileNotFoundException {
		Connection connection = dataSource.getConnection();
		LognDatabaseUtil.createTables(connection);
		connection.close();
	}

	@After
	public void tearDown() throws SQLException, FileNotFoundException {
		LognDatabaseUtil.doTearDown(dataSource);
	}
}
