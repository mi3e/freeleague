package ie.logn.dao.springjdbc;

import ie.logn.dao.TeamDao;
import ie.logn.data.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class TeamJdbcDao extends SpringJdbcDao<Team> implements TeamDao {
	
	public TeamJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		super(dataSource, incrementer);
		this.insert.withTableName("TEAM").setGeneratedKeyName("ID");
	}

	@Override
	public Team getTeam(long id) {
		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);
		
		try{
			return jdbcTemplate.queryForObject(SQL_GET_TEAM, namedParameters, new TeamRowMapper());
		} catch (EmptyResultDataAccessException ex)
		{
			return null;
		}
	}

	@Override
	public void saveTeam(Team team) {
		super.saveIdentityObject(team);
	}
	
	@Override
	public void addTeamToCompetition(long competitionId, long teamId) {
		Map<String, Long> sqlParameters = new HashMap<String, Long>();
		
		sqlParameters.put("COMPETITION",competitionId);
		sqlParameters.put("TEAM", teamId);
		
		jdbcTemplate.update(SQL_INSERT_COMPETITION_TEAM_MAPPING, sqlParameters);
	}
	
	@Override
	public List<Team> getTeamsInCompetition(long competitionId) {
		Map<String, Long> namedParameters = Collections.singletonMap("COMPETITION", competitionId);
		
		return jdbcTemplate.query(SQL_GET_TEAMS_IN_COMPETITION, namedParameters, new TeamRowMapper());
	}
	
	@Override
	public void deleteTeam(long teamId) {
		super.deleteObject(teamId);
		
	}
	
	private class TeamRowMapper implements RowMapper<Team>
	{
		@Override
		public Team mapRow(ResultSet rs, int arg1) throws SQLException {
			return new Team(
					rs.getLong("ID"),
					rs.getString("NAME") );
		}
	}
	
	private static final String SQL_INSERT_COMPETITION_TEAM_MAPPING = "insert into COMPETITION_TEAMS (COMPETITION, TEAM) VALUES (:COMPETITION, :TEAM)";
	private static final String SQL_GET_TEAM = "select ID, NAME from TEAM where ID = :ID";
	private static final String SQL_GET_TEAMS_IN_COMPETITION = 
			"select ID, NAME from TEAM T, COMPETITION_TEAMS CT where T.ID = CT.TEAM and CT.COMPETITION = :COMPETITION";

}
