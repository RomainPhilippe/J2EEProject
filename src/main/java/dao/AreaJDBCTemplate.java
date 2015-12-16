package dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Area;

public class AreaJDBCTemplate implements AreaDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer id_user,String name_area,String label_area, Float latitude, Float longitude, Double rayon) {
		System.out.println("Created Record Name = " + id_user + " Area = " + name_area);
		String SQL = "insert into area (id_user, name_area,latitude, longitude, distance) values (?, ?,?,?,?)";
		jdbcTemplateObject.update( SQL,id_user, name_area,latitude, longitude, rayon);
		return;
	}
	
	public List<Area> listAreaByIdUser(Integer id_user) {
	      String SQL = "select * from area where id_user="+id_user;
	      List <Area> area = jdbcTemplateObject.query(SQL, 
	                                new AreaMapper());
	      return area;
	   }
	

}