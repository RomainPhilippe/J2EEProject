package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import model.Notification;

public class NotificationMapper implements RowMapper<Notification> {
   public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Notification notif = new Notification(rs.getInt("id_notification"),
			   rs.getInt("id_user"),rs.getFloat("latitude"),rs.getFloat("longitude"),
			   rs.getDate("date"),rs.getInt("flag_processing"));

	   	   
      return notif;
   }
}