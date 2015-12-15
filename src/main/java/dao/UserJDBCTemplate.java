package dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import model.Area;
import model.User;

public class UserJDBCTemplate implements UserDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	// create a user
	public void createUser(String mail,String password,Date date, String token) {
		//	System.out.println("Created Record Name = " + id_user + " Age = " + name_area);
		String SQL = "insert into users (mail,password,date,token) values (?,?,?,?)";
		jdbcTemplateObject.update(SQL,mail,password,date,token);
		return;
	}
	//check if email adress exist
	public Boolean checkMailUser(String mail) {
		String SQL = "select * from users where mail ='"+mail+"'";
		//User user=null;

		//user = jdbcTemplateObject.queryForObject(SQL, 
		//		new Object[]{mail}, new UserMapper());

		System.out.println(SQL);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		jdbcTemplateObject.query(SQL, countCallback);
		int rowCount = countCallback.getRowCount();
		System.out.println("row count : "+rowCount);
		if(rowCount>0){
			return true;
		}else{
			return false;
		}

	}

	//authentification parent
	public String identificationParent(String mail,String password) {
		String SQL = "select * from users where mail ='"+mail+"' and password='"+password+"'";

		System.out.println(SQL);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		jdbcTemplateObject.query(SQL, countCallback);
		int rowCount = countCallback.getRowCount();
		System.out.println("row count : "+rowCount);
		if(rowCount==1){
			User user=null;
			user = jdbcTemplateObject.queryForObject(SQL, 
					new Object[]{}, new UserMapper());
			return user.getToken(); 
		}else{
			return null;
		}

	}

	//authentification children
	public Boolean identificationChildren(String token) {
		String SQL = "select * from users where token ='"+token+"'";

		System.out.println(SQL);
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		jdbcTemplateObject.query(SQL, countCallback);
		int rowCount = countCallback.getRowCount();
		System.out.println("row count : "+rowCount);
		if(rowCount==1){
			return true;
		}else{
			return false;
		}

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