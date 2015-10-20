package model;

import java.util.Date;

public class Notification {

	private Integer id_notification;
	   private Integer id_user;
	   private Integer latitude;
	   private Integer longitude;
	   private Date date;
	   private Integer flag_processing;
	public Integer getId_notification() {
		return id_notification;
	}
	public void setId_notification(Integer id_notification) {
		this.id_notification = id_notification;
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public Integer getLongitude() {
		return longitude;
	}
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getFlag_processing() {
		return flag_processing;
	}
	public void setFlag_processing(Integer flag_processing) {
		this.flag_processing = flag_processing;
	}
	public Notification(Integer id_notification, Integer id_user, Integer latitude, Integer longitude, Date date,
			Integer flag_processing) {
		this.id_notification = id_notification;
		this.id_user = id_user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.flag_processing = flag_processing;
	}
	   
	   
	   
	   
}
