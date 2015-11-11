package dao;

import java.util.Date;

import javax.sql.DataSource;

import model.Notification;

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
	   public void createNotification(Integer id_user,Float latitude,Float longitude,Date date, Integer flag_processing);
	  
	   
	   public Notification getLastNotification(Integer id_user);
	   
	   public void updateNotif(Integer id_notification, Float latitude, Float longitude, Date date,Integer flag_processing);
		   
	   
}