package ie.logn.dao.springjdbc;

import ie.logn.dao.FixtureDao;
import ie.logn.data.Fixture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class FixtureJdbcDao extends SpringJdbcDao<Fixture> implements FixtureDao {
	
	public FixtureJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		super(dataSource, incrementer);
		this.insert.withTableName("FIXTURE").setGeneratedKeyName("ID");
	}

	@Override
	public Fixture getFixture(long id) {
		
		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);
		
		try {
			return jdbcTemplate.queryForObject(SQL_GET_FIXTURE, namedParameters, new FixtureRowMapper());
		} catch (EmptyResultDataAccessException ex)
		{
			return null;
		}
	}

	@Override
	public void saveFixture(Fixture fixture) {
		super.saveIdentityObject(fixture);
	}
	

	@Override
	public void deleteFixture(long fixtureId) {
		super.deleteObject(fixtureId);
	}

	@Override
	public void modifyFixture(Fixture fixture) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public List<Fixture> getFixturesInCompetition(long competitionId) {
		Map<String, Long> sqlParameters = Collections.singletonMap("COMPETITION", competitionId);
		
		return jdbcTemplate.query(SQL_GET_FIXTURE_IN_COMPETITION, sqlParameters, new FixtureRowMapper());
	}
	
	private class FixtureRowMapper implements RowMapper<Fixture>
	{

		@Override
		public Fixture mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Fixture(
					rs.getLong("ID"),
					rs.getLong("COMPETITION"),
					rs.getLong("HOME_TEAM"),
					rs.getLong("AWAY_TEAM"),
					rs.getDate("MATCH_DATE"));
		}
	}
	
	private static final String SQL_GET_FIXTURE = "select ID, COMPETITION, HOME_TEAM, AWAY_TEAM, MATCH_DATE from FIXTURE where ID = :ID";
	
	private static final String SQL_GET_FIXTURE_IN_COMPETITION = 
			"select ID, COMPETITION, HOME_TEAM, AWAY_TEAM, MATCH_DATE from FIXTURE where COMPETITION = :COMPETITION";

}
