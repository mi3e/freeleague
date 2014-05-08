package ie.logn.dao.springjdbc;

import ie.logn.dao.ResultDao;
import ie.logn.data.Result;
import ie.logn.data.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class ResultJdbcDao extends SpringJdbcDao<Result> implements ResultDao {

	public ResultJdbcDao(final DataSource dataSource,
			final DataFieldMaxValueIncrementer incrementer) {
		super(dataSource, incrementer);
		this.insert.withTableName("RESULT").setGeneratedKeyName("ID");
	}

	@Override
	public void saveResult(Result result) {
		// Complex type in the score object so not using the base method until I
		// can figure a workaround
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fixture", result.getFixture());
		args.put("home_goals", result.getHomeScore().getPrimaryScore());
		args.put("home_points", result.getHomeScore().getSecondaryScore());
		args.put("away_goals", result.getAwayScore().getPrimaryScore());
		args.put("away_points", result.getAwayScore().getSecondaryScore());

		Number key = insert.executeAndReturnKey(args);

		result.setId(key.longValue());

	}

	@Override
	public Result getResult(long id) {
		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);

		try {
			return jdbcTemplate.queryForObject(SQL_GET_RESULT, namedParameters,
					new ResultRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void deleteResult(long resultId) {
		super.deleteObject(resultId);

	}

	@Override
	public void modifyResult(Result result) {
		// TODO Auto-generated method stub
	}

	@Override
	public Result getResultForFixture(long fixtureId) {
		Map<String, Long> sqlParameters = Collections.singletonMap("FIXTURE",
				fixtureId);

		return jdbcTemplate.queryForObject(SQL_GET_RESULT_FOR_FIXTURE,
				sqlParameters, new ResultRowMapper());
	}

	private class ResultRowMapper implements RowMapper<Result> {

		@Override
		public Result mapRow(ResultSet rs, int arg1) throws SQLException {
			return new Result(rs.getLong("ID"), rs.getLong("FIXTURE"),
					new Score(rs.getLong("HOME_PRIMARY_SCORE"),
							rs.getLong("HOME_SECONDARY_SCORE")), new Score(
							rs.getLong("AWAY_PRIMARY_SCORE"),
							rs.getLong("AWAY_SECONDARY_SCORE")));
		}
	}

	private static final String SQL_GET_RESULT = "select ID, FIXTURE, HOME_PRIMARY_SCORE, HOME_SECONDARY_SCORE, AWAY_PRIMARY_SCORE, AWAY_SECONDARY_SCORE from RESULT where ID = :ID";

	private static final String SQL_GET_RESULT_FOR_FIXTURE = "select ID, FIXTURE, HOME_PRIMARY_SCORE, HOME_SECONDARY_SCORE, AWAY_PRIMARY_SCORE, AWAY_SECONDARY_SCORE from RESULT where FIXTURE = :FIXTURE";
}
