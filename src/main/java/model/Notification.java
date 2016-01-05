package model;

import java.util.Date;

public class Notification {

	private Integer id_notification;
	private Integer id_user;
	private Float latitude;
	private Float longitude;
	private Date date;
	private Integer flag_processing;

	public Notification(Integer id_notification, Integer id_user, Float latitude, Float longitude, Date date,
			Integer flag_processing) {
		this.id_notification = id_notification;
		this.id_user = id_user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.flag_processing = flag_processing;
	}

	// getters

	public Integer getId_notification() {
		return id_notification;
	}

	public Integer getId_user() {
		return id_user;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public Date getDate() {
		return date;
	}

	public Integer getFlag_processing() {
		return flag_processing;
	}

	// setters

	public void setId_notification(Integer id_notification) {
		this.id_notification = id_notification;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setFlag_processing(Integer flag_processing) {
		this.flag_processing = flag_processing;
	}
}
