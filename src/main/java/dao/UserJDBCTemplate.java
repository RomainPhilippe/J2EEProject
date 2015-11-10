package dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Area;
import model.Student;
import model.User;

public class UserJDBCTemplate implements UserDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	// a modifier
	public void createUser(String mail,String password,Date date, String token) {
		//	System.out.println("Created Record Name = " + id_user + " Age = " + name_area);
		//	String SQL = "insert into area (id_user, name_area,latitude, longitude, distance) values (?, ?,?,?,?)";
		//	jdbcTemplateObject.update( SQL,id_user, name_area,latitude, longitude, rayon);
		//	return;
	}

	public User getUser(String token) {
		String SQL = "select * from users where token = ?";
		User user=null;
		try {
			user = jdbcTemplateObject.queryForObject(SQL, 
					new Object[]{token}, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			return new User(null,null,null,null,null);
		}
		
		return user;
	}


}