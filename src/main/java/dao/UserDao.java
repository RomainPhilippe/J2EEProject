package dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import model.Area;
import model.User;

	public interface UserDao {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the User table.
	    */
	   public void createUser(String mail,String password,Date date, String token);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Student table corresponding
	    * to a passed user token.
	    */
	   public User getUser(String token);
	   
	   public Boolean checkMailUser(String mail);
	   
	   public String identificationParent(String mail, String password);
	   
	   public User identificationParent2(String mail, String password);
	   
	   public Boolean identificationChildren(String token);
	   
	   
}