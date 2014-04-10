package ie.logn.dao.springjdbc;

import ie.logn.dao.CompetitionDao;
import ie.logn.data.Competition;
import ie.logn.data.CompetitionType;
import ie.logn.utils.enums.EnumUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class CompetitionJdbcDao extends SpringJdbcDao<Competition> implements
		CompetitionDao {

	public CompetitionJdbcDao(final DataSource dataSource,
			final DataFieldMaxValueIncrementer incrementer) {
		super(dataSource, incrementer);
		this.insert.withTableName("COMPETITION").setGeneratedKeyName("ID");
	}

	@Override
	public Competition getCompetition(long id) {

		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);

		try {
			return jdbcTemplate.queryForObject(SQL_GET_COMPETITION,
					namedParameters, new CompetitionRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void saveCompetition(Competition competition) {
		super.saveIdentityObject(competition);
	}

	@Override
	public void deleteCompetition(long competitionId) {
		super.deleteObject(competitionId);

	}

	private class CompetitionRowMapper implements RowMapper<Competition> {
		@Override
		public Competition mapRow(ResultSet rs, int arg1) throws SQLException {

			return new Competition(rs.getLong("ID"), rs.getString("NAME"),
					EnumUtils.getEnumFromString(CompetitionType.class,
							rs.getString("COMPETITION_TYPE")));
		}
	}

	private static final String SQL_GET_COMPETITION = "select ID, NAME, COMPETITION_TYPE from COMPETITION where ID = :ID";

}
