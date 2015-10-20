package dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Student;

public class AreaJDBCTemplate implements AreaDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id_user,String name_area,String label_area, Double latitude, Double longitude, Double rayon) {
		System.out.println("Created Record Name = " + id_user + " Age = " + name_area);
		String SQL = "insert into area (id_user, name_area,latitude, longitude, distance) values (?, ?,?,?,?)";

		jdbcTemplateObject.update( SQL,id_user, name_area,latitude, longitude, rayon);

		return;
	}

}