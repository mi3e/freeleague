package ie.logn.dao.springjdbc;

import java.util.Collections;
import java.util.Map;

import ie.logn.data.IdAndType;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class SpringJdbcDao<T extends IdAndType<T>> {
	
	public SpringJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.insert = new SimpleJdbcInsert(dataSource);
		this.incrementer = incrementer;
	}
	
	protected void saveObject(T t)
	{
		long nextId = incrementer.nextLongValue();
		
		t.setId(nextId);
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(t);
		
        insert.execute(parameters);
	}
	
	protected void deleteObject(long id)
	{
		String sql = "delete from " + insert.getTableName() + " where id = :ID";
		
		Map<String, Long> sqlParameters = Collections.singletonMap("ID", id);
		
		jdbcTemplate.update(sql, sqlParameters);
	}
	
	protected void saveIdentityObject(T t)
	{
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(t);
		
		Number key = insert.executeAndReturnKey(parameters);
		
		t.setId(key.longValue());
	}
	
	protected final NamedParameterJdbcTemplate jdbcTemplate;
	protected final SimpleJdbcInsert insert;
	protected final DataFieldMaxValueIncrementer incrementer;

}
