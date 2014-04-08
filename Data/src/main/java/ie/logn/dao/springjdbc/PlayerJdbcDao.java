package ie.logn.dao.springjdbc;

import ie.logn.dao.PlayerDao;
import ie.logn.data.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class PlayerJdbcDao extends SpringJdbcDao<Player> implements PlayerDao {

	public PlayerJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		super(dataSource, incrementer);
		this.insert.withTableName("PLAYER").setGeneratedKeyName("ID");
	}
	
	@Override
	public Player getPlayer(long id) {
		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);
		
		try {
			return jdbcTemplate.queryForObject(SQL_GET_PLAYER, namedParameters, new PlayerRowMapper());
		} catch (EmptyResultDataAccessException ex)
		{
			return null;
		}
	}

	@Override
	public void savePlayer(Player player) {
		super.saveIdentityObject(player);
	}
	

	@Override
	public void deletePlayer(long playerId) {
		super.deleteObject(playerId);
		
	}

	@Override
	public void modifyPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	private class PlayerRowMapper implements RowMapper<Player>
	{

		@Override
		public Player mapRow(ResultSet rs, int arg1) throws SQLException {
			return new Player(
					rs.getLong("ID"),
					rs.getLong("TEAM"),
					rs.getString("FIRST_NAME"),
					rs.getString("LAST_NAME"),
					rs.getString("PHONE_NUMBER"));
		}
		
	}
	
	private String SQL_GET_PLAYER = "select ID, TEAM, FIRST_NAME, LAST_NAME, PHONE_NUMBER from PLAYER where ID = :ID";

}
