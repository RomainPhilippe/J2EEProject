package dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import model.Area;
import model.Notification;

public class NotificationJDBCTemplate implements NotificationsDao {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void createNotification(Integer id_user,Float latitude,Float longitude,
		   Date date,Integer flag_processing) {
      String SQL = "insert into out_notification (id_user, latitude,longitude,date,flag_processing)"
      		+ " values (?,?,?,?,?)";
      
      jdbcTemplateObject.update( SQL,id_user,latitude,longitude,date,flag_processing);

      return;
   }
   public Notification getLastNotification(Integer id_user) {
	   
	   //on verifie s'il y a au moins une ligne pour cet id user
	   String SQL = "select * from out_notification where id_user='"+id_user+"'";
	   RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		jdbcTemplateObject.query(SQL, countCallback);
		int rowCount = countCallback.getRowCount();
		if(rowCount>0){
			String SQL2 = "select * from out_notification where id_user=? ORDER BY date DESC LIMIT 1";
		      Notification lastNotif = jdbcTemplateObject.queryForObject(SQL2,
		    		  new Object[]{id_user},new NotificationMapper());
		      return lastNotif;
		}else{
			return new Notification(null,null,null,null,null,null);
		}
   }


   public void updateNotif(Integer id_notification, Float latitude, Float longitude, Date date,Integer flag_processing){
      String SQL = "update out_notification set latitude = ?, longitude= ?,date= ?, flag_processing= ? where id_notification = ?";
      jdbcTemplateObject.update(SQL, latitude,longitude,date,flag_processing, id_notification);
      return;
   }
   
   public List<Notification> listNotificationByIdUser(Integer id_user) {
	      String SQL = "select * from out_notification where id_user="+id_user;
	      List <Notification> notification = jdbcTemplateObject.query(SQL, 
	                                new NotificationMapper());
	      return notification;
	   }
   

}