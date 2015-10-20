package dao;

import java.util.Date;

import javax.sql.DataSource;

	public interface NotificationsDao {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Student table.
	    */
	   public void create(Integer id_user,Integer latitude,Integer longitude,Date date, Integer flag_processing);
	  
}