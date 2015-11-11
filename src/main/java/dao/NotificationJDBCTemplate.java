package dao;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Notification;
import model.Student;

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
//
//   public Student getStudent(Integer id) {
//      String SQL = "select * from Student where id = ?";
//      Student student = jdbcTemplateObject.queryForObject(SQL, 
//                        new Object[]{id}, new StudentMapper());
//      return student;
//   }
//
   public Notification getLastNotification(Integer id_user) {
      String SQL = "select * from out_notification where id_user=? ORDER BY date DESC LIMIT 1";
      Notification lastNotif = jdbcTemplateObject.queryForObject(SQL,
    		  new Object[]{id_user},new NotificationMapper());
      return lastNotif;
   }


   public void updateNotif(Integer id_notification, Float latitude, Float longitude, Date date,Integer flag_processing){
      String SQL = "update out_notification set latitude = ?, longitude= ?,date= ?, flag_processing= ? where id_notification = ?";
      jdbcTemplateObject.update(SQL, latitude,longitude,date,flag_processing, id_notification);
      return;
   }

}